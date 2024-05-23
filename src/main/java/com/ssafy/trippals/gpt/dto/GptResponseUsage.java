package com.ssafy.trippals.gpt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GptResponseUsage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
}
