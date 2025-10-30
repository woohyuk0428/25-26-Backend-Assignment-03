package com.gdg.businfo.service;

import com.gdg.businfo.domain.Bus;
import com.gdg.businfo.domain.Company;
import com.gdg.businfo.domain.Route;
import com.gdg.businfo.dto.BusInfoResponseDto;
import com.gdg.businfo.dto.BusSaveRequestDto;
import com.gdg.businfo.repository.BusRepository;
import com.gdg.businfo.repository.CompanyRepository;
import com.gdg.businfo.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {
    private final RouteRepository routeRepository;
    private final BusRepository busRepository;
    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public List<Bus> findAll() {
        return busRepository.findAll();
    }

    @Transactional
    public BusInfoResponseDto saveBus(Long companyId, Long routeId, BusSaveRequestDto busSaveRequestDto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("회사를 찾을 수 없습니다."));
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("노선 정보가 없습니다."));
        Bus bus = Bus.builder()
                .busNumber(busSaveRequestDto.getBusNumber())
                .type(busSaveRequestDto.getType())
                .build();
        busRepository.save(bus);
        return BusInfoResponseDto.from(bus);
    }

    @Transactional
    public BusInfoResponseDto updateBus(Long id, BusSaveRequestDto busSaveRequestDto) {
        Bus bus = busRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("버스 아이디를 확인 할 수 없습니다.")
        );
        bus.setBusNumber(busSaveRequestDto.getBusNumber());
        bus.setType(busSaveRequestDto.getType());
        busRepository.save(bus);
        return BusInfoResponseDto.from(bus);
    }

}
