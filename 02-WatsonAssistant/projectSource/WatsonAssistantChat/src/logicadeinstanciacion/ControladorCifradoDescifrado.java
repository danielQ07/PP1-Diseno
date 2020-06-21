package logicadeinstanciacion;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

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
  
  public String ejecutarCifrado(ArrayList<String> pLista) {
	Mensaje mensaje = new Mensaje(pLista.get(0));	
	if(pLista.get(4).equals("int")) {
	  try {
	    strategy = crearCifradoDescifrado(pLista.get(2), pLista.get(1), Integer.parseInt(pLista.get(3)));
	  } catch(InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
		  | InvocationTargetException | NoSuchMethodException | SecurityException e) {
	    e.printStackTrace();
	   }
	  strategy.cifrar(mensaje);
	  if(pLista.get(5) != null) {
		enviarCorreo(pLista.get(5),"Mensaje codificado: "+mensaje.getMensajeCifrado());
	  }
	  return mensaje.getMensajeCifrado();
	}	
	try {
	  strategy = crearCifradoDescifrado(pLista.get(2), pLista.get(1), (String)pLista.get(3)); ;
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
		| InvocationTargetException | NoSuchMethodException | SecurityException e) {
	  e.printStackTrace();
	}
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
	   try {
	     strategy = crearCifradoDescifrado(pLista.get(2), pLista.get(1), Integer.parseInt(pLista.get(3)));
	   } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
		  | InvocationTargetException | NoSuchMethodException | SecurityException e) {
	     e.printStackTrace();
	   }
	   strategy.descifrar(mensaje);
	   if(pLista.get(5) != null) {
	     enviarCorreo(pLista.get(5),"Mensaje descodificado: "+mensaje.getMensajeDescifrado());
	   }
	   return mensaje.getMensajeDescifrado();
	 }
	 try {
       strategy = crearCifradoDescifrado(pLista.get(2), pLista.get(1),(String)pLista.get(3));
	 } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
		| InvocationTargetException | NoSuchMethodException | SecurityException e) {
		e.printStackTrace();
	}
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
