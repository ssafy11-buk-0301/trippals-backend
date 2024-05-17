package com.ssafy.trippals.board.dao;

import com.ssafy.trippals.board.dto.BoardData;
import com.ssafy.trippals.board.dto.BoardInsert;
import com.ssafy.trippals.board.dto.BoardParams;
import com.ssafy.trippals.board.dto.BoardUpdate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
    List<BoardData> findBoardData(BoardParams boardParams);
    BoardData findBoardBySeq(Integer boardSeq);
    Integer insertBoard(BoardInsert boardInsert);
    Integer updateBoard(BoardUpdate boardUpdate);
    Integer deleteBoardBySeq(Integer boardSeq,Integer userSeq);

    Integer boardListCount();

    Integer boardListSearchWordCount(String searchWord);
}
