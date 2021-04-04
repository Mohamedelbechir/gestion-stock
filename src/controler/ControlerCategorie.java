package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import classeMetier.Categorie;
import classeMetier.Utilisateur;
import dao.implement.ArticleDAO;
import dao.implement.CategorieDAO;
import utility.Imprimer;
import view.GestionArticle;
import view.GestionUtilisateur;
import view.MainApp;

public class ControlerCategorie implements ActionListener,MouseListener{
	private GestionArticle  gestionArticle;
	private CategorieDAO categorieDAO;
	public ControlerCategorie(GestionArticle  gestionArticle) {
		this.gestionArticle=gestionArticle;
		categorieDAO=new CategorieDAO();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String nameButton = ((JButton)arg0.getSource()).getText();
		if(nameButton.equals(gestionArticle.getBtnAjouter_Cat().getText())){
			if(gestionArticle.verifierAjoutCat()){
				Categorie categorie= gestionArticle.creer_Objet_Categorie_Saisi_Champ_PourAjout();
				ajouterCateg(categorie);
			}
			
		}else if(nameButton.equals(gestionArticle.getBtnModifier_Cat().getText())){
			if(gestionArticle.verifierUpdateCat()){
				
				Categorie categorie= gestionArticle.creer_Objet_Categorie_Saisi_Champ_PourAjout();
				categorie.setIdCat((int)gestionArticle.getTable_Categorie().getValueAt(gestionArticle.getTable_Categorie().getSelectedRow(), 0));
				modifierCateg(categorie);
				
			}
			
		}else if(nameButton.equals(gestionArticle.getBtnSupprimer_Cat().getText())){
			if(gestionArticle.obtenir_Categorie_Selection()!=null){
				supprimerCateg(gestionArticle.obtenir_Categorie_Selection());
			}else{
				JOptionPane.showMessageDialog(null, "Selectioner une Categorie !");
			}
		}else if(nameButton.equals(gestionArticle.getBtnImprimerLaListe_Cat().getText())){
			Imprimer.print("Rapport\\Liste_Des_Categorie.jrxml");
			
		}else if(nameButton.equals(gestionArticle.getBtnNettoyerCat().getText())){
				gestionArticle.nettoyerChampCat();
		}
		
	}
	public void ajouterCateg(Categorie categorie){
		
		if(categorieDAO.create(categorie)){
			JOptionPane.showMessageDialog(null, "Succès !!");
			gestionArticle.nettoyerChampCat();
			gestionArticle.chargerComboCat_ComboFourni();
			gestionArticle.chargerTableCat();
		}else{
			JOptionPane.showMessageDialog(null, "Une erreur s'est effectuée !");
		}
	}
	public void modifierCateg(Categorie categorie){
		if(categorieDAO.update(categorie)){
			gestionArticle.chargerTableCat();
			JOptionPane.showMessageDialog(null, "Succès !!");
		}else{
			JOptionPane.showMessageDialog(null, "Erreur !!");
		}
		
	}
	public void supprimerCateg(Categorie categorie){
		
		if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer cette categorie ?")==JOptionPane.OK_OPTION){
			if(categorieDAO.delete(categorie)){
				gestionArticle.getModel_table_Categorie().removeRow(gestionArticle.getTable_Categorie().getSelectedRow());
				gestionArticle.chargerComboCat_ComboFourni();
				JOptionPane.showMessageDialog(null, "Succès !!");
			}else{
				JOptionPane.showMessageDialog(null, "Erreur!!");
			}
		}
			
		
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Object inst=arg0.getSource();
		if (inst instanceof JTable) {
			Categorie categorie= gestionArticle.obtenir_Categorie_Selection();
			if(categorie!=null)
				gestionArticle.remplir_Champ_Categorie(categorie);
			
		}
		
	}
	
	public Vector<Categorie> getListeCateg(){
		return categorieDAO.findAll();
	}
	public Vector<Categorie> findCollection(String name){
		return categorieDAO.findCollection(name);
	}
	public Categorie getByNom(String nom){
		return categorieDAO.findByNom(nom);
	}
	public Categorie getById(Integer id){
		return categorieDAO.find(id);
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
