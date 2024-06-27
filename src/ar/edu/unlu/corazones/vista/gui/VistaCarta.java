package ar.edu.unlu.corazones.vista.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ar.edu.unlu.corazones.modelo.Carta;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class VistaCarta extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Carta carta;
	
	public VistaCarta(Carta carta) {
		setBackground(new Color(255, 255, 255));
		this.carta = carta;
		setLayout(null);
		
		JLabel lblNumero1 = new JLabel("test1");
		lblNumero1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNumero1.setText(getCarta().getValorTexto());
		lblNumero1.setBounds(4, 13, 1, 1);
		add(lblNumero1);
		
		JLabel lblNumero2 = new JLabel("test2");
		lblNumero2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNumero2.setText(getCarta().getValorTexto());
		lblNumero2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumero2.setBounds(10, 259, 182, 42);
		add(lblNumero2);
		
		JLabel lblValor = new JLabel();
		lblValor.setIcon(new ImageIcon(VistaCarta.class.getResource("/ar/edu/unlu/corazones/img/" + carta.getPalo().toString() + ".png")));
		//lblValor.setText(getCarta().getValorTexto());
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setBounds(18, 61, 174, 187);
		add(lblValor);
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

}
