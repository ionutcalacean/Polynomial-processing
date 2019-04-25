package Model;
/**
 * Autor:Calacean Ionut Eugen
 * Facultatea de Automatica si Calculatoare
 * Grupa 30229
 */
import java.util.ArrayList;
import java.util.Collections;


public class Polinom {

	private ArrayList<Monom> monomi;

	public Polinom(ArrayList<Monom> monomi) {
		this.monomi = monomi;
	}

	public Polinom() {
		monomi = new ArrayList<Monom>();
	}

	public Polinom(int grad) {
		monomi = new ArrayList<Monom>(grad);
		for (int i = 0; i <= grad; i++) {
			monomi.add(new Monom(i, 0));// constructor care face un polinom cu toti coef 0, avem nevoie de el la adunare
		}
	}

	public Polinom adunare(Polinom secondPol) {
		int gradMaxim, gradCurent;
		if (secondPol.gradPolinom() > this.gradPolinom())// am selectat gradul maxim
			gradMaxim = secondPol.gradPolinom();
		else
			gradMaxim = this.gradPolinom();
		Polinom newPol = new Polinom(gradMaxim + 1);
		Monom monomCurent;

		for (Monom list : monomi) { // se aduna polinomul curent la noul polinom
			gradCurent = list.getPutere();
			monomCurent = newPol.getMonomi().get(gradCurent).adunare(list);
			newPol.getMonomi().set(gradCurent, monomCurent);
		}
		for (Monom list : secondPol.getMonomi()) { // se aduna al doilea polinom
			gradCurent = list.getPutere();
			monomCurent = newPol.getMonomi().get(gradCurent).adunare(list);
			newPol.getMonomi().set(gradCurent, monomCurent);
		}
		newPol.stergereZerouri();
		return newPol;

	}

	public Polinom inmultire(Polinom secondPol) {
		ArrayList<Monom> newPol = new ArrayList<Monom>();

		for (Monom mon1 : this.getMonomi()) {
			for (Monom mon2 : secondPol.getMonomi()) {
				newPol.add(mon1.inmultire(mon2)); //se inmultesc efectiv toti monomii
			}
		}
		Collections.sort(newPol);
		stergereDuplicate(newPol);// se aduna termenii cu acelasi grad si se sterge unul dintre duplicate

		Polinom rezultat = new Polinom(newPol);
		rezultat.stergereZerouri();//daca avem monomi cu coeficientul 0 ii stergem
		return rezultat;
	}

	public Polinom scadere(Polinom secondPol) {
		Polinom dublare = new Polinom(secondPol.getMonomi());
		double coeficient;
		for (Monom list : dublare.getMonomi()) {    //adunare cu semnul celui de-al doilea polinom scchimbat
			coeficient = list.getCoeficient().doubleValue();
			list.setCoeficient(-coeficient);
		}

		return this.adunare(dublare);
	}

	public Polinom integrare() {
		Polinom pol = new Polinom(this.getMonomi());
		for (Monom list : pol.getMonomi()) {
			list.integrare();   //integram pe rand toti monomii constituenti ai primului polinom
		}
		return pol;
	}

	public Polinom derivare() {
		Polinom pol = new Polinom(this.getMonomi());
		for (Monom list : pol.getMonomi()) {
			list.derivare(); //derivam pe rand toti monomii constituenti ai primului polinom
		}
		pol.stergereZerouri();//stergem eventualii monomi cu coeficientul 0( care se obtin din derivare constanta)
		return pol;
	}

	public Polinom impartire(Polinom secondPol) {
		Polinom pol1, auxiliar, rezultat = new Polinom();//rezultat va pastra rezultatul efectiv, auxiliarul 
		                                                  //construieste treptat rezultatul

		pol1 = new Polinom(this.getMonomi());// dublare a polinomului curent

		if (secondPol.gradPolinom() == 0) {   //tratare a cazului in care se imparte la o constanta
           ArrayList<Monom> rezList=new ArrayList<Monom>(this.getMonomi());//punem polinomul 1 in rezList
           int coeficient=secondPol.getMonomi().get(0).getCoeficient().intValue();//luam aici constanta
           for(Monom list:rezList)
           {
        	   list.setCoeficient(list.getCoeficient().doubleValue()/(double)coeficient);
           }
           rezultat.setMonomi(rezList);
		}

		else {
			while (pol1.gradPolinom() >= secondPol.gradPolinom()) {
				auxiliar = new Polinom(pol1.impartePolinom(secondPol));//se impart monomii cei mai semnificativi
				rezultat = rezultat.adunare(auxiliar);//rezultatul se construieste treptat
				auxiliar = auxiliar.inmultire(secondPol);// cel mai semnificativ se inmultetste cu impartitorul
				pol1 = pol1.scadere(auxiliar);// scadem din polinomul 1 pe cel auxiliar
			}
		}

		this.setMonomi(pol1.getMonomi());// punem restul in polinomul 1(this)

		return rezultat;
	}

	public ArrayList<Monom> impartePolinom(Polinom secondPol) {
		ArrayList<Monom> auxiliar = new ArrayList<Monom>();
		Monom mon1, mon2, mon3;

		mon1 = this.getDominantMonom();
		mon2 = secondPol.getDominantMonom();
		mon3 = mon1.impartire(mon2); // se impart monomii dominanti din deimpartit si impartitor

		auxiliar.add(mon3);// punem intr-o lista pentru simplul fapt de a putea crea polinomul auxiliar din
							// impartire

		return auxiliar;

	}

	public Monom getDominantMonom() {
		for (Monom list : this.getMonomi()) {
			if (list.getPutere() == this.gradPolinom())
				return list;       //returneaza monomul dominant
		}
		return null;// doar pentru a compila cu succes pentru ca va intra mereu in if
	}

	public Polinom stergereZerouri() {
		ArrayList<Monom> newList = new ArrayList<Monom>(monomi);

		for (Monom list : newList) {
			if (list.getCoeficient().doubleValue() == 0)
				monomi.remove(list);        //se sterg monomii cu coeficientul 0
		}
		return this;
		/*
		 * Iterator<Monom> i=monomi.iterator(); while(i.hasNext()) { Monom
		 * curent=i.next(); if(curent.getCoeficient().doubleValue()==0) i.remove(); }
		 */
	}

	public int cautaGradMaxim(Polinom p2) {
		int gradMaxim;
		if (p2.gradPolinom() > this.gradPolinom())
			gradMaxim = p2.gradPolinom();
		else
			gradMaxim = p2.gradPolinom();

		return gradMaxim;   // returneaza gradul maxim dintre cele 2 polinoame
	}

	public int gradPolinom() {
		int gradMaxim = Integer.MIN_VALUE;
		for (Monom list : monomi) {
			if (list.getPutere() > gradMaxim)
				gradMaxim = list.getPutere();  //se returneaza gradul maxim prin iterare si salvare putere maxima
		}
		return gradMaxim;
	}

	public void stergereDuplicate(ArrayList<Monom> monomi) {
		Monom monomCurent = new Monom(-10, 0);
		Monom monomRezultat = new Monom(0, 0);
		ArrayList<Monom> newList = new ArrayList<Monom>(monomi);// copie a listei de monomi
		int sterse = 0, iteratii = 0;
		for (Monom list : newList) {
			if (monomCurent.getPutere() != list.getPutere()) {
				if (monomCurent.getPutere() != -10) // prima iteratie este evitata
					monomi.set(newList.lastIndexOf(monomCurent) - sterse, monomRezultat); // setam monomul rezultat in
																							// lista de monomi(ultima
																							// aparitie a lui - cate au
																							// fost sterse) pentru a comprima lista
				monomCurent = list;
				monomRezultat = new Monom(monomCurent.getPutere(), monomCurent.getCoeficient());
				sterse += iteratii;
				iteratii = 0;
			} else {
				monomRezultat = monomRezultat.adunare(list);
				monomi.remove(list);
				iteratii++;
			}
		}
	}

	public ArrayList<Monom> getMonomi() {
		return monomi;
	}

	public void setMonomi(ArrayList<Monom> monomi) {
		this.monomi = monomi;
	}

	@Override
	public String toString() {  //pentru a transforma in string, intai se sorteaza si apoi se adauga pe rand la String
		Collections.sort(monomi);
		Collections.reverse(monomi);
		String str = new String();
		for (Monom list : monomi)
			str += list.toString();

		return str;
	}

}
