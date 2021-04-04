package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import classeMetier.Categorie;
import classeMetier.Utilisateur;
import dao.DAO;

public class CategorieDAO extends DAO<Categorie> {

	@Override
	public boolean create(Categorie obj) {
		try {
			
			//ResultSet rs =   connect.prepareStatement("SELECT SEQUENCE_CATEGORIE.nextval FROM DUAL").executeQuery();	
			PreparedStatement stmt;
			stmt = connect.prepareStatement("INSERT INTO `categorie`(`NOMCATE`, `DESCRIPTION`) VALUES (?,?)");
			//stmt.setLong(1,rs.getInt(1));
			stmt.setString(1, obj.getNomCate());
			stmt.setString(2, obj.getDescription());
			
			stmt.execute();
			stmt.close();
		
			
				
					
		} catch (Exception  e) {
			
			e.printStackTrace();
			
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Categorie obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM CATEGORIE WHERE IDCATEG=?");
			stmt.setLong(1,obj.getIdCat());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean update(Categorie obj) {
		try {
			
			
			if(obj.getIdCat()!=null){
				PreparedStatement stmt;
				stmt = connect.prepareStatement("UPDATE  CATEGORIE SET NOMCATE=?, DESCRIPTION= ? WHERE IDCATEG=? ");
				stmt.setString(1, obj.getNomCate());
				stmt.setString(2, obj.getDescription());
				stmt.setInt(3, obj.getIdCat());
				stmt.execute();
				stmt.close();
			}
				
	} catch (Exception  e) {
		
		e.printStackTrace();
		
		return false;
	}
	return true;
	}
	@Override
	public Categorie find(Integer id) {
		Categorie categorie=null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM CATEGORIE WHERE IDCATEG = ? ");
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				categorie = new Categorie();
				categorie.setIdCat(rs.getInt(1));
				categorie.setNomCate(rs.getString(2));
				categorie.setDescription(rs.getString(3));
			}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		return categorie;
	}
	
	@Override
	public Vector<Categorie> findCollection(String id) {
		Vector<Categorie> vecteur = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM CATEGORIE WHERE NOMCATE LIKE ? ");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Categorie categorie = new Categorie();
				categorie.setIdCat(rs.getInt(1));
				categorie.setNomCate(rs.getString(2));
				categorie.setDescription(rs.getString(3));
				vecteur.add(categorie);
				
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vecteur;
	}
	public Categorie findByNom(String nom) {
		Categorie categorie=null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM CATEGORIE WHERE NOMCATE = ? ");
			stmt.setString(1,nom);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				categorie = new Categorie();
				categorie.setIdCat(rs.getInt(1));
				categorie.setNomCate(rs.getString(2));
				categorie.setDescription(rs.getString(3));
			}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		return categorie;
	}
	public static Vector<Categorie> findAll() {
		Vector<Categorie> vecteur = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM CATEGORIE");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Categorie categorie = new Categorie();
				categorie.setIdCat(rs.getInt(1));
				categorie.setNomCate(rs.getString(2));
				categorie.setDescription(rs.getString(3));
				vecteur.addElement(categorie);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vecteur;
	}

}
