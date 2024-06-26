package com.example.CandyApplication;

import com.example.CandyApplication.control.CandyController;
import com.example.CandyApplication.control.ConsumerController;
import com.example.CandyApplication.control.ProducerController;
import com.example.CandyApplication.view.CandyStackWatcher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class MainController
{
	@FXML
	private LineChart<Object, Object> candyChart;

	@FXML
	private LineChart<Object, Object> consumerChart;

	@FXML
	private TextArea logWindow1;

	@FXML
	private TextArea logWindow2;

	@FXML
	private Accordion mainAccordion;

	@FXML
	private BorderPane mainBorderPane;

	@FXML
	private TitledPane overviewPage;

	/** Der Button, der für das Starten der Threads im Async-Modus zuständig ist. */
	@FXML
	private Button startAsyncButton;

	/** Der Button, der für das Starten der Threads im Sync-Modus zuständig ist. */
	@FXML
	private Button startSyncButton;

	/** Der Button, der für das Stoppen aller laufenden Threads zuständig ist. */
	@FXML
	private Button stopThreadsButton;


	/**
	 * Behandelt die Aktion, wenn der {@code startAsyncButton} gedrückt wird.
	 *
	 * @param event Das Event, das durch Drücken des Buttons ausgelöst wird.
	 */
	@FXML
	void startAsyncButtonPressed (ActionEvent event)
	{
		// TODO: RESET vor Neustart
		if (this.candyChart != null)
			this.candyChart.getData().clear();
		CandyStackWatcher.resetValues();

		// Buttons werden disabled, damit der Nutzer erkennt, dass der laufende Prozess begonnen hat.
		startAsyncButton.setDisable(true);
		startSyncButton.setDisable(true);
		stopThreadsButton.setDisable(false);


		// Start des UI Threads
		Platform.runLater(new CandyStackWatcher(candyChart, () -> CandyController.getCandyStack().size()));

		// Instanziierung und Starten der Produzenten-Threads
		ProducerController.startRandomProducerAmount(10,10);

		// Instanziierung und Starten der Konsumenten-Threads
		startConsumers(10);


	}


	private static void startConsumers(int count)
	{
		for (int i = 0; i < count; i++)
		{
			ConsumerController.getInstance().startConsumer();
		}
	}



	@FXML
	void startSyncButtonPressed (ActionEvent event)
	{

	}


	@FXML
	void stopThreadsButtonPressed (ActionEvent event)
	{
		// Buttons werden enabled, damit der Nutzer erkennt, dass der laufende Prozess beendet wurde.
		startAsyncButton.setDisable(false);
		startSyncButton.setDisable(false);
		stopThreadsButton.setDisable(true);

		// Stoppen aller Produzenten-Threads
		ProducerController.stopAllActiveProducers();

		// Stoppen aller Konsumenten-Threads
		ConsumerController.getInstance().stopAllActiveConsumers();
	}

}
