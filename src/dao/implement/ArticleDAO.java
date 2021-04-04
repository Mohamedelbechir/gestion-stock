package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import classeMetier.*;

import dao.DAO;

public class ArticleDAO extends DAO<Article> {

	@Override
	public boolean create(Article obj) {
		try {
			
		
	
				PreparedStatement stmt;
				stmt = connect.prepareStatement("INSERT INTO `article`(`DESIGNATION`, `UNITE`, `QTE`, `PRIX`, `EMPLACEMENT`, `IDCATEG`, `NUMF`) "
								+ "	VALUES (?,?,?,?,?,?,?)");
				
				stmt.setString(1, obj.getDesignation());
				stmt.setInt(2, obj.getUnite());
				stmt.setLong(3,obj.getQte());
				stmt.setFloat(4, obj.getPrix());
				stmt.setString(5, obj.getEmplacement());					
				stmt.setInt(6, obj.getIdCat());
				stmt.setInt(7, obj.getIdFour());
				stmt.execute();
				stmt.close();
			
			
				
					
		} catch (Exception  e) {
			
			e.printStackTrace();
			
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Article obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM ARTICLE WHERE CODEART=?");
			stmt.setLong(1,obj.getCodeArt());
			stmt.executeQuery();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	@Override
	public boolean update(Article obj) {
		try {
			
				PreparedStatement stmt;
				stmt = connect.prepareStatement("UPDATE  ARTICLE SET DESIGNATION=?, UNITE= ?, QTE =?,PRIX=?,EMPLACEMENT=?,IDCATEG=?, NUMF=?"
						+ " WHERE CODEART=? ");
				stmt.setString(1, obj.getDesignation());
				stmt.setInt(2, obj.getUnite());
				stmt.setInt(3, obj.getQte());
				stmt.setFloat(4, obj.getPrix());					
				stmt.setString(5, obj.getEmplacement());
				stmt.setInt(6, obj.getIdCat());
				stmt.setInt(7, obj.getIdFour());
				stmt.setInt(8, obj.getCodeArt());
				
				stmt.execute();
				stmt.close();
				
		} catch (Exception  e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}

	@Override
	public Article find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	public   Vector<Article> findByDesi(String terme){
		Vector<Article> vecteur = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM ARTICLE where DESIGNATION like ?");
			stmt.setString(1, "%"+terme+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Article article = new Article();
				article.setCodeArt(rs.getInt("CODEART"));
				article.setDesignation(rs.getString("DESIGNATION"));
				article.setUnite(rs.getInt("UNITE"));
				article.setQte(rs.getInt("QTE"));
				article.setPrix(rs.getFloat("PRIX"));
				article.setEmplacement(rs.getString("EMPLACEMENT"));
				article.setIdCat(rs.getInt("IDCATEG"));
				article.setIdFour(rs.getInt("NUMF"));
				
				vecteur.addElement(article);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vecteur;
	}
	@Override
	public Vector<Article> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Vector<Article> findAll(){
		Vector<Article> vecteur = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM ARTICLE");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Article article = new Article();
				article.setCodeArt(rs.getInt("CODEART"));
				article.setDesignation(rs.getString("DESIGNATION"));
				article.setUnite(rs.getInt("UNITE"));
				article.setQte(rs.getInt("QTE"));
				article.setPrix(rs.getFloat("PRIX"));
				article.setEmplacement(rs.getString("EMPLACEMENT"));
				article.setIdCat(rs.getInt("IDCATEG"));
				article.setIdFour(rs.getInt("NUMF"));
				
				vecteur.addElement(article);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vecteur;
	}
	
	public boolean incrementerArticle(Integer idArticle,Integer qt){
		try {
			PreparedStatement stmt;
			stmt = connect.prepareStatement("UPDATE  ARTICLE SET ARTICLE.QTE = ARTICLE.QTE + "+qt+" WHERE CODEART=? ");
			stmt.setInt(1, idArticle);
	    	stmt.execute();
			stmt.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean decrementerArticle(Integer idArticle,Integer qt){
		try {
			PreparedStatement stmt;
			stmt = connect.prepareStatement("UPDATE  ARTICLE SET QTE = QTE - "+qt+" WHERE CODEART=? ");
			stmt.setInt(1, idArticle);
	    	stmt.execute();
			stmt.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// Obtenir liste article selon id Fournisseru
	public ArrayList<Article> getListByIdFour(Integer id){
		ArrayList<Article> lis= new ArrayList<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM ARTICLE where NUMF=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Article article = new Article();
				article.setCodeArt(rs.getInt("CODEART"));
				article.setDesignation(rs.getString("DESIGNATION"));
				article.setUnite(rs.getInt("UNITE"));
				article.setQte(rs.getInt("QTE"));
				article.setPrix(rs.getFloat("PRIX"));
				article.setEmplacement(rs.getString("EMPLACEMENT"));
				article.setIdCat(rs.getInt("IDCATEG"));
				article.setIdFour(rs.getInt("NUMF"));
				
				lis.add(article);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lis;
	}

}
