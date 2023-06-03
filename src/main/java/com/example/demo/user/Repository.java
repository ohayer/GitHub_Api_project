package com.example.demo.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class Repository {
    private String name;
    private Owner owner;
    private List<Branch> branches;

}
