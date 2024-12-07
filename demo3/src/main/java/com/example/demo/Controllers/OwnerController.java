package com.example.demo.Controllers;


import com.example.demo.ApiResponse.ApiResponse;
import com.example.demo.Models.Owner;
import com.example.demo.Services.OwnerService;
import jakarta.validation.Valid;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    // Endpoint Retrieve all owners
    @GetMapping("/get-all-owners")
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.getAllOwners();
        if (owners.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(owners);
    }

    @PostMapping("/add-owner")
    public ResponseEntity<ApiResponse> addOwner(@RequestBody @Valid  Owner owner, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }
        ownerService.addOwner(owner);
        return  ResponseEntity.status(200).body(new ApiResponse("Successfully added owner."));
    }


    // Update an existing owner by ownerId
    @PutMapping("/update-owner/{ownerId}")
    public ResponseEntity<ApiResponse> updateOwner(@PathVariable Long ownerId, @RequestBody @Valid Owner ownerUpdate, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }
         ownerService.updateOwner(ownerId, ownerUpdate);
        return ResponseEntity.status(200).body(new ApiResponse("successfully updated Owner with id: " + ownerId));

    }

    // Delete an existing owner by ownerId
    @DeleteMapping("/delete-owner{ownerId}")
    public ResponseEntity<ApiResponse> deleteOwner(@PathVariable Long ownerId) {
        ownerService.deleteOwner(ownerId);
        return ResponseEntity.status(200).body(new ApiResponse("successfully deleted Owner with id: " + ownerId));
    }
}
