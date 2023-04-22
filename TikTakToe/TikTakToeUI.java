import java.util.*;
import java.io.*;

/**
 * A text based user interface to the tiktaktoe game for Homework 01 & 02
 * 
 * @author Dr Mark C. Sinclair
 * @version October 2020
 */
public class TikTakToeUI {
  private Assign computerMove;
  private String display;
  private Stack<Assign> savedStack;

  /**
   * Default constructor
   */
  public TikTakToeUI() {
    scnr   = new Scanner(System.in);
    game   = new TikTakToe();
    player = new Player();
    stack  = new Stack<Assign>();
  }
  
  /**
   * Make the user and computer moves.  The user move is requested from
   * the user, and then the computer player makes its move (if the game
   * has not yet been won or drawn)
   */
  public void move(int row, int col, int num) {
    // user move
    Assign userMove = new Assign(row, col, num);
    if (userMove == null || !game.isValidAssign(userMove)) {
      display = "invalid user move";
      return;
    }
    game.assign(userMove);
    stack.push(userMove);
    trace("stack after userMove: " + stack);
    if (game.isWin() || game.isDraw())
      return;
    // computer move
    Vector<Assign> choices = game.getChoices(TikTakToe.CROSS);
    Assign compMove = player.move(choices);
    game.assign(compMove);
    computerMove = compMove;
    stack.push(compMove);
    trace("stack after compMove: " + stack);
  }
  
  public Assign getCompMove(){
    return computerMove;    
  }
  
  /**
   * Inform the user of a win or draw
   */
  public void checkWin() {
    if (game.isWin()) {
      display = ("Winner is: " + game.winToString());
    } else if (game.isDraw()) {
      display = "Draw";
    } 
  }
  
  public String getDisplay() {
      return display;  
  }
    
  /**
   * Undo the last user and (if necessary) computer move
   */
  public void undo() {
    if (stack.empty())
      return;
    Assign top = stack.pop();              // discard last assign
    if (top.getNum() == TikTakToe.CROSS) { // computer move
      top = stack.pop();                   // discard user move
    }
    Stack<Assign> oldStack = stack;        // otherwise stack will be lost
    savedStack = oldStack;
    clear();
    for (Assign a : oldStack) {            // rebuild puzzle without last user move
      game.assign(a);
      stack.push(a);
    }
  }
  
  /**
   * Restart the game
   */
  public void clear() {
    game  = new TikTakToe();
    stack = new Stack<Assign>();
    display = "Click for Your Move or Load Game !!!";
  }
  
  /**
   * Save the game to a text file (FILENAME)
   */
  public void save() {
    try {
      PrintStream ps = new PrintStream(new File(FILENAME));
      for (Assign a : stack)
        ps.println(a.toStringForFile());
      ps.close();
      display = "Game saved to file";
    } catch (IOException e) {
      display = "An input output error occurred";
    }    
  }
  
  /**
   * Load the game from a text file (FILENAME)
   */
  public void load() {
    try {
      Scanner fscnr = new Scanner(new File(FILENAME));
      clear();
      while (fscnr.hasNextInt()) {
        Assign a = new Assign(fscnr);
        game.assign(a);
        stack.push(a);
      }
      savedStack = stack;
      fscnr.close();
      display = "Game loaded from file";
    } catch (IOException e) {
      display = "An input output error occurred";
    } 
  }
  
  public Stack returnStack() {
      return savedStack;  
  }
  
  /**
   * A trace method for debugging (active when traceOn is true)
   * 
   * @param s the string to output
   */
  public static void trace(String s) {
    if (traceOn)
      System.out.println("trace: " + s);
  }

  private Scanner          scnr   = null;
  private TikTakToe        game   = null;
  private Player           player = null;
  private Stack<Assign>    stack  = null;
  
  private static final String FILENAME = "tiktaktoe.txt"; 
  
  private static boolean   traceOn = false; // for debugging
}
