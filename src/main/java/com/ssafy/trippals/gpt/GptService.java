//package com.ssafy.trippals.gpt;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.trippals.gpt.dto.GptRequest;
//import com.ssafy.trippals.gpt.dto.GptResponse;
//import com.ssafy.trippals.gpt.dto.Message;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class GptService {
//
//    @Value("${openai.api.key}")
//    private String apiKey;
//
//    private final RestTemplate restTemplate;
//    private final ObjectMapper objectMapper;
//
//
//    public String getSummary(String location) {
//        String apiUrl = "https://api.openai.com/v1/chat/completions";
//        String prompt = String.format("'%s' 여행지에 대한 여행 팁을 100자 이내로 제공해줘.", location);
//
//        GptRequest chatGptRequest = new GptRequest("gpt-4o", List.of(new Message("user", prompt)));
//        String input = null;
//        try {
//            input = objectMapper.writeValueAsString(chatGptRequest);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(apiKey);
//
//        HttpEntity<String> requestEntity = new HttpEntity<>(input, headers);
//
//        ResponseEntity<GptResponse> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, GptResponse.class);
//
//
//        GptResponse responseBody = responseEntity.getBody();
//        if (responseBody != null) {
//            if (!responseBody.getChoices().isEmpty()) {
//                return responseBody.getChoices().get(0).getMessage().getContent();
//            }
//        }
//        return "No summary available.";
//    }
//}
//
