package com.bank.assigmenBNI.service;

import com.bank.assigmenBNI.model.Bank;
import com.bank.assigmenBNI.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService{

    private BankRepository bankRepository;

    @Override
    public List<Bank> findAllBank() {
        return bankRepository.findAll();
    }

    @Override
    public Bank findSpecificBank(Long ktp) {
        return bankRepository.findById(ktp).orElse(null);
    }

    @Override
    public Bank createDataBank(Bank bank) {
        bankRepository.save(bank);
        return bankRepository.save(bank);
    }

    @Override
    public Bank updateDataBank(Bank bank) {
        // 1. Gunakan Optional untuk menghindari error jika ID tidak ada
        return bankRepository.findById(bank.getNo_ktp()).map(existingBank -> {

            // 2. Hanya update jika field di request body tidak null
            if (bank.getNama_lengkap() != null) {
                existingBank.setNama_lengkap(bank.getNama_lengkap());
            }
            if (bank.getAlamat() != null) {
                existingBank.setAlamat(bank.getAlamat());
            }
            if (bank.getNo_hp() != null) {
                existingBank.setNo_hp(bank.getNo_hp());
            }
            if (bank.getTempat_lahir() != null) {
                existingBank.setTempat_lahir(bank.getTempat_lahir());
            }
            if (bank.getTanggal_lahir() != null) {
                existingBank.setTanggal_lahir(bank.getTanggal_lahir());
            }

            // 3. Simpan perubahan
            return bankRepository.save(existingBank);

        }).orElseThrow(() -> new RuntimeException("Data Bank dengan KTP " + bank.getNo_ktp() + " tidak ditemukan"));
    }

    @Override
    public void deleteDataBank(Long ktp) {
        bankRepository.deleteById(ktp);
    }
}
