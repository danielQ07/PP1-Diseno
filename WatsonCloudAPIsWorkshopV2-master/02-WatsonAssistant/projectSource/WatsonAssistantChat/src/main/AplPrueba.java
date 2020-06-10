package main;
import java.lang.*;
import java.io.*;

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
    palabraInversa.descifrar(mensaje1);
   
	  
    Mensaje mensaje2 = new Mensaje("viva la vida");
    SustitucionCesar nuevaCesar = new SustitucionCesar(8);
    System.out.println(mensaje2.getMensajeViejo());
    nuevaCesar.cifrar(mensaje2);
    System.out.println(mensaje2.getMensajeCifrado());
    nuevaCesar.descifrar(mensaje2);
    System.out.println(mensaje2.getMensajeDescifrado());
    
   /* String texto = "10001";
	int integer = Integer.parseInt(texto);
	String textoFinal = Integer.toBinaryString(integer);
	System.out.println("Binario:::  "+ textoFinal);*/
  }	
	

}
