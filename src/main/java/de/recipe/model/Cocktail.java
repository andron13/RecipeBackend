package de.recipe.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Entity
@EqualsAndHashCode
@Data
@Table( name = "COCKTAILS" )
public class Cocktail implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "COCKTAILS_ID" )
    private Long id;

    private String title;

    @Column( length = 3000 )
    private String announce;

    @CreationTimestamp
    @Column( nullable = false, updatable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date publicationDate;

    @OneToOne( cascade = CascadeType.ALL, targetEntity = User.class )
    @JoinColumn( name = "user_id" )
    private User author;

    @ManyToMany( cascade = CascadeType.ALL, targetEntity = Ingredient.class )
    @LazyCollection( LazyCollectionOption.FALSE )
    private List <Ingredient> ingredients;

    @Column
    private int prepTimeMinute;

    @Column
    private int cookingTime;

    @ManyToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )

    private List <Photo> image;



    public Cocktail() {
    }

    public Cocktail(String title, String announce, User author, List <Ingredient> ingredients, int prepTimeMinute, int cookingTime, List <Photo> image) {
        this.title = title;
        this.announce = announce;

        this.author = author;
        this.ingredients = ingredients;
        this.prepTimeMinute = prepTimeMinute;
        this.cookingTime = cookingTime;
        this.image = image;
    }
}
