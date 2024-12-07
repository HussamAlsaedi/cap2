package com.example.demo.Services;

import com.example.demo.ApiResponse.ApiException;
import com.example.demo.Models.Owner;
import com.example.demo.Models.Property;
import com.example.demo.Repositories.OwnerRepository;
import com.example.demo.Repositories.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;

    // Retrieve all properties
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // Add a new property
    public void addProperty(Property property) {
        Owner owner1 = ownerRepository.findOwnerById(property.getProperty_ownerId());

        if (owner1 == null) {
            throw new ApiException("owner not found");
        }

        if (property.getProperty_createdAt() == null) {
            property.setProperty_createdAt(property.getProperty_createdAt());
        }
          propertyRepository.save(property);
    }

    // Endpoint Update an existing property by propertyId
    public void updateProperty(Long propertyId, Property updatedProperty) {
        Property oldProperty = propertyRepository.findPropertyById(propertyId);

        if (oldProperty == null) {
            throw new ApiException("Property not found");
        }
        oldProperty.setProperty_location(updatedProperty.getProperty_location());
        oldProperty.setProperty_price(updatedProperty.getProperty_price());
        oldProperty.setProperty_type(updatedProperty.getProperty_type());
        oldProperty.setProperty_area(updatedProperty.getProperty_area());
        oldProperty.setProperty_isRented(updatedProperty.isProperty_isRented());
        oldProperty.setProperty_ownerId(updatedProperty.getProperty_ownerId());
        oldProperty.setProperty_description(updatedProperty.getProperty_description());
         propertyRepository.save(oldProperty);
    }


    // Delete a property by its propertyId
    public void deleteProperty(Long propertyId) {
        Property deletedProperty = propertyRepository.findPropertyById(propertyId);

        if (deletedProperty == null) {
            throw new ApiException("Property not found");
        }

        propertyRepository.delete(deletedProperty);
    }
}
