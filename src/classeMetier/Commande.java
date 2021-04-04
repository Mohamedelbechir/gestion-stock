package classeMetier;

import java.util.ArrayList;

import antlr.collections.List;

public class Commande {
	private Integer numcmd;
	private String datecmd;
	private String etat;
	private ArrayList<LineCommande> listCommande;

	
	public Commande(Integer numcmd, String datecmd, String etat) {
		super();
		this.numcmd = numcmd;
		this.datecmd = datecmd;
		this.etat = etat;
		listCommande = listCommande=new ArrayList<>();
	}
	public Commande() {
		listCommande = listCommande=new ArrayList<>();
	}
	public void setNumcmd(Integer numcmd) {
		this.numcmd = numcmd;
	}
	public String getDatecmd() {
		return datecmd;
	}
	public void setDatecmd(String datecmd) {
		this.datecmd = datecmd;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	public void inserLine(LineCommande lineCommande){
		listCommande.add(lineCommande);
	}
	public ArrayList<LineCommande> getListCommande() {
		return listCommande;
	}
	public void setListCommande(ArrayList<LineCommande> listCommande) {
		this.listCommande = listCommande;
	}
	public Integer getNumcmd() {
		return numcmd;
	}
	
}
