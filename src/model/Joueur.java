package model;

import exceptions.FaillureException;
import exceptions.SmallerThanOneException;

/**
 * @overview Un Joueur rassemble les informations sur le joueur
 * Un Joueur est mutable
 * Un exemple de Joueur: {"Emil", 79 points}
 * @specfield nom: String // le nom du joueur
 * @specfield score: int // nombre de points qu'a le joueur
 * @invariant nom != null, points >= 0
 */
public class Joueur {
	
	private String nom;
	private int score;
	
	// FA(c) = 
	
	// IR(c):
	// c.nom != null &&
	// c.score >= 0
	
	
	// Constructeurs
	/**
	 * @throws NullPointerException si nom == null
	 * @effects initialise this.nom a nom et score a 0
	 */
	public Joueur(String nom) throws NullPointerException {
		this.nom = nom;
		this.score = 0;
		if (!repOk()) {
			throw new FaillureException("Joueur.Joueur()");
		}
	}
	
	
	// Methodes
	/**
	 * @modifies this
	 * @throws SmallerThanOneException si pts < 1
	 * @effects points_post = score + pts
	 */
	public void ajouterPoints(int pts) throws SmallerThanOneException {
		if (pts < 1) {
			throw new SmallerThanOneException("Joueur.ajouterPoints(int)");
		}
		
		score += pts;
		if (!repOk()) {
			throw new FaillureException("Joueur.ajouterPoints(int)");
		}
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean repOk() {
		if (nom == null) return false;
		if (score < 0) return false;
		return true;
	}
	
}
