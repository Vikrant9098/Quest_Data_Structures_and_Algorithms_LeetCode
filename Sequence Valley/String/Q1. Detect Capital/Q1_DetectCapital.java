class Solution {                                       // Define the Solution class
    public boolean detectCapitalUse(String word) {     // Method to check capital usage
        
        if (word.length() == 0 || word.length() == 1)  // If word is empty or has one letter
            return true;                               // Always valid
        
        if (Character.isUpperCase(word.charAt(0))) {  // If first letter is uppercase
            
            boolean isFirstCharacter =                // Check case of second letter
                    Character.isUpperCase(word.charAt(1));
            
            for (int i = 2; i < word.length(); i++) { // Loop from third character
                boolean currentCharState =            // Check current letter case
                        Character.isUpperCase(word.charAt(i));
                
                if (currentCharState != isFirstCharacter) // If case does not match
                    return false;                     // Capital usage is wrong
            }
        } else {                                      // If first letter is lowercase
            
            for (int i = 1; i < word.length(); i++) { // Loop through remaining letters
                if (Character.isUpperCase(word.charAt(i))) // If any letter is uppercase
                    return false;                     // Capital usage is wrong
            }
        }
        
        return true;                                  // Capital usage is correct
    }
}
