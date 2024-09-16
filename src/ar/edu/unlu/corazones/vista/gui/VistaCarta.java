package ar.edu.unlu.corazones.vista.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ar.edu.unlu.corazones.modelo.Carta;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class VistaCarta extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final int ANCHO = 60;
	private final int ALTO = 90;
	private final int TAMAÑO_FUENTE = 20;
	
	private Carta carta;
	

	/**
	 * Constructor donde se crea la vista de la carta
	 */
	public VistaCarta(Carta carta) {
		this();
		setCarta(carta);
		actualizarVista();
	}
	
	 // Constructor por defecto
    public VistaCarta() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(ANCHO, ALTO)); // Tamaño fijo de la carta
        setLayout(new BorderLayout());
    }
	
	
	private void actualizarVista() {
		removeAll();

		//Toma el numero de la carta
		JLabel lblValorCartaArriba = new JLabel(getCarta().getValorTexto());
		lblValorCartaArriba.setFont(new Font("Tahoma", Font.BOLD, TAMAÑO_FUENTE));
		lblValorCartaArriba.setHorizontalAlignment(SwingConstants.LEFT);
        add(lblValorCartaArriba, BorderLayout.NORTH);
		
        //Toma el numero de la carta
		JLabel lblValorCartaAbajo = new JLabel(getCarta().getValorTexto());
		lblValorCartaAbajo.setFont(new Font("Tahoma", Font.BOLD, TAMAÑO_FUENTE));
		lblValorCartaAbajo.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lblValorCartaAbajo, BorderLayout.SOUTH);;
		
        //Muestra como imagen en palo de la carta, buscando la url relativa a la imagen.
		JLabel lblValorCartaTipo = new JLabel();
        lblValorCartaTipo.setIcon(new ImageIcon(redimensionarImagen("/ar/edu/unlu/corazones/img/" + getCarta().getPalo().toString() + ".png", 20, 40)));
		lblValorCartaTipo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblValorCartaTipo, BorderLayout.CENTER);
        
        revalidate();
        repaint();
        
        //setPreferredSize(new Dimension(60, 90)); // Tamaño de la carta

	}
	
	
    private Image redimensionarImagen(String ruta, int ancho, int alto) {
        try {
            BufferedImage imagenOriginal = ImageIO.read(VistaCarta.class.getResource(ruta));
            Image imagenRedimensionada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return imagenRedimensionada;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

	// *************************************************************
	//                       GETTERS y SETTERS
	// *************************************************************
	
	public Carta getCarta() {
		return carta;
	}


	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	
	

}
