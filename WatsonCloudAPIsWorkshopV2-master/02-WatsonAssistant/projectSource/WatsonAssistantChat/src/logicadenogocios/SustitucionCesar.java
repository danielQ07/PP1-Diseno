package logicadenogocios;

import java.util.ArrayList;

public class SustitucionCesar implements Cifrado {
	
	
	private int cantidadPosiciones;
	
	public SustitucionCesar() {
		
	}
	
	public SustitucionCesar(int pCantidadPosiciones) {
		this.cantidadPosiciones = pCantidadPosiciones;
	}
	
	private String AsciiLetra(int numero) {
		return Character.toString((char)numero);
	}
	
	
	private int LetraAscii(Character letra) {
		letra = Character.toUpperCase(letra);
		return (int)letra;
	}


//	public Mensaje cifrar(Mensaje pMensaje,int cantidadPosiciones) {
//		
//		char[] mensajeSeparado = pMensaje.getMensajeViejo().toCharArray();
//		String mensajeCifrado = "";
//
//		
//		for(char letra: mensajeSeparado) {
//			
//			if(letra == ' '){
//				mensajeCifrado +=" ";
//			}else {
//			
//				int posicionLetraAscii = LetraAscii(letra)+cantidadPosiciones;
//				
//				if(LetraAscii(letra)+cantidadPosiciones > 90){
//					posicionLetraAscii = LetraAscii(letra)+cantidadPosiciones - 26;
//				}
//				
//				char letraCifrada = AsciiLetra(posicionLetraAscii);
//				mensajeCifrado += Character.toString(letraCifrada);
//			}
//		}
//		
//		pMensaje.setMensajeCifrado(mensajeCifrado);
//		
//		return pMensaje;
//		
//	}
	@Override
	public Mensaje cifrar(Mensaje pMensaje) {
			
		char[] mensajeSeparado = pMensaje.getMensajeViejo().toCharArray();
		String mensajeCifrado = "";

		
		for(char letra: mensajeSeparado) {
			
			if(letra == ' '){
				mensajeCifrado +=" ";
			}else {
			
				int posicionLetraAscii = LetraAscii(letra)+cantidadPosiciones;
				
				if(LetraAscii(letra)+cantidadPosiciones > 90){
					posicionLetraAscii = LetraAscii(letra)+cantidadPosiciones - 26;
				}
				
				char letraCifrada = AsciiLetra(posicionLetraAscii).charAt(0);
				mensajeCifrado += Character.toString(letraCifrada);
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
			
				int numeroLetraAscii = LetraAscii(letra)-cantidadPosiciones;
				
				if(LetraAscii(letra)-cantidadPosiciones < 65){
					numeroLetraAscii = LetraAscii(letra)+cantidadPosiciones + 26;
				}
				
				char letraDescifrada = AsciiLetra(numeroLetraAscii).charAt(0);
				mensajeDescifrado += Character.toString(letraDescifrada);
			}
		}
		
		pMensaje.setMensajeDescifrado(mensajeDescifrado);
		
		return pMensaje;
	}

}
