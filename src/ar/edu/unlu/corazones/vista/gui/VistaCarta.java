package ar.edu.unlu.corazones.vista.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ar.edu.unlu.corazones.modelo.Carta;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;

public class VistaCarta extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Carta carta;
	

	/**
	 * Constructor donde se crea la vista de la carta
	 */
	
	public VistaCarta(Carta carta) {
		setBackground(new Color(255, 255, 255));
		this.carta = carta;
		setLayout(new BorderLayout());

		//Toma el numero de la carta
		JLabel lblValorCartaArriba = new JLabel(getCarta().getValorTexto());
		lblValorCartaArriba.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblValorCartaArriba.setHorizontalAlignment(SwingConstants.LEFT);
        add(lblValorCartaArriba, BorderLayout.NORTH);
		
        //Toma el numero de la carta
		JLabel lblValorCartaAbajo = new JLabel(getCarta().getValorTexto());
		lblValorCartaAbajo.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblValorCartaAbajo.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lblValorCartaAbajo, BorderLayout.SOUTH);;
		
        //Muestra como imagen en palo de la carta, buscando la url relativa a la imagen.
		JLabel lblValorCartaTipo = new JLabel();
		lblValorCartaTipo.setIcon(new ImageIcon(VistaCarta.class.getResource("/ar/edu/unlu/corazones/img/"+getCarta().getPalo().toString()+".png")));
		lblValorCartaTipo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblValorCartaTipo, BorderLayout.CENTER);

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
