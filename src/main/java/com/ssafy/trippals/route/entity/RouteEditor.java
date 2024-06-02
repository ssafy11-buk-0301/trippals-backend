package com.ssafy.trippals.route.entity;

import com.ssafy.trippals.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "route_editors")
@AllArgsConstructor
@NoArgsConstructor
public class RouteEditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @ManyToOne
    @JoinColumn(name = "route_seq")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "editor")
    private User user;
}
