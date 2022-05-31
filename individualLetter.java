public class individualLetter {
  private String letter; 
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  private String stateColor = ""; 

  public individualLetter(String letter){
    this.letter = letter; 
  }

  public String toString(){
    if(this.stateColor.equals("h")) return toStringHorizontal();
    if(this.stateColor.equals("v")) return toStringVertical();
    if(this.stateColor.equals("d")) return toStringDiagonal();
    return this.letter; 
  }

  public String toStringReal(){
    return this.letter; 
  }

  private String toStringHorizontal(){
    return ANSI_GREEN + this.letter + ANSI_RESET; 
  } 

  private String toStringVertical(){
    return ANSI_RED + this.letter + ANSI_RESET; 
  } 
  private String toStringDiagonal(){
    return ANSI_YELLOW + this.letter + ANSI_RESET; 
  } 

  protected void changeState(String stateColor){
    this.stateColor = stateColor; 
  }
}
