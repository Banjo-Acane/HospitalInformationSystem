package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		UserMaster.readData();
		RegistrationMaster.readData();
		MedicineMaster.readData();
		PrescriptionMaster.readModelData();
		PrescriptionMaster.readData();
		DepartmentMaster.readData();
		
		// TODO Auto-generated method stub
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			primaryStage.setTitle("¶«ÈíÔÆÒ½Ôº");
			primaryStage.setHeight(320);
			primaryStage.setWidth(450);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
