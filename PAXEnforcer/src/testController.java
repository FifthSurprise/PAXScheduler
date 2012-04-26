//Controller that just tests whether Enforcer/Department objects work the way they should

public class testController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println ("Begin test of enforcer object:\n");
		Enforcer myEnforcer = new Enforcer();
		//myEnforcer.setDepartment(new Department("Line Entertainment"));
		myEnforcer.setDepartment("Line Entertainment");
		myEnforcer.addDepPref("Test1,Test2,Test3,Test4,Test5");
		
		int[]myArray = new int[3];
		myArray[0]=1;
		myArray[1]=2;
		myArray[2]=3;
		
		myEnforcer.setTimePref(myArray);
		myEnforcer.setHandle("FifthSurprise");
		
		System.out.println (myEnforcer.toString());
		
		System.out.println("Begin test of department object:\n");
		Department myDepartment = new Department("Line Entertainment");
		
		myDepartment.addEnforcer(myEnforcer, 3);
		System.out.println(myDepartment.toString());
		
		myDepartment.remove("FifthSurprise");
		System.out.println(myDepartment.toString());
	}

}
