
public class testController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println ("Begin test of application:\n");
		Enforcer myEnforcer = new Enforcer();
		myEnforcer.setDepartment(new Department("Line Entertainment"));
		myEnforcer.addDepPref("Test1,Test2,Test3,Test4,Test5");
		
		int[]myArray = new int[3];
		myArray[0]=1;
		myArray[1]=2;
		myArray[2]=3;
		
		myEnforcer.setTimePref(myArray);
		myEnforcer.setHandle("FifthSurprise");
		
		System.out.println (myEnforcer.toString());
	}

}
