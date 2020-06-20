package logicadeinstanciacion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import logicadenogocios.ICifrado;

public class SimpleCifradoFactory {

	public ICifrado crearCifradoDescifrado(String pType, String pSubType,Object pParametro) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		ICifrado iCifrado;
		if(pSubType == null) {
			iCifrado = (ICifrado) Class.forName("logicadenogocios."+pType).newInstance();
			return iCifrado;
		}
		Constructor construc = Class.forName("logicadenogocios."+pType).getConstructor(pParametro.getClass());
		iCifrado = (ICifrado) construc.newInstance(pParametro);
		return iCifrado;
		
	}
	
}
