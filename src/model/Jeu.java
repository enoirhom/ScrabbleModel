package model;

import exceptions.EmptyException;
import exceptions.SmallerThanOneException;
import exceptions.TakenSpotException;
import exceptions.TooLongArgumentException;


public class Jeu {
	
	public int tour;
	public int nbJoueur;
	public Joueur[] joueurs;
	public Chevalet[] chevalets;
	public Lettre pickedLettre;
	public Plateau plateau;
	public Sac sac;
	
	public Jeu(int nb) {
		tour = 0;
		nbJoueur = nb;
		joueurs = new Joueur[nbJoueur];
		chevalets = new Chevalet[nbJoueur];
		pickedLettre = null;
		plateau = new Plateau();
		sac = new Sac();
		
		for (int i = 0; i < nbJoueur; i++) {
			joueurs[i] = new Joueur("Joueur" + (i + 1));
		}
		
		for (int i = 0; i < nbJoueur; i++) {
			chevalets[i] = new Chevalet();
			try {
				chevalets[i].insert(sac.getLettres(chevalets[i].getPlacesLibres()));
			} catch (NullPointerException | EmptyException | SmallerThanOneException | TooLongArgumentException e) {
				System.out.println(e);
			}
		}
		
	}
	
	public void tourSuivant() {
		try {
			chevalets[tour % nbJoueur].insert(sac.getLettres(chevalets[tour % nbJoueur].getPlacesLibres()));
		} catch (NullPointerException | EmptyException | SmallerThanOneException | TooLongArgumentException e) {
			System.out.println(e);
		}
		tour++;
	}
	
	public void prendreLettreChevalet(int joueur, int lettreIndex) {
		pickedLettre = chevalets[joueur].getAndRemoveIndex(lettreIndex);
	}
	
	public void prendreLettrePlateau(int lettreX, int lettreY) {
		pickedLettre = plateau.prendreLettreAt(lettreX, lettreY);
	}
	
	public void placerLettrePlateau(int caseX, int caseY) throws TakenSpotException {
		try {
			plateau.placerLettre(caseX, caseY, pickedLettre);
		} catch (TakenSpotException e) {
			throw new TakenSpotException("Jeu.placerLettrePlateau(int, int)");
		}
		pickedLettre = null;
	}

	
	// Methode d'Object
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("Jeu:");
		s.append(String.format("%n"));
		
		for (int i = 0; i < nbJoueur; i++) {
			s.append(joueurs[i].getNom() + " ");
			s.append(String.format("%n"));
			s.append(chevalets[i].toString());
			s.append(String.format("%n"));
		}
		
		s.append(String.format("%n"));
		s.append(plateau.toString());
		
		
		return s.toString();
	}
	
}
