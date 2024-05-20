package com.ssafy.trippals.common.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface FileUploadService {
    UploadedFile upload(MultipartFile file) throws IOException;
    void deleteFile(String fileUUID);
}
