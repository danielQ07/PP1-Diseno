package logicadenogocios;

import java.util.ArrayList;

public class SustitucionCesar implements Cifrado {
	
	
	private char AsciiLetra(int numero) {
		return (char)numero;
	}
	
	
	private int LetraAscii(Character letra) {
		letra = Character.toUpperCase(letra);
		return (int)letra;
	}


	public Mensaje cifrar(Mensaje pMensaje,int cantidadPosiciones) {
		
		char[] mensajeSeparado = pMensaje.getMensajeViejo().toCharArray();
		String mensajeCifrado = "";

		
		for(char letra: mensajeSeparado) {
			
			int posicionLetraAscii = 0;
			
			if(LetraAscii(letra)+cantidadPosiciones > 90){
				posicionLetraAscii = LetraAscii(letra)+cantidadPosiciones - 90;
			}else {
				posicionLetraAscii = LetraAscii(letra)+cantidadPosiciones;
			}
			
			char letraCifrada = AsciiLetra(posicionLetraAscii);
			mensajeCifrado += Character.toString(letraCifrada);
		}
		
		pMensaje.setMensajeCifrado(mensajeCifrado);
		
		return pMensaje;
		
	}


	@Override
	public Mensaje descifrar(Mensaje pMensaje) {
		return null;
	}


	@Override
	public Mensaje cifrar(Mensaje pMensaje) {
		return null;
	}

}
