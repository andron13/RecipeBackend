package de.exeption;

public class NotFoundCocktailById extends RuntimeException {

	public NotFoundCocktailById(String e) {
		super(e);
	}
}