package com.example.CandyApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{
	public static void main (String[] args)
	{
		launch();
	}


	@Override
	public void start (Stage stage) throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
		stage.setTitle("Candy by Lasse-Leander Hillen");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

}