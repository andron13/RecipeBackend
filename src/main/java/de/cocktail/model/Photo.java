package de.cocktail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "photo")
@EqualsAndHashCode
public class Photo {

	@Id
	@Column(name = "photo_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "photo_path")
	private String patch;

	@Column(name = "photo_title")
	private String title;

	@Column(name = "photo_alt")
	private String alt;

	public Photo() {
	}
}