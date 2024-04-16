package com.example.CandyApplication.model;

import com.example.CandyApplication.MainController;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Beschreibung
 *
 * @author llhil
 * @version 1.0.0
 * @since 15.04.2024
 */
public class Consumer implements Runnable
{

	private final String ID;
	private final Stack<Candy> CANDY_STACK;
	private boolean executionStopped = false;


	/**
	 * Privater Konstruktor, da die Klasse nicht instanziiert werden soll.
	 */
	public Consumer (Stack<Candy> candyStack, String id)
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
		return String.format("Cons{%s}", this.ID);
	}

	/**
	 *
	 */
	@Override
	public void run ()
	{
		while (!executionStopped)
		{
			try
			{
				Candy consumedCandy = CANDY_STACK.pop();
				System.out.println(this + " konsumierte: " + consumedCandy);
				Thread.sleep(1000);
			}
			catch (EmptyStackException stackEx)
			{
				continue;
			}
			catch (InterruptedException e)
			{
				throw new RuntimeException(e);
			}
		}
	}


	/**
	 * @return
	 */
	public String getID ()
	{
		return ID;
	}


	/**
	 * @return
	 */
	public Stack<Candy> getCANDY_STACK ()
	{
		return CANDY_STACK;
	}

	/**
	 * Stoppt die Ausführung des Producers.
	 */
	public void stopExecution ()
	{
		this.executionStopped = true;
	}
}
