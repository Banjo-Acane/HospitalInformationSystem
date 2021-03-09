package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class PrescriptionMaster {
	static List<Prescription> prescriptionModel = new ArrayList<>();
	static List<Prescription> prescriptions = new ArrayList<>();
	public static void readModelData() {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("PrescriptionModel.txt"));
			String string;
			while ((string = bf.readLine()) != null) {
				Prescription p = JSON.parseObject(string, Prescription.class);
				prescriptionModel.add(p);
			}
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void readData() {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("Prescription.txt"));
			String string;
			while ((string = bf.readLine()) != null) {
				Prescription p = JSON.parseObject(string, Prescription.class);
				prescriptions.add(p);
			}
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
