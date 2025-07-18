package com.jonathansoriano.springapidemo.controller;

import com.jonathansoriano.springapidemo.model.Student;
import com.jonathansoriano.springapidemo.request.StudentRequest;
import com.jonathansoriano.springapidemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;

    }
    @GetMapping("/searchStudent")
    public ResponseEntity<List<Student>> getStudent(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) LocalDate dob,
            @RequestParam(required = false) String residentCity,
            @RequestParam(required = false) String residentState,
            @RequestParam(required = false) Long universityId,
            @RequestParam(required = false) String grade
    )
    {
        StudentRequest request = StudentRequest.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .dob(dob)
                .residentCity(residentCity)
                .residentState(residentState)
                .universityId(universityId)
                .grade(grade)
                .build();

        List<Student> response = service.find(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/insertNewStudent")
    public ResponseEntity<String> insertNewStudent(
            @RequestBody StudentRequest request
    )
    {
        StudentRequest newStudentRequest = StudentRequest.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dob(request.getDob())
                .residentCity(request.getResidentCity())
                .residentState(request.getResidentState())
                .universityId(request.getUniversityId())
                .grade(request.getGrade())
                .build();

        int response = service.insertNewStudent(newStudentRequest);

        String message = "Student Inserted Successfully: " + response;

        return new ResponseEntity<>(message, HttpStatus.CREATED);//Returns message and 201 status

    }
}
