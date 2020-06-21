package logicadenegocios;
import logicadenegocios.Transposicion;




public class PalabraInversa extends Transposicion {
	
  public Mensaje cifrar(Mensaje pMensaje) {
    return palabraInversa(pMensaje); 
  }
  
  
  public Mensaje descifrar(Mensaje pMensaje) {
	pMensaje.setMensajeDescifrado(pMensaje.getMensajeCifrado());  
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
