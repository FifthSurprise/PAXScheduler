import java.util.StringTokenizer;


public class Enforcer {

	private String handle;
	private String[] depPref;
	private Department myDepartment;
	private int depPrefNum = 5;
	
	public Enforcer()
	{
		setDepPref(new String[depPrefNum]);
		setHandle ("Enforcer");
		myDepartment = null;
	}
	
	//add a list of departments based upon a string
	public void addDepPref(String depPrefString)
	{
		try {
			this.depPref = depConvert(depPrefString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString()
	{
		//Output handle
		String output = "Handle: " + getHandle() + " ";
		
		//Output Department Preferences
		output += "Department Preferences: ";
		for (int i=0;i<depPref.length-1;i++)
		{
			output+= depPref[i];
			if (i+1<depPref.length-1)
				{output+=",";}
		}
		output += " ";
		
		//Output 
		return output;
	}
	
	/*
	 * Converts a string into the array of departments.
	 * Currently mandates that the array has to match the preset depPrefNum
	 * Consider changing so people can put in null preferences...?
	 *  
	*/
	private String[] depConvert(String depString) throws Exception
	{
		String depConvert[] =new String[depPrefNum];
		StringTokenizer myTokenizer = new StringTokenizer(depString);
		if(myTokenizer.countTokens()==depPrefNum){
			throw new Exception("Departments in the string does not match " +
					"Department Prefr Number of " + depPrefNum);
		}
		else
		{
			for (int i= 0; i<depPrefNum-1; i++)
			{
				depConvert[i]=myTokenizer.nextToken();
			}
		}
		
		return depConvert;
	}
	
	public String[] getDepPref() {
		return depPref;
	}

	public void setDepPref(String[] depPref) {
		this.depPref = depPref;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}
	
	public void setDepartment (Department newDep)
	{
		this.myDepartment = newDep;
	}
	
	public Department getDepartment()
	{
		return myDepartment;
	}

}
