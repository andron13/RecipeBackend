package de.cocktail.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;

@Transactional
@Entity
@Data
@EqualsAndHashCode
@Table
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

@Column(name = "TITLE")
    private String title;


    private String description;

    public Ingredient() {
    }

}
