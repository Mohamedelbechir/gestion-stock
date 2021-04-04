/**
 * 
 */
package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JOptionPane;

import classeMetier.Utilisateur;
import dao.DAO;

/**
 * @author Mohamed El Béchir
 *
 */
public class UtilisateurDAO extends DAO<Utilisateur> {

	/**
	 * 
	 */
	public UtilisateurDAO() {
		// TODO Auto-generated constructor stub
	}
	

	/* (non-Javadoc)
	 * @see dao.DAO#create(java.lang.Object)
	 */
	@Override
	public boolean create(Utilisateur obj) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date maDate = formatter.parse(obj.getDate_nais());
					
				PreparedStatement stmt;
				stmt = connect.prepareStatement("INSERT INTO `utilisateur`(`LOGIN`, `MOT_PASS`, `CIN`, `NOM`, `PRENOM`, `DATE_NAIS`, `STATUE`, `SEXE`) VALUES"
						+ " (?,?,?,?,?,?,?,?)");
				stmt.setString(1, obj.getLogin());
				stmt.setString(2, obj.getPass());
				stmt.setLong(3,obj.getCin());
				stmt.setString(4, obj.getNom());
				stmt.setString(5, obj.getPrenom());					
				stmt.setDate(6,  new java.sql.Date(maDate.getTime()));
				stmt.setString(7, obj.getStatue());
				stmt.setString(8, obj.getSexe());
				
				stmt.execute();
					stmt.close();
		
				
					
		} catch (Exception  e) {
			
			e.printStackTrace();
			
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public boolean delete(Utilisateur obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM UTILISATEUR WHERE CIN=?");
			stmt.setLong(1,obj.getCin());
			stmt.executeQuery();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see dao.DAO#update(java.lang.Object)
	 */
	@Override
	public boolean update(Utilisateur obj) {
		try {
			
			
			if(obj.getCin()!=null){
				PreparedStatement stmt;
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date maDate = formatter.parse(obj.getDate_nais());
				stmt = connect.prepareStatement("UPDATE  UTILISATEUR SET LOGIN=?, MOT_PASS= ?, NOM =?,PRENOM=?,DATE_NAIS=?,STATUE=?"
						+ ",SEXE=?, CIN=? WHERE IDUTIL=? ");
				stmt.setString(1, obj.getLogin());
				stmt.setString(2, obj.getPass());
				stmt.setString(3, obj.getNom());
				stmt.setString(4, obj.getPrenom());					
				//stmt.setString(5, obj.getDate_nais());
				//stmt.setDate(2,new java.sql.Date(maDate.getTime()) );
				stmt.setDate(5, new java.sql.Date(maDate.getTime()) );
				stmt.setString(6, obj.getStatue());
				stmt.setString(7, obj.getSexe());
				stmt.setLong(8, obj.getCin());
				stmt.setLong(9, obj.getIdUtil());
				stmt.execute();
				stmt.close();
			}
				
	} catch (Exception  e) {
		
		e.printStackTrace();
		
		return false;
	}
	return true;
	}

	/* (non-Javadoc)
	 * @see dao.DAO#find(java.lang.Long)
	 */
	@Override
	public Utilisateur find(Integer id) {
		Utilisateur utilisateur=null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM UTILISATEUR WHERE IDUTIL = ? ");
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				utilisateur = new Utilisateur();
				utilisateur.setIdUtil(rs.getInt(1));
				utilisateur.setLogin(rs.getString(2));
				utilisateur.setPass(rs.getString(3));
				utilisateur.setCin(rs.getInt(4));
				utilisateur.setNom(rs.getString(5));
				utilisateur.setPrenom(rs.getString(6));					
				utilisateur.setDate_nais(rs.getString(7));
				utilisateur.setStatue(rs.getString(8));
				utilisateur.setSexe(rs.getString(9));
			}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		return utilisateur;
	}
	
	public Utilisateur findByLogin(String login){
		Utilisateur utilisateur=null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM UTILISATEUR WHERE LOGIN like ? ");
			stmt.setString(1,login);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				utilisateur = new Utilisateur();
				utilisateur.setIdUtil(rs.getInt(1));
				utilisateur.setLogin(rs.getString(2));
				utilisateur.setPass(rs.getString(3));
				utilisateur.setCin(rs.getInt(4));
				utilisateur.setNom(rs.getString(5));
				utilisateur.setPrenom(rs.getString(6));					
				utilisateur.setDate_nais(rs.getString(7));
				utilisateur.setStatue(rs.getString(8));
				utilisateur.setSexe(rs.getString(9));
			}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		return utilisateur;
	}
	public Utilisateur find(String login , String pass){
		Utilisateur utilisateur=null;
		
		
			try {
				PreparedStatement stmt = connect.prepareStatement("SELECT * FROM UTILISATEUR WHERE LOGIN LIKE ? AND MOT_PASS LIKE ?");
				stmt.setString(1,login);
				stmt.setString(2, pass);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()){
					utilisateur = new Utilisateur();
					utilisateur.setIdUtil(rs.getInt(1));
					utilisateur.setLogin(rs.getString(2));
					utilisateur.setPass(rs.getString(3));
					utilisateur.setCin(rs.getInt(4));
					utilisateur.setNom(rs.getString(5));
					utilisateur.setPrenom(rs.getString(6));					
					utilisateur.setDate_nais(rs.getString(7));
					utilisateur.setStatue(rs.getString(8));
					utilisateur.setSexe(rs.getString(9));
					
				}
				rs.close();
				stmt.close();
				} catch (SQLException e) {
					System.out.println(utilisateur.toString());
					e.printStackTrace();
				}
			return utilisateur;
	}
		

	/* (non-Javadoc)
	 * @see dao.DAO#findCollection(java.lang.String)
	 */
	@Override
	public Vector<Utilisateur> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	public static  Vector<Utilisateur> findAll() {
		Vector<Utilisateur> vecteur = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM UTILISATEUR");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdUtil(rs.getInt(1));
				utilisateur.setLogin(rs.getString(2));
				utilisateur.setPass(rs.getString(3));
				utilisateur.setCin(rs.getInt(4));
				utilisateur.setNom(rs.getString(5));
				utilisateur.setPrenom(rs.getString(6));					
				utilisateur.setDate_nais(rs.getString(7));
				utilisateur.setStatue(rs.getString(8));
				utilisateur.setSexe(rs.getString(9));
				vecteur.addElement(utilisateur);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vecteur;
	}
	public  Vector<Utilisateur> findAll(String terme ,String facteur) {
		Vector<Utilisateur> vecteur = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM UTILISATEUR WHERE "+facteur+" LIKE ?");
			stmt.setString(1, terme);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdUtil(rs.getInt(1));
				utilisateur.setLogin(rs.getString(2));
				utilisateur.setPass(rs.getString(3));
				utilisateur.setCin(rs.getInt(4));
				utilisateur.setNom(rs.getString(5));
				utilisateur.setPrenom(rs.getString(6));					
				utilisateur.setDate_nais(rs.getString(7));
				utilisateur.setStatue(rs.getString(8));
				utilisateur.setSexe(rs.getString(9));
				vecteur.addElement(utilisateur);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vecteur;
	}

}
