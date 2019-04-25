package Controller;
/**
 * Autor:Calacean Ionut Eugen
 * Facultatea de Automatica si Calculatoare
 * Grupa 30229
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import Model.Monom;
import Model.Polinom;
import View.View;

public class Controller {

	private View myView;

	public Controller(View myView) {
		this.myView = myView;
		addListeners(this.myView);
	}

	public static ArrayList<Monom> parsingPol(String pol) {
		String regex = "(?:\\h*)([-+]?\\d*)(?:\\h*)[xX](\\^(\\d+))?|(?:\\h*)([-+]?\\d+)";//sursa stackoverflow
		Pattern myPattern = Pattern.compile(regex);
		pol=pol.replaceAll("\\s",""); //se suprimeaza spatiile
		Matcher myMatcher = myPattern.matcher(pol);  //instantiere Matcher
		ArrayList<Monom> polinom = new ArrayList<Monom>();
		int coeficient = 0, putere = 0;
		try {
			for (int i = 0; i < pol.length(); i++){
			    char c = pol.charAt(i);        
			    //verificam un input valid daca nu e se afiseaza Warning
			    if((Character.isLetter(c)&& c!='X' && c!='x')||(!Character.isDigit(c)&& !Character.isLetter(c)&& c!='+' && c!='-'&& c!='^'))
			    	
			      throw new IllegalArgumentException("Invalid input!");
			}
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid input", JOptionPane.WARNING_MESSAGE);
		}

		while (myMatcher.find()) {
			if (myMatcher.group(4) != null) {
				coeficient = Integer.parseInt(myMatcher.group(4)); // doar cueficient , deci monom de grad 0
				putere = 0;
			} else {
				if (myMatcher.group(2) != null) // doar x^d
					putere = Integer.parseInt(myMatcher.group(3));
				else
					putere = 1;   //daca avem doar x

				if (!myMatcher.group(1).equals("") && myMatcher.group(1) != null)// exista coef sau + -
				{
					if (myMatcher.group(1).equals("-"))
						coeficient = -1;
					else if (myMatcher.group(1).equals("+"))
						coeficient = 1;
					else
						coeficient = Integer.parseInt(myMatcher.group(1));//exista coeficient
				} else
					coeficient = 1;
			}
			polinom.add(new Monom(putere, coeficient));
		}
		return polinom;// cu aceasta lista cream polinomul
	}

	public void addListeners(final View myView) {
		myView.getAdunare().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Polinom pol1, pol2, pol3;
				pol1 = new Polinom(parsingPol(myView.getText1()));//parsare primul polinom
				pol2 = new Polinom(parsingPol(myView.getText2()));//parsare al doilea polinom
				pol3 = pol1.adunare(pol2);//operatia efectiva
				myView.setRezultat(pol3.toString());//transformare rezultat in String si afisare in labelul de rez

			}

		});

		myView.getScadere().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Polinom pol1, pol2, pol3;
				pol1 = new Polinom(parsingPol(myView.getText1()));
				pol2 = new Polinom(parsingPol(myView.getText2()));
				pol3 = pol1.scadere(pol2);
				myView.setRezultat(pol3.toString());

			}

		});

		myView.getInmultire().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Polinom pol1, pol2, pol3;
				pol1 = new Polinom(parsingPol(myView.getText1()));
				pol2 = new Polinom(parsingPol(myView.getText2()));
				pol3 = pol1.inmultire(pol2);
				myView.setRezultat(pol3.toString());

			}

		});

		myView.getImpartire().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Polinom pol1, pol2, pol3;
				pol1 = new Polinom(parsingPol(myView.getText1()));

				try {                                   //incercare impartire la 0 daca da se afiseaza eroare
					if (myView.getText2().equals("0"))
						throw new Exception("Eroare impartire cu 0!");
					else {
						pol2 = new Polinom(parsingPol(myView.getText2()));
						pol3 = pol1.impartire(pol2);
						if(pol2.gradPolinom()!=0)
						    myView.setRezultat(pol3.toString()+ " Rest: "+pol1.toString());
						else
							myView.setRezultat(pol3.toString());
							
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		myView.getDerivare().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Polinom pol1, pol3;
				pol1 = new Polinom(parsingPol(myView.getText1()));
				myView.setText2();
				pol3 = pol1.derivare();
				myView.setRezultat(pol3.toString());

			}

		});

		myView.getIntegrare().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Polinom pol1, pol3;
				pol1 = new Polinom(parsingPol(myView.getText1()));
				myView.setText2();
				pol3 = pol1.integrare();
				myView.setRezultat(pol3.toString());

			}

		});
		myView.getReset().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				myView.setReset();

			}

		});
	}
}
