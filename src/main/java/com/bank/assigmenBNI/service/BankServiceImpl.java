package com.bank.assigmenBNI.service;

import com.bank.assigmenBNI.model.Bank;
import com.bank.assigmenBNI.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService{

    private BankRepository bankRepository;

    @Override
    public List<Bank> findAllBank() {
        return bankRepository.findAll();
    }

    @Override
    public Bank findSpecificBank(String ktp) {
        return bankRepository.findByNoKtp(ktp);
    }

    @Override
    public Bank createDataBank(Bank bank) {
        if(bankRepository.existsByNoKtp(bank.getNoKtp())) {
            throw new RuntimeException("This No Ktp is already exist");
        }
        return bankRepository.save(bank);
    }

    @Override
    public Bank updateDataBank(Bank bank) {
        // Gunakan findByNoKtp. Karena findByNoKtp return Bank (bukan Optional),
        // kita bungkus dengan Optional.ofNullable agar logic .map() tetap jalan.
        return Optional.ofNullable(bankRepository.findByNoKtp(bank.getNoKtp()))
                .map(existingBank -> {
                    if (bank.getNama_lengkap() != null) existingBank.setNama_lengkap(bank.getNama_lengkap());
                    if (bank.getAlamat() != null) existingBank.setAlamat(bank.getAlamat());
                    if (bank.getNo_hp() != null) existingBank.setNo_hp(bank.getNo_hp());
                    if (bank.getTempat_lahir() != null) existingBank.setTempat_lahir(bank.getTempat_lahir());
                    if (bank.getTanggal_lahir() != null) existingBank.setTanggal_lahir(bank.getTanggal_lahir());

                    return bankRepository.save(existingBank);
                })
                .orElseThrow(() -> new RuntimeException("Data Bank dengan KTP " + bank.getNoKtp() + " tidak ditemukan"));
    }
    @Override
    public void deleteDataBank(String ktp) {
        bankRepository.deleteByNoKtp(ktp);
    }

    @Override
    public boolean isKtpExist(String ktp) {
        return bankRepository.existsByNoKtp(ktp);
    }

}
