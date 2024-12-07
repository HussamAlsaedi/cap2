package com.example.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RentalContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalContract_id;

    @NotEmpty(message = "startDate Id is mandatory")
    @Column(nullable = false)
    private LocalDateTime rentalContract_startDate;

    @NotEmpty(message = "endDate Id is mandatory")
    @Column(nullable = false)
    private LocalDateTime rentalContract_endDate;

    @NotNull(message = "Rental Amount is mandatory")
    @Positive(message = "Rental Amount must be Positive number")
    @Column(nullable = false)
    private double rentalContract_rentalAmount;

    @ManyToOne
    @JoinColumn(name = "rentalContract_owner_id", nullable = false)
    private Owner rentalContract_owner_id;


    @ManyToOne
    @JoinColumn(name = "rentalContract_tenant_id", nullable = false)
    private Tenant rentalContract_tenant_id;

    @Column(updatable = false, nullable = false)
    private LocalDateTime rentalContract_createdAt;

    @PrePersist
    protected void onCreate() {
        this.rentalContract_createdAt = LocalDateTime.now();
    }

}
