package com.example.WEBBANTHUOCTAY.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Loai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_loai;
    private String tenLoai;


}
