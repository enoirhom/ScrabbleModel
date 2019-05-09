package model;

import exceptions.NotALetterException;

/**
 * @overview Une Lettre est une lettre telle qu'utilisee au Scrabble
 * Une Lettre est immutable
 * Une Lettre typique est le E. Representee par (e, 1) dans la version francaise
 * Une Lettre est definie comme un duet <caractere, nbPoints> tel que:
 * @specfield caractere: char // la lettre reprise sur une Lettre. La lettre blanche est representee par l'espace
 * @specfield nbPoints: int // le nombre de points que vaut une lettre. Celui-ci depend de la langue du jeu
 * @invariant caractere est une lettre de l'alphabet minuscule ou un espace, nbPoints € {0, 1, 2, 3, 4, 8, 10}
 */
public class Lettre {
	
	private final char lettre;
	private final int points;
	
	// FA(c) = (c.lettre, c.point)
	
	// IR(c):
	// c.lettre € {alphabet minuscule} || {' '} &&
	// c.point € {0, 1, 2, 3, 4, 8, 10} (version francaise)
	
	
	// Constructeurs
	/**
	 * @throws NotALetterException si lettre n'est pas un caractere ou un espace
	 * @effects initialise this a la Lettre dont caractere = lettre
	 */
	public Lettre(char lettre, int pts) throws NotALetterException {
		if (!Character.isAlphabetic(lettre) && lettre != ' ') {
			throw new NotALetterException("Lettre.Lettre(char)");
		}
		
		this.lettre = lettre;
		this.points = pts;
	}
	
	// Methodes
	/**
	 * @return caractere
	 */
	public char getCaractere() {
		return lettre;
	}
	
	/**
	 * @return nbPoints
	 */
	public int getNbPoints() {
		return points;
	}
	
	// Methodes d'Object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lettre;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lettre other = (Lettre) obj;
		if (lettre != other.lettre)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lettre (" + lettre + ", " + points + ")";
	}
	
}
