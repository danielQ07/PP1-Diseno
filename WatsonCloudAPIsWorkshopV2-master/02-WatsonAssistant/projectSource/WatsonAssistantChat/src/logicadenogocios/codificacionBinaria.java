package logicadenogocios;

public class codificacionBinaria {
	
	public String cifrar(String pTexto) {
	  return textoBinario(pTexto);		
	}
	
	
	public String descifrar(String pTexto) {
	  return binarioTexto(pTexto);			  
	}
	
	private String cambiarCaracterBinario(int pValorLetra) {		
	  String letraConvertida;
	  if(pValorLetra == 32) {
	    return "*";
	  } else {
		  letraConvertida = Long.toBinaryString(pValorLetra-97);	
		  return validarCambioBinario(letraConvertida);
	    }		
	}
	
	
	private char cambiarBinarioCaracter(String pTexto) {
	  int valorLetra = 16;
	  int valorNumerico = 0;
	  for(int contador = 0;contador<pTexto.length();contador++) {
		valorNumerico += Character.getNumericValue(pTexto.charAt(contador)) *valorLetra;
		valorLetra = valorLetra / 2;
	  }
	  return AsciiLetra(valorNumerico+97);
	}
	
	
	private char validarEspacioBlanco(String pTexto) {
	  if(pTexto.contentEquals("*")) {
	    return ' ';   
	  }	else {
		  return cambiarBinarioCaracter(pTexto);  
	    }
	}
	
	
	private String validarCambioBinario(String pLetraConvertida) {
		
	  if(pLetraConvertida.length() == 5) {
		return pLetraConvertida;  
	  } else {
		  while(pLetraConvertida.length()<5) {
			  pLetraConvertida = "0" + pLetraConvertida; 
		  }
	    }
	  return pLetraConvertida;  
	}
	
	private String textoBinario(String texto){
      byte[] listaBytes = texto.getBytes();
      String textoCifrado = "";
      for (byte byteActual : listaBytes){
	     textoCifrado += cambiarCaracterBinario(byteActual)+" ";
	  }
	  return textoCifrado;
	}
	  
	private String binarioTexto(String texto) {
      String textoCifrado = "";
      String[] textoSeparado = texto.split(" ");
      for (String separadoActual : textoSeparado) {
        textoCifrado += validarEspacioBlanco(separadoActual);
      }
      return textoCifrado;
	}
	

	
	private char AsciiLetra(int numero) {
	  return (char) numero;
	}
	
	
	private int LetraAscii(Character letra) {
	  letra = Character.toUpperCase(letra);
	  return (int)letra;
	}
	
	
	
  	
  	

}
