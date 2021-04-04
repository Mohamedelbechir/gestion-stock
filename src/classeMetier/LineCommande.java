package classeMetier;

public class LineCommande {
	private Integer index;
	private Integer quantite;
	private Float prix;	
	private Float montat;
	private Integer codeArt;
	private Integer codeCommande;
	
	
	


	public LineCommande(Integer index, Integer quantite, Float prix, Float montat, Integer codeArt,
			Integer codeCommande) {
		super();
		this.index = index;
		this.quantite = quantite;
		this.prix = prix;
		this.montat = montat;
		this.codeArt = codeArt;
		this.codeCommande = codeCommande;
	}


	public LineCommande() {
		// TODO Auto-generated constructor stub
	}


	public Integer getIndex() {
		return index;
	}


	public void setIndex(Integer index) {
		this.index = index;
	}


	public Integer getQuantite() {
		return quantite;
	}


	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}


	public Float getPrix() {
		return prix;
	}


	public void setPrix(Float prix) {
		this.prix = prix;
	}


	public Float getMontat() {
		return montat;
	}


	public void setMontat(Float montat) {
		this.montat = montat;
	}


	public Integer getCodeArt() {
		return codeArt;
	}


	public void setCodeArt(Integer codeArt) {
		this.codeArt = codeArt;
	}


	public Integer getCodeCommande() {
		return codeCommande;
	}


	public void setCodeCommande(Integer codeCommande) {
		this.codeCommande = codeCommande;
	}


	public void calculerPrix(){
		montat=prix*quantite;
	}

}
