package com.example.CandyApplication.utility;

/**
 * Beschreibung
 *
 * @author llhil
 * @version 1.0.0
 * @since 16.04.2024
 */
public class RandomStrings
{
	// public enum GENERATOR_OPTIONS {}

	private static final char[] VALID_LETTERS =
			{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	private static final char[] VALID_NUMBERS = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

	private static final char[] VALID_SYMBOLS = {'#', '-'};


	/**
	 * Privater Konstruktor, da die Klasse nicht instanziiert werden soll.
	 */
	private RandomStrings ()
	{
	}


	/**
	 * @param length
	 *
	 * @return
	 */
	public static String generateRandomString (int length)
	{
		StringBuilder generatedString = new StringBuilder();
		for (int i = 1; i < length; i++)
		{
			// Einsetzen des Separator-Symbols.
			if (generatedString.length() == 5)
			{
				generatedString.append(getRandomSymbol());
			}

			// Ermittlung der restlichen Teile des Zufalls-Strings.
			if (Math.floor(Math.random() * 2) == 0)
			{
				generatedString.append(getRandomLetter());
				continue;
			}
			generatedString.append(getRandomNumber());
		}

		// Rückgabe des erstellten Strings der übergebenen Länge.
		return generatedString.toString();
	}


	/**
	 * @return
	 */
	private static char getRandomSymbol ()
	{
		return VALID_SYMBOLS[(int) Math.floor(Math.random() * (VALID_SYMBOLS.length - 1) + 1)];
	}


	/**
	 * @return
	 */
	private static char getRandomNumber ()
	{
		return VALID_NUMBERS[(int) Math.floor(Math.random() * (VALID_NUMBERS.length - 1) + 1)];
	}


	/**
	 * @return
	 */
	private static char getRandomLetter ()
	{
		return VALID_LETTERS[(int) Math.floor(Math.random() * (VALID_LETTERS.length - 1) + 1)];
	}

}