package org.balazsbela.bugreportr.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import org.balazsbela.bugreportr.controller.MainController;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

public class BugReportDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextArea descriptionArea;
	String projectName="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BugReportDialog dialog = new BugReportDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BugReportDialog() {
		setBounds(100, 100, 450, 300);
		setTitle(MainController.getInstance().getUsername());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("120px"),
				ColumnSpec.decode("31px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("57px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("4px"),},
			new RowSpec[] {
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			JTextArea textArea = new JTextArea();
			contentPanel.add(textArea, "8, 2, left, center");
		}
		{
			JLabel lblName = new JLabel("Name:");
			contentPanel.add(lblName, "1, 4, 2, 1, left, center");
		}
		{
			nameField = new JTextField();
			contentPanel.add(nameField, "4, 4, 5, 1, fill, top");
			nameField.setColumns(10);
		}
		{
			JLabel lblDescription = new JLabel("Description:");
			contentPanel.add(lblDescription, "1, 6, 2, 1, left, center");
		}
		{
			descriptionArea = new JTextArea();
			contentPanel.add(descriptionArea, "4, 6, 5, 1, fill, fill");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String bugName=nameField.getText();
						String bugDescription=nameField.getText();
						
						MainController.getInstance().reportBug(bugName,bugDescription,projectName);
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		
		

	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
