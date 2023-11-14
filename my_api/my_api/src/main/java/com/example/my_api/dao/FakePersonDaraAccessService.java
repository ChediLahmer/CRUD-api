package com.example.my_api.dao;

import com.example.my_api.model.Person;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository("fakeDao")
public class FakePersonDaraAccessService implements PersonDao {
    private static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id , Person person){
        DB.add(new Person(id, person.getName()));
        return 1;
    }
    @Override
    public List<Person> selectAllPeople(){
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id){
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMay = selectPersonById(id);
        if(personMay.isEmpty()){
            return 0;
        }
        else
        {
            DB.remove(personMay.get());
            return 1;
        }

    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return selectPersonById(id).map(person -> {
            int indexofPersonToUpdate = DB.indexOf(person);
            if(indexofPersonToUpdate >=0){
                DB.set(indexofPersonToUpdate,new Person(id, update.getName()));
                return 1;
            }
            return 0;
        }).orElse(0);

    }

}
