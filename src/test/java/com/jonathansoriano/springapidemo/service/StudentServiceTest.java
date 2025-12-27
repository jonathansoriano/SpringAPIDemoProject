package com.jonathansoriano.springapidemo.service;

import com.jonathansoriano.springapidemo.domain.StudentRequest;
import com.jonathansoriano.springapidemo.dto.StudentDto;
import com.jonathansoriano.springapidemo.model.Student;
import com.jonathansoriano.springapidemo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class) //What does this do? Without this we get null instances of the Repository and Service layer.
class StudentServiceTest {
    //We are mocking the Repository Layer
    @Mock
    StudentRepository repository;
    //Creates an instance of the class under test and automatically injects any fields annotated with @Mock (or @Spy) into it
    @InjectMocks
    StudentService service;

    @Test
    void find() {
        //Arrange
        StudentRequest request = StudentRequest.builder()
                .id(1L)
                .build();

        StudentDto student = StudentDto.builder()
                .id(1L)
                .firstName("Jon")
                .lastName("Sanjuan")
                .dob("2000-05-11")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityId(1L)
                .grade("Senior")
                .build();

        List<StudentDto> studentDtoList = List.of(student);

        when(repository.find(request)).thenReturn(studentDtoList);

        //Act - Test what the service method you are testing
        List<Student> actualList = service.find(request);
        //Assert
        assertEquals(student.getId(), actualList.get(0).getId());
        assertEquals(student.getFirstName(), actualList.get(0).getFirstName());
    }

    @Test
    void insertNewStudent() {
    }

    @Test
    void universityExists() {
    }

    @Test
    void buildStudentListFromStudentDtoList() {
        //Arrange
        StudentDto studentDto1 = StudentDto.builder()
                .id(1L)
                .firstName("Jon")
                .lastName("Sanjuan")
                .dob("2000-05-11")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityId(1L)
                .grade("Senior")
                .build();

        StudentDto studentDto2 = StudentDto.builder()
                .id(2L)
                .firstName("Juan")
                .lastName("Perez")
                .dob("2000-05-11")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityId(1L)
                .grade("Senior")
                .build();
        List<StudentDto> studentDtoList = List.of(studentDto1, studentDto2);

        List<Student> studentList = getStudentList();
        //Act
        List<Student> actual = service.buildStudentListFromStudentDtoList(studentDtoList);
        //Assert
        assertEquals(studentDto1.getFirstName(), actual.get(0).getFirstName());
        assertEquals(studentDto2.getFirstName(), actual.get(1).getFirstName());
    }

    @Test
    void buildStudentFromStudentDto() {

    }

    @Test
    void isStringInputFormattedCorrectly() {
    }

    @Test
    void properNounStringFormatter() {
    }

    private static List<Student> getStudentList(){
        List<Student> students = new ArrayList<>();

        Student student1 = Student.builder()
                .id(1L)
                .firstName("Jon")
                .lastName("Sanjuan")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityId(1L)
                .grade("Senior")
                .build();

        Student student2 = Student.builder()
                .id(2L)
                .firstName("Juan")
                .lastName("Perez")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityId(1L)
                .grade("Senior")
                .build();

        students.add(student1);
        students.add(student2);

        return students;
    }
}