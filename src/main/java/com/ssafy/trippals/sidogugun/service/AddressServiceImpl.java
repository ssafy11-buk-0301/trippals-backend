//package com.ssafy.trippals.sidogugun.service;
//
//import com.ssafy.trippals.sidogugun.dao.SidoGugunDao;
//import com.ssafy.trippals.sidogugun.dto.GugunCodeDto;
//import com.ssafy.trippals.sidogugun.dto.SidoCodeDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class AddressServiceImpl implements AddressService {
//
//    private final SidoGugunDao sidoGugunDao;
//
//    @Override
//    public List<GugunCodeDto> findGugunCodeBySidoCode(int sidoCode) {
//        return sidoGugunDao.findAllBySidoCode(sidoCode);
//    }
//
//    @Override
//    public List<SidoCodeDto> getAllSidoCode() {
//        return sidoGugunDao.getAllSidoCode();
//    }
//}
