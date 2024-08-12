package org.example.dto.viettelpost;

public class ViettelPostLoginResponseWrapper {
    private String status;
    private Boolean error;
    private String message;
    private VtPostLoginResponseDto data;
    public class VtPostLoginResponseDto {
        private long userId;
        private String token;
        private String partner;
        private String phone;
        private String postcode;
        private long expired;
        private String encrypted;
        private int source;
        private Boolean infoUpdated;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getPartner() {
            return partner;
        }

        public void setPartner(String partner) {
            this.partner = partner;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public long getExpired() {
            return expired;
        }

        public void setExpired(long expired) {
            this.expired = expired;
        }

        public String getEncrypted() {
            return encrypted;
        }

        public void setEncrypted(String encrypted) {
            this.encrypted = encrypted;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public Boolean getInfoUpdated() {
            return infoUpdated;
        }

        public void setInfoUpdated(Boolean infoUpdated) {
            this.infoUpdated = infoUpdated;
        }
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VtPostLoginResponseDto getData() {
        return data;
    }

    public void setData(VtPostLoginResponseDto data) {
        this.data = data;
    }

    //        "userId": 13032905,
//                "token": "eyJhbGciOiJFUzI1NiJ9.eyJzdWIiOiIwOTY5OTQ3MzQyIiwiVXNlcklkIjoxMzAzMjkwNSwiRnJvbVNvdXJjZSI6NSwiVG9rZW4iOiI4WTJGOFdIMTJLQ1hVMTE2NiIsImV4cCI6MTcyMzU1NDM0NCwiUGFydG5lciI6MTMwMzI5MDV9.YKgTwHoXdPr_LTEgl12MyDiuiuJoyPQ9my41Q8pm1SosY5P73zDQ8sySxkCQmvHFzrwpRqLzW_SkVdrgT-zjIA",
//                "partner": 13032905,
//                "phone": "0969947342",
//                "postcode": null,
//                "expired": 1723554344992,
//                "encrypted": null,
//                "source": 5,
//                "infoUpdated": true
}
