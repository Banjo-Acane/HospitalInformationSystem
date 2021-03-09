package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class ControllerR implements Initializable {
	@FXML
	private MenuItem registMenu;
	@FXML
	private ChoiceBox<String> payTypeChoiceBox;
	@FXML
	private ChoiceBox<String> genderChoiceBox;
	@FXML
	private ChoiceBox<String> departmentChoiceBox;
	@FXML
	private ChoiceBox<String> doctorChoiceBox;
	@FXML
	private ChoiceBox<String> payWayChoiceBox;
	@FXML
	private ChoiceBox<String> noonChoiceBox;
	@FXML
	private ChoiceBox<String> registTypeChoiceBox;
	@FXML
	private DatePicker birthdayDatePicker;
	@FXML
	private DatePicker consultationDataPicker;
	@FXML
	private TextField receiptNumTextField;
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField recordNumTextField;
	@FXML
	private TextField ageTextField;
	@FXML
	private TextField IDnumTextField;
	@FXML
	private TextField addressTextField;
	@FXML
	private TextField feeTextField;
	@FXML
	private CheckBox needRecordCheckBox;
	@FXML
	private Button clearButton;

	@FXML
	private Button loginOutButton;

	@FXML
	private TextField MedicalNumTF;
	@FXML
	private TextField PatientName;
	@FXML
	private TextField PatientID;
	@FXML
	private TextField PatientAddress;
	Registration reg;

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// for the choice box
		birthdayDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
			calculateAge();
		});
		Set<Integer> set = RegistrationMaster.registration.keySet();
		Object[] objects = set.toArray();
		Arrays.sort(objects);
		receiptNumTextField.setText(getReceiptNumber());
		recordNumTextField.setText(String.valueOf((Integer.parseInt((objects[objects.length - 1]).toString()) + 1)));
		payWayChoiceBox.getItems().addAll("现金", "移动支付", "刷卡支付");

		genderChoiceBox.getItems().addAll("男", "女");

		payTypeChoiceBox.getItems().addAll("公费", "自费");

		noonChoiceBox.getItems().addAll("上午", "下午");

		registTypeChoiceBox.getItems().addAll("专家号", "普通号", "急诊号");

		List<Department> list = DepartmentMaster.departments;

		for (Department department : list) {
			departmentChoiceBox.getItems().add(department.getName());
		}

		List<User> list1 = UserMaster.users;
		for (User user : list1) {
			if (user.getLocation().equals("Doctor")) {
				doctorChoiceBox.getItems().add(user.getName());
			}
		}
	}

	public String getReceiptNumber() {
		String num;
		String number;
		Map<Integer, Registration> patient = RegistrationMaster.registration;
		List<Registration> patients = new ArrayList<>();
		for (Map.Entry<Integer, Registration> map : patient.entrySet()) {
			patients.add(map.getValue());
		}
		Collections.sort(patients);
		num = patients.get(patient.size() - 1).getReceiptNum();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
		if (num.substring(0, 7).equals(df.format(LocalDate.now()).toString())) {
			number = num + 1;
		} else {
			number = df.format(LocalDate.now()).toString() + "00";
		}
		return number;
	}

	public void createNewRegistration() {
		// 要填写必要项！！！！
		if (receiptNumTextField.getText()==null || recordNumTextField.getText()==null
				|| nameTextField.getText()==null || birthdayDatePicker.getValue() == null
				|| genderChoiceBox.getValue()==null || departmentChoiceBox.getValue() == null
				|| ageTextField.getText()==null || doctorChoiceBox.getValue()==null
				|| payWayChoiceBox.getValue()==null || registTypeChoiceBox.getValue().isEmpty()
				|| feeTextField.getText()==null) {
			Alert warn = new Alert(AlertType.WARNING);
			warn.setContentText("请将必填项补充完整");
			warn.setTitle("提示");
			warn.setHeaderText("信息不全");
			warn.showAndWait();
		} else {
			Registration r = new Registration();
			r.setFee(0);
			r.setReceiptNum(receiptNumTextField.getText());
			r.setNumber(Integer.parseInt(recordNumTextField.getText()));
			r.setName(nameTextField.getText());
			r.setBirthday(birthdayDatePicker.getValue());
			r.setGender(genderChoiceBox.getValue());
			r.setId(IDnumTextField.getText());
			r.setDepartment(departmentChoiceBox.getValue());
			r.setAddress(addressTextField.getText());
			r.setNoonType(noonChoiceBox.getValue());
			r.setDate(consultationDataPicker.getValue());
			r.setFee(Double.parseDouble(feeTextField.getText()));
			r.setPayType(payTypeChoiceBox.getValue());
			r.setPayway(payWayChoiceBox.getValue());
			r.setState("未诊");
			r.setAge(Integer.parseInt(ageTextField.getText()));
			r.setNeedRecord(needRecordCheckBox.isSelected());
			boolean problem = false;
			for (Map.Entry<Integer, Registration> map : RegistrationMaster.registration.entrySet()) {
				if (map.getKey().equals(r.getNumber())) {
					problem = true;
					Alert sameNum = new Alert(AlertType.WARNING);
					sameNum.setContentText("该病历号已存在，请更改病历号");
					sameNum.showAndWait();
					break;
				}
				if (map.getValue().getReceiptNum() == r.getReceiptNum()) {
					problem = true;
					Alert sameNum = new Alert(AlertType.WARNING);
					sameNum.setContentText("该发票号已存在，请更改发票号");
					sameNum.showAndWait();
					break;
				}
			}
			if (problem == false) {
				RegistrationMaster.addRegistration(r);
				RegistrationMaster.writeRegistration(r);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("挂号成功！");
				alert.setContentText("挂号费为" + calculateFee() + "元");
				alert.showAndWait();
			}
		}
	}

	public Double calculateFee() {
		Double fee = 0.0;
		String type = registTypeChoiceBox.getValue();
		User doctor = new Doctor();
		for (User user : UserMaster.users) {
			if (user.getName().equals(doctorChoiceBox.getValue()))
				doctor = user;
		}
		Boolean needRecord = needRecordCheckBox.isSelected();
		if (doctor != null && type != null) {
			String noon = noonChoiceBox.getValue();
			((Doctor) doctor).getFee();
			if (type.equals("急诊号")) {
				fee += 10;
			} else if (type.equals("普通号")) {
				fee += 5;
			} else if (type.equals("专家号")) {
				if (noon != null) {
					if (noon.equals("下午")) {
						fee += ((Doctor) doctor).getFee() * 1.5;
					}else {
						fee += ((Doctor) doctor).getFee();
					}
				} else {
					fee += ((Doctor) doctor).getFee();
				}
				if (needRecord) {
					fee += 1;
				}
				feeTextField.setText(fee.toString());
			}
		}
		return fee;
	}

	public void calculateAge() {
		if (birthdayDatePicker.getValue() != null) {
			LocalDate now = LocalDate.now();
			int age = Period.between(birthdayDatePicker.getValue(), now).getYears();
			ageTextField.setText(String.valueOf(age));
		}
	}

	public void clearField() {
		nameTextField.clear();
		recordNumTextField.clear();
		ageTextField.clear();
		IDnumTextField.clear();
		addressTextField.clear();
		feeTextField.clear();
		payTypeChoiceBox.getSelectionModel().clearSelection();
		genderChoiceBox.getSelectionModel().clearSelection();
		departmentChoiceBox.getSelectionModel().clearSelection();
		doctorChoiceBox.getSelectionModel().clearSelection();
		payWayChoiceBox.getSelectionModel().clearSelection();
		noonChoiceBox.getSelectionModel().clearSelection();
		registTypeChoiceBox.getSelectionModel().clearSelection();
		needRecordCheckBox.setSelected(false);
		birthdayDatePicker.setValue(null);
		consultationDataPicker.setValue(null);
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

	public void searchThePatient() {
		int medicalNum = Integer.parseInt(MedicalNumTF.getText());
		boolean find = false;
		for (Map.Entry<Integer, Registration> map : RegistrationMaster.registration.entrySet()) {
			if (medicalNum == map.getKey()) {
				find = true;
				reg = map.getValue();
				PatientName.setText(map.getValue().getName());
				if (!reg.getAddress().isEmpty())
					PatientAddress.setText(reg.getAddress());
				if (!reg.getId().isEmpty()) {
					PatientID.setText(reg.getId());
				}
			}
		}
		if (!find) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("无该患者相关记录");
			alert.showAndWait();
		}
	}

	public void concelTheRegister() throws IOException {
		if (reg == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("未选择退号对象！");
			alert.showAndWait();
		} else {
			if (reg.getState().equals("未诊")) {
				RegistrationMaster.registration.remove(reg.getNumber());
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
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("退号成功！");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText("退号失败！");
				alert.setContentText("医生已诊断，无法退号");
				alert.showAndWait();
			}
		}
	}
}
