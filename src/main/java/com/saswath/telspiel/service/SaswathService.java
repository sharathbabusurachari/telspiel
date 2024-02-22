package com.saswath.telspiel.service;

import com.saswath.telspiel.module.Telspiel;
import com.saswath.telspiel.serviceResponse.SmsResponse;

public interface SaswathService {

	public SmsResponse getSaswathStatus(Telspiel telspiel);

}
