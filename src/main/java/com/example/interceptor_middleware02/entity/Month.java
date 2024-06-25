package com.example.interceptor_middleware02.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Month {
    private int monthNumber;
    private String englishName;
    private String italianName;
    private String germanName;


}
