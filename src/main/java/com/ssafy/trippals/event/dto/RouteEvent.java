package com.ssafy.trippals.event.dto;

import com.ssafy.trippals.event.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteEvent {
    private EventType eventType;
}
