package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

public class MainGui {

	private JFrame frmWebServiceApplicaiton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frmWebServiceApplicaiton.setVisible(true);
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWebServiceApplicaiton = new JFrame();
		frmWebServiceApplicaiton.setTitle("Web Service Applicaiton");
		frmWebServiceApplicaiton.setBounds(100, 100, 450, 300);
		frmWebServiceApplicaiton.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frmWebServiceApplicaiton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmWebServiceApplicaiton.setJMenuBar(menuBar);
		
		JMenu mnBiblio = new JMenu("Biblio");
		menuBar.add(mnBiblio);
		
		final JDesktopPane desktopPane = new JDesktopPane();
		frmWebServiceApplicaiton.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JMenuItem mntmLivre = new JMenuItem("Livre");
		mnBiblio.add(mntmLivre);
		
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
