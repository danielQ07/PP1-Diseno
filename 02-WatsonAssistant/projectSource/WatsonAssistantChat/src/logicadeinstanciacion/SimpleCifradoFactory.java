package logicadeinstanciacion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import logicadenogocios.ICifrado;

public class SimpleCifradoFactory {

	public ICifrado crearCifradoDescifrado(String pType, String subType,Object parametro) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		ICifrado iCifrado;
		if(subType == null) {
			iCifrado = (ICifrado) Class.forName("logicadenogocios."+pType).newInstance();
			return iCifrado;
		}
		Constructor construc = null;
		construc = Class.forName("logicadenogocios."+pType).getConstructor(parametro.getClass());
		iCifrado = (ICifrado) construc.newInstance(parametro);
		return iCifrado;
		
	}
	
}
