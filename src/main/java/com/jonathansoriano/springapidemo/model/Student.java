package com.jonathansoriano.springapidemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student
{
    private Long id;
    private String firstName;
    private String lastName;
    //private String dob; We don't to return this information back to the user.
    private String residentCity;
    private String residentState;
    private Long universityId;
    private String grade;
}
