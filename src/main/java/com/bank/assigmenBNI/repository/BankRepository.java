package com.bank.assigmenBNI.repository;

import com.bank.assigmenBNI.model.Bank;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface BankRepository extends JpaRepository<Bank,Long> {
    Bank findByNoKtp(String noKtp);

    // Untuk mengecek apakah KTP sudah ada
    boolean existsByNoKtp(String noKtp);

    // Untuk menghapus berdasarkan KTP
    @Transactional
    @Modifying
    void deleteByNoKtp(String noKtp);
}
