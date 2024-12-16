// Olivia Timmermann
// Pig Latin Translator 
/*
 * this is a part of a translator that follows the rules for english to piglatin.
 * this class is the PigLatin class that has the translate method for changing a word from English to PigLatin
 * 
 */

public class PigLatin { //I think running one time is affecting the next time it is run. 

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

    // constructor
    PigLatin(){
    
    }

    public String translate(String word){
        pigWord = ""; // this clears the pigWord for the next translation 
        resetBooleans();


        firstUppercaseLetter = firstLetterUppercase(word);
        word = word.toLowerCase(); //lowercase the word after checking for uppercase

        firstLast = checkFirstLast(word); //this sets a boolean to true if firstlast are the same, false if firstlast are different

        pigWord = checkQ(word); //checks and modifies if it starts with q or qu
        
        if (!qStart){
            pigWord = checkY(word); //checks and modifies if it starts with y
        }

        if (!qStart && !yStart){
            pigWord = checkVowels(word);
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
        
        //-------------------------------------------------------- debug
        /* 
        System.out.println("does it start with a consonant?");
        System.out.println(consonantStart);
        System.out.println("were the subsets of the vowel method run?");
        System.out.println(vowelCheck);
        System.out.println("does it start with a vowel and is over 4 letters?");
        System.out.println(longVowelCheck);
        System.out.println("q and y");
        System.out.println(qStart);
        System.out.println(yStart);
        System.out.println("checkingPigWordVowels");
        System.out.println(checkingPigWord);
        System.out.println("are the first and last letters the same?");
        System.out.println(firstLast);
        System.out.println("was the vowel method run at all?");
        System.out.println(vowelMethodRun);
        System.out.println("was the consonants method run at all?");
        System.out.println(consonantRun);
        */
        //------------------------------------------------------------------


        return pigWord;

    }

    private void resetBooleans() {
        boolean firstLast = false;
        boolean qStart = false;
        boolean yStart = false;
        boolean consonantStart = false;
        boolean firstUppercaseLetter = false;
        boolean vowelCheck = false;
        boolean longVowelCheck = false;
        boolean checkingPigWord = false;
        boolean vowelMethodRun = false;
        boolean consonantRun = false;
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
                qStart = true;
                return word = word.substring(2) + "qu" + "ay";
                
            }
            else if (word.substring(0, 1).equals("q")) { //if the word starts with just a q
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
                        return word = word + "hay";
                    }

                    if(wordLength >= 4) {
                        longVowelCheck = true;
                        return word = word + "way";
                
                    }
                }
            }
    return word;
    }
}   
 


        
//punctuation added to the end unless it is a double quote which is added to the front