package com.ssafy.trippals.sidogugun.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sido {
    @Id
    @Column(name = "sido_code")
    private int sidoCode;
    private String sidoName;
}
