package view;

import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import classeMetier.Article;
import classeMetier.Commande;
import classeMetier.Entree;
import classeMetier.Fournisseur;
import classeMetier.LineCommande;
import classeMetier.Sortie;
import classeMetier.Utilisateur;
import controler.ControlerCommande;
import controler.ControlerEntreSortie;
import groovy.swing.factory.TableModelFactory;
import utility.ModelTableau;
import utility.TableCellRender;
import utility.TableModelFromRS;

import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionEntreSortie extends JInternalFrame {
	private JTable table;
	private JTable table_Commande;
	private JTable table_LineC;
	private JButton btnC;
	private JButton btnInsererUneLine;
	private JButton btnPasserLaCommande;
	private ControlerCommande controlerCommande;
	private ControlerEntreSortie controlerEntreSortie;
	private JComboBox comboBox_Fourn;
	
	private ModelTableau model_table;
	private ModelTableau model_table_LineC;
	
	private LineCommande lineCommande;
	private Commande commande;
	private ArrayList<Article> articles;
	private JTable table_Entree;
	private JDateChooser dateChooser_4;
	private JButton btnAnnulerLaCommande;
	private JLabel errDate;
	private JTextField textField_qtlEntree;
	private JTextField textField_prixEntre;
	private JTextField textField_4;
	private ModelTableau model_table_Achat;
	private JLabel textField_Nomprod;
	private JLabel textField_NomFour;
	private JButton btnValider_Entre;
	private JDateChooser dateEntree;
	private JLabel errQe;
	private JLabel errPE;
	private JLabel errDE;
	private JPanel panel_2;
	private JPanel panel_13;
	private JTable table_ListEntree;
	private JTextField textField_Rc;
	private JTable table_Sortie;
	private JTextField textField_QtS;
	private JButton btnValider_S;
	private JButton btnNettoyer_S;
	private JDateChooser dateChooser_S;
	private JTable table_ListeSortie;
	private JLabel lblNomProduit_S;
	private JLabel lblNomFounisseurS;
	private JLabel errQS;
	private JLabel errpS;
	private JLabel errDS;
	private JLabel textField_PS;

	/**
	 * Create the frame.
	 */
	public GestionEntreSortie() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(241, 142, 1111, 590);
		getContentPane().setLayout(null);
		
		model_table= new ModelTableau(new Object[][]{
			{null,null,null,null}
		},new String[]{
				"NUMC",
				"DATE COMMANDE", 
				"ETAT" , 
				"NOMBRE DE LINE"
				
		});
		model_table_LineC= new ModelTableau(new Object[][]{
			{null,null,null,null}
		},new String[]{
				"numLC",
				"NOM PRODUIT", 
				"QUANTITE" , 
				"MONTAT"
				
		});
		
		commande=new Commande();
		
		JLabel lblGestionDesUtilisateurs = new JLabel("Gestion des Entr\u00E9s__Sorties");
		lblGestionDesUtilisateurs.setBounds(10, 0, 348, 30);
		getContentPane().add(lblGestionDesUtilisateurs);
		lblGestionDesUtilisateurs.setFont(new Font("Sitka Text", Font.BOLD, 13));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 30, 1091, 13);
		getContentPane().add(separator);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 43, 1091, 508);
		getContentPane().add(tabbedPane);
		
		model_table_Achat= new ModelTableau(new Object[][]{
			{null,null,null,null,null}
		},new String[]{
				"id",
				"NOM PRODUIT", 
				"NOM FOURNISSEUR" , 
				"QUANTITE" , 
				"EMPLACEMENT" 
				
		});
		/*Jtable not draggble , not resizble*/
		

		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Entr\u00E9es", null, tabbedPane_1, null);
		
		panel_2 = new JPanel();
		tabbedPane_1.addTab("Ajouter Une Entree", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 24, 660, 417);
		panel_2.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Liste", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 638, 334);
		panel_1.add(scrollPane);
		
		table_Entree = new JTable();
		table_Entree.getTableHeader().setReorderingAllowed(false);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Entree.getTableHeader().setResizingAllowed(false);
		table_Entree.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		table_Entree.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Entree.setRowHeight(28);
		table_Entree.setModel(model_table_Achat);
		scrollPane.setViewportView(table_Entree);
		table_Entree.setDefaultRenderer(Object.class, new TableCellRender());
		table_Entree.getTableHeader().setDefaultRenderer(new TableCellRender());
		//sizeOfTableEntre();
		JLabel lblChercher = new JLabel("Chercher :");
		lblChercher.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblChercher.setBounds(10, 31, 95, 26);
		panel_1.add(lblChercher);
		
		textField_4 = new JTextField();
		textField_4.setBounds(117, 30, 147, 28);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Achat d'un produit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(680, 24, 396, 417);
		panel_2.add(panel);
		panel.setLayout(null);
		
		JLabel lblQunatit = new JLabel("Qantit\u00E9 Entr\u00E9e");
		lblQunatit.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblQunatit.setBounds(10, 137, 137, 28);
		panel.add(lblQunatit);
		
		JLabel lblPrixDentr = new JLabel("Prix d'Entr\u00E9");
		lblPrixDentr.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblPrixDentr.setBounds(10, 176, 137, 28);
		panel.add(lblPrixDentr);
		
		textField_qtlEntree = new JTextField();
		textField_qtlEntree.setBounds(157, 137, 188, 28);
		panel.add(textField_qtlEntree);
		textField_qtlEntree.setColumns(10);
		
		textField_prixEntre = new JTextField();
		textField_prixEntre.setColumns(10);
		textField_prixEntre.setBounds(157, 176, 188, 28);
		panel.add(textField_prixEntre);
		
		btnValider_Entre = new JButton("Valider");
		btnValider_Entre.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/Ok-icon.png")));
		btnValider_Entre.setBounds(157, 281, 101, 33);
		panel.add(btnValider_Entre);
		
		JLabel lblNomProduit = new JLabel("Nom du Produit");
		lblNomProduit.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblNomProduit.setBounds(10, 58, 137, 28);
		panel.add(lblNomProduit);
		
		JLabel lblNomDuFournisseur = new JLabel("Nom du Fournisseur");
		lblNomDuFournisseur.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblNomDuFournisseur.setBounds(10, 98, 137, 28);
		panel.add(lblNomDuFournisseur);
		
		textField_Nomprod = new JLabel("Nom Produit");
		textField_Nomprod.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textField_Nomprod.setFont(new Font("Sitka Text", Font.BOLD, 13));
		textField_Nomprod.setForeground(Color.BLUE);
		textField_Nomprod.setBounds(157, 58, 188, 28);
		panel.add(textField_Nomprod);
		
		textField_NomFour = new JLabel("Nom Fournisseur");
		textField_NomFour.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textField_NomFour.setFont(new Font("Sitka Text", Font.BOLD, 13));
		textField_NomFour.setForeground(Color.BLUE);
		textField_NomFour.setBounds(157, 98, 188, 28);
		panel.add(textField_NomFour);
		
		JLabel lblDateDentre = new JLabel("Date d'entr\u00E9e");
		lblDateDentre.setBounds(10, 216, 137, 27);
		panel.add(lblDateDentre);
		
		dateEntree = new JDateChooser();
		dateEntree.setDateFormatString("dd/MM/yyyy");
		((JTextField)dateEntree.getDateEditor().getUiComponent()).setEditable(false);
		dateEntree.setBounds(157, 215, 188, 28);
		panel.add(dateEntree);
		
		errQe = new JLabel("");
		errQe.setVisible(false);
		errQe.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/error.png")));
		errQe.setBounds(355, 137, 31, 28);
		panel.add(errQe);
		
		errPE = new JLabel("");
		errPE.setVisible(false);
		errPE.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/error.png")));
		errPE.setBounds(355, 176, 31, 28);
		panel.add(errPE);
		
		errDE = new JLabel("");
		errDE.setVisible(false);
		errDE.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/error.png")));
		errDE.setBounds(355, 216, 31, 27);
		panel.add(errDE);
		
		JButton btnNettoyer = new JButton("Nettoyer");
		btnNettoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_prixEntre.setText("");
				textField_qtlEntree.setText("");
				
			}
		});
		btnNettoyer.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/clear.png")));
		btnNettoyer.setBounds(279, 281, 107, 33);
		panel.add(btnNettoyer);
		
		JPanel panel_11 = new JPanel();
		tabbedPane_1.addTab("Liste des Entree", null, panel_11, null);
		panel_11.setLayout(null);
		
		panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(null, "Liste des Entr\u00E9es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.setBounds(10, 11, 740, 430);
		panel_11.add(panel_13);
		panel_13.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 44, 720, 375);
		panel_13.add(scrollPane_4);
		
		table_ListEntree = new JTable();
		
		table_ListEntree.getTableHeader().setReorderingAllowed(false);
		//table_ListEntree.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_ListEntree.getTableHeader().setResizingAllowed(false);
		table_ListEntree.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		table_ListEntree.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_ListEntree.setRowHeight(28);
		//table_ListEntree.setModel(model_table_Achat);
		
		table_ListEntree.setDefaultRenderer(Object.class, new TableCellRender());
		table_ListEntree.getTableHeader().setDefaultRenderer(new TableCellRender());
		
		
		scrollPane_4.setViewportView(table_ListEntree);
		table_Entree.addMouseListener(controlerEntreSortie);
		btnValider_Entre.addActionListener(controlerEntreSortie);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Sorties", null, tabbedPane_2, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_2.addTab("Ajouter une Sortie", null, panel_4, null);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(null, "Liste", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(10, 24, 660, 430);
		panel_4.add(panel_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 72, 638, 346);
		panel_5.add(scrollPane_1);
		
		table_Sortie = new JTable();
		table_Sortie.getTableHeader().setReorderingAllowed(false);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Sortie.getTableHeader().setResizingAllowed(false);
		table_Sortie.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		table_Sortie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Sortie.setRowHeight(28);
		
		scrollPane_1.setViewportView(table_Sortie);
		table_Sortie.setDefaultRenderer(Object.class, new TableCellRender());
		table_Sortie.getTableHeader().setDefaultRenderer(new TableCellRender());
		JLabel label = new JLabel("Chercher :");
		label.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		label.setBounds(10, 31, 95, 26);
		panel_5.add(label);
		
		textField_Rc = new JTextField();
		textField_Rc.setColumns(10);
		textField_Rc.setBounds(117, 30, 147, 28);
		panel_5.add(textField_Rc);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Sortie D'un Produit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(680, 24, 396, 417);
		panel_4.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNomDuProduit = new JLabel("Nom du Produit ");
		lblNomDuProduit.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblNomDuProduit.setBounds(10, 58, 127, 28);
		panel_6.add(lblNomDuProduit);
		
		JLabel lblNomDuFournisseur_1 = new JLabel("Nom du Fournisseur");
		lblNomDuFournisseur_1.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblNomDuFournisseur_1.setBounds(10, 97, 127, 28);
		panel_6.add(lblNomDuFournisseur_1);
		
		lblNomProduit_S = new JLabel("Nom Produit");
		lblNomProduit_S.setForeground(Color.BLUE);
		lblNomProduit_S.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblNomProduit_S.setBounds(157, 58, 139, 28);
		panel_6.add(lblNomProduit_S);
		
		lblNomFounisseurS = new JLabel("Nom Founisseur");
		lblNomFounisseurS.setForeground(Color.BLUE);
		lblNomFounisseurS.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblNomFounisseurS.setBounds(157, 97, 139, 28);
		panel_6.add(lblNomFounisseurS);
		
		JLabel lblQuantit = new JLabel("Quantit\u00E9");
		lblQuantit.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblQuantit.setBounds(10, 136, 127, 28);
		panel_6.add(lblQuantit);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblPrix.setBounds(10, 175, 139, 28);
		panel_6.add(lblPrix);
		
		textField_QtS = new JTextField();
		textField_QtS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				try {
					Float prix=(Float)table_Sortie.getValueAt(table_Sortie.getSelectedRow(), 5);
					Float total= prix * Integer.parseInt(textField_QtS.getText());
					textField_PS.setText(String.valueOf(total));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		textField_QtS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				try {
					Float prix=(Float)table_Sortie.getValueAt(table_Sortie.getSelectedRow(), 5);
					Float total= prix * Integer.parseInt(textField_QtS.getText());
					textField_PS.setText(String.valueOf(total));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		textField_QtS.setBounds(157, 137, 188, 28);
		panel_6.add(textField_QtS);
		textField_QtS.setColumns(10);
		
		JLabel lblDateDeSortiee = new JLabel("Date de Sortiee");
		lblDateDeSortiee.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblDateDeSortiee.setBounds(10, 214, 133, 28);
		panel_6.add(lblDateDeSortiee);
		
		dateChooser_S = new JDateChooser();
		dateChooser_S.setDateFormatString("dd/MM/yyyy");
		((JTextField)dateChooser_S.getDateEditor().getUiComponent()).setEditable(false);
		dateChooser_S.setBounds(157, 214, 188, 28);
		panel_6.add(dateChooser_S);
		
		btnValider_S = new JButton("Valider");
		btnValider_S.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/Ok-icon.png")));
		btnValider_S.setBounds(157, 281, 109, 33);
		panel_6.add(btnValider_S);
		
		btnNettoyer_S = new JButton("Nettoyer");
		btnNettoyer_S.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/clear.png")));
		btnNettoyer_S.setBounds(276, 281, 110, 33);
		panel_6.add(btnNettoyer_S);
		
		errQS = new JLabel("");
		errQS.setVisible(false);
		errQS.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/error.png")));
		errQS.setBounds(355, 136, 31, 28);
		panel_6.add(errQS);
		
		errpS = new JLabel("");
		errpS.setVisible(false);
		errpS.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/error.png")));
		errpS.setBounds(354, 175, 32, 21);
		panel_6.add(errpS);
		
		errDS = new JLabel("");
		errDS.setVisible(false);
		errDS.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/error.png")));
		errDS.setBounds(354, 214, 32, 28);
		panel_6.add(errDS);
		
		textField_PS = new JLabel("Prix");
		textField_PS.setFont(new Font("Sitka Text", Font.BOLD, 13));
		textField_PS.setForeground(Color.BLUE);
		textField_PS.setBounds(157, 174, 139, 28);
		panel_6.add(textField_PS);
		
		JLabel lblDinar = new JLabel("Dinar");
		lblDinar.setForeground(Color.RED);
		lblDinar.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblDinar.setBounds(299, 174, 46, 28);
		panel_6.add(lblDinar);
		
		JPanel panel_8 = new JPanel();
		tabbedPane_2.addTab("Afficher les Sorties", null, panel_8, null);
		panel_8.setLayout(null);
		
		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setBorder(new TitledBorder(null, "Liste des Entr\u00E9es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_12.setBounds(10, 22, 740, 430);
		panel_8.add(panel_12);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 44, 720, 375);
		panel_12.add(scrollPane_5);
		
		table_ListeSortie = new JTable();
		
		table_ListeSortie.getTableHeader().setReorderingAllowed(false);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_ListeSortie.getTableHeader().setResizingAllowed(false);
		table_ListeSortie.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		table_ListeSortie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_ListeSortie.setRowHeight(28);
		table_ListeSortie.setDefaultRenderer(Object.class, new TableCellRender());
		table_ListeSortie.getTableHeader().setDefaultRenderer(new TableCellRender());
		scrollPane_5.setViewportView(table_ListeSortie);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Commandes", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Faire une Commande", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBounds(540, 11, 536, 458);
		panel_3.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblChoisirLeFournisseur = new JLabel("<html>Choisir le Fournisseur: <font color='red'>*</font></html> ");
		lblChoisirLeFournisseur.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblChoisirLeFournisseur.setBounds(10, 50, 272, 28);
		panel_7.add(lblChoisirLeFournisseur);
		
		comboBox_Fourn = new JComboBox();
		comboBox_Fourn.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		comboBox_Fourn.setBounds(270, 50, 219, 28);
		panel_7.add(comboBox_Fourn);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "Inserer une line de Commande", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(10, 133, 516, 250);
		panel_7.add(panel_10);
		panel_10.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 22, 496, 217);
		panel_10.add(scrollPane_3);
		
		table_LineC = new JTable();
		table_LineC.getTableHeader().setReorderingAllowed(false);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_LineC.getTableHeader().setResizingAllowed(false);
		table_LineC.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		table_LineC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_LineC.setRowHeight(28);
		table_LineC.setModel(model_table_LineC);
		scrollPane_3.setViewportView(table_LineC);
		
		table_LineC.setDefaultRenderer(Object.class, new TableCellRender());
		table_LineC.getTableHeader().setDefaultRenderer(new TableCellRender());
		
		
		
		table_LineC.getColumnModel().getColumn(0).setMinWidth(0);
		table_LineC.getColumnModel().getColumn(0).setMaxWidth(0);
		table_LineC.getColumnModel().getColumn(0).setWidth(0);
		
		btnPasserLaCommande = new JButton("<html><center>Passer la  <br> Commande</center></html>");
		btnPasserLaCommande.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/Ok-icon.png")));
		btnPasserLaCommande.setBounds(170, 399, 170, 48);
		panel_7.add(btnPasserLaCommande);
		
		btnInsererUneLine = new JButton("<html><center>Inserer une line <br>de Commande</center></html>");
		btnInsererUneLine.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/Button-Add-icon.png")));
		btnInsererUneLine.setBounds(10, 399, 150, 48);
		panel_7.add(btnInsererUneLine);
		
		JLabel lbldateDeLa = new JLabel("<html>Date de la Commande: <font color='red'>*</font></html> ");
		lbldateDeLa.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lbldateDeLa.setBounds(10, 89, 272, 28);
		panel_7.add(lbldateDeLa);
		
		dateChooser_4 = new JDateChooser();
		((JTextField)dateChooser_4.getDateEditor().getUiComponent()).setEditable(false);	
		dateChooser_4.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				try {
					String datecmd=((JTextField)dateChooser_4.getDateEditor().getUiComponent()).getText();
					commande.setDatecmd(datecmd);
				} catch (Exception e) {
				
				}
			}
		});
		dateChooser_4.setDateFormatString("dd/MM/yyyy");
		dateChooser_4.setBounds(270, 89, 219, 28);
		panel_7.add(dateChooser_4);
		
		JLabel errFour = new JLabel("");
		errFour.setVisible(false);
		errFour.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/error.png")));
		errFour.setBounds(494, 50, 21, 22);
		panel_7.add(errFour);
		
		errDate = new JLabel("");
		errDate.setVisible(false);
		errDate.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/error.png")));
		errDate.setBounds(494, 89, 21, 22);
		panel_7.add(errDate);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Liste de Commande", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.setBounds(10, 11, 520, 458);
		panel_3.add(panel_9);
		panel_9.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 46, 500, 339);
		panel_9.add(scrollPane_2);
		
		table = new JTable();
		table_Commande = new JTable();
		/*Jtable not draggble , not resizble*/
		table_Commande.getTableHeader().setReorderingAllowed(false);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Commande.getTableHeader().setResizingAllowed(false);
		table_Commande.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		table_Commande.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Commande.setRowHeight(28);
		table_Commande.setModel(model_table);
		scrollPane_2.setViewportView(table_Commande);
		
		table_Commande.setDefaultRenderer(Object.class, new TableCellRender());
		table_Commande.getTableHeader().setDefaultRenderer(new TableCellRender());
		
		
		
		table_Commande.getColumnModel().getColumn(0).setMinWidth(0);
		table_Commande.getColumnModel().getColumn(0).setMaxWidth(0);
		table_Commande.getColumnModel().getColumn(0).setWidth(0);
		
		btnC = new JButton("Valider la Commande");
		btnC.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/Ok-icon.png")));
		btnC.setBounds(10, 408, 186, 39);
		panel_9.add(btnC);
		
		btnAnnulerLaCommande = new JButton("Annuler la Commande");
		btnAnnulerLaCommande.setIcon(new ImageIcon(GestionEntreSortie.class.getResource("/image/Red X copie.png")));
		btnAnnulerLaCommande.setBounds(206, 408, 190, 39);
		panel_9.add(btnAnnulerLaCommande);
		remove_title_bar() ;
		
		
		controlerCommande=new ControlerCommande(this);
		controlerEntreSortie=new ControlerEntreSortie(this);
		table_Sortie.addMouseListener(controlerEntreSortie);
		table_Entree.addMouseListener(controlerEntreSortie);
		table_ListEntree.addMouseListener(controlerEntreSortie);
		btnValider_Entre.addActionListener(controlerEntreSortie);
		btnValider_S.addActionListener(controlerEntreSortie);
		//controlerCommande.articles
		btnInsererUneLine.addActionListener(controlerCommande);
		btnPasserLaCommande.addActionListener(controlerCommande);
		btnC.addActionListener(controlerCommande);
		
		charComboFourni();
		chargerTable_Commande();
		chargertableEntre();
		chargerListeEntree();
		chargerTableSortie();
		chargerListSortie();
	}

	public Entree creerObjetEntre(){
		Entree entree = new Entree();
		try {
			entree.setCodeart(Integer.parseInt(table_Entree.getValueAt(table_Entree.getSelectedRow(), 0).toString()));
			entree.setDateentre(((JTextField)dateEntree.getDateEditor().getUiComponent()).getText());
			entree.setPrixdentre(Float.parseFloat(textField_prixEntre.getText()));
			entree.setQte(Integer.parseInt(textField_qtlEntree.getText()));		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entree;
	}
	public boolean verifier(){
		// Verifier date commande
		if(commande.getDatecmd()==null | commande.getDatecmd()==""){
			errDate.setVisible(true);
			errDate.setToolTipText("Donner la date de la commande!");
			return false;
		}
		return true;
	}
	public boolean verifierQt(){
		try {
			Integer.parseInt(textField_qtlEntree.getText());
		} catch (Exception e) {
			errQe.setVisible(true);
			errQe.setToolTipText("Chiffre seulement !");
			return false;
		}
		errQe.setVisible(false);
		return true;
	}
	
	public boolean verifierPrix(){
		try {
			Integer.parseInt(textField_prixEntre.getText());
		} catch (Exception e) {
			errPE.setVisible(true);
			errPE.setToolTipText("Chiffre Seulement !!");
			return false;
		}
		errPE.setVisible(false);
		return true;
	}
	public boolean verifierDate(){
		if(dateEntree.getDate()==null){
			errDE.setVisible(true);
			errDE.setToolTipText("Erreur Date!");
			return false;
		}
		errDE.setVisible(false);
		return true;
	}
	public boolean verifierEntree() {
		
		return verifierQt()& verifierPrix()&verifierDate();
	}
	public boolean verifierQtS(){
		try {
			Integer.parseInt(textField_QtS.getText());
		} catch (Exception e) {
			errQS.setVisible(true);
			errQS.setToolTipText("Chiffre Seulement !!");
			return false;
		}
		if(textField_QtS.getText()=="" || textField_QtS.getText()==null){
			errQS.setVisible(true);
			errQS.setToolTipText("Champ Obligatoire !!");
			return false;
		}
		errQS.setVisible(false);
		return true;
	}
	public boolean verifierDateS(){
		if(dateChooser_S.getDate()==null){
			errDS.setVisible(true);
			errDS.setToolTipText("Erreur Date !");
			return false;
		}
		errDS.setVisible(false);
		return true;
	}
	public boolean verifierSortie() {
		
		return verifierQtS()&verifierDateS();
	}
	void remove_title_bar() {
        putClientProperty("Gestion.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(new LineBorder(new Color(0, 0, 0)));

    }
	public void charComboFourni(){
		Vector<Fournisseur> list= controlerCommande.getListFournisseur();
		comboBox_Fourn.removeAll();
		for (Fournisseur fournisseur : list) {
			comboBox_Fourn.addItem(fournisseur.getNomF());
		}
	}
	public void chargerChampEntre(){
		try {
			String nomProduit=(String) table_Entree.getValueAt(table_Entree.getSelectedRow(), 1);
			String nomFour=(String)table_Entree.getValueAt(table_Entree.getSelectedRow(), 2);
			textField_NomFour.setText(nomFour);
			textField_Nomprod.setText(nomProduit);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void chargerChampsSortie(){
		try {
			String nomProduit=(String) table_Sortie.getValueAt(table_Sortie.getSelectedRow(), 1);
			lblNomFounisseurS.setText(nomProduit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String nomFour=(String)table_Sortie.getValueAt(table_Sortie.getSelectedRow(), 2);
			lblNomProduit_S.setText(nomFour);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Sortie creerObjetSortie(){
		Sortie sortie = new Sortie();
		try {
			sortie.setCodeArt(Integer.parseInt(table_Sortie.getValueAt(table_Sortie.getSelectedRow(), 0).toString()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			sortie.setDateSortie(((JTextField)dateChooser_S.getDateEditor().getUiComponent()).getText());

		} catch (Exception e) {
			// TODO: handle exception
		}	
		try {
			sortie.setPrix(Float.parseFloat(textField_PS.getText()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			sortie.setQte(Integer.parseInt(textField_QtS.getText()));	
		} catch (Exception e) {
			// TODO: handle exception
		}	
				
		
		return sortie;
	}
	public void chargertableEntre(){
		ResultSet rs=controlerEntreSortie.getLiseEntre();
		table_Entree.setModel(new TableModelFromRS(rs));
		sizeOfTableEntre(table_Entree);
	}
	public void chargerListeEntree(){
		ResultSet rs=controlerEntreSortie.getListeEntree();
		table_ListEntree.setModel(new TableModelFromRS(rs));
	}
	public void chargerTableSortie(){
		ResultSet rs=controlerEntreSortie.getLiseEntre();
		table_Sortie.setModel(new TableModelFromRS(rs));
		sizeOfTableEntre(table_Sortie);
	}
	public void chargerListSortie() {
		ResultSet rs=controlerEntreSortie.getListeSortie();
		table_ListeSortie.setModel(new TableModelFromRS(rs));
		//sizeOfTableEntre(table_ListeSortie);
		
	}
	public void sizeOfTableEntre(JTable table){
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
	}
	public void chargerTable_Commande(){
		
		
		Vector<Commande> vec=controlerCommande.getListCommande();
		Object[][] data= new Object[vec.size()][model_table.getColumnCount()];
					
		for (int i = 0; i < data.length; i++) {
			
				data[i][0]=vec.get(i).getNumcmd();
				data[i][1]=vec.get(i).getDatecmd();
				data[i][2]=vec.get(i).getEtat();
				data[i][3]=vec.get(i).getListCommande().size();
				
			}
			model_table.changeData(data);	
	}
	public void chargerTableAchat(){
		Vector<Commande> vec=controlerCommande.getListCommande();
		Object[][] data= new Object[vec.size()][model_table.getColumnCount()];
					
		for (int i = 0; i < data.length; i++) {
			
				data[i][0]=vec.get(i).getNumcmd();
				data[i][1]=vec.get(i).getDatecmd();
				data[i][2]=vec.get(i).getEtat();
				data[i][3]=vec.get(i).getListCommande().size();
				
			}
			model_table.changeData(data);
	}
	public void chargerTable_LineC(){
		
		
		ArrayList<LineCommande> vec=commande.getListCommande();
		ArrayList<Article> list=controlerCommande.getListArtByFour();
		Object[][] data= new Object[vec.size()][model_table_LineC.getColumnCount()];
					
		for (int i = 0; i < data.length; i++) {
			
				data[i][0]=vec.get(i).getIndex();
				// Nom produit
				String nom=getNomArticle(list,vec.get(i).getCodeArt());
				data[i][1]=nom;
				// quantite
				data[i][2]=vec.get(i).getQuantite();
				data[i][3]=vec.get(i).getMontat();
				
			}
		model_table_LineC.changeData(data);	
	}
	public String getNomArticle(ArrayList<Article> list,Integer id){
		for (Article article : list) {
			if((int)article.getCodeArt()==(int)id){
				return article.getDesignation();
			}
		}
		return null;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public JTable getTable_Commande() {
		return table_Commande;
	}
	public void setTable_Commande(JTable table_Commande) {
		this.table_Commande = table_Commande;
	}
	public JTable getTable_LineC() {
		return table_LineC;
	}
	public void setTable_LineC(JTable table_LineC) {
		this.table_LineC = table_LineC;
	}
	public JButton getBtnC() {
		return btnC;
	}
	public void setBtnC(JButton btnC) {
		this.btnC = btnC;
	}
	public JButton getBtnInsererUneLine() {
		return btnInsererUneLine;
	}
	public void setBtnInsererUneLine(JButton btnInsererUneLine) {
		this.btnInsererUneLine = btnInsererUneLine;
	}
	public JButton getBtnPasserLaCommande() {
		return btnPasserLaCommande;
	}
	public void setBtnPasserLaCommande(JButton btnPasserLaCommande) {
		this.btnPasserLaCommande = btnPasserLaCommande;
	}
	public ControlerCommande getControlerCommande() {
		return controlerCommande;
	}
	public void setControlerCommande(ControlerCommande controlerCommande) {
		this.controlerCommande = controlerCommande;
	}
	public JComboBox getComboBoxFourn() {
		return comboBox_Fourn;
	}
	public void setComboBox(JComboBox comboBox) {
		this.comboBox_Fourn = comboBox;
	}
	public ModelTableau getModel_table() {
		return model_table;
	}
	public void setModel_table(ModelTableau model_table) {
		this.model_table = model_table;
	}
	public ModelTableau getModel_table_LineC() {
		return model_table_LineC;
	}
	public void setModel_table_LineC(ModelTableau model_table_LineC) {
		this.model_table_LineC = model_table_LineC;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	public JTable getTable_Entree() {
		return table_Entree;
	}
	public void setTable_Entree(JTable table_Entree) {
		this.table_Entree = table_Entree;
	}
	public JDateChooser getDateChooser_4() {
		return dateChooser_4;
	}
	public void setDateChooser_4(JDateChooser dateChooser_4) {
		this.dateChooser_4 = dateChooser_4;
	}
	public JButton getBtnAnnulerLaCommande() {
		return btnAnnulerLaCommande;
	}
	public void setBtnAnnulerLaCommande(JButton btnAnnulerLaCommande) {
		this.btnAnnulerLaCommande = btnAnnulerLaCommande;
	}
	public LineCommande getLineCommande() {
		return lineCommande;
	}
	public void setLineCommande(LineCommande lineCommande) {
		this.lineCommande = lineCommande;
	}
	public JButton getBtnValider_Entre() {
		return btnValider_Entre;
	}

	public ControlerEntreSortie getControlerEntreSortie() {
		return controlerEntreSortie;
	}

	public JComboBox getComboBox_Fourn() {
		return comboBox_Fourn;
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}

	public JLabel getErrDate() {
		return errDate;
	}

	public JTextField getTextField_qtlEntree() {
		return textField_qtlEntree;
	}

	public JTextField getTextField_prixEntre() {
		return textField_prixEntre;
	}

	public JTextField getTextField_4() {
		return textField_4;
	}

	public ModelTableau getModel_table_Achat() {
		return model_table_Achat;
	}

	public JLabel getTextField_Nomprod() {
		return textField_Nomprod;
	}

	public JLabel getTextField_NomFour() {
		return textField_NomFour;
	}

	public JDateChooser getDateEntree() {
		return dateEntree;
	}

	public JLabel getErrQe() {
		return errQe;
	}

	public JLabel getErrPE() {
		return errPE;
	}

	public JLabel getErrDE() {
		return errDE;
	}

	public JPanel getPanel_2() {
		return panel_2;
	}

	public JPanel getPanel_13() {
		return panel_13;
	}

	public JTable getTable_ListEntree() {
		return table_ListEntree;
	}

	public JTextField getTextField() {
		return textField_Rc;
	}

	public JTable getTable_Sortie() {
		return table_Sortie;
	}

	public JTextField getTextField_QtS() {
		return textField_QtS;
	}

	public JLabel getTextField_PS() {
		return textField_PS;
	}

	public JButton getBtnValider_S() {
		return btnValider_S;
	}

	public JButton getBtnNettoyer_S() {
		return btnNettoyer_S;
	}

	public JDateChooser getDateChooser_S() {
		return dateChooser_S;
	}

	public JTable getTable_ListeSortie() {
		return table_ListeSortie;
	}

	public JLabel getLblNomProduit_S() {
		return lblNomProduit_S;
	}

	public JLabel getLblNomFounisseurS() {
		return lblNomFounisseurS;
	}

	
	
}
