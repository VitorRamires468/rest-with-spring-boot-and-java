package br.com.vitorramires468.integrationtests.controllers.withyaml;

import br.com.vitorramires468.config.TestConfigs;
import br.com.vitorramires468.integrationtests.controllers.withyaml.mappers.YamlMapper;
import br.com.vitorramires468.integrationtests.dto.PersonDTO;
import br.com.vitorramires468.integrationtests.testcontainers.AbstractIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerYamlTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static YamlMapper yamlMapper;
    private static PersonDTO person;

    @BeforeAll
    static void setUp(){
        yamlMapper = new YamlMapper();
        person = new PersonDTO();
    }

    @Test
    @Order(1)
    void createTest() throws JsonProcessingException {
        mockPerson();

        EncoderConfig encoderConfig = new EncoderConfig()
                .encodeContentTypeAs("application/yaml", ContentType.TEXT);

        RestAssuredConfig config = RestAssuredConfig.config()
                .encoderConfig(encoderConfig);

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.HEADER_PARAM_ERUDIO)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .setConfig(config)
                .build();

        String yamlPerson = yamlMapper.toYamlString(person);

        var content = given(specification)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .body(yamlPerson)
                .when()
                .post()
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .extract()
                .body()
                .asString();

        PersonDTO createdPerson = yamlMapper.fromYamlString(content, PersonDTO.class);
        person = createdPerson;

        assertNotNull(createdPerson.getId());
        assertTrue(createdPerson.getId()>0);

        assertEquals("Linux", createdPerson.getFirstName());
        assertEquals("Torvalds", createdPerson.getLastName());
        assertEquals("Helsink", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
        assertTrue(createdPerson.getEnabled());
    }



    private void mockPerson() {
        person.setFirstName("Linux");
        person.setLastName("Torvalds");
        person.setAddress("Helsink");
        person.setGender("Male");
        person.setEnabled(true);
    }
}
