package main;

import logicadenogocios.Mensaje;
import logicadenogocios.PalabraInversa;
import logicadenogocios.PorLlave;
import logicadenogocios.SustitucionCesar;
import logicadenogocios.Vigenere;

public class AplPrueba {
	
  public static void main(String[] args) {
	  
    Mensaje mensaje1 = new Mensaje("esto es un secreto no lo puedo decir aserpros") ;
    PalabraInversa palabraInversa = new PalabraInversa();
    
    palabraInversa.cifrar(mensaje1);
    System.out.println(mensaje1.getMensajeCifrado());
    palabraInversa.descifrar(mensaje1);
   
	  
    Mensaje mensaje2 = new Mensaje("Viva la Vida");
    SustitucionCesar nuevaCesar = new SustitucionCesar(8);
    System.out.println(mensaje2.getMensajeViejo());
    nuevaCesar.cifrar(mensaje2);
    System.out.println(mensaje2.getMensajeCifrado());
    nuevaCesar.descifrar(mensaje2);
    System.out.println(mensaje2.getMensajeDescifrado());
    

    Mensaje mensaje3 = new Mensaje("Tarea Programada de Codificacion");
    PorLlave nueva3 = new PorLlave("tango");
    System.out.println("---------------separador-----------------");
    System.out.println(mensaje3.getMensajeViejo());
    nueva3.cifrar(mensaje3);
    System.out.println(mensaje3.getMensajeCifrado());
    nueva3.descifrar(mensaje3);
    System.out.println(mensaje3.getMensajeDescifrado());
    
    Mensaje mensaje4 = new Mensaje("Tarea Programada criptografia de datos");
    Vigenere nueva4 = new Vigenere(23);
    System.out.println(mensaje4.getMensajeViejo());
    nueva4.cifrar(mensaje4);
    System.out.println(mensaje4.getMensajeCifrado());
    nueva4.descifrar(mensaje4);
    System.out.println(mensaje4.getMensajeDescifrado());
    
  }

   /* String texto = "10001";
	int integer = Integer.parseInt(texto);
	String textoFinal = Integer.toBinaryString(integer);
	System.out.println("Binario:::  "+ textoFinal);*/
  }	

	


