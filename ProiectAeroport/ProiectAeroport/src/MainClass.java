import java.awt.EventQueue;

public class MainClass {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
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

}
