package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.toedter.calendar.JDateChooser;

import classeMetier.Fournisseur;
import classeMetier.Utilisateur;
import controler.ControlerFournisseur;
import dao.implement.FournisseurDAO;
import dao.implement.UtilisateurDAO;
import utility.ModelTableau;
import utility.TableCellRender;

import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionFournisseur extends JInternalFrame {
	private ModelTableau model_table;
	private ArrayList<JLabel> listeErr;
	private JTextField textField_nom;
	private JTable table;
	private JTextField textField_adr;
	private JTextField textField_pays;
	private JTextField textField_ville;
	private JTextField textField_codeP;
	private JTextField textField_tel;
	private JTextField textField_fax;
	private JTextField textField_mail;
	private JScrollPane scrollPane;
	private FournisseurDAO fournisseurDAO;
	private ControlerFournisseur controlerFournisseur;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnNettoyer;
	private JButton btnImprimer;
	private JLabel err_nom;
	private JLabel err_adr;
	private JLabel err_pst;
	private JLabel err_pays;
	private JLabel err_tel;
	private JLabel err_fax;
	private JLabel err_mail;
	private JLabel label;
	private JTextField textField_recherche;
	private JLabel lblChercherPar;
	private JComboBox comboBox;
	public GestionFournisseur() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(241, 142, 1111, 590);
		getContentPane().setLayout(null);
		
		listeErr= new ArrayList<>();
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Liste des Fournisseurs", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 76, 475, 475);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 26, 197, 28);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textField_recherche = new JTextField("Rechercher");
		textField_recherche.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!textField_recherche.getText().equals("Rechercher")){
					textField_recherche.setText("Rechercher");
					textField_recherche.setForeground(new Color(128, 128, 128));
				}
					
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				if(textField_recherche.getText().equals("Rechercher")){
					textField_recherche.setForeground(Color.BLUE);
					textField_recherche.setText("");
				}
				nettoyer();
			}
		});
		textField_recherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				chargerTable_Fournisseur_Rechercher();
			}

			
		});
		textField_recherche.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		textField_recherche.setForeground(new Color(128, 128, 128));
		textField_recherche.setHorizontalAlignment(SwingConstants.CENTER);
		textField_recherche.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField_recherche.setBounds(0, 0, 162, 28);
		panel_1.add(textField_recherche);
		textField_recherche.setColumns(10);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField_recherche.setText("Rechercher");				
				textField_recherche.setForeground(new Color(128, 128, 128));
			}
		});
		label_1.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/Red X copie.png")));
		label_1.setBounds(162, 0, 35, 28);
		panel_1.add(label_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 80, 455, 384);
		panel.add(scrollPane);
		
		
		model_table= new ModelTableau(new Object[][]{
			{null,null,null,null,null,null,null,null,null}
		},new String[]{
				"NUMF", 
				"NOM FOURNISSEUR" , 
				"ADRESSE", 
				"CODE POSTAL" , 
				"VILLE" , 
				"PAYS", 
				"TEL",
				"FAX",
				"EMAIL"
				
		});
		
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(28);
		scrollPane.setViewportView(table);
		table.setModel(model_table);
		
		lblChercherPar = new JLabel("Chercher par");
		lblChercherPar.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblChercherPar.setBounds(217, 26, 88, 28);
		panel.add(lblChercherPar);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		comboBox.addItem("Nom du Fournisseur");
		comboBox.addItem("Email");
		comboBox.setBounds(304, 26, 161, 28);
		panel.add(comboBox);
		
		table.setDefaultRenderer(Object.class, new TableCellRender());
		table.getTableHeader().setDefaultRenderer(new TableCellRender());
		
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		
		table.getColumnModel().getColumn(1).setMinWidth(175);
		table.getColumnModel().getColumn(1).setMaxWidth(175);
		table.getColumnModel().getColumn(1).setWidth(175);
		
		table.getColumnModel().getColumn(8).setMinWidth(175);
		table.getColumnModel().getColumn(8).setMaxWidth(175);
		table.getColumnModel().getColumn(8).setWidth(175);
		
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.getColumnModel().getColumn(3).setWidth(100);
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 52, 1091, 13);
		getContentPane().add(separator);
		
		JLabel lblGestionDesUtilisateurs = new JLabel("Gestion des Fournissseur");
		lblGestionDesUtilisateurs.setFont(new Font("Sitka Text", Font.BOLD, 13));
		lblGestionDesUtilisateurs.setBounds(10, 11, 224, 42);
		getContentPane().add(lblGestionDesUtilisateurs);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Information Fournisseur", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_11.setBounds(495, 76, 606, 371);
		getContentPane().add(panel_11);
		panel_11.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Nom: <font color= 'red'>*</font></html>");
		lblNewLabel.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		lblNewLabel.setBounds(20, 77,103, 28);
		panel_11.add(lblNewLabel);
		
		textField_nom = new JTextField();
		textField_nom.setBounds(143, 76, 173, 28);
		panel_11.add(textField_nom);
		textField_nom.setColumns(10);
		
		err_nom = new JLabel("");
		listeErr.add(err_nom);
		err_nom.setVisible(false);
		err_nom.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/error.png")));
		err_nom.setBounds(326, 77, 26, 28);
		panel_11.add(err_nom);
		
		err_adr = new JLabel("");
		listeErr.add(err_adr);
		err_adr.setVisible(false);
		err_adr.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/error.png")));
		err_adr.setBounds(298, 107, 30, 28);
		panel_11.add(err_adr);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		lblAdresse.setBounds(20, 107, 89, 28);
		panel_11.add(lblAdresse);
		
		JLabel lblCodePostal = new JLabel("Code Postal");
		lblCodePostal.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		lblCodePostal.setBounds(20, 139, 103, 28);
		panel_11.add(lblCodePostal);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		lblVille.setBounds(20, 165, 73, 28);
		panel_11.add(lblVille);
		
		JLabel lblPays = new JLabel("Pays");
		lblPays.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		lblPays.setBounds(20, 204, 56, 28);
		panel_11.add(lblPays);
		
		JLabel lblTel = new JLabel("Tel");
		lblTel.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		lblTel.setBounds(20, 235, 56, 28);
		panel_11.add(lblTel);
		
		JLabel lblFax = new JLabel("Fax");
		lblFax.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		lblFax.setBounds(20, 267, 56, 28);
		panel_11.add(lblFax);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		lblEmail.setBounds(20, 299, 56, 28);
		panel_11.add(lblEmail);
		
		textField_adr = new JTextField();
		textField_adr.setColumns(10);
		textField_adr.setBounds(143, 107, 149, 28);
		panel_11.add(textField_adr);
		
		textField_pays = new JTextField();
		textField_pays.setColumns(10);
		textField_pays.setBounds(143, 203, 149, 28);
		panel_11.add(textField_pays);
		
		textField_ville = new JTextField();
		textField_ville.setColumns(10);
		textField_ville.setBounds(143, 171, 149, 28);
		panel_11.add(textField_ville);
		
		textField_codeP = new JTextField();
		textField_codeP.setColumns(10);
		textField_codeP.setBounds(143, 139, 149, 28);
		panel_11.add(textField_codeP);
		
		textField_tel = new JTextField();
		textField_tel.setColumns(10);
		textField_tel.setBounds(143, 235, 149, 28);
		panel_11.add(textField_tel);
		
		textField_fax = new JTextField();
		textField_fax.setColumns(10);
		textField_fax.setBounds(142, 267, 150, 28);
		panel_11.add(textField_fax);
		
		textField_mail = new JTextField();
		textField_mail.setColumns(10);
		textField_mail.setBounds(142, 299, 150, 28);
		panel_11.add(textField_mail);
		
		err_pst = new JLabel("");
		listeErr.add(err_pst);
		err_pst.setVisible(false);
		err_pst.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/error.png")));
		err_pst.setBounds(298, 139, 26, 28);
		panel_11.add(err_pst);
		
		JLabel err_ville = new JLabel("");
		listeErr.add(err_ville);
		err_ville.setVisible(false);
		err_ville.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/error.png")));
		err_ville.setBounds(298, 171, 26, 28);
		panel_11.add(err_ville);
		
		err_pays = new JLabel("");
		listeErr.add(err_pays);
		err_pays.setVisible(false);
		err_pays.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/error.png")));
		err_pays.setBounds(298, 204, 30, 28);
		panel_11.add(err_pays);
		
		err_tel = new JLabel("");
		listeErr.add(err_tel);
		err_tel.setVisible(false);
		err_tel.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/error.png")));
		err_tel.setBounds(298, 235, 18, 21);
		panel_11.add(err_tel);
		
		err_fax = new JLabel("");
		listeErr.add(err_fax);
		err_fax.setVisible(false);
		err_fax.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/error.png")));
		err_fax.setBounds(298, 267, 30, 28);
		panel_11.add(err_fax);
		
		err_mail = new JLabel("");
		listeErr.add(err_mail);
		err_mail.setVisible(false);
		err_mail.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/error.png")));
		err_mail.setBounds(298, 299, 26, 28);
		panel_11.add(err_mail);
		
		label = new JLabel("* Champs obligatoires");
		label.setForeground(Color.RED);
		label.setFont(new Font("Sitka Text", Font.BOLD, 14));
		label.setBounds(20, 19, 183, 47);
		panel_11.add(label);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Op\u00E9ration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(495, 458, 606, 93);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/Button-Add-icon.png")));
		btnAjouter.setBounds(10, 23, 106, 47);
		panel_2.add(btnAjouter);
		
		btnModifier = new JButton("Modifier");
		btnModifier.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/edit.png")));
		btnModifier.setBounds(126, 23, 115, 47);
		panel_2.add(btnModifier);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/Red X copie.png")));
		btnSupprimer.setBounds(251, 23, 110, 47);
		panel_2.add(btnSupprimer);
		
		btnNettoyer = new JButton("Nettoyer");
		btnNettoyer.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/clear.png")));
		btnNettoyer.setBounds(371, 23, 106, 47);
		panel_2.add(btnNettoyer);
		
		btnImprimer = new JButton("Imprimer");
		btnImprimer.setIcon(new ImageIcon(GestionFournisseur.class.getResource("/image/print-icon.png")));
		btnImprimer.setBounds(487, 23, 110, 47);
		panel_2.add(btnImprimer);
		fournisseurDAO= new FournisseurDAO();
		controlerFournisseur=new ControlerFournisseur(this);
		
		table.addMouseListener(controlerFournisseur);
		
		btnAjouter.addActionListener(controlerFournisseur);
		btnImprimer.addActionListener(controlerFournisseur);
		btnModifier.addActionListener(controlerFournisseur);
		btnSupprimer.addActionListener(controlerFournisseur);
		btnNettoyer.addActionListener(controlerFournisseur);
		charger();
		remove_title_bar();
	}
	void remove_title_bar() {
        putClientProperty("Gestion.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(new LineBorder(new Color(0, 0, 0)));

    }
	/*------------ La permettant de créer un objet Fournisseur à partir des champs text--------------*/
	public Fournisseur creer_Objet_Fournisseur_Saisi_Champ_PourAjout(){
		
		Fournisseur fournisseur = new Fournisseur();
		try {
			fournisseur.setNomF(textField_nom.getText().trim());
			fournisseur.setAdr(textField_adr.getText().trim());
			fournisseur.setCodeP(textField_codeP.getText().trim());
			fournisseur.setVille(textField_ville.getText().trim());
			fournisseur.setPays(textField_pays.getText().trim());
			fournisseur.setFax(textField_fax.getText().trim());
			fournisseur.setTele(textField_tel.getText().trim());
			fournisseur.setEmail(textField_mail.getText().trim());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return fournisseur;
	}
	public boolean verifierNom(){
		// chaine vide
		if(textField_nom.getText().length()==0 || textField_nom.getText()==null){
			err_nom.setToolTipText("Donner le nom !!");
			err_nom.setVisible(true);
			return false;
		}else{
			// Verifier le nom du fournisseur dans la liste des fournisseur
			Vector<Fournisseur> liste= controlerFournisseur.getListefournisseur();
			for (Fournisseur fournisseur : liste) {
				if(fournisseur.getNomF().equals(textField_nom.getText())){
					err_nom.setVisible(true);
					err_nom.setToolTipText("Ce nom existe déja !");
					return false;
				}
				
			}
		}
		err_nom.setVisible(false);
		return true;
	}
	public boolean verifirNomUpdate(){
		if(textField_nom.getText().length()==0 || textField_nom.getText()==null){
			err_nom.setToolTipText("Donner le nom !!");
			err_nom.setVisible(true);
			return false;
		}else{
			// Verifier si le nouveau n'appartient pas à un autre
			Vector<Fournisseur> liste= controlerFournisseur.getListefournisseur();
			if( ! (obtenir_Fournisseur_Selection().getNomF().equals(textField_nom.getText().trim()))){
				for (Fournisseur fournisseur : liste) {
					if(fournisseur.getNomF().equals(textField_nom.getText().trim())){
						err_nom.setVisible(true);
						err_nom.setToolTipText("Ce nom existe déja !");
						return false;
					}
				}
				
			}
		}
		err_nom.setVisible(false);
		return true;
	}
	public boolean verifierTel(){
		if(textField_tel.getText().length()!=0){
			
			try {
			Integer.parseInt(textField_tel.getText().toString());
			
			} catch (NumberFormatException e) {
				err_tel.setVisible(true);
				err_tel.setToolTipText("8 chiffres !!");
				return false;
			}
			if(textField_tel.getText().length()>8){
				err_tel.setVisible(true);
				err_tel.setToolTipText("8 chiffres !!");
				return false;
			}
		}
		err_tel.setVisible(false);
		return true;
		
	}
	public boolean verifierFax(){
		if(textField_fax.getText().length()!=0){
			
			try {
			Integer.parseInt(textField_fax.getText().toString());
			
			} catch (NumberFormatException e) {
				err_fax.setVisible(true);
				err_fax.setToolTipText("8 chiffres !!");
				return false;
			}
			if(textField_fax.getText().length()>8){
				err_fax.setVisible(true);
				err_fax.setToolTipText("8 chiffres !!");
				return false;
			}
		}
		err_fax.setVisible(false);
		return true;
		
	}
	public boolean verifierMail(){
		if(textField_mail.getText().length()!=0){
			if(textField_mail.getText().indexOf("@")==-1){
				err_mail.setVisible(true);
				err_mail.setToolTipText("Email Incorrect");
				return false ;
			}
			// Verifier s'il existe
			Vector<Fournisseur> liste =controlerFournisseur.getListefournisseur();
			for (Fournisseur fournisseur : liste) {
				if(fournisseur.getEmail().equals(textField_mail.getText())){
					err_mail.setVisible(true);
					err_mail.setToolTipText("Mail existe !");
					return false;
				}
			}
			
		}
		err_mail.setVisible(false);
		return true;
	}
	public boolean verifierMailUpdate(){
		if(textField_mail.getText().length()!=0){
			if(textField_mail.getText().indexOf("@")==-1){
				err_mail.setVisible(true);
				err_mail.setToolTipText("Email Incorrect");
				return false ;
			}
//			// Si l'ancien etait null
//			JOptionPane.showMessageDialog(null, obtenir_Fournisseur_Selection().getEmail());
//			if(obtenir_Fournisseur_Selection().getEmail().length()==0){
//				if( mailExiste(textField_mail.getText())){
//					err_mail.setVisible(true);
//					err_mail.setToolTipText("Mail existe !");
//					return false;
//				}
//				return true;
//			}
			// Si nouveau differentde l'ancien
			if(!obtenir_Fournisseur_Selection().getEmail().equals(textField_mail.getText().toString())){
				// Verifier s'il existe
				Vector<Fournisseur> liste =controlerFournisseur.getListefournisseur();
				for (Fournisseur fournisseur : liste) {
					if(fournisseur.getEmail().equals(textField_mail.getText())){
						err_mail.setVisible(true);
						err_mail.setToolTipText("Mail existe !");
						return false;
					}
				}
			}
			
			
		}
		err_mail.setVisible(false);
		return true;
	}
	public boolean mailExiste(String mail){
		Vector<Fournisseur> liste =controlerFournisseur.getListefournisseur();
		for (Fournisseur fournisseur : liste) {
			if(fournisseur.getEmail().equals(mail)){
				
				return false;
			}
		}
		return true;
	}
	public boolean verifier(){
		
		return verifierFax() & verifierMail() & verifierNom() & verifierTel();
	}
	public boolean verifierUpdate() {
		
		return  verifierFax() & verifierMailUpdate() & verifirNomUpdate() & verifierTel() ;
	}
	public void remplir_Champ_Fournisseur(Fournisseur fournisseur) {
		try {
			textField_nom.setText(fournisseur.getNomF());
			textField_adr.setText(fournisseur.getAdr());
			textField_codeP.setText(fournisseur.getCodeP());
			textField_ville.setText(fournisseur.getVille());
			textField_pays.setText(fournisseur.getPays());
			textField_fax.setText(String.valueOf(fournisseur.getFax()));
			textField_tel.setText(String.valueOf(fournisseur.getTele()));
			textField_mail.setText(fournisseur.getEmail());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	/*------------------ la methode pour obtenir_Fournisseur_Selection -----------------------*/
	public Fournisseur obtenir_Fournisseur_Selection(){
		
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setNumF((int)table.getValueAt(table.getSelectedRow(), 0));
		try {
			fournisseur.setNomF((String)table.getValueAt(table.getSelectedRow(),1));
		} catch (Exception e) {
			fournisseur.setNomF("");
		}
		try {
			fournisseur.setAdr((String)table.getValueAt(table.getSelectedRow(),2));
		} catch (Exception e) {
			fournisseur.setAdr("");
		}
		try {
			fournisseur.setCodeP((String)table.getValueAt(table.getSelectedRow(),3));
		} catch (Exception e) {
			fournisseur.setCodeP("");
		}
		try {
			fournisseur.setVille((String)table.getValueAt(table.getSelectedRow(),4));
		} catch (Exception e) {
			fournisseur.setVille("");
		}
		try {
			fournisseur.setPays((String)table.getValueAt(table.getSelectedRow(),5));
		} catch (Exception e) {
			fournisseur.setPays("");
		}
		try {
			fournisseur.setTele((String)table.getValueAt(table.getSelectedRow(),6));
		} catch (Exception e) {
			fournisseur.setTele("");
		}
		try {
			fournisseur.setFax((String)table.getValueAt(table.getSelectedRow(),7));
		} catch (Exception e) {
			fournisseur.setFax("");
		}
		try {
			fournisseur.setEmail(model_table.getValueAt(table.getSelectedRow(),8).toString());
		} catch (Exception e) {
			fournisseur.setEmail("");
		}
		
		return fournisseur;
	}
	public void charger(){
		Vector<Fournisseur> vec = fournisseurDAO.findAll();
		
		//String[] columns = {"Id_Participant", "Nom", "Prénom","Assisté"}; 
		Object[][] data= new Object[vec.size()][model_table.getColumnCount()];
		for (int i = 0; i < data.length; i++) {
			
				data[i][0]=vec.get(i).getNumF();
				data[i][1]=vec.get(i).getNomF();
				data[i][2]=vec.get(i).getAdr();
				data[i][3]=vec.get(i).getCodeP();
				data[i][4]=vec.get(i).getVille();
				data[i][5]=vec.get(i).getPays();
				data[i][6]=vec.get(i).getTele();
				data[i][7]=vec.get(i).getFax();	
				data[i][8]=vec.get(i).getEmail();
		}
		model_table.changeData(data);	
	}
	
	public void chargerTable_Fournisseur_Rechercher() {
		Vector<Fournisseur> vec ;
		String facteur= facteur(comboBox.getSelectedItem().toString());
		vec=controlerFournisseur.findCollection(facteur,"%"+textField_recherche.getText().trim()+"%");
		Object[][] data= new Object[vec.size()][model_table.getColumnCount()];
		
		if(vec.size()>0){
			
			for (int i = 0; i < data.length; i++) {
				
				data[i][0]=vec.get(i).getNumF();
				data[i][1]=vec.get(i).getNomF();
				data[i][2]=vec.get(i).getAdr();
				data[i][3]=vec.get(i).getCodeP();
				data[i][4]=vec.get(i).getVille();
				data[i][5]=vec.get(i).getPays();
				data[i][6]=vec.get(i).getTele();
				data[i][7]=vec.get(i).getFax();	
				data[i][8]=vec.get(i).getEmail();
			}
			model_table.changeData(data);	
		}		
		
	}
	public String  facteur(String facteur){
		String rep="";
		switch (facteur) {
		case "Email":
			rep= "EMAIL";
			break;
		case "Nom du Fournisseur":
			rep= "NOMF";
			break;
			
		default:
			break;
		}
		return rep;
	}
	public void nettoyer(){
		//gestionUtilisateur.getBtnNettoyer().requestFocusInWindow();
		textField_nom.setText("");
		textField_adr.setText("");
		textField_codeP.setText("");
		textField_tel.setText("");
		textField_mail.setText("");
		textField_pays.setText("");
		textField_ville.setText("");
		
		for (JLabel iterable_element : listeErr) {
			iterable_element.setVisible(false);
		}
		table.clearSelection();
	}
	public ModelTableau getModel_table() {
		return model_table;
	}
	public JTextField getTextField_nom() {
		return textField_nom;
	}

	public JTable getTable() {
		return table;
	}
	public JTextField getTextField_adr() {
		return textField_adr;
	}
	public JTextField getTextField_pays() {
		return textField_pays;
	}
	public JTextField getTextField_ville() {
		return textField_ville;
	}
	public JTextField getTextField_codeP() {
		return textField_codeP;
	}
	public JTextField getTextField_tel() {
		return textField_tel;
	}
	public JTextField getTextField_fax() {
		return textField_fax;
	}
	public JTextField getTextField_mail() {
		return textField_mail;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public FournisseurDAO getFournisseurDAO() {
		return fournisseurDAO;
	}
	public ControlerFournisseur getControlerFournisseur() {
		return controlerFournisseur;
	}
	public JButton getBtnAjouter() {
		return btnAjouter;
	}
	public JButton getBtnModifier() {
		return btnModifier;
	}
	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}
	public JButton getBtnNettoyer() {
		return btnNettoyer;
	}
	public JButton getBtnImprimer() {
		return btnImprimer;
	}
	public ArrayList<JLabel> getListeErr() {
		return listeErr;
	}
	public JLabel getErr_nom() {
		return err_nom;
	}
	public JLabel getErr_adr() {
		return err_adr;
	}
	public JLabel getErr_pst() {
		return err_pst;
	}
	public JLabel getErr_pays() {
		return err_pays;
	}
	public JLabel getErr_tel() {
		return err_tel;
	}
	public JLabel getErr_fax() {
		return err_fax;
	}
	public JLabel getErr_mail() {
		return err_mail;
	}
}
