package com.example.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Check(constraints = "technician_role IN ('سباك', 'كهربائي', 'عامل نظافة')")
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technician_id;

    @NotEmpty(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 20 characters")
    @Column(nullable = false, length = 20)
    private String technician_name;

    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(min = 1, max = 50, message = "Email must be between 1 and 50 characters")
    @Column(nullable = false, unique = true, length = 60)
    private String technician_email;

    @NotEmpty(message = "username is mandatory")
    @Size(min = 1, max = 10, message = "username must be between 1 and 10 characters")
    @Column(nullable = false, unique = true, length = 10)
    private String username;

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase letter, one digit, and one special character"
    )    @Column(nullable = false, length =100)
    private String technician_password;

    @NotEmpty(message = "Role is mandatory")
    @Size(min = 1, max = 10, message = "Role must be between 1 and 10 characters")
    @Pattern(regexp = "سباك|كهربائي|عامل نظافة", message = "Technician role must be سباك, كهربائي, or عامل نظافة")
    @Column(nullable = false, unique = true, length = 10)
    private String technician_role;



    @Column(updatable = false, nullable = false)
    private LocalDateTime technician_createdAt;

    @PrePersist
    protected void onCreate() {
        this.technician_createdAt = LocalDateTime.now();
    }
}
