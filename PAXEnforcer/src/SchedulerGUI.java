import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
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
			updateElements();
			
			//add the scrollPane
			myPanel.add(departmentScrollPane,BorderLayout.CENTER);
			frame.add(myPanel);
			
			frame.pack();
			frame.setVisible(true);
	}
	
	private void updateElements()
	{
		departmentJList =new JList((mySchedulerController.getDepartmentList()).toArray()); 
		departmentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		departmentJList.addListSelectionListener((ListSelectionListener) this);
		
		departmentScrollPane = new JScrollPane(departmentJList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		departmentScrollPane.setPreferredSize(new Dimension(300,300));	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
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
