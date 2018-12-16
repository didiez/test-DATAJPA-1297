package es.didiez.DATAJPA1297;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author Diego Diez
 */
@Data
@Entity
public class City implements Serializable {
    @Id
    @GeneratedValue
    Long id;
    String name;
}
