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

public class VistaGrafica extends JFrame {

	private static final long serialVersionUID = 1L;

	private FondoTapete panelPrincipal;
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelEste;
	private JPanel panelOeste;
	private JPanel panelCentro;

	/**
	 * Metodo main, punto de entrada de la aplicacion
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Se crea y muestra la interfaz grafica
					VistaGrafica frame = new VistaGrafica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor
	 */
	public VistaGrafica() {

		/* CONFIGURACIONES DE VENTANA */
		setTitle("Corazones"); //Titulo de la ventana
		setSize(1000, 800); //Tamaño de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //La aplicacion se cierra cuando yo cierro la ventana
		setLocationRelativeTo(null); //Centra la ventana en la pantalla.

		// Tapete de juego
		panelPrincipal = new FondoTapete("/ar/edu/unlu/corazones/img/tapete.jpg");
		panelPrincipal.setLayout(new BorderLayout());
		setContentPane(panelPrincipal); //Lo pone como panel principal del JFrame, mostrando el tapete en la ventana

		//String[] jugadores = controlador.listaJugadores();
		
		// Panel para cada jugador y el centro
		/*panelNorte = crearPanelJugador(jugadores[1]);
		panelSur = crearPanelJugador(jugadores[2]);
		panelEste = crearPanelJugador(jugadores[3]);
		panelOeste = crearPanelJugador(jugadores[4]);
		panelCentro = crearPanelCentro();*/
		
		panelNorte = crearPanelJugador("Norte");
		panelSur = crearPanelJugador("Sur");
		panelEste = crearPanelJugador("Este");
		panelOeste = crearPanelJugador("Oeste");
		panelCentro = crearPanelCentro();

		// Agrego los paneles
		panelPrincipal.add(panelNorte, BorderLayout.NORTH);
		panelPrincipal.add(panelSur, BorderLayout.SOUTH);
		panelPrincipal.add(panelEste, BorderLayout.EAST);
		panelPrincipal.add(panelOeste, BorderLayout.WEST);
		panelPrincipal.add(panelCentro, BorderLayout.CENTER);
		

		/** SIMULANDO CARTAS**/
		Carta[] cartas = { new Carta(Palo.CORAZONES, 1), new Carta(Palo.CORAZONES, 2), new Carta(Palo.CORAZONES, 3),
				new Carta(Palo.CORAZONES, 4), new Carta(Palo.CORAZONES, 5), new Carta(Palo.CORAZONES, 6),
				new Carta(Palo.CORAZONES, 7), new Carta(Palo.CORAZONES, 8), new Carta(Palo.CORAZONES, 9),
				new Carta(Palo.CORAZONES, 10), new Carta(Palo.CORAZONES, 11), new Carta(Palo.CORAZONES, 12),
				new Carta(Palo.CORAZONES, 13), };
		
		for(Carta carta: cartas) {
			agregarCartas(panelNorte, carta);
			agregarCartas(panelSur, carta);
			agregarCartas(panelEste, carta);
			agregarCartas(panelOeste, carta);
		}
		

        /**mostrarCartasJugador(cartas, panelSur);
		mostrarCartasJugador(cartas, panelEste);
		mostrarCartasJugador(cartas, panelOeste);
		mostrarCartasJugador(cartas, panelNorte);**/

	}

	private JPanel crearPanelCentro() {
	    // Crear un nuevo panel transparente (el fondo no se verá)
	    JPanel panel = new JPanel();
	    panel.setOpaque(false); // Hace que el panel sea transparente (no opaco)
	    panel.setLayout(new GridBagLayout()); // Usa un GridBagLayout para tener control sobre la disposición de las cartas

	    // Crear un objeto GridBagConstraints para controlar la posición de los componentes en la grilla
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0; // Columna 0 (posición horizontal)
	    gbc.gridy = 0; // Fila 0 (posición vertical)
	    gbc.weightx = 1.0; // Indica que las celdas horizontales deberían expandirse si es necesario
	    gbc.weighty = 1.0; // Indica que las celdas verticales deberían expandirse si es necesario
	    gbc.anchor = GridBagConstraints.CENTER; // Posiciona los componentes en el centro de cada celda

	    // Crear las vistas de las cartas (sin cartas específicas, usando el constructor vacío)
	    VistaCarta cartaNorte = new VistaCarta();
	    VistaCarta cartaSur = new VistaCarta();
	    VistaCarta cartaEste = new VistaCarta();
	    VistaCarta cartaOeste = new VistaCarta();

	    // Posicionar las cartas en las posiciones correspondientes a una cruz

	    // Carta en la posición norte
	    gbc.gridx = 0; // Columna 0
	    gbc.gridy = 1; // Fila 1
	    panel.add(cartaNorte, gbc); // Agregar cartaNorte al panel en la posición especificada

	    // Carta en la posición este
	    gbc.gridx = 1; // Columna 1
	    gbc.gridy = 0; // Fila 0
	    panel.add(cartaEste, gbc); // Agregar cartaEste al panel en la posición especificada

	    // Carta en la posición oeste
	    gbc.gridx = 2; // Columna 2
	    gbc.gridy = 1; // Fila 1
	    panel.add(cartaOeste, gbc); // Agregar cartaOeste al panel en la posición especificada

	    // Carta en la posición sur
	    gbc.gridx = 1; // Columna 1
	    gbc.gridy = 2; // Fila 2
	    panel.add(cartaSur, gbc); // Agregar cartaSur al panel en la posición especificada

	    // Retornar el panel configurado
	    return panel;
	}

	private JPanel crearPanelJugador(String nombre) {
		JPanel panel = new JPanel(); //Creo el pane
		panel.setLayout(new FlowLayout(FlowLayout.CENTER)); //Configura el layout del panel para centrar el contenido
		panel.setPreferredSize(new Dimension(200, 100)); //Tamaño del panel 
		//panel.setBorder(BorderFactory.createTitledBorder(nombre)); //Borde del panel con el nombre del jugador
		//panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), nombre));
		panel.setBackground(Color.BLUE);
		panel.setOpaque(true); // Fondo no transparente
		
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
	
    private void agregarCartas(JPanel panel, Carta carta) {
        VistaCarta vistaCarta = new VistaCarta(carta);
        panel.add(vistaCarta);
        panel.revalidate(); // Actualiza el panel para mostrar los cambios
        panel.repaint();    // Redibuja el panel para asegurar que se reflejen los cambios
    }
	
    /**private void agregarCartas(JPanel panel, Carta carta) {
        VistaCarta vistaCarta = new VistaCarta(carta);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(vistaCarta, gbc);
        panel.revalidate(); // Actualiza el panel para mostrar los cambios
        panel.repaint();    // Redibuja el panel para asegurar que se reflejen los cambios
    }**/

}
