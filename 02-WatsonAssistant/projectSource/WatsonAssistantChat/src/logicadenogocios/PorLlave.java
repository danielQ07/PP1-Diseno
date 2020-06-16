package logicadenogocios;

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
				
				contadorPalabraClave = resetPosicionPalabraClave(contadorPalabraClave);

				int indexletra = alfabeto.indexOf(Character.toUpperCase(letra));
				
				char letraPalabraClave = clave[contadorPalabraClave];
				
				int indexLetraClave = alfabeto.indexOf(Character.toUpperCase(letraPalabraClave));
				
				int valorLetraEcriptada = indexLetraClave+indexletra;
				
				valorLetraEcriptada = validarLetraEncriptada(valorLetraEcriptada);
				
				mensajeEncriptado += validarCaps(letra,alfabeto.get(valorLetraEcriptada));
				
				contadorPalabraClave++;
				
			}
		}
		
		pMensaje.setMensajeCifrado(mensajeEncriptado);
		return pMensaje;
		
	}
	@Override
	public Mensaje descifrar(Mensaje pMensaje) {
		String mensajeDesencriptado = "";
		char[] mensajeSeparado = pMensaje.getMensajeViejo().toCharArray();
		
		int contadorPalabraClave = 0;
		
		for(char letra: mensajeSeparado) {
			
			if(letra == ' ') {
				contadorPalabraClave = 0;
				mensajeDesencriptado += " ";
			}else{
				
				contadorPalabraClave = resetPosicionPalabraClave(contadorPalabraClave);
				
				int indexLetraEncriptada = alfabeto.indexOf(Character.toUpperCase(letra));
				
				char letraPalabraClave = clave[contadorPalabraClave];
				
				int indexLetraClave = alfabeto.indexOf(Character.toUpperCase(letraPalabraClave));
				
				int sumaEncriptada = indexLetraEncriptada - indexLetraClave;
				
				sumaEncriptada = validarLetraDesencriptada(sumaEncriptada);
				
				mensajeDesencriptado += validarCaps(letra,alfabeto.get(sumaEncriptada));
				
				contadorPalabraClave++;
				
			}
			
		}
		
		pMensaje.setMensajeDescifrado(mensajeDesencriptado);
		
		return pMensaje;
	}
	private int resetPosicionPalabraClave(int pContadorPalabraClave) {
		if(pContadorPalabraClave == clave.length){
			pContadorPalabraClave = 0;
			return pContadorPalabraClave;
		}
		return pContadorPalabraClave;
	}
	private int validarLetraEncriptada(int pValorLetraEcriptada) {
		if(pValorLetraEcriptada > 26) {
			pValorLetraEcriptada -= 26;
			return pValorLetraEcriptada;
		}
		return pValorLetraEcriptada;
	}
	private int validarLetraDesencriptada(int pValorLetraDesecriptada) {
		if(pValorLetraDesecriptada < 0) {
			pValorLetraDesecriptada += 26;
			return pValorLetraDesecriptada;
		}
		return pValorLetraDesecriptada;
	}
	
}
