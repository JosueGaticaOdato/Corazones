package ar.edu.unlu.corazones.modelo;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlu.corazones.observer.Observable;
import ar.edu.unlu.corazones.observer.Observador;

public class Corazones implements Observable{
	
	// *************************************************************
	//                        CONSTANTES
	// *************************************************************
	
	private static final int cantCartasRepartidas = 13; //TESTING
	private static final int cantCartasIntercambio = 1; //TESTING
	private static final int puntajeMaximo = 12; //TESTING
	private static final int cantJugadores = 4;
	
	// *************************************************************
	//                        ATRIBUTOS
	// *************************************************************
	
	//Mazo del juego
	private Mazo mazo;
	
	//Array donde estaran los jugadores
	private Jugador[] jugadores;
	
	//Numero de ronda
	private int ronda;
	
	//Jugadas (En una ronda hay 13 jugadas)
	private List<Jugada> jugadas;
	
	//Turno del jugador actual
	private int turno = 0; 
	
	//Direccion del pasaje de cartas
	private Direccion direccion;
	
	//Atributo para guardar la carta a tirar por parte del jugador
	private Carta cartaAJugar;
	
	//Posicion del jugador ganador
	private int posJugadorGanador;
	
	//Lista de observadores
	private List<Observador> observadores;
		
	// *************************************************************
	//                       CONSTRUCTOR
	// *************************************************************
	
	//Constructor de la clase corazones
	public Corazones() {
		//Creo la instancia de los jugadores
		jugadores = new Jugador[cantJugadores];	
		ronda = 1;
		//Cargo default
		agregarJugadores("a");
		agregarJugadores("b");
		agregarJugadores("c");
		agregarJugadores("d");
		
		this.observadores = new ArrayList<>();
		this.jugadas = new ArrayList<>();
	}
	
	// *************************************************************
	//                       COMPORTAMIENTO
	// ************************************************************
	
	// *************************************************************
	//                     	 INICIO DE JUEGO:
	// .Tiene que dar por iniciado el juego y terminar una vez que se supere la cantidad maxima de puntos
	// .Tiene que crear las jugadas y llevarlas a cabo
	// .Tiene que mostrar al jugador ganador
	// **************************************************************

	public void iniciarJuego() {
		boolean juegoTerminado = false;
		while (!juegoTerminado) {
			mazo = new Mazo();
			repartirCartas();
			pasajeDeCartas();
			for (int j = 0; j < cantCartasRepartidas; j++) { //Abarco las 13 jugadas de esta ronda
				int i = 0;
				Jugada jugada = new Jugada(this.jugadores);
				jugadas.add(jugada);
				if (j == 0) { //Primer jugada, donde se inicia con el 2 de trebol
					primercarta2Trebol(jugada);
					i++; //Aumento i porque ya jugo uno de los jugadores
				}
				while (i < jugadores.length) { //Recorro todos los jugadores
					notificar(EventosCorazones.PEDIR_CARTA);
					jugada.tirarCartaEnMesa(turno, cartaAJugar);
					turno = (turno + 1) % jugadores.length; //Obtengo el siguiente jugador	
					i++;
				}
				//Determino el perdedor de esta jugada, obteniendo el siguiente a jugar
				turno = jugada.determinarPerdedor();
				notificar(EventosCorazones.GANADOR_JUGADA);
			}
			//Finalizada la ronda, se comprueba si se llego al puntaje maxio para finalizar el juego
			if (puntajeMaximoActual() >= puntajeMaximo) {
				juegoTerminado = true; 
			}
			Jugada.reiniciarContadorJugadas();
			ronda++;
		}
		//Fin del juego, determino al ganador
		determinarGanador();
		notificar(EventosCorazones.FIN_DE_JUEGO);
	}
	
	//Obtener puntaje maximo de jugadores (para comprobar si supero el max establecido)
	private int puntajeMaximoActual() {
		int max = 0;
		for (Jugador jugadoresCorazones : jugadores) {
			if (max <= jugadoresCorazones.getPuntaje()) {
				max = jugadoresCorazones.getPuntaje();
			}
		}
		return max;
	}
	
	//Metodo que determina quien gano el juego
	private void determinarGanador() {
		int minPuntaje = 100;
		for (int i = 0; i < cantJugadores; i++) {
			if (minPuntaje >= jugadores[i].getPuntaje()) {
				minPuntaje = jugadores[i].getPuntaje();
				this.posJugadorGanador = i;
			}
		}
	}
	
	// *************************************************************
	//                 FUNCIONALIDAD JUGADAS
	// *************************************************************	
	
	//Metodo para que el jugador que tiene el 2 de trebol tire la carta
	private void primercarta2Trebol(Jugada jugada) {
    	boolean encontrado = false;
    	int pos = 0;
    	while (!encontrado && pos < jugadores.length) {
    		int dosTrebol = jugadores[pos].tieneDosDeTrebol();
    		if (dosTrebol != -1) {
    			encontrado = true;
    			turno = pos;
    			jugada.tirarCartaEnMesa(turno, jugadores[pos].tirarCarta(dosTrebol)); //Tira el 2 de trebol
    			notificar(EventosCorazones.JUGO_2_DE_TREBOL);
    			turno = (turno + 1) % jugadores.length;;
    		} else {
    			pos++;
    		}
    	}
	}
	
	//Metodo que me dice las cartas que puede jugar el jugador
	public String cartasPosiblesAJugar() {
		return jugadores[turno].mostrarMano();
	}
	
	//Metodo para guardar la carta que va a ser jugada en mesa
	public void jugarCarta(int i) {
		cartaAJugar = jugadores[turno].tirarCarta(i);
	}
	
	// *************************************************************
	//                  FUNCIONALIDAD RONDA
	// *************************************************************
	
	//Metodo que reparte las cartas a cada jugador, como se hace de forma habitual
	//1 1 1 1, 2 2 2 2, 3 3 3 3, etc.
	private void repartirCartas() {
		for (int i = 0; i < cantCartasRepartidas; i++){
			for(Jugador jugador: jugadores) {
				jugador.recibirCarta(mazo.sacarCarta());
			}
		}
	}
	
	//Metodo que agrega jugadores al juego,segun lo que devuelva indica si se cargo de forma correcta o no el jugador
	//true = se cargo - false = no se cargo porque ya estan todos los jugadores
	private boolean agregarJugadores(String nombre) {
		boolean hayEspacio = false;
		int pos = 0;
		while(!hayEspacio && pos < jugadores.length) {
			if (jugadores[pos] == null) {
				jugadores[pos] = new Jugador(nombre,pos);
				hayEspacio = true;
			}
			else {
				pos++;
			}
		}
		return hayEspacio;
	}
	
	// *************************************************************
	//                  PASAJE DE CARTAS
	// *************************************************************
	
	//Metodo que determina a donde se van a pasar las cartas
	private void pasajeDeCartas() {
		int variablePasaje = 0;
		
		// *************************************************************
		//                     	 CASOS
		// 1. Pasaje de 1: Se realiza el pasaje a la izquierda -> variablePasaje = 1
		// 2. Pasaje de 2: Se realiza el pasaje al frente -> variablePasaje = 0
		// 3. Pasaje de 3: Se realiza el pasaje a la derecha -> variablePasaje = -1 
		// 4. Pasaje de 0: No hay pasaje
		// **************************************************************
		
		//Determino a donde se van a pasar las cartas
        switch (this.ronda) {
            case 1: //Izq
            	variablePasaje = 1;
            	direccion = Direccion.Izquierda;
                break;
            case 2: //Frente
            	variablePasaje = 2;
            	direccion = Direccion.Frente;
                break;
            case 3: //Der
            	variablePasaje = 3;
            	direccion = Direccion.Derecha;
                break;
            // Caso 4: No hay intercambio
            default:
                break;
        } 
        notificar(EventosCorazones.PASAJE_DE_CARTAS);
        if (variablePasaje != 0) {
        	intercambioDeCartas(variablePasaje);
        }
	}
	
	//Metodo privado que realiza el intercambio
	private void intercambioDeCartas(int valor) {
		//Esto funciona para que el intercambio se haga sobre el final y los otros jugadores no tengan acceso a las cartas nuevas recibidas
		ArrayList<Carta[]> arregloDeCartasAIntercambiar = new ArrayList<Carta[]>(cantJugadores);
		
		// Inicializar cada posición del ArrayList con un arreglo de Carta vacío
		for (int i = 0; i < cantJugadores; i++) {
		    arregloDeCartasAIntercambiar.add(new Carta[0]);
		}
		
		//Recorro todos los jugadores
		for (Jugador jugadorPasaje: jugadores) {
			
			//Obtengo la posicion del jugaodr actual y a quien le va a pasar las cartas
			int posicionJugadorActual = buscarJugador(jugadorPasaje);
			//Para saber a quien le paso las cartas, tengo que sumar la variable de pasaje a la posicion del jugador actual, y a eso dividirlo por la cantidad de jugadores
			int posicionJugadorPasaje = (posicionJugadorActual + valor + jugadores.length) % jugadores.length;
		
			//Creo la lista de las cartas que se van a pasar al otro jugador
			Carta[] cartasIntercambio = new Carta[cantCartasIntercambio];
			
			for (int i = 0; i < cantCartasIntercambio; i++) {
				//Obntego la carta que jugo el jugador
				notificar(EventosCorazones.PEDIR_CARTA_PASAJE);
				cartasIntercambio[i] = this.cartaAJugar;
			}
			
			//Guardo en el arreglo (POR POSICION DE JUGADOR) las cartas nuevas obtenidas
			arregloDeCartasAIntercambiar.set(posicionJugadorPasaje, cartasIntercambio);
			
			turno = (turno + 1) % jugadores.length; //Obtengo el siguiente jugador	
		}
		
		otorgarCartasJugadores(arregloDeCartasAIntercambiar);
	}
	
	//Metodo que busca la posicion de un jugador determinado
	private int buscarJugador(Jugador jugador) {
		int posicionJugadorBuscar = 0;
		boolean encontrado = false;
		while (!encontrado && posicionJugadorBuscar < jugadores.length){
	        if (jugadores[posicionJugadorBuscar] == jugador) {
	            encontrado = true;
	        } else {
	        	posicionJugadorBuscar++;
	        }
	    }
		return posicionJugadorBuscar;
	}
	
	//Otorgo las cartas que se pasaron a cada jugador
	private void otorgarCartasJugadores(ArrayList<Carta[]> cartasPasaje) {
		for (int i = 0; i < cantJugadores; i++) {
			for(int j = 0; j < cantCartasIntercambio; j++) {
				jugadores[i].recibirCarta(cartasPasaje.get(i)[j]);
			}
		}
	}

	
	// *************************************************************
	//                      	GETTERS
	// *************************************************************	

	//Muestra el jugador de turno
	public String getJugadorActual() {
		return jugadores[turno].getNombre();
	}
	
	//Muestra las cartas que hay en mesa
	public String getCartasEnMesa() {
		return this.jugadas.get(jugadas.size() - 1).getCartasJugadas();
	}
	
	//Muestra el jugador perdedor de la jugada
	public String getPerdedorJugada() {
		return this.jugadas.get(jugadas.size() - 1).getPerdedorJugada();
	}
	
	//Muestra el numero de jugada
	public String getNumeroJugada() {
		return this.jugadas.get(jugadas.size() - 1).getNumeroJugada();
	}

	//Metodo que muestra los puntajes actuales
	public String getPuntajes() {
		String s = "---------- PUNTAJES -------------" + "\n";
		for (int i = 0; i < cantJugadores; i++) {
			s += "Jugador " + (i + 1) + " -> " + jugadores[i].getPuntaje() + "\n";
		}
		return s;
	}
	
	//Metodo que me devuelve al jugador ganador
	public String getGanadorJuego() {
		return jugadores[this.posJugadorGanador].getNombre();
	}

	//Metodo que me devuelve el numero de ronda
	public String getRonda() {
		return String.valueOf(this.ronda);
	}
	
	//Getter que me dice direccion y cuantas cartas se van a pasar
	public String getDireccionPasaje() {
		String s = "No hay pasaje de cartas";
		if (this.direccion != null) {
			s = "Las cartas se pasan en la siguiente direccion: " + this.direccion.toString() + "\n";
			s += "Cantidad de cartas a pasar: " + this.getCantCartasPasaje();
		}
		return s;
		
	}
	
	public String getCantCartasPasaje() {
		return String.valueOf(cantCartasIntercambio);
	}

	
	// *************************************************************
	//                      	OBSERVER
	// *************************************************************	

	@Override //Notificar los eventos
	public void notificar(Object evento) {
		for (Observador observador : this.observadores) {
			observador.actualizar(evento, this);
		}
	}

	@Override //Agregar observadores
	public void agregarObservador(Observador observador) {
		this.observadores.add(observador);
	}


}

