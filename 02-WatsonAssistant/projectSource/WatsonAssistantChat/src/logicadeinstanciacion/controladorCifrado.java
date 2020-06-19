package logicadeinstanciacion;

import java.lang.reflect.InvocationTargetException;

import logicadenogocios.ICifrado;

public class controladorCifrado {
	
  private SimpleCifradoFactory fabrica;
  
  public controladorCifrado(SimpleCifradoFactory pFabrica) {
  	fabrica = pFabrica;  
	  
  }
  
  public ICifrado realizarCifradoDescifrado(String pTipo, String subTipo, Object parametro) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    ICifrado cifrado;
    cifrado = fabrica.crearCifradoDescifrado(pTipo, subTipo, parametro);
    return cifrado;
    
    
	  
  }

}
