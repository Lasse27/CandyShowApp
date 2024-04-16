package com.example.CandyApplication.model;

import com.example.CandyApplication.utility.RandomStrings;

/**
 * Beschreibung
 *
 * @author llhil
 * @version 1.0.0
 * @since 15.04.2024
 */
public class Candy
{
	private final int HELL;
	private final String HOPE;
	private final String PRODUCER_ID;

	/**
	 * Privater Konstruktor, da die Klasse nicht instanziiert werden soll.
	 */
	public Candy (String producerID)
	{
		this.HELL = -9999 + (int) (Math.random() * (9999 + 9999) + 1);
		this.HOPE = RandomStrings.generateRandomString(20);
		this.PRODUCER_ID = producerID;
	}


	/**
	 * @return
	 */
	@Override
	public String toString ()
	{
		return String.format("Candy{ hell={%-5d} | hope={%-20s} | prodID={%-20s} }", this.HELL, this.HOPE, this.PRODUCER_ID);
	}

}
