import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;

public class Pagina1 {

	private JFrame frame;

	public Pagina1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 1160, 682);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JButton admin = new JButton("Admin");
		admin.setForeground(SystemColor.activeCaptionText);
		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Admin window = new Admin();

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		admin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		admin.setBounds(631, 329, 151, 50);
		frame.getContentPane().add(admin);

		JButton user = new JButton("User");
		user.setForeground(SystemColor.activeCaptionText);
		user.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							User window = new User();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		user.setBounds(368, 329, 132, 50);
		frame.getContentPane().add(user);

		JLabel lblNewLabel = new JLabel("Cluj-Napoca");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setBounds(473, 108, 285, 57);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel cj = new JLabel("Aeroportul Intenational Avram Iancu");
		cj.setForeground(Color.BLACK);
		cj.setFont(new Font("Lucida Calligraphy", Font.BOLD | Font.ITALIC, 35));
		cj.setBounds(193, 53, 847, 57);
		frame.getContentPane().add(cj);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\POO\\ProiectAeroport\\Imagini\\im1.jpg"));
		lblNewLabel_1.setBounds(-70, 0, 1225, 654);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
