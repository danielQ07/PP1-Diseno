package main;

import logicadenogocios.Mensaje;
import logicadenogocios.MensajeInverso;
import logicadenogocios.PalabraInversa;
import logicadenogocios.SustitucionCesar;

public class AplPrueba {
	
  public static void main(String[] args) {
	  
    Mensaje mensaje1 = new Mensaje("esto es un secreto no lo puedo decir aserpros") ;
    PalabraInversa palabraInversa = new PalabraInversa();
    
    palabraInversa.cifrar(mensaje1);
    System.out.println(mensaje1.getMensajeCifrado());
   
	  
    Mensaje mensaje2 = new Mensaje("zorra");
    SustitucionCesar nuevaCesar = new SustitucionCesar();
    System.out.println(mensaje2.getMensajeViejo());
    nuevaCesar.cifrar(mensaje2, 26);
    System.out.println(mensaje2.getMensajeCifrado());
    
  }
	

}
