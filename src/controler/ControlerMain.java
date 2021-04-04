package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import classeMetier.Magasin;
import dao.implement.MagasinDAO;
import view.GestionArticle;
import view.GestionEntreSortie;
import view.GestionFournisseur;
import view.GestionMonProfil;
import view.GestionParametrage;
import view.GestionUtilisateur;
import view.Login;
import view.MainApp;

public class ControlerMain implements ActionListener {
	private JButton buttonHome;
	private JButton BtnUtilisateur;
	private JButton btnDeconnecter;
	private JPanel contentPane;
	private JPanel panel_Bande;
	private JPanel panel_Menu;
	private GestionUtilisateur gestionUtilisateur;
	private ControlerUtilisateur controlerUtilisateur;
	private GestionFournisseur gestionFournisseur;
	private GestionMonProfil gestionMonProfil;
	private GestionArticle gestionArticle;
	private GestionEntreSortie gestionEntreSortie;
	
	private MainApp main;
	private Magasin magasin;
	public ControlerMain(MainApp main) {
		this.main=main;
		buttonHome=main.getbuttonHome();
		//buttonHome.addActionListener(this);
		BtnUtilisateur=main.getBtnUtilisateur();
		btnDeconnecter=main.getBtnDeconnecter();
		//BtnUtilisateur.addActionListener(this);
	
		panel_Bande= main.getPanel_Bande();
		panel_Menu=main.getPanel_Menu();
		creerMag();
		// Gestion
//		gestionUtilisateur=new GestionUtilisateur();
//		gestionArticle= new GestionArticle();
//		gestionFournisseur= new GestionFournisseur();
//		gestionEntreSortie= new GestionEntreSortie();
//		gestionMonProfil=new GestionMonProfil(main.getUtilisateur());
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String nameButton = ((JButton) arg0.getSource()).getText();
	
		
		if(nameButton.equals(buttonHome.getText())){
			
//			MainApp.desk.removeAll();
//			MainApp.desk.repaint();
			remove_Panel_Gestion(main.getLogo());
			main.colorButton(buttonHome);

			
		}else if(nameButton.equals(BtnUtilisateur.getText())){
			
			remove_Panel_Gestion(new GestionUtilisateur());
			//main.repaint();
			main.colorButton(BtnUtilisateur);
		}else if(nameButton.equals(btnDeconnecter.getText())){
			main.dispose();
			new Login().setVisible(true);
		}else if(nameButton.equals(main.getBtnMonProfil().getText())){
			remove_Panel_Gestion(new GestionMonProfil());
			main.colorButton(main.getBtnMonProfil());
		}else if(nameButton.equals(main.getBtnArticleCategoris().getText())){
			remove_Panel_Gestion(new GestionArticle());
			main.colorButton(main.getBtnArticleCategoris());
		}else if(nameButton.equals(main.getBtnFournisseurs().getText())){
			remove_Panel_Gestion(new GestionFournisseur());
			main.colorButton(main.getBtnFournisseurs());
		}else if(nameButton.equals(main.getBtnEntrssorties().getText())){
			remove_Panel_Gestion(new GestionEntreSortie());
			main.colorButton(main.getBtnEntrssorties());

		}else if(nameButton.equals(main.getBtnParametre().getText())){
			remove_Panel_Gestion(new GestionParametrage(main.getMagasin()));
			main.colorButton(main.getBtnParametre());
		}else if(nameButton.equals(main.getBtnApropos().getText())){
			String v ="<html><head></head><body><h3 color ='#2C4CCC'><center>"
		            + "<br><p><b><i>Gestion de Stock </i></b>"
		            + "<br><p><b><i>Logiciel de gestion Stock</i></b><br></p><br>"
		        + "<br><p ><b><i>Tous droit reservé</i></b></p><br>"
		        + "<br><p >2017-2018</i></b></p><br>"
		        + "<br><p >---------<b><i>"+"</i></b></p><br></center></h3></p></body></html>";
		    JOptionPane p =new JOptionPane();
		    p.setMessage(v);
		    //p.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		    JDialog d =p.createDialog(null,"Information sur le logiciel ");
		    d.setVisible(true);
		    main.colorButton(main.getBtnApropos());
		}
		
	}
	public void remove_Panel_Gestion(JInternalFrame frame){
		MainApp.desk.removeAll();
		MainApp.desk.add(frame);
		frame.show();
		
		try {
			frame.setMaximum(true);
		} catch (Exception e) {
			
		}
		
		MainApp.desk.repaint();
	}
	public void creerMag(){
		MagasinDAO magasinDAO = new MagasinDAO();
		this.magasin= magasinDAO.getMagasin();
		
	}
	public Magasin getMagasin(){
		return magasin;
	}
	

}
