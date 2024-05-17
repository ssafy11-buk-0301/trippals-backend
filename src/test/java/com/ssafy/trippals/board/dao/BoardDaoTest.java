package com.ssafy.trippals.board.dao;

import com.ssafy.trippals.TrippalsApplication;
import com.ssafy.trippals.board.dto.BoardDto;
import com.ssafy.trippals.board.dto.BoardParams;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = TrippalsApplication.class)
public class BoardDaoTest {
    @Autowired private BoardDao boardDao;

    @Test
    void boardListCount(){
        assertNotNull(boardDao.boardListCount());
    }

    @Test
    void boardListSearchWordCount(){
        assertNotNull(boardDao.boardListSearchWordCount("제목"));
    }

    @Test
    void findBoardData() {

        //orderBy String
        BoardParams boardParams=new BoardParams(3,2,"","title");

        List<BoardDto> boardDtoList =boardDao.findBoardData(boardParams);

        System.out.println(boardDtoList);
        assertNotNull(boardDtoList);
    }

    @Test
    void findBoardBySeq(){

        BoardDto boardDto =boardDao.findBoardBySeq(1);

        assertNotNull(boardDto);
    }
    @Test
    void insertBoard() {
        Integer boardSeq=1;
        Integer routeSeq=1;
        Integer userSeq=1;
        boolean isDraft=false;
        BoardDto boardDto=new BoardDto(boardSeq,userSeq,"testTitle","testContent",LocalDateTime.now(),isDraft,routeSeq);

        Integer insertResult=boardDao.insertBoard(boardDto);

        assertNotNull(insertResult);
    }

    @Test
    void updateBoard(){
        Integer boardSeq=1;
        Integer routeSeq=1;
        Integer userSeq=1;
        boolean isDraft=false;

        BoardDto boardDto =new BoardDto(boardSeq,userSeq,"testTitle","testContent",LocalDateTime.now(),isDraft,routeSeq);

        Integer updateResult=boardDao.updateBoard(boardDto);

        assertNotNull(updateResult);
    }

    @Test
    void deleteBoard(){
        assertNotNull(boardDao.deleteBoardBySeq(1,1));
    }
}
