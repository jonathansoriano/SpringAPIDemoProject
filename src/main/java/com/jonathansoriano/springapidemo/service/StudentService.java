package com.jonathansoriano.springapidemo.service;

import com.jonathansoriano.springapidemo.dto.StudentDto;
import com.jonathansoriano.springapidemo.exception.CreationFailure;
import com.jonathansoriano.springapidemo.exception.SearchNotFound;
import com.jonathansoriano.springapidemo.model.Student;
import com.jonathansoriano.springapidemo.repository.StudentRepository;
import com.jonathansoriano.springapidemo.domain.StudentRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
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

    public int insertNewStudent(StudentRequest request) {
        // Add validation
        if (request.getFirstName() == null || request.getLastName() == null ||
            request.getDob() == null || request.getResidentCity() == null ||
            request.getResidentState() == null || request.getUniversityId() == null ||
            request.getGrade() == null) {
        throw new CreationFailure("All fields are required");
    }
    
    // Verify university exists
//    if (!universityExists(request.getUniversityId())) {
//        throw new CreationFailure("University with ID " + request.getUniversityId() + " does not exist");
//    }

    int response = repository.insertNewStudent(request);
    if (response != 1) {
        throw new CreationFailure("Student Creation Failed!");
    }
    return response;
}

//private boolean universityExists(Long universityId) {
//    String sql = "SELECT COUNT(*) FROM university WHERE id = :id";
//    MapSqlParameterSource params = new MapSqlParameterSource()
//            .addValue("id", universityId);
//    int count = jdbcTemplate.queryForObject(sql, params, Integer.class);
//    return count > 0;
//}


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