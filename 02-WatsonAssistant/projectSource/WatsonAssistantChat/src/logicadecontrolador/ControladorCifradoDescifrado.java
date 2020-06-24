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
	
  private SimpleCifradoFactory fabrica = new SimpleCifradoFactory();
  private ICifrado strategy;
  
  public ControladorCifradoDescifrado() {}
  /**
   * Método que crea el cifrado o el descifrado
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
    cifrado = fabrica.crearCifradoDescifrado(pTipo, subTipo, parametro);
    return cifrado;
  }
  
  private void asignarStrategy(String pTipo, String pSubTipo, int parametroNum) {
	  try {
		strategy = crearCifradoDescifrado(pTipo, pSubTipo, parametroNum);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
			| InvocationTargetException | NoSuchMethodException | SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  private void asignarStrategy(String pTipo, String pSubTipo, String parametroStr) {
	  try {
		strategy = crearCifradoDescifrado(pTipo, pSubTipo, parametroStr);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
			| InvocationTargetException | NoSuchMethodException | SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public String ejecutarCifrado(ArrayList<String> pLista) {
	Mensaje mensaje = new Mensaje(pLista.get(0));	
    if(pLista.get(4).equals("int")) {
		
      asignarStrategy(pLista.get(2), pLista.get(1), Integer.parseInt(pLista.get(3)));

      strategy.cifrar(mensaje);
	  
	  
	  if(pLista.get(5) != null) {
		enviarCorreo(pLista.get(5),"Mensaje codificado: "+mensaje.getMensajeCifrado());
	  }
	  return mensaje.getMensajeCifrado();
    }	
    asignarStrategy(pLista.get(2), pLista.get(1), (String)pLista.get(3));
    strategy.cifrar(mensaje);
    if(pLista.get(5) != null) {
	  enviarCorreo(pLista.get(5),"Mensaje codificado: "+mensaje.getMensajeCifrado());
	}
    return mensaje.getMensajeCifrado();	
  }
  
  public String ejecutarDescifrado(ArrayList<String> pLista) {
	Mensaje mensaje = new Mensaje(pLista.get(0));
	mensaje.setMensajeCifrado(pLista.get(0));
	
	if(pLista.get(4).equals("int")) {

      asignarStrategy(pLista.get(2), pLista.get(1), Integer.parseInt(pLista.get(3)));
      strategy.descifrar(mensaje);
	  if(pLista.get(5) != null) {
		enviarCorreo(pLista.get(5),"Mensaje descodificado: "+mensaje.getMensajeDescifrado());
	  }
	  return mensaje.getMensajeCifrado();
    }	
    asignarStrategy(pLista.get(2), pLista.get(1), (String)pLista.get(3));
	strategy.descifrar(mensaje);
	if(pLista.get(5) != null) {
		enviarCorreo(pLista.get(5),"Mensaje descodificado: "+mensaje.getMensajeDescifrado());
	}
	return mensaje.getMensajeDescifrado();	
  }
  
  public void enviarCorreo(String pCorreo,String pMensaje) {
    try {
	  EnvioMensajes.enviarSms(pCorreo,pMensaje);
	}catch(Exception e) {
		System.out.print(e);
	}
  }

}
