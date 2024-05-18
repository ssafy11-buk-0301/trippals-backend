package com.ssafy.trippals.board.service;

import com.ssafy.trippals.board.dao.BookmarkDao;
import com.ssafy.trippals.board.dto.BoardDto;
import com.ssafy.trippals.board.dto.BoardResultDto;
import com.ssafy.trippals.board.dto.BoardUserVO;
import com.ssafy.trippals.common.exception.UserAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService{
    private final BookmarkDao dao;

    @Override
    public List<BoardDto> bookmarkList(int userSeq) {
        List<BoardDto> list=new ArrayList<>();
        BoardResultDto boardResultDto = new BoardResultDto();
        //pagination?
        try {
            list = dao.findBookmarkByUserSeq(userSeq);


        }catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void bookmarkInsert(BoardUserVO boardUserVO) {
        dao.insertBookmark(boardUserVO);
    }

    @Override
    public void bookmarkUpdate(BoardUserVO boardUserVO) {
        //bookmark 이름이 있다면 필요
    }

    @Override
    public void bookmarkDelete(BoardUserVO boardUserVO) {
        dao.deleteBookmark(boardUserVO);
    }
}
