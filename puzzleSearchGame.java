import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.Random; 
public class puzzleSearchGame {
  private ArrayList<individualLetter[]> wordBank = new ArrayList<individualLetter[]>(); 
  private final String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", 
  "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}; 
  private int minGridSize = 10; //10 by 10 square 
  private final int MAX_GRID_SIZE = 25; //25 by 25 square 
  private individualMultipleTypes[][] puzzleGrid = {}; 
  private boolean gridIsFilled = true; 
  private int wordsFilled = 0; 
  private boolean continueLoop = true; 

  public void runProgram(){
    while(this.continueLoop){
      printInstructions(); 
      processUserMenuInput(); 
    }
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
      case "q":
        this.continueLoop = false; 
        break; 
      case "p":
        printPuzzle(false); 
        break; 
      case "s":
        printPuzzle(true); 
        break; 
      default: 
        break; 
    }
  } 

  private void printPuzzle(Boolean solution){
    if(puzzleGrid.length > 0){
      if(solution){
        puzzleToStringSolution();
      }else{
        puzzleToString();
      }
    }else{
      System.out.println("Please generate a puzzle search first before you can print"); 
    }
  } 

  private void generateWordSearch(){
    this.wordBank = new ArrayList<individualLetter[]>(); 
    collectWords(); 
    generateInitialGrid();  
    fillInGrid(); 
    System.out.println(this.wordsFilled + " amount of words have been filled in the grid"); 
    System.out.println("words left behind is a result of maximum grid capacity" 
    + "with regards to the maximum column numbers and chosen words"); 
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
        checkIfGridReal(i, j);
      }
      System.out.println(); 
    }
  } 

  private void checkIfGridReal(int i, int j){
    if(this.puzzleGrid[i][j].getRealValue() != null){
      System.out.print(this.puzzleGrid[i][j].getRealValue().toStringReal() + " ");
    }else{
      System.out.print(this.puzzleGrid[i][j].getFakeValue() + " ");
    }
  } 


  private void puzzleToStringSolution(){
    for(int i = 0; i < this.minGridSize; i ++){
      for (int j = 0; j<this.minGridSize; j++){
        checkIfGridRealSolution(i, j);
      }
      System.out.println(); 
    }
  } 

  private void checkIfGridRealSolution(int i, int j){
    if(this.puzzleGrid[i][j].getRealValue() != null){
      System.out.print(this.puzzleGrid[i][j].getRealValue() + " ");
    }else{
      System.out.print("X ");
    }
  } 

  private void fillInGrid(){
    this.wordsFilled = 0; 
    verticalIntegration integrateVertically = new verticalIntegration(this.minGridSize, this.puzzleGrid); 
    horizontalIntegration integrateHorizontally = new horizontalIntegration(this.minGridSize, this.puzzleGrid); 
    diagonalIntegration integrateDiagonally = new diagonalIntegration(this.minGridSize, this.puzzleGrid); 
    int turn = 0; 
    for(individualLetter[] word : wordBank){
      this.gridIsFilled = false; 
      int incrementCheck = 0; 
      integrationOptions(turn, word, integrateVertically, integrateHorizontally, integrateDiagonally); 
      turn = doAnotherMoveWhileNotFilled(incrementCheck, turn, word, integrateVertically, integrateHorizontally, integrateDiagonally); 
      turn = incrementTurn(turn); 
      if(!this.gridIsFilled && this.minGridSize < this.MAX_GRID_SIZE){
        reInitializeGrid();
        fillInGrid();
        break; 
      }else if (this.gridIsFilled){
        this.wordsFilled++; 
      }; 
    }
  } 

  private int doAnotherMoveWhileNotFilled(int incrementCheck, int turn, individualLetter[] word, verticalIntegration integrateVertically, 
  horizontalIntegration integrateHorizontally, diagonalIntegration integrateDiagonally){
    while(!this.gridIsFilled && incrementCheck < 6){
      incrementCheck++; 
      turn = incrementTurn(turn);
      integrationOptions(turn, word, integrateVertically, integrateHorizontally, integrateDiagonally); 
    }
    return turn; 
  }

  private int incrementTurn(int turn){
    if(turn + 1 >= 6){
      turn = 0;
    }else{
      turn++; 
    }
    return turn; 
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
