package logicadenogocios;

import java.util.ArrayList;

import util.ConversionAscii;

public abstract class SustitucionClave implements Cifrado {
	
	protected ArrayList<Character> alfabeto = asignarAlfabeto();
	
	private ArrayList<Character> asignarAlfabeto(){
		
		ArrayList<Character> alfabeto = new ArrayList<Character>();
		
		alfabeto.add(' ');
		
		int contador = 65;
		while(contador < 91) {
			
			Character letra = ConversionAscii.AsciiLetra(contador);
			alfabeto.add(letra);
			contador++;
		}
		
		return alfabeto;
	}

}
