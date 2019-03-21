package de.cocktail.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Entity
@EqualsAndHashCode
@Data
@Table(name = "cocktail")
public class Cocktail implements Serializable {

	@Id
	@Column(name = "cocktail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cocktail_title")
	private String title;

	@Column(name = "cocktail_announce")
	private String announce;

	@Column(name = "cocktail_prep_time_minute")
	private int prepTimeMinute;

	@Column(name = "cocktail_cooking_time")
	private int cookingTime;

	//TODO
	//Вопрос с настройками публикации и с таймстэмп
	@Column(name = "cocktail_publication_date")
	private Date publicationDate;

	//TODO
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_user_id"))
	private User user;

	//TODO
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_photo_id"))
	private Photo photo;

	//TODO
//	@Column(name = "cocktail_ingredients")
	@ManyToMany(cascade = CascadeType.ALL, targetEntity = Ingredient.class)
	@JoinTable(
			name = "cocktail_ingredients",
			joinColumns = {@JoinColumn(name = "fk_cocktail_id")},
			inverseJoinColumns = {@JoinColumn(name = "fk_ingredient_id")}
	)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Ingredient> ingredients;

	public Cocktail() {
	}
}