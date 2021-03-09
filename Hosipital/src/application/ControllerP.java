package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;

import java.util.ResourceBundle;
import java.util.Set;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ControllerP implements Initializable {
	@FXML
	private Pane payInformWindow;
	@FXML
	private TextField NumberTF;
	@FXML
	private TextField MedicalNumberTF;
	@FXML
	private TextField NameTF;
	@FXML
	private TextField MoneyTF;
	@FXML
	private TextField PayTF;
	@FXML
	private TextField PayBackTF;
	@FXML
	private ChoiceBox<String> payType;
	@FXML
	private TextField MedicalNumTF;
	@FXML
	private TextField PatientNameTF;
	@FXML
	private TextField PatientID;
	@FXML
	private TextField PatientAddress;
	@FXML
	private TableView<Medicine> payInform;
	@FXML
	private TableColumn<Medicine, String> drugNameColumn;
	@FXML
	private TableColumn<Medicine, String> drugPriceColumn;
	@FXML
	private TableColumn<Medicine, String> drugQuantityColumn;
	@FXML
	private TableColumn<Medicine, String> timeColumn;
	@FXML
	private TableColumn<Medicine, String> stateColumn;

	@FXML
	Button loginOutButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		payType.getItems().addAll("现金", "移动支付", "刷卡支付");
		PayTF.textProperty().addListener((observable, oldValue, newValue) -> {
		    calculatePayBack();
		});
	}

	Registration r;
	Prescription p;
	int medicalNum = -1;

	public void showPatientPre() {

		medicalNum = Integer.parseInt(MedicalNumTF.getText());
		drugNameColumn.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
		drugPriceColumn.setCellValueFactory(new PropertyValueFactory<Medicine, String>("price"));
		drugQuantityColumn.setCellValueFactory(new PropertyValueFactory<Medicine, String>("amount"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<Medicine, String>("time"));
		stateColumn.setCellValueFactory(new PropertyValueFactory<Medicine, String>("state"));
		boolean find = false;
		ObservableList<Medicine> items = FXCollections.observableArrayList();
		for (Prescription pre : PrescriptionMaster.prescriptions) {
			if (pre.getPatientMediNum() == Integer.parseInt(MedicalNumTF.getText())) {
				find = true;
				p = pre;
				PatientNameTF.setText(pre.getPatientMediName());
				for (Map.Entry<Integer, Registration> map : RegistrationMaster.registration.entrySet()) {
					if (medicalNum == map.getKey()) {
						r = map.getValue();
						if (!r.getAddress().isEmpty())
							PatientAddress.setText(r.getAddress());
						if (!r.getId().isEmpty()) {
							PatientID.setText(r.getId());
						}
					}
				}
				for (Map.Entry<String, Medicine> map : pre.getPrescription().entrySet()) {
					items.add(map.getValue());
				}
			}
			payInform.setItems(items);
		}
		if (!find) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("无该患者相关记录");
			alert.showAndWait();
		}
	}

	public void openInformWindow() {
		if (medicalNum == -1) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("请输入病历号查找待付款药品！");
			alert.showAndWait();
		} else if (r.getState().equals("已付款")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("该用户已经付款，无需再次付款");
			alert.showAndWait();
		} else {
			 
			NumberTF.setText(String.valueOf(r.getReceiptNum()));
			MedicalNumberTF.setText(String.valueOf(medicalNum));
			payType.setValue(r.getPayway());
			NameTF.setText(r.getName());
			double fee = r.getFee();
			for (Map.Entry<String, Medicine> map : p.getPrescription().entrySet()) {
				fee += map.getValue().getPrice()*Integer.parseInt(map.getValue().getAmount());
			}
			BigDecimal c = new BigDecimal(fee);
			fee = c.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
			MoneyTF.setText(String.valueOf(fee));
			payInformWindow.setVisible(true);
		}
	}

	public void calculatePayBack() {
		double money = Double.parseDouble(MoneyTF.getText());
		double pay = Double.parseDouble(PayTF.getText());
		double payback = pay-money;
		BigDecimal b = new BigDecimal(payback);
		payback = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();     
		if(payback>=0) {
			PayBackTF.setText(String.valueOf(payback));
		}
	}

	public void closeInformWindow() {
		payInformWindow.setVisible(false);
	}

	public void finish() throws IOException {
		if(PayTF.getText()!=null&&PayBackTF.getText()!=null) {
			if(Double.parseDouble(PayBackTF.getText())>=0) {
				BufferedWriter bw1 = new BufferedWriter(new FileWriter("Prescription.txt"));
				bw1.write("");
				bw1.flush();
				p.setRange("个人");
				BufferedWriter bw2 = new BufferedWriter(new FileWriter("Prescription.txt", true));
				for (Prescription pre : PrescriptionMaster.prescriptions) {
					bw2.write(JSON.toJSONString(pre) + "\r\n");
					bw2.flush();
					bw2.close();
				}
				for (Entry<String, Medicine> map : p.getPrescription().entrySet()) {
					map.getValue().setState("已付款");
				}
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("付款成功！");
				alert.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText("金额不足！");
				alert.setContentText("请输入足够金额！");
			}
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("请输入支付金额");
		}
	}

	public void loginOut() {
		Stage primarystage = (Stage) loginOutButton.getScene().getWindow();
		primarystage.close();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/login.fxml"));
			Stage mainStage = new Stage();
			mainStage.setScene(new Scene(root));
			mainStage.show();
			mainStage.setResizable(false);
			mainStage.setHeight(320);
			mainStage.setWidth(450);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
