package com.example.demo.Repositories;

import com.example.demo.Models.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ServiceRequestRepository extends JpaRepository<ServiceRequest,Long> {
}
