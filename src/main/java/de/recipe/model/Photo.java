package de.recipe.model;

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
public class Photo {

    @Id
    @Column( name = "photo_id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column( length = 5000, nullable = false )
    private String patch;

    public Photo() {
    }

    public Photo(String patch) {
        this.patch = patch;
    }
}
