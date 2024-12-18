// Olivia Timmermann
// Pig Latin Translator 
/*
 * this is a part of a translator that follows the rules for english to piglatin.
 * this class is the PigLatin class that has the translate method for changing a word from English to PigLatin
 * 
 */

public class PigLatin { 

    String pigWord = "";

    boolean firstLast = false; //boolean flags
    boolean qStart = false;
    boolean yStart = false;
    boolean consonantStart = false;
    boolean firstUppercaseLetter = false;

    boolean vowelCheck = false; //debug booleans
    boolean longVowelCheck = false;
    boolean checkingPigWord = false;
    boolean vowelMethodRun = false;
    boolean consonantRun = false;
    

    String punctuationHolder = "";
    String quoteHolder = "";
    String deleteLetter = "";

    // constructor
    PigLatin(){
    
    }

    public String translate(String word){
        pigWord = ""; // this clears the pigWord for the next translation 
        punctuationHolder = ""; //this clears the punctuation holder for the next translation
        quoteHolder = ""; // this clears the quote holder for the next translation
        deleteLetter = "";
        resetBooleans(); //this resets all booleans for the next translation



        firstUppercaseLetter = firstLetterUppercase(word); // check if the first letter needs to be uppercase at the end
        word = word.toLowerCase(); //lowercase the word after checking for uppercase
        
        word = doubleQuotes(word); //modify for quotes, string quoteHolder takes all punctuation
        word = takePunctuation(word); //sets the string punctuationholder to take all the punctuation 

        firstLast = checkFirstLast(word); //this sets a boolean to true if firstlast are the same, false if firstlast are different

        pigWord = checkQ(word); //checks and modifies if it starts with q or qu
        
        if (!qStart){
            pigWord = checkY(word); //checks and modifies if it starts with y
        }

        if (!qStart && !yStart){
            pigWord = checkVowels(word); //checks and modifies if it starts with a vowel
        }
            
        if (qStart == false && yStart == false && vowelCheck == false && longVowelCheck == false){ //if it doesnt start with q, qu, or y, or vowels
            pigWord = checkConsonantsPlace(word); //do the consonant translation
        }

        if (qStart == true || yStart == true || consonantStart == true){ //if it started with q, y, or consonant
            if (firstLast == true) { //if the boolean we set earlier was true
                pigWord = fixFirstLast(pigWord); //delete the fourth back letter
            }
        }

        if (firstUppercaseLetter == true) { //if the first letter (the boolean we set at the start) was true
            pigWord = uppercaseFirst(pigWord); //uppercase the first letter of the returned word
        
        }

        return quoteHolder + pigWord + punctuationHolder; //final return with punctuation

    }

    private void resetBooleans() { //resets all the booleans for the next word, run at the start of each new input
        firstLast = false;
        qStart = false;
        yStart = false;
        consonantStart = false;
        firstUppercaseLetter = false;
        vowelCheck = false;
        longVowelCheck = false;
        checkingPigWord = false;
        vowelMethodRun = false;
        consonantRun = false;
    }


    private String doubleQuotes(String word){

        int wordLength = word.length();


        for (int i = 0; i < wordLength; i++) {                       //cycles through each letter of word
            if(word.charAt(i) == 34){      //34 is the ASCII value for double quotes
                quoteHolder = quoteHolder + word.substring(i, i+1); 
                word = word.substring(0, i) + word.substring(i+1, wordLength);
                wordLength = wordLength - 1; //since the word length decreases with each removed letter
                i--; //remove 1 from the index, see above
            }
        }
        return word;
    }

        

    private String takePunctuation(String word){

        int wordLength = word.length();
        String punctuation = ".!;:/,?";
        int punctuationLength = punctuation.length();

        for (int i = 0; i < wordLength; i++) {                       //cycles through each letter of word
            for(int t = 0; t < punctuationLength; t++) {                  //compares each letter of word to each letter of punctuation
                if(word.charAt(i) == punctuation.charAt(t)){       //if the letter currently there equals one of the punctuations,
                    punctuationHolder = punctuationHolder + word.substring(i, i+1); //add this piece of punctuation to the punctuationHolder
                    word = word.substring(0, i) + word.substring(i+1, wordLength);
                    wordLength = wordLength - 1; //since the word length decreases with each removed letter
                    i--; //remove 1 from the index, see above
                }
            }
        }

        return word;
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
            deleteLetter = word.substring(0,1);
            return true;
        }
        return false;
    }

    private String fixFirstLast(String word) { //if at the end of the code, conditions above are met, this removes the 4th last letter - the double.
        
        int wordLength = word.length();

        for (int i = wordLength-2; i > 0; i--){ //go from the end backwards excluding ay and delete the first instance of that letter
            if (word.substring(i-2, i-1).equals(deleteLetter)){
                word = word.substring(0, i-2) + word.substring(i-1, wordLength) ;
                return word;
            }
        }
        
        return word;
    }

    private String checkQ(String word) { //if the word starts with a qu, end with quay, q = qay (qStart = true for both)

        int wordLength = word.length(); 
    
        if (wordLength > 2) {

            if (word.substring(0, 2).equals("qu")) { //if the word starts with QU 
                qStart = true;
                return word = word.substring(2) + "qu" + "ay";
                
            }
            if (word.substring(0, 1).equals("q")) { //if the word starts with just a q
                qStart = true;
                return word = word.substring(1) + "q" + "ay";   
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
        consonantRun = true;

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

        vowelMethodRun = true;

        int wordLength = word.length();

        String vowels = "aeiouy";
        int vowelsLength = vowels.length();

        //CHECK IF THERE IS A VOWEL FIRST
            for(int t = 0; t < vowelsLength; t++) {                  //compares each letter of word to each letter of vowels
                if(word.charAt(0) == vowels.charAt(t)){ 
                    if(wordLength <= 3) {
                        vowelCheck = true;
                        return word = word + "hay"; // for under =  3 letters
                    }

                    if(wordLength >= 4) {
                        longVowelCheck = true;
                        return word = word + "way"; // for over = 4 letters
                
                    }
                }
            }
    return word;
    }
}   
 