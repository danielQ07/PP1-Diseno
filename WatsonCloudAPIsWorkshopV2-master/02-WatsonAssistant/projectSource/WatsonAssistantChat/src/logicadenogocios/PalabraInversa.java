package logicadenogocios;
import logicadenogocios.Mensaje;
import logicadenogocios.Transposicion;

public class PalabraInversa extends Transposicion {
	
  public Mensaje cifrar(Mensaje pMensaje) {
	  
    return palabraInversa(pMensaje);
	  
  }
  
  public Mensaje descifrar(Mensaje pMensaje) {
	  
	return palabraInversa(pMensaje);  
  }
  
  private Mensaje palabraInversa(Mensaje pMensaje) {
	  
    String textoMensaje = pMensaje.getMensajeViejo();
    String textoFinal = "";
    String [] listaPalabras = textoMensaje.split("\\s+");
    
    for(String palabraActual : listaPalabras) {
    
      textoFinal += invertirPalabra(palabraActual) + " ";
    	
    }
    
    pMensaje.setMensajeCifrado(textoFinal);
    return pMensaje;
  }

}
