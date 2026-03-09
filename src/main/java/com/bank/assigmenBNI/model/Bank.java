package com.bank.assigmenBNI.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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
    private String nama_lengkap;

    @Column(nullable = false, length = 20)
    private String alamat;

    @Column(nullable = false, length = 10)
    private String tempat_lahir;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tanggal_lahir;

    @Column(name = "no_ktp",nullable = false,unique = true, length = 16)
    private String noKtp;

    @Column(length = 15)
    private String no_hp;
}
