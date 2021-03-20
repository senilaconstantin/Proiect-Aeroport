import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class User {
	private int clicks=0;
	private JFrame frame;
	JLayeredPane layeredPane = new JLayeredPane();
	private JTable table;
	DefaultTableModel model;
	DefaultTableModel model_1;
	DefaultTableModel modelMasina;
	DefaultTableModel modelBilet;
	
	private JTable table_1;
	private JTextField Nume;
	private JTextField Prenume;
	private JTextField varsta;
	private JTextField mail;
	private JTextField tel;
	private JTable tableMasini;
	private JTable DestinatiiFrecvente=new JTable();
	private JTable ZboruriZilnice= new JTable();
	private JTable ZboruriNoapte= new JTable();
	private JTable SosireZi= new JTable();
	private JTable SosireNoapte= new JTable();
	Object[] columnss= new Object[] { "Destinatii", "NumarPlecari"};
	Object[] columnsss=new Object[] { "Data Plecarii", "Numar Zboruri"};
	Object[] column10=new Object[] { "Destinatia", "Ora plecarii"};
	Object[] column11=new Object[] { "De la", "Ora sosirii", "Data sosirii"};
	Object[] column12=new Object[] { "De la", "Ora sosirii", "Data Sosirii"};
	
	private int adm=0;
	
	private JTextField textField;
	private JTextField textField_1;
	String[] s = new String[1000];

	private int p;

	ConnectDB c;
	private JTable tableBilett;
	private JTextField txtID;
	private JTextField textGreutateB;
	private JTextField numeTxt;

	public User() throws Exception {
		initialize();
	}
	
	public void setP(int pp) {
		adm=pp;
	}

	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public void paint(Graphics g) {
		g.drawLine(132, 0, 132, 736);
	}

	private void arataPlecari() {
		// TODO Auto-generated method stub
		try {

			Object[] row = new Object[6];

			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
			PreparedStatement statement = c.connection.prepareStatement(
					"select plecari.id_plecare, plecari.destinatie, plecari.data_plecarii, plecari.ora_plecarii, plecari.pret_bilet, plecari.capacitate\r\n"
							+ " from plecari");
			ResultSet result = statement.executeQuery();
			modelBilet = new DefaultTableModel();
			Object[] colBilet = { "ID", "Destinatie", "Data Plecarii", "Ora Plecarii", "Pret", "Locuri Disponibile" };
			modelBilet.setColumnIdentifiers(colBilet);
			tableBilett.setModel(modelBilet);

			int i = 0;

			while (result.next()) {
				row[0] = result.getString("plecari.id_plecare");
				row[1] = result.getString("plecari.destinatie");
				row[2] = result.getString("plecari.data_plecarii");
				row[3] = result.getString("plecari.ora_plecarii");
				row[4] = result.getString("plecari.pret_bilet");
				row[5] = result.getString("plecari.capacitate");
				s[i] = result.getString("plecari.destinatie");
				i++;

				modelBilet.addRow(row);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void arata_masini_disponibile() {
		// TODO Auto-generated method stub
		try {
			Object[] row = new Object[3];
			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
			PreparedStatement statement = c.connection
					.prepareStatement("Select idMasina, Marca,pret_zi from Masina where statuss='neinchiriat'");
			ResultSet result = statement.executeQuery();
			modelMasina = new DefaultTableModel();
			Object[] colMasina = { "idMasina", "Marca", "Pret/zi" };
			modelMasina.setColumnIdentifiers(colMasina);
			tableMasini.setModel(modelMasina);
			while (result.next()) {
				row[0] = result.getString("idMasina");
				row[1] = result.getString("Marca");
				row[2] = result.getString("pret_zi");

				modelMasina.addRow(row);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void initialize() throws Exception {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setVisible(true);
		frame.setBounds(100, 100, 973, 736);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		Graphics g;

		c = null;
		try {
			c = new ConnectDB();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		layeredPane.setBounds(180, 143, 749, 530);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel start = new JPanel();
		start.setBackground(new Color(.47f, 0.31f, 0.67f, 0.8f));
		layeredPane.add(start, "name_11458011287200");
		start.setLayout(null);

		JTextPane txtpnRegiaAutonomAeroportul = new JTextPane();
		txtpnRegiaAutonomAeroportul.setForeground(Color.GRAY);
		txtpnRegiaAutonomAeroportul.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtpnRegiaAutonomAeroportul.setBackground(new Color(1f, 0f, 0f, 0));
		txtpnRegiaAutonomAeroportul.setEnabled(false);
		txtpnRegiaAutonomAeroportul.setEditable(false);
		txtpnRegiaAutonomAeroportul.setText(
				"\t\r\n\r\n\r\n\tRegia Autonom\u0103 Aeroportul Interna\u0163ional Avram Iancu Cluj, aflat\u0103 \u00EEn  subordinea Consiliului Jude\u0163ean Cluj \u00EEncep\u00E2nd cu anul 1997, este al doilea aeroport al \u0163\u0103rii \u0219i primul aeroport regional din Rom\u00E2nia.\r\n\r\n \r\n\tJudetul Cluj num\u0103r\u0103 aproximativ 700.000 locuitori. Din acest punct de  vedere, aeroportul din Cluj-Napoca poate fi comparat cu aeroporturile din ora\u015Fele europene Geneva \u015Fi Stuttgart, care \u00EEnregistreaz\u0103 anual 12 milioane, respectiv  9 milioane de pasageri. \u00CEn 2017, pentru prima dat\u0103, Aeroportul Interna\u0163ional Avram  Iancu Cluj a atins num\u0103rul de 2.000.000 de pasageri. \u00CEn acest moment, aeroportul  clujean este pe locul 2 \u00EEn Rom\u00E2nia din punctul de vedere al num\u0103rului de pasageri,  dup\u0103 C. N. Aeroporturi Bucure\u015Fti. Pe o raz\u0103 de 170 km \u00EEn jurul ora\u015Fului locuiesc  aproximativ 3 milioane de poten\u0163iali pasageri, a c\u0103ror deservire reprezint\u0103 opreocupare fundamental\u0103 pentru aeroportul clujean. \r\n");
		txtpnRegiaAutonomAeroportul.setBounds(0, 0, 749, 530);
		txtpnRegiaAutonomAeroportul.setDisabledTextColor(SystemColor.inactiveCaptionBorder);
		Border b  = BorderFactory.createLineBorder(Color.white, 5);
		 
        // set the border of this component
		txtpnRegiaAutonomAeroportul.setBorder(b);
		start.add(txtpnRegiaAutonomAeroportul);

		JPanel panelSosiri = new JPanel();
		panelSosiri.setBackground(SystemColor.inactiveCaption);
		layeredPane.add(panelSosiri, "name_11564255432600");
		panelSosiri.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 57, 729, 220);
		panelSosiri.add(scrollPane_1);
		// tabelul pentru sosiri
		table_1 = new JTable();
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table_1.setBackground(Color.LIGHT_GRAY);
		table_1.setEnabled(false);
		model_1 = new DefaultTableModel();
		Object[] column_1 = { "ID", "De la", "Data sosirii", "Ora sosirii" };
		Object[] row_1 = new Object[4];
		model_1.setColumnIdentifiers(column_1);
		table_1.setModel(model_1);
		scrollPane_1.setViewportView(table_1);

		JLabel lblNewLabel_1 = new JLabel("SOSIRI");
		lblNewLabel_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(336, 18, 127, 29);
		panelSosiri.add(lblNewLabel_1);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setVisible(false);
		scrollPane_6.setBounds(119, 355, 172, 151);
		panelSosiri.add(scrollPane_6);
		
		JButton ButonZi = new JButton("Sosirile de zi");
		ButonZi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(scrollPane_6.isVisible())
				{
					scrollPane_6.setVisible(false);
				}
				else
				{
				scrollPane_6.setVisible(true);
				try {
				Statement dest=c.connection.createStatement();
				String query="Select * from AfisareSosiriZi";
				ResultSet r=dest.executeQuery(query);
				
				Object[] rows11=new Object[3];
				DefaultTableModel modelDestinatie=new DefaultTableModel();
				modelDestinatie.setColumnIdentifiers(column11);
				SosireZi.setModel(modelDestinatie);
				scrollPane_6.setViewportView(SosireZi);
			
					while(r.next())
					{
						rows11[0]=r.getString(1);
						rows11[1]=r.getString(2);
						rows11[2]=r.getString(3);
						modelDestinatie.addRow(rows11);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			}

		
		});
		ButonZi.setForeground(SystemColor.desktop);
		ButonZi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ButonZi.setBounds(109, 305, 209, 40);
		panelSosiri.add(ButonZi);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setVisible(false);
		scrollPane_7.setBounds(454, 355, 190, 151);
		panelSosiri.add(scrollPane_7);
		
		JButton ButonNoapte = new JButton("Sosirile de noapte");
		ButonNoapte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					if(scrollPane_7.isVisible())
					{
						scrollPane_7.setVisible(false);
					}
					else
					{
					scrollPane_7.setVisible(true);
					try {
					Statement dest=c.connection.createStatement();
					String query="Select * from AfisareSosiriNoapte";
					ResultSet r=dest.executeQuery(query);
					
					Object[] rows12=new Object[3];
					DefaultTableModel modelDestinatie=new DefaultTableModel();
					modelDestinatie.setColumnIdentifiers(column12);
					SosireNoapte.setModel(modelDestinatie);
					scrollPane_7.setViewportView(SosireNoapte);
				
						while(r.next())
						{
							rows12[0]=r.getString(1);
							rows12[1]=r.getString(2);
							rows12[2]=r.getString(3);
							modelDestinatie.addRow(rows12);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				}

			
			});
		ButonNoapte.setForeground(SystemColor.desktop);
		ButonNoapte.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ButonNoapte.setBounds(444, 305, 200, 40);
		panelSosiri.add(ButonNoapte);
		
		
		

		try {
			
			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
			PreparedStatement statement = c.connection
					.prepareStatement("Select id_sosiri, de_la,data_sosire, ora_sosire from Sosiri");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				row_1[0] = result.getString("id_sosiri");
				row_1[1] = result.getString("de_la");
				row_1[2] = result.getString("data_sosire");
				row_1[3] = result.getString("ora_sosire");
				model_1.addRow(row_1);

			}

		} catch (Exception e) {
			System.out.println(e);
		}

		JPanel panelPlecari = new JPanel();
		panelPlecari.setBackground(SystemColor.inactiveCaption);
		layeredPane.add(panelPlecari, "name_11569871202500");
		panelPlecari.setLayout(null);

		JLabel numeplc = new JLabel("PLECARI");
		numeplc.setForeground(SystemColor.desktop);
		numeplc.setFont(new Font("Times New Roman", Font.BOLD, 25));
		numeplc.setBounds(322, 12, 127, 30);
		panelPlecari.add(numeplc);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 729, 233);
		panelPlecari.add(scrollPane);

		table = new JTable();
		table.setEnabled(false);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table.setBackground(Color.LIGHT_GRAY);
		model = new DefaultTableModel();
		Object[] column = { "ID", "Destinatie", "Data plecarii", "Ora plecarii" };
		Object[] row = new Object[4];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(54, 362, 196, 158);
		scrollPane_3.setVisible(false);
		panelPlecari.add(scrollPane_3);
		
		JButton butondestinatii = new JButton("Cele mai cautate destinatii");
		butondestinatii.setForeground(SystemColor.desktop);
		butondestinatii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(scrollPane_3.isVisible())
				{
					scrollPane_3.setVisible(false);
				}
				else
				{
				scrollPane_3.setVisible(true);
				try {
				Statement dest=c.connection.createStatement();
				String query="Select * from DestinatiiCautate";
				ResultSet r=dest.executeQuery(query);
				
				Object[] rows=new Object[2];
				DefaultTableModel modelDestinatie=new DefaultTableModel();
				modelDestinatie.setColumnIdentifiers(columnss);
				DestinatiiFrecvente.setModel(modelDestinatie);
				scrollPane_3.setViewportView(DestinatiiFrecvente);
			
					while(r.next())
					{
						rows[0]=r.getString(1);
						rows[1]=r.getString(2);
						modelDestinatie.addRow(rows);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			}

		
		});
		butondestinatii.setFont(new Font("Times New Roman", Font.BOLD, 15));
		butondestinatii.setBounds(31, 305, 220, 47);
		panelPlecari.add(butondestinatii);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setVisible(false);
		scrollPane_4.setBounds(300, 362, 202, 158);
		panelPlecari.add(scrollPane_4);
		
		JButton ButonZboruri = new JButton("Numarul de zboruri zilnice");
		ButonZboruri.setForeground(SystemColor.desktop);
		ButonZboruri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(scrollPane_4.isVisible())
				{
					scrollPane_4.setVisible(false);
				}
				else
				{
				scrollPane_4.setVisible(true);
				try {
				Statement zboruri=c.connection.createStatement();
				String query="Select * from AfisareZile";
				ResultSet r=zboruri.executeQuery(query);
				
				Object[] rowss=new Object[2];
				
				DefaultTableModel modelZboruri=new DefaultTableModel();
				modelZboruri.setColumnIdentifiers(columnsss);
				ZboruriZilnice.setModel(modelZboruri);
				scrollPane_4.setViewportView(ZboruriZilnice);
			
					while(r.next())
					{
						rowss[0]=r.getString(1);
						rowss[1]=r.getString(2);
						modelZboruri.addRow(rowss);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			}

		
		});
		ButonZboruri.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ButonZboruri.setBounds(280, 305, 238, 46);
		panelPlecari.add(ButonZboruri);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setVisible(false);
		scrollPane_5.setBounds(558, 362, 153, 158);
		panelPlecari.add(scrollPane_5);
	
		JButton ButonOre = new JButton("Zborurile de noapte");
		ButonOre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(scrollPane_5.isVisible())
				{
					scrollPane_5.setVisible(false);
				}
				else
				{
				scrollPane_5.setVisible(true);
				try {
				Statement ore=c.connection.createStatement();
				String query="Select * from Afisareore";
				ResultSet r=ore.executeQuery(query);
				
				Object[] row10=new Object[2];
				
				DefaultTableModel modelZboruri=new DefaultTableModel();
				modelZboruri.setColumnIdentifiers(column10);
				ZboruriNoapte.setModel(modelZboruri);
				scrollPane_5.setViewportView(ZboruriNoapte);
			
					while(r.next())
					{
						row10[0]=r.getString(1);
						row10[1]=r.getString(2);
						modelZboruri.addRow(row10);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			}

		
		});
		ButonOre.setForeground(SystemColor.desktop);
		ButonOre.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ButonOre.setBounds(548, 305, 163, 46);
		panelPlecari.add(ButonOre);
		
		
	
		

		//////

		try {
			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
			PreparedStatement statement = c.connection
					.prepareStatement("Select id_plecare, destinatie,data_plecarii, ora_plecarii from Plecari");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				row[0] = result.getString("id_plecare");
				row[1] = result.getString("destinatie");
				row[2] = result.getString("data_plecarii");
				row[3] = result.getString("ora_plecarii");
				model.addRow(row);

			}

		} catch (Exception e) {
			System.out.println(e);
		}

		/////

		JPanel panelInchiriaza = new JPanel();
		panelInchiriaza.setForeground(new Color(248, 248, 255));
		Border b2  = BorderFactory.createLineBorder(Color.white, 5);
		panelInchiriaza .setBorder(b2);
		panelInchiriaza.setBackground(SystemColor.inactiveCaption);
		panelInchiriaza.setToolTipText("");
		layeredPane.add(panelInchiriaza, "name_11575003809500");
		panelInchiriaza.setLayout(null);

		JPanel panelInchiriaza2 = new JPanel();
		panelInchiriaza2.setBackground(new Color(.47f, 0.31f, 0.67f, 0.1f));
		Border b1  = BorderFactory.createLineBorder(Color.white, 5);
		panelInchiriaza2 .setBorder(b1);
		layeredPane.add(panelInchiriaza2, "name_940990897200");
		panelInchiriaza2.setLayout(null);

		JLabel masDisponibile = new JLabel("Masini Disponibile");
		masDisponibile.setForeground(SystemColor.inactiveCaptionBorder);
		masDisponibile.setBackground(SystemColor.inactiveCaptionBorder);
		masDisponibile.setFont(new Font("Times New Roman", Font.BOLD, 25));
		masDisponibile.setBounds(439, 16, 206, 39);
		panelInchiriaza2.add(masDisponibile);

		JScrollPane scrollPaneMasini = new JScrollPane();
		scrollPaneMasini.setBounds(360, 65, 346, 409);
		panelInchiriaza2.add(scrollPaneMasini);

		tableMasini = new JTable();
		tableMasini.setEnabled(false);
		tableMasini.setForeground(Color.BLACK);
		tableMasini.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableMasini.setBackground(Color.LIGHT_GRAY);
		modelMasina = new DefaultTableModel();
		Object[] colMasina = { "idMasina", "Marca", "Pret/zi" };
		Object[] rowMasina = new Object[3];
		modelMasina.setColumnIdentifiers(colMasina);
		tableMasini.setModel(modelMasina);
		scrollPaneMasini.setViewportView(tableMasini);

		JLabel lblNewLabel_4 = new JLabel("idMasina");
		lblNewLabel_4.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_4.setBounds(147, 92, 112, 32);
		panelInchiriaza2.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Marca");
		lblNewLabel_4_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_4_1.setBounds(156, 182, 103, 32);
		panelInchiriaza2.add(lblNewLabel_4_1);

		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(138, 123, 133, 39);
		panelInchiriaza2.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(98, 212, 217, 39);
		panelInchiriaza2.add(textField_1);

		JButton butonRezervaMasina = new JButton("Rezerva");
		butonRezervaMasina.setFont(new Font("Times New Roman", Font.BOLD, 20));
		butonRezervaMasina.setBounds(148, 299, 111, 39);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		butonRezervaMasina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String query = "call update_masini(" + textField.getText() + ");";
				ResultSet r = null;
				ResultSet r2 = null;
				Connection connection = null;
				try {
					connection = DriverManager.getConnection(c.url, c.username, c.password);
					Statement statement = connection.createStatement();
					r = statement.executeQuery(query);
					String querry2 = "call update_user('" + textField_1.getText() + "');";
					r2 = statement.executeQuery(querry2);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textField.setText("");
				textField_1.setText("");
				arata_masini_disponibile();
			}
		});
		panelInchiriaza2.add(butonRezervaMasina);

		//////

		arata_masini_disponibile();
		//

		JLabel lblNewLabel_2 = new JLabel("Nume");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(99, 179, 66, 26);
		panelInchiriaza.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Prenume");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(49, 215, 116, 26);
		panelInchiriaza.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Varsta");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2_2.setBounds(49, 251, 116, 26);
		panelInchiriaza.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Email");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2_3.setBounds(49, 287, 116, 26);
		panelInchiriaza.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_3_1 = new JLabel("Telefon");
		lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_3_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2_3_1.setBounds(49, 323, 116, 26);
		panelInchiriaza.add(lblNewLabel_2_3_1);

		Nume = new JTextField("");
		Nume.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Nume.setBounds(187, 179, 176, 31);
		panelInchiriaza.add(Nume);
		Nume.setColumns(10);

		Prenume = new JTextField("");
		Prenume.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Prenume.setColumns(10);
		Prenume.setBounds(187, 216, 176, 30);
		panelInchiriaza.add(Prenume);

		varsta = new JTextField("");
		varsta.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		varsta.setColumns(10);
		varsta.setBounds(187, 252, 176, 30);
		panelInchiriaza.add(varsta);

		mail = new JTextField("");
		mail.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		mail.setColumns(10);
		mail.setBounds(187, 288, 176, 30);
		panelInchiriaza.add(mail);

		tel = new JTextField("");
		tel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tel.setColumns(10);
		tel.setBounds(187, 324, 176, 30);
		panelInchiriaza.add(tel);

		JPanel panelRezerva = new JPanel();
		layeredPane.add(panelRezerva, "name_11582456673300");
		panelRezerva.setLayout(null);

		JLabel plcrezv = new JLabel("Plecari");
		plcrezv.setBounds(287, 13, 452, 33);
		plcrezv.setForeground(new Color(0, 0, 0));
		plcrezv.setBackground(Color.WHITE);
		plcrezv.setHorizontalAlignment(SwingConstants.CENTER);
		plcrezv.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panelRezerva.add(plcrezv);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(261, 56, 478, 464);
		panelRezerva.add(scrollPane_2);

		///////////////////////////////////////////////////////////////////////////////////////////////////////
		tableBilett = new JTable();
		tableBilett.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableBilett.setBackground(Color.LIGHT_GRAY);
		modelBilet = new DefaultTableModel();
		Object[] colRezBilet = { "ID", "Destinatie", "Data Plecarii", "Ora Plecarii", "Pret", "Locuri Disponibile" };
		Object[] rowBilet = new Object[6];
		modelBilet.setColumnIdentifiers(colRezBilet);
		tableBilett.setModel(modelBilet);
		tableBilett.setEnabled(false);
		scrollPane_2.setViewportView(tableBilett);

		JLabel lblID = new JLabel("ID");
		lblID.setBounds(71, 140, 96, 21);
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panelRezerva.add(lblID);

		txtID = new JTextField();
		txtID.setBounds(71, 158, 96, 27);
		txtID.setHorizontalAlignment(SwingConstants.CENTER);
		txtID.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panelRezerva.add(txtID);
		txtID.setColumns(10);

		JLabel lblDest = new JLabel("Destinatie");
		lblDest.setBounds(71, 195, 96, 21);
		lblDest.setHorizontalAlignment(SwingConstants.CENTER);
		lblDest.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panelRezerva.add(lblDest);

		arataPlecari();

		JComboBox comboBox;
		comboBox = new JComboBox(s);
		comboBox.setBounds(52, 215, 136, 27);
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panelRezerva.add(comboBox);

		JButton btnRzv = new JButton("Rezerva");
		btnRzv.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnRzv.setForeground(Color.BLACK);
		btnRzv.setBounds(63, 376, 114, 33);
		panelRezerva.add(btnRzv);
		
		JLabel lblBagaj = new JLabel("Bagaj");
		lblBagaj.setForeground(Color.BLACK);
		lblBagaj.setHorizontalAlignment(SwingConstants.CENTER);
		lblBagaj.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblBagaj.setBounds(71, 252, 96, 21);
		panelRezerva.add(lblBagaj);
		
		JComboBox comboBoxBagaj = new JComboBox();
		comboBoxBagaj.setForeground(Color.BLACK);
		comboBoxBagaj.setFont(new Font("Times New Roman", Font.BOLD, 17));
		comboBoxBagaj.setModel(new DefaultComboBoxModel(new String[] {"DA", "NU"}));
		comboBoxBagaj.setBackground(Color.LIGHT_GRAY);
		comboBoxBagaj.setBounds(91, 274, 56, 27);
		panelRezerva.add(comboBoxBagaj);
		
		JLabel lblGreutate = new JLabel("Greutate");
		lblGreutate.setForeground(Color.BLACK);
		lblGreutate.setHorizontalAlignment(SwingConstants.CENTER);
		lblGreutate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblGreutate.setBounds(71, 311, 96, 21);
		panelRezerva.add(lblGreutate);
		
		textGreutateB = new JTextField();
		textGreutateB.setHorizontalAlignment(SwingConstants.CENTER);
		textGreutateB.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textGreutateB.setColumns(10);
		textGreutateB.setBounds(71, 329, 96, 27);
		panelRezerva.add(textGreutateB);
		btnRzv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String query = "call insert_rezervari(" + txtID.getText() + ");";
				ResultSet r = null;
				ResultSet r2 = null;
				try {
					c.connection = DriverManager.getConnection(c.url, c.username, c.password);
					Statement statement = c.connection.createStatement();
					r = statement.executeQuery(query);
					String querry2 = "call update_user_bilet('" + comboBox.getSelectedItem().toString() + "');";
					r2 = statement.executeQuery(querry2);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(comboBoxBagaj.getSelectedItem().toString().equals("DA")) {
					if(textGreutateB.getText().equals(""))
						new ErrrLogare("Adaugati greutatea bagajului!");
					else
						new ErrrLogare("Rezervarea s-a facut cu succes!");
					c.insertBagaj(textGreutateB.getText());
					//new ErrrLogare("Rezervare efectuata!");
				}
				else
					new ErrrLogare("Rezervarea s-a facut cu succes!");
				txtID.setText("");
				textGreutateB.setText("");
				
//				textField_1.setText("");
				arataPlecari();
			}
		});

		JButton butonIntr = new JButton("Intra");
		butonIntr.setForeground(SystemColor.menuText);

		butonIntr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Nume.getText().equals("") || Prenume.getText().equals("") || varsta.getText().equals("")
						|| mail.getText().equals("") || tel.getText().equals("")) {
					new ErrrLogare("Ceva nu e bine! Verifica campurile!");
				} else if (p == 1) {

					try {
						c.setUser(Nume.getText(), Prenume.getText(), varsta.getText(), mail.getText(), tel.getText());
						Nume.setText("");
						Prenume.setText("");
						varsta.setText("");
						mail.setText("");
						tel.setText("");
						switchPanels(panelInchiriaza2);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				else if (p == 2) {
					c.setUser(Nume.getText(), Prenume.getText(), varsta.getText(), mail.getText(), tel.getText());
					Nume.setText("");
					Prenume.setText("");
					varsta.setText("");
					mail.setText("");
					tel.setText("");
					switchPanels(panelRezerva);

				}

			}
		});

		butonIntr.setFont(new Font("Times New Roman", Font.BOLD, 30));
		butonIntr.setBounds(496, 302, 135, 36);
		panelInchiriaza.add(butonIntr);

		JLabel labeltitluInch = new JLabel();
		labeltitluInch.setBackground(SystemColor.desktop);
		labeltitluInch.setForeground(SystemColor.desktop);
		labeltitluInch.setHorizontalAlignment(SwingConstants.CENTER);
		labeltitluInch.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		labeltitluInch.setBounds(49, 56, 650, 61);
		panelInchiriaza.add(labeltitluInch);

		JPanel panelContact = new JPanel();
		panelContact.setBackground(SystemColor.inactiveCaption);
		layeredPane.add(panelContact, "name_11589465943700");
		panelContact.setLayout(null);

		JLabel dtcnt = new JLabel(
				"<html>Mai jos aveti informatiile noastre de contact:</html>\r\n");
		dtcnt.setForeground(SystemColor.desktop);
		dtcnt.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		dtcnt.setBounds(51, 23, 655, 106);
		panelContact.add(dtcnt);

		JLabel eml = new JLabel("Email:\r\n");
		eml.setHorizontalAlignment(SwingConstants.CENTER);
		eml.setFont(new Font("Times New Roman", Font.BOLD, 30));
		eml.setBounds(10, 121, 143, 47);
		panelContact.add(eml);

		JLabel tlf = new JLabel("Telefon:");
		tlf.setHorizontalAlignment(SwingConstants.CENTER);
		tlf.setFont(new Font("Times New Roman", Font.BOLD, 30));
		tlf.setBounds(20, 162, 143, 47);
		panelContact.add(tlf);

		JLabel email = new JLabel(c.getContact().get(0));
		email.setBackground(Color.WHITE);
		email.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		email.setBounds(127, 123, 591, 47);
		panelContact.add(email);

		JLabel telefon = new JLabel(c.getContact().get(1));
		telefon.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		telefon.setBounds(154, 164, 585, 47);
		panelContact.add(telefon);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(Color.GRAY);
		textArea.setText("<scrie mesajul>");
	
		 
        // set the border of this component
		
		textArea.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(clicks==0)
				textArea.setText("");
				clicks++;
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		textArea.setBounds(44, 382, 432, 106);
		panelContact.add(textArea);
		
		numeTxt = new JTextField();
		numeTxt.setBounds(218, 311, 217, 40);
		panelContact.add(numeTxt);
		numeTxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Numele dvs:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(51, 312, 175, 24);
		panelContact.add(lblNewLabel);
		
		JButton butonTrimite = new JButton("Trimite!");
		butonTrimite.setForeground(SystemColor.desktop);
		butonTrimite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg=numeTxt.getText()+": \n"+textArea.getText();
				String query="update contact set mesaj='"+msg+"';";
				try {
					PreparedStatement stm=c.connection.prepareStatement(query);
					stm.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				numeTxt.setText("");
				textArea.setText("");
				JOptionPane.showMessageDialog(butonTrimite,"Mesajul a fost trimis!");
			}
		});
		butonTrimite.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		butonTrimite.setBounds(563, 437, 109, 40);
		panelContact.add(butonTrimite);
		
		JLabel lblNewLabel_3 = new JLabel("SCRIE-NE!");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_3.setBounds(290, 258, 143, 31);
		panelContact.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("intrebare*");
		lblNewLabel_5.setForeground(SystemColor.windowBorder);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_5.setBounds(44, 369, 61, 13);
		panelContact.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Adresa:");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_6.setBounds(40, 205, 106, 40);
		panelContact.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Traian Vuia, Nr. 149, 400397 Cluj-Napoca, Rom\u00E2nia");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_7.setBounds(154, 208, 473, 40);
		panelContact.add(lblNewLabel_7);

		JLabel meniu = new JLabel("MENIU");
		meniu.setForeground(SystemColor.inactiveCaptionBorder);
		meniu.setFont(new Font("Times New Roman", Font.BOLD, 35));
		meniu.setBounds(21, 95, 131, 49);
		frame.getContentPane().add(meniu);

		JButton sosiri = new JButton("Sosiri");
		sosiri.setForeground(Color.BLACK);
		sosiri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(panelSosiri);
			}
		});
		sosiri.setFont(new Font("Times New Roman", Font.BOLD, 20));
		sosiri.setBounds(10, 209, 131, 33);
		frame.getContentPane().add(sosiri);

		JLabel num1 = new JLabel("Aeroportul International Avram Iancu");
		num1.setForeground(SystemColor.inactiveCaptionBorder);
		num1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		num1.setBounds(278, 10, 590, 58);
		frame.getContentPane().add(num1);

		JLabel num2 = new JLabel("Cluj-Napoca");
		num2.setForeground(SystemColor.inactiveCaptionBorder);
		num2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		num2.setBounds(416, 61, 251, 49);
		frame.getContentPane().add(num2);

		JButton Acasa = new JButton("Acasa");
		Acasa.setForeground(Color.BLACK);
		Acasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(start);
			}
		});
		Acasa.setFont(new Font("Times New Roman", Font.BOLD, 20));
		Acasa.setBounds(10, 154, 131, 33);
		frame.getContentPane().add(Acasa);

		JButton plecari = new JButton("Plecari");
		plecari.setForeground(Color.BLACK);
		plecari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(panelPlecari);
			}
		});
		plecari.setFont(new Font("Times New Roman", Font.BOLD, 20));
		plecari.setBounds(10, 262, 131, 33);
		frame.getContentPane().add(plecari);

		JButton inchiriaza = new JButton("Inchiriaza");
		inchiriaza.setForeground(Color.BLACK);
		inchiriaza.setVerticalAlignment(SwingConstants.TOP);
		inchiriaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Nume.setText("");
				Prenume.setText("");
				varsta.setText("");
				mail.setText("");
				tel.setText("");
				p = 1;
				labeltitluInch.setText("INSCRIE-TE PENTRU MASINA!");
				switchPanels(panelInchiriaza);
			}
		});
		inchiriaza.setFont(new Font("Times New Roman", Font.BOLD, 20));
		inchiriaza.setBounds(10, 314, 131, 33);
		frame.getContentPane().add(inchiriaza);

		JButton rezerva = new JButton("Rezerva");
		rezerva.setForeground(Color.BLACK);
		rezerva.setFont(new Font("Times New Roman", Font.BOLD, 20));
		rezerva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Nume.setText("");
				Prenume.setText("");
				varsta.setText("");
				mail.setText("");
				tel.setText("");
				p = 2;
				labeltitluInch.setText("INSCRIE-TE PENTRU BILET!");
				switchPanels(panelInchiriaza);
			}
		});
		rezerva.setBounds(10, 367, 131, 33);
		frame.getContentPane().add(rezerva);

		JButton contact = new JButton("Contact");
		contact.setForeground(Color.BLACK);
		contact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(panelContact);
			}
		});
		contact.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contact.setBounds(10, 417, 131, 33);
		frame.getContentPane().add(contact);
		
		JButton btnBack = new JButton("Inapoi");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(adm==1) {
					frame.setVisible(false);
					try {
						new AdminLogat();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					frame.setVisible(false);
					new Pagina1();
				}
				
			}
		});
		btnBack.setForeground(Color.BLACK);
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBack.setBounds(10, 472, 131, 33);
		frame.getContentPane().add(btnBack);
		
		
		

		JLabel fundal = new JLabel("");
		fundal.setIcon(new ImageIcon("D:\\POO\\ProiectAeroport\\Imagini\\imadm.jpg"));
		fundal.setBounds(0, 0, 968, 710);
		frame.getContentPane().add(fundal);
		
		
		
		

	}
}
