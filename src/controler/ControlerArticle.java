package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import classeMetier.Article;
import classeMetier.Categorie;
import classeMetier.Fournisseur;
import classeMetier.Utilisateur;
import dao.implement.ArticleDAO;
import dao.implement.CategorieDAO;
import dao.implement.FournisseurDAO;
import utility.Imprimer;
import view.GestionArticle;
import view.MainApp;

public class ControlerArticle implements ActionListener, MouseListener{
	private GestionArticle  gestionArticle;
	private CategorieDAO categorieDAO;
	private ArticleDAO articleDAO;
	private FournisseurDAO fournisseurDAO;
	
	public ControlerArticle(GestionArticle  gestionArticle) {
		this.gestionArticle=gestionArticle;
		categorieDAO=new CategorieDAO();
		fournisseurDAO= new FournisseurDAO();
		articleDAO= new ArticleDAO();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String nameButton = ((JButton)arg0.getSource()).getText();
		if(nameButton.equals(gestionArticle.getBtnAjouter_Art().getText())){
			if(gestionArticle.verifierArticle()){
				
				Article article = gestionArticle.creer_Objet_Article_Saisi_Champ_PourAjout();
				ajouterArticle(article);
			}
			
			
		}else if(nameButton.equals(gestionArticle.getBtnModifier_Art().getText())){
			if(gestionArticle.verifierArticleUptdate()){
				Article article= gestionArticle.creer_Objet_Article_Saisi_Champ_PourAjout();
				article.setCodeArt((int)gestionArticle.getTable_Article().getValueAt(gestionArticle.getTable_Article().getSelectedRow(), 0));
				modifierArticle(article);
				
			}
			
		}else if(nameButton.equals(gestionArticle.getBtnSupprimer_Art().getText())){
			
			if(gestionArticle.obtenir_Article_Selection()!=null){
				supprimerArt(gestionArticle.obtenir_Article_Selection());
			}else{
				JOptionPane.showMessageDialog(null, "Selectionner un Article !");
			}
		}else if(nameButton.equals(gestionArticle.getBtnNettoyer_Art().getText())){
				gestionArticle.nettoyerChampArt();
		}
		else if(nameButton.equals(gestionArticle.getBtnImprimer_Art().getText())){
			Imprimer.print("Rapport\\Liste_Des_Articles.jrxml");
		}
		
	}
	public void ajouterArticle(Article article){
		
		// Verifiecation
		if(articleDAO.create(article)){
			JOptionPane.showMessageDialog(null, "Succès !");
			// Ajouter une nouvelle ligne au tableau sans le charger dans la base			
			gestionArticle.chargerTableArt();
			gestionArticle.nettoyerChampArt();
		}else{
			JOptionPane.showMessageDialog(null, "Erreur !!");
		}
						
	}
	public void modifierArticle(Article article){
		// Verification
		if(articleDAO.update(article)){
			JOptionPane.showMessageDialog(null, "Succès !!");
			// modifier dans le tableau
			int row =gestionArticle.getTable_Article().getSelectedRow();
			//gestionArticle.getModel_table_Article().updateRow(newRow(article), row);
			gestionArticle.chargerTableArt();
		}else{
			JOptionPane.showMessageDialog(null, "Erreur!!");
		}
	}
	public void supprimerArt(Article article){
		
		if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer cet utilisateur ?")==JOptionPane.OK_OPTION){
			if(articleDAO.delete(article)){
				gestionArticle.getModel_table_Article().removeRow(gestionArticle.getTable_Article().getSelectedRow());
				JOptionPane.showMessageDialog(null, "Succès !!");
			}
		}
			
		
	}
	// Créer un object pour l'ajouter dans le taleau
	public Object[] newRow(Article article){
		
		Object[] newRow= new Object[]{
				article.getCodeArt(),
				article.getDesignation(),
				article.getUnite(),
				article.getQte(),
				article.getPrix(),
				article.getEmplacement(),
				article.getIdCat()
		};
		return newRow;
	}
	public Vector<Article>  getListeArt(){
		return articleDAO.findAll();
	}
	
	public Vector<Article> findByDesi(String terme){
		return articleDAO.findByDesi(terme);
	}
	public Vector<Fournisseur> getListFourn(){
		return new FournisseurDAO().findAll();
	}
	public Fournisseur getFournById(Integer id){
		return fournisseurDAO.find(id);
	}
	public Fournisseur getFournByNom(String nom){
		return fournisseurDAO.findByNom(nom);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Object inst=arg0.getSource();
		if (inst instanceof JTable) {
			Article article= gestionArticle.obtenir_Article_Selection();
			if(article!=null)
				gestionArticle.remplir_Champ_Article(article);
			
		}
		
		
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
