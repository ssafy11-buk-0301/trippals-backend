package com.ssafy.trippals.sidogugun.dao;

import com.ssafy.trippals.sidogugun.dto.GugunCodeDto;
import com.ssafy.trippals.sidogugun.dto.SidoCodeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SidoGugunDao {
    List<GugunCodeDto> findAllBySidoCode(int sidoCode);
    List<SidoCodeDto> getAllSidoCode();
}
