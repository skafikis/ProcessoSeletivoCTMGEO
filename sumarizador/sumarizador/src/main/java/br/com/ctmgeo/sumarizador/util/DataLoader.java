package br.com.ctmgeo.sumarizador.util;

import br.com.ctmgeo.sumarizador.model.Person;
import br.com.ctmgeo.sumarizador.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    public void loadData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("csv/lista.csv").getInputStream()))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) !=null) {
                try {
                    String[] fields = line.split(",");

                    String gender = fields[0];
                    String title = fields[1];
                    String givenName = fields[2];
                    String middleInitial = fields[3];
                    String surname = fields[4];
                    String state = fields[5];
                    String emailAddress = fields[6];
                    LocalDate birthdate = LocalDate.parse(fields[7], formatter);
                    Double latitude = Double.parseDouble(fields[8]);
                    Double longitude = Double.parseDouble(fields[9]);

                    Person person = new Person(null, gender, title, givenName, middleInitial, surname, state, emailAddress, birthdate, latitude, longitude);
                    personRepository.save(person);
                } catch (Exception e){
                    System.err.println("Erro ao processar o registro: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}