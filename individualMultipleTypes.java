public class individualMultipleTypes {

    private individualLetter realLetter;
    private String fakeLetter;
  
    public individualMultipleTypes(String fakeLetter, individualLetter realLetter){
      this.fakeLetter = fakeLetter; 
      this.realLetter = realLetter;
    } 
    
    public String getFakeValue(){
      return fakeLetter; 
    } 
  
    public individualLetter getRealValue(){
      return realLetter; 
    } 

    public String toString(){
      return fakeLetter; 
    }
    
  
  
}
