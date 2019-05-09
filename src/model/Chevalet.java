package model;

import java.util.Arrays;

import exceptions.EmptyException;
import exceptions.FaillureException;
import exceptions.TooLongArgumentException;

/**
 * @overview Un Chevalet est une sequence ordonnee de 8 places pouvant acceuillir ou non une Lettre
 * Un Chevalet peut contenir des doublons
 * Seules 7 places peuvent etre occupees simultanement par une lettre
 * Un Chevalet est mutable
 * Un exemple de Chevalet: {E, M, I, L, null, L, E, U}
 * @specfield placesUtilisees: int // le nombre de places occupees dans le chevalet
 * @derivedfield placesLibres: int // le nombre de places disponibles dans le chevalet
 * @invariant 0 <= placesUtilisees <= 7, 0 <= placesLibres <= 7, 0 <= placesUtilisees + placesLibres <= 7
 */
public class Chevalet {
	
	private final int NB_PLACES = 8;
	private final int NB_LETTRES_MAX = 7;
	private Lettre[] lettres;
	private int nbLettres;
	
	// FA(c) = {c[i] | 0 <= i <= 7}
	
	// IR(c):
	// NB_LETTRES_MAX = 7 &&
	// c.lettres != null &&
	// c.lettres.lenght = NB_PLACES = 8 &&
	// tout element de c.lettres est une Lettre || null &&
	// le nombre d'element non null de c.lettres = c.nbLettres
	
	
	// Constructeurs
	/**
	 * @effects initialise this en un Chevalet vide
	 */
	public Chevalet() {
		lettres = new Lettre[NB_PLACES];
		nbLettres = 0;
		if (!repOk()) {
			throw new FaillureException("Chevalet.Chevalet()");
		}
	}
	
	/**
	 * @throws NullPointerException si ltrs == null ou si un element de ltrs == null
	 * @throws EmptyException si ltrs est vide
	 * @throws TooLongArgumentException si ltrs.length > placesLibres
	 * @effects initialise this en un Chevalet contenant les Lettres lettres
	 */
	public Chevalet(Lettre[] ltrs) throws NullPointerException, EmptyException, TooLongArgumentException {
		this();
		insert(ltrs);
		if (!repOk()) {
			throw new FaillureException("Chevalet.Chevalet(Lettre[])");
		}
	}
	
	// Methodes
	/**
	 * @modifies this
	 * @throws NullPointerException si ltrs == null ou si un element de ltrs == null
	 * @throws EmptyException si ltrs est vide
	 * @throws TooLongArgumentException si ltrs.length > placesLibres
	 * @effects this_post = this + ltrs. Place les Lettres dans les premiers emplacements vides
	 */
	public void insert(Lettre[] ltrs) throws NullPointerException, EmptyException, TooLongArgumentException {
		if (ltrs == null) {
			throw new NullPointerException("Chevalet.insert(Lettre[]");
		}
		if (ltrs.length == 0) {
			throw new EmptyException("Chevalet.insert(Lettre[]");
		}
		if (ltrs.length > getPlacesLibres()) {
			throw new TooLongArgumentException("Chevalet.insert(Lettre[]");
		}
		
		Lettre[] newLettres = new Lettre[NB_PLACES];
		newLettres = lettres.clone();
		int i = 0;
		for (Lettre lettre : ltrs) {
			if (lettre == null) {
				throw new NullPointerException("Chevalet.insert(Lettre[]");
			}
			
			while (lettres[i] != null) {
				newLettres[i] = lettres[i];
				i++;
			}
			
			newLettres[i] = lettre;
			i++;
		}
		
		lettres = newLettres;
		nbLettres += ltrs.length;
		
		if (!repOk()) {
			throw new FaillureException("Chevalet.insert(Lettre[])");
		}
	}
	
	private void removeOne(Lettre ltr) {
		int i = 0;
		while (lettres[i] == null || !lettres[i].equals(ltr)) {
			i++;
		}
		lettres[i] = null;
		if (!repOk()) {
			throw new FaillureException("Chevalet.removeOne(Lettre)");
		}
	}
	
	/**
	 * @modifies this
	 * @throws NullPointerException si ltrs == null ou si un element de ltrs == null
	 * @throws EmptyException si ltrs est vide
	 * @throws TooLongArgument si ltrs.length > placesUtilisees
	 * @effects this_post = this - ltrs
	 */
	public void remove(Lettre[] ltrs) throws NullPointerException, EmptyException, TooLongArgumentException {
		if (ltrs == null) {
			throw new NullPointerException("Chevalet.remove(Lettre[]");
		}
		if (ltrs.length == 0) {
			throw new EmptyException("Chevalet.remove(Lettre[]");
		}
		if (ltrs.length > getPlacesUtilisees()) {
			throw new TooLongArgumentException("Chevalet.remove(Lettre[]");
		}
		
		Lettre[] newLettres = new Lettre[NB_PLACES];
		newLettres = lettres.clone();
		
		for (Lettre lettre : ltrs) {
			if (lettre == null) {
				lettres = newLettres;
				throw new NullPointerException("Chevalet.remove(Lettre[]");
			}
			removeOne(lettre);
		}
		
		nbLettres -= ltrs.length;
		if (!repOk()) {
			throw new FaillureException("Chevalet.remove(Lettre[])");
		}
	}
	
	/**
	 * @modifies this
	 * @throws ArrayIndexOutOfBoundsException si from < 0 ou from > 7 ou to < 0 ou to > 7
	 * @effects deplace la Lettre a l'index from vers l'index to et "pousse" les autres Lettres vers la droite si possible, vers la gauche sinon
	 * Par pousse on entend deplace d'une position jusqu'a une place vide
	 */
	public void moveFromTo(int from, int to) throws ArrayIndexOutOfBoundsException {
		
		if (!repOk()) {
			throw new FaillureException("Chevalet.moveFromTo(int, int)");
		}
	}
	
	/**
	 * @return placesUtilisees
	 */
	private int getPlacesUtilisees() {
		return nbLettres;
	}
	
	/**
	 * @return placesLibres
	 */
	public int getPlacesLibres() {
		return NB_LETTRES_MAX - nbLettres;
	}
	
	/**
	 * @requires 0 <= index <= 7
	 * @throws NullPointerException s'il n'y a pas de Lettre a l'index index
	 * @return this[index]
	 */
	public Lettre getLettreAt(int index) throws NullPointerException {
		Lettre lettre;
		try {
		lettre = lettres[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new FaillureException("chevalet.getLettreAt(int)");
		}
		
		if (lettre == null) {
			throw new NullPointerException("chevalet.getLettreAt(int)");
		}
		
		return lettres[index];
	}
	
	/**
	 * @requires 0 <= index <= 7
	 * @modifies this
	 * @throws NullPointerException s'il n'y a pas de Lettre a l'index index
	 * @effect this_post[index] = null, this.placesUtilisees -= 1
	 * @return this[index]
	 */
	public Lettre getAndRemoveIndex(int index) throws NullPointerException {		
		Lettre lettre;
		try {
			lettre = getLettreAt(index);
		} catch (NullPointerException e) {
			throw new NullPointerException("chevalet.getAndRemove(int)");
		}
		
		lettres[index] = null;
		nbLettres -= 1;
		if (!repOk()) {
			throw new FaillureException("Chevalet.getAndRemoveIndex(int)");
		}
		return lettre;
	}
	
	public boolean repOk() {
		int countNull = 0;
		if (lettres == null) return false;
		if (lettres.length != NB_PLACES) return false;
		for (Lettre lettre : lettres) {
			if (lettre == null) {
				countNull++;
			}
		}
		if (countNull != NB_PLACES - nbLettres) return false;
		return true;
	}
	
	// Methodes d'Object
	@Override
	public String toString() {
		return "Chevalet: " + Arrays.toString(lettres);
	}
	
}
