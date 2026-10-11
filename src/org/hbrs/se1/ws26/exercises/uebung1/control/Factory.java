package org.hbrs.se1.ws26.exercises.uebung1.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Fabrik-Klasse (Entwurfsmuster: Factory bzw. Fabrikmethode / Simple Factory).
 *
 * Sie liegt im Package "control", also bei Translator und GermanTranslator,
 * damit die Client-Klasse (Package "view") die konkrete Klasse GermanTranslator
 * nie kennen muss. Die Fabrikmethode ist static, damit der Client auch die
 * Factory selbst nicht mit "new" erzeugen muss.
 */
public class Factory {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    // Keine Instanzen noetig: nur die statische Fabrikmethode wird genutzt.
    private Factory() {
    }

    /**
     * Erzeugt einen Translator, setzt das heutige Datum (Format dd.MM.yyyy)
     * und gibt ihn nur als Interface-Typ nach aussen.
     */
    public static Translator createTranslator() {
        GermanTranslator translator = new GermanTranslator();
        translator.setDate(LocalDate.now().format(DATE_FORMAT));
        return translator;
    }

}
