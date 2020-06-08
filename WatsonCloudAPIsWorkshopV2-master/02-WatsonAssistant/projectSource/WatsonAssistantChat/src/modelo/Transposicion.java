package modelo;
import modelo.Cifrado;

public abstract class Transposicion implements Cifrado  {
	
	public String invertirPalabra (String pTexto ) {
		
      String palabraInvertida = "";
      	
      for(int largo = pTexto.length()-1 ;largo >= 0;largo-- ) {
    	  palabraInvertida += pTexto.charAt(largo);
      }
      	
      return palabraInvertida;
		
		
	}
	

}
	