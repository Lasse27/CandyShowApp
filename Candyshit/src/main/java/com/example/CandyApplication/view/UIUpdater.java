package com.example.CandyApplication.view;

import com.example.CandyApplication.control.CandyController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

public class UIUpdater implements Runnable
{
	private static volatile int producedCandys = 0;
	private static volatile int consumedCandys = 0;
	private static volatile int candyAmount = 0;

	private static int TIMER = 0;
	private static LineChart<Object, Object> PRODUCER_CHART;
	private static LineChart<Object, Object> CONSUMER_CHART;
	private static LineChart<Object, Object> CANDY_CHART;
	private static Timeline CANDY_CHART_TIMELINE;
	private static int CANDY_POINTS = 0;
	private static XYChart.Series<Object, Object> CANDY_VALUES;


	public UIUpdater (LineChart<Object, Object> producerChart, LineChart<Object, Object> consumerChart, LineChart<Object, Object> candyChart)
	{
		PRODUCER_CHART = producerChart;
		CONSUMER_CHART = consumerChart;
		CANDY_CHART = candyChart;
	}


	/**
	 *
	 */
	@Override
	public void run ()
	{
		TIMER++;
		// Aktualisierung des Produzenten-Graphen

		// Aktualisierung des Konsumenten-Graphen

		// Aktualisierung des Füllstand-Graphen
		updateCandyChart();
	}


	private static void updateProducerChart ()
	{

	}


	private static void updateCandyChart ()
	{
		CANDY_VALUES = new XYChart.Series<>();
		CANDY_CHART.getData().addFirst(CANDY_VALUES);

		EventHandler<ActionEvent> chartUpdater = event ->
		{
			// add a new point to the chart
			CANDY_VALUES.getData().add(new XYChart.Data<>(String.valueOf(CANDY_POINTS++), CandyController.getCandyStack().size()));
			if (CANDY_POINTS > 20)
			{
				CANDY_VALUES.getData().removeFirst();
			}
		};
		Timeline chartUpdate = new Timeline(new KeyFrame(Duration.seconds(1), chartUpdater));
		chartUpdate.setCycleCount(Timeline.INDEFINITE);
		chartUpdate.play();
		CANDY_CHART_TIMELINE = chartUpdate;
	}


	public static void resetValues ()
	{
		CANDY_POINTS = 0;
		CANDY_VALUES = new XYChart.Series<>();
		if (CANDY_CHART_TIMELINE != null)
		{
			CANDY_CHART_TIMELINE.stop();

		}
	}

}
