package View;
/**
 * Autor:Calacean Ionut Eugen
 * Facultatea de Automatica si Calculatoare
 * Grupa 30229
 */
import java.awt.*;

import javax.swing.*;

public class View {
	private JTextField text1 = new JTextField(30);
	private JTextField text2 = new JTextField(30);
	private JButton adunare = new JButton("Adunare ");
	private JButton scadere = new JButton("Scadere ");
	private JButton inmultire = new JButton("Inmultire");
	private JButton impartire = new JButton("Impartire");
	private JButton integrare = new JButton("Integrare");
	private JButton derivare = new JButton("Derivare ");
	private JLabel rezultat = new JLabel("Selectati operatia");
	private JLabel pol1 = new JLabel("Introduceti primul polinom:");
	private JLabel pol2 = new JLabel("Introcudeti al doilea polinom:");
	private JButton reset=new JButton("Reset");

	public View() {
		JFrame frame = new JFrame("Polinoame");
		frame.setSize(800, 600);

		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan21 = new JPanel();
		JPanel pan22 = new JPanel();
		JPanel pan23 = new JPanel();
		JPanel pan3 = new JPanel();
		JPanel pan4 = new JPanel();
		JPanel pan5 = new JPanel();
		JPanel text1Pane = new JPanel();
		JPanel text2Pane = new JPanel();
		JPanel finalPane = new JPanel();//paneluri pentru controlul pozitiei pe finalPane si apoi pe Frame

		JLabel rezultatLabel = new JLabel("Rezultatul operatiei:");

		// Text Fields
		text1Pane.add(text1);
		text1Pane.setLayout(new FlowLayout());
		text2Pane.add(text2);
		text2Pane.setLayout(new FlowLayout());
		pan1.add(pol1);
		pan1.add(text1Pane);
		pan1.add(pol2);
		pan1.add(text2Pane);
		pan1.setLayout(new GridLayout(4,1));

		// Buttons
		pan2.add(adunare);
		pan2.add(Box.createRigidArea(new Dimension(5, 0)));
		pan2.add(scadere);
		pan21.add(inmultire);
		pan21.add(Box.createRigidArea(new Dimension(5, 0)));
		pan21.add(impartire);
		pan22.add(integrare);
		pan22.add(Box.createRigidArea(new Dimension(5, 0)));
		pan22.add(derivare);
	    pan23.add(reset);
	    pan23.add(Box.createRigidArea(new Dimension(5, 0)));
		pan2.setLayout(new BoxLayout(pan2, BoxLayout.X_AXIS));
		pan21.setLayout(new BoxLayout(pan21, BoxLayout.X_AXIS));
		pan22.setLayout(new BoxLayout(pan22, BoxLayout.X_AXIS));
		pan23.setLayout(new BoxLayout(pan23, BoxLayout.Y_AXIS));

		//pan3.add(pan1);
		//pan3.add(Box.createRigidArea(new Dimension(0, 15)));
		pan3.add(pan2);
		pan3.add(Box.createRigidArea(new Dimension(0, 15)));
		pan3.add(pan21);
		pan3.add(Box.createRigidArea(new Dimension(0, 15)));
		pan3.add(pan22);
		pan3.add(Box.createRigidArea(new Dimension(0, 15)));
		pan3.add(pan23);
		pan3.add(Box.createRigidArea(new Dimension(0, 15)));
		pan3.setLayout(new BoxLayout(pan3, BoxLayout.Y_AXIS));
		pan3.setAlignmentX(Component.RIGHT_ALIGNMENT);

		

		pan5.add(rezultatLabel);
		pan5.add(rezultat);
		pan5.setLayout(new FlowLayout());
		
        finalPane.add(pan1);
		finalPane.add(pan3);
		finalPane.add(pan4);
		finalPane.add(pan5);
		finalPane.setLayout(new BoxLayout(finalPane, BoxLayout.Y_AXIS));
		finalPane.setAlignmentX(Component.CENTER_ALIGNMENT);

		frame.setContentPane(finalPane);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public String getText1() {
		return text1.getText();
	}

	public String getText2() {
		return text2.getText();
	}
	public void setReset()//resetarea aplicatiei
	{
		text1.setText("0");
		text2.setText("0");
		rezultat.setText("");
	}
	
	public void setText2()//se foloseste cand avem operatie de derivare sau integrare, se blocheaza TextField2
	{
		text2.setEditable(false);
		text2.setText("Derivarea sau integrarea se face in primul camp!");
	}

	public JButton getImpartire() {
		return impartire;
	}

	public void setRezultat(String p3) {
		this.rezultat.setText(p3);
	}

	public JButton getInmultire() {
		return inmultire;
	}

	public JButton getAdunare() {
		return adunare;
	}

	public JButton getDerivare() {
		return derivare;
	}

	public JButton getScadere() {
		return scadere;
	}

	public JButton getIntegrare() {
		return integrare;
	}
	public JButton getReset()
	{
		text2.setEditable(true);
		return reset;
	}

}
