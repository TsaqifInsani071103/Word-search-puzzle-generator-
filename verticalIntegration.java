import java.util.Arrays; 
import java.util.Random; 
public class verticalIntegration {
  private int minGridSize; 
  private individualMultipleTypes[][] puzzleGrid; 

  public verticalIntegration(int minGridSize, individualMultipleTypes[][] puzzleGrid){
    this.minGridSize = minGridSize; 
    this.puzzleGrid = puzzleGrid; 
  } 

  //DELETE THIS LATER WHEN YOU'RE DONE CHECKING EVERYTHING 
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


  public void integrate(int[] columnIndexes, individualLetter[] word){
    boolean integrateWithGrid = false; 
    int currentChosencolumnIndex = -1; 
    while(!integrateWithGrid){
      //check if at index, to bottom, matches appropriate length 
      //if so, check if contains only fake letters, if so integrate
      //if not, go down. 
      if(currentChosencolumnIndex < columnIndexes.length -1){
        currentChosencolumnIndex++; 
      }else{
        System.out.println("no place for this word");
        break; 
      }
      integrateWithGrid = verticalGridCheck(columnIndexes[currentChosencolumnIndex], word); 
    }

} 

  private boolean verticalGridCheck(int columnIndex, individualLetter[] word){
    int[] rowIndexes = indexesAsArray();
    int firstRowIndex = 0; 
    int lengthOfWord = word.length; 
    boolean integratedWithGrid = false; 
    while(!integratedWithGrid){
      if(checkIfWithinBounds(rowIndexes[firstRowIndex], lengthOfWord)){
        if(checkIfOnlyFakeLetters(rowIndexes[firstRowIndex], columnIndex, word)){   
          System.out.println("INTEGRATED!");
          integrateWord(rowIndexes[firstRowIndex], columnIndex, word); 
          puzzleToString(); 
          integratedWithGrid = true; 
        }else{
          firstRowIndex++; 
          System.out.println("moved down one index"); 
        }
      } else{ // if row index array hasnt finished yet, change firstRow Index, else, move to another column 
        System.out.println("OUT OF BOUNDS"); 
        System.out.println("moved to another column"); 
        return false; 
      }
    }
    return true; 
  }

  private boolean checkIfWithinBounds(int firstIndex, int length){
    int lastIndex = firstIndex + length - 1;
    try{
      individualMultipleTypes[] check = puzzleGrid[lastIndex]; 
      return true; 
    } catch(Exception e){
      return false; 
    }
  } 

  private boolean checkIfOnlyFakeLetters(int firstRowIndex, int columnIndex, individualLetter[] word){
    for(int i = 0; i<word.length; i++, firstRowIndex++){
      individualMultipleTypes gridBlock = this.puzzleGrid[firstRowIndex][columnIndex]; 
      if(gridBlock.getRealValue() == null){//check if letter is fake latter 
        continue; 
      }else if(gridBlock.getRealValue().toString().equals(word[i].toString())){
        System.out.println("EQUAL"); 
        continue; 
      }else{
        return false; 
      }
    }
    return true; 
  } 

  private void integrateWord(int rowNumber, int columnNumber, individualLetter[] word){
    for(individualLetter i : word){
      this.puzzleGrid[rowNumber][columnNumber] = new individualMultipleTypes(null, i); 
      rowNumber ++; 
    }
  } 
}