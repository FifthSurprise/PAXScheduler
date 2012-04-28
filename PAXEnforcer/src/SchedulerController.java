import java.io.*;


/*
 * Overall controller to handle managing all departments (and subsequently all enforcers)
 * 
 * Will possibly eventually control gui but definitely needs to contorl File I/O
 */

public class SchedulerController {

	/**
	 * @param args
	 */
	public SchedulerController()
	{
		
	}
	//Load all departments and enforcers
	public void load() throws IOException
	{

        BufferedReader inputStream = null;

		//Load Departments
		try {
			inputStream = new BufferedReader(new FileReader("Resources/DepartmentData.txt"));
			String iString;
			
			while ((iString=inputStream.readLine())!=null)
			{					
				if (iString.equals("Department"))
				{
					parseDepartment(inputStream);
				}
				else if (iString.equals("Enforcer")) 
				{
					parseEnforcer(inputStream);
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
			{
				inputStream.close();
			}
		}
		//Load spare enforcers (possibly in same file?)
	}

	//parse new department from file
	public void parseDepartment(BufferedReader inputStream) throws IOException
	{
		System.out.println(inputStream.readLine());
	}

	//parse a new Enforcer from file
	public void parseEnforcer(BufferedReader inputStream)throws IOException
	{
		
	}
	
	//Save all data
	public void save()
	{
		//Save all departments and their enforcers
		
		//
	}	
	public static void main(String[] args) {
		SchedulerController mySchedulerController = new SchedulerController();
		
		try {
			mySchedulerController.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
