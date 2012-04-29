package org.balazsbela.bugreportr.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.balazsbela.bugreportr.commons.BugreportrServicesFacade;
import org.balazsbela.bugreportr.commons.NotificationSubscriber;
import org.balazsbela.bugreportr.commons.User;
import org.balazsbela.bugreportr.controller.MainController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.rmi.RmiServiceExporter;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class LoginWindow {

	private JFrame frame;
	private JTextField usernameField;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JLabel lblLogin;
	private JButton btnLogin;
	private JFrame parent;
	
	MainController controller;
	ProjectWindow parentProjWindow;
	
	private Log log = LogFactory.getLog(getClass());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow(JFrame parent) {
		this.parent = parent;
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//controller = (MainController) ctx.getBean("mainController");
		controller = MainController.getInstance();
		initialize();
		parent.setVisible(false);
		frame.setVisible(true);		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 410, 288);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		lblLogin = new JLabel("Login");
		frame.getContentPane().add(lblLogin, "12, 6");
		
		JLabel lblUsername = new JLabel("Username:");
		frame.getContentPane().add(lblUsername, "10, 10");
		
		usernameField = new JTextField();
		frame.getContentPane().add(usernameField, "12, 10, fill, default");
		usernameField.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		frame.getContentPane().add(lblPassword, "10, 14");
		
		passwordField = new JPasswordField();
		frame.getContentPane().add(passwordField, "12, 14");
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.login(usernameField.getText(), passwordField.getText());
					parent.setVisible(true);
					frame.dispose();					
					parentProjWindow.displayProjects();
					
				} catch (RemoteAccessException e) {
					JOptionPane.showMessageDialog(frame, "Server unreacheable!", "Network Error!", 0, null);
				}	
				catch(IllegalArgumentException e) {
					JOptionPane.showMessageDialog(frame, "Wrong credentials!", "Access denied!", 0, null);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnLogin, "12, 18");
	}

	public ProjectWindow getParentProjWindow() {
		return parentProjWindow;
	}

	public void setParentProjWindow(ProjectWindow parentProjWindow) {
		this.parentProjWindow = parentProjWindow;
	}

	
	
	
}
