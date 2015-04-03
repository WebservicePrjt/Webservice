package com.gui;

import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainGui {

	public JFrame frmWebServiceApplicaiton;
	JMenuBar menuBar;
	private JLabel textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//MainGui window = new MainGui();
					//window.frmWebServiceApplicaiton.setVisible(true);
					JDialogConnect connect = new JDialogConnect(null,true);
					connect.setVisible(true);
					//connect.
					//if(connect.isVisible()==false) details();
					//connect.setModalityType(type);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGui() {
		initialize();
		details();
		
	}

	public  void details() {
		textField.setText(JDialogConnect.e.getNom());
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWebServiceApplicaiton = new JFrame();
		frmWebServiceApplicaiton.setTitle("Web Service Application");
		frmWebServiceApplicaiton.setBounds(100, 100, 450, 300);
		frmWebServiceApplicaiton.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frmWebServiceApplicaiton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frmWebServiceApplicaiton.setJMenuBar(menuBar);
		
		JMenu mnBiblio = new JMenu("Biblio");
		//JMenu mnEmprunt = new JMenu("Emprunt");
		
		menuBar.add(mnBiblio);
		//menuBar.add(mnEmprunt);
		frmWebServiceApplicaiton.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		final JDesktopPane desktopPane = new JDesktopPane();
		frmWebServiceApplicaiton.getContentPane().add(desktopPane);
		
		JPanel panel = new JPanel();
		frmWebServiceApplicaiton.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Etudiant : ");
		
		textField = new JLabel();
		//textField.setColumns(10);
		//textField.setEditable(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(297, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		JMenuItem mntmAjouter = new JMenuItem("Ajouter");
		JMenuItem mntmEmprunt = new JMenuItem("Emprunter");
		JMenuItem mntmLivre = new JMenuItem("Rechercher");
		
		mnBiblio.add(mntmAjouter);
		mnBiblio.add(mntmEmprunt);
		mnBiblio.add(mntmLivre);
		
		
		mntmAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JDialogAddLivre().setVisible(true);
			}
		});
		
		mntmEmprunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame iframe = new IFrameEmprunt();
				//iframe.setContentPane(desktopPane);
				desktopPane.add(iframe);
				iframe.setVisible(true);
				try {
					iframe.setMaximum(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		mntmLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("entree dans le menu");
				JInternalFrame iframe = new IFrameLivre();
				//iframe.setContentPane(desktopPane);
				desktopPane.add(iframe);
				iframe.setVisible(true);
				try {
					iframe.setMaximum(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
	}
}
