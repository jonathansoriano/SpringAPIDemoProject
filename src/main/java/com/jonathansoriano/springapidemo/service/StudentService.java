package com.jonathansoriano.springapidemo.service;

import com.jonathansoriano.springapidemo.dto.StudentDto;
import com.jonathansoriano.springapidemo.exception.CreationFailure;
import com.jonathansoriano.springapidemo.exception.SearchNotFound;
import com.jonathansoriano.springapidemo.model.Student;
import com.jonathansoriano.springapidemo.repository.StudentRepository;
import com.jonathansoriano.springapidemo.domain.StudentRequest;
import com.jonathansoriano.springapidemo.utils.InputValidationUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StudentService(StudentRepository repository,  NamedParameterJdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> find (StudentRequest request)
    {
        List<Student> list = buildStudentListFromStudentDtoList(repository.find(request));

        if (CollectionUtils.isEmpty(list)) {
            throw new SearchNotFound("Student Not Found!");
        }
        else {
            return list;
        }
    }

    public int insertNewStudent(StudentRequest request)
    {

        // Add validation
        if (InputValidationUtils.isInputNullOrEmpty(request)) {
        throw new CreationFailure("All fields require valid input!");
        }
    
        // Verify the university exists
        if (!repository.universityExists(request.getUniversityId())) {
            throw new CreationFailure("University with ID " + request.getUniversityId() + " does not exist");
        }

        //Verify that capitalization is correct prior to insertion otherwise correct it
        if (!InputValidationUtils.isStringInputFormattedCorrectly(request.getFirstName())){

            request.setFirstName(InputValidationUtils.properNounStringFormatter(request.getFirstName()));
            //This is just a line printed to the console that shows that proper nouns are correctly capitalized.
            System.out.println("This is the updated firstName: " + request.getFirstName());
        }
        if (!InputValidationUtils.isStringInputFormattedCorrectly(request.getLastName())){

            request.setLastName(InputValidationUtils.properNounStringFormatter(request.getLastName()));
            System.out.println("This is the updated lastName: " + request.getLastName());
        }
        if (!InputValidationUtils.isStringInputFormattedCorrectly(request.getResidentCity())){

            request.setResidentCity(InputValidationUtils.properNounStringFormatter(request.getResidentCity()));
            System.out.println("This is the updated Resident City: " + request.getResidentCity());
        }

        int response = repository.insertNewStudent(request);
        if (response != 1) {
            throw new CreationFailure("Student Creation Failed!");
        }
        return response;
}

    List<Student> buildStudentListFromStudentDtoList(List<StudentDto> studentDtos) {
        ArrayList<Student> students = new ArrayList<>();

        if (!studentDtos.isEmpty()){
            studentDtos.forEach(student -> {
                students.add(buildStudentFromStudentDto(student));
            });
        }

        return students;
    }

    Student buildStudentFromStudentDto(StudentDto student) {
        return Student.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .residentCity(student.getResidentCity())
                .residentState(student.getResidentState())
                .universityId(student.getUniversityId())
                .grade(student.getGrade())
                .build();
    }

}