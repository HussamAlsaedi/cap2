package com.example.demo.Services;

import com.example.demo.ApiResponse.ApiException;
import com.example.demo.Models.Owner;
import com.example.demo.Repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
  import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;



    // Retrieve all Owners
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }


    public void addOwner(Owner owner) {

       /* // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(owner.getOwner_password());
        owner.setOwner_password(hashedPassword);*/
        ownerRepository.save(owner);
    }

   //  Update an existing owner by ownerId
    public Owner updateOwner(Long ownerId, Owner ownerUpdate) {

        Owner oldOwner = ownerRepository.findOwnerById(ownerId);

        if (oldOwner == null) {
            throw new ApiException("Owner not found");
        }

        oldOwner.setOwner_name(ownerUpdate.getOwner_name());
        oldOwner.setOwner_email(ownerUpdate.getOwner_email());
        oldOwner.setOwner_password(ownerUpdate.getOwner_password());
        return ownerRepository.save(oldOwner);
    }

    // Delete an existing owner by ownerId
    public void deleteOwner(Long ownerId) {

         Owner deleteOwner = ownerRepository.findOwnerById(ownerId);

         if (deleteOwner == null) {
             throw new ApiException("Owner not found");
         }
        ownerRepository.deleteById(ownerId);
    }
}
