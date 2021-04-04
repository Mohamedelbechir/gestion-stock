package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import classeMetier.Commande;
import classeMetier.LineCommande;
import dao.DAO;

public class LineCommandeDAO extends DAO<LineCommande> {

	@Override
	public boolean create(LineCommande obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean createListe(Commande commande){
		PreparedStatement stmt;
		for (LineCommande lineCommande : commande.getListCommande()) {
			try {
				stmt = connect.prepareStatement("INSERT INTO `linecommande`(`qteCommander`, `prix`, `montat`, `NUMCMD`, `CODEART`)"
						+ " VALUES (?,?,?,?,?)");
				stmt.setInt(1, lineCommande.getQuantite());
				stmt.setFloat(2, lineCommande.getPrix());
				stmt.setFloat(3,lineCommande.getMontat());
				stmt.setInt(4, commande.getNumcmd());
				stmt.setInt(5, lineCommande.getCodeArt());					
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	
			
		}
		
		return true;
	}
	@Override
	public boolean delete(LineCommande obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(LineCommande obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LineCommande find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<LineCommande> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public  ArrayList<LineCommande> getListLineCommande(Integer id){
		ArrayList<LineCommande> vecteur = new ArrayList();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM linecommande,commande,article where linecommande.NUMCMD =? "
					+ "and linecommande.CODEART=article.CODEART");
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				LineCommande lineCommande = new LineCommande();
				lineCommande.setIndex	(rs.getInt("numLC"));
				lineCommande.setQuantite(rs.getInt("qteCommander"));
				lineCommande.setPrix(rs.getFloat("PRIX"));
				lineCommande.setCodeArt(rs.getInt("CODEART"));
				lineCommande.setCodeCommande(id);
				vecteur.add(lineCommande);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vecteur;
	}
	
	// Chercher by id Fournisseur
	public ArrayList<LineCommande> getListLineByIdFour(Integer id){
		ArrayList<LineCommande> list=null;
		
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM linecommande,commande,article where linecommande.NUMCMD =? "
					+ "and linecommande.CODEART=article.CODEART");
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				LineCommande lineCommande = new LineCommande();
				lineCommande.setIndex	(rs.getInt("numLC"));
				lineCommande.setQuantite(rs.getInt("qteCommander"));
				lineCommande.setPrix(rs.getFloat("PRIX"));
				lineCommande.setCodeArt(rs.getInt("CODEART"));
				lineCommande.setCodeCommande(id);
				list.add(lineCommande);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
