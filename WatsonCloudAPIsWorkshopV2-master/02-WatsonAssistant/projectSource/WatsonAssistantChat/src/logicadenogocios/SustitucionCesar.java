package logicadenogocios;

import java.util.ArrayList;

import util.ConversionAscii;

public class SustitucionCesar implements Cifrado {
	
	
	private int cantidadPosiciones;
	
	public SustitucionCesar() {
		
	}
	
	public SustitucionCesar(int pCantidadPosiciones) {
		this.cantidadPosiciones = pCantidadPosiciones;
	}
	
	
	@Override
	public Mensaje cifrar(Mensaje pMensaje) {
			
		char[] mensajeSeparado = pMensaje.getMensajeViejo().toCharArray();
		String mensajeCifrado = "";

		
		for(char letra: mensajeSeparado) {
			
			if(letra == ' '){
				mensajeCifrado +=" ";
			}else {
			
				int posicionLetraAscii = ConversionAscii.LetraAscii(letra)+cantidadPosiciones;
				
				if(ConversionAscii.LetraAscii(letra)+cantidadPosiciones > 90){
					posicionLetraAscii = ConversionAscii.LetraAscii(letra)+cantidadPosiciones - 26;
				}
				
				char letraCifrada = ConversionAscii.AsciiLetra(posicionLetraAscii);
				
				if(Character.isLowerCase(letra)) {
					mensajeCifrado += Character.toString(letraCifrada).toLowerCase();
				}else {
					mensajeCifrado += Character.toString(letraCifrada);
				}
				
			}
		}
		
		pMensaje.setMensajeCifrado(mensajeCifrado);
		
		return pMensaje;
			
	}


	@Override
	public Mensaje descifrar(Mensaje pMensaje) {
		char[] mensajeSeparado = pMensaje.getMensajeCifrado().toCharArray();
		String mensajeDescifrado = "";

		
		for(char letra: mensajeSeparado) {
			
			if(letra == ' '){
				mensajeDescifrado +=" ";
			}else {
			
				int numeroLetraAscii = ConversionAscii.LetraAscii(letra)-cantidadPosiciones;
				
				if(ConversionAscii.LetraAscii(letra)-cantidadPosiciones < 65){
					numeroLetraAscii = ConversionAscii.LetraAscii(letra)-cantidadPosiciones + 26;
				}
				
				char letraDescifrada = ConversionAscii.AsciiLetra(numeroLetraAscii);
				
				if(Character.isLowerCase(letra)) {
					mensajeDescifrado += Character.toString(letraDescifrada).toLowerCase();
				}else {
					mensajeDescifrado += Character.toString(letraDescifrada);
				}
				
			}
		}
		
		pMensaje.setMensajeDescifrado(mensajeDescifrado);
		
		return pMensaje;
	}

}
