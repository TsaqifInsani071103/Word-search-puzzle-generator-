import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.Random; 
import java.util.Arrays; 
public class puzzleSearchGame {
  private ArrayList<individualLetter[]> wordBank = new ArrayList<individualLetter[]>(); 
  private final String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", 
  "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}; 
  private int minGridSize = 5; //10 by 10 square 
  private final int MAX_GRID_SIZE = 25; //30 by 30 square 
  private individualMultipleTypes[][] puzzleGrid = {}; 
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
    for(individualLetter[] i : this.wordBank){
      System.out.println(Arrays.toString(i));
    }
    System.out.println(this.minGridSize); 
    generateInitialGrid(); 
    puzzleToString();  
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
        // puzzleRow[j] = this.ALPHABET[randomObject.nextInt(26)]; // uncomment this later 
        puzzleRow[j] = new individualMultipleTypes("X", null);
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
    // if (this.wordBank.get(0)[0] instanceof individualLetter){
    //   System.out.println("POOP!"); 
    // } instanceof individualLetter works to check if letter is a letter object 
    int[] columnIndexes = indexesAsArray(); //random column index 
    System.out.println(Arrays.toString(columnIndexes)); 
    //check if verticalSubgrid is appropriate length, if not, move to the next column 
    //if it is, and only fake letters, put it down 
    // if it is, and there is real letters, check if the real letters are in the appropriate index in the word, if not, move down 
    //repeat 
    for(individualLetter[] word : wordBank){
      verticalIntegration integrateVertically = new verticalIntegration(this.minGridSize, this.puzzleGrid); 
      integrateVertically.integrate(columnIndexes, word); 
    }
    
  } 


  //random vertical index 
  private int[] indexesAsArray(){
    int[] indexes = new int[this.minGridSize];
    Arrays.fill(indexes, -1000); 
    int indexIncrement = 0; 
    while(indexIncrement < this.minGridSize){
      int randomIndex = uniqueIndex(indexes); 
      indexes[indexIncrement] = randomIndex; 
      indexIncrement++;
    }
    return indexes; 
  } 

  private int uniqueIndex(int[] indexArray){
    Random randomObject = new Random(); 
    int randomUniqueIndex = randomObject.nextInt(this.minGridSize); 
    while (arrayContainsNumber(indexArray, randomUniqueIndex)){  
      randomUniqueIndex = randomObject.nextInt(this.minGridSize); 
    }
    return randomUniqueIndex; 
  } 

  private boolean arrayContainsNumber(int[] indexArray, int randomUniqueIndex){
    for(int i : indexArray){
      if(i == randomUniqueIndex) return true; 
    }
    return false; 
  }

  private void integrateWord(int rowNumber, int columnNumber, individualLetter[] word){
    for(individualLetter i : word){
      this.puzzleGrid[rowNumber][columnNumber] = new individualMultipleTypes(null, i); 
      rowNumber ++; 
    }
  } 
}
