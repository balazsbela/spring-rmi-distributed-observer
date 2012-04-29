package org.balazsbela.bugreportr.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;

import org.balazsbela.bugreportr.commons.Bug;
import org.balazsbela.bugreportr.controller.MainController;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class BugBrowserImpl extends JDialog implements BugBrowser {

	private final JPanel contentPanel = new JPanel();
	private String projectName;
	private JList list;
	private	JLabel lblDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BugBrowserImpl dialog = new BugBrowserImpl();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BugBrowserImpl() {
		setBounds(100, 100, 450, 300);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				MainController.getInstance().setBugBrowser(null);
				super.windowClosed(e);
			}
		});
		setTitle(MainController.getInstance().getUsername());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			list = new JList();
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					if(list.getSelectedValue()!=null) {
						Bug b = (Bug) list.getSelectedValue();
						String label = "";
						if(b.getFixed()) {
							label+="Fixed:"+b.getDesription();
						}
						else {
							label+="Open:"+b.getDesription();
						}
						lblDescription.setText(label);
					}
				}
			});
			contentPanel.add(list, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				lblDescription = new JLabel("Description");
				buttonPane.add(lblDescription);
			}
			{
				JButton fixBugButton = new JButton("Fix Bug");
				fixBugButton.setActionCommand("fixBug");
				buttonPane.add(fixBugButton);
				fixBugButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(list.getSelectedValue()!=null) {
							Bug b = (Bug) list.getSelectedValue();
							
							MainController.getInstance().fixBug(b,projectName);
						}
					}
				});
				getRootPane().setDefaultButton(fixBugButton);
			}
		}		
		
		MainController.getInstance().setBugBrowser(this);

	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public void loadBugs() {
		//Populate list of bugs.
		DefaultListModel model = new DefaultListModel();
		List<Bug> bugs = MainController.getInstance().getBugs(this.projectName);
		for(Bug b:bugs) {
			model.addElement(b);
		}
		list.setModel(model);
	}

	public String getProjectName() {
		return projectName;
	}

}
