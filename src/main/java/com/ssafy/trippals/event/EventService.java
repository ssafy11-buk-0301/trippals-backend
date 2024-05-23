package com.ssafy.trippals.event;

import com.ssafy.trippals.event.dto.RouteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendRouteModifyEvent(int routeSeq, EventType eventType) {
        String url = String.format("/events/routes/%d", routeSeq);
        log.debug("Sending route modify event to " + url);
        messagingTemplate.convertAndSend(url, eventType);
    }

    public void sendRequestAddEvent(int userSeq) {
        String url = "/events/" + userSeq;
        log.debug("Sending request add event to " + url);
        messagingTemplate.convertAndSend(url, EventType.ADD_REQUEST);
    }
}
