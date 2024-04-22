package com.example.CandyApplication.control;

import com.example.CandyApplication.model.Consumer;
import com.example.CandyApplication.utility.RandomStrings;

import java.util.ArrayList;
import java.util.List;

/**
 * Die ConsumerController-Klasse dient als Controller-Klasse für Objekte der Consumer-Klasse. Sie ist nach dem Model-View-Control Schema entworfen und
 * wird als Singleton umgesetzt. Sie ermöglicht das Sammeln und Verwalten von Consumer-Objekten und ermöglicht das Starten der Consumer-Runnables auf
 * neuen Threads.
 *
 * @author Lasse-Leander Hillen
 * @version 1.0.0
 * @since 22.04.2024
 */
public class ConsumerController
{
	/** Die Instanz der Singleton-Klasse. */
	public static ConsumerController instance = null;

	/** Eine Sammlung der aktiven Konsumenten. */
	private final List<Consumer> activeConsumers;

	//-------------------------------------------------------------------------


	/**
	 * Privater Konstruktor, da die Klasse nicht von außerhalb über den Konstruktor instanziiert werden soll.
	 */
	private ConsumerController ()
	{
		this.activeConsumers = new ArrayList<Consumer>();
	}


	/**
	 * Gibt die Singleton-Instanz der Klasse zurück oder erstellt diese, wenn dies noch nicht geschehen ist.
	 *
	 * @return Ein Objekt der ConsumerController-Klasse
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
	public Consumer startConsumer ()
	{
		Consumer nextConsumer = new Consumer(CandyController.getCandyStack(), RandomStrings.generateRandomString(20));
		this.activeConsumers.add(nextConsumer);
		(new Thread(nextConsumer)).start();
		return nextConsumer;
	}


	/**
	 * Stoppt den letzten Consumer, der gestartet wurde.
	 *
	 * @return Gibt true zurück, wenn der Consumer erfolgreich gestoppt wurde.
	 */
	public boolean stopLastConsumer ()
	{
		this.activeConsumers.getLast().stopExecution();
		this.activeConsumers.removeLast();
		return true;
	}


	/**
	 * Stoppt einen Consumer anhand einer eindeutigen ID.
	 *
	 * @param id Die Consumer-ID, die geprüft werden soll.
	 *
	 * @return Gibt true zurück, wenn der Consumer erfolgreich gestoppt wurde.
	 */
	public boolean stopConsumer (String id)
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
	 * Stoppt alle aktiven Consumer.
	 */
	public void stopAllActiveConsumers ()
	{
		for (Consumer activeConsumer : this.activeConsumers)
		{
			activeConsumer.stopExecution();
		}
	}


	/**
	 * Gibt die aktiven Consumer zurück.
	 *
	 * @return Die aktiven Consumer als Array.
	 */
	public List<Consumer> getActiveConsumers ()
	{
		return this.activeConsumers;
	}

}
