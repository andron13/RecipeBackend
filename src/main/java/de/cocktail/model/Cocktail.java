package de.cocktail.model;

import lombok.*;
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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "COCKTAIL")
public class Cocktail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ANNOUNCE")
    private String announce;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationDate;

    @Column(name = "PREP_TIME")
    private int prepTime;

    @Column(name = "COOKING_TIME")
    private int cookingTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PHOTO_ID")
    private Photo image;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "COCKTAIL_INGREDIENTS", joinColumns = {@JoinColumn(name = "COCKTAIL_ID")}, inverseJoinColumns = {@JoinColumn(name = "INGREDIENT_ID")})
    private List<Ingredient> ingredients;
}