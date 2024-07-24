package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
