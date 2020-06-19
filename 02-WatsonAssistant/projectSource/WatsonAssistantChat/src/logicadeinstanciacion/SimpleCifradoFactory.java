package logicadeinstanciacion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import logicadenogocios.ICifrado;

public class SimpleCifradoFactory {

	public ICifrado crearCifradoDescirado(String pType, String subType,Object parametro) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		ICifrado iCifrado;
		
		if(subType == null) {
			iCifrado = (ICifrado) Class.forName("logicadenogocios."+pType).newInstance();
			return iCifrado;
		}
		
		Constructor construc = Class.forName("logicadenogocios."+pType).getConstructor(Integer.TYPE);
		iCifrado = (ICifrado) construc.newInstance(parametro);
		return iCifrado;
	}
	
}
