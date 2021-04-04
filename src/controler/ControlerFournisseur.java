package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import classeMetier.Fournisseur;
import classeMetier.Utilisateur;
import dao.implement.FournisseurDAO;
import utility.Imprimer;
import utility.ModelTableau;
import view.GestionFournisseur;

public class ControlerFournisseur implements ActionListener, MouseListener{
	private GestionFournisseur gestionFournisseur=null;
	private FournisseurDAO fournisseurDAO=null;

	public ControlerFournisseur(GestionFournisseur gestionUtilisateur) {
		this.gestionFournisseur=gestionUtilisateur;
		fournisseurDAO=new FournisseurDAO();
	}
	public Vector<Fournisseur> getListefournisseur(){
		return fournisseurDAO.findAll();
	}
	public Vector<Fournisseur> findCollection(String facteur, String terme) {
		return  fournisseurDAO.findAll(facteur,terme);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Bonjour");
		String nameButton = ((JButton)arg0.getSource()).getText();
		// Ajout d'un utilisateur
		if(nameButton.equals(gestionFournisseur.getBtnAjouter().getText())){
			if(gestionFournisseur.verifier()){
				Fournisseur fournisseur= gestionFournisseur.creer_Objet_Fournisseur_Saisi_Champ_PourAjout();
				ajouterFournisseur(fournisseur);
			}
			
		
		}else if(nameButton.equals(gestionFournisseur.getBtnModifier().getText())){
			if(gestionFournisseur.verifierUpdate()){
				Fournisseur fournisseur= gestionFournisseur.creer_Objet_Fournisseur_Saisi_Champ_PourAjout();
				fournisseur.setNumF(gestionFournisseur.obtenir_Fournisseur_Selection().getNumF());
				modifierUtilisateur(fournisseur);
				
			}
		}else if(nameButton.equals(gestionFournisseur.getBtnSupprimer().getText())){
			if(gestionFournisseur.getTable().getSelectedRow()!=-1){
				Fournisseur fournisseur= gestionFournisseur.obtenir_Fournisseur_Selection();
				if(fournisseurDAO.delete(fournisseur)){
					JOptionPane.showMessageDialog(null, "Succès !");
					gestionFournisseur.getModel_table().removeRow(gestionFournisseur.getTable().getSelectedRow());
				}
					
				else
					JOptionPane.showMessageDialog(null, "Echec !!");
			}else
				JOptionPane.showMessageDialog(null, "Selectionner un fournisseur !");
		}else if(nameButton.equals(gestionFournisseur.getBtnNettoyer().getText())){
				gestionFournisseur.nettoyer();
		}else if(nameButton.equals(gestionFournisseur.getBtnImprimer().getText())){
			Imprimer.print("Rapport\\Liste_Des_Fournisseurs.jrxml");
		}
	}	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Object inst=arg0.getSource();
		if (inst instanceof JTable) {
			Fournisseur fournisseur= gestionFournisseur.obtenir_Fournisseur_Selection();
			if(fournisseur!=null)
				gestionFournisseur.remplir_Champ_Fournisseur(fournisseur);
			
		}
		
	}
	public void ajouterFournisseur(Fournisseur fournisseur){
		
		// Verifiecation
		if(fournisseurDAO.create(fournisseur)){
			JOptionPane.showMessageDialog(null, "Succès !");
			// Ajouter une nouvelle ligne au tableau sans le charger dans la base			
			gestionFournisseur.charger();
		}
						
	}
	public void modifierUtilisateur(Fournisseur fournisseur){
		
		if(gestionFournisseur.getTable().getSelectedRow()!=-1){
			if(fournisseurDAO.update(fournisseur)){
			int row = gestionFournisseur.getTable().getSelectedRow();
			//gestionFournisseur.getModel_table().updateRow(newRow(fournisseur),row);
			gestionFournisseur.charger();
			JOptionPane.showMessageDialog(null, "Succès !!");
		}else{
			JOptionPane.showMessageDialog(null, "Erreur !!");
		}
		
		}else
			JOptionPane.showMessageDialog(null, "Aucun Fournisseur Selectionner !");
		
		
		
	}
	
	// Créer un object pour l'ajouter dans le taleau
	public Object[] newRow(Fournisseur fournisseur){
			
			Object[] newRow= new Object[]{
					fournisseur.getNumF(),
					fournisseur.getNomF(),
					fournisseur.getAdr(),
					fournisseur.getCodeP(),
					fournisseur.getVille(),
					fournisseur.getPays(),
					fournisseur.getTele(),
					fournisseur.getFax(),	
					fournisseur.getEmail()
			};
			return newRow;
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	

}
