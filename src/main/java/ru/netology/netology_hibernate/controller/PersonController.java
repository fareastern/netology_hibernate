package ru.netology.netology_hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.netology_hibernate.entity.Person;
import ru.netology.netology_hibernate.entity.PersonId;
import ru.netology.netology_hibernate.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    // Получить всех пользователей
    @GetMapping
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // Получить по городу
    @GetMapping("/by-city")
    public List<Person> getPersonsByCity(@RequestParam String city) {
        return personRepository.findByCityOfLiving(city);
    }

    // Получить по возрасту меньше заданного
    @GetMapping("/younger-than")
    public List<Person> getPersonsYoungerThan(@RequestParam int age) {
        return personRepository.findByAgeLessThanOrderByAgeAsc(age);
    }

    // Получить по имени и фамилии
    @GetMapping("/by-name-and-surname")
    public ResponseEntity<Person> getPersonByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        Optional<Person> personOpt = personRepository.findByNameAndSurname(name, surname);
        return personOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Создать пользователя
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    // Обновить пользователя
    @PutMapping
    public Person updatePerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    // Удалить по составному ключу
    @DeleteMapping
    public void deletePerson(@RequestParam String name, @RequestParam String surname, @RequestParam int age) {
        PersonId id = new PersonId(name, surname, age);
        personRepository.deleteById(id);
    }
}