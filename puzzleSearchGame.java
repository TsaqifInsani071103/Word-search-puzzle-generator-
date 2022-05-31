import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.Random; 
import java.util.Arrays; 
public class puzzleSearchGame {
  private ArrayList<individualLetter[]> wordBank = new ArrayList<individualLetter[]>(); 
  private final String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", 
  "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}; 
  private int minGridSize = 10; //10 by 10 square 
  private final int MAX_GRID_SIZE = 25; //30 by 30 square 
  private individualMultipleTypes[][] puzzleGrid = {}; 
  private boolean gridIsFilled = true; 
  private int wordsFilled = 0; 

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
        break; 
      default: 
        System.out.println("isyou"); 
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
    System.out.println(this.minGridSize); 
    generateInitialGrid();  
    fillInGrid(); 

    
    
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
      this.wordBank.add(makeWordIntoArray(userInput)); 
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

  private individualLetter[] makeWordIntoArray(String userInput){
    individualLetter[] wordArrayString = new individualLetter[userInput.length()]; 
    for(int i = 0; i<userInput.length(); i++){
      wordArrayString[i] = new individualLetter(userInput.substring(i, i+1).toUpperCase());
    }
    return wordArrayString; 
  } 

  private void setMinimumGridLength(String userInput){
    //remove the equals to ten later 
    if (this.minGridSize == 10 && userInput.length() > this.minGridSize && userInput.length() < this.MAX_GRID_SIZE){
      this.minGridSize = userInput.length(); 
    } 
  }

  private void generateInitialGrid(){
    Random randomObject = new Random(); 
    this.puzzleGrid = new individualMultipleTypes[this.minGridSize][]; 
    for(int i = 0; i<this.minGridSize; i++){
      individualMultipleTypes[] puzzleRow = new individualMultipleTypes[this.minGridSize]; 
      for(int j = 0; j<this.minGridSize; j++){
        puzzleRow[j] = new individualMultipleTypes(ALPHABET[randomObject.nextInt(26)], null, null);
      }
      this.puzzleGrid[i] = puzzleRow; 
    }
  } 


  private void puzzleToString(){
    for(int i = 0; i < this.minGridSize; i ++){
      for (int j = 0; j<this.minGridSize; j++){
        if(this.puzzleGrid[i][j].getRealValue() != null){
          System.out.print(this.puzzleGrid[i][j].getRealValue() + " ");
        }else{
          System.out.print(this.puzzleGrid[i][j].getFakeValue() + " ");
        }
      }
      System.out.println(); 
    }
  } 

  private void fillInGrid(){
    verticalIntegration integrateVertically = new verticalIntegration(this.minGridSize, this.puzzleGrid); 
    horizontalIntegration integrateHorizontally = new horizontalIntegration(this.minGridSize, this.puzzleGrid); 
    diagonalIntegration integrateDiagonally = new diagonalIntegration(this.minGridSize, this.puzzleGrid); 
    int turn = 0; 
    for(individualLetter[] word : wordBank){
      int incrementCheck = 1; 
      integrationOptions(turn, word, integrateVertically, integrateHorizontally, integrateDiagonally); 
      while(!this.gridIsFilled && incrementCheck <= 6){
        incrementCheck++; 
        if(turn + 1 >= 6){
          turn = 0;
        }else{
          turn++; 
        }
        integrationOptions(turn, word, integrateVertically, integrateHorizontally, integrateDiagonally); 
      }
      if(turn + 1 >= 6){
        turn =0;
      }else{
        turn++; 
      }
      if(!this.gridIsFilled && this.minGridSize < this.MAX_GRID_SIZE){
        this.wordsFilled = 0; 
        break; 
      }else if (this.gridIsFilled){
        this.wordsFilled++; 
      }; 
    }

    if(!this.gridIsFilled && this.minGridSize < this.MAX_GRID_SIZE){
      reInitializeGrid();
      fillInGrid();
      this.wordsFilled = 0; 
    }else{
      System.out.println(wordsFilled); 
      puzzleToString();
      return; 
    }
  } 

  private void reInitializeGrid(){
    if(this.minGridSize < this.MAX_GRID_SIZE){
      this.minGridSize++;
    }else{
      System.out.println("No more space"); 
    }
    generateInitialGrid();
  } 

  private void integrationOptions(int number, individualLetter[] word, verticalIntegration integrateVertically, 
  horizontalIntegration integrateHorizontally, diagonalIntegration integrateDiagonally){
    switch(number){
      case 0: 
        this.gridIsFilled = integrateVertically.integrate(word);
        break; 
      case 1: 
        this.gridIsFilled = integrateVertically.integrateBackwards(word); 
        break; 
      case 2: 
        this.gridIsFilled = integrateHorizontally.integrate(word);
        break; 
      case 3: 
        this.gridIsFilled = integrateHorizontally.integrateBackwards(word); 
        break; 
      case 4:
        this.gridIsFilled = integrateDiagonally.integrate(word); 
        break; 
      case 5:
        this.gridIsFilled = integrateDiagonally.integrateBackwards(word); 
        break; 
      default: 
        return; 
    }
  } 
}
