package model;

import java.util.Iterator;
import java.util.NoSuchElementException;

import exceptions.FaillureException;
import exceptions.IllegalCoordinateException;
import exceptions.TakenSpotException;

/**
 * @overview Un Plateau est une grille 15x15 emplacements pouvant acceuillir ou non une Lettre
 * Un Plateau est mutable
 */
public class Plateau {

	private Lettre[][] grille;
	
	// FA(c) = 
	
	// IR(c):
	// c.grille != null
	// c.grille.length = 15
	// pour tout int i c.grille[i].lenght = 15 | 0 <= i <= 14
	// pour tout int i, j c.grille[i, j] = (Lettre || null) | 0 <= i <= j <= 14
	
	
	// Constructeurs
	/**
	 * @effects initialise this en un Plateau vide
	 */
	public Plateau() {
		grille = new Lettre[15][15];
		if (!repOk()) {
			throw new FaillureException("Plateau.Plateau()");
		}
	}
	
	
	// Methodes
	/**
	 * @modifies this
	 * @throws NullPointerException si lettre == null
	 * @throws IllegalCoordinateException si les coordonnees (x, y) ne font pas partie de la grille
	 * @throws TakenSpotException si l'emplacement (x, y) contient deja une Lettre
	 * @effects place lettre dans la case (x, y) du Plateau
	 */
	public void placerLettre(int x, int y, Lettre lettre) throws NullPointerException, IllegalCoordinateException, TakenSpotException {
		if (lettre == null) {
			throw new NullPointerException("Plateau.placerLettre(int, int, lettre)");
		}
		
		try {
			if (grille[x][y] == null) {
				grille[x][y] = lettre;
			} else {
				throw new TakenSpotException("Plateau.placerLettre(int, int, lettre)");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalCoordinateException("Plateau.placerLettre(int, int, lettre)");
		}
		
		if (!repOk()) {
			throw new FaillureException("Plateau.placerLettre(int, int, Lettre)");
		}
	}
	
	/**
	 * @modifies this
	 * @throws NullPointerException s'il n'y a pas de Lettre en (x, y)
	 * @throws IllegalCoordinateException si les coordonnees (x, y) ne font pas partie de la grille
	 * @effects retire la Lettre en (x, y) du Plateau
	 * @return la lettre en (x, y)
	 */
	public Lettre prendreLettreAt(int x, int y) throws NullPointerException, IllegalCoordinateException {
		Lettre ltr;
		try {
			ltr = grille[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalCoordinateException("Plateau.prendreLettreAt(int, int)");
		}
		
		if (ltr == null) {
			throw new NullPointerException("Plateau.prendreLettreAt(int, int)");
		}
		
		grille[x][y] = null;
		
		if (!repOk()) {
			throw new FaillureException("Plateau.prendreLettreAt(int, int)");
		}
		return ltr;
	}
	
	public boolean repOk() {
		if (grille == null) return false;
		if (grille.length != 15) return false;
		for (int i = 0; i < grille.length; i++) {
			if (grille[i].length != 15) return false;
		}
		return true;
	}

	// Methodes d'Object
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(String.format("Plateau:%n"));
		
		for (int y = 0; y < grille.length; y++) {
			for (int x = 0; x < grille[y].length; x++) {
				if (grille[x][y] == null) {
					s = s.append("  |");
				} else {
					s = s.append(grille[x][y].getCaractere() + " |");
				}
			}
			s.append(String.format("%n"));
		}
		return s.toString();
	}
	
	// Iterateurs
	/**
	 * @return un generateur qui produira tous les elements utilises de this, chacun une fois, sans ordre particulier
	 * @requires this ne doit pas etre modifie pendant que le generateur est utilise
	 */
	public Iterator<LettrePos> placesUtilisees() {
		return new LettrePosGen();
	}
	
	private class LettrePosGen implements Iterator<LettrePos> {
		
		private int x, y;
		
		private LettrePosGen() {
			x = 0;
			y = 0;
		}

		@Override
		public boolean hasNext() {
			Lettre lettre;
			try {
				lettre = grille[x][y];
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
			while (lettre == null) {
				if (x < 14) {
					x++;
				} else {
					y++;
					x = 0;
				}
				try {
					lettre = grille[x][y];
				} catch (ArrayIndexOutOfBoundsException e) {
					return false;
				}
			}
			return true;
		}

		@Override
		public LettrePos next() throws NoSuchElementException {
			if (hasNext()) {
				LettrePos lettre = new LettrePos();
				lettre.lettre = grille[x][y];
				lettre.posX = x;
				lettre.posY = y;
				if (x < 14) {
					x++;
				} else {
					y++;
					x = 0;
				}
				return lettre;
			}
			throw new NoSuchElementException("LettrePosGen.next()");
		}
		
	}
	
}
