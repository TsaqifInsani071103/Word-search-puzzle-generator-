public class individualLetter {
  private String letter; 
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_RESET = "\u001B[0m";

  public individualLetter(String letter){
    this.letter = letter; 
  }

  public String toString(){
    return this.letter; 
  }

  public String toStringHorizontal(){
    return ANSI_GREEN + this.letter + ANSI_RESET; 
  } 

  public String toStringVertical(){
    return ANSI_RED + this.letter + ANSI_RESET; 
  } 
}
