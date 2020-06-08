package logicadenogocios;
import logicadenogocios.Mensaje;

public interface Cifrado {
	
	
  public abstract Mensaje cifrar(Mensaje pMensaje ) ;
  
  public abstract Mensaje descifrar(Mensaje pMensaje ) ;

}
