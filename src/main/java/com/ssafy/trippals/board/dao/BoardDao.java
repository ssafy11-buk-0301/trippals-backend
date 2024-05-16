package com.ssafy.trippals.board.dao;

import com.ssafy.trippals.board.dto.BoardData;
import com.ssafy.trippals.board.dto.BoardInsert;
import com.ssafy.trippals.board.dto.BoardParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
    List<BoardData> findBoardData(BoardParams boardParams);
    int insertBoard(BoardInsert boardInsert);
    int updateboard(BoardInsert boardUpdate);
    int deleteBoard(Integer board_seq);
}
