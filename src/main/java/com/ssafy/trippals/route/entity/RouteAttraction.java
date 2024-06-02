package com.ssafy.trippals.route.entity;

import com.ssafy.trippals.attraction.entity.AttractionInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RouteAttraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    private int orderNumber;

    @ManyToOne
    @JoinColumn(name = "route_seq")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "attraction_seq")
    private AttractionInfo attractionInfo;
}
