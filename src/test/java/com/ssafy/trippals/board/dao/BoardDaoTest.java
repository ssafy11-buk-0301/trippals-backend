package com.ssafy.trippals.board.dao;

import com.ssafy.trippals.board.dto.BoardData;
import com.ssafy.trippals.board.dto.BoardParams;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardDaoTest {
    @Autowired private BoardDao boardDao;

    @Test
    void findBoardData() {
        BoardParams boardParams=new BoardParams(10,2,"");

        List<BoardData> boardDataList=boardDao.findBoardData(boardParams);

        assertThat(actualRouteData)
    }
}
