package ar.edu.unlu.corazones.modelo;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlu.corazones.observer.Observable;
import ar.edu.unlu.corazones.observer.Observador;

public class Corazones implements Observable{
	
	// *************************************************************
	//                        CONSTANTES
	// *************************************************************
	
	private static final int cantCartasRepartidas = 2; //TESTING
	private static final int cantCartasIntercambio = 1; //TESTING
	private static final int puntajeMaximo = 50; //TESTING
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
			//pasajeDeCartas();
			for (int j = 0; j < cantCartasRepartidas; j++) {
				int i = 0;
				Jugada jugada = new Jugada(this.jugadores);
				jugadas.add(jugada);
				while (i < jugadores.length) {
					notificar(EventosCorazones.PEDIR_CARTA);
					jugada.tirarCartaEnMesa(turno, cartaAJugar);
					turno = (turno + 1) % jugadores.length; //Obtengo el siguiente jugador	
					i++;
				}
				jugada.determinarPerdedor();
				notificar(EventosCorazones.GANADOR_JUGADA);
			}
			if (puntajeMaximoActual() >= puntajeMaximo) {
				juegoTerminado = true; 
			}
			Jugada.reiniciarContadorJugadas();
			ronda++;
		}
		determinarGanador();
		notificar(EventosCorazones.FIN_DE_JUEGO);
	}
	
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
	
	//Metodo que me dice las cartas que puede jugar el jugador
	public String cartasPosiblesAJugar() {
		return jugadores[turno].mostrarMano();
	}
	
	//Metodo para guardar la carta que va a ser jugada en mesa
	public void jugarCarta(int i) {
		cartaAJugar = jugadores[turno].tirarCarta(i);
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

