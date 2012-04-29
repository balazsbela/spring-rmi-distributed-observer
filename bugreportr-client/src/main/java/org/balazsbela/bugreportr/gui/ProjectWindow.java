package org.balazsbela.bugreportr.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;

import org.balazsbela.bugreportr.commons.Project;
import org.balazsbela.bugreportr.controller.MainController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProjectWindow {

	private JFrame frame;
	private LoginWindow loginWindow;
	private MainController controller;
	private JList projectList;
	private JButton btnReportBug;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectWindow window = new ProjectWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProjectWindow() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// controller = (MainController) ctx.getBean("mainController");
		controller = MainController.getInstance();
		initialize();
		this.frame.setVisible(false);
		loginWindow = new LoginWindow(frame);
		loginWindow.setParentProjWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnReportBug = new JButton("Report Bug");
		frame.getContentPane().add(btnReportBug, BorderLayout.SOUTH);

		projectList = new JList();
		frame.getContentPane().add(projectList, BorderLayout.CENTER);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void displayProjects() {
		frame.setTitle(MainController.getInstance().getUsername());
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosed(e);
				MainController.getInstance().unsubscribe();
				System.exit(0);
			}
		});
		List<Project> projects = controller.getProjects();
		DefaultListModel model = new DefaultListModel();
		for (Project p : projects) {
			model.addElement(p);
		}
		projectList.setModel(model);

		String label = controller.getLabel();
		btnReportBug.setLabel(label);

		if (label.contains("Browse")) {
			btnReportBug.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					BugBrowserImpl bugBrowser;
					bugBrowser = new BugBrowserImpl();
					bugBrowser.setProjectName(((Project) projectList.getSelectedValue()).getName());
					bugBrowser.setVisible(true);
					bugBrowser.loadBugs();
				}
			});
		} else {
			btnReportBug.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					BugReportDialog dialog = new BugReportDialog();
					dialog.setProjectName(((Project) projectList.getSelectedValue()).getName());
					dialog.setVisible(true);
				}
			});
		}
	}
}
