package logicadenogocios;
import logicadenogocios.Mensaje;

public interface ICifrado {
	
	
  public abstract Mensaje cifrar(Mensaje pMensaje ) ;
  
  public abstract Mensaje descifrar(Mensaje pMensaje ) ;

}
