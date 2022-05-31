import java.util.Scanner; 
import java.util.ArrayList; 
public class puzzleSearchGame {
  private ArrayList<String> wordBank = new ArrayList<String>(); 
  private int minGridSize = 10; //10 by 10 square 
  private final int MAX_GRID_SIZE = 30; //30 by 30 square 

  public void runProgram(){
    printInstructions(); 
    processUserMenuInput(); 
  } 
  
  private void printInstructions(){
    System.out.println("Welcome to my word search generator!");
    System.out.println("This program will allow you to generate your own word search puzzle");
    System.out.println("Please select an option: ");
    printInputOptions();
  }

  private void printInputOptions(){
    System.out.println("\t Generate a new word search (g)");
    System.out.println("\t Print out your word search (p)");
    System.out.println("\t Show the solution to your word search (s)");
    System.out.println("\t Quit the program (q)");
  } 

  private void processUserMenuInput(){
    Scanner scannerObject = new Scanner(System.in); 
    String userInput = scannerObject.next(); 
    while(!checkIfValidLetter(userInput)){
      userInput = askForValidInput();
    }
    executeOption(userInput);
  } 

  private boolean checkIfValidLetter(String userInput){
    switch(userInput.toLowerCase()){
      case "g":
        return true; 
      case "p":
        return true; 
      case "s":
        return true; 
      case "q":
        return true; 
      default: 
        return false; 
    }
  } 

  private String askForValidInput(){
    Scanner scannerObject = new Scanner(System.in); 
    System.out.println("Your input is invalid, please consider re-typing in the following options: ");
    printInputOptions();
    String userInput = scannerObject.next(); 
    return userInput; 
  } 

  private void executeOption(String option){
    switch(option){
      case "g":
        generateWordSearch(); 
      default: 
        System.out.println("awd"); 
    }
  } 

  private void generateWordSearch(){
    //ask user repeatedly, the words they want to include. 
    //set up a counter, to get which word is the longest, 
    //set up the grid at the longest word's size 
    //if words don't fit the grid no more, and grid size is still less than max grid size, increase grid size. 

    //if lonest word is less than 10, size is 10 
    //if longest word is more than 10 and less than 30, grid size depends on number of words and if they fit 
    collectWords(); 
    
    
  } 

  private void collectWords(){
    System.out.println("Please enter your word, type in '0' when you're done");
    String userInput = askUserForWords(); 
    while(userInput != "0"){
      this.wordBank.add(userInput); 
      userInput = askUserForWords(); 
    } 
    System.out.println(this.wordBank.toString());  
  } 

  private String askUserForWords(){
    Scanner scannerObject = new Scanner(System.in);
    int wordCount = this.wordBank.size(); 
    System.out.println("enter word " + wordCount + ": ");
    String userInput = scannerObject.next(); 
    return userInput; 
  } 

}
