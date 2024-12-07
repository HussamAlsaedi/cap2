package com.example.demo.Services;

import com.example.demo.Models.Tenant;
import com.example.demo.Repositories.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public void addTenant(Tenant tenant) {
        tenantRepository.save(tenant);
    }

    public void updateTenant(Long tenant_id, Tenant updatedTenant) {
        Tenant oldTenant = tenantRepository.findTenantById(tenant_id);

        if (oldTenant == null) {
            throw new RuntimeException("Tenant not found");
        }

        oldTenant.setTenant_name(updatedTenant.getTenant_name());
        oldTenant.setTenant_email(updatedTenant.getTenant_email());
        oldTenant.setUsername(updatedTenant.getUsername());
        oldTenant.setTenant_password(updatedTenant.getTenant_password());
        tenantRepository.save(oldTenant);
    }


    public void deleteTenant(Long id) {
        if (!tenantRepository.existsById(id)) {
            throw new IllegalArgumentException("Tenant not found");
        }
        tenantRepository.deleteById(id);
    }
}
