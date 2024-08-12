package org.example.dto.viettelpost;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VtpostListPostOffficeWrapper {
    private String status;
    private Boolean error;
    private String message;
    @JsonProperty("data")
    private List<VtPostOffice> postOffices;
    public class VtPostOffice{
        @JsonProperty("TEN_TINH")
        private String province;
        @JsonProperty("TEN_QUANHUYEN")
        private String district;
        @JsonProperty("TEN_PHUONGXA")
        private String ward;
        @JsonProperty("MA_BUUCUC")
        private String postCode;
        @JsonProperty("TEN_BUUCUC")
        private String postName;
        @JsonProperty("DIA_CHI")
        private String address;
        @JsonProperty("LATITUDE")
        private String latitude;
        @JsonProperty("LONGITUDE")
        private String longtitude;
        @JsonProperty("PHUTRACH")
        private String personInCharge;
        @JsonProperty("DIEN_THOAI")
        private String phone;
        @JsonProperty("PHUTRACHPHONE")
        private String personInChargePhone;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getWard() {
            return ward;
        }

        public void setWard(String ward) {
            this.ward = ward;
        }

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public String getPostName() {
            return postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongtitude() {
            return longtitude;
        }

        public void setLongtitude(String longtitude) {
            this.longtitude = longtitude;
        }

        public String getPersonInCharge() {
            return personInCharge;
        }

        public void setPersonInCharge(String personInCharge) {
            this.personInCharge = personInCharge;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPersonInChargePhone() {
            return personInChargePhone;
        }

        public void setPersonInChargePhone(String personInChargePhone) {
            this.personInChargePhone = personInChargePhone;
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

    public List<VtPostOffice> getPostOffices() {
        return postOffices;
    }

    public void setPostOffices(List<VtPostOffice> postOffices) {
        this.postOffices = postOffices;
    }

}
