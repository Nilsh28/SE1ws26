package se1.ws26.tests.uebung1;

import org.hbrs.se1.ws26.exercises.uebung1.control.Factory;
import org.hbrs.se1.ws26.exercises.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws26.exercises.uebung1.control.Translator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Blackbox-Test der Methode translateNumber (Klasse GermanTranslator).
 * Aequivalenzklassen (Parameter "zahl"):
 *   AK1 (negativ): zahl < 1          Repraesentant: -5   Grenzwert: 0
 *   AK2 (positiv): 1 <= zahl <= 19   Repraesentant: 5    Grenzwerte: 1, 19
 *   AK3 (positiv): 20 <= zahl <= 99  Repraesentant: 67   Grenzwerte: 20, 99
 *   AK4 (positiv): zahl = 100        Repraesentant: 100
 *   AK5 (negativ): zahl > 100        Repraesentant: 500  Grenzwert: 101
 * Zusaetzlich: Sonderfaelle der deutschen Sprache (16, 21, 30) und Extremwerte des Typs int.
 */
public class GermanTranslatorTest {

    private static String fehler(int zahl) {
        return "Übersetzung der Zahl " + zahl + " nicht möglich (" + Translator.version + ")";
    }

    // ---------- Negativtests (ungueltige Aequivalenzklassen) ----------

    @Test
    public void nullTest() {                       // Grenzwert 0
        GermanTranslator translator = new GermanTranslator();
        assertEquals(fehler(0), translator.translateNumber(0));
    }

    @Test
    public void minusFuenfTest() {                 // Repraesentant AK1
        GermanTranslator translator = new GermanTranslator();
        assertEquals(fehler(-5), translator.translateNumber(-5));
    }

    @Test
    public void einhunderteinsTest() {             // Grenzwert 101
        GermanTranslator translator = new GermanTranslator();
        assertEquals(fehler(101), translator.translateNumber(101));
    }

    @Test
    public void fuenfhundertTest() {               // Repraesentant AK5
        GermanTranslator translator = new GermanTranslator();
        assertEquals(fehler(500), translator.translateNumber(500));
    }

    @Test
    public void maxIntTest() {                     // Extremwert: kein Ueberlauf, keine Exception
        GermanTranslator translator = new GermanTranslator();
        assertEquals(fehler(Integer.MAX_VALUE), translator.translateNumber(Integer.MAX_VALUE));
    }

    @Test
    public void minIntTest() {                     // Extremwert
        GermanTranslator translator = new GermanTranslator();
        assertEquals(fehler(Integer.MIN_VALUE), translator.translateNumber(Integer.MIN_VALUE));
    }

    // ---------- Positivtests (gueltige Aequivalenzklassen) ----------

    @Test
    public void einsTest() {                       // Grenzwert 1
        GermanTranslator translator = new GermanTranslator();
        assertEquals("eins", translator.translateNumber(1));
    }

    @Test
    public void fuenfTest() {                      // Repraesentant AK2 (mit Umlaut)
        GermanTranslator translator = new GermanTranslator();
        assertEquals("fünf", translator.translateNumber(5));
    }

    @Test
    public void sechzehnTest() {                   // Sonderfall: "sechzehn" (nicht "sechszehn")
        GermanTranslator translator = new GermanTranslator();
        assertEquals("sechzehn", translator.translateNumber(16));
    }

    @Test
    public void neunzehnTest() {                   // Grenzwert 19
        GermanTranslator translator = new GermanTranslator();
        assertEquals("neunzehn", translator.translateNumber(19));
    }

    @Test
    public void zwanzigTest() {                    // Grenzwert 20
        GermanTranslator translator = new GermanTranslator();
        assertEquals("zwanzig", translator.translateNumber(20));
    }

    @Test
    public void einundzwanzigTest() {              // Sonderfall: "einundzwanzig" (nicht "einsundzwanzig")
        GermanTranslator translator = new GermanTranslator();
        assertEquals("einundzwanzig", translator.translateNumber(21));
    }

    @Test
    public void zweiundzwanzigTest() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("zweiundzwanzig", translator.translateNumber(22));
    }

    @Test
    public void dreissigTest() {                   // Sonderfall: "dreißig" mit ß
        GermanTranslator translator = new GermanTranslator();
        assertEquals("dreißig", translator.translateNumber(30));
    }

    @Test
    public void siebenundsechzigTest() {           // Repraesentant AK3
        GermanTranslator translator = new GermanTranslator();
        assertEquals("siebenundsechzig", translator.translateNumber(67));
    }

    @Test
    public void neunundneunzigTest() {             // Grenzwert 99
        GermanTranslator translator = new GermanTranslator();
        assertEquals("neunundneunzig", translator.translateNumber(99));
    }

    @Test
    public void einhundertTest() {                 // AK4 / Grenzwert 100
        GermanTranslator translator = new GermanTranslator();
        assertEquals("einhundert", translator.translateNumber(100));
    }

    // ---------- Datum ----------

    @Test
    public void datumTest() {
        // Das Datum setzt die Factory, daher wird das Objekt ueber die Factory erzeugt.
        // Cast noetig, weil getDate() nicht im Interface Translator steht.
        String vorher = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        GermanTranslator translator = (GermanTranslator) Factory.createTranslator();
        String nachher = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        assertNotNull(translator.getDate());
        // Heutiges Datum im Format dd.MM.yyyy (Sicherheit fuer den Fall eines Tageswechsels waehrend des Tests)
        assertTrue(translator.getDate().equals(vorher) || translator.getDate().equals(nachher),
                "Erwartet heutiges Datum, war aber: " + translator.getDate());
    }

    @Test
    public void datumNichtGesetztOhneFactoryTest() {
        // Ohne Factory wird kein Datum gesetzt: bestaetigt, dass die Factory das Datum setzt.
        GermanTranslator translator = new GermanTranslator();
        assertNull(translator.getDate());
    }

    @Test
    public void factoryLiefertTranslatorTest() {
        Translator translator = Factory.createTranslator();
        assertNotNull(translator);
        assertEquals("zwei", translator.translateNumber(2));
    }
}
