import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class Admin {

	private JFrame frame;
	private JTextField textU;
	private JPasswordField textP;

	public Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 1160, 682);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JLabel lbUsername = new JLabel("Username");
		lbUsername.setForeground(Color.BLACK);
		lbUsername.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbUsername.setBounds(573, 236, 131, 41);
		frame.getContentPane().add(lbUsername);

		JLabel lbParola = new JLabel("Password");
		lbParola.setForeground(Color.BLACK);
		lbParola.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbParola.setBounds(573, 329, 149, 41);
		frame.getContentPane().add(lbParola);

		textU = new JTextField();
		textU.setHorizontalAlignment(SwingConstants.CENTER);
		textU.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textU.setToolTipText("");
		textU.setBounds(531, 287, 214, 32);
		frame.getContentPane().add(textU);
		textU.setColumns(10);

		textP = new JPasswordField();
		textP.setHorizontalAlignment(SwingConstants.CENTER);
		textP.setFont(new Font("Times New Roman", Font.BOLD, 15));
		textP.setBounds(531, 380, 214, 31);
		frame.getContentPane().add(textP);
		textP.setColumns(10);

		JButton log = new JButton("Log in");
		ConnectDB c = null;
		try {
			c = new ConnectDB();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// System.out.println(c.getAdmin().get(0)+" "+c.getAdmin().get(1));
		log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = textU.getText();
				String password = textP.getText();

				try {
					if (userName.trim().equals(c.getAdmin().get(0)) && password.trim().equals(c.getAdmin().get(1))) {
						frame.setVisible(false);
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									AdminLogat window = new AdminLogat();

								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});

						//System.out.println("buuun");
					} else {

						try {
							ErrrLogare var = new ErrrLogare("Username sau parola gresita!!");
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		log.setFont(new Font("Times New Roman", Font.BOLD, 16));
		log.setBounds(601, 426, 85, 32);
		frame.getContentPane().add(log);

		JButton back = new JButton("Back");
		back.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Pagina1 window = new Pagina1();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		back.setBounds(10, 48, 102, 41);
		frame.getContentPane().add(back);

		JLabel numeCmp = new JLabel("Aeroportul Intenational Avram Iancu");
		numeCmp.setForeground(SystemColor.activeCaptionText);
		numeCmp.setFont(new Font("Lucida Calligraphy", Font.BOLD | Font.ITALIC, 30));
		numeCmp.setBounds(279, 48, 671, 67);
		frame.getContentPane().add(numeCmp);

		JLabel fundal = new JLabel("");
		fundal.setIcon(new ImageIcon("D:\\POO\\ProiectAeroport\\Imagini\\imadm.jpg"));
		fundal.setBounds(0, 0, 1266, 711);
		frame.getContentPane().add(fundal);
	}
}
