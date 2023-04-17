package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.flywaydb.core.internal.jdbc.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override public int insertPerson(UUID id, Person person) {
        return 0;
    }

    private RowMapper<Person> mapPersonFromDb(){
        return resultSet ->
            new Person(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("name"));
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name FROM person;";
        List<Person> people = new ArrayList<>();
        people = jdbcTemplate.query(sql, (resultSet, i) ->
            new Person(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("name")));
        return people;
    }

    @Override
        public Optional<Person> selectPersonByid(UUID id) {
        return Optional.empty();
    }

    @Override public int deletePersonById(UUID id) {
        return 0;
    }

    @Override public int updatePersonById(UUID id, Person person) {
        return 0;
    }
}
