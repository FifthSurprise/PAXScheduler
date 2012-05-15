import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SchedulerGUI extends JPanel implements ListSelectionListener,ActionListener{

	private SchedulerController mySchedulerController;
	
	//GUI elements
	private static final String FRAME_LABEL= "Enforcer Scheduler";
	private JFrame frame = new JFrame (FRAME_LABEL);
	private JPanel mainPanel = new JPanel();
	
	//Department Panel
	private JPanel depPanel = new JPanel();
	private JList departmentJList;
	private JScrollPane departmentScrollPane;
	private Department currentDep;
	
	//panel for viewing department data
	private JScrollPane depViewScrollPane;
	private JPanel depViewPanel = new JPanel();
	private static final JLabel shiftOneLabel= new JLabel ("First Shift");
	private JList shiftOneList = new JList();
	private static final JLabel shiftTwoLabel= new JLabel ("Second Shift");
	private JList shiftTwoList = new JList();;
	private static final JLabel shiftThreeLabel= new JLabel ("Third Shift");
	private JList shiftThreeList = new JList();
	
	//button for removing department
	private JButton deleteDepButton;

	private boolean recentUpdate = false;
	
	public SchedulerGUI()
	{
		mySchedulerController = new SchedulerController();
		
		//test loading
		try {
			
			mySchedulerController.load("Resources/DepartmentData.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mySchedulerController.toString());
		setGUI();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SchedulerGUI mySchedulerGUI = new SchedulerGUI();
		
	}

	//Initialize the GUI elements
	private void setGUI()
	{	
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(true);
			frame.getContentPane().add(mainPanel,BorderLayout.CENTER);
			
			mainPanel.setLayout(new BorderLayout());
			mainPanel.setPreferredSize(new Dimension(800,800));
			
			//Set up the Department Panel and JList
			mainPanel.add(depPanel,BorderLayout.WEST);
			depPanel.setLayout(new BorderLayout());
			
			departmentJList =new JList((mySchedulerController.getDepartmentList()).toArray()); 
			departmentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			departmentJList.addListSelectionListener((ListSelectionListener) this);
			
			//When setting up system, set up selection/update for departments.  Remove when loading is no longer part of testing
			if (departmentJList.isSelectionEmpty())
				{	departmentJList.setSelectedIndex(0);
					updateElements();
				}
			
			//Set up the Scrollpane
			departmentScrollPane = new JScrollPane(departmentJList,
									JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			departmentScrollPane.setPreferredSize(new Dimension(300,800));
			depPanel.add(departmentScrollPane,BorderLayout.WEST);
			
			//Add the depViewPanel (this is not set up initially because there may not be any department to select)
			depViewScrollPane= new JScrollPane(depViewPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			depViewScrollPane.setPreferredSize(new Dimension(400,800));
			depPanel.add(depViewScrollPane,BorderLayout.EAST);
			
			depViewPanel.setLayout(new BoxLayout(depViewPanel,BoxLayout.Y_AXIS));
			shiftOneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			shiftTwoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			shiftThreeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			depViewPanel.add(shiftOneLabel);
			depViewPanel.add(shiftOneList);
			depViewPanel.add(shiftTwoLabel);
			depViewPanel.add(shiftTwoList);
			depViewPanel.add(shiftThreeLabel);
			depViewPanel.add(shiftThreeList);
			
			
			//add the delete button
			deleteDepButton = new JButton("Delete Department");
			deleteDepButton.setActionCommand("delete department");
			depPanel.add(deleteDepButton,BorderLayout.SOUTH);
			deleteDepButton.addActionListener(this);
			
			frame.pack();
			frame.setVisible(true);
	}
	
	private void updateElements()
	{
		if (recentUpdate)
		{
		//Update the departmentJList from mySchedulerController.  This will update the scrollpane
		departmentJList.setListData((mySchedulerController.getDepartmentList()).toArray());

		recentUpdate=  false;
		}
		//reset the selected index	
		if (departmentJList.getModel().getSize()>0 && departmentJList.isSelectionEmpty())
			departmentJList.setSelectedIndex(0);
		else
		{
		//Updating Dep Panel
		updateDepPanel();
		mainPanel.validate();
		}
	}
	
	//Update the department panel
	private void updateDepPanel()
	{
		if (departmentJList.isSelectionEmpty())
		{
			//I don't have a department selected, hide all the department data
			shiftOneList.setVisible(false);
			shiftTwoList.setVisible(false);
			shiftThreeList.setVisible(false);
		}
		else
		{
			shiftOneList.setListData(currentDep.myTeamArray[0].toArray());
			shiftTwoList.setListData(currentDep.myTeamArray[1].toArray());
			shiftThreeList.setListData(currentDep.myTeamArray[2].toArray());
			shiftOneList.setVisible(true);
			shiftTwoList.setVisible(true);
			shiftThreeList.setVisible(true);
		}
		
	}
	
	//Delete the currently selected department
	private void deleteDepartment()
	{
		if (!departmentJList.isSelectionEmpty())
		{
			System.out.println ("Is nothing selected? " + departmentJList.isSelectionEmpty());
			System.out.println ("Deleting " + currentDep.toString());
			mySchedulerController.deleteDepartment(currentDep.toString());
			recentUpdate=true;
			updateElements();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Delete the selected department
		if ("delete department".equals(e.getActionCommand()))
		{
			deleteDepartment();
		}	
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false)
		{
			//Accessing departmentGUI
			if (e.getSource().equals(departmentJList))
			{
				if (departmentJList.isSelectionEmpty())
					currentDep = null;
				else
				{
					
					currentDep=mySchedulerController.getDepartment((departmentJList.getSelectedValue().toString()));
					System.out.println ("Current selection is " +departmentJList.getSelectedValue());
					updateElements();
				}
			}
		}
		
	}

}
