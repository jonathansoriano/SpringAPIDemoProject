package com.jonathansoriano.springapidemo.service;

import com.jonathansoriano.springapidemo.dto.StudentDto;
import com.jonathansoriano.springapidemo.exception.CreationFailure;
import com.jonathansoriano.springapidemo.exception.SearchNotFound;
import com.jonathansoriano.springapidemo.model.Student;
import com.jonathansoriano.springapidemo.repository.StudentRepository;
import com.jonathansoriano.springapidemo.domain.StudentRequest;
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
        if (isInputNullOrEmpty(request)) {
        throw new CreationFailure("All fields require valid input!");
        }
    
        // Verify the university exists
        if (!universityExists(request.getUniversityId())) {
            throw new CreationFailure("University with ID " + request.getUniversityId() + " does not exist");
        }

        int response = repository.insertNewStudent(request);
        if (response != 1) {
            throw new CreationFailure("Student Creation Failed!");
        }
        return response;
}

boolean universityExists(Long universityId)
{
    try {
        String sql = "SELECT COUNT(*) FROM university WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", universityId);

        /*
        QueryForObject()
        It is used to execute a SQL query that returns exactly one row and
        map a single column of that row to a Java object of the specified type
         */
        Integer count = jdbcTemplate.queryForObject(sql, params, Integer.class); //NamedJDBCTemplate can throw exceptions... like EmptyResultDataAccessException if there are no rows. IncorrectResultSizeDataAccessException is thrown if many rows are found.

        return count != null && count > 0; //Check if value in the DB is null or if the university or many instances of the university exists in the DB
    } catch (EmptyResultDataAccessException e) { //This exception is thrown if no rows are found
        return false;
    } catch (DataAccessException e) { //This is thrown for any other database issues.
        // Log the database access error
        throw new CreationFailure("Unable to verify university existence due to database error");
    }
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

    private boolean isInputNullOrEmpty(StudentRequest request)
    {
        return request.getFirstName() == null || request.getFirstName().matches("^*$") ||
                request.getLastName() == null || request.getLastName().matches("^*$") ||
                request.getResidentCity() == null || request.getResidentCity().matches("^*$") ||
                request.getResidentState() == null || request.getResidentState().matches("^*$") ||
                request.getGrade() == null || request.getGrade().matches("^*$") ||
                request.getUniversityId() == null || request.getDob() == null;
    }
}