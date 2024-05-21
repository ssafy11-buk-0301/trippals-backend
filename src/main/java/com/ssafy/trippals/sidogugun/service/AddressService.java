package com.ssafy.trippals.sidogugun.service;

import com.ssafy.trippals.sidogugun.dto.GugunCodeDto;
import com.ssafy.trippals.sidogugun.dto.SidoCodeDto;

import java.util.List;

public interface AddressService {
    List<GugunCodeDto> findGugunCodeBySidoCode(int sidoCode);
    List<SidoCodeDto> getAllSidoCode();
}
