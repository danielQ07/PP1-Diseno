package main;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import conexion.ChatService;
import logicadeinstanciacion.SimpleCifradoFactory;
import logicadenegocios.CodificacionBinaria;
import logicadenegocios.CodigoTelefonico;
import logicadenegocios.ICifrado;
import logicadenegocios.Mensaje;
import logicadenegocios.MensajeInverso;
import logicadenegocios.PalabraInversa;
import logicadenegocios.PorLlave;
import logicadenegocios.SustitucionCesar;
import logicadenegocios.Vigenere;
import util.EnvioMensajes;
import logicadecontrolador.ControladorCifradoDescifrado;

public class AplPrueba {
	
  public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	  

	  String var = "KEVIN PLAYAZO";
	 Matcher matcher = Pattern.compile(" \"(.*?)\"").matcher("\" \"tango\"\"");
	 if (matcher.find()){
		 System.out.println(matcher.group(1));
	 }
	  


    Mensaje mensaje1 = new Mensaje("nohtyP se erbmon im aloH") ;
    MensajeInverso mensajeinverso = new MensajeInverso();
    mensajeinverso.descifrar(mensaje1);
    System.out.println(mensaje1.getMensajeDescifrado());

    PalabraInversa palabraInversa = new PalabraInversa();
    
    palabraInversa.cifrar(mensaje1);
    System.out.println(mensaje1.getMensajeCifrado());
    palabraInversa.descifrar(mensaje1);

    System.out.println(mensaje1.getMensajeCifrado());

    

	  
    Mensaje mensaje2 = new Mensaje("tarea programada criptografia de datos jeje");
    SustitucionCesar nuevaCesar = new SustitucionCesar(3);
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

    Mensaje mensaje5 = new Mensaje("tarea programada");
//    CodificacionBinaria nuevaBinario = new CodificacionBinaria();
//    System.out.println(nuevaBinario.cifrar(mensaje5));

    Mensaje mensaje6 = new Mensaje("tarea programada criptografia de datos");
    CodigoTelefonico nuevo6 = new CodigoTelefonico();
    
    System.out.println(nuevo6.alfabeto);
    System.out.println("PRUEBAAAA "+nuevo6.cifrar(mensaje6).getMensajeCifrado());
    System.out.println(nuevo6.descifrar(mensaje6).getMensajeDescifrado());
   

    SimpleCifradoFactory nuevoo = new SimpleCifradoFactory();
    ICifrado aa = nuevoo.crearCifradoDescifrado("SustitucionCesar", "", 3);

    aa.cifrar(mensaje5);
    System.out.println(mensaje5.getMensajeCifrado());
 
    ControladorCifradoDescifrado controlador = new ControladorCifradoDescifrado(); 
    controlador.enviarCorreo("danqp071298@gmail.com", "mensaje4");
    
  }
 }	

	


