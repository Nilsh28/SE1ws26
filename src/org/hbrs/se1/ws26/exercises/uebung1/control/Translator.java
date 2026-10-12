package org.hbrs.se1.ws26.exercises.uebung1.control;

/**
 *Das Translator Interface. Die Anzahl der Methoden ist fix
 *und darf NICHT erweitert werden. Sichtbarkeiten koennen
 *unter Umstaenden angepasst werden.
 *Anpassung gegenueber der Vorlage: Das Interface ist jetzt "public".
 *Ohne "public" waere es nur innerhalb des Packages "control" sichtbar
 *und die Klasse Client (Package "view") wuerde nicht kompilieren
 *("... is not public in ...; cannot be accessed from outside package").
 * @author saschaalda
 */

public interface Translator {
	double version = 1.0; // Version des Interface (implizit public static final)

	/**
	 * Uebersetzt eine numerische Zahl in eine String-basierte
	 * Repraesentation gemaess der Spezifikation in der Aufgabe 1-2
	 * (Methoden in Interfaces sind implizit public abstract)
	 */
	String translateNumber(int number);

}
