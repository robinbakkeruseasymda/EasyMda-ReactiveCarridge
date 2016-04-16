package test.merge;

// @generated
public class TestJavaMerge1 {

	// @generated
	private String name;
	// @generated
	private int age;
	
	// @generated
	public String getName() {
		return name;
	}
	// @generated
	public void setName(String name) {
		this.name = name;
	}
	// @generated
	public int getAge() {
		return age;
	}
	// @generated
	public void setAge(int age) {
		this.age = age;
	}
	
	
	//== manual code that we want to keep
	private String upname() {
		return name.toUpperCase();
	}
}
