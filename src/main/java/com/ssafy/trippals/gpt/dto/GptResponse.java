package com.ssafy.trippals.gpt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GptResponse {
    private String id;
    private String object;
    private int created;
    private String model;
    private List<GptResponseChoice> choices;
    private Object usage;
}
