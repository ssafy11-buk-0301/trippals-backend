//package com.ssafy.trippals.board.service;
//
//import com.ssafy.trippals.board.dao.BookmarkDao;
//import com.ssafy.trippals.board.dto.BoardParamDto;
//import com.ssafy.trippals.board.dto.BoardResultDto;
//import com.ssafy.trippals.board.dto.BoardUserVO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class BookmarkServiceImpl implements BookmarkService{
//    private final BookmarkDao dao;
//
//    @Override
//    public BoardResultDto bookmarkList(BoardParamDto boardParamDto) {
//
//        BoardResultDto boardResultDto = new BoardResultDto();
//        //pagination?
//        try {
//            boardResultDto.setList(dao.findBookmarkByUserSeq(boardParamDto));
//            boardResultDto.setCount(dao.countBookmark(boardParamDto));
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return boardResultDto;
//    }
//
//    @Override
//    public int bookmarkInsert(BoardUserVO boardUserVO) {
//        return dao.insertBookmark(boardUserVO);
//    }
//
//    @Override
//    public void bookmarkUpdate(BoardUserVO boardUserVO) {
//        //bookmark 이름이 있다면 필요
//    }
//    @Override
//    public void bookmarkDelete(BoardUserVO boardUserVO) {
//        dao.deleteBookmark(boardUserVO);
//    }
//
//}
