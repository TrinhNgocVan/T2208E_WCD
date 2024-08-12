package org.example.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViettelPostApiProperties {
    @Value("${viettelpost.api.base-url}")
    private String baseUrl;
    @Value("${viettelpost.api.login-path}")
    private String loginPath;
    @Value("${viettelpost.api.get-list-post-office-path}")
    private String getListPostOfficePath;
    @Value("${viettelpost.api.username}")
    private String username;
    @Value("${viettelpost.api.password}")
    private String password;

    public String getGetListPostOfficePath() {
        return getListPostOfficePath;
    }

    public void setGetListPostOfficePath(String getListPostOfficePath) {
        this.getListPostOfficePath = getListPostOfficePath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getLoginPath() {
        return loginPath;
    }

    public void setLoginPath(String loginPath) {
        this.loginPath = loginPath;
    }
}
