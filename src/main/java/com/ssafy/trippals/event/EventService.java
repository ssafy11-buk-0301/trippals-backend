//package com.ssafy.trippals.event;
//
//import com.ssafy.trippals.attraction.dao.AttractionDao;
//import com.ssafy.trippals.attraction.dto.AttractionDto;
//import com.ssafy.trippals.event.dto.RouteEvent;
//import com.ssafy.trippals.gpt.GptService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class EventService {
//    private final SimpMessagingTemplate messagingTemplate;
//    private final GptService gptService;
//    private final AttractionDao attractionDao;
//
//    public void sendRouteModifyEvent(int routeSeq, EventType eventType) {
//        String url = String.format("/events/routes/%d", routeSeq);
//        log.debug("Sending route modify event to " + url);
//        messagingTemplate.convertAndSend(url, eventType);
//    }
//
//    public void sendRequestAddEvent(int userSeq) {
//        String url = "/events/" + userSeq;
//        log.debug("Sending request add event to " + url);
//        messagingTemplate.convertAndSend(url, EventType.ADD_REQUEST);
//    }
//
//    public void sendAiRecommendEvent(int routeSeq, int contentId) {
//        Optional<AttractionDto> attractionDto = attractionDao.findByContentId(contentId);
//
//        attractionDto.ifPresent(attraction -> {
//           new Thread(() -> {
//               String message = gptService.getSummary(attraction.getTitle());
//               log.debug("AI Message: {}", message);
//               messagingTemplate.convertAndSend("/events/routes/" + routeSeq, message);
//           }).start();
//        });
//    }
//}
