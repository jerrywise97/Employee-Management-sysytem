package com.employee.employee.data.models;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String email;


    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String address;


}
