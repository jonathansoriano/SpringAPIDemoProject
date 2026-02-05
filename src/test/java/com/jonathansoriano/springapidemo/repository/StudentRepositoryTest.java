package com.jonathansoriano.springapidemo.repository;

import com.jonathansoriano.springapidemo.domain.StudentRequest;
import com.jonathansoriano.springapidemo.dto.StudentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class StudentRepositoryTest
{
    //We are going to actually use our repository here (in our case its our h2 db)
    @Autowired
    StudentRepository repository;

    @Test
    void findAll_Success() {

    }

    @ParameterizedTest
    @MethodSource("provideNormalFieldsForFind")
    void find_Success(
            Long inputId,
            String inputFirstName,
            String inputLastName,
            LocalDate inputDateOfBirth,
            String inputCity,
            String inputState,
            Long inputUniversityId,
            String inputGrade,
            int expectedSize
    )

    {
        //Arrange
        StudentRequest request = StudentRequest.builder()
                .id(inputId)
                .firstName(inputFirstName)
                .lastName(inputLastName)
                .dob(inputDateOfBirth)
                .residentCity(inputCity)
                .residentState(inputState)
                .universityId(inputUniversityId)
                .grade(inputGrade)
                .build();
        //Act
        List<StudentDto> actualStudentDtoList = repository.find(request);
        //Assert
        assertEquals(expectedSize, actualStudentDtoList.size());

    }
    private static Stream<Arguments> provideNormalFieldsForFind()
    {
        //Normal/Expected Cases for each input field
        return Stream.of(
                Arguments.of(1L, null, null, null, null, null, null, null, 1),
                Arguments.of(null, "Nina", null, null, null, null, null, null, 1),
                Arguments.of(null, null, "Hall", null, null, null, null, null, 1),
                Arguments.of(null, null, null, LocalDate.of(2002, 4, 11), null, null, null, null, 1),
                Arguments.of(null, null, null, null, "Rochester", null, null, null, 1),
                Arguments.of(null, null, null, null, null, "CA", null, null, 9),
                Arguments.of(null, null, null, null, null, null, 5L, null, 5),
                Arguments.of(null, null, null, null, null ,null, null, "Senior", 14)
        );
    }

    @ParameterizedTest
    @MethodSource("provideAbnormalFieldsForFind")
    void find_AbnormalFields_Success(
            Long inputId,
            String inputFirstName,
            String inputLastName,
            LocalDate inputDateOfBirth,
            String inputCity,
            String inputState,
            Long inputUniversityId,
            String inputGrade,
            int expectedSize
    )

    {
        //Arrange
        StudentRequest request = StudentRequest.builder()
                .id(inputId)
                .firstName(inputFirstName)
                .lastName(inputLastName)
                .dob(inputDateOfBirth)
                .residentCity(inputCity)
                .residentState(inputState)
                .universityId(inputUniversityId)
                .grade(inputGrade)
                .build();
        //Act
        List<StudentDto> actualStudentDtoList = repository.find(request);
        //Assert
        assertEquals(expectedSize, actualStudentDtoList.size());

    }
    private static Stream<Arguments> provideAbnormalFieldsForFind()
    {
        //Abnormal/Unexpected Cases for each input field
        return Stream.of(
                Arguments.of(null, null, null, null, null, null, null, null, 63),//Null fields
                Arguments.of(100L, null, null, null, null, null, null, null, 0), //Non-existing ID
                Arguments.of(null, "Tobey", null, null, null, null, null, null, 0),//Non-existing FName
                Arguments.of(null, "nina", null, null, null, null, null, null, 1),//Lower-case FName
                Arguments.of(null, "ni", null, null, null, null, null, null, 2),//Partial-case
                Arguments.of(null, null, "Yuno", null, null, null, null, null, 0),//Non-existing LName
                Arguments.of(null, null, "hall", null, null, null, null, null, 1),//Lower-case LName
                Arguments.of(null, null, "h", null, null, null, null, null, 12),//Partial-case LName
                Arguments.of(null, null, null, LocalDate.of(2100, 12, 12), null, null, null, null, 0),//Non-existing DOB
                Arguments.of(null, null, null, null, "Town", null, null, null, 0),//Non-existing city
                Arguments.of(null, null, null, null, "rochester", null, null, null, 1),//Lower-case city
                Arguments.of(null, null, null, null, "h", null, null, null, 6),//Partial-case city
                Arguments.of(null, null, null, null, null, "YO", null, null, 0),//Non-existing state
                Arguments.of(null, null, null, null, null, "ca", null, null, 0),//Lower-case state
                Arguments.of(null, null, null, null, null, null, 100L, null, 0),//Non-existing UniId
                Arguments.of(null, null, null, null, null ,null, null, "Senor", 0),//Non-existing grade
                Arguments.of(null, null, null, null, null ,null, null, "senior", 0)//Lower-case grade
        );
    }

    @Test
    void insertNewStudent() {
    }

    @Test
    void universityExists() {
    }
}