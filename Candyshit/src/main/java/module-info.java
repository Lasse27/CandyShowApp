module com.example.candyshit {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.CandyApplication to javafx.fxml;
	exports com.example.CandyApplication.control;
	exports com.example.CandyApplication.model;
	opens com.example.CandyApplication.control to javafx.fxml;
	exports com.example.CandyApplication;
}