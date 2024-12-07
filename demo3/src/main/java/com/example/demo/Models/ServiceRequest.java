package com.example.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ServiceRequest_id;

    @NotBlank(message = "Service type is mandatory")
    @Column(nullable = false)
    private String ServiceRequest_serviceType; // Example: "Plumbing", "Electricity"

    @NotBlank(message = "Request date is mandatory")
    @Column(nullable = false)
    private LocalDateTime ServiceRequest_requestDate;

    @NotNull(message = "tenant_id is mandatory")
    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @NotNull(message = "technician_id is mandatory")
    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @Column(updatable = false, nullable = false)
    private LocalDateTime ServiceRequest_createdAt;

    @PrePersist
    protected void onCreate() {
        this.ServiceRequest_createdAt = LocalDateTime.now();
    }
}
