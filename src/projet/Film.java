package projet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
/**
 * Clase para las películas
 * @author Javier
 *
 */
public class Film {
/**
 * id de la pelicula
 */
	private int id,id_r;
/**
 * Titulo de la película
 */
	private String titre;
/**
 * Precio de la película, en euros
 */
	private double prix;
/**
 * Método get para obtener el id
 * @return
 */
	public int getId() {
		return id;
	}
	/**
	 * Método set para establecer el id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Método get para obtener el id_r
	 * @return
	 */
	public int getId_r() {
		return id_r;
	}
	/**
	 * Método set para establecer el id_r
	 * @param id_r
	 */
	public void setId_r(int id_r) {
		this.id_r = id_r;
	}
	/**
	 * Método get para obtener el titulo
	 * @return
	 */
	public String getTitre() {
		return titre;
	}
	/**
	 * Método set para establecer el título
	 * @param titre
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	/**
	 * Método get para obtener el precio
	 * @return
	 */
	public double getPrix() {
		return prix;
	}
	/**
	 * Método set para establecer el precio
	 * @param prix
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}
	/**
	 * Establece todos las variables
	 * @param titre
	 * @param prix
	 * @param id_r
	 * @param type
	 */
	public void insert(String titre,double prix,int id_r, String type)
	{
		Statement stm;
		PreparedStatement stmt = null;	
		try {
			stm = Main.connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM film where id_realisateur ="+id_r+" AND titre ='"+titre+"'");
			if(rs.next())
			{
				JOptionPane.showMessageDialog(null, "titre existe deja pour ce realisateur");
				return;
			}else{
			
			stmt = Main.connection.prepareStatement("insert into Film values(null,'"+titre+"',"+prix+","+id_r+",'"+type+"')");
			stmt.executeUpdate();}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Hace algo que desconozco
	 * @param titre
	 * @param id
	 */
	public void sup_film(String titre,int id)
	{
		Statement stm;
		try {
			if(titre != null){
				stm = Main.connection.createStatement();
				stm.executeUpdate("DELETE FROM Film WHERE titre='"+titre+"' AND "+" id_realisateur = "+id);
			}
			else{
				stm = Main.connection.createStatement();
				stm.executeUpdate("DELETE FROM Film WHERE id_realisateur = "+id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Muestra el resultado
	 * @return
	 */
	public ResultSet select()
	{
		Statement stm;
		try {
			stm = Main.connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM film");
			return(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return(null);
	}
	
}
