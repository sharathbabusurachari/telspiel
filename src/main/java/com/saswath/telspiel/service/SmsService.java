package com.saswath.telspiel.service;

import com.saswath.telspiel.module.SmsModule;
import com.saswath.telspiel.serviceResponse.SmsResponse;

public interface SmsService {

	public SmsResponse getTelspielSms(SmsModule smsModule);

}