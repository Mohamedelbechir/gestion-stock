package classeMetier;

public class Article {
	private Integer codeArt;
	private Integer idCat ;
	private Integer idFour;
	private String designation;
	private Integer unite;
	private Integer qte;
	private Float prix;
	private String emplacement;


	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Article(Integer codeArt, Integer idCat, Integer idFour, String designation, Integer unite, Integer qte,
			Float prix, String emplacement) {
		super();
		this.codeArt = codeArt;
		this.idCat = idCat;
		this.idFour = idFour;
		this.designation = designation;
		this.unite = unite;
		this.qte = qte;
		this.prix = prix;
		this.emplacement = emplacement;
	}



	public void setCodeArt(Integer codeArt) {
		this.codeArt = codeArt;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Integer getUnite() {
		return unite;
	}
	public void setUnite(Integer unite) {
		this.unite = unite;
	}
	public Integer getQte() {
		return qte;
	}
	public void setQte(Integer qte) {
		this.qte = qte;
	}
	public Float getPrix() {
		return prix;
	}
	public void setPrix(Float prix) {
		this.prix = prix;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public Integer getIdCat() {
		return idCat;
	}

	public void setIdCat(Integer idCat) {
		this.idCat = idCat;
	}

	public Integer getCodeArt() {
		return codeArt;
	}



	public Integer getIdFour() {
		return idFour;
	}



	public void setIdFour(Integer idFour) {
		this.idFour = idFour;
	}




}
