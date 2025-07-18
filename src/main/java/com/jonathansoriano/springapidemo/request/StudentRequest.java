package com.jonathansoriano.springapidemo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String residentCity;
    private String residentState;
    private Long universityId;
    private String grade;
}
