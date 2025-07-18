package com.jonathansoriano.springapidemo.repository;

import com.jonathansoriano.springapidemo.dto.StudentDto;
import com.jonathansoriano.springapidemo.domain.StudentRequest;
import com.jonathansoriano.springapidemo.utils.SqlUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String SELECT = "SELECT * FROM student WHERE 1=1";
    private final String AND_ID = "AND id = :id";
    private final String AND_FIRST_NAME = "AND first_name = :firstName";
    private final String AND_LAST_NAME = "AND last_name = :lastName";
    private final String AND_DOB = "AND dob = :dob";
    private final String AND_RESIDENT_CITY = "AND resident_city = :residentCity";
    private final String AND_RESIDENT_STATE = "AND resident_state = :residentState";
    private final String AND_UNIVERSITY_ID = "AND university_id = :universityId";
    private final String AND_GRADE = "AND grade = :grade";

    private final String SQL_INSERT_NEW_STUDENT = "INSERT INTO student (first_name,last_name,dob, resident_city, resident_state, university_id, grade) VALUES (:firstName, :lastName, :dob, :residentCity, :residentState, :universityId, :grade);";

    public StudentRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<StudentDto> find (StudentRequest request){
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", request.getId())
                .addValue("firstName", request.getFirstName())
                .addValue("lastName", request.getLastName())
                .addValue("dob", request.getDob())
                .addValue("residentCity", request.getResidentCity())
                .addValue("residentState", request.getResidentState())
                .addValue("universityId", request.getUniversityId())
                .addValue("grade", request.getGrade());

        StringBuilder query = new StringBuilder(SELECT)
                .append(SqlUtils.appendWhereIfNotNull(AND_ID, request.getId()))
                .append(SqlUtils.appendWhereIfNotNull(AND_FIRST_NAME, request.getFirstName()))
                .append(SqlUtils.appendWhereIfNotNull(AND_LAST_NAME, request.getLastName()))
                .append(SqlUtils.appendWhereIfNotNull(AND_DOB, request.getDob()))
                .append(SqlUtils.appendWhereIfNotNull(AND_RESIDENT_CITY, request.getResidentCity()))
                .append(SqlUtils.appendWhereIfNotNull(AND_RESIDENT_STATE, request.getResidentState()))
                .append(SqlUtils.appendWhereIfNotNull(AND_UNIVERSITY_ID, request.getUniversityId()))
                .append(SqlUtils.appendWhereIfNotNull(AND_GRADE, request.getGrade()));

        return jdbcTemplate.query(query.toString(), params, new BeanPropertyRowMapper<>(StudentDto.class, true));
    }

    public int insertNewStudent(StudentRequest request) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstName", request.getFirstName())
                .addValue("lastName", request.getLastName())
                .addValue("dob", request.getDob())  // Now it's a proper LocalDate
                .addValue("residentCity", request.getResidentCity())
                .addValue("residentState", request.getResidentState())
                .addValue("universityId", request.getUniversityId())
                .addValue("grade", request.getGrade());

        System.out.println("Params: " + params.getValues());

        return jdbcTemplate.update(SQL_INSERT_NEW_STUDENT, params);
    }
}