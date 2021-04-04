package classeMetier;

public class Sortie {
	private Integer nums;
	private Integer qte;
	private String dateSortie;
	private Float prix;
	private Integer codeArt;
	public Sortie(Integer nums, Integer qte, String dateSortie, Float prix, Integer codeArt) {
		super();
		this.nums = nums;
		this.qte = qte;
		this.dateSortie = dateSortie;
		this.prix = prix;
		this.codeArt = codeArt;
	}
	public Sortie() {
		// TODO Auto-generated constructor stub
	}

	public Integer getNums() {
		return nums;
	}
	public void setNums(Integer nums) {
		this.nums = nums;
	}
	public Integer getQte() {
		return qte;
	}
	public void setQte(Integer qte) {
		this.qte = qte;
	}
	public String getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}
	public Float getPrix() {
		return prix;
	}
	public void setPrix(Float f) {
		this.prix = f;
	}
	public Integer getCodeArt() {
		return codeArt;
	}
	public void setCodeArt(Integer i) {
		this.codeArt = i;
	}


}
