package com.ssafy.trippals.attraction.entity;

import com.ssafy.trippals.sidogugun.entity.Gugun;
import com.ssafy.trippals.sidogugun.entity.Sido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AttractionInfo {
    @Id
    private int contentId;

    private String title;
    @Column(name = "addr1")
    private String addr;
    @Column(name = "first_image")
    private String image;
    private BigDecimal latitude;
    private BigDecimal longitude;

    @ManyToOne
    @JoinColumn(name = "gugun_code")
    @JoinColumn(name = "sido_code")
    private Gugun gugun;
}
