package com.klef.soa.docker.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CourierBranchRequest {

    @NotBlank(message = "Branch code is required")
    @Size(max = 100, message = "Branch code must not exceed 100 characters")
    private String branchCode;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Manager name is required")
    private String managerName;

    @NotNull(message = "Daily parcel limit is required")
    @Min(value = 1, message = "Daily parcel limit must be at least 1")
    private Integer dailyParcelLimit;

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getDailyParcelLimit() {
        return dailyParcelLimit;
    }

    public void setDailyParcelLimit(Integer dailyParcelLimit) {
        this.dailyParcelLimit = dailyParcelLimit;
    }
}
