package de.cocktail.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Transactional
@Entity
@EqualsAndHashCode
@Data
@Table
public class Cocktail implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "cocktail_id" )
    private Long id;
    @Column( name = "cocktail_title" )
    private String title;

    @Column( name = "cocktail_announce" )
    private String announce;

    @CreationTimestamp
    @Column( name = "cocktail_publication_date" )
    @Temporal( TemporalType.TIMESTAMP )
    private Date publicationDate;

    @ManyToOne( cascade = CascadeType.ALL, targetEntity = User.class )

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_user_id"),referencedColumnName = "user_id")
    private User user;

    @ManyToMany( cascade = CascadeType.ALL, targetEntity = Ingredient.class )
    @LazyCollection( LazyCollectionOption.FALSE )
    private List <Ingredient> ingredients;

    @Column( name = "cocktail_prep_time_minute" )
    private int prepTimeMinute;

    @Column( name = "cocktail_cooking_time" )
    private int cookingTime;

    @ManyToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )

    private List <Photo> image;


    public Cocktail() {
    }


}
