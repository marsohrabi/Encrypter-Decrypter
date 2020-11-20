public class VigenereCipher {
	
	int[] keyCodes;
	String output = "";
	char currentChar;
	
	public String encrypt(String plaintext, String key){
		
		// Get shift values from the key
		getASCIIShift(key);

		int keyLength = keyCodes.length;
		int counter = 0;
		
		// Consider spaces, lowercase and uppercase letters, plus wrapping around
		for (int i=0; i<plaintext.length(); i++){
			if ((int) plaintext.charAt(i) == 32){
				currentChar = (char) 32;
			} else if ((int) plaintext.charAt(i) < 91 && (int) plaintext.charAt(i) + keyCodes[counter%keyLength] > 90){
				currentChar = (char) (65 + (plaintext.charAt(i) + keyCodes[counter%keyLength]) - 91);
				counter++;
			} else if ((int) plaintext.charAt(i) > 96 && (int) plaintext.charAt(i) + keyCodes[counter%keyLength] > 122){
				currentChar = (char) (97 + (plaintext.charAt(i) + keyCodes[counter%keyLength]) - 123);
				counter++;
			} else {
				currentChar = (char) (plaintext.charAt(i) + keyCodes[counter%keyLength]);
				counter++;
			}
			
			output += currentChar;
		}
		
		return output;
	}
	
	public String decrypt(String ciphertext, String key){
		
		keyCodes = new int[key.length()];
		getASCIIShift(key);
		
		int keyLength = keyCodes.length;
		int counter = 0;
		
		// Consider spaces, lowercase and uppercase letters, plus wrapping around
		for (int i=0; i<ciphertext.length(); i++){
			if ((int) ciphertext.charAt(i) == 32){
				currentChar = (char) 32;
			} else if ((int) ciphertext.charAt(i) < 91 && (int) ciphertext.charAt(i) - keyCodes[counter%keyLength] < 65){
				currentChar = (char) (26 + ciphertext.charAt(i) - keyCodes[counter%keyLength]);
				counter++;
			} else if ((int) ciphertext.charAt(i) > 96 && (int) ciphertext.charAt(i) - keyCodes[counter%keyLength] < 97){
				currentChar = (char) (26 + ciphertext.charAt(i) - keyCodes[counter%keyLength]);
				counter++;
			} else {
				currentChar = (char) (ciphertext.charAt(i) - keyCodes[counter%keyLength]);
				counter++;
			}
			
			output += currentChar;
		}
				
		return output;
	}
	
	/**
	 * Converts characters of a string into shift values. A = 0 and Z = 25.
	 * @param keyString The string to be converted into shift values
	 */
	public void getASCIIShift(String keyString){
		// 
		keyCodes = new int[keyString.length()];
		for (int i=0; i<keyString.length(); i++){
			if ((int) keyString.charAt(i) > 90){
				keyCodes[i] = (int) keyString.charAt(i) - 97;
			} else {
				keyCodes[i] = (int) keyString.charAt(i) - 65;
			}
		}
	}
}
