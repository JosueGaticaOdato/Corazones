package ar.edu.unlu.corazones.controlador;

import ar.edu.unlu.corazones.modelo.Corazones;
import ar.edu.unlu.corazones.modelo.EventosCorazones;
import ar.edu.unlu.corazones.observer.Observable;
import ar.edu.unlu.corazones.observer.Observador;
import ar.edu.unlu.corazones.vista.VistaConsola;

/**
 *	CONTROLADOR
 *	.Encargado de la comunicacion entre la vista y el juego
 */

public class Controlador implements Observador{
	
	private Corazones modelo;
	
	private VistaConsola vista;
	
	// *************************************************************
	//                       CONSTRUCTOR
	// *************************************************************
	
	public Controlador(Corazones modelo, VistaConsola vista) {
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
		this.modelo.agregarObservador(this);
	}
	
	// *************************************************************
	//                       COMPORTAMIENTO
	//                    DESDE LA VISTA AL MODELO
	// *************************************************************

	public void comenzarJuego() {
		this.modelo.iniciarJuego();
	}
	
	public String mostrarCartasPosiblesATirar() {
		return this.modelo.cartasPosiblesAJugar();
	}

	public String jugadorActual() {
		return this.modelo.getJugadorActual();
	}

	public void cartaJugada(int i) {
		this.modelo.jugarCarta(i);
	}

	public String cartasEnMesa() {
		return this.modelo.getCartasEnMesa();
	}

	public String perdedorJugada() {
		return this.modelo.getPerdedorJugada();
	}

	public String numeroJugada() {
		return this.modelo.getNumeroJugada();
	}
	
	public String puntajesJugadores() {
		return this.modelo.getPuntajes();
	}

	public String ganadorJuego() {
		return this.modelo.getGanadorJuego();
	}

	public String numeroRonda() {
		return this.modelo.getRonda();
	}	
	
	// *************************************************************
	//                    DESDE MODELO A VISTA
	// *************************************************************
	
	@Override
	public void actualizar(Object evento, Observable observado) {
		// TODO Auto-generated method stub
		if (evento instanceof EventosCorazones) {
			switch ((EventosCorazones) evento) {
			case PEDIR_CARTA:
				this.vista.pedirCarta();
				break;
			case GANADOR_JUGADA:
				this.vista.mostrarGanadorJugada();
				break;
			case FIN_DE_JUEGO:
				this.vista.ganadorJuego();
				break;
		}}
		
	}


}
