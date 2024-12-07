package com.example.demo.Controllers;

import com.example.demo.ApiResponse.ApiResponse;
import com.example.demo.Models.Property;
import com.example.demo.Services.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    // Endpoint Get all properties
    @GetMapping("/get-all-properties")
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    // Endpoint Add a new property
    @PostMapping("/add-property")
    public ResponseEntity<ApiResponse> addProperty(@RequestBody @Valid Property property, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage) );
        }
        propertyService.addProperty(property);
        return ResponseEntity.status(201).body(new ApiResponse("successfully added property"));
    }

    // Endpoint Update an existing property by propertyId
    @PutMapping("/Update-property/{propertyId}")
    public ResponseEntity<ApiResponse> updateProperty(@PathVariable Long propertyId, @RequestBody @Valid Property updatedProperty, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage) );
        }
        propertyService.updateProperty(propertyId, updatedProperty);
         return ResponseEntity.status(200).body(new ApiResponse("successfully updated property with id: " + propertyId));

    }

    // Endpoint Delete a property by its ID
    @DeleteMapping("/delete-property/{propertyId}")
    public ResponseEntity<ApiResponse> deleteProperty(@PathVariable Long propertyId) {
        propertyService.deleteProperty(propertyId);
        return ResponseEntity.status(200).body(new ApiResponse("successfully deleted property with id: " + propertyId));

    }

}
