package com.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.beans.PropertyVetoException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

import com.interfaceServ.IBibliotheque;
import com.interfaceServ.ILivre;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JRadioButton;

public class IFrameLivre extends JInternalFrame {
	private JTable table;
	IBibliotheque b;
	JDialogAddLivre jdialogue = new JDialogAddLivre();
	int selectedRow;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IFrameLivre frame = new IFrameLivre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IFrameLivre() {

		super(" Livre ", true, true, true, true);
		try {
			b = (IBibliotheque) Naming.lookup("rmi://localhost:1099/IF_School");
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setBounds(100, 100, 585, 389);
		IBibliotheque b;
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		// panel_1.add(table, BorderLayout.WEST);
		//DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		refresh();

		// model.addRow();

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JRadioButton rdbtnIsbn = new JRadioButton("ISBN");
		
		JRadioButton rdbtnTitre = new JRadioButton("Titre");
		
		JRadioButton rdbtnAuteur = new JRadioButton("Auteur");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cherche = textField.getText();
				refreshFilter(cherche);
				
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnIsbn)
					.addGap(18)
					.addComponent(rdbtnTitre)
					.addGap(18)
					.addComponent(rdbtnAuteur)
					.addGap(106)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnValider)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnValider)
						.addComponent(rdbtnIsbn)
						.addComponent(rdbtnTitre)
						.addComponent(rdbtnAuteur))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		panel_1.add(panel_2, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Supprimer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				
			}
		});
		
				JButton btnModifier = new JButton("Resume");
				btnModifier.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						resume();
					}
				});
				
				JButton btnCommentaire = new JButton("Commentaire");
				btnCommentaire.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						commentaire();
					}
				});
				panel_3.add(btnCommentaire);
				panel_3.add(btnModifier);
		panel_3.add(btnNewButton);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdialogue.setVisible(true);
				//jdialogue.dispose();
				// SwingUtilities.updateComponentTreeUI(this);
				// repaint();
				//redraw();

				// refresh();
			}

		});
		jdialogue.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				System.out.println("windows closed");
				refresh();
			}
		});
		rdbtnIsbn.setSelected(true);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnAuteur);
		bg.add(rdbtnTitre);
		bg.add(rdbtnIsbn);
		

		panel_3.add(btnAjouter);
		scrollPane.setViewportView(table);
		System.out.println("fin de creation");

	}

	protected void commentaire() {
		// TODO Auto-generated method stub
		if(selectedRow > -1){
			//new JDialogCommentaire((String)table.getValueAt(selectedRow, 0)).setVisible(true);
		}
		
	}

	protected void resume() {
		// TODO Auto-generated method stub
		if(selectedRow > -1){
			new JDialogResumeLivre((String)table.getValueAt(selectedRow, 0)).setVisible(true);
			
			
		}
		
		
		
		
	}

	protected void refreshFilter(String recherche) {
		// TODO Auto-generated method stub
		try {
			DefaultTableModel model = new DefaultTableModel();
			List<ILivre> listes = b.rechercherLivre(recherche);
			model.addColumn("ISBN");
			model.addColumn("Auteur");
			model.addColumn("Titre");
			model.addColumn("Exemplaire");
			for (ILivre livre : listes) {
				String[] data = { String.valueOf(livre.getISBN()),
						livre.getAuteur(), livre.getTitre(),
						String.valueOf(livre.getNombreExemplaires()) };
				//DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				
				//for (int i = 0; i < model.getRowCount(); i++) {
					// model.removeRow(i);
				//}
				model.addRow(data);
				// model.fireTableDataChanged();
				//SwingUtilities.updateComponentTreeUI(this);

			}
			table.setModel(model);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getSelectionModel().addListSelectionListener(
					new ListSelectionListener(){
						@Override
						public void valueChanged(ListSelectionEvent e) {
							// TODO Auto-generated method stub
							selectedRow = table.getSelectedRow();
							
							
						}
					}
			        
			);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	protected void delete() {
		// TODO Auto-generated method stub
		try {
			//List<ILivre> livres = b.getMaBibliotheque();
			if(selectedRow >-1){
				b.supprimerLivre(Long.parseLong((String)table.getValueAt(selectedRow, 0)));
				System.out.println("row "+selectedRow);
				refresh();
				
				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void refresh() {

		try {
			DefaultTableModel model = new DefaultTableModel();
			List<ILivre> listes = b.getMaBibliotheque();
			model.addColumn("ISBN");
			model.addColumn("Auteur");
			model.addColumn("Titre");
			model.addColumn("Exemplaire");
			for (ILivre livre : listes) {
				String[] data = { String.valueOf(livre.getISBN()),
						livre.getAuteur(), livre.getTitre(),
						String.valueOf(livre.getNombreExemplaires()) };
				//DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				
				//for (int i = 0; i < model.getRowCount(); i++) {
					// model.removeRow(i);
				//}
				model.addRow(data);
				// model.fireTableDataChanged();
				//SwingUtilities.updateComponentTreeUI(this);

			}
			table.setModel(model);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getSelectionModel().addListSelectionListener(
					new ListSelectionListener(){
						@Override
						public void valueChanged(ListSelectionEvent e) {
							// TODO Auto-generated method stub
							selectedRow = table.getSelectedRow();
							
							
						}
					}
			        
			);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
