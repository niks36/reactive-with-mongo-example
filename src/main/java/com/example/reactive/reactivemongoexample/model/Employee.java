package com.example.reactive.reactivemongoexample.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Employee {

    @Id
    private String id;

    private String name;

    private Long salary;
}
