package com.ssafy.trippals.board.dao;

import com.ssafy.trippals.TrippalsApplication;
import com.ssafy.trippals.board.dto.BoardData;
import com.ssafy.trippals.board.dto.BoardInsert;
import com.ssafy.trippals.board.dto.BoardParams;
import com.ssafy.trippals.board.dto.BoardUpdate;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

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

        BoardParams boardParams=new BoardParams(3,2,"");

        List<BoardData> boardDataList=boardDao.findBoardData(boardParams);

        System.out.println(boardDataList);
        assertNotNull(boardDataList);
    }

    @Test
    void findBoardBySeq(){

        BoardData boardData=boardDao.findBoardBySeq(1);

        assertNotNull(boardData);
    }
    @Test
    void insertBoard() {
        Integer routeSeq=1;
        Integer userSeq=1;
        Boolean isDraft=false;
        BoardInsert boardInsert=new BoardInsert(userSeq,"testTitle","testContent",isDraft,routeSeq);

        Integer insertResult=boardDao.insertBoard(boardInsert);

        assertNotNull(insertResult);
    }

    @Test
    void updateBoard(){
        Integer boardSeq=1;
        Integer routeSeq=1;
        Integer userSeq=1;
        Boolean isDraft=false;

        BoardUpdate boardUpdate=new BoardUpdate(boardSeq,userSeq,"testTitle","testContent",isDraft,routeSeq);

        Integer updateResult=boardDao.updateBoard(boardUpdate);

        assertNotNull(updateResult);
    }

    @Test
    void deleteBoard(){
        assertNotNull(boardDao.deleteBoardBySeq(1,1));
    }
}
