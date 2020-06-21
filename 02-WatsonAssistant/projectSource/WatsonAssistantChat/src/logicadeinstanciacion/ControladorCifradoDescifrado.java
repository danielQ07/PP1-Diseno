package logicadeinstanciacion;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import logicadenegocios.ICifrado;
import logicadenegocios.Mensaje;




/**
 * Clase que sirve como contralador del
 * cifrado y el descifrado.
 * @author Oscar y Daniel
 *
 */
public class ControladorCifradoDescifrado {
	
  private SimpleCifradoFactory fabrica = new SimpleCifradoFactory();
  
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
    cifrado = fabrica.crearCifradoDescifrado(pTipo, subTipo, parametro);
    return cifrado;
  }
  
  public String ejecutarCifrado(ArrayList<String> pLista) {
	  
	  ICifrado cifrado	= null;
	  Mensaje mensaje = new Mensaje(pLista.get(0));	
		
		if(pLista.get(4).equals("int")) {
		  try {
		    cifrado = crearCifradoDescifrado(pLista.get(2), pLista.get(1), Integer.parseInt(pLista.get(3)));
		  } catch(InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
			  | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			  e.printStackTrace();
			}
		  cifrado.cifrar(mensaje);
		 return mensaje.getMensajeCifrado();
		}	
		try {
			cifrado = crearCifradoDescifrado(pLista.get(2), pLista.get(1), (String)pLista.get(3)); ;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
			| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		  }
		cifrado.cifrar(mensaje);
		return mensaje.getMensajeCifrado();	
  }
  
  public String ejecutarDescifrado(ArrayList<String> pLista) {
	  
	  ICifrado nuevo = null;
		Mensaje mensaje = new Mensaje(pLista.get(0));
		mensaje.setMensajeCifrado(pLista.get(0));
		
		  if(pLista.get(4).equals("int")) {
		    try {
			  nuevo = crearCifradoDescifrado(pLista.get(2), pLista.get(1), Integer.parseInt(pLista.get(3)));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			  }
			nuevo.descifrar(mensaje);
			return mensaje.getMensajeDescifrado();
		  }
		  try {
		    nuevo = crearCifradoDescifrado(pLista.get(2), pLista.get(1),(String)pLista.get(3));
		  } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
			  | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			  e.printStackTrace();
			}
		  nuevo.descifrar(mensaje);
		return mensaje.getMensajeDescifrado();	
  }

}