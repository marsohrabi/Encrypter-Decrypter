import java.util.Scanner;


public class Encrypter {

	public static void main(String[] args) {
		String cipher = "";
		String operation = "";
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter 'C' for Casear Cipher or 'V' for Vigenere Cipher: ");
		cipher = sc.nextLine();
		
		// If input is invalid, continue prompting until a valid input is taken
		while (!cipher.equals("C") && !cipher.equals("V")){
			System.out.println("Invalid input. Try again.");
			cipher = sc.nextLine();
		}
		
		System.out.print("Enter E to encrypt or D to decrypt: ");
		operation = sc.nextLine();
		
		while (!operation.equals("E") && !operation.equals("D")){
			System.out.println("Invalid input. Try again.");
			operation = sc.nextLine();
		}
		
		System.out.println("Enter the plaintext (if encrypting) or ciphertext (if decrypting):");
		String text = sc.nextLine();
		
		// Check that there are no invalid characters in the text
		while (!isValid(text,true)){
			System.out.println("Invalid text entered. Try again.");
			text = sc.nextLine();
		}
	
		if (operation.equals("E")){ // Encryption mode
			if (cipher.equals("C")){ // Caesar Cipher
				CaesarCipher caesarCipher = new CaesarCipher();
				System.out.println("Encrypting line: " + text + "\n");
				System.out.println("Encrypted message:\n" + caesarCipher.encrypt(text));
			} else { // Vigenere Cipher
				VigenereCipher vigenereCipher = new VigenereCipher();
				
				System.out.println("Enter a key (a series of letters, not case sensetive):");
				String key = sc.nextLine();
				
				while (!isValid(key,false)){
					System.out.println("Invalid key entered. Try again.");
					key = sc.nextLine();
				}
				
				System.out.println("Encrypting line: " + text + "\n");
				System.out.println("Encrypted message:\n" + vigenereCipher.encrypt(text, key));
			}
		} else { // Decryption mode
			if (cipher.equals("C")){ // Caesar Cipher
				CaesarCipher caesarCipher = new CaesarCipher();
				System.out.println("Decrypting line: " + text + "\n");
				System.out.println("Decrypted message:\n" + caesarCipher.decrypt(text));
			} else { // Vigenere Cipher
				VigenereCipher vigenereCipher = new VigenereCipher();
				
				System.out.println("Enter a key (a series of letters, not case sensetive):");
				String key = sc.next();
				
				while (!isValid(key,false)){
					System.out.println("Invalid key entered. Try again.");
					key = sc.next();
				}
				
				System.out.println("Decrypting line: " + text + "\n");
				System.out.println("Encrypted message:\n" + vigenereCipher.decrypt(text, key));
			}
		}
		
		System.out.println("\nExiting Encrypter");
		sc.close();
				
	}
	/**
	 * Checks for invalid characters in a string of text.
	 * @param input The string to be checked
	 * @param space True if spaces are allowed
	 * @return True if the string contains only valid characters
	 */
	static boolean isValid(String input, boolean space){
		int inter;
		boolean result = true;
		
		if (space){
			for (int i=0; i<input.length(); i++){
				inter = (int) input.charAt(i);

				if (inter != 32 && !((inter >= 65 && inter <= 90) || (inter >= 97 && inter <= 122))){
					result = false;
				}
			}
		} else {
			for (int i=0; i<input.length(); i++){
				inter = (int) input.charAt(i);

				if (!((inter >= 65 && inter <= 90) || (inter >= 97 && inter <= 122))){
					result = false;
				}
			}
		}
		
		return result;
	}

}
