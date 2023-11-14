package com.example.my_api.dao;

import com.example.my_api.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        String name = person.getName();
        final String sql = "insert into person (id, name) values(?,?)";
        int rows_affected = jdbcTemplate.update(sql,id,name);
        return rows_affected > 0 ? 1 : 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql ="SELECT id , name FROM person";
       return jdbcTemplate.query(sql,(ResultSet,i)->{
            return new Person(UUID.fromString(ResultSet.getString("id")),ResultSet.getString("name"));
        });

    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql ="SELECT id , name FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sql,new Object[]{id} ,(ResultSet,i)->{
            return new Person(UUID.fromString(ResultSet.getString("id")),ResultSet.getString("name"));
        });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "Delete FROM person WHERE id = ?";
        int rows_affected = jdbcTemplate.update(sql,id);
        return rows_affected > 0 ? 1 : 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        String name = person.getName();
        final String sql = "UPDATE person SET name = ? WHERE id=?";
        int rows_affected = jdbcTemplate.update(sql,name,id);
        return rows_affected > 0 ? 1 : 0;
    }
}
