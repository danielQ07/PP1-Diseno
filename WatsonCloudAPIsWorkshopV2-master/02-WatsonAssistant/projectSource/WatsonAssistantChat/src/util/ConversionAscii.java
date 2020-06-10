package util;

public class ConversionAscii {

	public static char AsciiLetra(int numero) {
		return (char)numero;
	}
	
	
	public static int LetraAscii(Character letra) {
		letra = Character.toUpperCase(letra);
		return (int)letra;
	}

	
}
