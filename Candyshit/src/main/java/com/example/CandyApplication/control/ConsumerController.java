package com.example.CandyApplication.control;

import com.example.CandyApplication.model.Consumer;
import com.example.CandyApplication.utility.RandomStrings;

/**
 * Beschreibung
 *
 * @author llhil
 * @version 1.0.0
 * @since 15.04.2024
 */
public class ConsumerController
{
	private static Consumer[] ACTIVE_CONSUMERS = null;


	/**
	 * Privater Konstruktor, da die Klasse nicht instanziiert werden soll.
	 */
	private ConsumerController ()
	{
	}


	/**
	 * Instanziiert eine zufällige Anzahl an Producer-Objekten innerhalb der angegebenen Limits und gibt dieses als Array zurück.
	 *
	 * @param minAmount Die Mindestanzahl der erzeugten Runnable-Objekte.
	 * @param maxAmount Die Maximalanzahl der erzeugten Runnable-Objekte.
	 *
	 * @return Die erzeugten Runnable-Objekte in einem Array.
	 */
	public static Consumer[] createRandomConsumerAmount (int minAmount, int maxAmount)
	{
		// Erstellung eines zufälligen Integers innerhalb der Limits.
		final int consumerAmount = (int) Math.floor(Math.random() * (maxAmount - minAmount + 1) + minAmount);
		Consumer[] consumers = new Consumer[consumerAmount];


		// Erstellung und Sammlung der benötigten Consumer
		for (int i = 0; i < consumerAmount; i++)
		{
			consumers[i] = new Consumer(CandyController.getCandyStack(), RandomStrings.generateRandomString(20));
		}

		// Der erstellte Consumer-Array der Größe producerAmount wird zurückgegeben.
		return consumers;
	}


	/**
	 * @param minAmount
	 * @param maxAmount
	 *
	 * @return
	 */
	public static Consumer[] startRandomConsumerAmount (int minAmount, int maxAmount)
	{
		// Erstellt eine zufällige Anzahl an Producer-Objekten und speichert sie in der jeweiligen Klassenvariable
		ACTIVE_CONSUMERS = ConsumerController.createRandomConsumerAmount(minAmount, maxAmount);

		for (Consumer consumer : ACTIVE_CONSUMERS)
		{
			(new Thread(consumer)).start();
		}
		System.out.println(ACTIVE_CONSUMERS.length + " Consumer-Threads wurden erstellt.");
		return ACTIVE_CONSUMERS;
	}


	/**
	 * Gibt die aktiven Konsumenten zurück.
	 *
	 * @return Die aktiven Konsumenten als Array.
	 */
	public static Consumer[] getActiveConsumers ()
	{
		return ACTIVE_CONSUMERS;
	}


	public static void stopAllActiveConsumers ()
	{
		for (Consumer activeConsumer : ACTIVE_CONSUMERS)
		{
			activeConsumer.stopExecution();
		}
	}

}
