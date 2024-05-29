package br.com.ctmgeo.sumarizador.repository;

import br.com.ctmgeo.sumarizador.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p.state, COUNT(p) FROM Person p WHERE p.gender = 'male' GROUP BY p.state ORDER BY COUNT(p) ASC")
    List<Object[]> findMinMaleByState();

    @Query("SELECT p.state, COUNT(p) FROM Person p WHERE p.gender = 'female' GROUP BY p.state ORDER BY COUNT(p) ASC")
    List<Object[]> findMinFemaleByState();

    @Query("SELECT p.state, COUNT(p) FROM Person p WHERE p.gender = 'male' GROUP BY p.state ORDER BY COUNT(p) DESC")
    List<Object[]> findMaxMaleByState();

    @Query("SELECT p.state, COUNT(p) FROM Person p WHERE p.gender = 'female' GROUP BY p.state ORDER BY COUNT(p) DESC")
    List<Object[]> findMaxFemaleByState();


    @Query("SELECT p.state, COUNT(p) FROM Person p WHERE p.birthdate <= ?1 GROUP BY p.state ORDER BY COUNT(p) DESC")
    List<Object[]> findPeopleOlderThanByState(LocalDate date);

    @Query("SELECT p.state, COUNT(p) FROM Person p WHERE p.birthdate >= ?1 GROUP BY p.state ORDER BY COUNT(p) DESC")
    List<Object[]> findPeopleYoungerThanByState(LocalDate date);


    @Query("SELECT p.state, SUM(CASE WHEN p.gender = 'male' THEN 1 ELSE 0 END), SUM(CASE WHEN p.gender = 'female' THEN 1 ELSE 0 END) FROM Person p GROUP BY p.state ORDER BY p.state")
    List<Object[]> findStateSexDistribution();
}
