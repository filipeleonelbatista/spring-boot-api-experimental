package br.com.filipeleonelbatista.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.filipeleonelbatista.api.model.Person;

@Repository
public interface Repo extends CrudRepository<Person, Integer> {

  @SuppressWarnings("null")
  List<Person> findAll();

  Person findById(int id);

  List<Person> findByOrderByNameDesc();

  List<Person> findByNameOrderByAgeDesc(String name);

  List<Person> findByNameContaining(String text);

  List<Person> findByNameStartsWith(String text);

  List<Person> findByNameEndsWith(String text);

  @Query(value="SELECT SUM(age) FROM people", nativeQuery = true)
  int sumAges();

  @Query(value="SELECT * FROM people WHERE age >= :age", nativeQuery = true)
  List<Person> ageMoreOrEqual(int age);

  int countById(int id);
}
