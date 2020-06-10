package logicadenogocios;
import logicadenogocios.Cifrado;

public abstract class Transposicion implements Cifrado  {
	
	protected String invertirPalabra (String pTexto ) {
		
      String palabraInvertida = "";
      	
      for(int largo = pTexto.length()-1 ;largo >= 0;largo-- ) {
    	  palabraInvertida += pTexto.charAt(largo);
      }
      	
      return palabraInvertida;
		
	}

}
	