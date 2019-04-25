package Model;

/**
 * Autor:Calacean Ionut Eugen Facultatea de Automatica si Calculatoare Grupa
 * 30229
 */
public class Monom implements Comparable<Object> {

	private int putere;
	private Number coeficient;

	public Monom() {
		super();
		this.putere = 0;
		this.coeficient = 0;
	}

	public Monom(int putere, Number coeficient) {
		super();
		this.putere = putere;
		this.coeficient = coeficient;
	}

	public Monom inmultire(Monom m) {
		Monom x = new Monom();
		if (this.getCoeficient().doubleValue() != this.getCoeficient().intValue()
				|| m.getCoeficient().doubleValue() != m.getCoeficient().intValue()) //cauzul cu o valoare intreaga
			x.setCoeficient(this.getCoeficient().doubleValue() * m.getCoeficient().doubleValue());
		else
			x.setCoeficient(this.getCoeficient().intValue() * m.getCoeficient().intValue());
		x.setPutere(this.getPutere() + m.getPutere());
		return x;
	}

	public Monom impartire(Monom m) {// grad this> grad m se considera mereu
		Monom x = new Monom();
		x.setCoeficient(this.getCoeficient().doubleValue() / m.getCoeficient().doubleValue());
		x.setPutere(this.getPutere() - m.getPutere());
		return x;
	}

	public Monom adunare(Monom m) {
		Monom x = new Monom();
		if (this.getCoeficient().doubleValue() != this.getCoeficient().intValue()
				|| m.getCoeficient().doubleValue() != m.getCoeficient().intValue())  //cauzul cu o valoare intreaga
			x.setCoeficient(this.getCoeficient().doubleValue() + m.getCoeficient().doubleValue());
		else
			x.setCoeficient(this.getCoeficient().intValue() + m.getCoeficient().intValue());
		x.setPutere(this.putere);
		return x;
	}

	public void derivare() {//X^2 ' => 2X^1
		int coeficient = this.getCoeficient().intValue();
		int putere = this.getPutere();
		this.setCoeficient(coeficient * putere);
		this.setPutere(putere - 1);
	}

	public void integrare() {// 3X => 3/2(X^2)
		double coeficient = this.getCoeficient().doubleValue();
		int putere = this.getPutere();
		this.setCoeficient(coeficient / (putere + 1));
		this.setPutere(putere + 1);
	}

	public int getPutere() {
		return putere;
	}

	public void setPutere(int putere) {
		this.putere = putere;
	}

	public Number getCoeficient() {
		return coeficient;
	}

	public void setCoeficient(Number coeficient) {
		this.coeficient = coeficient;
	}

	public double taiereZecimale(double coef) {
		int intreg = (int) (100 * coef);
		coef = (double) intreg / 100;
		return coef;//utila pentru trimming la numarul de zecimale ale coef intreg la 2
	}

	@Override
	public String toString() { // aceasta metoda transforma in String fiecare monom, fapt util in constructia 
		int coeficientIntreg;  //polinomului ca String
		double coeficientZecimal;
		if (coeficient.doubleValue() != coeficient.intValue()) {// cazul cu coeficient real
			coeficientZecimal = this.taiereZecimale(coeficient.doubleValue());

			if (putere != 0) {
				if (coeficientZecimal > 0) {
					return "+" + coeficientZecimal + "X^" + putere; //cazul cu putere
				}
				return coeficientZecimal + "X^" + putere;
			}
			if (coeficientZecimal > 0)
				return "+" + coeficientZecimal;
			return coeficientZecimal + "";   //aici intra cazul cu '-' se pune automat

		}

		else {  //cazul cu coeficient intreg
			coeficientIntreg = coeficient.intValue();
			if (putere != 0) {
				if (coeficientIntreg > 0)
					return "+" + coeficientIntreg + "X^" + putere; //cazul cu putere
				return coeficientIntreg + "X^" + putere;
			} 
			if (coeficientIntreg > 0)
				return "+" + coeficientIntreg;
			return coeficientIntreg + "";
		}

	}

	public int compareTo(Object arg0) {  //utila opentru sortare
		// TODO Auto-generated method stub
		Monom m = (Monom) arg0;
		return this.getPutere() - m.getPutere();

	}

}
