package com.saswath.telspiel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.saswath.telspiel.controller.TelspielController;
import com.saswath.telspiel.module.SmsModule;
import com.saswath.telspiel.module.Telspiel;
import com.saswath.telspiel.service.SaswathService;
import com.saswath.telspiel.service.SmsService;
import com.saswath.telspiel.serviceResponse.SmsResponse;

public class TelspielControllerTest {

    @Test
    public void testSaswathStatus() {
        // Create a mock of SaswathService
        SaswathService saswathService = mock(SaswathService.class);
        
        // Create a sample Telspiel object
        Telspiel telspiel = new Telspiel();
        // Mock the behavior of saswathService
        SmsResponse expectedResponse = new SmsResponse();
        when(saswathService.getSaswathStatus(telspiel)).thenReturn(expectedResponse);
        
        // Create an instance of TelspielController with the mocked SaswathService
        TelspielController controller = new TelspielController();
        
        // Invoke the controller method
        ResponseEntity<SmsResponse> responseEntity = controller.saswathStatus(telspiel);
        
        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testTelspielSms() {
        // Create a mock of SmsService
        SmsService smsService = mock(SmsService.class);
        
        // Create a sample SmsModule object
        SmsModule smsModule = new SmsModule();
        // Mock the behavior of smsService
        SmsResponse expectedResponse = new SmsResponse();
        when(smsService.getTelspielSms(smsModule)).thenReturn(expectedResponse);
        
        // Create an instance of TelspielController with the mocked SmsService
        TelspielController controller = new TelspielController();
        
        // Invoke the controller method
        ResponseEntity<SmsResponse> responseEntity = controller.telspielSms(smsModule);
        
        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}
