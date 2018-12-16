package es.didiez.DATAJPA1297;

import java.io.IOException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.OracleContainer;

/**
 *
 * @author Diego Diez
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ContextConfiguration(initializers = {OracleDataJpa1927BugTests.Initializer.class})
public class OracleDataJpa1927BugTests {

    @ClassRule
    public static OracleContainer oracleContainer = new OracleContainer("wnameless/oracle-xe-11g");
    
    @Autowired
    private CityRepository cityRepository;
    
    @Test
    public void testStoredProcedurePositionalParameters() throws IOException {  
        assertThat(cityRepository.callDoSomethingPositionalParameters(123, 123)).isEqualTo("OK");
        assertThat(cityRepository.callDoSomethingPositionalParameters(123, 456)).isEqualTo("KO");
    }
    
    @Test
    public void testStoredProcedureNamedParameters() throws IOException {        
        assertThat(cityRepository.callDoSomethingNamedParameters(123, 123)).isEqualTo("OK");
        assertThat(cityRepository.callDoSomethingNamedParameters(123, 456)).isEqualTo("KO");
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.platform=oracle",
                    "spring.datasource.url=" + oracleContainer.getJdbcUrl(),
                    "spring.datasource.username=" + oracleContainer.getUsername(),
                    "spring.datasource.password=" + oracleContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
