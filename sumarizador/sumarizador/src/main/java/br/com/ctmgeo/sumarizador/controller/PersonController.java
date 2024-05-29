package br.com.ctmgeo.sumarizador.controller;

import br.com.ctmgeo.sumarizador.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    // OK
    @GetMapping("stats/sex")
    public ResponseEntity<?> getStatsBySex() {
        return ResponseEntity.ok(personService.getStatsBySex());
    }

    //OK
    @GetMapping("stats/age")
    public ResponseEntity<?> getStatsByAge() {
        return ResponseEntity.ok(personService.getStatsByAge());
    }

    //OK
    @GetMapping("stats/sexDistribution")
    public ResponseEntity<?> getStateSexDistribution() {
        return ResponseEntity.ok(personService.getStateSexDistribution());
    }
}
