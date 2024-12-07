package com.example.demo.Services;


import com.example.demo.Models.RentalContract;
import com.example.demo.Repositories.RentalContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalContractService {
    private final RentalContractRepository rentalContractRepository;


    public List<RentalContract> findAllRentalContracts() {
        return rentalContractRepository.findAll();
    }


    public void addRentalContract(RentalContract rentalContract) {
         rentalContractRepository.save(rentalContract);
    }

    public void updateRentalContract(Long rentalContractId, RentalContract updatedRentalContract) {
        RentalContract oldRentalContract = rentalContractRepository.findRentalContractById(rentalContractId);
        if (oldRentalContract == null) {
            throw new RuntimeException("Rental contract not found");
        }

        oldRentalContract.setRentalContract_startDate(updatedRentalContract.getRentalContract_startDate());
        oldRentalContract.setRentalContract_endDate(updatedRentalContract.getRentalContract_endDate());
        oldRentalContract.setRentalContract_rentalAmount(updatedRentalContract.getRentalContract_rentalAmount());
        oldRentalContract.setRentalContract_owner_id(updatedRentalContract.getRentalContract_owner_id());
        oldRentalContract.setRentalContract_tenant_id(updatedRentalContract.getRentalContract_tenant_id());

        rentalContractRepository.save(oldRentalContract);

    }



}
