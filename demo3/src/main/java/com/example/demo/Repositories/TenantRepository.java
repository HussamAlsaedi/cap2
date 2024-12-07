package com.example.demo.Repositories;

import com.example.demo.Models.Owner;
import com.example.demo.Models.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

    @Query("select t from Tenant t where t.tenant_id= :tenant_id")
    Tenant findTenantById(@Param("tenant_id") Long tenant_id);
}
