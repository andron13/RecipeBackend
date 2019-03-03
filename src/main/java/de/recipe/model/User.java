package de.recipe.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Entity
@Data
@Table
@EqualsAndHashCode
public class User implements Serializable {

    @Id
    @Column( name = "user_id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column( nullable = false )
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
