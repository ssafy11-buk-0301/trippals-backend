//package com.ssafy.trippals.sidogugun.controller;
//
//import com.ssafy.trippals.sidogugun.dto.GugunCodeDto;
//import com.ssafy.trippals.sidogugun.dto.SidoCodeDto;
//import com.ssafy.trippals.sidogugun.service.AddressService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/sidocode")
//public class SidoGugunCodeController {
//    private final AddressService addressService;
//
//    public SidoGugunCodeController(AddressService addressService) {
//        this.addressService = addressService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<SidoCodeDto>> getSidoCodeList() {
//        List<SidoCodeDto> sidoList = addressService.getAllSidoCode();
//        return ResponseEntity.ok(sidoList);
//    }
//
//    @GetMapping("/{sidocode}/guguncode")
//    public ResponseEntity<List<GugunCodeDto>> getSidoCodeList(@PathVariable("sidocode") int sidoCode) {
//        System.out.println(sidoCode);
//        List<GugunCodeDto> gugunList = addressService.findGugunCodeBySidoCode(sidoCode);
//        return ResponseEntity.ok(gugunList);
//    }
//}
