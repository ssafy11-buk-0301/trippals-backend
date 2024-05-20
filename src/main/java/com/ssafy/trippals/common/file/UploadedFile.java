package com.ssafy.trippals.common.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadedFile {
    private String fileName;
    private String fileUUID;
    private File saveFile;
}
