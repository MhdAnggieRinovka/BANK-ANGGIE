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
    @Column(nullable = false,unique = true)
    private Long no_ktp;

    @Column(nullable = false)
    private String nama_lengkap;

    @Column(nullable = false)
    private String alamat;

    @Column(nullable = false)
    private String tempat_lahir;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tanggal_lahir;

    @Column
    private String no_hp;
}
