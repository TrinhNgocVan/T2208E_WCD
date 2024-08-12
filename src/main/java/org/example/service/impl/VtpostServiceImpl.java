package org.example.service.impl;

import org.example.config.properties.ViettelPostApiProperties;
import org.example.dto.viettelpost.ViettelPostLoginRequestDto;
import org.example.dto.viettelpost.ViettelPostLoginResponseWrapper;
import org.example.dto.viettelpost.VtpostListPostOffficeWrapper;
import org.example.service.VtpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class VtpostServiceImpl implements VtpostService {

    @Autowired
    private ViettelPostApiProperties viettelPostApiProps;

    @Override
    public String login() {
        String token  = null;
        try{
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
            ResponseEntity<ViettelPostLoginResponseWrapper> resEntity =  restTemplate.exchange(
                    url,  // path url to call
                    HttpMethod.POST, // http method
                    requestEntity, // header and body
                    ViettelPostLoginResponseWrapper.class); // response type
            if(resEntity.getStatusCode() == HttpStatus.OK){
                ViettelPostLoginResponseWrapper res = resEntity.getBody();
                if(!res.getError() && res.getStatus().equalsIgnoreCase("200")
                        && !StringUtils.isEmpty(res.getData().getToken())
                ){
                    token = res.getData().getToken();
                }
            }
        } catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return token;
    }

    @Override
    public List<VtpostListPostOffficeWrapper.VtPostOffice> getListPostOffice(String token) {
        String url  = viettelPostApiProps.getBaseUrl()+viettelPostApiProps.getGetListPostOfficePath();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("token",token);
        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<VtpostListPostOffficeWrapper> resEntity =  restTemplate.exchange(
                url,  // path url to call
                HttpMethod.GET, // http method
                requestEntity, // header and body
                VtpostListPostOffficeWrapper.class); // response type
        if(resEntity.getStatusCode() == HttpStatus.OK){
            VtpostListPostOffficeWrapper res = resEntity.getBody();
            return res.getPostOffices();
        }
        return null;
    }
}
