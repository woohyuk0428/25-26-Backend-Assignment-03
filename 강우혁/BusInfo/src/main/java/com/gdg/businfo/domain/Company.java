package com.gdg.businfo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", unique = true )
    private String companyName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "region")
    private String region;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @JoinColumn
    private List<Bus> buses = new ArrayList<>();

    @Builder
    public Company(String companyName, String phoneNumber, String region) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.region = region;
    }
    public void update(String companyName, String phoneNumber, String region) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.region = region;
    }
}
