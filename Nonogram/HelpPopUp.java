import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Dimension;

/**
 * This class is to create a popup window to display a help instructions to the user when user clicks 'Help' button
 */
public class HelpPopUp
{
    private String msg;

    public HelpPopUp()
    {
        this.msg = "Nonogram is a puzzle where you must colour in/fill in the grid according to the patterns of contiguous full cells given in the rows and columns. Full cells are shown in Black colour.\n Unknown cells in White colour with a '?' and cells you are sure as empty are shown in White colour. \n";
        
        UIManager.put("OptionPane.maximumSize",new Dimension(500,200));
        JOptionPane.showMessageDialog(null, msg, "Help !!", JOptionPane.CLOSED_OPTION);
    }    
}
