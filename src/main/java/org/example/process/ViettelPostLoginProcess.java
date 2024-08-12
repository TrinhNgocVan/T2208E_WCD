package org.example.process;


import org.example.service.VtpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ViettelPostLoginProcess {

    @Autowired
    private VtpostService vtpostService;

    @Scheduled(fixedDelay = 1000)
    public void testViettelPostlogin(){
        vtpostService.login();
    }


}
