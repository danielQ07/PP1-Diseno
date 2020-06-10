package logicadenogocios;

import util.ConversionAscii;

public class SustitucionCesar implements Cifrado {
	
	private int cantidadPosiciones;
	
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
				
				posicionLetraAscii = validarAsciiLetra(letra,posicionLetraAscii);
				
				char letraCifrada = ConversionAscii.AsciiLetra(posicionLetraAscii);
				
				mensajeCifrado += validarCaps(letra,letraCifrada);
				
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
				
				numeroLetraAscii = validarLetraAscii(letra,numeroLetraAscii);
				
				char letraDescifrada = ConversionAscii.AsciiLetra(numeroLetraAscii);
				
				mensajeDescifrado += validarCaps(letra,letraDescifrada);
				
			}
		}
		
		pMensaje.setMensajeDescifrado(mensajeDescifrado);
		
		return pMensaje;
	}
	private char validarCaps(char pLetraActual,char pLetraEncriptada) {
		if(Character.isLowerCase(pLetraActual)) {
			pLetraEncriptada = Character.toLowerCase(pLetraEncriptada);
			return pLetraEncriptada;
		}
		return pLetraEncriptada;
	}
	private int validarLetraAscii(char pLetra, int pNumeroLetraAscii) {
		if(ConversionAscii.LetraAscii(pLetra)-cantidadPosiciones < 65){
			return (pNumeroLetraAscii = ConversionAscii.LetraAscii(pLetra)-cantidadPosiciones + 26);
		}
		return pNumeroLetraAscii;
	}
	private int validarAsciiLetra(char pLetra, int pNumeroAsciiLetra) {
		if(ConversionAscii.LetraAscii(pLetra)+cantidadPosiciones > 90){
			pNumeroAsciiLetra = ConversionAscii.LetraAscii(pLetra)+cantidadPosiciones - 26;
		}
		return pNumeroAsciiLetra;
	}
	
}
