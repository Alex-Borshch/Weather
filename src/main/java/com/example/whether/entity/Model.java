package com.example.whether.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     protected Long id;

}
