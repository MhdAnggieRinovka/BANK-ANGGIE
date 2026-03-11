package com.bank.assigmenBNI.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long id;

    @Column(nullable = false, length = 15)
    @NotBlank(message = "Nama harus diisi")
    private String nama_lengkap;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "Alamat harus diisi")
    private String alamat;

    @Column(nullable = false, length = 10)
    @NotBlank(message = "Tempat lahir harus diisi")
    private String tempat_lahir;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date tanggal_lahir;

    @Column(name = "no_ktp",nullable = false,unique = true, length = 16)
    @NotBlank(message = "No Ktp harus diisi")
    private String noKtp;

    @Column(length = 15)
    private String no_hp;
}
