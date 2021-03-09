package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControllerD implements Initializable {

	@FXML
	private Label choosePatient;
	@FXML
	private Label patientInform;
	@FXML
	private Pane mainPane;
	@FXML
	private Pane addMedicineDetails;
	@FXML
	private Pane addMedicineToPri;
	@FXML
	private Pane addPrescriptionWindow;

	@FXML
	private TextField getPrescriptionName;
	@FXML
	private TextField drugCodeTF;
	@FXML
	private TextField drugNameTF;
	@FXML
	private TextField drugFormatTF;
	@FXML
	private TextField drugUnitTF;
	@FXML
	private TextField drugUsageTF;
	@FXML
	private TextField drugDosageTF;
	@FXML
	private TextField drugAmountTF;

	@FXML
	private TableView<Registration> waitingPatient;
	@FXML
	private TableColumn<Registration, Integer> medicalNumCol;
	@FXML
	private TableColumn<Registration, SimpleObjectProperty<String>> patientNameCol;
	@FXML
	private TableColumn<Registration, Integer> patientAgeCol;

	@FXML
	private TableView<Registration> overPatient;
	@FXML
	private TableColumn<Registration, Integer> medicalNumOCol;
	@FXML
	private TableColumn<Registration, SimpleObjectProperty<String>> patientNameOCol;
	@FXML
	private TableColumn<Registration, Integer> patientAgeOCol;

	@FXML
	private TableView<Medicine> allMedicineView;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> drugCodeColumn;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> drugNameColumn;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> drugFormatColumn;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> drugPriceColumn;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> drugDosageColumn;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> drugTypeColumn;

	@FXML
	private TableView<Prescription> allPrescriptionView;
	@FXML
	private TableColumn<Prescription, SimpleObjectProperty<String>> prescriptionNameColumn;
	@FXML
	private TableColumn<Prescription, SimpleObjectProperty<String>> prescriptionStateColumn;

	@FXML
	private TableView<Medicine> mediOfPreView;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> drugNameP;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> drugFormatP;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> drugPriceP;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> drugUsageP;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> drugDosageP;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> drugAmountP;

	@FXML
	private TableView<Prescription> modelPreView;
	@FXML
	private TableColumn<Prescription, SimpleObjectProperty<String>> modelPreName;
	@FXML
	private TableColumn<Prescription, SimpleObjectProperty<String>> modelPreRange;

	@FXML
	private TableView<Medicine> mediofModelView;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> mediOfModelName;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> mediOfModelFormat;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> mediOfModelPrice;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> mediOfModelUsage;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> mediOfModelDosage;
	@FXML
	private TableColumn<Medicine, SimpleObjectProperty<String>> mediOfModelAmount;

	@FXML
	private TextField medicineCode;
	@FXML
	private Button loginOutButton;

	@FXML
	private TextField patientName;

	Map<String, Medicine> allMedis = MedicineMaster.medicine;
	List<Prescription> allPres = new ArrayList<>();
	List<Prescription> modelPre = PrescriptionMaster.prescriptionModel;

	Registration patient;

	public void initialize(URL location, ResourceBundle resources) {
		medicalNumCol.setCellValueFactory(new PropertyValueFactory<Registration, Integer>("number"));
		patientNameCol
				.setCellValueFactory(new PropertyValueFactory<Registration, SimpleObjectProperty<String>>("name"));
		patientAgeCol.setCellValueFactory(new PropertyValueFactory<Registration, Integer>("age"));
		waitingPatient.setItems(getWaitPatient());

		medicalNumOCol.setCellValueFactory(new PropertyValueFactory<Registration, Integer>("number"));
		patientNameOCol
				.setCellValueFactory(new PropertyValueFactory<Registration, SimpleObjectProperty<String>>("name"));
		patientAgeOCol.setCellValueFactory(new PropertyValueFactory<Registration, Integer>("age"));
		// overPatient.setItems(getOverPatient());

		prescriptionNameColumn
				.setCellValueFactory(new PropertyValueFactory<Prescription, SimpleObjectProperty<String>>("name"));
		prescriptionStateColumn
				.setCellValueFactory(new PropertyValueFactory<Prescription, SimpleObjectProperty<String>>("state"));
		allPrescriptionView.setItems(getPrescriptions());

		allPrescriptionView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		drugCodeColumn.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("code"));
		drugNameColumn.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("name"));
		drugFormatColumn
				.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("format"));
		drugPriceColumn.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("price"));
		drugDosageColumn
				.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("dosage"));
		drugTypeColumn.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("type"));
		allMedicineView.setItems(getMedicine());

		mediOfPreView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		modelPreName.setCellValueFactory(new PropertyValueFactory<Prescription, SimpleObjectProperty<String>>("name"));
		modelPreRange
				.setCellValueFactory(new PropertyValueFactory<Prescription, SimpleObjectProperty<String>>("range"));
		modelPreView.setItems(getModel());

		FilteredList<Medicine> filteredData = new FilteredList<>(getMedicine(), b -> true);
		medicineCode.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(medicine -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (medicine.getCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (medicine.getName().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				return false;
			});
		});
		SortedList<Medicine> sortedData = new SortedList<>(filteredData);
		allMedicineView.setItems(sortedData);

		FilteredList<Registration> Data = new FilteredList<>(getOverPatient(), b -> true);
		patientName.textProperty().addListener((observable, oldValue, newValue) -> {
			Data.setPredicate(patient -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String str = newValue.toLowerCase();
				if (patient.getName().toLowerCase().indexOf(str) != -1) {
					return true;
				}
				return false;
			});
		});
		SortedList<Registration> sorted = new SortedList<>(Data);
		overPatient.setItems(sorted);

		FilteredList<Registration> Datas = new FilteredList<>(getWaitPatient(), b -> true);
		patientName.textProperty().addListener((observable, oldValue, newValue) -> {
			Datas.setPredicate(patient -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String str = newValue.toLowerCase();
				if (patient.getName().toLowerCase().indexOf(str) != -1) {
					return true;
				}
				return false;
			});
		});
		SortedList<Registration> sort = new SortedList<>(Datas);
		waitingPatient.setItems(sort);

	}

	public void showMediOfModel() {
		if (!modelPreView.getSelectionModel().isEmpty()
				&& !modelPreView.getSelectionModel().getSelectedItem().getPrescription().isEmpty()) {
			mediOfModelName
					.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("name"));
			mediOfModelFormat
					.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("format"));
			mediOfModelPrice
					.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("price"));
			mediOfModelUsage
					.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("usage"));
			mediOfModelDosage
					.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("dosage"));
			mediOfModelAmount
					.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("amount"));
			mediofModelView.setItems(getMediOfModelPre());
		} else {
			mediofModelView.setItems(null);
		}
	}

	public void showMediOfPre() {
		if (!allPrescriptionView.getSelectionModel().isEmpty()
				&& !allPrescriptionView.getSelectionModel().getSelectedItem().getPrescription().isEmpty()) {
			drugNameP.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("name"));
			drugFormatP.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("format"));
			drugPriceP.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("price"));
			drugUsageP.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("usage"));
			drugDosageP.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("dosage"));
			drugAmountP.setCellValueFactory(new PropertyValueFactory<Medicine, SimpleObjectProperty<String>>("amount"));
			mediOfPreView.setItems(getMediOfPre());
		} else {
			mediOfPreView.setItems(null);
		}
	}

	public ObservableList<Registration> getWaitPatient() {
		ObservableList<Registration> re = FXCollections.observableArrayList();
		for (Map.Entry<Integer, Registration> entry : RegistrationMaster.registration.entrySet()) {
			if (entry.getValue().getState().equals("未诊")) {
				re.add(entry.getValue());
			}
		}
		return re;
	}

	public ObservableList<Registration> getOverPatient() {
		ObservableList<Registration> re = FXCollections.observableArrayList();
		for (Map.Entry<Integer, Registration> entry : RegistrationMaster.registration.entrySet()) {
			if (entry.getValue().getState().equals("已诊")) {
				re.add(entry.getValue());
			}
		}
		return re;
	}

	public ObservableList<Prescription> getPrescriptions() {
		ObservableList<Prescription> pr = FXCollections.observableArrayList();
		if (!allPres.isEmpty()) {
			for (Prescription prescription : allPres) {
				pr.add(prescription);
			}
		}
		return pr;
	}

	public ObservableList<Medicine> getMedicine() {
		ObservableList<Medicine> medicines = FXCollections.observableArrayList();
		for (Map.Entry<String, Medicine> entry : allMedis.entrySet()) {
			medicines.add(entry.getValue());
		}
		return medicines;
	}

	public ObservableList<Prescription> getModel() {
		ObservableList<Prescription> pr = FXCollections.observableArrayList();
		if (!modelPre.isEmpty()) {
			for (Prescription prescription : modelPre) {
				pr.add(prescription);
			}
		}
		return pr;
	}

	public ObservableList<Medicine> getMediOfPre() {
		ObservableList<Medicine> medicines = FXCollections.observableArrayList();
		Prescription p = allPrescriptionView.getSelectionModel().getSelectedItem();
		for (Map.Entry<String, Medicine> entry : p.getPrescription().entrySet()) {
			medicines.add(entry.getValue());
		}
		return medicines;
	}

	public ObservableList<Medicine> getMediOfModelPre() {
		ObservableList<Medicine> medicines = FXCollections.observableArrayList();
		Prescription p = modelPreView.getSelectionModel().getSelectedItem();
		for (Map.Entry<String, Medicine> entry : p.getPrescription().entrySet()) {
			medicines.add(entry.getValue());
		}
		return medicines;
	}

	public void showAddPrescriptionWindow() {
		if (patient == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("未选择患者！");
			alert.setContentText("清选择要开处方的患者");
			alert.showAndWait();
		} else {
			if (patient.getState().equals("未诊")) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText("该患者未诊断！");
				alert.setContentText("需先诊断患者才可开药");
				alert.showAndWait();
			} else if (patient.getState().equals("已诊")) {
				addPrescriptionWindow.setVisible(true);
				mainPane.setDisable(true);
			}
		}
	}

	public void closeAddPrescriptionWindow() {
		addPrescriptionWindow.setVisible(false);
		mainPane.setDisable(false);
	}

	public void showAddMedicineWindow() {
		if (allPrescriptionView.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("请选择要添加药品的处方");
			alert.showAndWait();
		} else {
			addMedicineToPri.setVisible(true);
			mainPane.setDisable(true);
		}
	}

	public void closeAddMedicineWindow() {
		addMedicineToPri.setVisible(false);
		mainPane.setDisable(false);
	}

	public void showMediDetailWindow() {
		addMedicineDetails.setVisible(true);
		addMedicineToPri.setDisable(true);
	}

	public void closeMediDetailWindow() {
		addMedicineDetails.setVisible(false);
		addMedicineToPri.setDisable(false);
		mainPane.setDisable(false);
	}

	public void addPrescription() {
		Prescription p = new Prescription();
		p.setPatientMediNum(patient.getNumber());
		p.setPatientMediName(patient.getName());
		String name = getPrescriptionName.getText();
		int num = 0;
		if (name.equals("")) {
			for (Prescription prescription : allPres) {
				if (prescription.getName().indexOf("新建处方", 0) != -1) {
					num = Integer.parseInt(prescription.getName().substring(4));
				}
			}
			p.setName("新建处方" + String.valueOf(num + 1));
		} else {
			p.setName(name);
		}
		p.setState("暂存");

		HashMap<String, Medicine> medi = new HashMap<>();
		p.setPriscription(medi);
		allPres.add(p); // put it into the prescription list
		allPrescriptionView.getItems().add(p);// show it
		closeAddPrescriptionWindow();
	}

	public void deleteTemPri() {
		ObservableList<Prescription> selectedRows, allPre;
		selectedRows = allPrescriptionView.getSelectionModel().getSelectedItems();
		allPre = allPrescriptionView.getItems();
		for (Prescription p : selectedRows) {
			allPres.remove(p);
			allPre.remove(p);
		}
	}

	public void showMedicinDetailWindow() {
		showMediDetailWindow();
		Medicine m = allMedicineView.getSelectionModel().getSelectedItem();
		drugCodeTF.setText(m.getCode());
		drugNameTF.setText(m.getName());
		drugFormatTF.setText(m.getFormat());
		drugUnitTF.setText(m.getUnit());
	}

	public void closeMedicineDetailWindow() {
		addMedicineDetails.setVisible(false);
	}

	public void addMedicine() {
		Prescription p = allPrescriptionView.getSelectionModel().getSelectedItem();
		if (drugUsageTF.getText().isEmpty() || drugDosageTF.getText().isEmpty() || drugAmountTF.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("请填补药品详细信息");
			alert.showAndWait();
		} else {
			Medicine m = allMedicineView.getSelectionModel().getSelectedItem();
			m.setUsage(drugUsageTF.getText());
			m.setDosage(drugDosageTF.getText());
			m.setAmount(drugAmountTF.getText());
			m.setState("未付款");
			m.setTime(LocalDateTime.now());
			p.getPrescription().put(m.getPinyin(), m);
			mediOfPreView.setItems(getMediOfPre());
			closeMediDetailWindow();
		}
	}

	public void deleteMedicine() {
		Prescription p = allPrescriptionView.getSelectionModel().getSelectedItem();
		ObservableList<Medicine> selectedRows;
		selectedRows = mediOfPreView.getSelectionModel().getSelectedItems();
		for (Medicine m : selectedRows) {
			p.getPrescription().remove(m.getPinyin());
		}
		mediOfPreView.setItems(getMediOfPre());

	}

	public void selectModel() {
		if (overPatient.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("请在已诊患者栏中选择需开处方的患者");
			alert.showAndWait();
		} else if (modelPreView.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("请选择要添加的模板");
			alert.showAndWait();
		} else {
			Prescription p = modelPreView.getSelectionModel().getSelectedItem();
			Prescription pre = new Prescription();
			allPres.add(p);
			allPrescriptionView.getItems().add(p);
			for (Map.Entry<String, Medicine> m : p.getPrescription().entrySet()) {
				pre.getPrescription().put(m.getValue().getName(), m.getValue());
			}
			pre.setPatientMediName(overPatient.getSelectionModel().getSelectedItem().getName());
			pre.setPatientMediNum(overPatient.getSelectionModel().getSelectedItem().getNumber());
		}
	}

	public void sendPrescription() throws IOException {
		if (allPrescriptionView.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("请在已诊患者栏中选择需开处方的患者");
			alert.showAndWait();
		} else {
			Prescription p = allPrescriptionView.getSelectionModel().getSelectedItem();
			PrescriptionMaster.prescriptions.add(p);
			BufferedWriter bw = new BufferedWriter(new FileWriter("Prescription.txt", true));
			p.setRange("个人");
			p.setState("已开立");
			allPrescriptionView.setItems(getPrescriptions());
			bw.write(JSON.toJSONString(p) + "\r\n");
			bw.flush();
			bw.close();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.titleProperty().set("提示");
			alert.headerTextProperty().set("开立成功！");
			alert.showAndWait();
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

	public void chooseWaitingPatient() {
		patient = waitingPatient.getSelectionModel().getSelectedItem();
		choosePatient.setVisible(false);
		String string = "患者姓名:" + patient.getName() + "  患者病历号：" + patient.getNumber() + "\n" + "患者性别："
				+ patient.getGender() + "  患者年龄" + patient.getAge();
		patientInform.setText(string);
	}

	public void chooseOverPatient() {
		patient = overPatient.getSelectionModel().getSelectedItem();
		choosePatient.setVisible(false);
		String string = "患者姓名:" + patient.getName() + "  患者病历号：" + patient.getNumber() + "\n" + "患者性别："
				+ patient.getGender() + "  患者年龄" + patient.getAge();
		patientInform.setText(string);
	}

	public void toOverPatient() throws IOException {
		if (patient == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("请选择病人");
			alert.showAndWait();
		} else if (patient.getState().equals("已诊")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("诊断失败！");
			alert.setContentText("该患者已诊断");
			alert.showAndWait();
		} else {
			patient.setState("已诊");
			BufferedWriter bw1 = new BufferedWriter(new FileWriter("RegistrationData.txt"));
			bw1.write("");
			bw1.flush();
			bw1.close();
			for (Map.Entry<Integer, Registration> map : RegistrationMaster.registration.entrySet()) {
				BufferedWriter bw2 = new BufferedWriter(new FileWriter("RegistrationData.txt", true));
				bw2.write(JSON.toJSONString(map.getValue()) + "\r\n");
				bw2.flush();
				bw2.close();
			}
			overPatient.setItems(getOverPatient());
			waitingPatient.setItems(getWaitPatient());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("诊断完成！");
			alert.showAndWait();
		}
	}
}
