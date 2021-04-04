package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import classeMetier.Magasin;
import classeMetier.Utilisateur;
import dao.DAO;

public class MagasinDAO extends DAO<Magasin> {

	public MagasinDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Magasin obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Magasin obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Magasin obj) {
		String requete="UPDATE MAGASIN SET NOM_MAG=?,PROPRIETAIRE=?,ADRESSE_MAG=?,VILLE=?,CODE_POSTALE=?,EMAIL_MAG=?,TEL_FIXE=?,TEL_PORTABLE=? WHERE ID_MAG =?";
		try {
			PreparedStatement stm = connect.prepareStatement(requete);
			stm.setString(1, obj.getNomMag());
			stm.setString(2, obj.getProprietaire());
			stm.setString(3, obj.getAdr());
			stm.setString(4, obj.getVille());
			stm.setString(5, obj.getBp());
			stm.setString(6,obj.getEmail());
			stm.setInt(7,obj.getFax());
			stm.setInt(8,obj.getTel());
			stm.setInt(9,obj.getIdMag());	
			stm.execute();
			
//			obj.Changer();
//			obj.notifyObservers();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}

	@Override
	public Magasin find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Magasin getMagasin(){
		Magasin magasin=null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM MAGASIN ");
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				magasin = new Magasin();
				magasin.setIdMag(rs.getInt("ID_MAG"));
				magasin.setAdr(rs.getString("ADRESSE_MAG"));
				magasin.setBp(rs.getString("CODE_POSTALE"));
				magasin.setEmail(rs.getString("EMAIL_MAG"));
				magasin.setFax(rs.getInt("TEL_FIXE"));
				magasin.setIdMag(rs.getInt("ID_MAG"));
				magasin.setNomMag(rs.getString("NOM_MAG"));
				magasin.setProprietaire(rs.getString("PROPRIETAIRE"));
				magasin.setTel(rs.getInt("TEL_PORTABLE"));
				magasin.setVille(rs.getString("VILLE"));
			}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		return magasin;
	}
	@Override
	public Vector<Magasin> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
