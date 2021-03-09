package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MedicineMaster {
	static Map<String, Medicine> medicine = new HashMap<>();

	public static void readData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("MedicineList.txt"));
			String str;
			try {
				while ((str = br.readLine()) != null) {
					Medicine m = new Medicine();
					String[] string = br.readLine().split(",");
						m.setCode(string[0]);
						m.setName(string[1]);
						m.setFormat(string[2]);
						m.setUnit(string[3]);
						m.setDosage(string[4]);
						m.setType(string[5]);
						m.setPrice(Double.parseDouble(string[6]));
						m.setPinyin(string[7]);
						medicine.put(m.getPinyin(), m);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
