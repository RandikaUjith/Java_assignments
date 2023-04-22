import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;

/**
 * Main function of this PanelCell class is to create a genaral Button cell for the game
 * which can be used in the TiKtakToePanel class to create the GUI of the game
 * @author (Randika Ujith - w22037955)
 */
public class PanelCell
{
    private JButton btn;

    public PanelCell(){}
    
    public JButton makeButton(String s) {
        btn = new JButton(s);
        btn.setFont(new Font("Arial", Font.PLAIN, 60));
        return btn;
    }

   
}
