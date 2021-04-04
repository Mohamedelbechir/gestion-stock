package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import classeMetier.Article;
import classeMetier.Commande;
import classeMetier.LineCommande;
import controler.controlerInsererLineCom;
import utility.ModelTableau;
import utility.TableCellRender;
import view.ConfirmationCommande.FrameDragListener;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;

public class GestionLineCommande extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_Qt;
	private Commande commande;
	
	private controlerInsererLineCom controlerInsererLineCom;
	private ArrayList<Article> listArt;
	private JButton btnInserer;
	private ModelTableau model_table;
	private JLabel err_Qt;

	/**
	 * Create the frame.
	 * @param listArt 
	 */
	public GestionLineCommande(Commande commande, ArrayList<Article> listArt,GestionEntreSortie gestionEntreSortie) {
		setUndecorated(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 489, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.commande=commande;
		this.listArt=listArt;
		
		model_table= new ModelTableau(new Object[][]{
			{null,null,null}
		},new String[]{
				"CODE ARTICLE", 
				"NOM ARTICLE", 
				"PRIX"
				
		});
		
		JLabel lblNewLabel = new JLabel("Les Article du Fournisseur :");
		lblNewLabel.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblNewLabel.setBounds(26, 91, 197, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNomDuFournisseur = new JLabel("nom du fournisseur");
		lblNomDuFournisseur.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblNomDuFournisseur.setForeground(Color.BLUE);
		lblNomDuFournisseur.setBounds(233, 91, 166, 28);
		contentPane.add(lblNomDuFournisseur);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 78, 453, 2);
		contentPane.add(separator);
		
		JLabel lblInsertionDuneLine = new JLabel("Insertion d'une line de commande");
		lblInsertionDuneLine.setFont(new Font("Sitka Text", Font.BOLD, 13));
		lblInsertionDuneLine.setBounds(20, 45, 283, 25);
		contentPane.add(lblInsertionDuneLine);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Liste Article du Fournisseur", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 130, 453, 319);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 110, 433, 198);
		panel.add(scrollPane);
		
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(28);
		scrollPane.setViewportView(table);
		table.setModel(model_table);
		
		table.setDefaultRenderer(Object.class, new TableCellRender());
		table.getTableHeader().setDefaultRenderer(new TableCellRender());
		
		
		
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		
		JLabel lblVeuillezChoisirLarticle = new JLabel("Veuillez choisir l'Article dans la liste");
		lblVeuillezChoisirLarticle.setForeground(Color.RED);
		lblVeuillezChoisirLarticle.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblVeuillezChoisirLarticle.setBounds(10, 28, 360, 30);
		panel.add(lblVeuillezChoisirLarticle);
		
		JLabel lblChercher = new JLabel("Chercher :");
		lblChercher.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblChercher.setBounds(10, 71, 84, 28);
		panel.add(lblChercher);
		
		textField = new JTextField();
		textField.setBounds(97, 71, 145, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblQuantit = new JLabel("<html>Quantit\u00E9 :<font color='red'>*</font></html>");
		lblQuantit.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblQuantit.setBounds(26, 458, 89, 28);
		contentPane.add(lblQuantit);
		
		textField_Qt = new JTextField();
		textField_Qt.setBounds(125, 458, 86, 28);
		contentPane.add(textField_Qt);
		textField_Qt.setColumns(10);
		
		btnInserer = new JButton("Inserer la line");
		btnInserer.setIcon(new ImageIcon(GestionLineCommande.class.getResource("/image/icons8_Add_List_20px_7.png")));
		btnInserer.setBounds(159, 497, 172, 33);
		contentPane.add(btnInserer);
		
		JLabel lblX = new JLabel("X");
		lblX.setForeground(Color.RED);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			 dispose();
			}
		});
		lblX.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblX.setBounds(453, 11, 20, 28);
		contentPane.add(lblX);
		
		err_Qt = new JLabel("");
		err_Qt.setVisible(false);
		err_Qt.setIcon(new ImageIcon(GestionLineCommande.class.getResource("/image/error.png")));
		err_Qt.setBounds(221, 460, 28, 26);
		contentPane.add(err_Qt);
		
		controlerInsererLineCom=new controlerInsererLineCom(this,gestionEntreSortie);
		btnInserer.addActionListener(controlerInsererLineCom);
		
		 FrameDragListener frameDragListener = new FrameDragListener(this);
	     this.addMouseListener(frameDragListener);
	     this.addMouseMotionListener(frameDragListener);
	     
	     chargerTableArt();
	     setLocationRelativeTo(null);
	}
	
	 
//	public JPanel getContentPane() {
//		return contentPane;
//	}
//
//	public void setContentPane(JPanel contentPane) {
//		this.contentPane = contentPane;
//	}
	public void chargerTableArt(){
		
		
		Object[][] data= new Object[listArt.size()][model_table.getColumnCount()];
		for (int i = 0; i < data.length; i++) {
			
				data[i][0]=listArt.get(i).getCodeArt();
				data[i][1]=listArt.get(i).getDesignation();
				data[i][2]=listArt.get(i).getPrix();
				
		}
		model_table.changeData(data);	
	}
	public LineCommande genererLine(){
		
		 LineCommande lineCommande = new LineCommande();
		 lineCommande.setCodeArt((Integer)table.getValueAt(table.getSelectedRow(), 0));
		 lineCommande.setPrix((Float)table.getValueAt(table.getSelectedRow(), 2));
		 lineCommande.setQuantite(Integer.parseInt(textField_Qt.getText()));
		 lineCommande.calculerPrix();
		 return lineCommande;
		
	}
	
	public boolean verifier(){
		// Verifier quantite
		try {
			Integer.parseInt(textField_Qt.getText());
		} catch (Exception e) {
			err_Qt.setToolTipText("Pas de lettre svp!");
			err_Qt.setVisible(true);
			return false;
		}
		if(textField_Qt.getText()=="" | textField_Qt.getText()==null){
			err_Qt.setToolTipText("Quantite obligatoire");
			err_Qt.setVisible(true);
			return false;
		}
		if(table.getSelectedRow()<0){
			JOptionPane.showMessageDialog(null, "<html><center><font color='red'>Choisir un Article SVP !!</font></center></html>");
			return false;
		}
		err_Qt.setVisible(false);
		return true;
	}
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getTextField_Qt() {
		return textField_Qt;
	}

	public void setTextField_Qt(JTextField textField_Qt) {
		this.textField_Qt = textField_Qt;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public controlerInsererLineCom getControlerInsererLineCom() {
		return controlerInsererLineCom;
	}

	public void setControlerInsererLineCom(controlerInsererLineCom controlerInsererLineCom) {
		this.controlerInsererLineCom = controlerInsererLineCom;
	}

	public JButton getBtnInserer() {
		return btnInserer;
	}

	public void setBtnInserer(JButton btnInserer) {
		this.btnInserer = btnInserer;
	}
	public static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
}

}
