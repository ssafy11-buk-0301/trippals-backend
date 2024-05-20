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
    String uploadPath;

    @PostConstruct
    public void createDir() {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
    }

    @Override
    public UploadedFile upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();

        //file extension
        String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()

        String fileUUID = uuid + "." + extension;
        File saveFile = new File(uploadPath + File.separator + fileUUID);
        file.transferTo(saveFile);//물리 파일 저장

        return new UploadedFile(fileName, fileUUID, saveFile);
    }

    @Override
    public void deleteFile(String fileUUID) {
        File file = new File(uploadPath + File.separator + fileUUID);
        if(file.exists()) {
            file.delete();
        }
    }
}
