package com.example.demo.Repositories;

import com.example.demo.Models.Owner;
import com.example.demo.Models.RentalContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentalContractRepository extends JpaRepository<RentalContract, Long> {

    @Query("select R from RentalContract R where R.rentalContract_id= :rentalContract_id")
    RentalContract findRentalContractById(@Param("rentalContract_id") Long rentalContract_id);
}
