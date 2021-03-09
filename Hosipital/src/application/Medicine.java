package application;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;

public class Medicine {
	private SimpleStringProperty name, unit, code, format, type, usage, dosage, amount, state;
	private String pinyin;
	private double price;
	private LocalDateTime time;	// Medicine of Prescription
	public Medicine(String name, String format, String dosage, String usage, String amount, double price) {
		this.name = new SimpleStringProperty(name);
		this.format = new SimpleStringProperty(format);
		this.dosage = new SimpleStringProperty(dosage);
		this.usage = new SimpleStringProperty(usage);
		this.amount = new SimpleStringProperty(amount);
		this.price = price;
	}

	public Medicine() {

	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getUnit() {
		return unit.get();
	}

	public void setUnit(String unit) {
		this.unit = new SimpleStringProperty(unit);
	}
	public String getState() {
		return state.get();
	}
	public void setState(String state) {
		this.state = new SimpleStringProperty(state);
	}
	public String getCode() {
		return code.get();
	}

	public void setCode(String code) {
		this.code = new SimpleStringProperty(code);
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getType() {
		return type.get();
	}

	public void setType(String typeId) {
		this.type = new SimpleStringProperty(typeId);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUsage() {
		return usage.get();
	}

	public void setUsage(String usage) {
		this.usage = new SimpleStringProperty(usage);
	}

	public String getDosage() {
		return dosage.get();
	}

	public void setDosage(String dosage) {
		this.dosage = new SimpleStringProperty(dosage);
	}

	public String getFormat() {
		return format.get();
	}

	public void setFormat(String format) {
		this.format = new SimpleStringProperty(format);
	}

	public String getAmount() {
		return amount.get();
	}

	public void setAmount(String amount) {
		this.amount = new SimpleStringProperty(amount);
	}

}
