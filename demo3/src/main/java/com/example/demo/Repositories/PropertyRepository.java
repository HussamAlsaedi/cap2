package com.example.demo.Repositories;

import com.example.demo.Models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("select p from Property p where p.property_id= :propertyId")
    Property findPropertyById(@Param("propertyId") Long propertyId);
}
