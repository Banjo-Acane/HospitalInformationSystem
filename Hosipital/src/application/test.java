package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;

public class test {
	public static void main(String[] args) {
		File f = new File("RegistrationData.txt");
		writeUser();
		//testString();
		// writeRegistration();
		//writePriscription();
	}
	
	public static void testString() {
		String str = "新建处方56";
		System.out.println(str.indexOf("新建处方", 0));
		int n = Integer.parseInt(str.substring(4));
		System.out.println(n+1);
	}
	
    public static void writePriscription() {
    	Prescription p = new Prescription();
		p.setName("脑血栓/心肌梗塞");
		p.setState("");
		p.setPriscription(new HashMap<String, Medicine>());
		System.out.println("s ");
		Medicine m = new Medicine("蒙药心脑方", "盒", "每天两次", "一次一盒", "两盒", 233.33);
		m.setCode("");
		m.setPinyin("MYXNF");
		m.setType("");
		m.setUnit("");

		p.getPrescription().put("MYXNF", m);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Prescription.txt",true));
			bw.write(JSON.toJSONString(p)+"\r\n");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public static void writeRegistration() {
		Registration r = new Registration();
		r.setName("wow");
		r.setNumber(001);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("RegistrationData.txt"));
			bw.write(JSON.toJSONString(r)+"\r\n");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeUser() {
		 User u = new User("Acane123","123456","Acane","null","Payer");
//		User u = new User();
//		User u1 = new User();
//		User u2 = new User();
//		u.setName("华佗");
//		u.setId("huotuo");
//		u.setLocation("Doctor");
//		u.setPassword("huotuo233");
//		u.setDepartment("普通内科");
//
//		u1.setName("刘洪斌");
//		u1.setId("MYXNF");
//		u1.setLocation("Doctor");
//		u1.setPassword("MYXNF233");
//		u1.setDepartment("神经内科");
//
//		u2.setName("乌仁吉");
//		u2.setId("XSLZY");
//		u2.setLocation("Doctor");
//		u2.setPassword("Ga233");
//		u2.setDepartment("中医风湿免疫内科");
//		User u = new User("ghy","ghy123","ghy","null","Register");
//
		try {
			File f = new File("UserData.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
			bw.write(JSON.toJSONString(u) + "\r\n");
//			bw.write(JSON.toJSONString(u1) + "\r\n");
//			bw.write(JSON.toJSONString(u2) + "\r\n");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
