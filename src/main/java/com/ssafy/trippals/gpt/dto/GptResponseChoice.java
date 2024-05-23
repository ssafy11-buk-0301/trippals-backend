package com.ssafy.trippals.gpt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GptResponseChoice {
    private Message message;
    private int index;
    private Object logprobs;
    private String finish_reason;
}
