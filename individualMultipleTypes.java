public class individualMultipleTypes {

    private individualLetter realLetter;
    private String fakeLetter;
    private String stateColor; 
  
    public individualMultipleTypes(String fakeLetter, individualLetter realLetter, String stateColor){
      this.fakeLetter = fakeLetter; 
      this.realLetter = realLetter;
      this.stateColor = stateColor; 
    } 
    
    public String getFakeValue(){
      return fakeLetter; 
    } 
  
    public individualLetter getRealValue(){
      if(this.stateColor != null)realLetter.changeState(this.stateColor); 
      return realLetter; 
    } 

    public String toString(){
      return fakeLetter; 
    }
    
  
  
}
