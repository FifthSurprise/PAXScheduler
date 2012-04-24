
public class Enforcer {

	private String handle;
	private String[] depPref;
	private Department myDepartment;
	
	public Enforcer()
	{
		setDepPref(new String[5]);
		setHandle ("Enforcer");
		myDepartment = null;
	}
	
	public String toString()
	{
		String output = "Handle: " + getHandle() + " ";
		
		output += "Department Preferences: ";
		for (int i=0;i<depPref.length;i++)
		{
			output+= depPref[i] +",";
		}
		
		return output;
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

}
