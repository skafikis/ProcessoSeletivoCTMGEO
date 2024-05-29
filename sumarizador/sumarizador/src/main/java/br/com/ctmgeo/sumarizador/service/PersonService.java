package br.com.ctmgeo.sumarizador.service;

import br.com.ctmgeo.sumarizador.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Obtém os estados com maior e menor número de pessoas de cada sexo.
     *
     *
     * @return
     * @Author Victor William
     */
    public Map<String, List<Map<String, Object>>> getStatsBySex() {
        Map<String, List<Map<String, Object>>> result = new LinkedHashMap<>();

        List<Map<String, Object>> minStats = new ArrayList<>();
        List<Map<String, Object>> maxStats = new ArrayList<>();

        minStats.add(getStatsMap("male", personRepository.findMinMaleByState()));
        minStats.add(getStatsMap("female", personRepository.findMinFemaleByState()));

        maxStats.add(getStatsMap("male", personRepository.findMaxMaleByState()));
        maxStats.add(getStatsMap("female", personRepository.findMaxFemaleByState()));

        result.put("min", minStats);
        result.put("max", maxStats);

        return result;
    }
    private Map<String, Object> getStatsMap(String gender, List<Object[]> stats) {
        Map<String, Object> statsMap = new LinkedHashMap<>();
        Object[] data = stats.get(0);

        statsMap.put("gender", gender);
        statsMap.put("state", data[0]);
        statsMap.put("total", data[1]);

        return statsMap;
    }

    /**
     * Obtém os estados com maior número de pessoas acima de 50 anos e abaixo de 20 anos.
     *
     *
     * @return
     * @Author Victor WIlliam
     */
    public Map<String, List<Map<String, Object>>> getStatsByAge() {
        LocalDate fiftyYearsAgo = LocalDate.now().minusYears(50);
        LocalDate twentyYearsAgo = LocalDate.now().minusYears(20);

        List<Object[]> olderThan50 = personRepository.findPeopleOlderThanByState(fiftyYearsAgo);
        List<Object[]> youngerThan20 = personRepository.findPeopleYoungerThanByState(twentyYearsAgo);

        List<Map<String, Object>> greaterEquals50 = olderThan50.stream()
                .map(result -> {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("state", result[0]);
                    map.put("total", result[1]);
                    return map;
                })
                .collect(Collectors.toList());

        List<Map<String, Object>> lowerEquals20 = youngerThan20.stream()
                .map(result -> {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("state", result[0]);
                    map.put("total", result[1]);
                    return map;
                })
                .collect(Collectors.toList());

        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        result.put("greaterEquals50", greaterEquals50);
        result.put("lowerEquals20", lowerEquals20);

        return result;
    }

    /**
     * Obter uma relação de estados e a quantidade de pessoas de cada sexo, ordenada pelo estado.
     *
     *
     * @return
     * @Author Victor William
     */
    public List<Map<String, Object>> getStateSexDistribution() {
        return  personRepository.findStateSexDistribution().stream()
                .map(e -> {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("state", e[0]);
                    map.put("male", e[1]);
                    map.put("female", e[2]);
                    return map;
                })
                .collect(Collectors.toList());
    }
}
