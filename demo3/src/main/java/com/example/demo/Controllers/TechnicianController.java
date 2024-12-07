package com.example.demo.Controllers;

import com.example.demo.ApiResponse.ApiResponse;
import com.example.demo.Models.Technician;
import com.example.demo.Services.TechnicianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/technicians")
@RequiredArgsConstructor
public class TechnicianController {

    private final TechnicianService technicianService;

    @GetMapping("/get-all-technicians")
    public List<Technician> getAllTechnicians() {
        return technicianService.findAllTechnicians();
    }

    @PostMapping("/add-technician")
    public ResponseEntity<ApiResponse> addTechnician(
           @RequestBody  @Valid
           Technician technician,
           Errors errors) {

        if (errors.hasErrors()) {
                String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }

        technicianService.addTechnician(technician);
        return ResponseEntity.status(200).body(new ApiResponse("Technician added successfully"));
    }

@PutMapping("/update-technician/{technicianId}")
    public ResponseEntity<ApiResponse> updateTechnician(
            @PathVariable Long technicianId,
            @RequestBody  @Valid
            Technician technician,
            Errors errors) {

        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }

        technicianService.updateTechnician(technicianId, technician);
        return ResponseEntity.status(200).body(new ApiResponse("successfully updated Technician with id " + technicianId));
    }

    @DeleteMapping("/delete-technician/{technicianId}")
    public ResponseEntity<ApiResponse> deleteTechnician(@PathVariable Long technicianId) {
        technicianService.deleteTechnician(technicianId);
        return ResponseEntity.status(200).body(new ApiResponse("successfully deleted technician with id: " + technicianId));
    }



}
