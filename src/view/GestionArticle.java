package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import classeMetier.Article;
import classeMetier.Categorie;
import classeMetier.Fournisseur;
import classeMetier.Utilisateur;
import controler.ControlerArticle;
import controler.ControlerCategorie;
import controler.ControlerFournisseur;
import dao.implement.CategorieDAO;
import dao.implement.UtilisateurDAO;
import utility.ModelTableau;
import utility.TableCellRender;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionArticle extends JInternalFrame {
	private JTable table_Article;
	private ModelTableau model_table_Article;
	private ModelTableau model_table_Categorie;
	private ControlerCategorie controlerCategorie;
	private CategorieDAO categorieDAO;
	private ControlerArticle controlerArticle;
	private JTextField textField_desi;
	private JTextField textField_unit;
	private JTextField textField_qt;
	private JTextField textField_prix;
	private JTextField textField_empl;
	private JTable table_Categorie;
	private JTextField textField_nomCat;
	private JTextField textField_Recherche;
	private JButton btnAjouter_Art;
	private JButton btnModifier_Art;
	private JButton btnSupprimer_Art;
	private JButton btnNettoyer_Art;
	private JButton btnImprimer_Art;
	private JButton btnAjouter_Cat;
	private JButton btnModifier_Cat;
	private JButton btnSupprimer_Cat;
	private JButton btnImprimerLaListe_Cat;
	private JLabel err_nomCat;
	private JTextArea textArea_DesCat;
	private JTextField textField_nom_cat;
	private JComboBox comboBox_Cat;
	private JLabel err_desi;
	private JLabel err_unit;
	private JLabel err_qt;
	private JLabel err_prix;
	private JLabel err_empl;
	private JLabel err_cat;
	private JButton btnNettoyerCat;
	private JPanel panel_7;
	private JLabel label_3;
	private JPanel panel_8;
	private JLabel label_4;
	private JComboBox comboBox_Fourn;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionArticle frame = new GestionArticle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionArticle() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(241, 142, 1111, 590);
		getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 40, 1091, 13);
		getContentPane().add(separator);
		
		JLabel lblGestionDesUtilisateurs = new JLabel("Gestion des Articles");
		lblGestionDesUtilisateurs.setFont(new Font("Sitka Text", Font.BOLD, 13));
		lblGestionDesUtilisateurs.setBounds(10, 11, 224, 30);
		getContentPane().add(lblGestionDesUtilisateurs);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 52, 1091, 500);
		getContentPane().add(tabbedPane);
		
		JPanel panel_Gestion_Article = new JPanel();
		tabbedPane.addTab("Gestion des Articles", null, panel_Gestion_Article, null);
		panel_Gestion_Article.setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 469, 450);
		panel_Gestion_Article.add(panel);
		panel.setBorder(new TitledBorder(null, "Listes des Articles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 442, 360);
		panel.add(scrollPane);
		
		model_table_Article= new ModelTableau(new Object[][]{
			{null,null,null,null,null,null,null,null}
		},new String[]{
				"CODEART", 
				"DESIGNATION", 
				"UNITE" , 
				"QUANTITE" , 
				"PRIX" , 
				"EMPLACEMENT",
				"IDCATEG",
				"IDF"
				
		});
		model_table_Categorie=new ModelTableau(new Object[][]{
			{null,null,null}
		},new String[]{
				"IDCATEG",
				"NOM CATEGORIE", 
				"DESCRIPTION",
				
		});
		
		table_Article = new JTable();
		table_Article.getTableHeader().setReorderingAllowed(false);
		table_Article.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		table_Article.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Article.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Article.setRowHeight(28);
		scrollPane.setViewportView(table_Article);
		table_Article.setModel(model_table_Article);
		
		panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		panel_7.setBounds(10, 21, 190, 28);
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		textField_Recherche = new JTextField("Donner la designation");
		textField_Recherche.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!textField_Recherche.getText().equals("Donner la designation")){
					textField_Recherche.setText("Donner la designation");
					textField_Recherche.setForeground(new Color(128, 128, 128));
				}
					
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				if(textField_Recherche.getText().equals("Donner la designation")){
					textField_Recherche.setForeground(Color.BLUE);
					textField_Recherche.setText("");
				}
				nettoyerChampArt();
			}
		});
		textField_Recherche.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		textField_Recherche.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Recherche.setForeground(new Color(128, 128, 128));
		textField_Recherche.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField_Recherche.setBounds(0, 0, 164, 28);
		panel_7.add(textField_Recherche);
		textField_Recherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				chargerTable_Article_Rechercher();
			}

			
		});
		textField_Recherche.setColumns(10);
		
		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField_Recherche.setText("");
			}
		});
		label_2.setBounds(163, 0, 27, 28);
		panel_7.add(label_2);
		label_2.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/Red X copie.png")));
		
		JLabel lblRercherPar = new JLabel("Rercher par ");
		lblRercherPar.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblRercherPar.setBounds(210, 21, 79, 28);
		panel.add(lblRercherPar);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(299, 21, 153, 28);
		panel.add(comboBox_1);
		
		table_Article.setDefaultRenderer(Object.class, new TableCellRender());
		table_Article.getTableHeader().setDefaultRenderer(new TableCellRender());
		
		
		
		table_Article.getColumnModel().getColumn(0).setMinWidth(0);
		table_Article.getColumnModel().getColumn(0).setMaxWidth(0);
		table_Article.getColumnModel().getColumn(0).setWidth(0);
		
		table_Article.getColumnModel().getColumn(6).setMinWidth(0);
		table_Article.getColumnModel().getColumn(6).setMaxWidth(0);
		table_Article.getColumnModel().getColumn(6).setWidth(0);
		
		table_Article.getColumnModel().getColumn(7).setMinWidth(0);
		table_Article.getColumnModel().getColumn(7).setMaxWidth(0);
		table_Article.getColumnModel().getColumn(7).setWidth(0);
		
		table_Article.getColumnModel().getColumn(1).setMinWidth(150);
		table_Article.getColumnModel().getColumn(1).setMaxWidth(150);
		table_Article.getColumnModel().getColumn(1).setWidth(150);
		
		table_Article.getColumnModel().getColumn(5).setMinWidth(150);
		table_Article.getColumnModel().getColumn(5).setMaxWidth(150);
		table_Article.getColumnModel().getColumn(5).setWidth(150);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(489, 12, 587, 369);
		panel_Gestion_Article.add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Informations Article", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		JLabel lblDsignation = new JLabel("<html>D\u00E9signation: <font color='red'>*</font> </html>");
		lblDsignation.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblDsignation.setBounds(20, 59, 134, 28);
		panel_1.add(lblDsignation);
		
		JLabel lblUnit = new JLabel("Unit\u00E9");
		lblUnit.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblUnit.setBounds(20, 91, 134, 28);
		panel_1.add(lblUnit);
		
		JLabel lblQuantit = new JLabel("Quantit\u00E9");
		lblQuantit.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblQuantit.setBounds(20, 123, 134, 28);
		panel_1.add(lblQuantit);
		
		JLabel lblPrix = new JLabel("<html>Prix: <font color='red'>*</font> </html>");
		lblPrix.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblPrix.setBounds(20, 155, 134, 28);
		panel_1.add(lblPrix);
		
		JLabel lblEmplacement = new JLabel("Emplacement");
		lblEmplacement.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblEmplacement.setBounds(20, 187, 134, 28);
		panel_1.add(lblEmplacement);
		
		textField_desi = new JTextField();
		textField_desi.setBounds(164, 59, 165, 28);
		panel_1.add(textField_desi);
		textField_desi.setColumns(10);
		
		textField_unit = new JTextField();
		textField_unit.setColumns(10);
		textField_unit.setBounds(164, 91, 165, 28);
		panel_1.add(textField_unit);
		
		textField_qt = new JTextField();
		textField_qt.setColumns(10);
		textField_qt.setBounds(164, 123, 165, 28);
		panel_1.add(textField_qt);
		
		textField_prix = new JTextField();
		textField_prix.setColumns(10);
		textField_prix.setBounds(164, 155, 165, 28);
		panel_1.add(textField_prix);
		
		textField_empl = new JTextField();
		textField_empl.setColumns(10);
		textField_empl.setBounds(164, 187, 165, 28);
		panel_1.add(textField_empl);
		
		JLabel label = new JLabel("");
		label.setBounds(339, 30, 46, 14);
		panel_1.add(label);
		
		JLabel lblCategorie = new JLabel("<html>Categorie: <font color='red'>*</font> </html>");
		lblCategorie.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblCategorie.setBounds(20, 219, 134, 28);
		panel_1.add(lblCategorie);
		
		comboBox_Cat = new JComboBox();
		comboBox_Cat.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		comboBox_Cat.setBounds(164, 219, 165, 28);
		panel_1.add(comboBox_Cat);
		
		JLabel lblChampsObligatoires = new JLabel("* Champs obligatoires");
		lblChampsObligatoires.setForeground(Color.RED);
		lblChampsObligatoires.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblChampsObligatoires.setBounds(20, 11, 193, 47);
		panel_1.add(lblChampsObligatoires);
		
		err_desi = new JLabel("");
		err_desi.setVisible(false);
		err_desi.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/error.png")));
		err_desi.setBounds(339, 59, 46, 28);
		panel_1.add(err_desi);
		
		err_unit = new JLabel("");
		err_unit.setVisible(false);
		err_unit.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/error.png")));
		err_unit.setBounds(339, 91, 46, 28);
		panel_1.add(err_unit);
		
		err_qt = new JLabel("");
		err_qt.setVisible(false);
		err_qt.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/error.png")));
		err_qt.setBounds(339, 123, 46, 28);
		panel_1.add(err_qt);
		
		err_prix = new JLabel("");
		err_prix.setVisible(false);
		err_prix.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/error.png")));
		err_prix.setBounds(339, 155, 46, 28);
		panel_1.add(err_prix);
		
		err_empl = new JLabel("");
		err_empl.setVisible(false);
		err_empl.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/error.png")));
		err_empl.setBounds(339, 187, 46, 28);
		panel_1.add(err_empl);
		
		err_cat = new JLabel("");
		err_cat.setVisible(false);
		err_cat.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/error.png")));
		err_cat.setBounds(339, 219, 46, 24);
		panel_1.add(err_cat);
		
		label_4 = new JLabel("<html>Fournisseur: <font color='red'>*</font> </html>");
		label_4.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		label_4.setBounds(20, 248, 134, 28);
		panel_1.add(label_4);
		
		comboBox_Fourn = new JComboBox();
		comboBox_Fourn.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		comboBox_Fourn.setBounds(164, 251, 165, 28);
		panel_1.add(comboBox_Fourn);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Operation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(485, 392, 591, 69);
		panel_Gestion_Article.add(panel_6);
		panel_6.setLayout(null);
		
		btnAjouter_Art = new JButton("Ajouter");
		btnAjouter_Art.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/Button-Add-icon.png")));
		btnAjouter_Art.setBounds(10, 18, 99, 40);
		panel_6.add(btnAjouter_Art);
		
		btnModifier_Art = new JButton("modifier");
		btnModifier_Art.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/edit.png")));
		btnModifier_Art.setBounds(119, 18, 103, 40);
		panel_6.add(btnModifier_Art);
		
		btnSupprimer_Art = new JButton("Supprimer");
		btnSupprimer_Art.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/Red X copie.png")));
		btnSupprimer_Art.setBounds(232, 18, 115, 40);
		panel_6.add(btnSupprimer_Art);
		
		btnImprimer_Art = new JButton("Imprimer");
		btnImprimer_Art.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/print-icon.png")));
		btnImprimer_Art.setBounds(482, 18, 99, 40);
		panel_6.add(btnImprimer_Art);
		
		btnNettoyer_Art = new JButton("Nettoyer");
		btnNettoyer_Art.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/clear.png")));
		btnNettoyer_Art.setBounds(357, 18, 115, 40);
		panel_6.add(btnNettoyer_Art);
		
		JPanel panel_2 = new JPanel();
		panel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tabbedPane.addTab("Categories", null, panel_2, null);
		tabbedPane.setForegroundAt(1, new Color(0, 0, 0));
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Liste des Categories", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 11, 440, 450);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 62, 420, 377);
		panel_3.add(scrollPane_1);
		
		table_Categorie = new JTable();
		table_Categorie.getTableHeader().setReorderingAllowed(false);
		table_Categorie.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		table_Categorie.setRowHeight(28);
		table_Categorie.setModel(model_table_Categorie);
		scrollPane_1.setViewportView(table_Categorie);
		
		panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 255, 255));
		panel_8.setBounds(10, 23, 298, 28);
		panel_3.add(panel_8);
		panel_8.setLayout(null);
		
		textField_nom_cat = new JTextField("Donner le nom de la categorie");
		textField_nom_cat.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField_nom_cat.setBounds(0, 0, 265, 28);
		panel_8.add(textField_nom_cat);
		textField_nom_cat.setHorizontalAlignment(SwingConstants.CENTER);
		textField_nom_cat.setForeground(new Color(128, 128, 128));
		textField_nom_cat.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		textField_nom_cat.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!textField_nom_cat.getText().equals("Donner le nom de la categorie")){
					textField_nom_cat.setText("Donner len nom de la categorie");
					textField_nom_cat.setForeground(new Color(128, 128, 128));
				}
					
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				if(textField_nom_cat.getText().equals("Donner le nom de la categorie")){
					textField_nom_cat.setForeground(Color.BLUE);
					textField_nom_cat.setText("");
				}
				nettoyerChampCat();
			}
		});
		textField_nom_cat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				chargerTable_Categorie_Rechercher();
			}

			
		});
		textField_nom_cat.setColumns(10);
		
		label_3 = new JLabel("");
		label_3.setBounds(267, 0, 31, 28);
		panel_8.add(label_3);
		label_3.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/Red X copie.png")));
		
		
		
		table_Categorie.getColumnModel().getColumn(0).setMinWidth(0);
		table_Categorie.getColumnModel().getColumn(0).setMaxWidth(0);
		table_Categorie.getColumnModel().getColumn(0).setWidth(0);
		
		table_Categorie.setDefaultRenderer(Object.class, new TableCellRender());
		table_Categorie.getTableHeader().setDefaultRenderer(new TableCellRender());
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Informaton Categorie", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(460, 11, 616, 367);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNomCategorie = new JLabel("<html>Nom: <font color='red'>*</font></html>");
		lblNomCategorie.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblNomCategorie.setBounds(10, 50, 46, 28);
		panel_4.add(lblNomCategorie);
		
		textField_nomCat = new JTextField();
		textField_nomCat.setBounds(104, 50, 299, 28);
		panel_4.add(textField_nomCat);
		textField_nomCat.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblDescription.setBounds(8, 207, 86, 28);
		panel_4.add(lblDescription);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(104, 89, 502, 267);
		panel_4.add(scrollPane_2);
		
		textArea_DesCat = new JTextArea();
		textArea_DesCat.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane_2.setViewportView(textArea_DesCat);
		
		err_nomCat = new JLabel("");
		err_nomCat.setVisible(false);
		err_nomCat.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/error.png")));
		err_nomCat.setBounds(408, 50, 25, 28);
		panel_4.add(err_nomCat);
		
		JLabel label_1 = new JLabel("* Champs obligatoires");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Sitka Text", Font.BOLD, 14));
		label_1.setBounds(10, 11, 193, 47);
		panel_4.add(label_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Operation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(460, 389, 616, 72);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		btnAjouter_Cat = new JButton("Ajouter");
		btnAjouter_Cat.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAjouter_Cat.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/Button-Add-icon.png")));
		btnAjouter_Cat.setBounds(10, 21, 111, 40);
		panel_5.add(btnAjouter_Cat);
		
		btnModifier_Cat = new JButton("modifier");
		btnModifier_Cat.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnModifier_Cat.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/edit.png")));
		btnModifier_Cat.setBounds(131, 21, 111, 40);
		panel_5.add(btnModifier_Cat);
		
		btnSupprimer_Cat = new JButton("supprimer");
		btnSupprimer_Cat.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSupprimer_Cat.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/Red X copie.png")));
		btnSupprimer_Cat.setBounds(252, 21, 111, 40);
		panel_5.add(btnSupprimer_Cat);
		
		btnImprimerLaListe_Cat = new JButton("<html><center>Imprimer <br>la liste</center></html>");
		btnImprimerLaListe_Cat.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnImprimerLaListe_Cat.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/print-icon.png")));
		btnImprimerLaListe_Cat.setBounds(497, 21, 111, 40);
		panel_5.add(btnImprimerLaListe_Cat);
		
		btnNettoyerCat = new JButton("Nettoyer");
		btnNettoyerCat.setIcon(new ImageIcon(GestionArticle.class.getResource("/image/clear.png")));
		btnNettoyerCat.setBounds(373, 21, 111, 40);
		panel_5.add(btnNettoyerCat);
		
		
		
		
		categorieDAO= new CategorieDAO();
		controlerCategorie=new ControlerCategorie(this);
		controlerArticle= new ControlerArticle(this);
		
		btnAjouter_Cat.addActionListener(controlerCategorie);
		btnModifier_Cat.addActionListener(controlerCategorie);
		btnSupprimer_Cat.addActionListener(controlerCategorie);
		btnNettoyerCat.addActionListener(controlerCategorie);
		btnImprimerLaListe_Cat.addActionListener(controlerCategorie);
		
		btnAjouter_Art.addActionListener(controlerArticle);
		btnModifier_Art.addActionListener(controlerArticle);
		btnSupprimer_Art.addActionListener(controlerArticle);
		btnNettoyer_Art.addActionListener(controlerArticle);
		btnImprimer_Art.addActionListener(controlerArticle);
		
		table_Article.addMouseListener(controlerArticle);
		table_Categorie.addMouseListener(controlerCategorie);
		chargerTableCat();
		chargerComboCat_ComboFourni();
		chargerTableArt();
		remove_title_bar();

	}
	void remove_title_bar() {
        putClientProperty("Gestion.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(new LineBorder(new Color(0, 0, 0)));

    }

	public boolean verifierAjoutCat(){
		if(textField_nomCat.getText().length()==0 || textField_nomCat.getText()==null){
			err_nomCat.setVisible(true);
			err_nomCat.setToolTipText("Donner le nom de la categorie!");
			return false;
		}else{
			// verifier s'il existe dans le liste
			Vector<Categorie> liste= controlerCategorie.getListeCateg();
			for (Categorie categorie : liste) {
				if(categorie.getNomCate().equals(textField_nomCat.getText())){
					err_nomCat.setVisible(true);
					err_nomCat.setToolTipText("Ce nom existe déja !!");		
					return false;
				}
					
			}
		}
		err_nomCat.setVisible(false);
		return true;
	}
	public boolean verifierUpdateCat(){
		if(textField_nomCat.getText().length()==0 || textField_nomCat.getText()==null){
			err_nomCat.setVisible(true);
			err_nomCat.setToolTipText("Donner le nom de la categorie!");
			return false;
		}else{
			//si ancien different de nouveau
			if(!obtenir_Categorie_Selection().getNomCate().equals(textField_nomCat.getText())){
				// verifier s'il existe dans le liste
				Vector<Categorie> liste= controlerCategorie.getListeCateg();
				for (Categorie categorie : liste) {
					if(categorie.getNomCate().equals(textField_nomCat.getText())){
						err_nomCat.setVisible(true);
						err_nomCat.setToolTipText("Ce nom existe déja !!");		
						return false;
					}
						
				}
			}
		}
		err_nomCat.setVisible(false);
		return true;
	}
	public Article creer_Objet_Article_Saisi_Champ_PourAjout(){
		Article article=new Article();
		article.setCodeArt(null);
		article.setDesignation(textField_desi.getText().toString());
		article.setUnite(Integer.parseInt(textField_unit.getText().toString()));
		article.setQte(Integer.parseInt(textField_qt.getText().toString()));
		article.setPrix(Float.parseFloat(textField_prix.getText().toString()));
		article.setEmplacement(textField_empl.getText().toString());
		int idCat =controlerCategorie.getByNom(comboBox_Cat.getSelectedItem().toString()).getIdCat();
		int idF =controlerArticle.getFournByNom(comboBox_Fourn.getSelectedItem().toString()).getNumF();
		article.setIdCat(idCat);
		article.setIdFour(idF);
		return article;
	}
	public Categorie creer_Objet_Categorie_Saisi_Champ_PourAjout(){
		Categorie categorie=new Categorie();
		categorie.setNomCate(textField_nomCat.getText());
		categorie.setDescription(textArea_DesCat.getText());
		return categorie;
	}
	public void chargerComboCat_ComboFourni(){
		
			//ClasseDAO da = new ClasseDAO();
			Vector<Categorie> v = controlerCategorie.getListeCateg();
			Vector<Fournisseur> lf=controlerArticle.getListFourn();
			comboBox_Cat.removeAllItems();
			for (Categorie classe : v) {
				comboBox_Cat.addItem(classe.getNomCate().trim());
			}
			for (Fournisseur fournisseur : lf) {
				comboBox_Fourn.addItem(fournisseur.getNomF().trim());
			}
			comboBox_Fourn.setSelectedItem(null);
			comboBox_Cat.setSelectedItem(null);
			
		
	}
	public void chargerTableCat(){
		
		Vector<Categorie> vec = controlerCategorie.getListeCateg();
		
		//String[] columns = {"Id_Participant", "Nom", "Prénom","Assisté"}; 
		Object[][] data= new Object[vec.size()][model_table_Categorie.getColumnCount()];
		for (int i = 0; i < data.length; i++) {
			
				data[i][0]=vec.get(i).getIdCat();
				data[i][1]=vec.get(i).getNomCate();
				data[i][2]=vec.get(i).getDescription();
		}
		nettoyerChampCat();
		model_table_Categorie.changeData(data);	
	}
	private void chargerTable_Categorie_Rechercher() {
		Vector<Categorie> vec ;
		//String facteur= facteur(comboBox.getSelectedItem().toString());
		vec=controlerCategorie.findCollection("%"+textField_nom_cat.getText()+"%");
		Object[][] data= new Object[vec.size()][model_table_Categorie.getColumnCount()];
		
		if(vec.size()>0){
			
			for (int i = 0; i < data.length; i++) {
				
				data[i][0]=vec.get(i).getIdCat();
				data[i][1]=vec.get(i).getNomCate();
				data[i][2]=vec.get(i).getDescription();
			}
			model_table_Categorie.changeData(data);	
		}
		
	}
	public void chargerTableArt(){
		
		Vector<Article> vec = controlerArticle.getListeArt();
		
		//String[] columns = {"Id_Participant", "Nom", "Prénom","Assisté"}; 
		Object[][] data= new Object[vec.size()][model_table_Article.getColumnCount()];
		for (int i = 0; i < data.length; i++) {
			
				data[i][0]=vec.get(i).getCodeArt();
				data[i][1]=vec.get(i).getDesignation();
				data[i][2]=vec.get(i).getUnite();
				data[i][3]=vec.get(i).getQte();
				data[i][4]=vec.get(i).getPrix();
				data[i][5]=vec.get(i).getEmplacement();
				data[i][6]=vec.get(i).getIdCat();
				data[i][7]=vec.get(i).getIdFour();
		}
		model_table_Article.changeData(data);	
	}
	private void chargerTable_Article_Rechercher() {
		Vector<Article> vec ;
		//String facteur= facteur(comboBox.getSelectedItem().toString());
		vec=controlerArticle.findByDesi(textField_Recherche.getText());
		Object[][] data= new Object[vec.size()][model_table_Article.getColumnCount()];
		
		if(vec.size()>0){
			
			for (int i = 0; i < data.length; i++) {
				
				data[i][0]=vec.get(i).getCodeArt();
				data[i][1]=vec.get(i).getDesignation();
				data[i][2]=vec.get(i).getUnite();
				data[i][3]=vec.get(i).getQte();
				data[i][4]=vec.get(i).getPrix();
				data[i][5]=vec.get(i).getEmplacement();
				data[i][6]=vec.get(i).getIdCat();
				data[i][7]=vec.get(i).getIdFour();
			}
			model_table_Article.changeData(data);	
		}
		
	}
	public Categorie obtenir_Categorie_Selection() {
		Categorie categorie=new Categorie();
		categorie.setIdCat(Integer.parseInt(table_Categorie.getValueAt(table_Categorie.getSelectedRow(), 0).toString()));
		categorie.setNomCate((String)table_Categorie.getValueAt(table_Categorie.getSelectedRow(), 1));
		categorie.setDescription((String)table_Categorie.getValueAt(table_Categorie.getSelectedRow(), 2));
		
		return categorie;
	}
	public Article obtenir_Article_Selection() {
		Article article=new Article();
		article.setCodeArt(Integer.parseInt(table_Article.getValueAt(table_Article.getSelectedRow(), 0).toString()));
		article.setDesignation((String)table_Article.getValueAt(table_Article.getSelectedRow(), 1));
		article.setUnite((Integer)table_Article.getValueAt(table_Article.getSelectedRow(), 2));
		article.setQte((Integer)table_Article.getValueAt(table_Article.getSelectedRow(), 3));
		article.setPrix(Float.parseFloat(table_Article.getValueAt(table_Article.getSelectedRow(), 4).toString()));
		article.setEmplacement((String)table_Article.getValueAt(table_Article.getSelectedRow(), 5));
		article.setIdCat((Integer)table_Article.getValueAt(table_Article.getSelectedRow(), 6));
		article.setIdFour((Integer)table_Article.getValueAt(table_Article.getSelectedRow(), 7));
		return article;
	}
	public void remplir_Champ_Categorie(Categorie categorie) {
		
		textField_nomCat.setText(categorie.getNomCate());
		textArea_DesCat.setText(categorie.getDescription());
	}
	public void nettoyerChampCat(){
		textField_nomCat.setText("");
		textArea_DesCat.setText("");
		err_nomCat.setVisible(false);
	}
	public void nettoyerChampArt(){
		textField_desi.setText("");
		textField_empl.setText("");
		textField_prix.setText("");
		textField_qt.setText("");
		textField_unit.setText("");
		comboBox_Cat.setSelectedItem(null);
		table_Article.clearSelection();
	}
	public void remplir_Champ_Article(Article article){
		textField_desi.setText(article.getDesignation());
		textField_empl.setText(article.getEmplacement());
		textField_prix.setText(article.getPrix().toString());
		textField_qt.setText(article.getQte().toString());
		textField_unit.setText(article.getUnite().toString());
		// Recuperer le nom de la categorie selectionner
		try {
			String nomCat= controlerCategorie.getById(article.getIdCat()).getNomCate();
			comboBox_Cat.setSelectedItem(nomCat);
		} catch (Exception e) {
			e.printStackTrace();
			comboBox_Cat.setSelectedItem(null);
		}
		// Recuperer le non du fournisseur
		try {
			
			String nomFour= controlerArticle.getFournById(article.getIdFour()).getNomF();
			comboBox_Fourn.setSelectedItem(nomFour);
		} catch (Exception e) {
			e.printStackTrace();
			comboBox_Fourn.setSelectedItem(null);
		}
		
		
		
	}
	public boolean verifierDesign_Ajout(){
		if(textField_desi.getText().length()==0 || textField_desi.getText()==null){
			err_desi.setVisible(true);
			err_desi.setToolTipText("Donner la designation !");
			return false;
		}else {
			// Verifier si la designation existe deja
			Vector<Article> list= controlerArticle.getListeArt();
			for (Article article : list) {
				if(article.getDesignation().equals(textField_desi.getText().toString())){
					err_desi.setVisible(true);
					err_desi.setToolTipText("Designation existante !");
					return false;
				}
			}
		}
		err_desi.setVisible(false);
		return true;
	}
	
	public boolean verifierDesign_Update(){
		if(textField_desi.getText().length()==0 || textField_desi.getText()==null){
			err_desi.setVisible(true);
			err_desi.setToolTipText("Donner la designation !");
			return false;
		}else {
			// Verifier si new different de old
			if(! (obtenir_Article_Selection().getDesignation().equals(textField_desi.getText()))){
				Vector<Article> list= controlerArticle.getListeArt();
				for (Article article : list) {
					if(article.getDesignation().equals(textField_desi.getText().toString())){
						err_desi.setVisible(true);
						err_desi.setToolTipText("Designation existante !");
						return false;
					}
				}
			}
			
		}
		err_desi.setVisible(false);
		return true;
	}
	public boolean verifierPrix(){
		try {
			Float.parseFloat(textField_prix.getText().toString());
		} catch (NumberFormatException e) {
			err_prix.setVisible(true);
			err_prix.setToolTipText("Seulement nombre autorisé !");
			return false;
		}
		err_prix.setVisible(false);
		return true;
	}
	public boolean verifierQt(){
		try {
			Integer.parseInt(textField_qt.getText().toString());
		} catch (NumberFormatException e) {
			err_qt.setVisible(true);
			err_qt.setToolTipText("Seulement nombre autorisé !");
			return false;
		}
		err_qt.setVisible(false);
		return true;
	}
	public boolean verifierUnit(){
		try {
			Integer.parseInt(textField_unit.getText().toString());
		} catch (NumberFormatException e) {
			err_unit.setVisible(true);
			err_unit.setToolTipText("Deloler pas chaine de charactere !!");
			return false;
		}
		err_unit.setVisible(false);
		return true;
	}
	public boolean verifierCatg(){
		if(comboBox_Cat.getSelectedItem()==null){
			err_cat.setVisible(true);
			err_cat.setToolTipText("Selectionner la categorie");
			return false;
		}
		err_cat.setVisible(false);
		return true;
	}
	
	public boolean verifierArticle(){
		return verifierCatg()&verifierQt()&verifierPrix()&verifierDesign_Ajout() & verifierUnit();
	}
	public boolean verifierArticleUptdate(){
		return verifierCatg()&verifierQt()&verifierPrix()&verifierDesign_Update() & verifierUnit();

	}
	public JTable getTable_Article() {
		return table_Article;
	}

	public ModelTableau getModel_table_Article() {
		return model_table_Article;
	}

	public ModelTableau getModel_table_Categorie() {
		return model_table_Categorie;
	}

	public ControlerCategorie getControlerCategorie() {
		return controlerCategorie;
	}

	public ControlerArticle getControlerArticle() {
		return controlerArticle;
	}

	public JTextField getTextField() {
		return textField_desi;
	}

	public JTextField getTextField_1() {
		return textField_unit;
	}

	public JTextField getTextField_2() {
		return textField_qt;
	}

	public JTextField getTextField_3() {
		return textField_prix;
	}

	public JTextField getTextField_4() {
		return textField_empl;
	}

	public JTable getTable_Categorie() {
		return table_Categorie;
	}

	public JTextField getTextField_5() {
		return textField_nomCat;
	}

	public JTextField getTextField_6() {
		return textField_Recherche;
	}

	public JButton getBtnAjouter_Art() {
		return btnAjouter_Art;
	}

	public JButton getBtnModifier_Art() {
		return btnModifier_Art;
	}

	public JButton getBtnSupprimer_Art() {
		return btnSupprimer_Art;
	}

	public JButton getBtnNettoyer_Art() {
		return btnNettoyer_Art;
	}

	public JButton getBtnImprimer_Art() {
		return btnImprimer_Art;
	}

	public JButton getBtnAjouter_Cat() {
		return btnAjouter_Cat;
	}

	public JButton getBtnModifier_Cat() {
		return btnModifier_Cat;
	}

	public JButton getBtnSupprimer_Cat() {
		return btnSupprimer_Cat;
	}

	public JButton getBtnImprimerLaListe_Cat() {
		return btnImprimerLaListe_Cat;
	}

	public CategorieDAO getCategorieDAO() {
		return categorieDAO;
	}

	public JTextField getTextField_desi() {
		return textField_desi;
	}

	public JTextField getTextField_unit() {
		return textField_unit;
	}

	public JTextField getTextField_qt() {
		return textField_qt;
	}

	public JTextField getTextField_prix() {
		return textField_prix;
	}

	public JTextField getTextField_empl() {
		return textField_empl;
	}

	public JTextField getTextField_nomCat() {
		return textField_nomCat;
	}

	public JLabel getErr_nomCat() {
		return err_nomCat;
	}

	public JTextArea getTextArea_DesCat() {
		return textArea_DesCat;
	}

	public JComboBox getComboBox_Cat() {
		return comboBox_Cat;
	}

	public JLabel getErr_desi() {
		return err_desi;
	}

	public JLabel getErr_unit() {
		return err_unit;
	}

	public JLabel getErr_qt() {
		return err_qt;
	}

	public JLabel getErr_prix() {
		return err_prix;
	}

	public JLabel getErr_empl() {
		return err_empl;
	}

	public JLabel getErr_cat() {
		return err_cat;
	}

	public JButton getBtnNettoyerCat() {
		return btnNettoyerCat;
	}
}
