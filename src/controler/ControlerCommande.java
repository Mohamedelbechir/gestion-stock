package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import classeMetier.*;
import classeMetier.Fournisseur;
import dao.implement.ArticleDAO;
import dao.implement.CommandeDAO;
import dao.implement.FournisseurDAO;
import dao.implement.LineCommandeDAO;
import view.ConfirmationCommande;
import view.GestionEntreSortie;
import view.GestionLineCommande;

public class ControlerCommande implements ActionListener {
	private GestionEntreSortie gestionEntreSortie;
	private Commande commande;
	private Commande commandeValidation;
	private LineCommande lineCommande;
	private CommandeDAO commandeDAO;
	private LineCommandeDAO lineCommandeDAO;
	
	
	public ControlerCommande(GestionEntreSortie gestionEntreSortie ) {
		this.gestionEntreSortie=gestionEntreSortie;
		commandeDAO=new CommandeDAO();
		lineCommandeDAO=new LineCommandeDAO();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==gestionEntreSortie.getBtnInsererUneLine()){
			if(gestionEntreSortie.verifier()){
				inserLine();
			}
			
		}else if(arg0.getSource()==gestionEntreSortie.getBtnPasserLaCommande()){
			if(commandeDAO.create(gestionEntreSortie.getCommande())){
				Integer idCom=commandeDAO.getMaxIdCom();
				gestionEntreSortie.getCommande().setNumcmd(idCom);
				if(lineCommandeDAO.createListe(gestionEntreSortie.getCommande())){
					JOptionPane.showMessageDialog(null, "Succès !!");
					gestionEntreSortie.chargerTable_Commande();
				}
			}
			
		}else if(arg0.getSource()==gestionEntreSortie.getBtnC()){
			// Valider la Commande
			new ConfirmationCommande(commandeValidation).setVisible(true);
			
		}
		
	}
	public void inserLine(){
		ArrayList<Article> list=getListArtByFour();
		gestionEntreSortie.getCommande().setEtat("Non Réçue");;
		new GestionLineCommande(gestionEntreSortie.getCommande(),list,gestionEntreSortie).setVisible(true);
	}
	public Vector<Fournisseur> getListFournisseur() {
		return FournisseurDAO.findAll();
	}
	public Vector<Commande> getListCommande(){
		Vector<Commande> list=CommandeDAO.findAll();
		for (Commande commande : list) {
			ArrayList<LineCommande> listCommande=lineCommandeDAO.getListLineCommande(commande.getNumcmd());
			commande.setListCommande(listCommande);
		}
		return list;
	}
	public ArrayList<LineCommande> getListLineCommande(Integer id){
		
		return lineCommandeDAO.getListLineCommande(id);
	}
	public ArrayList<Article> getListArtByFour(){
		String nomF=gestionEntreSortie.getComboBoxFourn().getSelectedItem().toString();
		Integer id=new FournisseurDAO().findByNom(nomF).getNumF();
		return new ArticleDAO().getListByIdFour(id);
	}
}
