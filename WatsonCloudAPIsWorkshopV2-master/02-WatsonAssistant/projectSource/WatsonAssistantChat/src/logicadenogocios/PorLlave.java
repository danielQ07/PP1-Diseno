package logicadenogocios;

import java.util.ArrayList;

import util.ConversionAscii;

public class PorLlave extends SustitucionClave {
	
	private char[] clave;

	public PorLlave(String pClave) {
		this.clave = pClave.toCharArray();
	}
	
	@Override
	public Mensaje cifrar(Mensaje pMensaje) {
		
		String mensajeEncriptado = "";
		char[] mensajeSeparado = pMensaje.getMensajeViejo().toCharArray();
		
		int contadorPalabraClave = 0;
		
		for(char letra: mensajeSeparado) {
			
			if(letra == ' ') {
				contadorPalabraClave = 0;
				mensajeEncriptado += " ";
			}else{
				
				if(contadorPalabraClave == clave.length){
					contadorPalabraClave = 0;
				}
				
				int indexletra = alfabeto.indexOf(Character.toUpperCase(letra));
				
				char letraPalabraClave = clave[contadorPalabraClave];
				
				int indexLetraClave = alfabeto.indexOf(Character.toUpperCase(letraPalabraClave));
				
				int valorLetraEcriptada = indexLetraClave+indexletra;
				
				if(valorLetraEcriptada > 26) {
					valorLetraEcriptada -= 26;
				}
				
				char letraEncriptada = alfabeto.get(valorLetraEcriptada);
				
				
				mensajeEncriptado += letraEncriptada;
				
				contadorPalabraClave++;
				
			}
			
		}
		
		pMensaje.setMensajeCifrado(mensajeEncriptado);
		
		return pMensaje;
		
	}

	@Override
	public Mensaje descifrar(Mensaje pMensaje) {
		return null;
	}
	
	private int posicionLetra(Character letra) {
		
		return ConversionAscii.LetraAscii(letra);

	}
	
}
