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
import org.testcontainers.containers.MariaDBContainer;

/**
 *
 * @author Diego Diez
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ContextConfiguration(initializers = {MariaDBDataJpa1927BugTests.Initializer.class})
public class MariaDBDataJpa1927BugTests {

    @ClassRule
    public static MariaDBContainer mariaDB = new MariaDBContainer();
    
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
                    "spring.datasource.platform=mariadb",
                    "spring.datasource.url=" + mariaDB.getJdbcUrl(),
                    "spring.datasource.username=" + mariaDB.getUsername(),
                    "spring.datasource.password=" + mariaDB.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
