package com.company.dao;

import com.company.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
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
}
