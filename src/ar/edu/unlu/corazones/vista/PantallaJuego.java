package ar.edu.unlu.corazones.vista;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
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

public class PantallaJuego extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelCartas;
	
	private FondoTapete panelMesa;
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelEste;
	private JPanel panelOeste;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaJuego frame = new PantallaJuego();
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
	public PantallaJuego() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaJuego.class.getResource("/ar/edu/unlu/corazones/img/CORAZONES.png")));
		setTitle("Corazones");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		// Tapete de juego
		FondoTapete panelPrincipal = new FondoTapete("/ar/edu/unlu/corazones/img/tapete.jpg");
        panelPrincipal.setLayout(new BorderLayout());
        setContentPane(panelPrincipal);
		
		//Panel para cada jugador
		panelNorte = crearPanelJugador("Norte");
        panelSur = crearPanelJugador("Sur");
        panelEste = crearPanelJugador("Este");
        panelOeste = crearPanelJugador("Oeste");
        
        //Agrego los paneles
        add(panelNorte, BorderLayout.NORTH);
        add(panelSur, BorderLayout.SOUTH);
        add(panelEste, BorderLayout.EAST);
        add(panelOeste, BorderLayout.WEST);
        
        //Panel para las cartas que se van a tirar
        //panelMesa = new JPanel();
        //panelMesa.setLayout(new GridLayout(2, 2, 10, 10));
        //panelMesa.setBackground(Color.GREEN);
        //add(panelMesa, BorderLayout.CENTER);
	}
	
	private JPanel crearPanelJugador(String posicion)
	{
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 100));
        panel.setBorder(BorderFactory.createTitledBorder(posicion));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setOpaque(false); // Fondo transparente
        return panel;
	}
	
	public void mostrarCartas(Carta[] cartas) {
		panelCartas.removeAll();
		for (Carta carta: cartas) {
			panelCartas.add(new VistaCarta(carta));
		}
		panelCartas.revalidate();
		panelCartas.repaint();
	}

}
