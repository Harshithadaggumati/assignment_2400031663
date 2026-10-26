package com.klef.soa.docker.dto;

import com.klef.soa.docker.entity.CourierBranch;

public class CourierBranchResponse {

    private Long id;
    private String branchCode;
    private String city;
    private String managerName;
    private Integer dailyParcelLimit;

    public CourierBranchResponse(CourierBranch branch) {
        this.id = branch.getId();
        this.branchCode = branch.getBranchCode();
        this.city = branch.getCity();
        this.managerName = branch.getManagerName();
        this.dailyParcelLimit = branch.getDailyParcelLimit();
    }

    public Long getId() {
        return id;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getCity() {
        return city;
    }

    public String getManagerName() {
        return managerName;
    }

    public Integer getDailyParcelLimit() {
        return dailyParcelLimit;
    }
}
