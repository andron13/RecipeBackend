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
@EqualsAndHashCode
@Table
public class Ingredient implements Serializable {
    @Id
    @Column( name = "ingredient_id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( length = 3000, nullable = false )
    private String name;

    @Column( length = 3000, nullable = false )
    private String description;

    public Ingredient() {
    }

    public Ingredient(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
