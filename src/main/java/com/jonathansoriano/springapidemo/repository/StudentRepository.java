package com.jonathansoriano.springapidemo.repository;

import com.jonathansoriano.springapidemo.dto.StudentDto;
import com.jonathansoriano.springapidemo.domain.StudentRequest;
import com.jonathansoriano.springapidemo.exception.CreationFailure;
import com.jonathansoriano.springapidemo.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private final String AND_FIRST_NAME = "AND LOWER(first_name) LIKE :firstName";
    private final String AND_LAST_NAME = "AND LOWER(last_name) LIKE :lastName";
    private final String AND_DOB = "AND dob = :dob";
    private final String AND_RESIDENT_CITY = "AND LOWER(resident_city) LIKE :residentCity";
    private final String AND_RESIDENT_STATE = "AND resident_state = :residentState";
    private final String AND_UNIVERSITY_ID = "AND university_id = :universityId";
    private final String AND_GRADE = "AND grade = :grade";

    private final String SQL_INSERT_NEW_STUDENT = "INSERT INTO student (first_name,last_name,dob, resident_city, resident_state, university_id, grade) VALUES (:firstName, :lastName, :dob, :residentCity, :residentState, :universityId, :grade);";

    private final String SQL_CHECK_UNIVERSITY_EXISTENCE = "SELECT COUNT(*) FROM university WHERE 1=1 AND id = :id";

    public StudentRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<StudentDto> find (StudentRequest request){
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", request.getId())
                .addValue("firstName", "%" + StringUtils.lowerCase(request.getFirstName()) + "%")
                .addValue("lastName", "%" + StringUtils.lowerCase(request.getLastName()) + "%")
                .addValue("dob", request.getDob())
                .addValue("residentCity", "%" + StringUtils.lowerCase(request.getResidentCity()) + "%")
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

        return jdbcTemplate.update(SQL_INSERT_NEW_STUDENT, params); //update() returns an "int" to indicate how many rows
                                                                    // Were affected by the SQL Operation. If int > 0,
                                                                    // Then operation was successful; else no rows were inserted/updated...
    }
    public boolean universityExists(Long universityId)
    {
        try {
            //String sql = "SELECT COUNT(*) FROM university WHERE id = :id";

            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("id", universityId);

        /*
        QueryForObject()
        It is used to execute a SQL query that returns exactly one row and
        map a single column of that row to a Java object of the specified type
         */
            Integer count = jdbcTemplate.queryForObject(SQL_CHECK_UNIVERSITY_EXISTENCE, params, Integer.class); //NamedJDBCTemplate can throw exceptions... like EmptyResultDataAccessException if there are no rows. IncorrectResultSizeDataAccessException is thrown if many rows are found.

            return count != null && count > 0; //Checks if value in the DB is NOT null or if the university or many instances of the university exists in the DB
        } catch (EmptyResultDataAccessException e) { //This exception is thrown if no rows are found
            return false;
        } catch (DataAccessException e) { //This is thrown for any other database issues.
            // Log the database access error
            throw new CreationFailure("Unable to verify university existence due to database error");
        }
    }
}