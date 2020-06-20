package logicadeinstanciacion;
import java.lang.reflect.InvocationTargetException;
import logicadenogocios.ICifrado;




/**
 * Clase que sirve como contralador del
 * cifrado y el descifrado.
 * @author Oscar y Daniel
 *
 */
public class ControladorCifradoDescifrado {
	
  private SimpleCifradoFactory fabrica;
  
  /**
   * Constructor de la clase.
   * @param pFabrica
   */
  public ControladorCifradoDescifrado(SimpleCifradoFactory pFabrica) {
  	fabrica = pFabrica;  
  }
  
  
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
  public ICifrado crearCifradoDescifrado(String pTipo, String subTipo, Object parametro) throws InstantiationException,
    IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    ICifrado cifrado;
    cifrado = fabrica.crearCifradoDescifrado(pTipo, subTipo, parametro);
    return cifrado;
  }

}
