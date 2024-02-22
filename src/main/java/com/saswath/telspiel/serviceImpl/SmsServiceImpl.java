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
import com.saswath.telspiel.module.SmsModule;
import com.saswath.telspiel.service.SmsService;
import com.saswath.telspiel.serviceResponse.SmsResponse;

@Service
public class SmsServiceImpl implements SmsService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public SmsResponse getTelspielSms(SmsModule smsModule) {

		SmsResponse SmsResponse = new SmsResponse();

		try {
			String baseURL = "https://api.telsp.in/pushapi/sendbulkmsg";

			String username = "SwatApi";
			String dest = smsModule.getDest();
			String apikey = "uhEqWZKRPDOVmUB91K1VgegFg6pgTuqW";
			String signature = "SASWTF";
			String msgtype = "PM";
			String entityid = "1101415480000073106";
			String templateid = "1107170781932088080";
			int smsotp = smsModule.getOtp();
			String msgtxt = "Greetings%20from%20Saswat%20Finance%2C%20your%20otp%20is%20" + smsotp
					+ "%20for%20the%20verification.%20-%20Saswat%20Finance.%20-SASWAT%20Financial%20Services%20Pvt%20LTD";

			// Build the full URL
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

		return null;
	}

}

