package ar.edu.unlu.corazones.vista;

import ar.edu.unlu.corazones.controlador.Controlador;

public interface IVista {

	//Menu principal del programa
	void mostrarMenu();

	//Iniciar
	void iniciar();

	void pedirCarta();

	void mostrarGanadorJugada();

	void jugador2deTrebol();

	void pasajeDeCartas();

	void pedirCartaPasaje();

	void corazonesRotos();

	void cartaTiradaIncorrecta();

	void cartaTiradaIncorrectaCorazones();

	void ganadorJuego();

	void finDeRonda();

	void finPasajeDeCartas();

	//Metodo que setea el controlador
	void setControlador(Controlador controlador);

}