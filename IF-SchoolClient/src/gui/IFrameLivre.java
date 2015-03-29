package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
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

public class IFrameLivre extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	IBibliotheque b;
	JDialogAddLivre jdialogue = new JDialogAddLivre();

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

		JLabel lblNewLabel = new JLabel("ISBN");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblAuteur = new JLabel("Auteur");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblTitre = new JLabel("Titre");

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_2
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblAuteur)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTitre)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		gl_panel_2
				.setVerticalGroup(gl_panel_2
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel)
														.addComponent(
																textField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblAuteur)
														.addComponent(
																textField_1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblTitre)
														.addComponent(
																textField_2,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);
		panel_1.add(panel_2, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Supprimer");
		panel_3.add(btnNewButton);

		JButton btnModifier = new JButton("Modifier");
		panel_3.add(btnModifier);

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

		panel_3.add(btnAjouter);
		scrollPane.setViewportView(table);
		System.out.println("fin de creation");

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
				
				
				for (int i = 0; i < model.getRowCount(); i++) {
					// model.removeRow(i);
				}
				model.addRow(data);
				// model.fireTableDataChanged();
				//SwingUtilities.updateComponentTreeUI(this);

			}
			table.setModel(model);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
