
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Overall controller to handle managing all departments (and subsequently all enforcers)
 * 
 * Will possibly eventually control gui but definitely needs to control File I/O
 */

public class SchedulerController extends JPanel implements ActionListener{

	//GUI Elements
	private static final String FRAME_LABEL= "Enforcer Scheduler";
	JFrame frame = new JFrame (FRAME_LABEL);
	JPanel myPanel = new JPanel();
	JLabel emptyLabel;
	
	protected JButton b1;
	private int blah = 1;
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
		
		setGUI();
	}
	
    public void actionPerformed(ActionEvent e) {
    	if (e.getActionCommand().equals("Update"))
    	{
    		update();
    	}
    }
    
	private void setGUI()
	{
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(myPanel,BorderLayout.CENTER);
		
		emptyLabel = new JLabel(""+blah);
		emptyLabel.setPreferredSize(new Dimension(175,100));
		
		myPanel.setLayout(new BorderLayout());
		myPanel.add(emptyLabel,BorderLayout.CENTER);
		
		b1 = new JButton("MyButton");
		b1.setActionCommand("Update");
		b1.addActionListener(this);
		myPanel.add(b1,BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private void update()
	{
		blah++;
		myPanel.remove(emptyLabel);
		emptyLabel = new JLabel(""+blah);
		myPanel.add(emptyLabel, BorderLayout.CENTER);
		myPanel.revalidate();
		System.out.println (blah);
	}

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
	
	public String toString()
	{
		String output ="Department List: \n";
		for (int i=0; i<departmentList.size();i++)
		{
			output += (departmentList.get(i)).toString();
		}
		return output;
	}
	
	public static void main(String[] args) 
	{
		//Create the SchedulerController
		SchedulerController mySchedulerController = new SchedulerController();
		
		//Test loading
		try {
			mySchedulerController.load("Resources/DepartmentData.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println(mySchedulerController.toString());
	}
}
