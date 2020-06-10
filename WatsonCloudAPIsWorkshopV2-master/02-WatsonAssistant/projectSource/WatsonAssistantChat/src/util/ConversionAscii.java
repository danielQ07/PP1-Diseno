package util;

public class ConversionAscii {

	public static String AsciiLetra(int numero) {
		return Character.toString((char)numero);
	}
	
	
	public static int LetraAscii(Character letra) {
		letra = Character.toUpperCase(letra);
		return (int)letra;
	}

	
}
