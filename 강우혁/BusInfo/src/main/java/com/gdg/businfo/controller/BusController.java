package com.gdg.businfo.controller;

import com.gdg.businfo.dto.BusInfoResponseDto;
import com.gdg.businfo.dto.BusSaveRequestDto;
import com.gdg.businfo.service.BusService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bus")
public class BusController {
    private final BusService busService;

    @PostMapping
    public ResponseEntity<BusInfoResponseDto> saveBus(@RequestBody BusSaveRequestDto busSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(busService.saveBus(busSaveRequestDto));
    }

    @GetMapping("/{busId}")
    public ResponseEntity<BusInfoResponseDto> getBus(@PathVariable Long busId) {
        return ResponseEntity.status(HttpStatus.OK).body(busService.getBus(busId));
    }

    @GetMapping("/busAll")
    public ResponseEntity<List<BusInfoResponseDto>> getAllBus() {
        return ResponseEntity.status(HttpStatus.OK).body(busService.getAllBus());
    }
    @PatchMapping("/{busId}")
    public ResponseEntity<?> updateBus(@PathVariable Long busId,
                                                        @RequestBody BusSaveRequestDto busSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(busService.updateBus(busId, busSaveRequestDto));
    }
    @PatchMapping("/{busId}/{companyId}")
    public ResponseEntity<?> updateCompanyBus(@PathVariable Long busId,
                                                               @PathVariable Long companyId,
                                                               @RequestBody BusSaveRequestDto busSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(busService.updateCompanyBus(busId, companyId, busSaveRequestDto));
    }
    @PatchMapping("/{busId}/{routeId}")
    public ResponseEntity<?> updateRouteBus(@PathVariable Long busId,
                                                             @PathVariable Long routeId,
                                                             @RequestBody BusSaveRequestDto busSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(busService.updateRouteBus(busId, routeId, busSaveRequestDto));
    }
    @PatchMapping("/busCompanyRouteUpdate/{busId}/{companyId}/{routeId}")
    public ResponseEntity<?> updateAllBus(@PathVariable Long busId,
                                          @PathVariable Long companyId,
                                          @PathVariable Long routeId,
                                          @RequestBody BusSaveRequestDto busSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(busService.updateAllBus(busId, companyId, routeId, busSaveRequestDto));
    }
    @DeleteMapping("/{busId}")
    public ResponseEntity<?> deleteBus(@PathVariable Long busId) {
        busService.deleteBus(busId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
