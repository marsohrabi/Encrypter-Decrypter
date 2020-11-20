import java.util.Scanner;


public class CaesarCipher {
	Scanner sc = new Scanner(System.in);
	char currentChar;
	int shift = 0;
	boolean validShift = false;
	String output = "";
	
	public String encrypt(String plaintext){	
		// Prompt for the shift/key integer
		System.out.println("Enter the shift value (an integer between 0 and 25):");

		// If input is invalid, continue prompting until a valid input is taken
		while (!validShift){
			if (sc.hasNextInt()){
				shift = sc.nextInt();
				
				if (shift >= 0 && shift <= 25) {
					validShift = true;
				} else {
					System.out.println("Input out of range. Try again.");
				}

			} else {
				System.out.println("You did not enter an integer. Try again.");
				sc.next();
			}
		}
		
		// Consider spaces, lowercase and uppercase letters, plus wrapping around
		for (int i=0; i<plaintext.length(); i++){
			if ((int) plaintext.charAt(i) == 32){
				currentChar = (char) 32;
				
			} else if ((int) plaintext.charAt(i) < 91 && (int) plaintext.charAt(i) + shift > 90){
				currentChar = (char) (65 + (plaintext.charAt(i) + shift) - 91);
				
			} else if ((int) plaintext.charAt(i) > 96 && (int) plaintext.charAt(i) + shift > 122){
				currentChar = (char) (97 + (plaintext.charAt(i) + shift) - 123);
				
			} else {
				currentChar = (char) (plaintext.charAt(i) + shift);
				
			}
			output += currentChar;
		}
		
		sc.close();
		return output;		
		
	}


	public String decrypt(String ciphertext){
		// Prompt for the shift/key integer
		System.out.println("Enter the shift value (an integer between 0 and 25):");

		// If input is invalid, continue prompting until a valid input is taken
		while (!validShift){
			if (sc.hasNextInt()){
				shift = sc.nextInt();
				
				if (shift >= 0 && shift <= 25) {
					validShift = true;
				} else {
					System.out.println("Input out of range. Try again.");
				}

			} else {
				System.out.println("You did not enter an integer. Try again.");
				sc.next();
			}
		}
		
		// Consider spaces, lowercase and uppercase letters, plus wrapping around
		for (int i=0; i<ciphertext.length(); i++){
			if ((int) ciphertext.charAt(i) == 32){
				currentChar = (char) 32;
				
			} else if ((int) ciphertext.charAt(i) < 91 && (int) ciphertext.charAt(i) - shift < 65){
				currentChar = (char) (26 + ciphertext.charAt(i) - shift);
				
			} else if ((int) ciphertext.charAt(i) > 96 && (int) ciphertext.charAt(i) - shift < 97){
				currentChar = (char) (26 + ciphertext.charAt(i) - shift);
				
			} else {
				currentChar = (char) (ciphertext.charAt(i) - shift);
				
			}
			output += currentChar;
		}
		
		sc.close();
	
		return output;
	}
}