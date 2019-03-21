1. Id, int, object. \src\main\java\de\cocktail\model\Ingredient.java
Что мапить?
	@Column(name = "fk_ingredient_photo_id")
	private Photo ingredientPhoto;

2. one to many, one to ona, many to one

	@OneToMany(cascade = CascadeType.ALL, targetEntity = User.class)
	private User author;

3. Кирилица в Hibernate, Spring, Flyway
