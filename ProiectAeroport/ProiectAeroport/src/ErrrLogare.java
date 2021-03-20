import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ErrrLogare {

	private JFrame err;

	
	public ErrrLogare(String s) {
		initialize(s);
	}

	private void initialize(String s) {
		err = new JFrame();
		err.setVisible(true);
		err.setBounds(100, 100, 333, 159);
		//err.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		err.getContentPane().setLayout(null);
		err.setLocationRelativeTo(null);
		err.setResizable(false);
		//err.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel msg = new JLabel(s);
		msg.setHorizontalAlignment(SwingConstants.CENTER);
		msg.setFont(new Font("Times New Roman", Font.BOLD, 14));
		msg.setBounds(10, 10, 309, 56);
		err.getContentPane().add(msg);
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				err.setVisible(false);
			}
		});
		ok.setBounds(111, 76, 85, 21);
		err.getContentPane().add(ok);
	}
}
