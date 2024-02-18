package ar.edu.unlu.corazones.vista;

import ar.edu.unlu.corazones.controlador.Controlador;

public interface IVista {
	
	public void iniciar();

	void setControlador(Controlador controlador);
}
