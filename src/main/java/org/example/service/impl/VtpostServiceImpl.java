package org.example.service.impl;

import org.example.config.properties.ViettelPostApiProperties;
import org.example.dto.viettelpost.ViettelPostLoginRequestDto;
import org.example.dto.viettelpost.ViettelPostLoginResponseWrapper;
import org.example.service.VtpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class VtpostServiceImpl implements VtpostService {

    @Autowired
    private ViettelPostApiProperties viettelPostApiProps;




    @Override
    public boolean login() {
        RestTemplate restTemplate = new RestTemplate();
        // url login:
        String url = viettelPostApiProps.getBaseUrl()+ viettelPostApiProps.getLoginPath();
        ViettelPostLoginRequestDto request = new ViettelPostLoginRequestDto();
        request.setUsername(viettelPostApiProps.getUsername());
        request.setPassword(viettelPostApiProps.getPassword());
        // only pass body
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ViettelPostLoginRequestDto> requestEntity = new HttpEntity<>(request,httpHeaders);
        System.err.println(requestEntity.toString());
        ResponseEntity<ViettelPostLoginResponseWrapper> responseStr =  restTemplate.exchange(
                url,  // path url to call
                HttpMethod.POST, // http method
                requestEntity, // header and body
                ViettelPostLoginResponseWrapper.class); // response type
        System.err.println(responseStr);
        return false;
    }
}
