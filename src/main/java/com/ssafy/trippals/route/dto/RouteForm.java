package com.ssafy.trippals.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteForm {
    private String name;
    private String overview;
    private MultipartFile thumbnail;
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private LocalDate startDate;
}
