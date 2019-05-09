package model;

import exceptions.EmptyException;
import exceptions.SmallerThanOneException;

/**
 * @overview Un Sac est une collection de 102 Lettres dans la vesrion francaise
 * Un Sac est mutable
 * @specfield nbLettres: int // le nombre de lettres restant dans le sac
 * @invariant 0 <= nbLettres <= 102
 */
public class Sac {
	
	private LettreCount[] lettres;
	
	// FA(c) = 
	
	// IR(c):
	// c.lettres != null &&
	// c.lettres.length = 27 &&
	// pour tout int i, c.lettres[i].count <= 15 &&
	// 
	
	
	// Constructeurs
	/**
	 * @effects initialise this en un Sac contenant les Lettres de la version francaise
	 */
	public Sac() {
		lettres = new LettreCount[27];
		for (int i = 0; i < 27; i++) {
			lettres[i] = new LettreCount();
		}
		lettres[0].lettre = new Lettre('a', 1);
		lettres[0].count = 9;
		lettres[1].lettre = new Lettre('b', 3);
		lettres[1].count = 2;
		lettres[2].lettre = new Lettre('c', 3);
		lettres[2].count = 2;
		lettres[3].lettre = new Lettre('d', 2);
		lettres[3].count = 3;
		lettres[4].lettre = new Lettre('e', 1);
		lettres[4].count = 15;
		lettres[5].lettre = new Lettre('f', 4);
		lettres[5].count = 2;
		lettres[6].lettre = new Lettre('g', 2);
		lettres[6].count = 2;
		lettres[7].lettre = new Lettre('h', 4);
		lettres[7].count = 2;
		lettres[8].lettre = new Lettre('i', 1);
		lettres[8].count = 8;
		lettres[9].lettre = new Lettre('j', 8);
		lettres[9].count = 1;
		lettres[10].lettre = new Lettre('k', 10);
		lettres[10].count = 1;
		lettres[11].lettre = new Lettre('l', 1);
		lettres[11].count = 5;
		lettres[12].lettre = new Lettre('m', 2);
		lettres[12].count = 3;
		lettres[13].lettre = new Lettre('n', 1);
		lettres[13].count = 6;
		lettres[14].lettre = new Lettre('o', 1);
		lettres[14].count = 6;
		lettres[15].lettre = new Lettre('p', 3);
		lettres[15].count = 2;
		lettres[16].lettre = new Lettre('q', 8);
		lettres[16].count = 1;
		lettres[17].lettre = new Lettre('r', 1);
		lettres[17].count = 6;
		lettres[18].lettre = new Lettre('s', 1);
		lettres[18].count = 6;
		lettres[19].lettre = new Lettre('t', 1);
		lettres[19].count = 6;
		lettres[20].lettre = new Lettre('u', 1);
		lettres[20].count = 6;
		lettres[21].lettre = new Lettre('v', 4);
		lettres[21].count = 2;
		lettres[22].lettre = new Lettre('w', 10);
		lettres[22].count = 1;
		lettres[23].lettre = new Lettre('x', 10);
		lettres[23].count = 1;
		lettres[24].lettre = new Lettre('y', 10);
		lettres[24].count = 1;
		lettres[25].lettre = new Lettre('z', 10);
		lettres[25].count = 1;
		lettres[26].lettre = new Lettre(' ', 2);
		lettres[26].count = 2;
	}
	
	// Methodes
	/**
	 * @modifies this
	 * @throws EmptyException si this est vide
	 * @throws SmallerThanOneException si nb < 1
	 * @effects this_post = this - Lettres retournees et nbLettres_post = nbLettres - nb
	 * @return si nb <= nbLettres, nb Lettres aleatoires choisies dans les lettres restant dans le sac 
	 * Sinon les nbLettres restantes
	 */
	public Lettre[] getLettres(int nb) throws EmptyException, SmallerThanOneException {
		if (getNblettres() < 1) {
			throw new EmptyException("Sac.getLettres(int)");
		}
		if (nb < 1) {
			throw new SmallerThanOneException("Sac.getLettres(int)");
		}
		
		Lettre[] ltrs = new Lettre[nb];
		
		for (int i = 0; i < ltrs.length; i++) {
			ltrs[i] = lettres[i].lettre;
		}
		
		return ltrs;
	}
	
	/**
	 * @return nbLettres
	 */
	public int getNblettres() {
		int sum = 0;
		
		for (LettreCount lettre : lettres) {
			sum += lettre.count;
		}
		
		return sum;
	}
	
	// Methodes d'Object
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("Sac: {" + lettres[0].lettre.getCaractere() + ": " + lettres[0].count);
		
		for (int i = 1; i < lettres.length; i++) {
			s = s.append(", " + lettres[i].lettre.getCaractere() + ": " + lettres[i].count);
		}
		
		// ATTENTION AU toString A LA FIN
		return s.append("}").toString();
	}
	
	// Record
	private class LettreCount {
		public Lettre lettre;
		public int count;
	}
	

}
