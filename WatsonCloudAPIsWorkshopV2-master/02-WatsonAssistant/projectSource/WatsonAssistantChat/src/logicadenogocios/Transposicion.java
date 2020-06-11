package logicadenogocios;
import logicadenogocios.ICifrado;

public abstract class Transposicion implements ICifrado  {
	
	protected String invertirPalabra (String pTexto ) {
		
      String palabraInvertida = "";
      	
      for(int largo = pTexto.length()-1 ;largo >= 0;largo-- ) {
    	  palabraInvertida += pTexto.charAt(largo);
      }
      	
      return palabraInvertida;
		
	}

}
	