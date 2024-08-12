package org.example.dto.viettelpost;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ViettelPostLoginRequestDto {
    @JsonProperty("USERNAME")
    private String username;
    @JsonProperty("PASSWORD")
    private String password;

    public ViettelPostLoginRequestDto(String USERNAME, String PASSWORD) {
        this.username = USERNAME;
        this.password = PASSWORD;
    }

    public ViettelPostLoginRequestDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String USERNAME) {
        this.username = USERNAME;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String PASSWORD) {
        this.password = PASSWORD;
    }
}
