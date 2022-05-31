public class individualLetter {
  private String letter; 
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_RESET = "\u001B[0m";
  private String stateColor = ""; 

  public individualLetter(String letter){
    this.letter = letter; 
  }

  public String toString(){
    if(this.stateColor.equals("h")) return toStringHorizontal();
    if(this.stateColor.equals("v")) return toStringVertical();
    return this.letter; 
  }

  private String toStringHorizontal(){
    return ANSI_GREEN + this.letter + ANSI_RESET; 
  } 

  private String toStringVertical(){
    return ANSI_RED + this.letter + ANSI_RESET; 
  } 

  protected void changeState(String stateColor){
    this.stateColor = stateColor; 
  }
}
