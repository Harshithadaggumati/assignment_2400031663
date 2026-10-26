
package com.klef.soa.docker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.soa.docker.dto.CourierBranchRequest;
import com.klef.soa.docker.dto.CourierBranchResponse;
import com.klef.soa.docker.service.CourierBranchService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/CourierBranch")
public class CourierBranchController {

    @Autowired
    private CourierBranchService service;

    @GetMapping("/")
    public String demo() {
        return "Courier Branch Service is running";
    }

    @PostMapping("/add")
    public ResponseEntity<CourierBranchResponse> addCourierBranch(
            @Valid @RequestBody CourierBranchRequest request) {
        CourierBranchResponse response = service.addCourierBranch(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/displayall")
    public ResponseEntity<List<CourierBranchResponse>> displayAllCourierBranches() {
        List<CourierBranchResponse> branches = service.displayAllCourierBranches();
        return ResponseEntity.ok(branches);
    }

    @GetMapping("/display/{id}")
    public ResponseEntity<CourierBranchResponse> displayById(@PathVariable Long id) {
        CourierBranchResponse response = service.displayById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CourierBranchResponse> updateCourierBranch(
            @PathVariable Long id,
            @Valid @RequestBody CourierBranchRequest request) {
        CourierBranchResponse response = service.updateCourierBranch(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourierBranch(@PathVariable Long id) {
        service.deleteCourierBranch(id);
        return ResponseEntity.ok("Courier branch deleted successfully");
    }
}
