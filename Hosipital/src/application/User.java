package application;

public class User {
	private String id;
	private String password;
	private String name;
	private String department;
	private String location;// �Һ��շ�Ա������ҽ����ҽ��ҽ����ҩ������Ա���������Ա��ҽԺ����Ա
	boolean remPassword;
	public User(String id, String password, String name, String department, String location) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.department = department;
		this.location = location;
	}

	public User() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isRemPassword() {
		return remPassword;
	}

	public void setRemPassword(boolean remPassword) {
		this.remPassword = remPassword;
	}
	

}
