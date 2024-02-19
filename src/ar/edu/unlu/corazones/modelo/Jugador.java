package ar.edu.unlu.corazones.modelo;

import java.util.ArrayList;

/**
 * CLASE JUGADOR
 * Se encarga de realizar todos los movimientos dentro del juego
 * Es el que impulsa al juego y sus decisiones afectan a su funcionamiento
 */

public class Jugador {

	// *************************************************************
	//                        ATRIBUTOS
	// *************************************************************
	
	//Nombre del jugador
	private String nombre;
	
	//Puntaje del jugador
	private int puntaje;
	
	//Mano del jugador
	private ArrayList<Carta> mano;
	
	// *************************************************************
	//                       CONSTRUCTOR
	// *************************************************************
	public Jugador(String nombre, int posicion) {
		this.nombre = nombre; //Determino el nombre del jugador
		this.puntaje = 0; //Seteo los puntos en 0
		mano = new ArrayList<Carta>();
	}

	// *************************************************************
	//                       COMPORTAMIENTO
	// *************************************************************
	//Metodo que le da las cartas al jugador y las agrega a su mano
	public void recibirCarta(Carta carta) {
		mano.add(carta);
	}

	//Metodo que muestra la mano del jugador
	public String mostrarMano() {
		String s = "";
		for (int i = 0; i < mano.size() ; i++){
			s += (i+1) + ") " + mano.get(i).mostrarCarta() + "\n";
		 }
		return s;
	}
	
	//Tirar carta a traves de la posicion
	public Carta tirarCarta(int posCarta) {
		Carta cartaTirada = obtenerCarta(posCarta);
		mano.remove(posCarta);
		return cartaTirada;
	}
	
	// *************************************************************
	//                 FUNCION EN CORAZONES
	// *************************************************************
	
	//Comprueba si el jugador tiene el 2 de trebol en su poder
    public int tieneDosDeTrebol() {
    	boolean loTiene = false;
    	int pos = 0;
    	while (!loTiene && pos < mano.size()) {
    		if (mano.get(pos).getPalo() == Palo.TREBOL && mano.get(pos).getValor() == 2)  {
            	loTiene = true;
    		} else {
    			pos++;
    		}
    	}
    	if (!loTiene) {
    		pos = -1;
    	}
        return pos;
    }
    
    //Metodo que me dice cuales cartas puede tirar el jugador en la mesa
    public String cartasJugables(Carta primeraCarta) {
		String s = "";
		if (primeraCarta == null) {
			s = mostrarMano();
		} else {
		for (int i = 0; i < mano.size() ; i++){
			Carta carta = mano.get(i);
			s += (i+1) + ") " + carta.mostrarCarta();
			if (carta.getPalo() != primeraCarta.getPalo()) {
				s += " X";
			}
			s += "\n";
		 }
		}
		return s;
    }

	
	// *************************************************************
	//                      SETTERS
	// *************************************************************
	
	//Set nombre
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	// *************************************************************
	//                      GETTERS
	// *************************************************************
	
	//Obtener carta a traves de la posicion
	public Carta obtenerCarta(int posCarta) {
		return mano.get(posCarta);
	}
	
	//Getter del nombre del jugador
	public String getNombre() {
		return nombre;
	}
	
	//Metodo que muestra las cartas en mano del jugador
	public int cartasEnMano() {
		return mano.size();
	}
	
	//Getter que obtiene el puntaje del jugador
	public int getPuntaje() {
		return this.puntaje;
	}

	public void agregarPuntaje(int puntajeCarta) {
		// TODO Auto-generated method stub
		this.puntaje += puntajeCarta;
	}
	
}
