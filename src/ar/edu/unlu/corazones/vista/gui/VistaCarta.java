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
	 * Create the panel.
	 */
	
	public VistaCarta(Carta carta) {
		setBackground(new Color(255, 255, 255));
		this.carta = carta;
		setLayout(new BorderLayout());

		JLabel lblValorCartaArriba = new JLabel(getCarta().getValorTexto());
		lblValorCartaArriba.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblValorCartaArriba.setHorizontalAlignment(SwingConstants.LEFT);
        add(lblValorCartaArriba, BorderLayout.NORTH);
		
		JLabel lblValorCartaAbajo = new JLabel(getCarta().getValorTexto());
		lblValorCartaAbajo.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblValorCartaAbajo.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lblValorCartaAbajo, BorderLayout.SOUTH);;
		
		JLabel lblValorCartaTipo = new JLabel();
		lblValorCartaTipo.setIcon(new ImageIcon(VistaCarta.class.getResource("/ar/edu/unlu/corazones/img/"+getCarta().getPalo().toString()+".png")));
		lblValorCartaTipo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblValorCartaTipo, BorderLayout.CENTER);

	}


	public Carta getCarta() {
		return carta;
	}


	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	
	

}
