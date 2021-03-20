import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
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
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class AdminLogat {

	private JFrame frame;
	JLayeredPane layeredPane = new JLayeredPane();
	private JTextField username;
	private JPasswordField pVeche;
	private JPasswordField pNoua;
	private JPasswordField pNoua2;
	JScrollPane scrollPane;
	JScrollPane scrollPaneMasina;
	JScrollPane scrollPanePersonal;
	JScrollPane scrollPaneAeronave;
	JScrollPane scrollPanelPlecari;
	JScrollPane scrollPanelSosiri;
	ConnectDB c;
	private JTable table;
	DefaultTableModel modelU;
	DefaultTableModel modelM;
	DefaultTableModel modelPersonal;
	DefaultTableModel modelAeronave;
	DefaultTableModel modelPlecari;
	DefaultTableModel modelSosiri;
	DefaultTableModel modelAeronavePersonal;
	JPanel panelUseri;
	JPanel panelMasina;
	JPanel panelAeronava;
	JPanel panelPlecari;
	JPanel panelPersonal;
	private JTextField textEmail;
	private JTextField textTelefon;
	private JTextField textNrLocuri;
	private JTextField textMarca;
	private JTextField textPret;
	private JTextField textAnFabricatie;
	private JTable tableMasina;
	private JTextField textIdMasinaStergere;
	private JTextField textId;
	private JTextField textPretNou;
	private JTable tablePersonal;
	private JTable tableAeronave;
	private JTable tablePlecari;
	private JTable tableSosiri;
	private JTextField textCompanie;
	private JTextField textNume;
	private JTextField textCapacitate;
	private JTextField textAn;
	private JTextField textIDStergPlecare;
	private JTextField textIDStergereAeronava;
	private JTextField textData;
	private JTextField textDestinatie;
	private JTextField textOra;
	private JTextField textZbor;
	private JTextField textEscala;
	private JTextField textPretul;
	private JTextField textTipPlecare;
	private JTextField textIdUpdatePlecare;
	private JTextField textPretPlecare;
	private JTable tableAeronavePersonal;
	private JTextField textIdAeronava;
	private JTextField textNumePersonal;
	private JTextField textPrenumePersonal;
	private JTextField textVarstaPersonal;
	private JTextField textDataAngajare;
	private JTextField textIdStergerePersonal;
	private JTextField textIdPersonalU;
	JTextArea mesajTxt;
	private JTextField textIdPersonalaAU;
	/**
	 * @wbp.nonvisual location=-53,124
	 */
	private final JScrollPane scrollPane_2 = new JScrollPane();
	private JTextField txtRaspunsAdmin;
	private JTextField txtMesajePrimiteDe;

	/**
	 * Launch the application.
	 * 
	 * @throws Exception
	 */

	public AdminLogat() throws Exception {
		initialize();
	}

	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public void schimbareParola(String pV, String pN) {
		try {
			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
			PreparedStatement stm = c.connection.prepareStatement("call schimbare_parola(?,?)");
			stm.setString(1, pV);
			stm.setString(2, pN);
			ResultSet result = stm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void afiseazaMasina() {
		scrollPaneMasina = new JScrollPane();
		scrollPaneMasina.setBounds(597, 61, 327, 487);
		panelMasina.add(scrollPaneMasina);

		tableMasina = new JTable();
		tableMasina.setBackground(new Color(176, 224, 230));
		tableMasina.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableMasina.setEnabled(false);
		modelM = new DefaultTableModel();
		Object[] cM = { "ID", "Marca", "Pret (Lei/Zi)" };
		Object[] rM = new Object[3];
		modelM.setColumnIdentifiers(cM);
		tableMasina.setModel(modelM);
		scrollPaneMasina.setViewportView(tableMasina);

		PreparedStatement statement;
		try {
			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
			statement = c.connection.prepareStatement("Select  idMasina, Marca, pret_zi from masina");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				rM[0] = result.getString("idMasina");
				rM[1] = result.getString("Marca");
				rM[2] = result.getString("pret_zi");
				modelM.addRow(rM);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void tabelUseri() {
		table = new JTable();
		table.setBackground(new Color(176, 196, 222));
		table.setEnabled(false);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		modelU = new DefaultTableModel();
		Object[] cU = { "ID", "Nume Prenume", "Email", "Telefon", "Scop" };
		Object[] rU = new Object[5];
		modelU.setColumnIdentifiers(cU);
		table.setModel(modelU);
		scrollPane.setViewportView(table);

		try {
			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
			PreparedStatement statement = c.connection.prepareStatement("Select * from user_aeroport");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				rU[0] = result.getString("id_User");
				String s = result.getString("Nume") + " " + result.getString("Prenume");
				rU[1] = s;
				rU[2] = result.getString("email");
				rU[3] = result.getString("Telefon");
				if (result.getString("masina_inchiriata") != null)
					rU[4] = "masina";

				else if (result.getString("destinatie_rezervata") != null)
					rU[4] = "bilet";
				else
					rU[4] = "nimic";

				modelU.addRow(rU);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void tabelAeronave() {
		try {
			scrollPaneAeronave = new JScrollPane();
			scrollPaneAeronave.setBounds(10, 68, 506, 480);
			panelAeronava.add(scrollPaneAeronave);

			tableAeronave = new JTable();
			tableAeronave.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			tableAeronave.setBackground(Color.LIGHT_GRAY);
			modelAeronave = new DefaultTableModel();
			Object[] cAeronave = { "IDAeronava", "Nume-Companie", "Capacitate", "An fabricatie" };
			Object[] rAeronave = new Object[4];
			modelAeronave.setColumnIdentifiers(cAeronave);
			tableAeronave.setModel(modelAeronave);
			scrollPaneAeronave.setViewportView(tableAeronave);

			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
			PreparedStatement statement = c.connection.prepareStatement("Select * from aeronava where id_aeronava>1");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				rAeronave[0] = result.getString("id_aeronava");
				String s = result.getString("Nume") + " - " + result.getString("companie");
				rAeronave[1] = s;
				rAeronave[2] = result.getString("capacitate");
				rAeronave[3] = result.getString("an_fabricatie");

				modelAeronave.addRow(rAeronave);

			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	void tabelPlecari() {
		scrollPanelPlecari = new JScrollPane();
		scrollPanelPlecari.setBounds(506, 73, 418, 475);
		panelPlecari.add(scrollPanelPlecari);

		tablePlecari = new JTable();
		tablePlecari.setBackground(Color.LIGHT_GRAY);
		tablePlecari.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tablePlecari.setForeground(Color.BLACK);
		tablePlecari.setEnabled(false);
		modelPlecari = new DefaultTableModel();
		Object[] cPlecari = { "IDPlecare", "Destinatie", "Data", "Ora", "Pret" };
		Object[] rPlecari = new Object[5];
		modelPlecari.setColumnIdentifiers(cPlecari);
		tablePlecari.setModel(modelPlecari);
		scrollPanelPlecari.setViewportView(tablePlecari);

		try {
			// c.connection = DriverManager.getConnection(c.url, c.username, c.password);
			PreparedStatement statement = c.connection.prepareStatement("Select * from plecari");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				rPlecari[0] = result.getString("id_plecare");
				rPlecari[1] = result.getString("destinatie");
				rPlecari[2] = result.getString("data_plecarii");
				rPlecari[3] = result.getString("ora_plecarii");
				rPlecari[4] = result.getString("pret_bilet");

				modelPlecari.addRow(rPlecari);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	void tabelPersonal() {
		scrollPanePersonal = new JScrollPane();
		scrollPanePersonal.setBounds(571, 51, 353, 205);
		panelPersonal.add(scrollPanePersonal);

		
		tablePersonal = new JTable();
		tablePersonal.setBackground(Color.LIGHT_GRAY);
		tablePersonal.setEnabled(false);
		tablePersonal.setForeground(Color.BLACK);
		tablePersonal.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		modelPersonal = new DefaultTableModel();
		Object[] cPersonal = { "IDPesonal", "Nume Prenume", "Functie" };
		Object[] rPersonal = new Object[3];
		modelPersonal.setColumnIdentifiers(cPersonal);
		tablePersonal.setModel(modelPersonal);
		scrollPanePersonal.setViewportView(tablePersonal);

		try {
			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
			PreparedStatement statement = c.connection.prepareStatement("Select * from personal");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				rPersonal[0] = result.getString("id_personal");
				String s = result.getString("nume") + " " + result.getString("prenume");
				rPersonal[1] = s;
				rPersonal[2] = result.getString("functie");

				modelPersonal.addRow(rPersonal);

			}

		} catch (Exception e) {
			System.out.println(e);
		}

		
	}

	private void initialize() throws Exception {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setVisible(true);
		frame.setBounds(100, 100, 967, 689);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		layeredPane.setBounds(10, 81, 934, 558);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		c = null;
		try {
			c = new ConnectDB();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel panelAcasa = new JPanel();
		panelAcasa.setBackground(new Color(.47f, 0.31f, 0.67f, 0.2f));
		layeredPane.add(panelAcasa, "name_6288052649500");
		Border b  = BorderFactory.createLineBorder(Color.white, 5);
		 
       
		panelAcasa.setBorder(b);
		panelAcasa.setLayout(null);

		JPanel panelScimbareParola = new JPanel();
		panelScimbareParola.setBounds(518, 10, 406, 538);
		panelAcasa.add(panelScimbareParola);
		panelScimbareParola.setLayout(null);

		JLabel tltParola = new JLabel("Schimbare Parola");
		tltParola.setHorizontalAlignment(SwingConstants.CENTER);
		tltParola.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tltParola.setBounds(10, 10, 386, 42);
		panelScimbareParola.add(tltParola);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(10, 77, 386, 25);
		panelScimbareParola.add(lblUsername);

		username = new JTextField();
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setBounds(114, 102, 170, 31);
		panelScimbareParola.add(username);
		username.setColumns(10);

		JLabel lblParolaVeche = new JLabel("Parola veche");
		lblParolaVeche.setHorizontalAlignment(SwingConstants.CENTER);
		lblParolaVeche.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblParolaVeche.setBounds(10, 146, 386, 25);
		panelScimbareParola.add(lblParolaVeche);

		pVeche = new JPasswordField();
		pVeche.setHorizontalAlignment(SwingConstants.CENTER);
		pVeche.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pVeche.setBounds(114, 170, 180, 31);
		panelScimbareParola.add(pVeche);

		JLabel lblParolaNoua = new JLabel("Parola noua");
		lblParolaNoua.setHorizontalAlignment(SwingConstants.CENTER);
		lblParolaNoua.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblParolaNoua.setBounds(10, 212, 386, 25);
		panelScimbareParola.add(lblParolaNoua);

		pNoua = new JPasswordField();
		pNoua.setHorizontalAlignment(SwingConstants.CENTER);
		pNoua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pNoua.setBounds(114, 235, 180, 31);
		panelScimbareParola.add(pNoua);

		JLabel lblRepetareParolaNoua = new JLabel("Repetare parola noua");
		lblRepetareParolaNoua.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepetareParolaNoua.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblRepetareParolaNoua.setBounds(10, 278, 386, 25);
		panelScimbareParola.add(lblRepetareParolaNoua);

		pNoua2 = new JPasswordField();
		pNoua2.setHorizontalAlignment(SwingConstants.CENTER);
		pNoua2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pNoua2.setBounds(114, 303, 180, 31);
		panelScimbareParola.add(pNoua2);

		JButton btnSchimbare = new JButton("Schimbare");/// Aici schimb parola la admin!!!
		btnSchimbare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (pNoua.getText().equals(pNoua2.getText()) && c.getAdmin().get(1).equals(pVeche.getText())) {
						schimbareParola(pVeche.getText(), pNoua.getText());
						pNoua2.setText("");
						pNoua.setText("");
						pVeche.setText("");
						username.setText("");
						new ErrrLogare("Parola schimbata cu succes!");
					} else if (!pNoua.getText().equals(pNoua2.getText()))
						new ErrrLogare("Parolele nu corespund!");
					else
						new ErrrLogare("Parola veche nu corespunde!");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSchimbare.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnSchimbare.setBounds(138, 386, 130, 31);
		panelScimbareParola.add(btnSchimbare);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(47, 127, 429, 200);
		panelAcasa.add(scrollPane_1);
		
		txtMesajePrimiteDe = new JTextField();
		txtMesajePrimiteDe.setBackground(SystemColor.inactiveCaption);
		txtMesajePrimiteDe.setForeground(new Color(0, 0, 0));
		txtMesajePrimiteDe.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtMesajePrimiteDe.setText("                Mesaje primite de la utilizatori:");
		scrollPane_1.setColumnHeaderView(txtMesajePrimiteDe);
		txtMesajePrimiteDe.setColumns(10);
		
		mesajTxt = new JTextArea();
		mesajTxt.setEditable(false);
		scrollPane_1.setViewportView(mesajTxt);
		mesajTxt.setTabSize(6);
		mesajTxt.setForeground(Color.BLACK);
		mesajTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mesajTxt.setText("\r\n");
		mesajTxt.setText(createTextMesaj());
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(125, 355, 262, 145);
		panelAcasa.add(scrollPane_3);
		
		JTextArea txtrRaspunsAdmin = new JTextArea();
		scrollPane_3.setViewportView(txtrRaspunsAdmin);
		txtrRaspunsAdmin.setText("<scrie ceva>");
		txtrRaspunsAdmin.addMouseListener(new MouseListener() {
			int clicks=0;
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(clicks==0)
					txtrRaspunsAdmin.setText("");
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
		txtrRaspunsAdmin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtrRaspunsAdmin.setForeground(Color.GRAY);
		
		JLabel lblNewLabel_3 = new JLabel("CHAT");
		lblNewLabel_3.setBackground(SystemColor.controlShadow);
		lblNewLabel_3.setBounds(197, 82, 156, 47);
		lblNewLabel_3.setForeground(SystemColor.controlHighlight);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		panelAcasa.add(lblNewLabel_3);
		
		txtRaspunsAdmin = new JTextField();
		txtRaspunsAdmin.setBackground(SystemColor.inactiveCaption);
		txtRaspunsAdmin.setBounds(125, 326, 262, 30);
		panelAcasa.add(txtRaspunsAdmin);
		txtRaspunsAdmin.setEditable(false);
		txtRaspunsAdmin.setForeground(SystemColor.windowText);
		txtRaspunsAdmin.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtRaspunsAdmin.setText("             Raspuns admin:");
		txtRaspunsAdmin.setColumns(10);
		
		JPanel panelRaspnd = new JPanel();
		panelRaspnd.setBounds(125, 500, 262, 30);
		panelAcasa.add(panelRaspnd);
		panelRaspnd.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Raspunde!");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtrRaspunsAdmin.getText().isEmpty()) {
					new ErrrLogare("Campul este gol, mesajul nu s-a putut trimite!");
				}
				else new ErrrLogare("Raspunsul a fost trimis catre utilizator ");
				txtrRaspunsAdmin.setText("");
			}
		});
		btnNewButton_1.setBounds(131, 0, 133, 29);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panelRaspnd.add(btnNewButton_1);

		panelMasina = new JPanel();
		layeredPane.add(panelMasina, "name_4564630398200");
		panelMasina.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Masini");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(206, 10, 205, 36);
		panelMasina.add(lblNewLabel_1);

		JLabel lblAdaugare = new JLabel("Adugare Masina");
		lblAdaugare.setForeground(new Color(0, 0, 0));
		lblAdaugare.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdaugare.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblAdaugare.setBounds(10, 61, 205, 36);
		panelMasina.add(lblAdaugare);

		JLabel lblNumarLocuri = new JLabel("Numar locuri");
		lblNumarLocuri.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumarLocuri.setForeground(Color.BLACK);
		lblNumarLocuri.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNumarLocuri.setBounds(10, 107, 205, 36);
		panelMasina.add(lblNumarLocuri);

		JLabel lblPretleizi = new JLabel("Marca");
		lblPretleizi.setHorizontalAlignment(SwingConstants.CENTER);
		lblPretleizi.setForeground(Color.BLACK);
		lblPretleizi.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPretleizi.setBounds(10, 166, 205, 36);
		panelMasina.add(lblPretleizi);

		JLabel lblPretleizi_1 = new JLabel("Pret (Lei/Zi)");
		lblPretleizi_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPretleizi_1.setForeground(Color.BLACK);
		lblPretleizi_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPretleizi_1.setBounds(10, 222, 205, 36);
		panelMasina.add(lblPretleizi_1);

		JLabel lblAnFabricatie = new JLabel("An fabricatie");
		lblAnFabricatie.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnFabricatie.setForeground(Color.BLACK);
		lblAnFabricatie.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAnFabricatie.setBounds(10, 284, 205, 36);
		panelMasina.add(lblAnFabricatie);

		textNrLocuri = new JTextField();
		textNrLocuri.setHorizontalAlignment(SwingConstants.CENTER);
		textNrLocuri.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		textNrLocuri.setBounds(83, 137, 62, 32);
		panelMasina.add(textNrLocuri);
		textNrLocuri.setColumns(10);

		textMarca = new JTextField();
		textMarca.setHorizontalAlignment(SwingConstants.CENTER);
		textMarca.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		textMarca.setColumns(10);
		textMarca.setBounds(10, 194, 205, 32);
		panelMasina.add(textMarca);

		textPret = new JTextField();
		textPret.setHorizontalAlignment(SwingConstants.CENTER);
		textPret.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		textPret.setColumns(10);
		textPret.setBounds(10, 254, 205, 32);
		panelMasina.add(textPret);

		textAnFabricatie = new JTextField();
		textAnFabricatie.setHorizontalAlignment(SwingConstants.CENTER);
		textAnFabricatie.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		textAnFabricatie.setColumns(10);
		textAnFabricatie.setBounds(10, 311, 205, 32);
		panelMasina.add(textAnFabricatie);

		JButton btnAdaugaM = new JButton("Adauga");
		btnAdaugaM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.adaugaMasina(textNrLocuri.getText(), textMarca.getText(), textPret.getText(),
						textAnFabricatie.getText());
				textNrLocuri.setText("");
				textMarca.setText("");
				textPret.setText("");
				textAnFabricatie.setText("");
				new ErrrLogare("Masina a fost adaugata!");
				afiseazaMasina();
			}
		});
		btnAdaugaM.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		btnAdaugaM.setBounds(62, 364, 106, 36);
		panelMasina.add(btnAdaugaM);
		/////////////////////////////
		JLabel lblStergere = new JLabel("Stergere Masina");
		lblStergere.setForeground(new Color(0, 0, 0));
		lblStergere.setHorizontalAlignment(SwingConstants.CENTER);
		lblStergere.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblStergere.setBounds(341, 56, 246, 36);
		panelMasina.add(lblStergere);

		JLabel lblIdMasina = new JLabel("ID Masina");
		lblIdMasina.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdMasina.setForeground(Color.BLACK);
		lblIdMasina.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblIdMasina.setBounds(341, 107, 246, 36);
		panelMasina.add(lblIdMasina);

		textIdMasinaStergere = new JTextField();
		textIdMasinaStergere.setHorizontalAlignment(SwingConstants.CENTER);
		textIdMasinaStergere.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textIdMasinaStergere.setBounds(435, 137, 62, 32);
		panelMasina.add(textIdMasinaStergere);
		textIdMasinaStergere.setColumns(10);

		JButton btnSterge = new JButton("Sterge");////////////////////////////////////////////////// STERG Masina
		btnSterge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					c.connection = DriverManager.getConnection(c.url, c.username, c.password);
					String query = "call deleteMasina(" + textIdMasinaStergere.getText() + ");";
					Statement statement = c.connection.createStatement();
					statement.executeQuery(query);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textIdMasinaStergere.setText("");
				afiseazaMasina();

			}
		});
		btnSterge.setForeground(Color.BLACK);
		btnSterge.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		btnSterge.setBounds(413, 192, 106, 36);
		panelMasina.add(btnSterge);

		JPanel panelUpdateMasina = new JPanel();
		panelUpdateMasina.setBackground(Color.LIGHT_GRAY);
		panelUpdateMasina.setBounds(366, 254, 193, 270);
		panelMasina.add(panelUpdateMasina);
		panelUpdateMasina.setLayout(null);

		JLabel lblUpdatePret = new JLabel("Update Pret");
		lblUpdatePret.setBounds(37, 5, 118, 29);
		panelUpdateMasina.add(lblUpdatePret);
		lblUpdatePret.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdatePret.setForeground(Color.BLACK);
		lblUpdatePret.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));

		JLabel lblIdMasinaUpdate = new JLabel("ID Masina");
		lblIdMasinaUpdate.setBounds(-31, 44, 246, 36);
		panelUpdateMasina.add(lblIdMasinaUpdate);
		lblIdMasinaUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdMasinaUpdate.setForeground(Color.BLACK);
		lblIdMasinaUpdate.setFont(new Font("Times New Roman", Font.BOLD, 20));

		textId = new JTextField();
		textId.setBounds(58, 79, 62, 32);
		panelUpdateMasina.add(textId);
		textId.setHorizontalAlignment(SwingConstants.CENTER);
		textId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textId.setColumns(10);

		JLabel lblPretNou = new JLabel("Pret Nou");
		lblPretNou.setBounds(-31, 116, 246, 36);
		panelUpdateMasina.add(lblPretNou);
		lblPretNou.setHorizontalAlignment(SwingConstants.CENTER);
		lblPretNou.setForeground(Color.BLACK);
		lblPretNou.setFont(new Font("Times New Roman", Font.BOLD, 20));

		textPretNou = new JTextField();
		textPretNou.setBounds(37, 148, 110, 32);
		panelUpdateMasina.add(textPretNou);
		textPretNou.setHorizontalAlignment(SwingConstants.CENTER);
		textPretNou.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textPretNou.setColumns(10);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					c.connection = DriverManager.getConnection(c.url, c.username, c.password);

					PreparedStatement stm = c.connection.prepareStatement("call updateMasina(?,?)");
					stm.setString(1, textId.getText());
					stm.setString(2, textPretNou.getText());
					ResultSet result = stm.executeQuery();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textId.setText("");
				textPretNou.setText("");
				afiseazaMasina();
			}
		});
		btnUpdate.setBounds(37, 224, 108, 36);
		panelUpdateMasina.add(btnUpdate);
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		/////////////////////////////////////////////////////////////////////////// tabel
		/////////////////////////////////////////////////////////////////////////// masina
//		scrollPaneMasina = new JScrollPane();
//		scrollPaneMasina.setBounds(597, 61, 327, 259);
//		panelMasina.add(scrollPaneMasina);
//
//		tableMasina = new JTable();
//		tableMasina.setBackground(new Color(176, 224, 230));
//		tableMasina.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		tableMasina.setEnabled(false);
//		modelM = new DefaultTableModel();
//		Object[] cM = { "ID", "Marca", "Pret (Lei/Zi)" };
//		Object[] rM = new Object[3];
//		modelM.setColumnIdentifiers(cM);
//		tableMasina.setModel(modelM);
//		scrollPaneMasina.setViewportView(tableMasina);
//
//		// c.connection = DriverManager.getConnection(c.url, c.username, c.password);
//		PreparedStatement statement = c.connection.prepareStatement("Select  idMasina, Marca, pret_zi from masina");
//		ResultSet result = statement.executeQuery();
//		while (result.next()) {
//			rM[0]=result.getString("idMasina");
//			rM[1]=result.getString("Marca");
//			rM[2]=result.getString("pret_zi");
//			modelM.addRow(rM);
//
//		}
		afiseazaMasina();

		panelPlecari = new JPanel();
		layeredPane.add(panelPlecari, "name_4655496574100");
		panelPlecari.setLayout(null);

		///////////// aici fac pentru
		///////////// plecari//////////////////////////////////pppppppppppppppppppppppppplllllllllllllllllleeeeeeeeeeeeccccccccccccccccaaaaaaaaaaaarrrrrrrrriiiiiiiiii
//		scrollPanelPlecari = new JScrollPane();
//		scrollPanelPlecari.setBounds(506, 73, 418, 475);
//		panelPlecari.add(scrollPanelPlecari);
//
//		tablePlecari = new JTable();
//		tablePlecari.setBackground(Color.LIGHT_GRAY);
//		tablePlecari.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		tablePlecari.setForeground(Color.BLACK);
//		tablePlecari.setEnabled(false);
//		modelPlecari = new DefaultTableModel();
//		Object[] cPlecari = { "IDPlecare", "Destinatie", "Data", "Ora", "Pret" };
//		Object[] rPlecari = new Object[5];
//		modelPlecari.setColumnIdentifiers(cPlecari);
//		tablePlecari.setModel(modelPlecari);
//		scrollPanelPlecari.setViewportView(tablePlecari);
		tabelPlecari();

		JLabel lblPlecari = new JLabel("Plecari");
		lblPlecari.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblPlecari.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlecari.setBounds(506, 10, 418, 53);
		panelPlecari.add(lblPlecari);

		JLabel lblStergerePlecare = new JLabel("Stergere\r\n");
		lblStergerePlecare.setForeground(Color.BLACK);
		lblStergerePlecare.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblStergerePlecare.setHorizontalAlignment(SwingConstants.CENTER);
		lblStergerePlecare.setBounds(335, 73, 150, 27);
		panelPlecari.add(lblStergerePlecare);

		textIDStergPlecare = new JTextField("");
		textIDStergPlecare.setBounds(384, 120, 52, 27);
		panelPlecari.add(textIDStergPlecare);
		textIDStergPlecare.setColumns(10);

		JLabel lblStergerePlc = new JLabel("ID");
		lblStergerePlc.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblStergerePlc.setHorizontalAlignment(SwingConstants.CENTER);
		lblStergerePlc.setBounds(335, 98, 150, 27);
		panelPlecari.add(lblStergerePlc);

		JButton btnStergePlecari = new JButton("Sterge");
		btnStergePlecari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textIDStergPlecare.getText().equals(""))
					new ErrrLogare("trebuie completata casuta ID!");
				else {
					c.stergePlecare(textIDStergPlecare.getText());
					new ErrrLogare("Plecarea s-a sters!");
				}

				textIDStergPlecare.setText("");
				tabelPlecari();

			}
		});
		btnStergePlecari.setForeground(Color.BLACK);
		btnStergePlecari.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnStergePlecari.setBounds(356, 157, 104, 32);
		panelPlecari.add(btnStergePlecari);

		JLabel lblAdaugarePlecare = new JLabel("Adaugare plecare");
		lblAdaugarePlecare.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdaugarePlecare.setForeground(Color.BLACK);
		lblAdaugarePlecare.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblAdaugarePlecare.setBounds(10, 76, 240, 27);
		panelPlecari.add(lblAdaugarePlecare);

		JLabel lblData = new JLabel("Alege data plecarii (an-luna-zi):");
		lblData.setForeground(Color.BLACK);
		lblData.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblData.setBounds(10, 107, 259, 27);
		panelPlecari.add(lblData);

		textData = new JTextField("");
		textData.setHorizontalAlignment(SwingConstants.CENTER);
		textData.setToolTipText("AN-LUNA-ZI");
		textData.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textData.setColumns(10);
		textData.setBounds(56, 130, 150, 27);
		panelPlecari.add(textData);

		JLabel lblDestinatie = new JLabel("Destinatie");
		lblDestinatie.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestinatie.setForeground(Color.BLACK);
		lblDestinatie.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblDestinatie.setBounds(10, 157, 259, 27);
		panelPlecari.add(lblDestinatie);

		textDestinatie = new JTextField("");
		textDestinatie.setToolTipText("");
		textDestinatie.setHorizontalAlignment(SwingConstants.CENTER);
		textDestinatie.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textDestinatie.setColumns(10);
		textDestinatie.setBounds(56, 180, 150, 27);
		panelPlecari.add(textDestinatie);

		JLabel lblora = new JLabel("Ora plecarii (ora:minute:secunde):");
		lblora.setHorizontalAlignment(SwingConstants.CENTER);
		lblora.setForeground(Color.BLACK);
		lblora.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblora.setBounds(10, 210, 295, 27);
		panelPlecari.add(lblora);

		textOra = new JTextField("");
		textOra.setToolTipText("ora:minute:secunde");
		textOra.setHorizontalAlignment(SwingConstants.CENTER);
		textOra.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textOra.setColumns(10);
		textOra.setBounds(56, 233, 150, 27);
		panelPlecari.add(textOra);

		JLabel lblzborDirect = new JLabel("Zbor direct (da/nu):");
		lblzborDirect.setHorizontalAlignment(SwingConstants.CENTER);
		lblzborDirect.setForeground(Color.BLACK);
		lblzborDirect.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblzborDirect.setBounds(10, 270, 259, 27);
		panelPlecari.add(lblzborDirect);

		textZbor = new JTextField("");
		textZbor.setToolTipText("");
		textZbor.setHorizontalAlignment(SwingConstants.CENTER);
		textZbor.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textZbor.setColumns(10);
		textZbor.setBounds(56, 293, 150, 27);
		panelPlecari.add(textZbor);

		JLabel lblEscala = new JLabel("Escala");
		lblEscala.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscala.setForeground(Color.BLACK);
		lblEscala.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblEscala.setBounds(10, 319, 259, 27);
		panelPlecari.add(lblEscala);

		textEscala = new JTextField("");
		textEscala.setToolTipText("");
		textEscala.setHorizontalAlignment(SwingConstants.CENTER);
		textEscala.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textEscala.setColumns(10);
		textEscala.setBounds(56, 342, 150, 27);
		panelPlecari.add(textEscala);

		JLabel lblPret = new JLabel("Pret bilet:");
		lblPret.setHorizontalAlignment(SwingConstants.CENTER);
		lblPret.setForeground(Color.BLACK);
		lblPret.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblPret.setBounds(10, 379, 259, 27);
		panelPlecari.add(lblPret);

		textPretul = new JTextField("");
		textPretul.setToolTipText("");
		textPretul.setHorizontalAlignment(SwingConstants.CENTER);
		textPretul.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textPretul.setColumns(10);
		textPretul.setBounds(56, 402, 150, 27);
		panelPlecari.add(textPretul);

		textTipPlecare = new JTextField("");
		textTipPlecare.setToolTipText("");
		textTipPlecare.setHorizontalAlignment(SwingConstants.CENTER);
		textTipPlecare.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textTipPlecare.setColumns(10);
		textTipPlecare.setBounds(56, 462, 150, 27);
		panelPlecari.add(textTipPlecare);

		JLabel lblTipPlecareinternextern = new JLabel("Tip plecare(intern/extern):");
		lblTipPlecareinternextern.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipPlecareinternextern.setForeground(Color.BLACK);
		lblTipPlecareinternextern.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblTipPlecareinternextern.setBounds(10, 439, 259, 27);
		panelPlecari.add(lblTipPlecareinternextern);

		JButton btnVeziAeronave = new JButton("Next");
		btnVeziAeronave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textData.getText().equals("") || textDestinatie.getText().equals("") || textOra.getText().equals("")
						|| textZbor.getText().equals("") || textPretul.getText().equals("")
						|| textTipPlecare.getText().equals("")) {
					new ErrrLogare("Trebuie completate toate campurile!");
				} else {

					new AdaugarePlecare(textData.getText(), textDestinatie.getText(), textOra.getText(),
							textZbor.getText(), textEscala.getText(), textPretul.getText(), textTipPlecare.getText());
				}
				textData.setText("");
				textDestinatie.setText("");
				textOra.setText("");
				textZbor.setText("");
				textEscala.setText("");
				textPretul.setText("");
				textTipPlecare.setText("");
				tabelPlecari();
			}
		});
		btnVeziAeronave.setForeground(Color.BLACK);
		btnVeziAeronave.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnVeziAeronave.setBounds(83, 499, 84, 32);
		panelPlecari.add(btnVeziAeronave);

		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabelPlecari();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(506, 50, 104, 27);
		panelPlecari.add(btnNewButton);

		JLabel lblUpdate = new JLabel("Update Pret");
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setForeground(Color.BLACK);
		lblUpdate.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblUpdate.setBounds(304, 290, 181, 27);
		panelPlecari.add(lblUpdate);

		JLabel lblIdPlecare = new JLabel("ID plecare");
		lblIdPlecare.setForeground(Color.BLACK);
		lblIdPlecare.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdPlecare.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblIdPlecare.setBounds(310, 320, 150, 27);
		panelPlecari.add(lblIdPlecare);

		textIdUpdatePlecare = new JTextField("");
		textIdUpdatePlecare.setColumns(10);
		textIdUpdatePlecare.setBounds(359, 342, 52, 27);
		panelPlecari.add(textIdUpdatePlecare);

		JLabel lblPreNou = new JLabel("Pret Nou");
		lblPreNou.setForeground(Color.BLACK);
		lblPreNou.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreNou.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPreNou.setBounds(310, 379, 150, 27);
		panelPlecari.add(lblPreNou);

		textPretPlecare = new JTextField("");
		textPretPlecare.setColumns(10);
		textPretPlecare.setBounds(359, 403, 52, 27);
		panelPlecari.add(textPretPlecare);

		JButton btnUpdatePlecare = new JButton("Update");
		btnUpdatePlecare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textIdUpdatePlecare.getText().equals("") || textPretPlecare.getText().equals(""))
					new ErrrLogare("Completati toate casutele!");
				else {
					c.updatePlecari(textIdUpdatePlecare.getText(), textPretPlecare.getText());
					textIdUpdatePlecare.setText("");
					textPretPlecare.setText("");
				}
				tabelPlecari();
			}
		});
		btnUpdatePlecare.setForeground(Color.BLACK);
		btnUpdatePlecare.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnUpdatePlecare.setBounds(335, 436, 104, 32);
		panelPlecari.add(btnUpdatePlecare);

//		try {
//			// c.connection = DriverManager.getConnection(c.url, c.username, c.password);
//			PreparedStatement statement = c.connection.prepareStatement("Select * from plecari");
//			ResultSet result = statement.executeQuery();
//			while (result.next()) {
//				rPlecari[0] = result.getString("id_plecare");
//				rPlecari[1] = result.getString("destinatie");
//				rPlecari[2] = result.getString("data_plecarii");
//				rPlecari[3] = result.getString("ora_plecarii");
//				rPlecari[4] = result.getString("pret_bilet");
//
//				modelPlecari.addRow(rPlecari);
//
//			}
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}

		////////////////////////////////////////////////////////////////////////

		JPanel panelSosiri = new JPanel();
		layeredPane.add(panelSosiri, "name_4703226730600");
		panelSosiri.setLayout(null);

//////////////////////////////////aici fac pentru sosiriiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii////////////////////
		scrollPanelSosiri = new JScrollPane();
		scrollPanelSosiri.setBounds(553, 70, 371, 478);
		panelSosiri.add(scrollPanelSosiri);

		tableSosiri = new JTable();
		tableSosiri.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableSosiri.setBackground(Color.LIGHT_GRAY);
		tableSosiri.setForeground(Color.BLACK);
		tableSosiri.setEnabled(false);
		modelSosiri = new DefaultTableModel();
		Object[] cSosiri = { "IDSosire", "De la", "Data", "Ora" };
		Object[] rSosiri = new Object[4];
		modelSosiri.setColumnIdentifiers(cSosiri);
		tableSosiri.setModel(modelSosiri);
		scrollPanelSosiri.setViewportView(tableSosiri);

		JLabel lblSosiri = new JLabel("Sosiri");
		lblSosiri.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblSosiri.setForeground(Color.BLACK);
		lblSosiri.setHorizontalAlignment(SwingConstants.CENTER);
		lblSosiri.setBounds(553, 10, 371, 53);
		panelSosiri.add(lblSosiri);

		try {
			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
			PreparedStatement statement = c.connection.prepareStatement("Select * from sosiri");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				rSosiri[0] = result.getString("id_sosiri");
				rSosiri[1] = result.getString("de_la");
				rSosiri[2] = result.getString("data_sosire");
				rSosiri[3] = result.getString("ora_sosire");

				modelSosiri.addRow(rSosiri);

			}

		} catch (Exception e) {
			System.out.println(e);
		}

		////////////////////////////////////////////////////

		panelAeronava = new JPanel();
		panelAeronava.setBackground(Color.WHITE);
		layeredPane.add(panelAeronava, "name_4719852952300");
		panelAeronava.setLayout(null);
		////// aici fac pentru
		////// Aeronaveeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaeronave

		tabelAeronave();

		JLabel lblTAeronava = new JLabel("Aeronave");
		lblTAeronava.setForeground(Color.BLACK);
		lblTAeronava.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblTAeronava.setHorizontalAlignment(SwingConstants.CENTER);
		lblTAeronava.setBounds(10, 10, 506, 48);
		panelAeronava.add(lblTAeronava);

		JLabel lblAdugare = new JLabel("Adaugare aeronav\u0103");
		lblAdugare.setForeground(Color.BLACK);
		lblAdugare.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblAdugare.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdugare.setBounds(526, 10, 398, 32);
		panelAeronava.add(lblAdugare);

		JLabel lblCompanie = new JLabel("Companie");
		lblCompanie.setForeground(Color.BLACK);
		lblCompanie.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompanie.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblCompanie.setBounds(526, 52, 198, 19);
		panelAeronava.add(lblCompanie);

		textCompanie = new JTextField("");
		textCompanie.setForeground(Color.BLACK);
		textCompanie.setHorizontalAlignment(SwingConstants.CENTER);
		textCompanie.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textCompanie.setBounds(549, 69, 167, 25);
		panelAeronava.add(textCompanie);
		textCompanie.setColumns(10);

		JLabel lblNume = new JLabel("Nume");
		lblNume.setHorizontalAlignment(SwingConstants.CENTER);
		lblNume.setForeground(Color.BLACK);
		lblNume.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNume.setBounds(726, 52, 198, 19);
		panelAeronava.add(lblNume);

		textNume = new JTextField("");
		textNume.setHorizontalAlignment(SwingConstants.CENTER);
		textNume.setForeground(Color.BLACK);
		textNume.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textNume.setColumns(10);
		textNume.setBounds(750, 69, 152, 25);
		panelAeronava.add(textNume);

		JLabel lblCapacitate = new JLabel("Capacitate");
		lblCapacitate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapacitate.setForeground(Color.BLACK);
		lblCapacitate.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblCapacitate.setBounds(526, 104, 198, 19);
		panelAeronava.add(lblCapacitate);

		textCapacitate = new JTextField("");
		textCapacitate.setHorizontalAlignment(SwingConstants.CENTER);
		textCapacitate.setForeground(Color.BLACK);
		textCapacitate.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textCapacitate.setColumns(10);
		textCapacitate.setBounds(564, 124, 123, 25);
		panelAeronava.add(textCapacitate);

		JLabel lblNume_1_1 = new JLabel("An Fabricatie");
		lblNume_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNume_1_1.setForeground(Color.BLACK);
		lblNume_1_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNume_1_1.setBounds(726, 104, 198, 19);
		panelAeronava.add(lblNume_1_1);

		textAn = new JTextField("");
		textAn.setHorizontalAlignment(SwingConstants.CENTER);
		textAn.setForeground(Color.BLACK);
		textAn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textAn.setColumns(10);
		textAn.setBounds(768, 124, 118, 25);
		panelAeronava.add(textAn);

		JButton btnAdauga = new JButton("Adauga");
		btnAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textCompanie.getText().equals("") || textNume.getText().equals("")
						|| textCapacitate.getText().equals("") || textAn.getText().equals("")) {
					new ErrrLogare("Trebuie completate toate campurile!");
				} else {
					c.adaugaAeronava(textCompanie.getText(), textNume.getText(), textCapacitate.getText(),
							textAn.getText());
				}
				textCompanie.setText("");
				textNume.setText("");
				textCapacitate.setText("");
				textAn.setText("");
				tabelAeronave();
			}
		});
		btnAdauga.setForeground(Color.BLACK);
		btnAdauga.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnAdauga.setBounds(678, 159, 105, 32);
		panelAeronava.add(btnAdauga);

		JLabel lblStergereAeronava = new JLabel("Stergere aeronava");
		lblStergereAeronava.setHorizontalAlignment(SwingConstants.CENTER);
		lblStergereAeronava.setForeground(Color.BLACK);
		lblStergereAeronava.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblStergereAeronava.setBounds(526, 287, 398, 32);
		panelAeronava.add(lblStergereAeronava);

		JLabel lblIdaeronava = new JLabel("IDAeronava");
		lblIdaeronava.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdaeronava.setForeground(Color.BLACK);
		lblIdaeronava.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblIdaeronava.setBounds(613, 329, 198, 19);
		panelAeronava.add(lblIdaeronava);

		textIDStergereAeronava = new JTextField("");
		textIDStergereAeronava.setHorizontalAlignment(SwingConstants.CENTER);
		textIDStergereAeronava.setForeground(Color.BLACK);
		textIDStergereAeronava.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textIDStergereAeronava.setColumns(10);
		textIDStergereAeronava.setBounds(623, 346, 167, 25);
		panelAeronava.add(textIDStergereAeronava);

		JButton btnStergeAeronava = new JButton("Sterge");
		btnStergeAeronava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textIDStergereAeronava.getText().equals(""))
					new ErrrLogare("Trebuie pus ID-ul!");
				else {
					c.stergeAeronava(textIDStergereAeronava.getText());
					new ErrrLogare("Aeronava s-a sters!");
				}
				textIDStergereAeronava.setText("");
				tabelAeronave();
			}
		});
		btnStergeAeronava.setForeground(Color.BLACK);
		btnStergeAeronava.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnStergeAeronava.setBounds(663, 392, 105, 32);
		panelAeronava.add(btnStergeAeronava);

///////////////////////////////////////////////////////////////
		panelPersonal = new JPanel();
		layeredPane.add(panelPersonal, "name_4723356622100");
		panelPersonal.setLayout(null);
		tabelPersonal();
		///// aici fac tabelul pentru
		///// Personalppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp
//		scrollPanePersonal = new JScrollPane();
//		scrollPanePersonal.setBounds(571, 51, 353, 205);
//		panelPersonal.add(scrollPanePersonal);
//
//		tablePersonal = new JTable();
//		tablePersonal.setBackground(Color.LIGHT_GRAY);
//		tablePersonal.setEnabled(false);
//		tablePersonal.setForeground(Color.BLACK);
//		tablePersonal.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		modelPersonal = new DefaultTableModel();
//		Object[] cPersonal = { "IDPesonal", "Nume Prenume", "Functie" };
//		Object[] rPersonal = new Object[3];
//		modelPersonal.setColumnIdentifiers(cPersonal);
//		tablePersonal.setModel(modelPersonal);
//		scrollPanePersonal.setViewportView(tablePersonal);
//
//		try {
//			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
//			PreparedStatement statement = c.connection.prepareStatement("Select * from personal");
//			ResultSet result = statement.executeQuery();
//			while (result.next()) {
//				rPersonal[0] = result.getString("id_personal");
//				String s = result.getString("nume") + " " + result.getString("prenume");
//				rPersonal[1] = s;
//				rPersonal[2] = result.getString("functie");
//
//				modelPersonal.addRow(rPersonal);
//
//			}
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}

		/////////////////

		JLabel lblPersonal = new JLabel("Personal");
		lblPersonal.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonal.setBounds(571, 10, 353, 39);
		panelPersonal.add(lblPersonal);

		JLabel lblAeronave = new JLabel("Aeronave");
		lblAeronave.setForeground(Color.BLACK);
		lblAeronave.setHorizontalAlignment(SwingConstants.CENTER);
		lblAeronave.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblAeronave.setBounds(571, 266, 353, 47);
		panelPersonal.add(lblAeronave);

		JScrollPane scrollPanelAeronavePersonal = new JScrollPane();
		scrollPanelAeronavePersonal.setBounds(571, 309, 353, 239);
		panelPersonal.add(scrollPanelAeronavePersonal);

		tableAeronavePersonal = new JTable();
		tableAeronavePersonal.setEnabled(false);
		tableAeronavePersonal.setBackground(Color.LIGHT_GRAY);
		modelAeronavePersonal = new DefaultTableModel();
		Object[] cA = { "IDAeronava", "Nume-Companie", "Capacitate", "An fabricatie" };
		Object[] rA = new Object[4];
		modelAeronavePersonal.setColumnIdentifiers(cA);
		tableAeronavePersonal.setModel(modelAeronavePersonal);

		scrollPanelAeronavePersonal.setViewportView(tableAeronavePersonal);

		JLabel lblNewLabel_2 = new JLabel("Adaugare personal");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 27, 261, 30);
		panelPersonal.add(lblNewLabel_2);

		JLabel lblIdAeronava = new JLabel("Id aeronava");
		lblIdAeronava.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdAeronava.setForeground(Color.BLACK);
		lblIdAeronava.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblIdAeronava.setBounds(10, 67, 261, 30);
		panelPersonal.add(lblIdAeronava);

		textIdAeronava = new JTextField("");
		textIdAeronava.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textIdAeronava.setForeground(Color.BLACK);
		textIdAeronava.setHorizontalAlignment(SwingConstants.CENTER);
		textIdAeronava.setBounds(111, 93, 51, 30);
		panelPersonal.add(textIdAeronava);
		textIdAeronava.setColumns(10);

		JLabel lblNume_1 = new JLabel("Nume");
		lblNume_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNume_1.setForeground(Color.BLACK);
		lblNume_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNume_1.setBounds(10, 122, 261, 30);
		panelPersonal.add(lblNume_1);

		textNumePersonal = new JTextField("");
		textNumePersonal.setHorizontalAlignment(SwingConstants.CENTER);
		textNumePersonal.setForeground(Color.BLACK);
		textNumePersonal.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textNumePersonal.setColumns(10);
		textNumePersonal.setBounds(67, 148, 143, 30);
		panelPersonal.add(textNumePersonal);

		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrenume.setForeground(Color.BLACK);
		lblPrenume.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPrenume.setBounds(10, 178, 261, 30);
		panelPersonal.add(lblPrenume);

		textPrenumePersonal = new JTextField("");
		textPrenumePersonal.setHorizontalAlignment(SwingConstants.CENTER);
		textPrenumePersonal.setForeground(Color.BLACK);
		textPrenumePersonal.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textPrenumePersonal.setColumns(10);
		textPrenumePersonal.setBounds(67, 204, 143, 30);
		panelPersonal.add(textPrenumePersonal);

		JLabel lblVarsta = new JLabel("Varsta");
		lblVarsta.setHorizontalAlignment(SwingConstants.CENTER);
		lblVarsta.setForeground(Color.BLACK);
		lblVarsta.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblVarsta.setBounds(10, 234, 261, 30);
		panelPersonal.add(lblVarsta);

		textVarstaPersonal = new JTextField("");
		textVarstaPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		textVarstaPersonal.setForeground(Color.BLACK);
		textVarstaPersonal.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textVarstaPersonal.setColumns(10);
		textVarstaPersonal.setBounds(111, 266, 51, 30);
		panelPersonal.add(textVarstaPersonal);

		JLabel lblDataAngajarii = new JLabel("Data angajarii (an-luna-zi)");
		lblDataAngajarii.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataAngajarii.setForeground(Color.BLACK);
		lblDataAngajarii.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDataAngajarii.setBounds(10, 288, 261, 30);
		panelPersonal.add(lblDataAngajarii);

		textDataAngajare = new JTextField("");
		textDataAngajare.setToolTipText("an-luna-zi");
		textDataAngajare.setHorizontalAlignment(SwingConstants.CENTER);
		textDataAngajare.setForeground(Color.BLACK);
		textDataAngajare.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textDataAngajare.setColumns(10);
		textDataAngajare.setBounds(67, 314, 133, 30);
		panelPersonal.add(textDataAngajare);

		JLabel lblFunctie = new JLabel("Functie");
		lblFunctie.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunctie.setForeground(Color.BLACK);
		lblFunctie.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblFunctie.setBounds(10, 344, 261, 30);
		panelPersonal.add(lblFunctie);

		JComboBox comboBoxFunctie = new JComboBox();
		comboBoxFunctie.setBackground(new Color(255, 255, 255));
		comboBoxFunctie.setModel(new DefaultComboBoxModel(new String[] { "Pilot", "Copilot", "Stewardesa" }));
		comboBoxFunctie.setForeground(Color.BLACK);
		comboBoxFunctie.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		comboBoxFunctie.setBounds(70, 373, 130, 30);
		panelPersonal.add(comboBoxFunctie);

		JButton btnAdaugaPersonal = new JButton("Adauga");
		btnAdaugaPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textIdAeronava.getText().equals("") || textNumePersonal.getText().equals("")
						|| textPrenumePersonal.getText().equals("") || textVarstaPersonal.getText().equals("")
						|| textDataAngajare.getText().equals(""))
					new ErrrLogare("Completati toate campurile!");
				else {
					c.adaugaPersonal(textIdAeronava.getText(), textNumePersonal.getText(), textPrenumePersonal.getText(),
							textVarstaPersonal.getText(), textDataAngajare.getText(),
							comboBoxFunctie.getSelectedItem().toString());
					textIdAeronava.setText("");
					textNumePersonal.setText("");
					textPrenumePersonal.setText("");
					textVarstaPersonal.setText("");
					textDataAngajare.setText("");
					tabelPersonal();
					new ErrrLogare("Personal adaugat!");
				}

			}
		});
		btnAdaugaPersonal.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnAdaugaPersonal.setForeground(Color.BLACK);
		btnAdaugaPersonal.setBounds(79, 442, 105, 30);
		panelPersonal.add(btnAdaugaPersonal);
		
		JLabel lblStPersonal = new JLabel("Sterge Personal");
		lblStPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		lblStPersonal.setForeground(Color.BLACK);
		lblStPersonal.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblStPersonal.setBounds(300, 27, 261, 30);
		panelPersonal.add(lblStPersonal);
		
		JLabel lblIdPersonal = new JLabel("ID personal");
		lblIdPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdPersonal.setForeground(Color.BLACK);
		lblIdPersonal.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblIdPersonal.setBounds(300, 67, 261, 30);
		panelPersonal.add(lblIdPersonal);
		
		textIdStergerePersonal = new JTextField("");
		textIdStergerePersonal.setHorizontalAlignment(SwingConstants.CENTER);
		textIdStergerePersonal.setForeground(Color.BLACK);
		textIdStergerePersonal.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textIdStergerePersonal.setColumns(10);
		textIdStergerePersonal.setBounds(401, 93, 51, 30);
		panelPersonal.add(textIdStergerePersonal);
		
		JButton btnStergePersonal = new JButton("Sterge");
		btnStergePersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textIdStergerePersonal.getText().equals(""))
					new ErrrLogare("Adugati ceva in casauta!");
				else {
					c.stergerePersonal(textIdStergerePersonal.getText());
					textIdStergerePersonal.setText("");;
					tabelPersonal();
				}
			}
		});
		btnStergePersonal.setForeground(Color.BLACK);
		btnStergePersonal.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnStergePersonal.setBounds(374, 129, 105, 30);
		panelPersonal.add(btnStergePersonal);
		
		JLabel lblUpdatePersonal = new JLabel("Update Personal");
		lblUpdatePersonal.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdatePersonal.setForeground(Color.BLACK);
		lblUpdatePersonal.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblUpdatePersonal.setBounds(300, 234, 261, 30);
		panelPersonal.add(lblUpdatePersonal);
		
		JLabel lblIdPersonal_1 = new JLabel("ID personal");
		lblIdPersonal_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdPersonal_1.setForeground(Color.BLACK);
		lblIdPersonal_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblIdPersonal_1.setBounds(300, 274, 261, 30);
		panelPersonal.add(lblIdPersonal_1);
		
		textIdPersonalU = new JTextField("");
		textIdPersonalU.setHorizontalAlignment(SwingConstants.CENTER);
		textIdPersonalU.setForeground(Color.BLACK);
		textIdPersonalU.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textIdPersonalU.setColumns(10);
		textIdPersonalU.setBounds(401, 300, 51, 30);
		panelPersonal.add(textIdPersonalU);
		
		JLabel lblIdPersonal_1_1 = new JLabel("ID Aeronava noua");
		lblIdPersonal_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdPersonal_1_1.setForeground(Color.BLACK);
		lblIdPersonal_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblIdPersonal_1_1.setBounds(300, 329, 261, 30);
		panelPersonal.add(lblIdPersonal_1_1);
		
		textIdPersonalaAU = new JTextField("");
		textIdPersonalaAU.setHorizontalAlignment(SwingConstants.CENTER);
		textIdPersonalaAU.setForeground(Color.BLACK);
		textIdPersonalaAU.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textIdPersonalaAU.setColumns(10);
		textIdPersonalaAU.setBounds(401, 355, 51, 30);
		panelPersonal.add(textIdPersonalaAU);
		
		JButton btnUpdatePersonal = new JButton("Update");
		btnUpdatePersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textIdPersonalU.getText().equals("")||textIdPersonalaAU.getText().equals(""))
					new ErrrLogare("Completati toate campurile!");
				else
				{
					c.updatePersonal(textIdPersonalU.getText(), textIdPersonalaAU.getText());
					textIdPersonalU.setText("");
					textIdPersonalaAU.setText("");
					new ErrrLogare("Personalul s-a updatat!");
					tabelPersonal();
				}
			}
		});
		btnUpdatePersonal.setForeground(Color.BLACK);
		btnUpdatePersonal.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnUpdatePersonal.setBounds(374, 392, 105, 30);
		panelPersonal.add(btnUpdatePersonal);
		try {
			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
			PreparedStatement statement = c.connection.prepareStatement("Select * from aeronava where id_aeronava>1");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				rA[0] = result.getString("id_aeronava");
				String s = result.getString("Nume") + " - " + result.getString("companie");
				rA[1] = s;
				rA[2] = result.getString("capacitate");
				rA[3] = result.getString("an_fabricatie");

				modelAeronavePersonal.addRow(rA);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		panelUseri = new JPanel();
		panelUseri.setBackground(new Color(.0f, 1.0f, 1.0f, 0.3f));
		layeredPane.add(panelUseri, "name_4726701888500");
		panelUseri.setLayout(null);

		//// aici fac butonul pentru a sterge toti utilizatorii inregistrati si care nu
		//// am inchiriat/comandat nimic
		JLabel lblNewLabel = new JLabel("Useri Inregistrati");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(146, 10, 654, 40);
		panelUseri.add(lblNewLabel);

		JButton strg = new JButton("Sterge VIzitatori");
		strg.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String query = "call deleteUser();";
				ResultSet r = null;

				try {
					c.connection = DriverManager.getConnection(c.url, c.username, c.password);
					Statement statement = c.connection.createStatement();
					statement.executeQuery(query);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tabelUseri();

			}
		});
		strg.setFont(new Font("Times New Roman", Font.BOLD, 12));
		strg.setBounds(10, 10, 124, 35);
		panelUseri.add(strg);
		///////////////

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 914, 483);
		panelUseri.add(scrollPane);
		//////////////////////////////////////////// pun toti userii intr-un tabel
		tabelUseri();
//		table = new JTable();
//		table.setBackground(new Color(176, 196, 222));
//		table.setEnabled(false);
//		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		modelU = new DefaultTableModel();
//		Object[] cU = { "ID", "Nume Prenume", "Email", "Telefon", "Scop" };
//		Object[] rU = new Object[5];
//		modelU.setColumnIdentifiers(cU);
//		table.setModel(modelU);
//		scrollPane.setViewportView(table);
//		
//		JLabel lblNewLabel = new JLabel("Useri Inregistrati");
//		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
//		lblNewLabel.setForeground(Color.BLACK);
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel.setBounds(10, 10, 914, 40);
//		panelUseri.add(lblNewLabel);
//
//		try {
//			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
//			PreparedStatement statement = c.connection.prepareStatement("Select * from user_aeroport");
//			ResultSet result = statement.executeQuery();
//			while (result.next()) {
//				rU[0] = result.getString("id_User");
//				String s = result.getString("Nume") + " " + result.getString("Prenume");
//				rU[1] = s;
//				rU[2] = result.getString("email");
//				rU[3] = result.getString("Telefon");
//				if (result.getString("masina_inchiriata") != null)
//					rU[4] = "masina";
//
//				else if (result.getString("destinatie_rezervata") != null)
//					rU[4] = "bilet";
//				else
//					rU[4] = "nimic";
//
//				modelU.addRow(rU);
//
//			}
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		////////////////////////////////////////////////////////////

		JPanel panelContact = new JPanel();
		layeredPane.add(panelContact, "name_4730727111600");
		panelContact.setLayout(null);

		JLabel lblmail = new JLabel("Email:");
		lblmail.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblmail.setBounds(10, 84, 453, 35);
		panelContact.add(lblmail);

		JLabel lblContact = new JLabel("Contact");
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblContact.setBounds(10, 10, 453, 35);
		panelContact.add(lblContact);

		JLabel m = new JLabel(c.getContact().get(0));
		m.setHorizontalAlignment(SwingConstants.CENTER);
		m.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		m.setBounds(10, 116, 453, 35);
		panelContact.add(m);

		JLabel lbltelefon = new JLabel("Numar de telefon:");
		lbltelefon.setHorizontalAlignment(SwingConstants.CENTER);
		lbltelefon.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbltelefon.setBounds(10, 177, 453, 35);
		panelContact.add(lbltelefon);

		JLabel t = new JLabel(c.getContact().get(1));
		t.setHorizontalAlignment(SwingConstants.CENTER);
		t.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		t.setBounds(10, 210, 453, 35);
		panelContact.add(t);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(510, 26, 414, 497);
		panelContact.add(panel);
		panel.setLayout(null);

		JLabel tltEditareContact = new JLabel("Editare date de contact");
		tltEditareContact.setFont(new Font("Times New Roman", Font.BOLD, 25));
		tltEditareContact.setHorizontalAlignment(SwingConstants.CENTER);
		tltEditareContact.setBounds(10, 10, 394, 39);
		panel.add(tltEditareContact);

		JLabel lblMail = new JLabel("Noul email");
		lblMail.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMail.setHorizontalAlignment(SwingConstants.CENTER);
		lblMail.setBounds(10, 91, 394, 51);
		panel.add(lblMail);

		JLabel lblNoulTelefon = new JLabel("Noul telefon");
		lblNoulTelefon.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoulTelefon.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNoulTelefon.setBounds(10, 204, 394, 51);
		panel.add(lblNoulTelefon);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		textEmail = new JTextField("");
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textEmail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textEmail.setBounds(45, 135, 331, 39);
		panel.add(textEmail);
		textEmail.setColumns(10);

		textTelefon = new JTextField("");
		textTelefon.setHorizontalAlignment(SwingConstants.CENTER);
		textTelefon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textTelefon.setColumns(10);
		textTelefon.setBounds(45, 248, 331, 39);
		panel.add(textTelefon);

		JButton btnEditareContact = new JButton("Schimba");
		btnEditareContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.editContact(textEmail.getText(), textTelefon.getText());
				if (!textEmail.getText().equals("")) {
					m.setText(textEmail.getText());
				}
				if (!textTelefon.getText().equals("")) {
					t.setText(textTelefon.getText());
				}

				textEmail.setText("");
				textTelefon.setText("");
				// }
				// else
				// new ErrrLogare("Camp necompletat!");

			}
		});
		btnEditareContact.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		btnEditareContact.setBounds(150, 338, 123, 39);
		panel.add(btnEditareContact);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(0, 0, 0));
		menuBar.setMargin(new Insets(5, 4, 5, 4));
		menuBar.setBackground(new Color(176, 224, 230));
		menuBar.setBounds(10, 10, 145, 51);
		frame.getContentPane().add(menuBar);

		JMenu file = new JMenu("File");
		file.setSelectedIcon(new ImageIcon("D:\\POO\\ProiectAeroport\\Imagini\\imadm.jpg"));
		file.setHorizontalAlignment(SwingConstants.CENTER);
		file.setForeground(Color.BLACK);
		file.setFont(new Font("Times New Roman", Font.BOLD, 20));
		menuBar.add(file);

		JMenuItem acasa = new JMenuItem("Acasa");
		acasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(panelAcasa);
			}
		});

		acasa.setSelected(true);
		acasa.setBackground(Color.LIGHT_GRAY);
		acasa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		acasa.setForeground(Color.BLACK);
		acasa.setHorizontalAlignment(SwingConstants.CENTER);
		file.add(acasa);

		JMenuItem pgUser = new JMenuItem("Pagina User");
		pgUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);

				try {
					User u = new User();
					u.setP(1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		pgUser.setSelected(true);
		pgUser.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		pgUser.setHorizontalAlignment(SwingConstants.LEFT);
		file.add(pgUser);

		JMenu edit = new JMenu("Edit");
		edit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		edit.setForeground(Color.BLACK);
		menuBar.add(edit);

		JMenuItem msn = new JMenuItem("Masina");
		msn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(panelMasina);
			}
		});
		msn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		edit.add(msn);

		JMenuItem plc = new JMenuItem("Plecari");
		plc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(panelPlecari);
			}
		});
		plc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		edit.add(plc);

		JMenuItem sos = new JMenuItem("Sosiri");
		sos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(panelSosiri);
			}
		});
		sos.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		edit.add(sos);

		JMenuItem aeronava = new JMenuItem("Aeronava");
		aeronava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(panelAeronava);
			}
		});
		aeronava.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		edit.add(aeronava);

		JMenuItem mntmPersonal = new JMenuItem("Personal");
		mntmPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(panelPersonal);
			}
		});
		mntmPersonal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		edit.add(mntmPersonal);

		JMenu vezi = new JMenu("Vezi");
		vezi.setForeground(new Color(0, 0, 0));
		vezi.setFont(new Font("Times New Roman", Font.BOLD, 20));
		menuBar.add(vezi);

		JMenuItem user = new JMenuItem("Useri");
		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(panelUseri);
			}
		});
		user.setHorizontalAlignment(SwingConstants.CENTER);
		user.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		user.setForeground(Color.BLACK);
		vezi.add(user);

		JMenuItem contact = new JMenuItem("Contact");
		contact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(panelContact);
			}
		});
		contact.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		vezi.add(contact);

		JButton deconectare = new JButton("Deconectare");
		deconectare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				new Admin();
			}
		});
		deconectare.setFont(new Font("Times New Roman", Font.BOLD, 20));
		deconectare.setBounds(794, 23, 150, 44);
		frame.getContentPane().add(deconectare);

		JLabel titlu = new JLabel("Aeroportul International Avram Iancu");
		titlu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		titlu.setHorizontalAlignment(SwingConstants.CENTER);
		titlu.setForeground(new Color(255, 255, 0));
		titlu.setBounds(187, 10, 602, 61);
		frame.getContentPane().add(titlu);

		JLabel fundal = new JLabel("");
		fundal.setIcon(new ImageIcon("D:\\POO\\ProiectAeroport\\Imagini\\imadm.jpg"));
		fundal.setBounds(0, 0, 971, 661);
		frame.getContentPane().add(fundal);

	}

	private String createTextMesaj() {
		try {
			String text=mesajTxt.getText();
			Statement stm=c.connection.createStatement();
			String query="select mesaj from contact where id_aeroport=1;";
			ResultSet r=stm.executeQuery(query);
			r.next();
			String s=r.getString(1);
			//if(!s.equals("null"))
				text=text+"\n"+r.getString(1);
			return text;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
