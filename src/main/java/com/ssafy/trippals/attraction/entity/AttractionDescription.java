package com.ssafy.trippals.attraction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AttractionDescription {
    @Id
    private int contentId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "content_id")
    private AttractionInfo attractionInfo;

    private String overview;
}
