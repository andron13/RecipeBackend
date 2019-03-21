package de.cocktail.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Entity
@Data
@EqualsAndHashCode
@Table(name = "ingredient")
public class Ingredient implements Serializable {

	@Id
	@Column(name = "ingredient_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ingredient_title")
	private String title;

	@Column(name = "ingredient_description")
	private String description;

/*	// TODO
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Photo ingredientPhoto;*/

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_ingredient_photo_id"))
	private Photo ingredientPhoto;

	public Ingredient() {
	}
}