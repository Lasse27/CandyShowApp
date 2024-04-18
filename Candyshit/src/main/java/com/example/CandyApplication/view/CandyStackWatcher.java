package com.example.CandyApplication.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

import java.util.concurrent.Callable;

/**
 *
 */
public class CandyStackWatcher implements Runnable
{
	/**  */
	private final LineChart<Object, Object> chart;

	/**  */
	private final XYChart.Series<Object, Object> series;
	private final Callable<Integer> valueToObserve;

	/** Die Anzahl der aktuell angezeigten Punkte im Graphen. */
	private int pointCount = 0;

	/** Die TimeLine, in der die Anzeige aktualisiert wird. */
	private Timeline chartTimeline;


	/**
	 * @param chart
	 */
	public CandyStackWatcher (LineChart<Object, Object> chart, Callable<Integer> valueToObserve)
	{
		this.chart = chart;
		this.series = new XYChart.Series<>();
		this.chart.getData().addFirst(this.series);
		this.valueToObserve = valueToObserve;
	}


	/**
	 *
	 */
	public static void resetValues ()
	{
		//		pointCount = 0;
		//		series = new XYChart.Series<>();
		//		if (chartTimeline != null)
		//		{
		//			chartTimeline.stop();
		//
		//		}
	}


	/**
	 *
	 */
	@Override
	public void run ()
	{
		// Erstellung eines Events für das Hinzufügen eines Wertes in den Graphen.
		EventHandler<ActionEvent> chartUpdater = event ->
		{
			// add a new point to the chart
			try
			{
				series.getData().add(new XYChart.Data<>(String.valueOf(pointCount++), valueToObserve.call()));
			}
			catch (Exception e)
			{
				throw new RuntimeException(e);
			}
			if (pointCount > 20)
			{
				series.getData().removeFirst();
			}
		};

		// Das Event wird einmal jede Sekunde gestartet.
		Timeline chartUpdate = new Timeline(new KeyFrame(Duration.seconds(1), chartUpdater));
		chartUpdate.setCycleCount(Timeline.INDEFINITE);
		chartUpdate.play();
		chartTimeline = chartUpdate;
	}

}
