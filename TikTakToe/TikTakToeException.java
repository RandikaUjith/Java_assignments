 

/**
 * A program-specific exception for tiktaktoe for Homework 01 & 02.
 * 
 * @author Dr Mark C. Sinclair
 * @version October 2020
 */
@SuppressWarnings("serial")
public class TikTakToeException extends RuntimeException {
  /**
   * Default constructor
   */
  TikTakToeException() {}
  
  /**
   * Constructor with a message
   * 
   * @param message the message
   */
  TikTakToeException(String message) {
    super(message);
  }
}
