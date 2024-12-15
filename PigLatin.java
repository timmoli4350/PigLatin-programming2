// Olivia Timmermann
// Pig Latin Translator 
/*
 * this is a part of a translator that follows the rules for english to piglatin.
 * this class is the PigLatin class that has the translate method for changing a word from English to PigLatin
 * 
 */

public class PigLatin {

    String pigWord = "";

    boolean firstLast = false;
    boolean qStart = false;
    boolean yStart = false;
    boolean consonantStart = false;
    boolean firstUppercaseLetter = false;

    // constructor
    PigLatin(){
    
    }

    public String translate(String word){
        pigWord = ""; // this clears the pigWord and booleans for the next translation 
        //resetting booleans here causes errors

        firstUppercaseLetter = firstLetterUppercase(word);

        word = word.toLowerCase(); //lowercase the word after checking for uppercase

        firstLast = checkFirstLast(word); //this sets a boolean to true if firstlast are the same, false if firstlast are different

        pigWord = checkQ(word); //checks and modifies if it starts with q or qu
        pigWord = checkY(word); //checks and modifies if it starts with y

        //System.out.println(yStart);
        //System.out.println(firstLast);
        System.out.println(firstUppercaseLetter);

        if (qStart == false && yStart == false){ //if it doesnt start with q, qu, or y
            pigWord = checkConsonantsPlace(word); //do the consonant translation
        }
        
        if (qStart == true || yStart == true || consonantStart == true){ //if it started with q, y, or consonant
            if (firstLast == true) { //if the boolean we set earlier was true
                pigWord = fixFirstLast(pigWord); //delete the fourth back letter
            }
        }

        if (consonantStart == false && qStart == false && yStart == false) { //if it didn't start with a q, y, or consonant
            pigWord = checkVowels(word); //check for all vowel cases
        }

        if (firstUppercaseLetter == true) { //if the first letter (the boolean we set at the start) was true
            pigWord = uppercaseFirst(pigWord); //uppercase the first letter of the returned word
        }
        
        return pigWord;

    }

 
    private boolean firstLetterUppercase(String word) { //boolean flag for if the first character is uppercase
        if(word.substring(0,1).equals(word.substring(0,1).toUpperCase())){
            return true;
        }
        
        return false;
    }

    private String uppercaseFirst(String word) { //uppercases the first letter if the boolean flag is on (called only at the end)
        int wordLength = word.length();

        word = word.substring(0,1).toUpperCase() + word.substring(1);

        return word;
    }

    private boolean checkFirstLast(String word) { //this checks if the first and last letters are the same, then flags a boolean if they are.

        int wordLength = word.length();
        if(word.substring(0,1).equals(word.substring(wordLength-1, wordLength))){
            firstLast = true;
            return true;
        }
        return false;
    }

    private String fixFirstLast(String word) { //if at the end of the code, conditions above are met, this removes the 4th last letter - the double.
        
        int wordLength = word.length();
        if(wordLength >= 4) {
            word = word.substring(0, wordLength-3) + word.substring(wordLength-2, wordLength);
        }
        
        return word;
    }

    private String checkQ(String word) { //if the word starts with a qu, end with quay, q = qay (qStart = true for both)

        int wordLength = word.length(); 
    
        if (wordLength > 2) {

            if (word.substring(0, 2).equals("qu")) { //if the word starts with QU 
                word = word.substring(2) + "qu" + "ay";
                qStart = true;
            }
            else if (word.substring(1).equals("q")) { //if the word starts with just a q
                word = word.substring(1) + "q" + "ay";   
                qStart = true;
            }

        }

        return word;
    }


    private String checkY(String word) { //if the word starts with a y, end with yay (yStart = true)

        int wordLength = word.length();

        if (wordLength > 1) {
            if (word.substring(0,1).equals("y")) {
                word = word.substring(1, wordLength) + "y" + "ay";
                yStart = true; //flag if this part of the method was run
            }
        }

        return word;
    }

    private String checkConsonantsPlace(String word) { //---------------------- checkConsonants
        int wordLength = word.length();
        String vowels = "aeiouy";
        int vowelsLength = vowels.length();
        int vowelPlace = 0;

        for (int i = 0; i < wordLength; i++) {                       //cycles through each letter of word
            for(int t = 0; t < vowelsLength; t++) {                  //compares each letter of word to each letter of vowels
                if(word.charAt(i) == vowels.charAt(t)){              //if at any point the above is true:
                    vowelPlace = i;                                  //vowelPlace (where the vowel is in the word) is equal to that place
                    consonantStart = true; //boolean flag
                    return word.substring(vowelPlace, wordLength) + word.substring(0, vowelPlace) + "ay"; //return the pigword: from the vowel place to the end, then add from the start to the vowel place. add -ay
                    
                }
            }
        }

        return word;
    }


    private String checkVowels(String word) { //------------------------------ checkVowels
        int wordLength = word.length();

        String vowelsMin = "aeiouy";
        int vowelsLength = vowelsMin.length();

        for(int t = 0; t < vowelsLength; t++) {
            if(word.charAt(0) == vowelsMin.charAt(t)){
                if(wordLength <= 3) {
                    word = word + "hay";
                }
    
                if(wordLength >= 4) {
                    word = word + "way";
                }
            }
        }

    return word;
    }
}
 


        
