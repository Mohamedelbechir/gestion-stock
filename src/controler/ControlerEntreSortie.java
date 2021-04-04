package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import classeMetier.Entree;
import classeMetier.Sortie;
import classeMetier.Utilisateur;
import dao.implement.ArticleDAO;
import dao.implement.EntreeDAO;
import dao.implement.SortieDAO;
import view.GestionEntreSortie;

public class ControlerEntreSortie implements MouseListener, ActionListener {
	private EntreeDAO  entreeDAO;
	private GestionEntreSortie gestionEntreSortie;
	private ArticleDAO articleDAO;
	private SortieDAO sortieDAO;
	public ControlerEntreSortie(GestionEntreSortie gestionEntreSortie) {
		this.gestionEntreSortie=gestionEntreSortie;
		entreeDAO= new EntreeDAO();
		articleDAO= new ArticleDAO();
		sortieDAO=new SortieDAO();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==gestionEntreSortie.getBtnValider_Entre()){
			
			ajouterEntre();
			
		}else if(arg0.getSource()==gestionEntreSortie.getBtnValider_S()){
			ajouterSortie();
		}
		
	}

	public ResultSet getLiseEntre(){
		return entreeDAO.getListEntre();
	}
	public ResultSet getListeEntree() {
		return entreeDAO.findAll();
		
	}
	public ResultSet getListeSortie(){
		return sortieDAO.findAll();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Object inst=arg0.getSource();
		if (arg0.getSource()==gestionEntreSortie.getTable_Entree()) {
			if(gestionEntreSortie.getTable_Entree().getSelectedRow()!=-1){
				gestionEntreSortie.chargerChampEntre();
			}
			
			
		}else if(arg0.getSource()==gestionEntreSortie.getTable_Sortie()){
			if(gestionEntreSortie.getTable_Sortie().getSelectedRow()!=-1){
				gestionEntreSortie.chargerChampsSortie();
			}
		}
		
	}
	public void ajouterSortie(){
		if(gestionEntreSortie.getTable_Sortie().getSelectedRow()!=-1){
			if(gestionEntreSortie.verifierSortie()){
				Sortie sortie=gestionEntreSortie.creerObjetSortie();
				if(sortieDAO.create(sortie) & 
						articleDAO.decrementerArticle(sortie.getCodeArt(), sortie.getQte()) ){
							JOptionPane.showMessageDialog(null, "Succès !!");
							gestionEntreSortie.chargertableEntre();
							gestionEntreSortie.chargerTableSortie();
					
					}else
						JOptionPane.showMessageDialog(null, "Erreur !!");
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "Selectionner un article dans la liste !");
		}
	}
	public void ajouterEntre(){

		if(gestionEntreSortie.getTable_Entree().getSelectedRow()!=-1){
				
				if(gestionEntreSortie.verifierEntree()){
					Entree entree=gestionEntreSortie.creerObjetEntre();
					
					if(entreeDAO.create(entree) & 
						articleDAO.incrementerArticle(entree.getCodeart(), entree.getQte()) ){
							JOptionPane.showMessageDialog(null, "Succès !!");
							gestionEntreSortie.chargertableEntre();
							gestionEntreSortie.chargerTableSortie();
					
					}else
						JOptionPane.showMessageDialog(null, "Erreur !!");
					}
		}else
			JOptionPane.showMessageDialog(null, "Selectionner L'article dans la liste dabord !");
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
