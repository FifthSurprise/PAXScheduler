import java.util.ArrayList;

//Specific department at PAX (ex. Line Entertainment)

/*
 * Needs to handle:
 * 
 * Department Name
 * 2d array with time zones and list of enforcers in time zones
 * Staff Number for departments (2d array perhaps?).  Possibly implement this later
 * Output Method
 */
public class Department extends Object{
	public String departmentName; 
	
	//2d array of enforcers.  1st dimension = shifts.  2nd dimension = enforcers (arraylist to ease adding/removing)
	//Maybe fix unchecked conversion but probably unnecessary
	public ArrayList<Enforcer>[] myTeamArray = new ArrayList[3];
	
	public Department(String setName)
	{
		this.departmentName = setName;
		myTeamArray[0]= new ArrayList<Enforcer>();
		myTeamArray[1]= new ArrayList<Enforcer>();
		myTeamArray[2]= new ArrayList<Enforcer>();
	}
	
	//Add an enforcer to a shift
	public void addEnforcer(Enforcer addedEnforcer, int shift) throws Exception
	{
		//Throw an exception if adding an enforcer who already exists
		if (myTeamArray[shift].contains(addedEnforcer)){
			throw new Exception("Enforcer exists in this department already");
		}
		myTeamArray[shift].add(addedEnforcer);
	}
	
	
	//ToString output of Department Data
	public String toString()
	{
		String output = "Department Name: ";
		output += departmentName + "/n";
		
		return output;
	}
}
