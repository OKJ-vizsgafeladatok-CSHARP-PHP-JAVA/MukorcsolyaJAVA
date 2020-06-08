
public class Mukorcsolya {
	private String nev;
	private String orszag;
	private double technikai;
	private double komponens;
	private int levonas;
	public Mukorcsolya(String nev, String orszag, double technikai, double komponens, int levonas) {
		super();
		this.nev = nev;
		this.orszag = orszag;
		this.technikai = technikai;
		this.komponens = komponens;
		this.levonas = levonas;
	}
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	public String getOrszag() {
		return orszag;
	}
	public void setOrszag(String orszag) {
		this.orszag = orszag;
	}
	public double getTechnikai() {
		return technikai;
	}
	public void setTechnikai(double technikai) {
		this.technikai = technikai;
	}
	public double getKomponens() {
		return komponens;
	}
	public void setKomponens(double komponens) {
		this.komponens = komponens;
	}
	public int getLevonas() {
		return levonas;
	}
	public void setLevonas(int levonas) {
		this.levonas = levonas;
	}
	@Override
	public String toString() {
		return "Mukorcsolya [nev=" + nev + ", orszag=" + orszag + ", technikai=" + technikai + ", komponens="
				+ komponens + ", levonas=" + levonas + "]";
	}
	
	
}
