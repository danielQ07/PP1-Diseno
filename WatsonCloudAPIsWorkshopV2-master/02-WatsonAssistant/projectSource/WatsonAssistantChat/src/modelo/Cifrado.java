package modelo;
import modelo.Mensaje;

public interface Cifrado {
	
	
  public abstract Mensaje cifrar(Mensaje pMensaje ) ;
  
  public abstract Mensaje descifrar(Mensaje pMensaje ) ;

}
