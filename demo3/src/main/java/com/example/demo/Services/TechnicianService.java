package com.example.demo.Services;

import com.example.demo.Models.Technician;
import com.example.demo.Repositories.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class TechnicianService {
    private final TechnicianRepository technicianRepository;

    public List<Technician> findAllTechnicians() {
        return technicianRepository.findAll();
    }

    public void addTechnician(Technician technician) {
        technicianRepository.save(technician);
    }

    public void updateTechnician(Long technicianId, Technician updatedTechnician) {
        Technician oldTechnician = technicianRepository.findTechnicianById(technicianId);
            if (oldTechnician == null) {
                throw new RuntimeException("Technician not found");
            }

        oldTechnician.setTechnician_name(updatedTechnician.getTechnician_name());
        oldTechnician.setTechnician_email(updatedTechnician.getTechnician_email());
        oldTechnician.setUsername(updatedTechnician.getUsername());
        oldTechnician.setTechnician_password(updatedTechnician.getTechnician_password());
        oldTechnician.setTechnician_role(updatedTechnician.getTechnician_role());

        technicianRepository.save(oldTechnician);
    }

    public void deleteTechnician(Long technicianId) {
        Technician technician = technicianRepository.findTechnicianById(technicianId);
        if (technician == null) {
            throw new RuntimeException("Technician not found");
        }
        technicianRepository.delete(technician);
    }
}
