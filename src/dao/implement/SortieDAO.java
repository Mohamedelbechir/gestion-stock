/**
 * 
 */
package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import classeMetier.Sortie;
import dao.DAO;

/**
 * @author Mohamed El Béchir
 *
 */
public class SortieDAO extends DAO<Sortie> {

	@Override
	public boolean create(Sortie obj) {
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date maDate = formatter.parse(obj.getDateSortie());
			PreparedStatement stmt;
			stmt = connect.prepareStatement("INSERT INTO `sortie`(`qte`, `datSortie`, `prixSortie`, `CODEART`) VALUES "
					+ "(?,?,?,?)");
			stmt.setInt(1, obj.getQte());
			stmt.setDate(2, new java.sql.Date(maDate.getTime()) );
			stmt.setFloat(3,obj.getPrix());
			stmt.setInt(4, obj.getCodeArt());
			stmt.execute();
			stmt.close();
				
	} catch (Exception  e) {
		
		e.printStackTrace();
		
		return false;
	}
	return true;
	}

	@Override
	public boolean delete(Sortie obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Sortie obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sortie find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Sortie> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ResultSet findAll(){
		try {
			String req="SELECT article.DESIGNATION as 'NOM DU PRODUIT', sortie.qte as 'QUANTITE',"
			 		+ " sortie.datSortie as `DATE DE SORTIE`, sortie.prixSortie as `PRIX DE SORTIE`  FROM sortie,article "
			 		+ " where  article.CODEART=sortie.CODEART";
			 PreparedStatement stmt=connect.prepareStatement(req);
			 ResultSet rs = stmt.executeQuery();
			 return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
