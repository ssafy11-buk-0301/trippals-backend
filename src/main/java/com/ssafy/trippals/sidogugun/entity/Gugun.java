package com.ssafy.trippals.sidogugun.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Gugun {
    @EmbeddedId
    private GugunId id;

    @MapsId("sidoCode")
    @ManyToOne
    @JoinColumn(name = "sido_code")
    private Sido sido;

    private String gugunName;
}
