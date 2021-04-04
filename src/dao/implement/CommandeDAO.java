package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import classeMetier.Commande;
import classeMetier.LineCommande;
import classeMetier.Utilisateur;
import dao.DAO;

public class CommandeDAO extends DAO<Commande> {

	@Override
	public boolean create(Commande obj) {
		try {

			
			PreparedStatement stmt;
			stmt = connect.prepareStatement("INSERT INTO `commande`( `DATECMD`, `ETAT`) VALUES (?,?)");
			
			stmt.setString(1, obj.getDatecmd());
			stmt.setString(2, obj.getEtat());
			stmt.execute();
			stmt.close();
				
		} catch (Exception  e) {
					e.printStackTrace();
					return false;
		}
		return true;
	}
	public Integer getMaxIdCom(){
		try {

			
			PreparedStatement stmt;
			stmt = connect.prepareStatement("SELECT MAX(NUMCMD) FROM commande; ");
			
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
			stmt.close();
				
		} catch (Exception  e) {
					e.printStackTrace();
					return null;
		}
		return null;
	}

	@Override
	public boolean delete(Commande obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Commande obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Commande find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Commande> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public static Vector<Commande> findAll() {
		Vector<Commande> vecteur = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM commande");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Commande commande = new Commande();
				commande.setNumcmd(rs.getInt(1));
				commande.setDatecmd(rs.getString(2));
				commande.setEtat(rs.getString(3));
				vecteur.addElement(commande);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vecteur;
	}
	
	

}
