package com.gdg.businfo.service;

import com.gdg.businfo.domain.Company;
import com.gdg.businfo.dto.CompanyInfoResponseDto;
import com.gdg.businfo.dto.CompanySaveRequestDto;
import com.gdg.businfo.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Transactional
    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    @Transactional
    public CompanyInfoResponseDto saveConpany(CompanySaveRequestDto companySaveRequestDto) {
        Company company = Company.builder()
                .companyName(companySaveRequestDto.getCompanyName())
                .phoneNumber(companySaveRequestDto.getPhoneNumber())
                .region(companySaveRequestDto.getRegion())
                .build();
        companyRepository.save(company);
        return CompanyInfoResponseDto.from(company);
    }

    @Transactional
    public CompanyInfoResponseDto updateConpany(Long id,CompanySaveRequestDto companySaveRequestDto) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        company.setCompanyName(companySaveRequestDto.getCompanyName());
        company.setPhoneNumber(companySaveRequestDto.getPhoneNumber());
        company.setRegion(companySaveRequestDto.getRegion());
        return CompanyInfoResponseDto.from(companyRepository.save(company));
    }
    @Transactional
    public void deleteConpany(Long id) {
        companyRepository.deleteById(id);
    }
}
