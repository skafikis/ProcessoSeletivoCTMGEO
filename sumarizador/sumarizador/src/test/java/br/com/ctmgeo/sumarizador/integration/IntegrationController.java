package br.com.ctmgeo.sumarizador.integration;

import br.com.ctmgeo.sumarizador.service.PersonService;
import br.com.ctmgeo.sumarizador.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataLoader dataLoader;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    @BeforeEach
    public void setUp() {
        dataLoader.loadData();
    }


    /**
     * Todo: Verifica se os estados com o menor e maior número de pessoas de cada sexo são retornados corretamente.
     *
     * Testando endpoint stats/sex,
     * fazendo requisição GET e verifica se a resposta HTTP tem retorno;
     *
     * Verifica se campo "min" e "max" possui dois elementos e verifica se contem
     * "male" e "female" em ambos os elementos;
     *
     *
     * @throws Exception
     * @Author Victor William
     */
    @Test
    public void testGetStatsBySex() throws Exception {
        mockMvc.perform(get("/api/persons/stats/sex").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.min", hasSize(2)))
                .andExpect(jsonPath("$.min[0].gender", is("male")))
                .andExpect(jsonPath("$.min[1].gender", is("female")))

                .andExpect(jsonPath("$.max", hasSize(2)))
                .andExpect(jsonPath("$.max[0].gender", is("male")))
                .andExpect(jsonPath("$.max[1].gender", is("female")));
    }


    /**
     * Todo: Verifica se os estados com maior número de pessoas acima de 50 anos e abaixo de 20 anos são retornados corretamente.
     *
     * Testando endpoint stats/sex,
     * fazendo requisição GET e verifica se a resposta HTTP tem retorno;
     *
     * Verifica se campo "greaterEquals50" e "lowerEquals20" possui pleo menos um elemento.
     *
     *
     * @throws Exception
     * @Author Victor William
     */
    @Test
    public void testGetStatsByAge() throws Exception {
        mockMvc.perform(get("/api/persons/stats/age").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.greaterEquals50", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$.lowerEquals20", hasSize(greaterThanOrEqualTo(1))));
    }


    /**
     * Todo: Verifica se a distribuição de pessoas por sexo em cada estado é retornada corretamente.
     *
     * Testando endpoint stats/sex,
     * fazendo requisição GET e verifica se a resposta HTTP tem retorno;
     *
     * Verifica se a lista contem pelo menos uma distribuição de elementos não nulos.
     *
     *
     * @throws Exception
     * @Author Victor William
     */
    @Test
    public void testGetStateSexDistribution() throws Exception {
        mockMvc.perform(get("/api/persons/stats/sexDistribution").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].state", notNullValue()))
                .andExpect(jsonPath("$[0].male", notNullValue()))
                .andExpect(jsonPath("$[0].female", notNullValue()));
    }
}