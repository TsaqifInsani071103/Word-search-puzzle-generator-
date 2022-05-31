import java.util.Arrays; 
import java.util.Random;
public class diagonalIntegration {
  private int minGridSize; 
  private individualMultipleTypes[][] puzzleGrid; 

  public diagonalIntegration(int minGridSize, individualMultipleTypes[][] puzzleGrid){
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
      integrateWithGrid = diagonalGridCheck(rowIndexes[currentChosenRowIndex], word); 
    }
    if(!integrateWithGrid){
      return false; 
    }else{
      return true; 
    }
  } 


  private boolean diagonalGridCheck(int rowIndex, individualLetter[] word){
    int[] columnIndexes = indexesAsArray();
    int firstColumnIndex = 0; 
    int lengthOfWord = word.length; 
    boolean integratedWithGrid = false; 
    while(!integratedWithGrid){
      if(ifRowWithinBounds(rowIndex, lengthOfWord)){
        if(ifForwardColumnWithinBounds(columnIndexes[firstColumnIndex], lengthOfWord)){
          if(checkIfOnlyFakeLettersForward(columnIndexes[firstColumnIndex], rowIndex, word)){   
            // System.out.println("INTEGRATED!");
            integrateWordForward(columnIndexes[firstColumnIndex], rowIndex, word); 
            integratedWithGrid = true; 
          }else{
            if(firstColumnIndex + 1 < columnIndexes.length){
              firstColumnIndex++; 
            }else{
              return false; 
            }
          }
        }else if(ifBackwardColumnWithinBounds(columnIndexes[firstColumnIndex], lengthOfWord)){
          if(checkIfOnlyFakeLettersBackward(columnIndexes[firstColumnIndex], rowIndex, word)){
            // System.out.println("INTEGRATED!");
            integrateWordBackward(columnIndexes[firstColumnIndex], rowIndex, word); 
            integratedWithGrid = true;
          }else{
            if(firstColumnIndex + 1 < columnIndexes.length){
              firstColumnIndex++; 
            }else{
              return false; 
            }
          }
        }else{
          if(firstColumnIndex + 1 < columnIndexes.length){
            firstColumnIndex++; 
          }else{
            return false; 
          } 
        }
      } else{ 
        return false; 
      }
    }
    return true; 
  }

  private boolean ifForwardColumnWithinBounds(int firstIndex, int length){
    if((this.minGridSize - firstIndex) >= length){
      return true; 
    }
    return false; 
  } 
  private boolean ifBackwardColumnWithinBounds(int firstIndex, int length){
    if((firstIndex + 1) >= length){
      return true; 
    }
    return false; 
  } 

  private boolean ifRowWithinBounds(int rowIndex, int length){
    if(this.minGridSize-rowIndex >= length){
      return true; 
    }
    return false; 
  }

  private boolean checkIfOnlyFakeLettersForward(int firstColumnIndex, int rowIndex, individualLetter[] word){
    for(int i = 0; i < word.length; i++, firstColumnIndex++, rowIndex++){
      // System.out.println("ROW: " + rowIndex);
      // System.out.println("COLUMN: " + firstColumnIndex); 
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
  private boolean checkIfOnlyFakeLettersBackward(int firstColumnIndex, int rowIndex, individualLetter[] word){
    for(int i = 0; i < word.length; i++, firstColumnIndex--, rowIndex++){
      // System.out.println("ROW: " + rowIndex);
      // System.out.println("COLUMN: " + firstColumnIndex); 
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

  private void integrateWordForward(int columnNumber, int rowNumber, individualLetter[] word){
    for(individualLetter i : word){
      this.puzzleGrid[rowNumber][columnNumber] = new individualMultipleTypes(null, i, "d"); 
      columnNumber++; 
      rowNumber++; 
    }
  } 
  private void integrateWordBackward(int columnNumber, int rowNumber, individualLetter[] word){
    for(individualLetter i : word){
      this.puzzleGrid[rowNumber][columnNumber] = new individualMultipleTypes(null, i, "d"); 
      columnNumber--; 
      rowNumber++; 
    }
  } 
}
