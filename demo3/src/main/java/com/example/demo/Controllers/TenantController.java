package com.example.demo.Controllers;

import com.example.demo.ApiResponse.ApiResponse;
import com.example.demo.Models.Tenant;
import com.example.demo.Services.TenantService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @GetMapping("/get-all-tenants")
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @PostMapping("/add-tenant")
    public ResponseEntity<ApiResponse> addTenant(
            @RequestBody @Valid
            Tenant tenant,
            Errors errors
    ) {

        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }
        tenantService.addTenant(tenant);
        return ResponseEntity.ok(new ApiResponse("Successfully added tenant"));
    }

    @PutMapping("/update-tenant-By-tenantId/{tenantId}")
    public ResponseEntity<ApiResponse> updateTenant(
            @PathVariable Long tenantId,
            @RequestBody @Valid
            Tenant tenant,
            Errors errors
    )
    {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }
        tenantService.updateTenant(tenantId, tenant);
        return ResponseEntity.ok(new ApiResponse("Successfully updated tenant with id" + tenantId));
    }

    @DeleteMapping("/delete-tenant-by-tenantId/{tenantId}")
    public ResponseEntity<ApiResponse> deleteTenant(@PathVariable Long tenantId) {
        tenantService.deleteTenant(tenantId);
        return ResponseEntity.status(200).body(
                new ApiResponse("Successfully deleted tenant with id" + tenantId));
    }

}
