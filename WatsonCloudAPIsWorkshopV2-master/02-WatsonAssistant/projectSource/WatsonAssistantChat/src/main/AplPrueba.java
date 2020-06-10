package main;

import logicadenogocios.Mensaje;
import logicadenogocios.MensajeInverso;
import logicadenogocios.PalabraInversa;
import logicadenogocios.PorLlave;
import logicadenogocios.SustitucionCesar;

public class AplPrueba {
	
  public static void main(String[] args) {
	  
    Mensaje mensaje1 = new Mensaje("esto es un secreto no lo puedo decir aserpros") ;
    PalabraInversa palabraInversa = new PalabraInversa();
    
    palabraInversa.cifrar(mensaje1);
    System.out.println(mensaje1.getMensajeCifrado());
   
	  
    Mensaje mensaje2 = new Mensaje("viva la vida");
    SustitucionCesar nuevaCesar = new SustitucionCesar(8);
    System.out.println(mensaje2.getMensajeViejo());
    nuevaCesar.cifrar(mensaje2);
    System.out.println(mensaje2.getMensajeCifrado());
    nuevaCesar.descifrar(mensaje2);
    System.out.println(mensaje2.getMensajeDescifrado());
    
    Mensaje mensaje3 = new Mensaje("tarea programada de codificacion");
    PorLlave nueva3 = new PorLlave("tango");
    System.out.println(nueva3.alfabeto);
    System.out.println("---------------separador-----------------");
    System.out.println(mensaje3.getMensajeViejo());
    nueva3.cifrar(mensaje3);
    System.out.println(mensaje3.getMensajeCifrado());
    
  }
	

}
