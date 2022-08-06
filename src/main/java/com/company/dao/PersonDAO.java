package com.company.dao;

import com.company.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 0;
    List<Person> people;
    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Valera"));
        people.add(new Person(++PEOPLE_COUNT, "Lera"));
        people.add(new Person(++PEOPLE_COUNT, "Oksana"));
        people.add(new Person(++PEOPLE_COUNT, "Roma"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person){
        Person personForUpdate = show(id);

        personForUpdate.setName(person.getName());
    }

    public void delete(int id){
        people.removeIf(x -> x.getId()==id);
    }
}
