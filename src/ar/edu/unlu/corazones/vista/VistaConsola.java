package ar.edu.unlu.corazones.vista;

import java.util.Scanner;

import ar.edu.unlu.corazones.controlador.Controlador;

/**
 * VISTA CONSOLA
 * Esta se encargara de mostrarle al usuario toda la funcionalidad del sistema
 *
 */

public class VistaConsola implements IVista {

	// *************************************************************
	//                       CONSTANTES
	// *************************************************************
	
	private final int lineas = 50;
	
	// *************************************************************
	//                       ATRIBUTOS
	// *************************************************************
	
	private Scanner entrada;
	
	//Pongo como atributo el controlador, que se comunicara con el modelo
	private Controlador controlador;
	
	// *************************************************************
	//                       CONSTRUCTOR
	// *************************************************************
	
	//Creo la instancia para que el usuario pueda ingresar los datos
	public VistaConsola() {
		this.entrada = new Scanner(System.in);
	}
	
	// *************************************************************
	//                       COMPORTAMIENTO
	// *************************************************************

	//Menu principal del programa
	@Override
	public void mostrarMenu() {
		System.out.println("****************************");
		System.out.println("*    	 CORAZONES         *");
		System.out.println("****************************");
		System.out.println();
		System.out.println("Seleccione una opcion:");
		System.out.println("----------------------");
		System.out.println("1 - Crear jugador");
		System.out.println("2 - Modificar jugador");
		System.out.println("3 - Ver lista de jugadores");
		System.out.println("4 - Comenzar juego");
		System.out.println("----------------------");
		System.out.println();
		System.out.println("0 - Salir");
		System.out.print("Opcion: ");
	}
	
	//Iniciar
	@Override
	public void iniciar(){
		boolean salir = false;
		while(!salir) {
			limpiarPantalla();
			mostrarMenu();
			int opcion = this.entrada.nextInt();
			limpiarPantalla();
			switch (opcion) {
				case 1: //Crear jugador
					nuevoJugador();
					break;
				case 2: //Modificar jugador por posicion
					modificarJugador();
					break;
				case 3: //Mostrar lista de jugadores 
					listaJugadores();
					break;
				case 4: //Comenzar juego
					jugar();
					break;
				case 0: //Salir del juego
					salir = true;
					System.out.println("Gracias por jugar!!");
					break;
				default: //Opcion por default
					System.out.println("Opcion no valida.");
			}
			continuar();
		}
	}

	//Metodo para continuar y no sacar la pantalla de una
	private void continuar() {
		System.out.println("Escriba cualquier tecla para continuar...");
		this.entrada.next();
		limpiarPantalla();
	}
	
	//Limpieza de pantalla (en realidad agrega lineas)
	private void limpiarPantalla()
	{
	 for (int i=0; i < this.lineas; i++)
	 {
	  System.out.println();
	 }
	}
	
	// *************************************************************
	//                         ALTA
	// *************************************************************
	
	private void nuevoJugador() {
		if (!this.controlador.cantidadJugadoresValida()) {
			System.out.println("\n" + "---------- NUEVO JUGADOR! -------------" + "\n");
			System.out.print("Ingrese el nombre del nuevo jugador: ");
			String nombre = entrada.next();
			this.controlador.agregarJugador(nombre);
		} else {
			System.out.println("\n" + "Ya estan todos los jugadores inscriptos" + "\n");
		}
	}
	
	// *************************************************************
	//                     MODIFICACION
	// *************************************************************

	private void modificarJugador() {
		listaJugadores();
		System.out.print("Por favor, ingrese el numero de jugador que quiere modificar: ");
		int pos = entrada.nextInt();
		System.out.print("Por favor, ingrese el numero del nuevo jugador: ");
		String nombre = entrada.next();
		boolean creado = controlador.modificarJugador(nombre, pos);
		if (creado) {
			//modificar jugador devuelve verdadero si se pudo modificar
			System.out.println("Jugador modificado con exito!");
		} else {
			//devuelve falso en el caso de que sea lo contrario
			System.out.println("No se pudo modificar el jugador.");
		}
		
	}

	// *************************************************************
	//                 LISTA DE JUGADORES
	// *************************************************************

	private void listaJugadores() {
		System.out.println("\n" + "Lista de jugadores:");
		System.out.println(controlador.listaJugadores());
	}
	
	// *************************************************************
	//                         JUGAR
	// *************************************************************
	
	private void jugar() {
		if (this.controlador.cantidadJugadoresValida()) {
			System.out.println("Juego comenzado!");
			continuar();
			controlador.comenzarJuego();
		} else {
			System.out.println("Faltan jugadores para comenzar el juego");
		}		
	}
	
	private void combinacionRondaJugada() {
		System.out.println("****************************");
		System.out.println("*         RONDA #" + this.controlador.numeroRonda() + "         *");
		System.out.println("*    	  JUGADA #" + this.controlador.numeroJugada() + "        *");
		System.out.println("****************************");
	}
	
	private void combinacionRondaPasaje() {
		System.out.println("****************************");
	    System.out.println("*         RONDA #" + this.controlador.numeroRonda() + "         *");
		System.out.println("*     PASAJE DE CARTAS     *");
		System.out.println("****************************");
	}
	
	// *************************************************************
	//               PEDIR CARTA (para tirar en mesa)
	// *************************************************************

	@Override
	public void pedirCarta() {
		//Mostrar cartas en mesa
		combinacionRondaJugada();
		System.out.println("Es el turno del jugador: "
				+ this.controlador.jugadorActual()); //Digo quien tiene que jugar
		continuar();
		combinacionRondaJugada();
		System.out.println("****************************");
		System.out.println("*    JUGADOR #" + this.controlador.jugadorActual() + "    *");
		System.out.println("*      CARTAS EN MESA      *");
		System.out.println();
		System.out.println(this.controlador.cartasEnMesa());
		System.out.println();
		System.out.println("****************************");
		System.out.println();
		if (this.controlador.isCorazonesRotos()) {
			System.out.println("------------- ¡CORAZONES ROTOS! -------------"  + "\n");}
		System.out.println();
		System.out.println("     	    MANO            ");
		System.out.println("----------------------------");
		System.out.print(this.controlador.mostrarCartasPosiblesATirar());
		System.out.println("----------------------------");
		System.out.println();
		System.out.println("Con (X), las cartas que NO se puede jugar en esta mesa");
		System.out.println();
		System.out.print("Elija una carta; ");
		int posCarta = entrada.nextInt();
		controlador.cartaJugada(posCarta - 1); //Paso la carta
		continuar();
	}
	
	// *************************************************************
	//               MOSTRAR GANADOR DE LA JUGADA
	// *************************************************************
	
	@Override
	public void mostrarGanadorJugada() {
		combinacionRondaJugada();
		System.out.println("*      CARTAS EN MESA      *");
		System.out.println();
		System.out.println(this.controlador.cartasEnMesa());
		System.out.println();
		System.out.println("****************************");
		
		System.out.println("El perdedor de esta jugada es " + this.controlador.perdedorJugada() + "\n");
		System.out.println("*          PUNTAJE         *");
		System.out.println();
		System.out.println(this.controlador.puntajesJugadores());
		System.out.println();
		System.out.println("****************************");
		continuar();
	}
	
	// *************************************************************
	//               MOSTRAR QUIEN TIRO 2 TREBOL
	// *************************************************************

	@Override
	public void jugador2deTrebol() {
		//Mostrar cartas en mesa
		combinacionRondaJugada();
		System.out.println("Es el turno del jugador: "
				+ this.controlador.jugadorActual()); //Digo quien tiene que jugar
		continuar();
		combinacionRondaJugada();
		System.out.println();
		System.out.println("Como el jugador tiene el 2 de trebol, el comienza la jugada");
		System.out.println();
		System.out.println("*      CARTAS EN MESA      *");
		System.out.println();
		System.out.println(this.controlador.cartasEnMesa());
		System.out.println();
		System.out.println("****************************");
		continuar();
	}

	// *************************************************************
	//                    PASAJE DE CARTAS
	// *************************************************************
	
	@Override
	public void pasajeDeCartas() {
		combinacionRondaPasaje();
		System.out.println(this.controlador.direccionPasaje());
		continuar();
	}
	
	// *************************************************************
	//               PEDIR CARTA (para pasaje)
	// *************************************************************

	@Override
	public void pedirCartaPasaje() {
		combinacionRondaPasaje();
		System.out.println("Es el turno del jugador: "
				+ this.controlador.jugadorActual()); //Digo quien tiene que jugar
		continuar();
		combinacionRondaPasaje();
		System.out.println("*                          *");
		System.out.println("*   JUGADOR -  " + this.controlador.jugadorActual() + "   *");
		System.out.println("*                          *");
		System.out.println("****************************");
		System.out.println();
		System.out.println("     	    MANO            ");
		System.out.println("----------------------------");
		System.out.print(this.controlador.mostrarCartasPosiblesATirarPasaje());
		System.out.println("----------------------------");
		System.out.println();
		System.out.print("Elija una carta; ");
		int posCarta = entrada.nextInt();
		controlador.cartaJugadaPasaje(posCarta - 1); //Paso la carta
		continuar();
	}
	
	// *************************************************************
	//               CORAZONES ROTOS
	// *************************************************************
	
	@Override
	public void corazonesRotos() {
		System.out.println("\n" + "CORAZONES ROTOS");
		System.out.println("A partir de ahora se pueden tirar cartas de cualquier palo" + "\n");
		continuar();
	}
	
	// *************************************************************
	//               CARTA TIRADA INCORRECTA
	// *************************************************************
	
	@Override
	public void cartaTiradaIncorrecta() {
		System.out.println("No puedes tirar esa carta ya que no corresponde con el mismo palo de la misma carta tirada");
		System.out.println("Repite tu jugada...");
		continuar();
	}
	
	@Override
	public void cartaTiradaIncorrectaCorazones() {
		System.out.println("No puedes tirar esa carta ya que aun no estan los corazones rotos.");
		System.out.println("Repite tu jugada...");
		continuar();
	}
	
	// *************************************************************
	//               FIN DE RONDA(PASAJE/JUEGO
	// *************************************************************
	
	@Override
	public void ganadorJuego() {
		System.out.println("****************************");
		System.out.println("*  		FIN DEL JUEGO      *");
		System.out.println("****************************");
		System.out.println(this.controlador.puntajesJugadores() + "\n");
		System.out.println("El ganador fue " + this.controlador.ganadorJuego());
	}
	
	@Override
	public void finDeRonda() { 
		System.out.println("****************************");
		System.out.println("* 	   FIN DE LA RONDA	   *");
		System.out.println("****************************");
		System.out.println("Asi estan los puntajes hasta el momento" + "\n");
		System.out.println(this.controlador.puntajesJugadores() + "\n");
		continuar();
	}

	@Override
	public void finPasajeDeCartas() {
		System.out.println("****************************");
		System.out.println("* FIN DEL PASAJE DE CARTAS *");
		System.out.println("*    COMIENZA LA RONDA     *");
		System.out.println("****************************");
		continuar();
	}
	


	//Metodo que setea el controlador
	@Override
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}


}
