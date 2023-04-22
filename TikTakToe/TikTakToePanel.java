import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.JLabel;
import java.util.Stack;

/**
 * This class is for creating the GUI for the game and implement all EventListeners of it.
 *
 * @author Randika Ujith - w22037955
 */
public class TikTakToePanel extends JFrame implements ActionListener
{
    private JPanel gamePanel;
    private JPanel labPanel;
    private JPanel btnPanel;
    private PanelCell cell = new PanelCell();
    private TikTakToeUI ui = new TikTakToeUI();
    private String display;                     // this variable is to do changes to the top JLabel
    private Stack<Assign> savedStack;
    
    private JLabel topLabel; //initiating top display
    
    private JButton btn11;   //initiating game cells
    private JButton btn12;
    private JButton btn13;
    private JButton btn21;
    private JButton btn22;
    private JButton btn23;
    private JButton btn31;
    private JButton btn32;
    private JButton btn33;
    
    private JButton clearBtn; // initiating action buttons
    private JButton undoBtn;
    private JButton saveBtn;
    private JButton loadBtn;
    private JButton quitBtn;
    
    /**
     * Constructor for objects of class TikTakToePanel
     */
    private TikTakToePanel()
    {
        super("TikTakToe");
        makeFrame();
    }
    /**
     * Create GUI 
     */
    private void makeFrame() {
        setLayout(new BorderLayout());
        labPanel = new JPanel();
        gamePanel = new JPanel(new GridLayout(3, 3));
        btnPanel = new JPanel();
        
        topLabel = new JLabel("Click for Your Move !!!");
        topLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        labPanel.add(topLabel);
        
        btn11 = cell.makeButton("");
        btn12 = cell.makeButton("");
        btn13 = cell.makeButton("");
        btn21 = cell.makeButton("");
        btn22 = cell.makeButton("");
        btn23 = cell.makeButton("");
        btn31 = cell.makeButton("");
        btn32 = cell.makeButton("");
        btn33 = cell.makeButton("");
        
        gamePanel.add(btn11);
        gamePanel.add(btn12);
        gamePanel.add(btn13);
        gamePanel.add(btn21);
        gamePanel.add(btn22);
        gamePanel.add(btn23);
        gamePanel.add(btn31);
        gamePanel.add(btn32);
        gamePanel.add(btn33);
        
        btn11.addActionListener(this);
        btn12.addActionListener(this);
        btn13.addActionListener(this);
        btn21.addActionListener(this);
        btn22.addActionListener(this);
        btn23.addActionListener(this);
        btn31.addActionListener(this);
        btn32.addActionListener(this);
        btn33.addActionListener(this);
        
        clearBtn = new JButton("Clear Game");
        undoBtn = new JButton("Undo Move");
        saveBtn = new JButton("Save Game");
        loadBtn = new JButton("Load Game");
        quitBtn = new JButton("Quit Game");
        
        clearBtn.addActionListener(this);
        undoBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        loadBtn.addActionListener(this);
        quitBtn.addActionListener(this);
        
        btnPanel.add(undoBtn);
        btnPanel.add(saveBtn);
        btnPanel.add(loadBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(quitBtn);
        
        add(labPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        setSize(600,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    }
    /**
     * Add Event Listeners for all Game panel buttons (game cells) and action buttons (save, load, clear, undo, quit)
     */    
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == btn11){
            getAction(evt, btn11, 1, 1);
        } else if(evt.getSource() == btn12){
            getAction(evt, btn12, 1, 2);
        } else if(evt.getSource() == btn13){
            getAction(evt, btn13, 1, 3);
        } else if(evt.getSource() == btn21){
            getAction(evt, btn21, 2, 1);
        } else if(evt.getSource() == btn22){
            getAction(evt, btn22, 2, 2);
        } else if(evt.getSource() == btn23){
            getAction(evt, btn23, 2, 3);
        } else if(evt.getSource() == btn31){
            getAction(evt, btn31, 3, 1);
        } else if(evt.getSource() == btn32){
            getAction(evt, btn32, 3, 2);
        } else if(evt.getSource() == btn33){
            getAction(evt,btn33, 3, 3);
        } else if(evt.getSource() == clearBtn){
            clearPanel();
            ui.clear();
            labelDisplay();
        } else if(evt.getSource() == undoBtn){
            clearPanel();
            ui.undo();
            loadGame();
        } else if(evt.getSource() == saveBtn){
            ui.save();
            labelDisplay();
        } else if(evt.getSource() == loadBtn){
            clearPanel();
            ui.load();
            loadGame();
            labelDisplay();
        } else if(evt.getSource() == quitBtn){
            System.exit(0);
        }         
    }
    /**
     * Functions of this method are
     *      appear "O" on game cell with a click on it by the user and transfer the coordinate of it to the move() method of TikTakToeUI class to store
     *      it in the stack.
     *      get coordinate of the computer move from TikTakToeUI class and implement it on game cells
     *      check weather the game is win/draw after the move
     *      change the JLabel at the top if the game is win or draw
     */
    private void getAction(ActionEvent evt, JButton btn, int i, int j){
        if(evt.getActionCommand().equals("")){
                btn.setText("O");               
                ui.move(i, j, 0);
                Assign compMove = ui.getCompMove();
                loadMove(compMove.getRow(), compMove.getCol(), "X");
                ui.checkWin();
                labelDisplay();
            }
    }
    /**
     * Change texts of of a given game cell
     * row and col for the position and s is the text which need to appear on tha game cell
     */
    private void loadMove(int row, int col, String s){       
        if(row == 1 && col == 1){
            btn11.setText(s);
        }else if(row == 1 && col == 2){
            btn12.setText(s);
        }else if(row == 1 && col == 3){
            btn13.setText(s);
        }else if(row == 2 && col == 1){
            btn21.setText(s);
        }else if(row == 2 && col == 2){
            btn22.setText(s);
        }else if(row == 2 && col == 3){
            btn23.setText(s);
        }else if(row == 3 && col == 1){
            btn31.setText(s);
        }else if(row == 3 && col == 2){
            btn32.setText(s);
        }else if(row == 3 && col == 3){
            btn33.setText(s);
        }
    }
    /**
     * clear the game panel by make the texts of all game cells to ""
     */
    private void clearPanel() {
        btn11.setText("");
        btn12.setText("");
        btn13.setText("");
        btn21.setText("");
        btn22.setText("");
        btn23.setText("");
        btn31.setText("");
        btn32.setText("");
        btn33.setText("");
    }
    /**
     * load the game on game cells according the current stack return from TikTakToeUI class
     */
    private void loadGame() {
        savedStack = ui.returnStack();
        for (Assign a : savedStack) {
          if(a.getNum() == 0){
              loadMove(a.getRow(), a.getCol(), "O");
          } else {
              loadMove(a.getRow(), a.getCol(), "X");  
          }
          
        }
    }
    /**
     * Change top JLabel according to the value returned by TikTakToeUI class. (All the game statements which were printed on the terminal)
     */
    private void labelDisplay() {
        display = ui.getDisplay();
        if(display != null){
            topLabel.setText(display);
        }
    }
    
    public static void main(String[] args) {
    TikTakToePanel TTT = new TikTakToePanel();
    
  }
}
