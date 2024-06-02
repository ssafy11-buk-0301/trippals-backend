package com.ssafy.trippals.route.entity;

import com.ssafy.trippals.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;
    private String name;
    private String overview;
    private String thumbnail;
    private LocalDate startDate;
}
