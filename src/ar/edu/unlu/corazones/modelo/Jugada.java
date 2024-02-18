package ar.edu.unlu.corazones.modelo;

public class Jugada {
	
	// *************************************************************
	//                        CONSTANTES
	// *************************************************************
	
	//Constantes del valor de las cartas
	private final int DAMADEPICAS = 13;
	private final int CORAZONES = 1;
	private final int OTRAS = 0;
	
	private static int contadorJugadas = 0; //Variables estatica que cuenta las jugadas
	
	//Numero de ronda
	private int numeroJugada;
	
	//Cartas que puede haber en mesa
	public final int cartasEnMesa = 4;
	
	// *************************************************************
	//                        ATRIBUTOS
	// *************************************************************

	//Vector aparedeado para determinar el jugador con la carta que tiro
	private Jugador[] jugadores; 
	private Carta[] cartasJugadas;
	
	private Carta primeraCartaJugada;
	
	private Jugador jugadorPerdedor;
	
	// *************************************************************
	//                       CONSTRUCTOR
	// *************************************************************
	
	public Jugada(Jugador[] jugadores) {
		this.jugadores = jugadores;
		cartasJugadas = new Carta[cartasEnMesa];
		contadorJugadas++;
		this.numeroJugada = contadorJugadas;
	}
	
	// *************************************************************
	//                       COMPORTAMIENTO
	// *************************************************************
	
	//Metodo que realiza la tirada de la carta por parte del jugador a la mesa
	public void tirarCartaEnMesa(int turnoProx, Carta cartaEnJuego) {
		if(primeraCarta()) {
			primeraCartaJugada = cartaEnJuego;
		}
		
		this.cartasJugadas[turnoProx] = cartaEnJuego;
	}
	
	//Metodo que determina si es la primera carta jugada: Es fundamental ya que el palo de esta determinada cual es la carta mas alta
	private boolean primeraCarta() {
		boolean vacio = true;
		int pos = 0;
		while (vacio && pos < cartasJugadas.length) { //Busco en la mesa si hay alguna carta jugada
			if (cartasJugadas[pos] != null){
				vacio = false;
			}
			pos++;
		}
		return vacio;
	}
	
	//Metodo que determina el ganador de esta jugada
	public int determinarPerdedor() {
		//Obtengo palo y valor de la primer carta jugada
		Palo paloMejorCarta = primeraCartaJugada.getPalo();
		int valorMejorCarta = primeraCartaJugada.getValor();
		
		int posicionPerdedor = 0;
		
		//Recorro las 4 cartas en mesa y comparto
		for (int cartaJugada = 0; cartaJugada < cartasEnMesa; cartaJugada++) {
			
			//Si hay una carta del mismo palo que sobrepase a la mejor que esta en mesa, entonces esa es la mejor
			if (cartasJugadas[cartaJugada].getPalo() == paloMejorCarta
					&& cartasJugadas[cartaJugada].getValor() >= valorMejorCarta) {
				paloMejorCarta = cartasJugadas[cartaJugada].getPalo();
				valorMejorCarta = cartasJugadas[cartaJugada].getValor();
				posicionPerdedor = cartaJugada;
			}
		}
		contarPuntos(posicionPerdedor); //Agrego los puntos al perdedor
		jugadorPerdedor = jugadores[posicionPerdedor]; //Retorno posicion del ganador
		return posicionPerdedor;
	}
	
	//Metodo que le agrega los puntos al jugador perdedor
	private void contarPuntos(int posPerdedorJugadas) {
		for (Carta cartaConseguida : cartasJugadas) {
			jugadores[posPerdedorJugadas].agregarPuntaje(puntajeCarta(cartaConseguida));
		}
	}
	
	//Metodo privado que determina el puntaje en el juego Corazones
	private int puntajeCarta(Carta carta) {
		int puntaje;
		//Si la carta es de corazones, entonces vale 1 punto
		if (carta.getPalo() == Palo.CORAZONES){
			puntaje = CORAZONES;
		//En cambio, si es la dama de picas, vale 13 puntos
		} else if (carta.getPalo() == Palo.PICAS && carta.getValor() == 12) {
			puntaje = DAMADEPICAS;
		//Sino, la carta no vale nada en este juego
		} else {
			puntaje = OTRAS;
		}
		return puntaje;
	}
	
    // Método para reiniciar el contador de jugadas
    public static void reiniciarContadorJugadas() {
        contadorJugadas = 0;
    }
    
	// *************************************************************
	//                       GETTERS
	// *************************************************************

    //Metodo que muestra las cartas jugadas en mesa
	public String getCartasJugadas() {
		String s = "\n";
		for (int i = 0; i < cartasJugadas.length; i++) {
			if (cartasJugadas[i] != null) {
				s += "Jugador " + (i + 1) + " -> " + cartasJugadas[i].mostrarCarta() + "\n";
			} else {
				s += "Jugador " + (i + 1) + " -> Ninguna" + "\n" ;
			}
		}
		return s;
	}
	
	//Metodo que me dice el numero de jugada (determinante porque si es la primera comienza distinto)
	public String getNumeroJugada() {
		return String.valueOf(this.numeroJugada);
	}

	//Metodo que me dice el nombre del jugador perdedor
	public String getPerdedorJugada() {
		return this.jugadorPerdedor.getNombre();
	}
	
	
}
