package javaTester;

public class System_Property {
	
public static void main(String [] args) {
	String projectPath = System.getProperty("user.dir");
	System.out.println(projectPath);
}

}
