package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.GestionEntreSortie;
import view.GestionLineCommande;

public class controlerInsererLineCom implements ActionListener{
	 
	private GestionLineCommande gestionLineCommande;
	private GestionEntreSortie gestionEntreSortie;
	public controlerInsererLineCom(GestionLineCommande gestionLineCommande, GestionEntreSortie gestionEntreSortie) {
		this.gestionLineCommande=gestionLineCommande;
		this.gestionEntreSortie=gestionEntreSortie;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==gestionLineCommande.getBtnInserer()){
			if(gestionLineCommande.verifier()){
				gestionLineCommande.getCommande().getListCommande().add(gestionLineCommande.genererLine());
				if(JOptionPane.showConfirmDialog(null, "Voulez-vous ajouter d'autre Line ?")!=JOptionPane.OK_OPTION){
					gestionEntreSortie.chargerTable_LineC();
					gestionLineCommande.dispose();
				}
					
			}
			
		}
		
	}

}
