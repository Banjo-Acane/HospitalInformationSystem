package application;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;

public class Registration implements Comparable<Registration>{
	private String receiptNum;
	private int number;// ²¡ÀúºÅ
	private SimpleStringProperty name;
	private String gender;
	private LocalDate birthday;
	private LocalDate date;
	private int age;
	private String noonType;
	private String id;
	private String address;
	private String level;
	private String department;
	private String doctor;
	private boolean needRecord;
	private double fee;
	private String PayType;
	private String Payway;
	private String state;

	public String getReceiptNum() {
		return receiptNum;
	}

	public void setReceiptNum(String receiptNum) {
		this.receiptNum = receiptNum;
	}



	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public boolean isNeedRecord() {
		return needRecord;
	}

	public void setNeedRecord(boolean needRecord) {
		this.needRecord = needRecord;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getPayType() {
		return PayType;
	}

	public void setPayType(String payType) {
		PayType = payType;
	}

	public String getPayway() {
		return Payway;
	}

	public void setPayway(String payway) {
		Payway = payway;
	}

	public String getNoonType() {
		return noonType;
	}

	public void setNoonType(String noonType) {
		this.noonType = noonType;
	}

	@Override
	public int compareTo(Registration o) {
		// TODO Auto-generated method stub
		return this.getReceiptNum().compareTo(o.getName());
	}

}
