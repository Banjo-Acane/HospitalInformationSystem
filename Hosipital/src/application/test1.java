package application;

import java.util.HashMap;
import java.util.Map;

public class test1 {
	Student s1 = new Student(1, "Sakata", 28, 177.0);
	Student s2 = new Student(2,"Hijikata",28,177.0);
	Map<String, Major> major = new HashMap<String, Major>();
	
	Major m1 = new Major("Chinese");
	Major m2 = new Major("Japnese");
	Major m3 = new Major("Maths");
	
}

class Student {
	public Student(int i, String string, int j, double d) {

	}
	int num;
	String name;
	int age;
	float height;
	Map<Integer, Major> major = new HashMap<Integer, Major>();
    
}
class Major{
	public Major(String string) {
		// TODO Auto-generated constructor stub
	}

	String name;
}
