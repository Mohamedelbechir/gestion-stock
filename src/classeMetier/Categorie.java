package classeMetier;

public class Categorie {
	
	private Integer idCat;
	private String nomCate;
	private String description;

	public Categorie(Integer idCat,String nomCate, String description) {
		super();
		this.nomCate = nomCate;
		this.description = description;
		this.idCat=idCat;
	}
	public Categorie() {
		// TODO Auto-generated constructor stub
	}
	public String getNomCate() {
		return nomCate;
	}
	public void setNomCate(String nomCate) {
		this.nomCate = nomCate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIdCat() {
		return idCat;
	}
	public void setIdCat(Integer idCat) {
		this.idCat = idCat;
	}

}
