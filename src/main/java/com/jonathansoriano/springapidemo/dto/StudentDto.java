package com.jonathansoriano.springapidemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto
{
    private Long id;
    private String firstName;
    private String lastName;
    private String dob;
    private String residentCity;
    private String residentState;
    private Long universityId;
    private String grade;
}
