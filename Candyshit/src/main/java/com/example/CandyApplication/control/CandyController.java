package com.example.CandyApplication.control;

import com.example.CandyApplication.model.Candy;

import java.util.Stack;

/**
 * Beschreibung
 *
 * @author llhil
 * @version 1.0.0
 * @since 15.04.2024
 */
public class CandyController
{

	private static Stack<Candy> CANDY_STACK = null;


	/**
	 * Privater Konstruktor, da die Klasse nicht instanziiert werden soll.
	 */
	private CandyController ()
	{
	}


	/**
	 * @return
	 */
	public static Stack<Candy> getCandyStack ()
	{
		if (CANDY_STACK == null)
		{
			CANDY_STACK = new Stack<>();
		}
		return CANDY_STACK;
	}

}
