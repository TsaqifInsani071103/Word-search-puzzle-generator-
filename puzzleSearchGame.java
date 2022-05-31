import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.Arrays; 
public class puzzleSearchGame {
  private ArrayList<String> wordBank = new ArrayList<String>(); 
  private int minGridSize = 10; //10 by 10 square 
  private final int MAX_GRID_SIZE = 30; //30 by 30 square 
  private String[][] puzzleGrid = {}; 
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
    System.out.println(this.wordBank.toString()); 
    System.out.println(this.minGridSize); 
    generateGrid(); 
    puzzleToString();  
    
    
  } 

  private void collectWords(){
    System.out.println("Please enter your word, type in '0' when you're done");
    String userInput = "placeholder"; 
    while(!userInput.equals("0")){
      userInput = askUserForWords(); 
      if(!userInput.equals("0")) processWordInput(userInput);
    }  
  } 

  private void processWordInput(String userInput){
    if(checkIfString(userInput)){
      this.wordBank.add(userInput); 
      setMinimumGridLength(userInput);
    }else{
      System.out.println("invalid input, please try again"); 
    }
  } 

  private String askUserForWords(){
    Scanner scannerObject = new Scanner(System.in);
    int wordCount = this.wordBank.size() + 1; 
    System.out.println("enter word " + wordCount + ": ");
    String userInput = scannerObject.next(); 
    return userInput; 
  } 

  private boolean checkIfString(String userInput){
    try{
      Integer.parseInt(userInput);
      return false; 
    } catch(Exception e){
      return true; 
    }
  } 

  private void setMinimumGridLength(String userInput){
    if (userInput.length() > this.minGridSize && userInput.length() < this.MAX_GRID_SIZE){
      this.minGridSize = userInput.length(); 
    } 
  }

  private void generateGrid(){
    this.puzzleGrid = new String[this.minGridSize][]; 
    for(int i = 0; i<this.minGridSize; i++){
      this.puzzleGrid[i] = new String[this.minGridSize]; 
    }
  } 


  private void puzzleToString(){
    for(int i = 0; i < this.minGridSize; i ++){
      for (int j = 0; j<this.minGridSize; j++){
        System.out.print(this.puzzleGrid[i][j] + " ");
      }
      System.out.println(); 
      System.out.println(); 
    }
  } 



}
