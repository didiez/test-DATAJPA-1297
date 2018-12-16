package es.didiez.DATAJPA1297;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 * Repository of cities
 * @author Diego Diez
 */
public interface CityRepository extends JpaRepository<City, Long>{
    
    // Two different (equivalent) options to map a stored procedure to a repository method
    
    @Transactional
    @Procedure(procedureName = "DOSOMETHING", outputParameterName = "res")
    public String callDoSomethingNamedParameters(@Param("param1") Integer param1, @Param("param2") Integer param2);
    
    @Transactional
    @Procedure(procedureName = "DOSOMETHING", outputParameterName = "res")
    public String callDoSomethingPositionalParameters(Integer param1, Integer param2);
}
