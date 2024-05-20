package com.ssafy.trippals.common.file;

import jakarta.annotation.PostConstruct;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${app.fileupload.upload.path}")
    private String uploadPath;

    @Value("${app.imageupload.upload.path}")
    private String imageUploadPath;

    @Override
    public UploadedFile uploadFile(MultipartFile file) throws IOException {
        return upload(file, uploadPath);
    }

    @Override
    public UploadedFile uploadImage(MultipartFile file) throws IOException {
        return upload(file, imageUploadPath);
    }

    private UploadedFile upload(MultipartFile file, String path) throws IOException {
        File uploadDir = new File(path);
        if (!uploadDir.exists()) uploadDir.mkdir();

        String fileName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();

        //file extension
        String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()

        String fileUUID = uuid + "." + extension;
        File saveFile = new File(path + File.separator + fileUUID);
        file.transferTo(saveFile);//물리 파일 저장

        return new UploadedFile(fileName, fileUUID, saveFile);
    }

    @Override
    public void deleteFile(String fileUUID) {
        deleteFile(fileUUID, uploadPath);
    }

    @Override
    public void deleteImage(String fileUUID) {
        deleteFile(fileUUID, imageUploadPath);
    }

    public void deleteFile(String fileUUID, String path) {
        File file = new File(path + File.separator + fileUUID);
        if(file.exists()) {
            file.delete();
        }
    }
}
