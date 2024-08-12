package org.example.service;

import org.example.dto.viettelpost.VtpostListPostOffficeWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VtpostService {
    String  login();
    List<VtpostListPostOffficeWrapper.VtPostOffice> getListPostOffice(String token);
}
