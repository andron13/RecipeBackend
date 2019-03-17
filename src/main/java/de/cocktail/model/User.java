package de.cocktail.model;

import java.io.Serializable;
import javax.persistence.*;

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
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "user_id")
    private Long id;

    private String name;

    public User() {
    }


}
