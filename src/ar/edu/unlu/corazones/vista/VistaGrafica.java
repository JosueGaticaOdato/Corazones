package ar.edu.unlu.corazones.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.corazones.modelo.Mazo;
import ar.edu.unlu.corazones.vista.gui.VistaCarta;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;

public class VistaGrafica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Mazo mazo;

	public Mazo getMazo() {
		return mazo;
	}

	public void setMazo(Mazo mazo) {
		this.mazo = mazo;
	}

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
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		JPanel panel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(panel);
		contentPane.add(scrollPane);
		
		panel.setLayout(new GridLayout(4,13, 10, 10));
		
		mazo = new Mazo();
		for (int i = 0; i < mazo.Cant_Cartas; i++) {
			panel.add(new VistaCarta(mazo.getMazo()[i]));
		}
	}
	
	public void agregarCartas() {
		for (int i = 0; i < mazo.Cant_Cartas; i++) {
			contentPane.add(new VistaCarta(mazo.getMazo()[i]));
		}
	}
}
