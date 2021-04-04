/**
 * 
 */
package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import classeMetier.Article;
import classeMetier.Fournisseur;
import classeMetier.Utilisateur;
import dao.DAO;

/**
 * @author Mohamed El Béchir
 *
 */
public class FournisseurDAO extends DAO<Fournisseur> {

	@Override
	public boolean create(Fournisseur obj) {
		try {
			
			
			
				
					PreparedStatement stmt;
					//stmt = connect.prepareStatement("INSERT INTO fournissuer VALUES (?,?,?,?,?,?,?,?)");
					stmt=connect.prepareStatement("INSERT INTO fournissuer  (NOMF, ADR, CODEP,VILLE, PAYS, TELE, FAX, EMAIL) "
							+ "VALUES (?,?,?,?,?,?,?,?)");
					stmt.setString(1, obj.getNomF());
					stmt.setString(2,obj.getAdr());
					stmt.setString(3, obj.getCodeP());
					stmt.setString(4, obj.getVille());					
					stmt.setString(5, obj.getPays());
					stmt.setString(6, obj.getTele());
					stmt.setString(7, obj.getFax());
					stmt.setString(8, obj.getEmail());
					
					stmt.execute();
					stmt.close();
				
				
				
	} catch (Exception  e) {
		
		e.printStackTrace();
		
		return false;
	}
	return true;
	}

	@Override
	public boolean delete(Fournisseur obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM fournissuer WHERE NUMF=?");
			stmt.setLong(1,obj.getNumF());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean update(Fournisseur obj) {
		try {
			
			
			if(obj.getNomF()!=null){
				PreparedStatement stmt;
				stmt = connect.prepareStatement("UPDATE  fournissuer SET NOMF=?, ADR= ?, CODEP =?,VILLE=?,PAYS=?,TELE=?"
						+ ",FAX=? ,EMAIL=? WHERE NUMF=? ");
				stmt.setString(1, obj.getNomF());
				stmt.setString(2, obj.getAdr());
				stmt.setString(3, obj.getCodeP());
				stmt.setString(4, obj.getVille());					
				stmt.setString(5, obj.getPays());
				stmt.setString(6, obj.getTele());
				stmt.setString(7, obj.getFax());
				stmt.setString(8, obj.getEmail());
				stmt.setInt(9, obj.getNumF());
				stmt.execute();
				stmt.close();
			}
		}catch (Exception  e) {
			
			e.printStackTrace();
			
			return false;
		}
		return true;
	}

	@Override
	public Fournisseur find(Integer id) {
		Fournisseur fournisseur=null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM fournissuer where NUMF= ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				fournisseur = new Fournisseur();
				fournisseur.setNumF(rs.getInt(1));
				fournisseur.setNomF(rs.getString(2));
				fournisseur.setAdr(rs.getString(3));
				fournisseur.setCodeP(rs.getString(4));
				fournisseur.setVille(rs.getString(5));					
				fournisseur.setPays(rs.getString(6));
				fournisseur.setTele(rs.getString(7));
				fournisseur.setFax(rs.getString(8));
				fournisseur.setEmail(rs.getString(9));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fournisseur;
	}
	public Fournisseur findByNom(String nom){
		Fournisseur fournisseur=null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM fournissuer where NOMF like ?");
			stmt.setString(1, nom);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				fournisseur = new Fournisseur();
				fournisseur.setNumF(rs.getInt(1));
				fournisseur.setNomF(rs.getString(2));
				fournisseur.setAdr(rs.getString(3));
				fournisseur.setCodeP(rs.getString(4));
				fournisseur.setVille(rs.getString(5));					
				fournisseur.setPays(rs.getString(6));
				fournisseur.setTele(rs.getString(7));
				fournisseur.setFax(rs.getString(8));
				fournisseur.setEmail(rs.getString(9));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fournisseur;
	}
	@Override
	public Vector<Fournisseur> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public static  Vector<Fournisseur> findAll() {
		Vector<Fournisseur> vecteur = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM fournissuer");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Fournisseur fournisseur = new Fournisseur();
				fournisseur.setNumF(rs.getInt(1));
				fournisseur.setNomF(rs.getString(2));
				fournisseur.setAdr(rs.getString(3));
				fournisseur.setCodeP(rs.getString(4));
				fournisseur.setVille(rs.getString(5));					
				fournisseur.setPays(rs.getString(6));
				fournisseur.setTele(rs.getString(7));
				fournisseur.setFax(rs.getString(8));
				fournisseur.setEmail(rs.getString(9));
				vecteur.addElement(fournisseur);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vecteur;
	}

	public Vector<Fournisseur> findAll(String facteur, String terme) {
		Vector<Fournisseur> vecteur = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM fournissuer WHERE "+facteur+" LIKE ?");
			stmt.setString(1, terme);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Fournisseur fournisseur = new Fournisseur();
				fournisseur.setNumF(rs.getInt(1));
				fournisseur.setNomF(rs.getString(2));
				fournisseur.setAdr(rs.getString(3));
				fournisseur.setCodeP(rs.getString(4));
				fournisseur.setVille(rs.getString(5));					
				fournisseur.setPays(rs.getString(6));
				fournisseur.setTele(rs.getString(7));
				fournisseur.setFax(rs.getString(8));
				fournisseur.setEmail(rs.getString(9));
				vecteur.addElement(fournisseur);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vecteur;
	}

}
