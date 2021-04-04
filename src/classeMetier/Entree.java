package classeMetier;

public class Entree {
	private Integer numEntree;
	private Integer qte;
	private String dateentre;
	private float prixdentre;
	private Integer codeart;

	public Entree(Integer numEntree, Integer qte, String dateentre, Integer prixdentre, Integer codeart) {
		super();
		this.numEntree = numEntree;
		this.qte = qte;
		this.dateentre = dateentre;
		this.prixdentre = prixdentre;
		this.codeart = codeart;
	}
	public Entree() {
		// TODO Auto-generated constructor stub
	}
	public void setNumEntree(Integer numEntree) {
		this.numEntree = numEntree;
	}
	public Integer getQte() {
		return qte;
	}
	public void setQte(Integer qte) {
		this.qte = qte;
	}
	public String getDateentre() {
		return dateentre;
	}
	public void setDateentre(String dateentre) {
		this.dateentre = dateentre;
	}
	public float getPrixdentre() {
		return prixdentre;
	}
	public void setPrixdentre(float f) {
		this.prixdentre = f;
	}
	public Integer getCodeart() {
		return codeart;
	}
	public void setCodeart(Integer codeart) {
		this.codeart = codeart;
	}


}
