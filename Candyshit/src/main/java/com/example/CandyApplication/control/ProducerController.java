package com.example.CandyApplication.control;

import com.example.CandyApplication.model.Producer;
import com.example.CandyApplication.utility.RandomStrings;

/**
 * Der {@code ProducerController} bietet statische Methoden zur Erstellung und Bearbeitung von {@link Producer}-Objekten. Primär wird diese Klasse zur
 * Instanziierung einer zufälligen Anzahl an {@code Producer}-Objekten und dem vollständigen Stoppen dieser verwendet.
 *
 * @author Lasse-Leander Hillen
 * @version 1.0.0
 * @since 15.04.2024
 */
public class ProducerController
{
	private static Producer[] ACTIVE_PRODUCERS = null;


	/**
	 * Privater Konstruktor, da die Klasse nicht instanziiert werden soll.
	 */
	private ProducerController ()
	{
	}


	/**
	 * Gibt eine formatierte Darstellung der Klasse als String wieder.
	 *
	 * @return Eine Zeichenfolge, die die Klasse repräsentiert.
	 */
	@Override
	public String toString ()
	{
		return super.toString();
	}


	/**
	 * Instanziiert eine zufällige Anzahl an Producer-Objekten innerhalb der angegebenen Limits und gibt dieses als Array zurück.
	 *
	 * @param minAmount Die Mindestanzahl der erzeugten Runnable-Objekte.
	 * @param maxAmount Die Maximalanzahl der erzeugten Runnable-Objekte.
	 *
	 * @return Die erzeugten Runnable-Objekte in einem Array.
	 */
	public static Producer[] createRandomProducerAmount (int minAmount, int maxAmount)
	{
		// Erstellung eines zufälligen Integers innerhalb der Limits.
		final int producerAmount = (int) Math.floor(Math.random() * (maxAmount - minAmount + 1) + minAmount);
		Producer[] producers = new Producer[producerAmount];


		// Erstellung und Sammlung der benötigten Producer
		for (int i = 0; i < producerAmount; i++)
		{
			Producer newProducer = new Producer(CandyController.getCandyStack(), RandomStrings.generateRandomString(20));
			producers[i] = newProducer;
		}

		// Der erstellte Producer-Array der Größe producerAmount wird zurückgegeben.
		return producers;
	}


	public static Producer[] startRandomProducerAmount (int minAmount, int maxAmount)
	{
		// Erstellt eine zufällige Anzahl an Producer-Objekten und speichert sie in der jeweiligen Klassenvariable
		ACTIVE_PRODUCERS = ProducerController.createRandomProducerAmount(minAmount, maxAmount);

		for (Producer producer : ACTIVE_PRODUCERS)
		{
			(new Thread(producer)).start();
		}
		System.out.println(ACTIVE_PRODUCERS.length + " Producer-Threads wurden erstellt.");
		return ACTIVE_PRODUCERS;
	}


	public static void stopAllActiveProducers ()
	{
		for (Producer activeProducer : ACTIVE_PRODUCERS)
		{
			activeProducer.stopExecution();
		}
	}

}
