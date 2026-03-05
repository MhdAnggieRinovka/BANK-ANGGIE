package com.bank.assigmenBNI.controller;

import com.bank.assigmenBNI.model.Bank;
import com.bank.assigmenBNI.repository.BankRepository;
import com.bank.assigmenBNI.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bank")
public class BankController {
    private BankService bankService;

    @GetMapping
    public ResponseEntity<List<Bank>> getAllData()
    {
        List<Bank> allData = bankService.findAllBank();
        return new ResponseEntity<>(allData, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getData(@PathVariable("id") Long Ktp){
        Bank getData = bankService.findSpecificBank(Ktp);
        return new ResponseEntity<>(getData,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveNewData(@RequestBody Bank bank){
        if(bankService.isKtpExist(bank.getNo_ktp()))
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Maaf, KTP " + bank.getNo_ktp() + " sudah terdaftar di sistem.");
        }
        else{
            Bank bankResult = bankService.createDataBank(bank);
            return new ResponseEntity<>(bankResult, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bank> updateData(@PathVariable("id") Long ktp,@RequestBody Bank bank){
        bank.setNo_ktp(ktp);
        Bank updatedData = bankService.updateDataBank(bank);
        return new ResponseEntity<>(updatedData, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDataBank(@PathVariable("id") Long ktp){
        bankService.deleteDataBank(ktp);
        return new ResponseEntity<>("Your data has been deleted", HttpStatus.OK);
    }
}
