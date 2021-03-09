package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class UserMaster {
	static List<User> users = new ArrayList<>();
	static List<String> remPassUsers = new ArrayList<>();
	
	public static User searchUser(String id) {
		for (User user : users) {
			if (id.equals(user.getId())) {
				return user;
			}
		}
		return null;
	}

	public void addUser() {

	}

	public void reviseUser() {

	}

	public void deleteUser() {

	}
	
	public static void readData() {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("UserData.txt"));
			String string;
			while ((string = bf.readLine()) != null) {
				User u = new Doctor();
				u = JSON.parseObject(string, Doctor.class);
				if(u.getLocation().equals("Doctor")) {
					User doctor = new Doctor();
					doctor = (Doctor)u;
					users.add(doctor);
				}else {
					User user = new User();
					user = u;
					users.add(user);
				}
			}
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void readRemPassData() {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("remPass.txt"));
			String string;
			while ((string = bf.readLine()) != null) {
				remPassUsers.add(string);
			}
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
