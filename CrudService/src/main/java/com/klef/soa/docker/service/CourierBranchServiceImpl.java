package com.klef.soa.docker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.docker.dto.CourierBranchRequest;
import com.klef.soa.docker.dto.CourierBranchResponse;
import com.klef.soa.docker.entity.CourierBranch;
import com.klef.soa.docker.exception.ResourceNotFoundException;
import com.klef.soa.docker.repository.CourierBranchRepository;

@Service
public class CourierBranchServiceImpl implements CourierBranchService {

    @Autowired
    private CourierBranchRepository repo;

    @Override
    public CourierBranchResponse addCourierBranch(CourierBranchRequest request) {
        CourierBranch branch = new CourierBranch();

        branch.setBranchCode(request.getBranchCode());
        branch.setCity(request.getCity());
        branch.setManagerName(request.getManagerName());
        branch.setDailyParcelLimit(request.getDailyParcelLimit());

        CourierBranch saved = repo.save(branch);

        return new CourierBranchResponse(saved);
    }

    @Override
    public CourierBranchResponse updateCourierBranch(Long id, CourierBranchRequest request) {
        CourierBranch branch = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Courier Branch ID not found: " + id));

        branch.setBranchCode(request.getBranchCode());
        branch.setCity(request.getCity());
        branch.setManagerName(request.getManagerName());
        branch.setDailyParcelLimit(request.getDailyParcelLimit());

        CourierBranch saved = repo.save(branch);

        return new CourierBranchResponse(saved);
    }

    @Override
    public void deleteCourierBranch(Long id) {
        CourierBranch branch = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Courier Branch ID not found: " + id));

        repo.delete(branch);
    }

    @Override
    public CourierBranchResponse displayById(Long id) {
        CourierBranch branch = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Courier Branch ID not found: " + id));

        return new CourierBranchResponse(branch);
    }

    @Override
    public List<CourierBranchResponse> displayAllCourierBranches() {
        return repo.findAll()
                .stream()
                .map(CourierBranchResponse::new)
                .toList();
    }
}
