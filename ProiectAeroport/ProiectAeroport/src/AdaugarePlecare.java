import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdaugarePlecare {

	private JFrame frame;
	String data;
	private JTable tableAeronaveDisponibile;
	JScrollPane scrollPaneAeronave; 
	DefaultTableModel modelAeronave;
	ConnectDB c;
	private JLabel lblAeronaveDisp;
	private JLabel lblIDAeronava;
	private JTextField textIDAeronava;
	private JButton btnAdauga;
	
	public AdaugarePlecare(String data,String destinatie, String ora, String zbor, String escala, String pret, String plecare) {
		this.data=data;
		initialize(data,destinatie, ora,zbor,escala, pret,plecare);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String dat,String dest, String ora, String zbor, String escala, String pret, String plecare) {
		frame = new JFrame();
		frame.setBounds(100, 100, 474, 626);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		c=null;
		try {
			c=new ConnectDB();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scrollPaneAeronave = new JScrollPane();
		scrollPaneAeronave.setBounds(10, 61, 439, 394);
		frame.getContentPane().add(scrollPaneAeronave);
		
		tableAeronaveDisponibile = new JTable();
		tableAeronaveDisponibile.setEnabled(false);
		tableAeronaveDisponibile.setBackground(Color.LIGHT_GRAY);
		modelAeronave = new DefaultTableModel();
		Object[] cAeronave = { "IDAeronava", "Nume-Companie", "Capacitate", "An fabricatie" };
		Object[] rAeronave = new Object[4];
		modelAeronave.setColumnIdentifiers(cAeronave);
		tableAeronaveDisponibile.setModel(modelAeronave);
		scrollPaneAeronave.setViewportView(tableAeronaveDisponibile);
		
		lblAeronaveDisp = new JLabel("Aeronave Disponibile");
		lblAeronaveDisp.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblAeronaveDisp.setForeground(Color.BLACK);
		lblAeronaveDisp.setHorizontalAlignment(SwingConstants.CENTER);
		lblAeronaveDisp.setBounds(10, 0, 428, 51);
		frame.getContentPane().add(lblAeronaveDisp);
		
		lblIDAeronava = new JLabel("ID aeronava");
		lblIDAeronava.setHorizontalAlignment(SwingConstants.CENTER);
		lblIDAeronava.setForeground(Color.BLACK);
		lblIDAeronava.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblIDAeronava.setBounds(129, 465, 160, 43);
		frame.getContentPane().add(lblIDAeronava);
		
		textIDAeronava = new JTextField();
		textIDAeronava.setHorizontalAlignment(SwingConstants.CENTER);
		textIDAeronava.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textIDAeronava.setColumns(10);
		textIDAeronava.setBounds(179, 505, 58, 28);
		frame.getContentPane().add(textIDAeronava);
		
		btnAdauga = new JButton("Adauga");
		btnAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//////////////////////////////////////////////////////////////////////////////////////////////////
				if(textIDAeronava.getText().equals(""))
					new ErrrLogare("Alegeti ID!");
				else
				{
					c.adaugaGestiune(textIDAeronava.getText());
					c.AdaugaPlecare(dest, dat, ora, zbor, escala, pret, plecare);
					frame.setVisible(false);
					new ErrrLogare("Plecare adaugata!");
				}
			}
		});
		btnAdauga.setForeground(Color.BLACK);
		btnAdauga.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnAdauga.setBounds(157, 543, 108, 33);
		frame.getContentPane().add(btnAdauga);
		
		try {
			c.connection = DriverManager.getConnection(c.url, c.username, c.password);
			data="'"+data+"'";
			PreparedStatement statement = c.connection.prepareStatement("Select a.id_aeronava, a.Nume, a.Companie, a.capacitate, a.an_fabricatie  from aeronava a \r\n"
					+ "join gestiune g  \r\n"
					+ "join plecari p \r\n"
					+ " where a.id_aeronava>1 and ((a.id_aeronava=g.idAeronava and g.id_gestiune=p.id_gestiune and p.data_plecarii!="+data+")\r\n"
					+ " or(a.id_aeronava not in (select gestiune.idAeronava from gestiune)))  group by(a.id_aeronava) order by(a.id_aeronava)");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				rAeronave[0] = result.getString("id_aeronava");
				String s = result.getString("Nume") + " - " + result.getString("companie");
				rAeronave[1] = s;
				rAeronave[2] = result.getString("capacitate");
				rAeronave[3] = result.getString("an_fabricatie");

				modelAeronave.addRow(rAeronave);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
