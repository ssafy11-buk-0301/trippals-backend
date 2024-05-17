package com.ssafy.trippals.board.dao;

import com.ssafy.trippals.TrippalsApplication;
import com.ssafy.trippals.board.dto.BoardData;
import com.ssafy.trippals.board.dto.BoardParams;
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
    void findBoardData() {

        BoardParams boardParams=new BoardParams(3,2,"");

        List<BoardData> boardDataList=boardDao.findBoardData(boardParams);

        System.out.println(boardDataList);
        assertNotNull(boardDataList);

    }
}
