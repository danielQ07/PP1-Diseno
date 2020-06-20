package logicadeinstanciacion;

import java.lang.reflect.InvocationTargetException;
import logicadenogocios.ICifrado;

public class ControladorCifradoDescifrado {
	
  private SimpleCifradoFactory fabrica;
  
  public ControladorCifradoDescifrado(SimpleCifradoFactory pFabrica) {
  	fabrica = pFabrica;  
  }
  
  public ICifrado crearCifradoDescifrado(String pTipo, String subTipo, Object parametro) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    ICifrado cifrado;
    cifrado = fabrica.crearCifradoDescifrado(pTipo, subTipo, parametro);
    return cifrado;
  }

}
