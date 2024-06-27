package ar.edu.unlu.corazones.vista;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import ar.edu.unlu.corazones.modelo.Carta;
import ar.edu.unlu.corazones.modelo.Mazo;
import ar.edu.unlu.corazones.modelo.Palo;
import ar.edu.unlu.corazones.vista.gui.VistaCarta;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;

public class VistaGrafica extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaGrafica frame = new VistaGrafica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaGrafica() {
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 52, 764, 498);
		getContentPane().add(panel);
		
		Mazo mazo = new Mazo();
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		VistaCarta vistaCarta = new VistaCarta(mazo.sacarCarta());
		panel.add(vistaCarta);
		vistaCarta.setLayout(null);
		VistaCarta vistaCarta_1 = new VistaCarta(mazo.sacarCarta());
		panel.add(vistaCarta_1);
		vistaCarta_1.setLayout(null);
		VistaCarta vistaCarta_2 = new VistaCarta(mazo.sacarCarta());
		panel.add(vistaCarta_2);
		vistaCarta_2.setLayout(null);
		VistaCarta vistaCarta_3 = new VistaCarta(mazo.sacarCarta());
		panel.add(vistaCarta_3);
		vistaCarta_3.setLayout(null);
		VistaCarta vistaCarta_4 = new VistaCarta(mazo.sacarCarta());
		panel.add(vistaCarta_4);
		vistaCarta_4.setLayout(null);
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		
		
		/**
		Carta carta = new Carta(Palo.CORAZONES,2);
		panel.setLayout(null);
		
		VistaCarta vistaCarta = new VistaCarta(carta);
		vistaCarta.setBounds(0, 0, 201, 303);
		panel.add(vistaCarta);
		vistaCarta.setLayout(null);**/
		
		
	
	}
}

