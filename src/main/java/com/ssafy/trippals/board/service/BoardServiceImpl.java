//package com.ssafy.trippals.board.service;
//
//import com.ssafy.trippals.board.dao.BoardDao;
//import com.ssafy.trippals.board.dao.BookmarkDao;
//import com.ssafy.trippals.board.dto.*;
//import com.ssafy.trippals.common.file.FileUploadService;
//import com.ssafy.trippals.common.file.UploadedFile;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class BoardServiceImpl implements BoardService{
//
//    private final BoardDao dao;
//    private final BookmarkDao bmdao;
//    private final FileUploadService fileUploadService;
//
//    private static final String SUCCESS = "success";
//    private static final String FAIL = "fail";
//
//    @Override
//    public BoardResultDto boardList(BoardParamDto boardParamDto) {
//
//        BoardResultDto boardResultDto = new BoardResultDto();
//
//        try {
//            List<BoardDto> list = dao.findBoard(boardParamDto);
//            int count = dao.countBoard(boardParamDto.getSearchWord());
//
//            boardResultDto.setList(list);
//            boardResultDto.setCount(count);
//
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        return boardResultDto;
//    }
//
//    @Override
//    public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto) {
//
//        BoardResultDto boardResultDto = new BoardResultDto();
//
//        try {
//            List<BoardDto> list = dao.findBoardBySearchWord(boardParamDto);
//            int count = dao.countBySearchWord(boardParamDto.getSearchWord());
//            System.out.println(boardParamDto);
//            boardResultDto.setList(list);
//            boardResultDto.setCount(count);
//
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return boardResultDto;
//    }
//
//    @Override
//    @Transactional
//    public BoardResultDto boardDetail(BoardParamDto boardParamDto) {
//
//        BoardResultDto boardResultDto = new BoardResultDto();
//
//        try {
//            int userReadCnt = dao.findReadByUser(boardParamDto);
//            if( userReadCnt == 0 ) {//두 메소드 한 트랜잭션
//                dao.insertReadByUser(boardParamDto.getBoardSeq(), boardParamDto.getUserSeq());
//                dao.boardReadCountUpdate(boardParamDto.getBoardSeq());
//            }
//
//            BoardDto dto = dao.findBoardBySeq(boardParamDto.getBoardSeq());
//            List<BoardFileDto> fileList = dao.boardDetailFileList(dto.getSeq());
//            int checkBookmark=bmdao.checkBookmarkByUserSeq(new BoardUserVO(boardParamDto.getBoardSeq(),boardParamDto.getUserSeq()));
//
//            dto.setFileList(fileList);
//            boardResultDto.setDto(dto);
//            boardResultDto.setCheckBookmark(checkBookmark == 1);
//
//        }catch(Exception e) {
//            e.printStackTrace();
//
//        }
//
//        return boardResultDto;
//    }
//
//    @Override
//    @Transactional
//    //DB 저장 먼저 하면 안되는 이유
//    //DB 트랜잭션 롤백이 catch 보다 우선
//    public BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request) {
//
//        BoardResultDto boardResultDto = new BoardResultDto();
//
//        List<File> rollbackFileList = new ArrayList<>();
//
//        try {
//            dao.insertBoard(dto); // useGeneratedKeys="true" keyProperty="boardId"
//
//            List<MultipartFile> fileList = request.getFiles("file");
//
//            for (MultipartFile part : fileList) {
//
//                int boardSeq = dto.getSeq();
//
//                UploadedFile uploaded = fileUploadService.uploadFile(part);
//
//                rollbackFileList.add(uploaded.getSaveFile());
//
//                // Table Insert
//                BoardFileDto boardFileDto = new BoardFileDto();
//                boardFileDto.setBoardSeq(boardSeq);
//                boardFileDto.setFileName(uploaded.getFileName());
//                boardFileDto.setFileSize(part.getSize());
//                boardFileDto.setFileContentType(part.getContentType());
//                boardFileDto.setFileUUID(uploaded.getFileUUID());
//
//                dao.boardFileInsert(boardFileDto);
//            }
//
//
//        }catch(Exception e) {
//            e.printStackTrace();
//
//            for(File file : rollbackFileList) {
//                if(file.exists()) {
//                    file.delete();
//                }
//            }
//
//
//        }
//        return boardResultDto;
//    }
//
//    @Override
//    @Transactional
//    public BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request){
//
//        BoardResultDto boardResultDto = new BoardResultDto();
//
//        List<File> rollbackFileList = new ArrayList<>();
//
//        try {
//            dao.updateBoard(dto);
//
//            List<MultipartFile> fileList = request.getFiles("file");
//
//            List<String> fileDeleteList = dao.boardFileDeleteList(dto.getSeq());
//            for(String fileUUID : fileDeleteList) {
//                fileUploadService.deleteFile(fileUUID);
//            }
//
//            dao.boardFileDelete(dto.getSeq());
//
//            for (MultipartFile part : fileList) {
//                int boardSeq = dto.getSeq();
//
//                UploadedFile uploaded = fileUploadService.uploadFile(part);
//                rollbackFileList.add(uploaded.getSaveFile());
//
//                // Table Insert
//                BoardFileDto boardFileDto = new BoardFileDto();
//                boardFileDto.setBoardSeq(boardSeq);
//                boardFileDto.setFileName(uploaded.getFileName());
//                boardFileDto.setFileSize(part.getSize());
//                boardFileDto.setFileContentType(part.getContentType());
//                boardFileDto.setFileUUID(uploaded.getFileUUID());
//
//                dao.boardFileInsert(boardFileDto);
//            }
//
//
//
//        }catch(Exception e) {
//            e.printStackTrace();
//
//            for(File file : rollbackFileList) {
//                if(file.exists()) {
//                    file.delete();
//                }
//            }
//
//
//        }
//
//        return boardResultDto;
//    }
//
//    @Override
//    @Transactional
//    public BoardResultDto boardDelete(int boardSeq,int userSeq) {
//
//        BoardResultDto boardResultDto = new BoardResultDto();
//
//        try {
//
//            List<String> fileDeleteList = dao.boardFileDeleteList(boardSeq);
//
//            dao.boardFileDelete(boardSeq);
//            dao.boardReadCountDelete(boardSeq);
//            dao.deleteBoardBySeq(boardSeq,userSeq);
//
//
//            for(String fileUUID : fileDeleteList) {
//                fileUploadService.deleteFile(fileUUID);
//            }
//
//        }catch(Exception e) {
//            e.printStackTrace();
//
//        }
//
//        return boardResultDto;
//    }
//
//
//}
