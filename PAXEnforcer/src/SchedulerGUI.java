import java.awt.BorderLayout;
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
	JFrame frame = new JFrame (FRAME_LABEL);
	JPanel myPanel = new JPanel();
	
	JList departmentJList;
	JScrollPane departmentScrollPane;
	
	//button for removing department
	JButton deleteDepButton;
	
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
				 
		setGUI();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SchedulerGUI mySchedulerGUI = new SchedulerGUI();
		
	}

	private void setGUI()
	{	
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(true);
			frame.getContentPane().add(myPanel,BorderLayout.CENTER);
			
			myPanel.setLayout(new BorderLayout());
			
			//add the scrollPane
			departmentJList =new JList((mySchedulerController.getDepartmentList()).toArray()); 
			departmentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			departmentJList.addListSelectionListener((ListSelectionListener) this);
			departmentJList.setSelectedIndex(0);
			
			departmentScrollPane = new JScrollPane(departmentJList,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			departmentScrollPane.setPreferredSize(new Dimension(300,300));
			
			myPanel.add(departmentScrollPane,BorderLayout.CENTER);
			
			//add the delete button
			deleteDepButton = new JButton("Delete Department");
			deleteDepButton.setActionCommand("delete department");
			myPanel.add(deleteDepButton,BorderLayout.SOUTH);
			deleteDepButton.addActionListener(this);
			
			frame.pack();
			frame.setVisible(true);
	}
	
	private void updateElements()
	{
		//Update the departmentJList from mySchedulerController.  This will update the scrollpane
		departmentJList.setListData((mySchedulerController.getDepartmentList()).toArray());
		//reset the selected index
		departmentJList.setSelectedIndex(0);
		
	}
	
	//Delete the currently selected department
	private void deleteDepartment()
	{
		System.out.println ("Deleting " + departmentJList.getSelectedValue());
		mySchedulerController.deleteDepartment(departmentJList.getSelectedValue().toString());
		
		updateElements();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
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
				System.out.println (departmentJList.getSelectedValue());
			}
		}
		
	}

}
