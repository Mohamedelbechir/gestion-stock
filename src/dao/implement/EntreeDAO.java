package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import classeMetier.Article;
import classeMetier.Entree;
import dao.DAO;

public class EntreeDAO extends DAO<Entree> {

	@Override
	public boolean create(Entree obj) {
		try {
				
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date maDate = formatter.parse(obj.getDateentre());
			PreparedStatement stmt;
			stmt = connect.prepareStatement("INSERT INTO `entree`(`qte`, `dateEntree`, `prixEntree`, `CODEART`) VALUES "
					+ "(?,?,?,?)");
			stmt.setInt(1, obj.getQte());
			stmt.setDate(2, new java.sql.Date(maDate.getTime()) );
			stmt.setFloat(3,obj.getPrixdentre());
			stmt.setInt(4, obj.getCodeart());
			stmt.execute();
			stmt.close();
				
	} catch (Exception  e) {
		
		e.printStackTrace();
		
		return false;
	}
	return true;
	}

	@Override
	public boolean delete(Entree obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Entree obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Entree find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Entree> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public ResultSet getListEntre(){
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT article.CODEART as 'id',article.DESIGNATION as 'NOM PRODUIT',fournissuer.NOMF as 'NOM FOURNISSEUR',"
					+ "article.QTE as 'QANTITE',article.EMPLACEMENT as 'EMPLACEMENT',article.PRIX as 'PRIX'  from article,fournissuer "
					+ "WHERE article.NUMF=fournissuer.NUMF");
			
			ResultSet rs = stmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public ResultSet findAll(){
		try {
			 String req="SELECT fournissuer.NOMF as 'FOURNISSEUR',article.DESIGNATION as 'NOM DU PRODUIT', entree.qte as 'QUANTITE',"
			 		+ " entree.dateEntree as `DATE D'ENTREE`, entree.prixEntree as `PRIX D'ENTRREE`  FROM entree,fournissuer,article "
			 		+ " where article.NUMF=fournissuer.NUMF and article.CODEART=entree.CODEART";
			 PreparedStatement stmt=connect.prepareStatement(req);
			 ResultSet rs = stmt.executeQuery();
			 return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
