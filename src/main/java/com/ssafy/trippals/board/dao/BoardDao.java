package com.ssafy.trippals.board.dao;

import com.ssafy.trippals.board.dto.BoardDto;
import com.ssafy.trippals.board.dto.BoardParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
    List<BoardDto> findBoardData(BoardParams boardParams);
    BoardDto findBoardBySeq(Integer boardSeq);
    Integer insertBoard(BoardDto boardDto);
    Integer updateBoard(BoardDto boardDto);
    Integer deleteBoardBySeq(Integer boardSeq,Integer userSeq);
    Integer boardListCount();
    Integer boardListSearchWordCount(String searchWord);
}
