package com.bank.assigmenBNI.service;

import com.bank.assigmenBNI.model.Bank;

import java.util.List;

public interface BankService {
    List<Bank> findAllBank();

    Bank findSpecificBank(String ktp);

    Bank createDataBank(Bank bank);

    Bank updateDataBank(Bank bank);

    void deleteDataBank(String ktp);

    boolean isKtpExist(String ktp);
}
