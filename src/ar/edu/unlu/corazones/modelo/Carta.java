package ar.edu.unlu.corazones.modelo;

public class Carta {
	
	// *************************************************************
	//                        ATRIBUTOS
	// *************************************************************
	
	//Valor del palo que tiene la carta
	private Palo palo;
	
	//Valor (1 a 13) que tiene la carta
	private int valor;
	
	//Valor textual de la carta (en el caso de que sea J,Q,K Y A)
	private String valorTexto;
	
	// *************************************************************
	//                       CONSTRUCTOR
	// *************************************************************
	
	public Carta(Palo palo, int valor) {
		this.palo = palo;
		this.valor = valor;
		valorCarta();
	}
	
	// *************************************************************
	//                       COMPORTAMIENTO
	// *************************************************************
	
	//Metodo que muestra la carta en forma de string
	public String mostrarCarta() {
			return valorTexto + " - " + palo.toString();
	}
	
	//Obtener el valor real de la carta (si son letras o no)
	private void valorCarta() {
		switch (this.valor) {
		case 11:
			this.valorTexto = "J";
			break;
		case 12:
			this.valorTexto = "Q";
			break;
		case 13:
			this.valorTexto = "K";
			break;
		case 1:
			this.valorTexto = "A";
			this.valor = 14; //Paso a 14 porque es la mas grande
			break;
		default:
			this.valorTexto = String.valueOf(this.valor);
			break;
		}
	}
	
	// *************************************************************
	//                      	GETTERS
	// *************************************************************
	
	//Getter para obtener el valor del palo de la carta
	public Palo getPalo() {
		return palo;
	}

	//Getter para obtener el valor de la carta
	public int getValor() {
		return valor;
	}
	
	//Getter para obtener el valor (en letra si es el caso) de la carta
	public String getValorTexto() {
		return valorTexto;
	}
}

