package com.bank.assigmenBNI.repository;

import com.bank.assigmenBNI.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank,Long> {
}
