package com.example.CandyApplication.control;

import com.example.CandyApplication.model.Consumer;
import com.example.CandyApplication.utility.RandomStrings;

import java.util.List;

/**
 * Beschreibung
 *
 * @author llhil
 * @version 1.0.0
 * @since 15.04.2024
 */
public class ConsumerController
{
	/** Die Instanz der Singleton-Klasse. */
	public static ConsumerController instance = null;

	/** Eine Sammlung der aktiven Konsumenten. */
	private List<Consumer> activeConsumers = null;


	/**
	 * Privater Konstruktor, da die Klasse nicht von außerhalb über den Konstruktor instanziiert werden soll.
	 */
	private ConsumerController ()
	{
	}


	/**
	 * @return
	 */
	public static ConsumerController getInstance ()
	{
		if (instance == null)
		{
			instance = new ConsumerController();
		}
		return instance;
	}


	/**
	 * Führt eine neue Consumer-Runnable auf einem neuen Thread aus.
	 */
	public void startConsumerThread ()
	{
		Consumer nextConsumer = new Consumer(CandyController.getCandyStack(), RandomStrings.generateRandomString(20));
		this.activeConsumers.add(nextConsumer);
		(new Thread(nextConsumer)).start();
	}


	/**
	 *
	 * @return
	 */
	public boolean stopConsumerThread ()
	{
		this.activeConsumers.getFirst().stopExecution();
		this.activeConsumers.removeFirst();
	}

	/**
	 * @param id
	 *
	 * @return
	 */
	public boolean stopConsumerThread (String id)
	{
		for (int i = 0; i < this.activeConsumers.size(); i++)
		{
			Consumer current = this.activeConsumers.get(i);
			if (current.getID() == id)
			{
				this.activeConsumers.remove(i);
				current.stopExecution();
				return true;
			}
		}
		return false;
	}


	/**
	 * Gibt die aktiven Konsumenten zurück.
	 *
	 * @return Die aktiven Konsumenten als Array.
	 */
	public List<Consumer> getActiveConsumers ()
	{
		return this.activeConsumers;
	}


	public static void stopAllActiveConsumers ()
	{
		for (Consumer activeConsumer : this.activeConsumers)
		{
			activeConsumer.stopExecution();
		}
	}

}
