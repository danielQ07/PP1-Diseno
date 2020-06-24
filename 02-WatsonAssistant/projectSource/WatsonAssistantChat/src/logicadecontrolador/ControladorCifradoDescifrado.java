package logicadecontrolador;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import logicadeinstanciacion.SimpleCifradoFactory;
import logicadenegocios.ICifrado;
import logicadenegocios.Mensaje;
import util.EnvioMensajes;

/**
 * Clase que sirve como contralador del
 * cifrado y el descifrado.
 * @author Oscar y Daniel
 *
 */
public class ControladorCifradoDescifrado {
	
  private SimpleCifradoFactory factory = new SimpleCifradoFactory();
  private ICifrado strategy;
  
  public ControladorCifradoDescifrado() {}
  /**
   * M�todo que crea el cifrado o el descifrado
   * dependiendo del tipo.
   * @param pTipo
   * @param subTipo
   * @param parametro
   * @return
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ClassNotFoundException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws SecurityException
   */
  private ICifrado crearCifradoDescifrado(String pTipo, String subTipo, Object parametro) throws InstantiationException,
    IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    ICifrado cifrado;
    cifrado = factory.crearCifradoDescifrado(pTipo, subTipo, parametro);
    return cifrado;
  }
  
  private void asignarStrategy(String pTipo, String pSubTipo, String parametro) {
	  try {
		strategy = crearCifradoDescifrado(pTipo, pSubTipo, parametro);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
			| InvocationTargetException | NoSuchMethodException | SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public String ejecutarCifrado(ArrayList<String> pLista) {
	Mensaje mensaje = new Mensaje(pLista.get(0));	
	asignarStrategy(pLista.get(2), pLista.get(1),pLista.get(3));
	strategy.cifrar(mensaje);
	if(pLista.get(5) != null) {
		enviarCorreo(pLista.get(5),"Mensaje codificado: "+mensaje.getMensajeCifrado());
	}
	return mensaje.getMensajeCifrado(); 
  }
  
  public String ejecutarDescifrado(ArrayList<String> pLista) {
	Mensaje mensaje = new Mensaje(pLista.get(0));
	mensaje.setMensajeCifrado(pLista.get(0));
	asignarStrategy(pLista.get(2), pLista.get(1),pLista.get(3));
	strategy.descifrar(mensaje);
	if(pLista.get(5) != null) {
		enviarCorreo(pLista.get(5),"Mensaje codificado: "+mensaje.getMensajeDescifrado());
	}
	return mensaje.getMensajeDescifrado();	
  }
  
  private void enviarCorreo(String pCorreo,String pMensaje) {
    try {
	  EnvioMensajes.enviarSms(pCorreo,pMensaje);
	}catch(Exception e) {
		System.out.print(e);
	}
  }

}
