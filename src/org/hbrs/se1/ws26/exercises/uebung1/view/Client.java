package org.hbrs.se1.ws26.exercises.uebung1.view;

import org.hbrs.se1.ws26.exercises.uebung1.control.Factory;
import org.hbrs.se1.ws26.exercises.uebung1.control.Translator;


public class Client {

	/**
	 * Methode zur Ausgabe einer Zahl auf der Console
	 * (auch bezeichnet als CLI, Terminal)
	 * Verwendung des Design Pattern: Factory (Fabrikmethode, GoF, Kapitel 6)
	 * Problem: Der Client darf kein Objekt der konkreten Klasse GermanTranslator
	 *          (oder einer anderen Klasse) mit "new" erzeugen und soll nur gegen
	 *          das Interface Translator programmiert sein. Ohne Objekt-Erzeugung
	 *          gibt es aber keinen Translator, dessen Methode man aufrufen koennte.
	 * Loesung: Die Objekt-Erzeugung liegt in der Klasse Factory. Der Client fragt
	 *          die Factory nach einem Translator und kennt nur den Interface-Typ.
	 *          Die Factory-Methode ist static, deshalb braucht auch die Factory
	 *          keinen Aufruf von "new" im Client.
	 *
	 */
	void display( int aNumber ){
		// In dieser Methode soll die Methode translateNumber
		// mit dem uebergebenen Wert der Variable aNumber
		// aufgerufen werden.
		//
		// Strenge Implementierung (nur) gegen das Interface Translator gewuenscht!
		Translator translator = Factory.createTranslator();
		String result = translator.translateNumber(aNumber);
		System.out.println("Das Ergebnis der Berechnung: " + result);
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.display(45);
	}
}
