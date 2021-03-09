package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller implements Initializable {
	@FXML
	private TextField nameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;
	@FXML
	private Label passError;
	@FXML
	private Label nameNotExits;
	@FXML
	private Label lackUserName;
	@FXML
	private Label lackPassword;
	@FXML
	private CheckBox rememberPassword;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		nameField.textProperty().addListener((observable, oldValue, newValue) -> {
			showPassword();
		});
	}

	User u = new User();

	public void checkUser() throws IOException {
		User u = new User();
		u = UserMaster.searchUser(nameField.getText());
		if(rememberPassword.isSelected()) {
			u.setRemPassword(true);
			BufferedWriter bw1 = new BufferedWriter(new FileWriter("UserData.txt"));
			bw1.write("");
			bw1.flush();
			bw1.close();
			for (User user : UserMaster.users) {
				BufferedWriter bw = new BufferedWriter(new FileWriter("UserData.txt", true));
				bw.write(JSON.toJSONString(user)+ "\r\n");
				bw.flush(); 
				bw.close();
			}
		}
		nameNotExits.setVisible(false);
		if (nameField.getText().isEmpty()) {
			lackUserName.setVisible(true);
		} else {
			lackUserName.setVisible(false);
		}
		if (passwordField.getText().isEmpty()) {
			lackPassword.setVisible(true);

		} else {
			lackPassword.setVisible(false);
		}
		if (!nameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
			if (UserMaster.searchUser(nameField.getText()) != null) {
				if (u.getPassword().equals(passwordField.getText())) {
					switch (u.getLocation()) {
					case "Register":
						openStageRegister();
						break;
					case "Doctor":
						openStageDoctor();
						break;
					case "Payer":// 给药结账那个
						openStagePay();
						break;
					}
				} else {
					passError.setVisible(true);
				}
			} else {
				nameNotExits.setVisible(true);
			}
		}
	}

	public void openStageRegister() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Register.fxml"));
			Stage mainStage = new Stage();
			mainStage.setScene(new Scene(root));
			mainStage.show();
			mainStage.setResizable(false);
			closeStageLogin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openStageDoctor() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Doctor.fxml"));
			Stage mainStage = new Stage();
			mainStage.setScene(new Scene(root));
			mainStage.show();
			mainStage.setWidth(1200);
			mainStage.setResizable(false);
			closeStageLogin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openStagePay() {// 开药的
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/pay.fxml"));
			Stage mainStage = new Stage();
			mainStage.setScene(new Scene(root));
			mainStage.show();
			mainStage.setResizable(false);
			closeStageLogin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeStageLogin() {
		Stage primarystage = (Stage) loginButton.getScene().getWindow();
		primarystage.close();
	}

	public void showPassword() {
		u = UserMaster.searchUser(nameField.getText());
		if (u != null) {
			for (String ID : UserMaster.remPassUsers) {
				if (ID.equals(u.getId())) {
				}
			}
			if (u.remPassword) {
				rememberPassword.setSelected(true);
				passwordField.setText(u.getPassword());
			}
		} else {
			passwordField.setText(null);
			rememberPassword.setSelected(false);
		}
	}

}
