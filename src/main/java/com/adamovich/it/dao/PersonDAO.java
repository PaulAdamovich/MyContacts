package com.adamovich.it.dao;

import com.adamovich.it.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private static int ID_COUNT = 1 ;

    public int getIdCount(){
        return PersonDAO.ID_COUNT;
    }
    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Person newPerson) {
     jdbcTemplate.update("INSERT INTO contacts VALUES (?, ?, ?, ?, ?, ?, ?)",
     newPerson.getId(), newPerson.getFirstName(), newPerson.getLastName(),
             newPerson.getAge(), newPerson.getCity(), newPerson.getMobileNumber(),
             newPerson.getEmail());
     ++ID_COUNT;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM contacts", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM contacts WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM contacts WHERE id=?", id);
        --ID_COUNT;
    }

    public void update(Person updatePerson, int id){
        jdbcTemplate.update("UPDATE contacts SET firstName=?, lastName=?, city=?, age=?, mobileNumber=?, email=? WHERE id=?",
                updatePerson.getFirstName(), updatePerson.getLastName(), updatePerson.getCity(), updatePerson.getAge(),
                updatePerson.getMobileNumber(), updatePerson.getEmail(), updatePerson.getId());
    }
}