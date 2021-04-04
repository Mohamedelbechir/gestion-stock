package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import classeMetier.Utilisateur;
import connxion_Requete.Connexion;
import controler.ControlerUtilisateur;
import dao.implement.UtilisateurDAO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import utility.ModelTableau;
import utility.TableCellRender;
import utility.VerifyUser;

import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.CellRendererPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionUtilisateur extends JInternalFrame {
	private JTable table;
	private ModelTableau model_table;
	private JTextField textField_nom;
	private JTextField textField_prenom;
	private JTextField textField_cin;
	private JTextField textField_login;
	private JTextField textField_pass;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnNettoyer;
	private JButton btnImprimer;
	public  JDateChooser dateChooser_age;
	private JLabel err_nom;
	private JTextField textField_recherche;
	private ControlerUtilisateur controlerUtilisateur;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnF;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnUtilisateur;
	private JLabel err_login;
	private JLabel err_pass;
	private JLabel err_prenom;
	private JLabel err_cin;
	private JLabel err_date;
	private ArrayList<JLabel> liste_Lab_err;
	private UtilisateurDAO utilisateurDAO; 
	private JLabel label_1;
	private JComboBox comboBox;
	private JPanel panel_1;
	public GestionUtilisateur() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(241, 142, 1111, 590);
		getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 40, 1091, 13);
		getContentPane().add(separator);
		
		liste_Lab_err= new ArrayList<>();
		JLabel lblGestionDesUtilisateurs = new JLabel("Gestion des Utilisateurs");
		lblGestionDesUtilisateurs.setFont(new Font("Sitka Text", Font.BOLD, 13));
		lblGestionDesUtilisateurs.setBounds(10, 11, 224, 30);
		getContentPane().add(lblGestionDesUtilisateurs);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Op\u00E9ration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(502, 464, 604, 87);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setIcon(new ImageIcon(GestionUtilisateur.class.getResource("/image/Button-Add-icon.png")));
		btnAjouter.setBounds(10, 22, 111, 47);
		panel_2.add(btnAjouter);
		
		btnModifier = new JButton("Modifier");
		btnModifier.setIcon(new ImageIcon(GestionUtilisateur.class.getResource("/image/edit.png")));
		btnModifier.setBounds(129, 22, 111, 47);
		panel_2.add(btnModifier);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setIcon(new ImageIcon(GestionUtilisateur.class.getResource("/image/Red X copie.png")));
		btnSupprimer.setBounds(250, 22, 111, 47);
		panel_2.add(btnSupprimer);
		
		btnNettoyer = new JButton("Nettoyer");
		btnNettoyer.setIcon(new ImageIcon(GestionUtilisateur.class.getResource("/image/clear.png")));
		btnNettoyer.setBounds(371, 22, 111, 47);
		panel_2.add(btnNettoyer);
		
		btnImprimer = new JButton("Imprimer");
		btnImprimer.setIcon(new ImageIcon(GestionUtilisateur.class.getResource("/image/print-icon.png")));
		btnImprimer.setBounds(492, 22, 102, 47);
		panel_2.add(btnImprimer);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(502, 64, 599, 389);
		getContentPane().add(panel_4);
		panel_4.setBorder(new TitledBorder(null, "Information Utilisateur", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_4.setLayout(null);
		
		dateChooser_age = new JDateChooser();
		dateChooser_age.setBounds(137, 263, 149, 28);
		((JTextField)dateChooser_age.getDateEditor().getUiComponent()).setEditable(false);
		panel_4.add(dateChooser_age);
		dateChooser_age.setDateFormatString("dd/MM/yyyy");
		
		JLabel lblAge = new JLabel("Date de Naissance");
		lblAge.setBounds(10, 263, 128, 26);
		panel_4.add(lblAge);
		lblAge.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		
		err_cin = new JLabel("");
		err_cin.setVisible(false);
		liste_Lab_err.add(err_cin);
		err_cin.setBounds(300, 224, 16, 26);
		panel_4.add(err_cin);
		err_cin.setIcon(new ImageIcon(GestionUtilisateur.class.getResource("/image/error.png")));
		
		textField_cin = new JTextField();
		
		textField_cin.setBounds(137, 230, 147, 26);
		panel_4.add(textField_cin);
		textField_cin.setColumns(10);
		
		textField_nom = new JTextField();
		
		textField_nom.setBounds(139, 121, 156, 26);
		panel_4.add(textField_nom);
		textField_nom.setColumns(10);
		
		textField_prenom = new JTextField();
		
		textField_prenom.setBounds(139, 156, 156, 28);
		panel_4.add(textField_prenom);
		textField_prenom.setColumns(10);
		
		err_prenom = new JLabel("");
		err_prenom.setVisible(false);
		liste_Lab_err.add(err_prenom);
		err_prenom.setBounds(299, 160, 29, 28);
		panel_4.add(err_prenom);
		err_prenom.setIcon(new ImageIcon(GestionUtilisateur.class.getResource("/image/error.png")));
		
		err_nom = new JLabel("");
		err_nom.setVisible(false);
		liste_Lab_err.add(err_nom);
		err_nom.setBounds(299, 125, 29, 26);
		panel_4.add(err_nom);
		err_nom.setIcon(new ImageIcon(GestionUtilisateur.class.getResource("/image/error.png")));
		
		JLabel lblCin = new JLabel("<html>Cin: <font color= 'red'>*</font></html>");
		lblCin.setBounds(10, 230, 54, 26);
		panel_4.add(lblCin);
		lblCin.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setBounds(10, 156, 54, 28);
		panel_4.add(lblPrnom);
		lblPrnom.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(10, 121, 54, 26);
		panel_4.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		
		err_date = new JLabel("");
		err_date.setVisible(false);
		liste_Lab_err.add(err_date);
		err_date.setBounds(298, 271, 16, 16);
		panel_4.add(err_date);
		err_date.setIcon(new ImageIcon(GestionUtilisateur.class.getResource("/image/error.png")));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(338, 58, 179, 192);
		panel_4.add(panel_3);
		panel_3.setBorder(new TitledBorder(null, "Identit\u00E9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setLayout(null);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(GestionUtilisateur.class.getResource("/image/file_image.png")));
		label_4.setBounds(10, 25, 158, 158);
		panel_3.add(label_4);
		
		JButton btnChoisir = new JButton("parcourir");
		btnChoisir.setBounds(338, 268, 91, 36);
		panel_4.add(btnChoisir);
		
		JLabel lblLogin = new JLabel("<html>Login: <font color= 'red'>*</font></html>");
		lblLogin.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblLogin.setBounds(10, 51, 128, 28);
		panel_4.add(lblLogin);
		
		JLabel lblMtDePasse = new JLabel("<html>Mot de Passe: <font color= 'red'>*</font></html>");
		lblMtDePasse.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblMtDePasse.setBounds(10, 85, 97, 28);
		panel_4.add(lblMtDePasse);
		
		JLabel lblStattue = new JLabel("Stattue");
		lblStattue.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblStattue.setBounds(10, 296, 46, 28);
		panel_4.add(lblStattue);
		
		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setBounds(133, 297, 73, 28);
		panel_4.add(rdbtnAdmin);
		
		rdbtnUtilisateur = new JRadioButton("Utilisateur");
		rdbtnUtilisateur.setSelected(true);
		rdbtnUtilisateur.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		buttonGroup.add(rdbtnUtilisateur);
		rdbtnUtilisateur.setBounds(209, 296, 91, 28);
		panel_4.add(rdbtnUtilisateur);
		
		textField_login = new JTextField();
		
		textField_login.setBounds(140, 51, 156, 28);
		panel_4.add(textField_login);
		textField_login.setColumns(10);
		
		textField_pass = new JTextField();
		textField_pass.setBounds(140, 85, 156, 28);
		panel_4.add(textField_pass);
		textField_pass.setColumns(10);
		
		JLabel lblSexe = new JLabel("Sexe");
		lblSexe.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblSexe.setBounds(10, 195, 46, 28);
		panel_4.add(lblSexe);
		
		rdbtnF = new JRadioButton("F");
		rdbtnF.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		buttonGroup_1.add(rdbtnF);
		rdbtnF.setBounds(197, 200, 38, 23);
		panel_4.add(rdbtnF);
		
		rdbtnM = new JRadioButton("M");
		rdbtnM.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		rdbtnM.setSelected(true);
		buttonGroup_1.add(rdbtnM);
		rdbtnM.setBounds(139, 200, 46, 23);
		panel_4.add(rdbtnM);
		
		err_pass = new JLabel("");
		err_pass.setVisible(false);
		liste_Lab_err.add(err_pass);
		err_pass.setIcon(new ImageIcon(GestionUtilisateur.class.getResource("/image/error.png")));
		err_pass.setBounds(299, 92, 29, 26);
		panel_4.add(err_pass);
		
		err_login = new JLabel("");
		err_login.setVisible(false);
		liste_Lab_err.add(err_login);
		err_login.setIcon(new ImageIcon(GestionUtilisateur.class.getResource("/image/error.png")));
		err_login.setBounds(299, 51, 29, 26);
		panel_4.add(err_login);
		
		label_1 = new JLabel("* Champs obligatoires");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Sitka Text", Font.BOLD, 14));
		label_1.setBounds(10, 11, 193, 47);
		panel_4.add(label_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Liste Utilisateurs", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 64, 482, 487);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		model_table= new ModelTableau(new Object[][]{
			{null,null,null,null,null,null,null,null}
		},new String[]{
				"IDUTIL",
				"LOGIN", 
				"MOT_PASS" , 
				"CIN" , 
				"NOM" , 
				"PRENOM" , 
				"DATE_NAIS", 
				"STATUE",
				"SEXE"
				
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 462, 411);
		panel.add(scrollPane);
		table = new JTable();
		/*Jtable not draggble , not resizble*/
		table.getTableHeader().setReorderingAllowed(false);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setResizingAllowed(false);
		table.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(28);
		scrollPane.setViewportView(table);
		table.setModel(model_table);
		
		table.setDefaultRenderer(Object.class, new TableCellRender());
		table.getTableHeader().setDefaultRenderer(new TableCellRender());
		
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		
		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(0);
		table.getColumnModel().getColumn(2).setWidth(0);
		
		table.getColumnModel().getColumn(6).setMinWidth(0);
		table.getColumnModel().getColumn(6).setMaxWidth(0);
		table.getColumnModel().getColumn(6).setWidth(0);
		
		table.getColumnModel().getColumn(8).setMinWidth(0);
		table.getColumnModel().getColumn(8).setMaxWidth(0);
		table.getColumnModel().getColumn(8).setWidth(0);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 26, 197, 28);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textField_recherche = new JTextField("Rechercher");
		textField_recherche.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField_recherche.setBounds(0, 0, 167, 28);
		panel_1.add(textField_recherche);
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
			}
		});
		textField_recherche.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		textField_recherche.setForeground(new Color(128, 128, 128));
		textField_recherche.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField_recherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				chargerTable_Utilisateur_Rechercher();
			}
		});
		textField_recherche.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setBounds(168, 0, 29, 28);
		panel_1.add(label);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField_recherche.setText("Rechercher");				
				textField_recherche.setForeground(new Color(128, 128, 128));
			}
		});
		label.setToolTipText("vider");
		label.setIcon(new ImageIcon(GestionUtilisateur.class.getResource("/image/Red X copie.png")));
		
		JLabel lblRercherPar = new JLabel("Rercher par :");
		lblRercherPar.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblRercherPar.setBounds(228, 26, 92, 28);
		panel.add(lblRercherPar);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		comboBox.addItem("Login");
		comboBox.addItem("Nom");
		comboBox.addItem("Prénom");
		comboBox.setBounds(330, 26, 142, 28);
		panel.add(comboBox);
		
		 
		utilisateurDAO= new UtilisateurDAO();
		// Ajout de controler 
		controlerUtilisateur = new ControlerUtilisateur(this);
		
		btnAjouter.addActionListener(controlerUtilisateur);
		btnModifier.addActionListener(controlerUtilisateur);
		table.addMouseListener(controlerUtilisateur);
		
		btnNettoyer.addActionListener(controlerUtilisateur);
		btnSupprimer.addActionListener(controlerUtilisateur);
		btnImprimer.addActionListener(controlerUtilisateur);
		charger();
		remove_title_bar();
	}
	
	
	/*public boolean verifierLogin(boolean ajout){
		if(textField_login.getText().length()==0 || textField_login.getText()==null){
			err_login.setVisible(true);
			err_login.setToolTipText("Veillez saisir le login");
			return false;
			
			// Le cas de l'ajout verifier si login appartient à un autre
		}else if(ajout){
			for (Utilisateur utilisateur : controlerUtilisateur.getListeUtilisateur()) {
				
				if(utilisateur.getLogin().equals(textField_login.getText())){
					err_login.setVisible(true);
					err_login.setToolTipText("Ce login existe déja !!");
					return false;
				}
				
			}
			//La modification verifier si le ancien login differenr
		}else{
			String logAncien= obtenir_Utilisateur_Selection().getLogin();
			String logNouveau= textField_login.getText();
			// Login ancien different de nouveau
			if(!logAncien.equals(logNouveau)){
				// Verifier s'il correspont à un login existant dans la liste
				for (Utilisateur utilisateur : controlerUtilisateur.getListeUtilisateur()) {
					
					if(utilisateur.getLogin().equals(textField_login.getText())){
						err_login.setVisible(true);
						err_login.setToolTipText("Ce login existe déja !!");
						return false;
					}
					
				}
			}
		}
		err_login.setVisible(false);
		return true;
	}*/
	public boolean verifierLoginAjout(){
		return VerifyUser.verifierLoginAjout(textField_login, err_login);
	}
	public boolean verifierLoginApdate(){
		return VerifyUser.verifierLoginUpdate(obtenir_Utilisateur_Selection(), textField_login, err_login);
	}
	public boolean verifierPass(){
		return VerifyUser.verifierPass(textField_pass, err_pass);
	}
	/*public boolean verifierPass(){
		if(textField_pass.getText().length()==0 || textField_pass.getText()==null){
			
			err_pass.setToolTipText("Veillez saisir le mot de passe !!");
			err_pass.setVisible(true);			
			return false;
		}else{
			// verifier la taille de mot de pass
			if(textField_pass.getText().length()<4){
				
				err_pass.setVisible(true);
				err_pass.setToolTipText("Taille doit etre plus que 4 ! ");
				return false;
			}
			
		}
		
		err_pass.setVisible(false);
		return true;
	}*/
	public boolean verifierCinAjout(){
		return VerifyUser.verifierCinAjout(textField_cin, err_cin);
		
	}
	public boolean verifierCinUpdate(){
		return VerifyUser.verifierCinApdate(obtenir_Utilisateur_Selection(),textField_cin, err_cin);
		
	}
	/*public boolean verifierCin(boolean ajout){
		// verifier si chaine vide
		if(textField_cin.getText().length()==0 || textField_cin.getText()==null){
			err_cin.setToolTipText("Donner le cin");
			err_cin.setVisible(true);
			return false;
			
		}else if( !textField_cin.getText().matches("[0-9]*") || ! (textField_cin.getText().length()==8)){
					err_cin.setToolTipText("Cin 8 chiffre !!");
					err_cin.setVisible(true);	
					return false;
		}else{
			// Verifire ci le cin exite deja
			if(ajout){
				for (Utilisateur utilisateur : controlerUtilisateur.getListeUtilisateur()) {
					int cinUtil=(int)utilisateur.getCin();
					int cinC=(int)Integer.parseInt(textField_cin.getText());
					if(cinC==cinUtil){
						err_cin.setVisible(true);
						err_cin.setToolTipText("Ce Cin existe déja !!");
						return false;
					}
				}
			}else{
				// Modification
				// Si ancien cin est different du nouveau cin
				int cinAncien = (int)obtenir_Utilisateur_Selection().getCin();
				int cinNouveau= (int)Integer.parseInt(textField_cin.getText());
				if(cinAncien!=cinNouveau){
					for (Utilisateur utilisateur : controlerUtilisateur.getListeUtilisateur()) {
						int cinUtil=(int)utilisateur.getCin();
						cinNouveau=(int)Integer.parseInt(textField_cin.getText());
						if(cinNouveau==cinUtil){
							err_cin.setVisible(true);
							err_cin.setToolTipText("Ce Cin existe déja !!");
							return false;
						}
					}
				}
				
			}
			
		}
		err_cin.setVisible(false);
		return true;
	}*/
	
	/*public boolean verifierNom(){
		// chaine vide
		if(textField_nom.getText().length()==0 || textField_nom.getText()==null){
			err_nom.setToolTipText("Donner le nom !!");
			err_nom.setVisible(true);
			return false;
		}
		err_nom.setVisible(false);
		return true;
	}*/
	public boolean verifierNom(){
		return VerifyUser.verifierNom(textField_nom, err_nom);
	}
	
	/*public boolean verifierPrenom(){
		// chaine vide
		if(textField_prenom.getText().length()==0 || textField_prenom.getText()==null){
			err_prenom.setToolTipText("Donner le prenom !!");
			err_prenom.setVisible(true);
			return false;
		}
		err_prenom.setVisible(false);
		return true;
	}*/
	public boolean verifierPrenom(){
		return VerifyUser.verifierPrenom(textField_prenom, err_prenom);
	}
	public void charger(){
		new UtilisateurDAO();
		Vector<Utilisateur> vec = UtilisateurDAO.findAll();
		
		//String[] columns = {"Id_Participant", "Nom", "Prénom","Assisté"}; 
		Object[][] data= new Object[vec.size()][model_table.getColumnCount()];
		for (int i = 0; i < data.length; i++) {
			
				data[i][0]=vec.get(i).getIdUtil();
				data[i][1]=vec.get(i).getLogin();
				data[i][2]=vec.get(i).getPass();
				data[i][3]=vec.get(i).getCin();
				data[i][4]=vec.get(i).getNom();
				data[i][5]=vec.get(i).getPrenom();
				data[i][6]=vec.get(i).getDate_nais();
				data[i][7]=vec.get(i).getStatue();	
				data[i][8]=vec.get(i).getSexe();
		}
		model_table.changeData(data);	
	}
	public void chargerTable_Utilisateur_Rechercher(){
		
		
		Vector<Utilisateur> vec ;
		String facteur= facteur(comboBox.getSelectedItem().toString());
		vec=controlerUtilisateur.findCollection(facteur,"%"+textField_recherche.getText()+"%");
		Object[][] data= new Object[vec.size()][model_table.getColumnCount()];
		
		if(vec.size()>0){
			
			for (int i = 0; i < data.length; i++) {
				
					data[i][0]=vec.get(i).getIdUtil();
					data[i][1]=vec.get(i).getLogin();
					data[i][2]=vec.get(i).getPass();
					data[i][3]=vec.get(i).getCin();
					data[i][4]=vec.get(i).getNom();
					data[i][5]=vec.get(i).getPrenom();
					data[i][6]=vec.get(i).getDate_nais();
					data[i][7]=vec.get(i).getStatue();	
					data[i][8]=vec.get(i).getSexe();
			}
			model_table.changeData(data);	
		}		
		
	}
	public String  facteur(String facteur){
		String rep="";
		switch (facteur) {
		case "Login":
			rep= "LOGIN";
			break;
		case "Nom":
			rep= "NOM";
			break;
		case "Prénom":
			rep= "PRENOM";
			break;	
		default:
			break;
		}
		return rep;
	}
	public boolean verifier(){
		boolean bol=true;
		return bol;
	}
	public boolean verifir_Modif(){
		boolean bol=true;
		return bol;
	}
	/*------------------ la methode pour remplir les champs Salle------------------------*/
	public void remplir_Champ_Utilisateur(Utilisateur utilisateur){
		
		textField_login.setText(utilisateur.getLogin());
		textField_pass.setText(utilisateur.getPass());
		textField_cin.setText(utilisateur.getCin().toString());
		textField_nom.setText(utilisateur.getNom());
		textField_prenom.setText(utilisateur.getPrenom());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date_Naissance=null;
				
		try {
			date_Naissance = dateFormat.parse(utilisateur.getDate_nais());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dateChooser_age.setDate(date_Naissance);
		try {
			if (utilisateur.getStatue().equals("admin")){
				rdbtnAdmin.setSelected(true);
				
			} else {
				rdbtnUtilisateur.setSelected(true);
			}
			if (utilisateur.getSexe().equals("masculin")) {
				rdbtnM.setSelected(true);
				
			}else{
				rdbtnF.setSelected(true);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	/*------------------ la methode pour obtenir_Utilisateur_Selection -----------------------*/
	public Utilisateur obtenir_Utilisateur_Selection(){
		
		Utilisateur utilisateur=new Utilisateur();
		utilisateur.setIdUtil(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
		utilisateur.setLogin((String)table.getValueAt(table.getSelectedRow(), 1));
		utilisateur.setPass((String)table.getValueAt(table.getSelectedRow(), 2));
		utilisateur.setCin((int)table.getValueAt(table.getSelectedRow(), 3));
		utilisateur.setNom((String)table.getValueAt(table.getSelectedRow(), 4));
		utilisateur.setPrenom((String)table.getValueAt(table.getSelectedRow(), 5));
		utilisateur.setDate_nais((String)table.getValueAt(table.getSelectedRow(), 6));
		utilisateur.setStatue((String)table.getValueAt(table.getSelectedRow(), 7));
		utilisateur.setSexe((String)table.getValueAt(table.getSelectedRow(), 8));
		return utilisateur;
	}
	/*------------ La permettant de créer un objet Utilisateur à partir des champs text--------------*/
	public Utilisateur creer_Objet_Utilisateur_Saisi_Champ_PourAjout(){
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setLogin(textField_login.getText());
		utilisateur.setPass(textField_pass.getText());
		utilisateur.setCin(Integer.parseInt(textField_cin.getText()));
		utilisateur.setNom(textField_nom.getText());
		utilisateur.setPrenom(textField_prenom.getText());
		utilisateur.setDate_nais(((JTextField)dateChooser_age.getDateEditor().getUiComponent()).getText());
		if(rdbtnM.isSelected())
			utilisateur.setSexe("masculin");
		
		else if(rdbtnF.isSelected())
			utilisateur.setSexe("féminin");
		
		/*------------------------------*/
		if(rdbtnAdmin.isSelected())
			utilisateur.setStatue("admin");
		
		else if(rdbtnUtilisateur.isSelected())
			utilisateur.setStatue("utilisateur");
		
		return utilisateur;
	}
	public void nettoyer(){
		//gestionUtilisateur.getBtnNettoyer().requestFocusInWindow();
		textField_cin.setText("");
		textField_login.setText("");
		textField_nom.setText("");
		textField_pass.setText("");
		textField_prenom.setText("");
		JTextField date= (JTextField) dateChooser_age.getDateEditor().getUiComponent();
		date.setText("");
		for (JLabel iterable_element :liste_Lab_err) {
			iterable_element.setVisible(false);
		}
		
	}
	
	void remove_title_bar() {
        putClientProperty("Gestion.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(new LineBorder(new Color(0, 0, 0)));

    }
	

	public JTable getTable() {
		return table;
	}



	public JTextField getTextField_nom() {
		return textField_nom;
	}



	public JTextField getTextField_prenom() {
		return textField_prenom;
	}



	public JTextField getTextField_cin() {
		return textField_cin;
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

	public JDateChooser getDateChooser_age() {
		return dateChooser_age;
	}

	public ModelTableau getModel_table() {
		return model_table;
	}

	public JTextField getTextField_login() {
		return textField_login;
	}

	public JTextField getTextField_pass() {
		return textField_pass;
	}

	public JRadioButton getRdbtnM() {
		return rdbtnM;
	}

	public JRadioButton getRdbtnF() {
		return rdbtnF;
	}

	public JRadioButton getRdbtnAdmin() {
		return rdbtnAdmin;
	}

	public JRadioButton getRdbtnUtilisateur() {
		return rdbtnUtilisateur;
	}

	public JLabel getErr_nom() {
		return err_nom;
	}

	public JTextField getTextField_recherche() {
		return textField_recherche;
	}

	public ControlerUtilisateur getControlerUtilisateur() {
		return controlerUtilisateur;
	}

	public JLabel getErr_login() {
		return err_login;
	}

	public JLabel getErr_pass() {
		return err_pass;
	}

	public JLabel getErr_prenom() {
		return err_prenom;
	}

	public JLabel getErr_cin() {
		return err_cin;
	}

	public JLabel getErr_date() {
		return err_date;
	}

	public ArrayList<JLabel> getListe_Lab_err() {
		return liste_Lab_err;
	}

	public UtilisateurDAO getUtilisateurDAO() {
		return utilisateurDAO;
	}
	
}
