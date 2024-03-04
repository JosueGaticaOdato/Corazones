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
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(0, 0, 784, 54);
		getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 52, 764, 498);
		getContentPane().add(panel);
		
		Mazo mazo = new Mazo();
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		panel.add(new VistaCarta(mazo.sacarCarta()));
		
		
		/**
		Carta carta = new Carta(Palo.CORAZONES,2);
		panel.setLayout(null);
		
		VistaCarta vistaCarta = new VistaCarta(carta);
		vistaCarta.setBounds(0, 0, 201, 303);
		panel.add(vistaCarta);
		vistaCarta.setLayout(null);**/
		
		
	
	}
}

