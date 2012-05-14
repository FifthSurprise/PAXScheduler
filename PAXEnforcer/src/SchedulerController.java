
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;


/*
 * Overall controller to handle managing all departments (and subsequently all enforcers)
 * 
 * Will possibly eventually control gui but definitely needs to control File I/O
 */

public class SchedulerController{

	//Schedule Components
	public ArrayList<Department> departmentList;
	public ArrayList<Enforcer> unassignedE;

	
	/**
	 * @param args
	 */
	public SchedulerController()
	{
		this.departmentList = new ArrayList<Department>();
		this.unassignedE = new ArrayList<Enforcer>();
	}

    
    public void addEnforcer(String newName)
    {
    	departmentList.add(new Department(newName));
    }
	
	public ArrayList<Department> getDepartmentList()
	{
		return departmentList;
	}

/*	
	//Activates when acting on a list
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false)
		{
			//Accessing departmentGUI
			if (e.getSource().equals(departmentJList))
			{
				System.out.println (departmentJList.getSelectedValue());
			}
		}
	}*/

	//Load all departments and enforcers
	public void load(String path) throws IOException
	{
        BufferedReader inputStream = null;
		//Load Departments
		try {
			inputStream = new BufferedReader(new FileReader(path));
			String iString;
			while ((iString=inputStream.readLine())!=null)
			{					
				if (iString.equals("Department:"))
						parseDepartment(inputStream);				
				else if (iString.equals("Enforcer:")) 
					{
						//Need to parse enforcers for this department
					}
				else
				{
					//Unable to parse text
					try {
						throw new Exception ("Unable to parse text! " + iString);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
		}
		finally
		{
			//close the input stream
			if (inputStream != null)
				inputStream.close();
		}
		//Load spare enforcers (possibly in same file?)
	}

	//parse new department from file
	public void parseDepartment(BufferedReader inputStream) throws IOException
	{
		String iString;
		//Parse the department
		iString=inputStream.readLine();
		
		//Ensure that the department hasn't been added already
		if (containDepartment(iString))
		{
			try {
				throw new Exception ("Department already exists: " + iString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Create a new department from the file
		Department parseDep = new Department(iString);
		
		//add Department's enforcers if any (stop when hit a break line)
		while (!iString.equals("break;"))
		{
			iString = inputStream.readLine();
			if (iString.equals ("Enforcer:"))
			{
				iString = inputStream.readLine();
				Enforcer addEnforcer = parseEnforcer(iString);
				parseDep.addEnforcer(addEnforcer, addEnforcer.getShift());
			}
		}	
		departmentList.add(parseDep);
	}

	//parse a new Enforcer from file
	public Enforcer parseEnforcer(String parseString)throws IOException
	{
		String[] enfStrArray = parseString.split(";");
		Enforcer pEnforcer = new Enforcer();
		
		//Set handle
		pEnforcer.setHandle(enfStrArray[0]);
		
		//Set current department
		pEnforcer.setDepartment(enfStrArray[1]);
		
		//Set current shift 
		pEnforcer.setShift(enfStrArray[2]);
		
		//Set department preferences
		pEnforcer.setDepPref(enfStrArray[3]);
		
		//Set shift preferences
		pEnforcer.setTimePref(enfStrArray[4]);
		
		return pEnforcer;
	}
	
	//Save all data
	public void save()
	{
		//Save all departments and their enforcers

	}	
	
	//Check to see if a department name already exists before attempting to add it
	public boolean containDepartment (String department)
	{
		for (int i=0;i<departmentList.size();i++)
		{
			if (departmentList.get(i).departmentName.equals(department))
			{
				return true;
			}
		}
		return false;
	}
	
	//delete a department from the list.  Implement removing all of that department's enforcers later.
	public void deleteDepartment(String dep)
	{
		for (int i=0;i<departmentList.size();i++)
		{
			if (departmentList.get(i).departmentName.equals(dep))
			{
				departmentList.remove(i);
			}
		}
	}
	
	//ToString of all department's data
	public String toString()
	{
		String output ="Department List: \n";
		for (int i=0; i<departmentList.size();i++)
		{
			output += (departmentList.get(i)).getData();
		}
		return output;
	}
}
