package com.saswath.telspiel.serviceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saswath.telspiel.module.Telspiel;
import com.saswath.telspiel.service.SaswathService;
import com.saswath.telspiel.serviceResponse.SmsResponse;

@Service
public class SaswathServiceImpl implements SaswathService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public SmsResponse getSaswathStatus(Telspiel telspiel) {

		SmsResponse SmsResponse = new SmsResponse();
		try {
			String baseURL = "https://api.telsp.in/pushapi/sendbulkmsg";
			String username = telspiel.getUsername();
			String dest = telspiel.getDest();
			String apikey = telspiel.getApikey();
			String signature = telspiel.getSignature();
			String msgtype = telspiel.getMsgtype();
			String entityid = telspiel.getEntityid();
			String templateid = telspiel.getTemplateid();

			String msgtxt = "Dear%20Rahul%2C%20Welcome%20to%20Saswat.%20Your%20application%20ID%20%3A%20A0932123%20and%20customer%20ID%20%3A%20CID0012001.%20If%20you%20have%20any%20questions%20reach-out%20to%20your%20sales%20officer.%20-Saswat%20Finance";
			String fullURL = String.format(
					"%s?username=%s&dest=%s&apikey=%s&signature=%s&msgtype=%s&msgtxt=%s&entityid=%s&templateid=%s",
					baseURL, username, dest, apikey, signature, msgtype, msgtxt, entityid, templateid);

			URL url = new URL(fullURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// Get the response
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			String json = response.toString();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(json);
			String code = jsonNode.get(0).get("code").asText();
			String desc = jsonNode.get(0).get("desc").asText();
			if (code.equalsIgnoreCase("6001")) {
				SmsResponse.setStatus("SUCCESS");
			} else {
				SmsResponse.setStatus("FAILURE");
			}
			SmsResponse.setCode(code);
			SmsResponse.setDesc(desc);

			return SmsResponse;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SmsResponse;

	}

}
