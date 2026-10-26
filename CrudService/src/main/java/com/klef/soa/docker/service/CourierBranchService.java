
package com.klef.soa.docker.service;

import java.util.List;

import com.klef.soa.docker.dto.CourierBranchRequest;
import com.klef.soa.docker.dto.CourierBranchResponse;

public interface CourierBranchService {

    CourierBranchResponse addCourierBranch(CourierBranchRequest request);

    CourierBranchResponse updateCourierBranch(Long id, CourierBranchRequest request);

    void deleteCourierBranch(Long id);

    CourierBranchResponse displayById(Long id);

    List<CourierBranchResponse> displayAllCourierBranches();
}
