 

import java.util.*;

/**
 * A tiktaktoe player for Homework 01 & 02
 * 
 * The player simply makes a random choice from available moves, with no knowledge of the board.
 * 
 * @author Dr Mark C. Sinclair
 * @version October 2020
 */
public class Player {
  /**
   * Default constructor
   */
  public Player() {
    rnd = new Random();
  }
  
  /**
   * Select a move at random from the supplied list of possibilities.
   * 
   * @param choices a vector of possible moves
   * @return the selected move
   */
  public Assign move(Vector<Assign> choices) {
     if (choices == null)
       throw new TikTakToeException("cannot have null choices");
     if (choices.isEmpty())
       throw new TikTakToeException("cannot have empty choices");
     int size = choices.size();
     int idx  = rnd.nextInt(size);
     return choices.get(idx);
  }
  
  private Random rnd = null;
}
