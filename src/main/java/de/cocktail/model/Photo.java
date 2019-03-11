package de.cocktail.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;


@Entity
@Data
@Table
@EqualsAndHashCode
public class Photo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String patch;

    public Photo() {
    }

}
