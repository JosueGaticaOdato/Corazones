package ar.edu.unlu.corazones.vista;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ar.edu.unlu.corazones.controlador.Controlador;
import ar.edu.unlu.corazones.modelo.Carta;
import ar.edu.unlu.corazones.modelo.Palo;
import ar.edu.unlu.corazones.vista.gui.FondoTapete;
import ar.edu.unlu.corazones.vista.gui.VistaCarta;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class VistaGrafica extends JFrame implements IVista {

	private static final long serialVersionUID = 1L;

	private FondoTapete panelPrincipal;
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelEste;
	private JPanel panelOeste;
	private JPanel panelCentro;
	private Controlador controlador;

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

		setTitle("Corazones");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Tapete de juego
		panelPrincipal = new FondoTapete("/ar/edu/unlu/corazones/img/tapete.jpg");
		panelPrincipal.setLayout(new BorderLayout());
		setContentPane(panelPrincipal);

		String[] jugadores = controlador.listaJugadores();
		
		// Panel para cada jugador y el centro
		panelNorte = crearPanelJugador(jugadores[1]);
		panelSur = crearPanelJugador(jugadores[2]);
		panelEste = crearPanelJugador(jugadores[3]);
		panelOeste = crearPanelJugador(jugadores[4]);
		panelCentro = crearPanelCentro();

		// Agrego los paneles
		panelPrincipal.add(panelNorte, BorderLayout.NORTH);
		panelPrincipal.add(panelSur, BorderLayout.SOUTH);
		panelPrincipal.add(panelEste, BorderLayout.EAST);
		panelPrincipal.add(panelOeste, BorderLayout.WEST);
		panelPrincipal.add(panelCentro, BorderLayout.CENTER);

		/** SIMULANDO CARTAS
		Carta[] cartas = { new Carta(Palo.CORAZONES, 1), new Carta(Palo.CORAZONES, 2), new Carta(Palo.CORAZONES, 3),
				new Carta(Palo.CORAZONES, 4), new Carta(Palo.CORAZONES, 5), new Carta(Palo.CORAZONES, 6),
				new Carta(Palo.CORAZONES, 7), new Carta(Palo.CORAZONES, 8), new Carta(Palo.CORAZONES, 9),
				new Carta(Palo.CORAZONES, 10), new Carta(Palo.CORAZONES, 11), new Carta(Palo.CORAZONES, 12),
				new Carta(Palo.CORAZONES, 13), };

		mostrarCartasJugador(cartas, panelSur);
		mostrarCartasJugador(cartas, panelEste);
		mostrarCartasJugador(cartas, panelOeste);
		mostrarCartasJugador(cartas, panelNorte);**/

	}

	private JPanel crearPanelCentro() {

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridBagLayout());

		// Crear un GridBagConstraints para posicionar las cartas
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;

		VistaCarta cartaNorte = new VistaCarta();
		VistaCarta cartaSur = new VistaCarta();
		VistaCarta cartaEste = new VistaCarta();
		VistaCarta cartaOeste = new VistaCarta();

		// Posicionar las cartas
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(cartaNorte, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(cartaEste, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		panel.add(cartaOeste, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(cartaSur, gbc);

		return panel;
	}

	private JPanel crearPanelJugador(String nombre) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(200, 100));
		panel.setBorder(BorderFactory.createTitledBorder(nombre));
		// panel.setBackground(Color.LIGHT_GRAY);
		panel.setOpaque(false); // Fondo transparente
		return panel;
	}

	public void mostrarCartasJugador(Carta[] cartas, JPanel panel) {
		panel.removeAll();
		for (Carta carta : cartas) {
			panel.add(new VistaCarta(carta));
		}
		panel.revalidate();
		panel.repaint();
	}

	@Override
	public void mostrarMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pedirCarta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarGanadorJugada() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jugador2deTrebol() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pasajeDeCartas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pedirCartaPasaje() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void corazonesRotos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cartaTiradaIncorrecta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cartaTiradaIncorrectaCorazones() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ganadorJuego() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finDeRonda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finPasajeDeCartas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

}
