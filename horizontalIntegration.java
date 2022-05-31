import java.util.Arrays; 
import java.util.Random; 
public class horizontalIntegration {
  private int minGridSize; 
  private individualMultipleTypes[][] puzzleGrid; 

  public horizontalIntegration(int minGridSize, individualMultipleTypes[][] puzzleGrid){
    this.minGridSize = minGridSize; 
    this.puzzleGrid = puzzleGrid; 
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

  public boolean integrateBackwards(individualLetter[] word){
    reverseWordArray(word, 0, word.length-1);
    return integrate(word); 
  }

  private void reverseWordArray(individualLetter[] word, int leftPointer, int rightPointer){
    while(!(leftPointer >= rightPointer)){
      individualLetter tempWord = word[leftPointer];
      word[leftPointer] = word[rightPointer];
      word[rightPointer] = tempWord; 
      leftPointer++;
      rightPointer--;
    }
  } 
  public boolean integrate(individualLetter[] word){
    int[] rowIndexes = indexesAsArray(); //the row index will be the dynamic one here 
    boolean integrateWithGrid = false; 
    int currentChosenRowIndex = -1; 
    while(!integrateWithGrid){
      if(currentChosenRowIndex < rowIndexes.length-1){
        currentChosenRowIndex++; 
      }else{
        // System.out.println("no place for this word");
        break; 
      }
      integrateWithGrid = horizontalGridCheck(rowIndexes[currentChosenRowIndex], word); 
    }
    if(!integrateWithGrid){
      return false; 
    }else{
      return true; 
    }
  } 


  private boolean horizontalGridCheck(int rowIndex, individualLetter[] word){
    int[] columnIndexes = indexesAsArray();
    int firstColumnIndex = 0; 
    int lengthOfWord = word.length; 
    boolean integratedWithGrid = false; 
    while(!integratedWithGrid){
      if(checkIfWithinBounds(columnIndexes[firstColumnIndex], lengthOfWord)){
        if(checkIfOnlyFakeLetters(columnIndexes[firstColumnIndex], rowIndex, word)){   
          // System.out.println("INTEGRATED!");
          integrateWord(columnIndexes[firstColumnIndex], rowIndex, word); 
          // puzzleToString(); //uncomment this later 
          integratedWithGrid = true; 
        }else{
          if(firstColumnIndex + 1 < columnIndexes.length){
            firstColumnIndex++; 
          }else{
            return false; 
          }
          // System.out.println("moved down one index"); 
        }
      } else{ // mvoe to another row  
        // System.out.println("OUT OF BOUNDS"); 
        // System.out.println("moved to another row"); 
        return false; 
      }
    }
    return true; 
  }

  private boolean checkIfWithinBounds(int firstIndex, int length){
    int lastIndex = firstIndex + length - 1;
    try{
      individualMultipleTypes check = puzzleGrid[0][lastIndex]; 
      return true; 
    } catch(Exception e){
      return false; 
    }
  } 

  private boolean checkIfOnlyFakeLetters(int firstColumnIndex, int rowIndex, individualLetter[] word){
    for(int i = 0; i<word.length; i++, firstColumnIndex++){
      individualMultipleTypes gridBlock = this.puzzleGrid[rowIndex][firstColumnIndex]; 
      if(gridBlock.getRealValue() == null){//check if letter is fake latter 
        continue; 
      }else if(gridBlock.getRealValue().toStringReal().equals(word[i].toString())){
        continue; 
      }else{
        return false; 
      }
    }
    return true; 
  } 

  private void integrateWord(int columnNumber, int rowNumber, individualLetter[] word){
    for(individualLetter i : word){
      this.puzzleGrid[rowNumber][columnNumber] = new individualMultipleTypes(null, i, "h"); 
      columnNumber ++; 
    }
  } 
}

