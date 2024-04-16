package com.example.CandyApplication.model;

import java.util.Stack;

/**
 * Die Klasse {@code Producer} stellt einen Produzenten im Rahmen der Übungsaufgabe 1 dar. Er wird als Runnable implementiert und kann so auf einem
 * Thread ausgeführt werden. Objekte der Klasse produzieren nach Aufruf der {@link Producer#run()}-Methode Candy-Objekte und speichern diese auf dem
 * übergebenen Stack.
 *
 * @author Lasse-Leander Hillen
 * @version 1.1.0
 * @since 16.04.2024
 */
public class Producer implements Runnable
{
	private final String ID;
	private final Stack<Candy> CANDY_STACK;

	private boolean executionStopped = false;


	/**
	 * Konstruktor der {@code Producer}-Klasse - instanziiert ein neues Objekt mit den übergebenen Parametern.
	 *
	 * @param candyStack Der Stack, auf den die produzierten Candy-Objekte hinzugefügt werden.
	 * @param id         Die ID des Produzenten.
	 */
	public Producer (Stack<Candy> candyStack, String id)
	{
		this.ID = id;
		this.CANDY_STACK = candyStack;
	}


	/**
	 * Gibt eine formatierte Darstellung der Klasse als String wieder.
	 *
	 * @return Eine Zeichenfolge, die die Klasse repräsentiert.
	 */
	@Override
	public String toString ()
	{
		return String.format("Prod{%s}", this.ID);
	}


	/**
	 * Startet die Ausführung des Objekts, z.B. durch einen Thread. Nach Start werden von dem Producer-Objekt in einem 1000s Intervall Candy-Objekte
	 * generiert und auf den Stack abgelegt, der bei Instanziierung des Objekts übergeben wurde.
	 */
	@Override
	public void run ()
	{
		while (!executionStopped)
		{
			try
			{
				Candy producedCandy = new Candy(this.ID);
				CANDY_STACK.add(producedCandy);
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				throw new RuntimeException(e);
			}
		}
	}


	/**
	 * Stoppt die Ausführung des Producers.
	 */
	public void stopExecution ()
	{
		this.executionStopped = true;
	}

}
