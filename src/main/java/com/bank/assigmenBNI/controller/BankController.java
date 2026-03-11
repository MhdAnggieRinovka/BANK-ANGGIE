package com.bank.assigmenBNI.controller;

import com.bank.assigmenBNI.Timer;
import com.bank.assigmenBNI.model.Bank;
import com.bank.assigmenBNI.repository.BankRepository;
import com.bank.assigmenBNI.service.BankService;
import com.bank.assigmenBNI.webResponseEntity.WebResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/bank")
public class BankController {
    private BankService bankService;
    private Timer timer;

    @GetMapping
    public ResponseEntity<WebResponse<List<Bank>>> getAllData() {

        try {
            timer.start();
            List<Bank> allData = bankService.findAllBank();
            log.info("Get All Data Bank "+ timer.stop());
            WebResponse<List<Bank>> webResponse = new WebResponse<>(
                    HttpStatus.OK.value(),
                    "Getting All Data Successfully",
                    allData
            );
            return ResponseEntity.ok(webResponse);
        }catch (Exception e) {
            log.error(new Date()+" Terjadi error ketika mendapatkan data: {}", e.getMessage());

            WebResponse<List<Bank>> errorResponse = new WebResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Internal Server Error: " + e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/noKtp")
    public ResponseEntity<WebResponse<Bank>> getData(@RequestBody Map<String, String> Ktp){
            Map<String,String> getValueKTP = Ktp;
            timer.start();
            Bank getData = bankService.findSpecificBank(getValueKTP.get("no_ktp"));
            log.info("Getting Specific Data "+ timer.stop());
            WebResponse<Bank> webResponse ;
        try{
            if(getData==null)
            {
                webResponse = new WebResponse<>(HttpStatus.BAD_REQUEST.value(),"Data Doesnt Exist",getData);
                log.error(new Date()+" Mencari data pecific..... tidak ditemukan");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(webResponse);
            };
            webResponse = new WebResponse<>(HttpStatus.OK.value(),"Get Specific Data Successfull", getData);
            return ResponseEntity.ok(webResponse);
        }catch (Exception err){
            log.error(new Date()+" Terjadi error ketika mendapatkan specific data: {}", err.getMessage());

            WebResponse<Bank> errorResponse = new WebResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Internal Server Error: " + err.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<WebResponse<Bank>> saveNewData(@Valid @RequestBody Bank bank, HttpServletRequest request) {
        request.setAttribute("startTime", System.currentTimeMillis());
        if (bankService.isKtpExist(bank.getNoKtp())) {
            WebResponse<Bank> errorResponse = new WebResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Maaf, KTP " + bank.getNoKtp() + " sudah terdaftar di sistem.",
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        timer.start();
        Bank bankResult = bankService.createDataBank(bank);
        log.info("Create Data Bank "+timer.stop());
        WebResponse<Bank> successResponse = new WebResponse<>(
                HttpStatus.CREATED.value(), // Menggunakan 201 Created untuk data baru
                "Data Bank Berhasil Disimpan",
                bankResult
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bank> updateData(@PathVariable("id") String ktp,@RequestBody Bank bank){
        timer.start();
        bank.setNoKtp(ktp);
        Bank updatedData = bankService.updateDataBank(bank);
        log.info("Update Data "+timer.stop());
        return new ResponseEntity<>(updatedData, HttpStatus.OK);
    }

    @DeleteMapping("/noKtp/{id}")
    public ResponseEntity<String> deleteDataBank(@PathVariable("id") String ktp){
        timer.start();
        bankService.deleteDataBank(ktp);
        log.info("Delete Data "+timer.stop());
        return new ResponseEntity<>("Your data has been deleted", HttpStatus.OK);
    }
}
