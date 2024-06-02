package com.ssafy.trippals.board.dao;

import com.ssafy.trippals.board.dto.BoardDto;
import com.ssafy.trippals.board.dto.BoardFileDto;
import com.ssafy.trippals.board.dto.BoardParamDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardDao {
    List<BoardDto> findBoard(BoardParamDto boardParamDto);
    BoardDto findBoardBySeq(Integer boardSeq);
    Integer insertBoard(BoardDto boardDto);
    Integer updateBoard(BoardDto boardDto);
    Integer deleteBoardBySeq(Integer boardSeq,Integer userSeq);
    List<BoardDto> findBoardBySearchWord(BoardParamDto boardParamDto);
    Integer countBoard(String searchWord);
    Integer countBySearchWord(String searchWord);

    // map - Dto
    int findReadByUser(BoardParamDto boardParamDto);

    // map - @param
    int insertReadByUser(
            @Param("boardSeq") int boardSeq,
            @Param("userSeq") int userSeq );

    int boardReadCountUpdate(int boardSeq);
    int boardReadCountDelete(int boardSeq);

    List<BoardFileDto> boardDetailFileList(int boardId);
    int boardFileInsert(BoardFileDto dto);
    int boardFileDelete(int boardId);//테이블
    List<String> boardFileDeleteList(int boardId);//물리 파일

}
