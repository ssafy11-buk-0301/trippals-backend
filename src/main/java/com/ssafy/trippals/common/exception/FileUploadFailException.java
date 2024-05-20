package com.ssafy.trippals.common.exception;

public class FileUploadFailException extends RuntimeException {
    public static final String MESSAGE = "파일 업로드에 실패했습니다.";

    public FileUploadFailException(Exception e) {
        super(MESSAGE, e);
    }
}
