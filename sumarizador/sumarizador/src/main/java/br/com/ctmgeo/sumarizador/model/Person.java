package br.com.ctmgeo.sumarizador.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String gender;
    private String title;
    private String givenName;
    private String middleInitial;
    private String surname;
    private String state;
    private String emailAddress;
    private LocalDate birthdate;
    private Double latitude;
    private Double longitude;
}
