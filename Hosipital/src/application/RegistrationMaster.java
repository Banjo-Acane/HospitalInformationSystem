package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;

public class RegistrationMaster {
	static Map<Integer, Registration> registration = new HashMap<>();

	public static void addRegistration(Registration r) {
		registration.put(r.getNumber(), r);
	}

	public static void writeRegistration(Registration r) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("RegistrationData.txt",true));
			bw.write(JSON.toJSONString(r)+"\r\n");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void readData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("RegistrationData.txt"));
		    String str;
		    while((str=br.readLine())!=null){
		    	Registration r = JSON.parseObject(str,Registration.class);
		    	addRegistration(r);
		    }
		    br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
