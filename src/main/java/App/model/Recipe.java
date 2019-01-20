package App.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String title;
    private String announce;
    @CreationTimestamp
    @Column( nullable = false, updatable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date publicationDate;
    @ManyToOne( targetEntity = Person.class )
    private Person author;
    @ElementCollection
    @LazyCollection( LazyCollectionOption.FALSE)
    private List <Ingredient> ingredients;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List <RecepiSteps> instructions;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List <Taxonomy> tags;
    @ManyToOne( targetEntity = Comments.class )
     private Comments comments;
    private int prepTimeMinute;
    private int cookingTime;
    @ManyToOne( targetEntity = Rate.class )
    private Rate rate;

,
}
