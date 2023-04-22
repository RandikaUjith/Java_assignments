import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.*;

/**
 * This class is to create some GUI components with their attributes
 */
public class GUIComponents
{
    private JButton btn;
    private JTextPane tp;

    /**
     * Constructor for objects of class GUIComponents
     */
    public GUIComponents()
    {
    }
    
    /**
     * Creating the game cell using JButton and assigning required attributes to it
     */
    public JButton makeButton(String s) {
        btn = new JButton(s);
        btn.setPreferredSize(new Dimension(50, 50));
        btn.setFont(new Font("Arial", Font.PLAIN, 20));
        btn.setBackground(new Color(211, 211, 211));
        return btn;
    }
    
    /**
     * Creating a display area to display game pattern data for rows and columns using JTextPane and assigning required attributes to it
     */
    public JTextPane makeTextPane(boolean s){
        tp = new JTextPane();
        tp.setFont(new Font("Arial", Font.PLAIN, 20));
        tp.setEditable(false);
        StyledDocument doc = tp.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        if(s == true){
            tp.setPreferredSize(new Dimension(50, 100));
        } else {
            tp.setPreferredSize(new Dimension(100, 50));
        }
        return tp;
    }
}
