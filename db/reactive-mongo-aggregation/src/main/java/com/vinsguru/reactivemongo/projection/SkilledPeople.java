package com.vinsguru.reactivemongo.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SkilledPeople {

    private String skill;
    private List<String> names;

}
