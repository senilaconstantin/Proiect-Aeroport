import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectDB {
	static Connection connection = null;
	static String databaseName = "";
	static String url = "jdbc:mysql://localhost/proiectaeroport";
	static String username = "root";
	static String password = "Senila1234";

	ConnectDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
	}

	public static ArrayList<String> get() throws Exception {
		try {
			 connection = DriverManager.getConnection(url, username, password);
			PreparedStatement statement = connection.prepareStatement("Select  * from aeronava");
			ResultSet result = statement.executeQuery();

			ArrayList<String> array = new ArrayList<String>();
			while (result.next()) {
				System.out.println(result.getString("Nume"));

				array.add(result.getString("Nume"));
			}
			System.out.println("Toate aeronavele au fost selectate!");
			return array;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static ArrayList<String> getAdmin() throws Exception {
		ArrayList<String> array = new ArrayList<String>();
		try {
			// connection = DriverManager.getConnection(url, username, password);
			PreparedStatement statement = connection.prepareStatement("Select * from Admin_Aeroport");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				array.add(result.getString("Username"));
				array.add(result.getString("parola"));
			}

			return array;
		} catch (Exception e) {
			System.out.println(e);
		}
		return array;
	}

	public static ArrayList<String> getContact() throws Exception {
		ArrayList<String> array = new ArrayList<String>();
		try {
			// connection = DriverManager.getConnection(url, username, password);
			PreparedStatement statement = connection.prepareStatement("Select * from Contact");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				array.add(result.getString("mail"));
				array.add(result.getString("numar_telefon"));
			}

			return array;
		} catch (Exception e) {
			System.out.println(e);
		}
		return array;
	}

	public static void setUser(String nume, String prenume, String varsta, String email, String telefon) {
		
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement stm = connection.prepareStatement("call insert_user(?,?,?,?,?)");
			stm.setString(1, nume);
			stm.setString(2, prenume);
			stm.setString(3, varsta);
			stm.setString(4, email);
			stm.setString(5, telefon);
			ResultSet result=stm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("nu s au putut extrage datele");
			//e.printStackTrace();
		}
	}
	
	public void editContact(String email, String telefon) {
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement stm = connection.prepareStatement("call editContact(?,?)");
			stm.setString(1, email);
			stm.setString(2, telefon);
			ResultSet result=stm.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void adaugaMasina(String nr, String marca, String pret, String anFabricatie) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement stm = connection.prepareStatement("call insert_masina(?,?,?,?)");
			stm.setString(1, nr);
			stm.setString(2, marca);
			stm.setString(3, pret);
			stm.setString(4, anFabricatie);
			ResultSet result=stm.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void adaugaAeronava(String companie, String nume, String capacitate, String an_Fabricatie) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement stm = connection.prepareStatement("call insertAeronava(?,?,?,?)");
			stm.setString(1, companie);
			stm.setString(2, nume);
			stm.setString(3, capacitate);
			stm.setString(4, an_Fabricatie);
			ResultSet result=stm.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stergePlecare(String id) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement stm = connection.prepareStatement("call stergerePlecare(?)");
			stm.setString(1, id);
			
			ResultSet result=stm.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void stergeAeronava(String id) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement stm = connection.prepareStatement("call deleteAeronava(?)");
			stm.setString(1, id);
			
			ResultSet result=stm.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void adaugaGestiune(String id) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement stm = connection.prepareStatement("call InsertGestiune(?)");
			stm.setString(1, id);
			
			ResultSet result=stm.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void AdaugaPlecare(String destinatie,String data,String ora,String zbor,String escala,String pret,String tip) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement stm = connection.prepareStatement("call InsertPlecari(?,?,?,?,?,?,?)");
			stm.setString(1, destinatie);
			stm.setString(2, data);
			stm.setString(3, ora);
			stm.setString(4, zbor);
			stm.setString(5, escala);
			stm.setString(6, pret);
			stm.setString(7, tip);
			
			ResultSet result=stm.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePlecari(String id, String pret) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement stm = connection.prepareStatement("call UpdatePlecari(?,?)");
			stm.setString(1, id);
			stm.setString(2, pret);
			
			ResultSet result=stm.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void adaugaPersonal(String id,String nume,String prenume,String varsta,String data,String functie) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement stm = connection.prepareStatement("call InsertPersonal(?,?,?,?,?,?)");
			stm.setString(1, id);
			stm.setString(2, nume);
			stm.setString(3, prenume);
			stm.setString(4, varsta);
			stm.setString(5, data);
			stm.setString(6, functie);
			
			ResultSet result=stm.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertBagaj(String greutate) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement stm = connection.prepareStatement("call insertBagaj(?)");
			stm.setString(1, greutate);
			
			ResultSet result=stm.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void stergerePersonal(String id) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement stm = connection.prepareStatement("call DeletePersonal(?)");
			stm.setString(1, id);
			
			ResultSet result=stm.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updatePersonal(String id, String idAeronava) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement stm = connection.prepareStatement("call UpdatePersonal(?,?)");
			stm.setString(1, id);
			stm.setString(2, idAeronava);
			
			ResultSet result=stm.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}