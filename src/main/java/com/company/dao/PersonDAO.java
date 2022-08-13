package com.company.dao;

import com.company.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 0;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index(){
        List<Person> list = jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
        PEOPLE_COUNT = list.size();
        return list;
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        jdbcTemplate.update("INSERT INTO person VALUES(?, ?, ?, ?)", person.getId(), person.getName(),
                person.getAge(), person.getEmail());
    }

    public void update(int id, Person person){
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?", person.getName(),
                person.getAge(), person.getEmail(), id);
    }

    public void delete(int id){
       jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
