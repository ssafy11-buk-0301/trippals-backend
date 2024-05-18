package com.ssafy.trippals.board.service;

import com.ssafy.trippals.board.dao.BoardDao;
import com.ssafy.trippals.board.dto.BoardDto;
import com.ssafy.trippals.board.dto.BoardFileDto;
import com.ssafy.trippals.board.dto.BoardParamDto;
import com.ssafy.trippals.board.dto.BoardResultDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardDao dao;

    //물리적 파일저장 위치
    @Value("${app.fileupload.upload.path}")
    String uploadPath;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Override
    public BoardResultDto boardList(BoardParamDto boardParamDto) {

        BoardResultDto boardResultDto = new BoardResultDto();

        try {
            List<BoardDto> list = dao.findBoard(boardParamDto);
            int count = dao.countBoard();
            boardResultDto.setList(list);
            boardResultDto.setCount(count);

        }catch(Exception e) {
            e.printStackTrace();
        }

        return boardResultDto;
    }

    @Override
    public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto) {

        BoardResultDto boardResultDto = new BoardResultDto();

        try {
            List<BoardDto> list = dao.findBoardBySearchWord(boardParamDto);
            int count = dao.countBySearchWord(boardParamDto.getSearchWord());
            System.out.println(boardParamDto);
            boardResultDto.setList(list);
            boardResultDto.setCount(count);

        }catch(Exception e) {
            e.printStackTrace();
        }
        return boardResultDto;
    }

    @Override
    @Transactional
    public BoardResultDto boardDetail(BoardParamDto boardParamDto) {

        BoardResultDto boardResultDto = new BoardResultDto();

        try {
            int userReadCnt = dao.findReadByUser(boardParamDto);
            if( userReadCnt == 0 ) {//두 메소드 한 트랜잭션
                dao.insertReadByUser(boardParamDto.getBoardSeq(), boardParamDto.getUserSeq());
                dao.boardReadCountUpdate(boardParamDto.getBoardSeq());
            }

            BoardDto dto = dao.findBoardBySeq(boardParamDto.getBoardSeq());
            List<BoardFileDto> fileList = dao.boardDetailFileList(dto.getSeq());

            dto.setFileList(fileList);
            boardResultDto.setDto(dto);



        }catch(Exception e) {
            e.printStackTrace();

        }

        return boardResultDto;
    }

    @Override
    @Transactional
    //DB 저장 먼저 하면 안되는 이유
    //DB 트랜잭션 롤백이 catch 보다 우선
    public BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request) {

        BoardResultDto boardResultDto = new BoardResultDto();

        List<File> rollbackFileList = new ArrayList<>();

        try {
            dao.insertBoard(dto); // useGeneratedKeys="true" keyProperty="boardId"

            List<MultipartFile> fileList = request.getFiles("file");

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            for (MultipartFile part : fileList) {

                int boardSeq = dto.getSeq();

                String fileName = part.getOriginalFilename();

                //Random File Id
                UUID uuid = UUID.randomUUID();

                //file extension
                String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()

                String fileUUID = uuid + "." + extension;

                File saveFile = new File(uploadPath + File.separator + fileUUID);

                rollbackFileList.add(saveFile);

                part.transferTo(saveFile);//물리 파일 저장

                // Table Insert
                BoardFileDto boardFileDto = new BoardFileDto();
                boardFileDto.setBoardSeq(boardSeq);
                boardFileDto.setFileName(fileName);
                boardFileDto.setFileSize(part.getSize());
                boardFileDto.setFileContentType(part.getContentType());
                boardFileDto.setFileUUID(fileUUID);

                dao.boardFileInsert(boardFileDto);
            }


        }catch(Exception e) {
            e.printStackTrace();

            for(File file : rollbackFileList) {
                if(file.exists()) {
                    file.delete();
                }
            }


        }
        return boardResultDto;
    }

    @Override
    @Transactional
    public BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request){

        BoardResultDto boardResultDto = new BoardResultDto();

        List<File> rollbackFileList = new ArrayList<>();

        try {
            dao.updateBoard(dto);

            //
            List<MultipartFile> fileList = request.getFiles("file");

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            List<String> fileDeleteList = dao.boardFileDeleteList(dto.getSeq());
            for(String fileUUID : fileDeleteList) {
                File file = new File(uploadPath + File.separator + fileUUID);
                if(file.exists()) {
                    file.delete();
                }
            }

            dao.boardFileDelete(dto.getSeq());

            for (MultipartFile part : fileList) {
                int boardSeq = dto.getSeq();

                String fileName = part.getOriginalFilename();

                //Random File Id
                UUID uuid = UUID.randomUUID();

                //file extension
                String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
                String fileUUID = uuid + "." + extension;

                File saveFile = new File(uploadPath + File.separator + fileUUID);

                rollbackFileList.add(saveFile);

                part.transferTo(saveFile);

                // Table Insert
                BoardFileDto boardFileDto = new BoardFileDto();
                boardFileDto.setBoardSeq(boardSeq);
                boardFileDto.setFileName(fileName);
                boardFileDto.setFileSize(part.getSize());
                boardFileDto.setFileContentType(part.getContentType());
                boardFileDto.setFileUUID(fileUUID);

                dao.boardFileInsert(boardFileDto);
            }



        }catch(Exception e) {
            e.printStackTrace();

            for(File file : rollbackFileList) {
                if(file.exists()) {
                    file.delete();
                }
            }


        }

        return boardResultDto;
    }

    @Override
    @Transactional
    public BoardResultDto boardDelete(int boardSeq,int userSeq) {

        BoardResultDto boardResultDto = new BoardResultDto();

        try {

            List<String> fileDeleteList = dao.boardFileDeleteList(boardSeq);

            dao.boardFileDelete(boardSeq);
            dao.boardReadCountDelete(boardSeq);
            dao.deleteBoardBySeq(boardSeq,userSeq);


            for(String fileUUID : fileDeleteList) {
                File file = new File(uploadPath + File.separator + fileUUID);
                if(file.exists()) {
                    file.delete();
                }
            }

        }catch(Exception e) {
            e.printStackTrace();

        }

        return boardResultDto;
    }


}
