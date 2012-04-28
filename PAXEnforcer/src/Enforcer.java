import java.util.StringTokenizer;

/*
 * Needs to handle:
 * 
 * Handle - Done
 * Time Preferences - Need to figure out better way of setting/getting data since this less complex
 * Department Preferences - Done
 * Current Department - done
 * Current Shift - done
 * Specialized Constructor
 * Happiness Level based upon Department/ Time - calculation needed
 * Output method - on hold
 */
public class Enforcer extends Object {

	private static final int shifts = 3;
	private static final int depPrefNum = 5;
	
	private String handle;
	private String[] depPref;
	private int[] timePref;
	private String myDepartment;
	private int myShift = 0;
	
	public Enforcer()
	{
		setTimePref(new int[shifts]);
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
	
	//ToString output of Enforcer Data.  Currently paragraph style for testing/readability...
	//Will need to later convert into outputfriendly format or make a new method for output friendly format (probably second)
	public String toString()
	{
		//Output handle
		String output = "Handle: " + getHandle() + "  |  Department: " + "Need to implement" + "  |   Shift: " +myShift;
						
		output+= "\n";
		
		//Output Department Preferences
		output += "Department Preferences: ";
		for (int i=0;i<depPref.length;i++)
		{
			output+= depPref[i];
			if (i+1<depPref.length)
				{output+=",";}
		}
		output += "\n";
		
		//Output Time Preferences
		output += "Shift Preferences: ";
		for (int i=0;i<timePref.length;i++)
		{
			output+= timePref[i];
			if (i+1<timePref.length)
				{output+=",";}
		}
		output += "\n";
		
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
		String[] myString = depString.split(",");
		
		if(myString.length !=depPrefNum){
			throw new Exception("Departments in the string does not match " +
					"Department Prefr Number of " + depPrefNum);
		}
		return myString;
	}
	

	/*
	 * I was considering doing the requirements of data saving in each object but probably should do it in the main controller
	 * 
	//Output enforcer data in a useful way for file saving
	//Delimit with ",".  Use string tokenizer to import later
	//Consider adding in labels for the future to make data loading/saving easier
	public String output ()
	{
		//Handle, Department Preferences, Shift Preferences, CurrentDepartment, CurrentShift
		
		//Handle
		String output = getHandle() + ",";
		
		//Department Preferences
		for (int i=0;i<depPref.length;i++)
		{
			output+= depPref[i];
			if (i+1<depPref.length)
				{output+=",";}
		}
				
		//Shift(Time) Preferences
		for (int i=0;i<timePref.length;i++)
		{
			output+= timePref[i];
			if (i+1<timePref.length)
				{output+=",";}
		}
		output += "\n";
		
		//Output 
		return output;
	}
	*/
	
	public int calculate()
	{
		return 0;
	}
	
	//Getters and Setters
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
	
	public void setDepartment (String newDep)
	{
		this.myDepartment = newDep;
	}
	
	public String getDepartment()
	{
		return myDepartment;
	}
	
	public void setShift (int newShift)
	{
			try {
				if (newShift >3 || newShift<0)
				{
					throw new Exception("New Shift is out of bounds: " + newShift);
				}
				this.myShift= newShift;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	public int getShift()
	{
		return myShift;
	}
	public int[] getTimePref() {
		return timePref;
	}

	public void setTimePref(int[] timePref) {
		this.timePref = timePref;
	}

}
