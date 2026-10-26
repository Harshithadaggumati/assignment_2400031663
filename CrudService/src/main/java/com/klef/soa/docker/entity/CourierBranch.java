package com.klef.soa.docker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CourierBranch_Table")
public class CourierBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cbid")
    private Long id;

    @Column(name = "cbranchCode", length = 100, nullable = false, unique = true)
    private String branchCode;

    @Column(name = "ccity", length = 100, nullable = false)
    private String city;

    @Column(name = "cmanagerName", length = 100, nullable = false)
    private String managerName;

    @Column(name = "cdailyParcelLimit", nullable = false)
    private Integer dailyParcelLimit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "CourierBranch [id=" + id + ", branchCode=" + branchCode
                + ", city=" + city + ", managerName=" + managerName
                + ", dailyParcelLimit=" + dailyParcelLimit + "]";
    }
}

