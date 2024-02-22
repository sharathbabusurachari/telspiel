package com.saswath.telspiel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saswath.telspiel.module.SmsModule;
import com.saswath.telspiel.module.Telspiel;
import com.saswath.telspiel.service.SaswathService;
import com.saswath.telspiel.service.SmsService;
import com.saswath.telspiel.serviceResponse.SmsResponse;

@RestController
@RequestMapping("/saswath")
public class TelspielController {

	@Autowired
	SaswathService saswathService;

	@Autowired
	SmsService smsService;

	@PostMapping(value = "message", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmsResponse> saswathStatus(@RequestBody Telspiel telspiel) {

		SmsResponse smsResponse = saswathService.getSaswathStatus(telspiel);

		return new ResponseEntity<>(smsResponse, HttpStatus.OK);

	}

	@PostMapping(value = "otp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmsResponse> telspielSms(@RequestBody SmsModule smsModule) {

		SmsResponse smsResponse = smsService.getTelspielSms(smsModule);

		return new ResponseEntity<>(smsResponse, HttpStatus.OK);

	}

}
