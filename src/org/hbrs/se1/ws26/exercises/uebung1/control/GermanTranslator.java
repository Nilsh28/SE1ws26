package org.hbrs.se1.ws26.exercises.uebung1.control;

public class GermanTranslator implements Translator {

	/**
	 * Aktuell laut Spezifikation gueltiger Bereich: 1 bis 100.
	 * Der Algorithmus unten kann bereits alle int-Werte uebersetzen
	 * (bis Integer.MAX_VALUE). Fuer zukuenftige Zwecke genuegt es daher,
	 * diese Konstante zu aendern, z.B. auf Integer.MAX_VALUE.
	 */
	private static final int MIN_GUELTIG = 1;
	private static final int MAX_GUELTIG = 100;

	// Index = Zahl (0 bleibt leer, wird nie gebraucht)
	private static final String[] EINER = {"", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben",
			"acht", "neun", "zehn", "elf", "zwölf", "dreizehn", "vierzehn", "fünfzehn",
			"sechzehn", "siebzehn", "achtzehn", "neunzehn"};

	// Index = Zehnerziffer (0 und 1 bleiben leer, werden nie gebraucht)
	private static final String[] ZEHNER = {"", "", "zwanzig", "dreißig", "vierzig", "fünfzig", "sechzig",
			"siebzig", "achtzig", "neunzig"};

	private String date = null;

	/**
	 * Methode zur Uebersetzung einer Zahl in eine String-Repraesentation
	 */
	public String translateNumber(int number) {
		if (number < MIN_GUELTIG || number > MAX_GUELTIG) {
			return "Übersetzung der Zahl " + number + " nicht möglich (" + Translator.version + ")";
		}
		return inWorte(number);
	}

	/**
	 * Wandelt eine Zahl von 1 bis Integer.MAX_VALUE in Worte um.
	 * Gruppen unter einer Million werden zusammengeschrieben ("dreihundertzwanzigtausend"),
	 * Millionen und Milliarden werden als eigene Woerter mit Leerzeichen geschrieben.
	 */
	private String inWorte(int zahl) {
		int milliarden = zahl / 1_000_000_000;
		int millionen = (zahl / 1_000_000) % 1_000;
		int rest = zahl % 1_000_000; // 0 bis 999999

		StringBuilder sb = new StringBuilder();
		if (milliarden > 0) {
			sb.append(milliarden == 1 ? "eine Milliarde" : unterTausend(milliarden, true) + " Milliarden");
		}
		if (millionen > 0) {
			if (sb.length() > 0) sb.append(" ");
			sb.append(millionen == 1 ? "eine Million" : unterTausend(millionen, true) + " Millionen");
		}
		if (rest > 0) {
			if (sb.length() > 0) sb.append(" ");
			sb.append(unterMillion(rest));
		}
		return sb.toString();
	}

	/** 1 bis 999999, in einem Wort geschrieben */
	private String unterMillion(int zahl) {
		int tausender = zahl / 1000;
		int rest = zahl % 1000;
		StringBuilder sb = new StringBuilder();
		if (tausender > 0) {
			sb.append(unterTausend(tausender, true)).append("tausend");
		}
		if (rest > 0) {
			sb.append(unterTausend(rest, false));
		}
		return sb.toString();
	}

	/**
	 * 1 bis 999. Wenn "vorWort" true ist, steht die Zahl vor einem weiteren Wort
	 * (tausend, Million, ...): dann heisst es "ein" statt "eins".
	 */
	private String unterTausend(int zahl, boolean vorWort) {
		int hunderter = zahl / 100;
		int rest = zahl % 100;
		StringBuilder sb = new StringBuilder();
		if (hunderter > 0) {
			sb.append(hunderter == 1 ? "ein" : EINER[hunderter]).append("hundert");
		}
		if (rest > 0) {
			sb.append(unterHundert(rest, vorWort));
		}
		return sb.toString();
	}

	/** 1 bis 99 */
	private String unterHundert(int zahl, boolean vorWort) {
		if (zahl < 20) {
			return (zahl == 1 && vorWort) ? "ein" : EINER[zahl];
		}
		int zehner = zahl / 10;
		int einer = zahl % 10;
		if (einer == 0) {
			return ZEHNER[zehner];
		}
		// 21 = "einundzwanzig" (nicht "einsundzwanzig")
		String einerWort = (einer == 1) ? "ein" : EINER[einer];
		return einerWort + "und" + ZEHNER[zehner];
	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	void printInfo(){
		System.out.println( "GermanTranslator v" + Translator.version + ", erzeugt am " + this.date );
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: dd.MM.yyyy (Beispiel: "20.08.2026"))
	 * Das Datum sollte system-intern durch eine Factory-Klasse gesetzt werden und nicht von externen View-Klassen
	 * Technisch sollte einfach das "heutige" Datum gesetzt werden.
	 */
	public void setDate( String date ) {
		this.date = date;
	}

	/**
	 * Auslesen des gesetzten Datums
	 * @return
	 */
	public String getDate() {
		return date;
	}
}
