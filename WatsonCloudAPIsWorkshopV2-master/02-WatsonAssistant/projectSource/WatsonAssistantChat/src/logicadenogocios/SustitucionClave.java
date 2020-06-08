package logicadenogocios;

import java.util.ArrayList;

public abstract class SustitucionClave implements Cifrado {
	
	private ArrayList<String> alfabeto;
	private ArrayList<String> alfabetoCifrado;
	
	public abstract void asignarValoresAlfabeto();

}
