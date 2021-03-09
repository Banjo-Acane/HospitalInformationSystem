package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentMaster {
	static List<Department> departments = new ArrayList<>();

	public Department serachDepart(String key) {
		for (Department department : departments) {
			if (department.getId().equals(key)) {
				return department;
			}

			if (department.getName().equals(key)) {
				return department;
			}
		}
		System.out.println("该科室不存在");
		return null;
	}

	public void addDepart() {

	}

	public void reviseDepart() {

	}

	public void deleteDepart(Department d) {
		departments.remove(d);
	}

	public static void readData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Department.txt"));
			String str;
			try {
				while ((str = br.readLine()) != null) {
					String[] string = br.readLine().split(",");

					Department d = new Department();
					d.setId(string[0]);
					d.setName(string[1]);
					d.setSystemclassify(string[2]);
					d.setType(string[3]);
					departments.add(d);
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
