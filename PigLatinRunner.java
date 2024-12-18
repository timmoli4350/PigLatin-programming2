// Olivia Timmermann
// Pig Latin Runner


import java.util.Scanner;
import static java.lang.System.*;
import java.io.File;
import java.io.IOException;

public class PigLatinRunner
{
	public static void main(String args[]) throws IOException

	{
		String word = "";
		String pigWord = "";
		String sentence = "";
		String pigSentence = "";
		PigLatin pigLatin = new PigLatin(); // instantiates the pigLatin object

		//Scanner keyboard = new Scanner(System.in); // setup to read from the keyboard ||KEYBOARD
 		
		Scanner file = new Scanner(new File("PigLatinTestData.txt")); //TO READ FILE
			while(file.hasNext()) {
				sentence = file.nextLine();
				System.out.println();
				System.out.println(sentence);
			
		
		
		//out.print("Enter a sentence :: "); //||KEYBOARD
		//sentence = keyboard.nextLine(); // read the sentence from the keyboard ||KEYBOARD

		//System.out.println (sentence); // debug statement, eventually delete. prints sentence -> console || KEYBOARD
		

 
 
		Scanner chopper = new Scanner(sentence); // setup the chopper to get one word at a time from the sentence ||KEYBOARD
		while(chopper.hasNext()) // loop to read one word at a time from sentence
		{
			word = chopper.next(); //get the next word
			//out.println(word); // debug - print the original word on the console
			pigWord = pigLatin.translate(word);
			//System.out.println("   PigWord = "+ pigWord); //debug: print each word individually
			pigSentence += " " + pigWord;
		}
		System.out.println("   PigSentence =" + pigSentence); //for the final code; print the full sentence
		

		}
		file.close();
	}
	
}

