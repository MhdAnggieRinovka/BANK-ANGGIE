package com.bank.assigmenBNI.controller;

import com.bank.assigmenBNI.model.Bank;
import com.bank.assigmenBNI.repository.BankRepository;
import com.bank.assigmenBNI.service.BankService;
import com.bank.assigmenBNI.webResponseEntity.WebResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bank")
public class BankController {
    private BankService bankService;

    @GetMapping
    public ResponseEntity<WebResponse<List<Bank>>> getAllData() {
        List<Bank> allData = bankService.findAllBank();

        WebResponse<List<Bank>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                "Getting All Data Successfully",
                allData
        );

        return ResponseEntity.ok(webResponse);
    }

    @PostMapping("/noKtp")
    public ResponseEntity<WebResponse<Bank>> getData(@RequestBody Map<String, String> Ktp){
        Map<String,String> getValueKTP = Ktp;
        Bank getData = bankService.findSpecificBank(getValueKTP.get("no_ktp"));
        WebResponse<Bank> webResponse ;
        if(getData==null)
        {
            webResponse = new WebResponse<>(HttpStatus.BAD_REQUEST.value(),"Data Doesnt Exist",getData);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(webResponse);
        }
        webResponse = new WebResponse<>(HttpStatus.OK.value(),"Get Specific Data Successfull", getData);
        return ResponseEntity.ok(webResponse);
    }
    @PostMapping
    public ResponseEntity<WebResponse<Bank>> saveNewData(@RequestBody Bank bank) {
        // 1. Validasi apakah KTP sudah ada
        // isKtpExist sekarang menerima String
        if (bankService.isKtpExist(bank.getNoKtp())) {
            WebResponse<Bank> errorResponse = new WebResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Maaf, KTP " + bank.getNoKtp() + " sudah terdaftar di sistem.",
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        // 2. Simpan data baru
        Bank bankResult = bankService.createDataBank(bank);

        // 3. Bungkus hasil dalam WebResponse
        WebResponse<Bank> successResponse = new WebResponse<>(
                HttpStatus.CREATED.value(), // Menggunakan 201 Created untuk data baru
                "Data Bank Berhasil Disimpan",
                bankResult
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bank> updateData(@PathVariable("id") String ktp,@RequestBody Bank bank){
        bank.setNoKtp(ktp);
        Bank updatedData = bankService.updateDataBank(bank);
        return new ResponseEntity<>(updatedData, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDataBank(@PathVariable("id") String ktp){
        bankService.deleteDataBank(ktp);
        return new ResponseEntity<>("Your data has been deleted", HttpStatus.OK);
    }
}
