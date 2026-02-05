package com.jonathansoriano.springapidemo.service;

import com.jonathansoriano.springapidemo.domain.StudentRequest;
import com.jonathansoriano.springapidemo.dto.StudentDto;
import com.jonathansoriano.springapidemo.exception.SearchNotFound;
import com.jonathansoriano.springapidemo.model.Student;
import com.jonathansoriano.springapidemo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    void find_exception(){
        //Arrange

            //find() takes a request. Query on an ID that is non-existent
        StudentRequest studentRequest = StudentRequest.builder()
                .id(404L)
                .build();
            //mock the behavior of the repository when we get a non-existing ID
        when(repository.find(any())).thenReturn(Collections.emptyList()); // Could've created an empty StudentDto List too.

        //Assert & Act (the lambda)
            //We expect a SearchNotFound Exception, and this is what actually happens with this code (executes the Act inside the lambda)
        SearchNotFound ex = assertThrows(SearchNotFound.class, () -> service.find(studentRequest));
        assertEquals("Student Not Found!", ex.getMessage());
    }

    @Test
    void insertNewStudent() {
    }

    @Test
    void universityExists() {
    }

    @Test
    void buildStudentListFromStudentDtoList_ReturnsList() {
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

        List<Student> studentList = getStudentList();//Expected list after conversion from studentdto to student
        //Act
        List<Student> actual = service.buildStudentListFromStudentDtoList(studentDtoList); //Actual list after conversion from studentdto to student
        //Assert
        assertEquals(studentList, actual);

    }

    @Test
    void buildStudentListFromStudentDtoList_ReturnsEmptyList(){
        //Arrange
         //Empty StudentDto List if Repo doesn't find a user
        List<StudentDto> studentDtoList = new ArrayList<>();
        // Empty Student List, since Repo could return a StudentDto, there's nothing to convert to student type
        List<Student> studentList = new ArrayList<>();


        //Act
        List<Student> actualStudentList = service.buildStudentListFromStudentDtoList(studentDtoList);
        //Assert
        assertEquals(studentList, actualStudentList);
        assertEquals(studentList.size(), actualStudentList.size());
    }

    @Test
    void buildStudentFromStudentDto() {
        //Arrange
        StudentDto studentDto = StudentDto.builder()
                .id(1L)
                .firstName("Jon")
                .lastName("Soriano")
                .dob("2002-04-11")
                .residentCity("Town")
                .residentState("OH")
                .universityId(1L)
                .grade("Senior")
                .build();

        Student expectedStudent = Student.builder()
                .id(1L)
                .firstName("Jon")
                .lastName("Soriano")
                .residentCity("Town")
                .residentState("OH")
                .universityId(1L)
                .grade("Senior")
                .build();

        // Act
        Student actualStudent = service.buildStudentFromStudentDto(studentDto);
        //Assert
        assertEquals(expectedStudent, actualStudent);
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