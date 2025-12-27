package com.jonathansoriano.springapidemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonathansoriano.springapidemo.domain.StudentRequest;
import com.jonathansoriano.springapidemo.exception.SearchNotFound;
import com.jonathansoriano.springapidemo.model.Student;
import com.jonathansoriano.springapidemo.repository.StudentRepository;
import com.jonathansoriano.springapidemo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    //We test for exceptions in the controller layer to verify the exception is translated to the
    //correct HTTP response (Status code, body, headers)

    @MockitoBean
    private StudentService service;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper; //Import not used, but it's there for reason... research on it.

    @Test
    void getStudent_200_Success() throws Exception {
        //Arrange - what gets passed into or returned back to this method? (Only worry about what gets returned by service layer)

        // A List<Student> gets returned by the service layer
        List<Student> studentList = getStudentList();

        //Any() here tells Mockito: "I don’t care what UniversitySearchRequest is passed in — just return this list."
        when(service.find(any())).thenReturn(studentList);


        //Act
        mockMvc.perform(get("/students/searchStudent")
                        .param("firstName", "Jo") //We need this to add a query param to the request URL
                        .contentType(MediaType.APPLICATION_JSON))//Tells the controller we are sending a request with JSON Data back to the server. Used for POST PUT request normally
                .andExpect(status().isOk());
        //Assert
        assertEquals(2, studentList.size());
        assertEquals("Jon", studentList.get(0).getFirstName());
        assertEquals("Jordan", studentList.get(1).getFirstName());
    }

    @Test
    void getStudent_404_Success() throws Exception{
        //Arrange - what gets passed into or returned back to this method?
        List<Student> emptyList = new ArrayList<>();

        when(service.find(any())).thenThrow(SearchNotFound.class);

        //Act
        mockMvc.perform(get("/students/searchStudent")
                .param("firstName", "Javier")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        //Assert
        assertEquals(0, emptyList.size());
    }

    @Test
    void getStudent_500_Success() throws Exception {
        //Arrange - what gets passed into or returned back to this method?

        //doThrow.when() pattern compared to the when().thenThrow patten
        doThrow(IllegalArgumentException.class).when(service).find(any());
        //Why this syntax? Why not .when(service.find(any()));?


        //Act and Assert
        mockMvc.perform(get("/students/searchStudent")
                .param("id", "one")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }


    @Test
    void insertNewStudent() {
    }


    private static List<Student> getStudentList() {
        List<Student> studentList = new ArrayList<>();

        Student student1 = Student.builder()
                .id(1L)
                .firstName("Jon")
                .lastName("Sanjuan")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityId(1L)
                .grade("Freshmen")
                .build();

        Student student2 = Student.builder()
                .id(2L)
                .firstName("Jordan")
                .lastName("Powell")
                .residentCity("Dayton")
                .residentState("OH")
                .universityId(2L)
                .grade("Senior")
                .build();

        studentList.add(student1);
        studentList.add(student2);

        return studentList;
    }

}