import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.io.IOException;
import java.util.Stack;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 Purpose of this class is to create Graphhical User Interface for the game with its all the action listeners.
 */
public class NonogramGUI extends JFrame implements ActionListener
{
    private GUIComponents element = new GUIComponents();
    private NonogramUI game;
    private Stack<Assign> stack  = null;              // this stack is used to store the moves
    private ArrayList<NGPattern> colPatArray;        //to store column pattern data imported from NonogramUI class (which is imported from tiny.non file)
    private ArrayList<NGPattern> rowPatArray;         //to store column pattern data imported from NonogramUI class (which is imported from tiny.non file)
    
    private Color color = new Color(0,0,0);             // colour defined to change status of the game (Fill, Empty or Unknown)
    private boolean markUnknown;                        //this variable is to differentiate the Empty state and Unknown state
    
    
    private JPanel displayPanel = new JPanel();                                  // main panels of the GUI
    private JPanel topDataPanel = new JPanel(new GridLayout(1,5));
    private JPanel leftDataPanel = new JPanel(new GridLayout(5,1));
    private JPanel gamePanel = new JPanel(new GridLayout(5, 5));
    private JPanel selectionPanel = new JPanel(new GridLayout(1, 3));
    private JPanel btnPanel = new JPanel(new GridLayout(6, 1));
    private JPanel statusPanel = new JPanel(new BorderLayout());
    
    private JPanel topData0 = new JPanel();                           //panels to display column game data (at the top of gamePanel of GUI)
    private JPanel topData1 = new JPanel();
    private JPanel topData2 = new JPanel();
    private JPanel topData3 = new JPanel();
    private JPanel topData4 = new JPanel();
    
    private JLabel displayLabel = new JLabel("Click on Game Cells to Play !!");      //user ffedback display
    private JLabel statusLabel = new JLabel("Current Status");                      
    private JTextField statusField = new JTextField("");               //to display the current status in status box
    
    
    private JButton btn00 = element.makeButton("");    
    private JButton btn01 = element.makeButton("");                    //creating game cells (buttons)
    private JButton btn02 = element.makeButton("");
    private JButton btn03 = element.makeButton("");
    private JButton btn04 = element.makeButton("");
    private JButton btn10 = element.makeButton("");
    private JButton btn11 = element.makeButton("");
    private JButton btn12 = element.makeButton("");
    private JButton btn13 = element.makeButton("");
    private JButton btn14 = element.makeButton("");   
    private JButton btn20 = element.makeButton("");
    private JButton btn21 = element.makeButton("");
    private JButton btn22 = element.makeButton("");
    private JButton btn23 = element.makeButton("");
    private JButton btn24 = element.makeButton("");
    private JButton btn30 = element.makeButton("");
    private JButton btn31 = element.makeButton("");
    private JButton btn32 = element.makeButton("");
    private JButton btn33 = element.makeButton(""); 
    private JButton btn34 = element.makeButton("");
    private JButton btn40 = element.makeButton("");
    private JButton btn41 = element.makeButton("");
    private JButton btn42 = element.makeButton("");
    private JButton btn43 = element.makeButton("");
    private JButton btn44 = element.makeButton("");
    
    private JButton fill = new JButton("Fill");
    private JButton empty = new JButton("Empty");              //buttons to change game state
    private JButton unknown = new JButton("Unknown ?");
    
    private JButton help = new JButton("Help");
    private JButton clear = new JButton("Clear");
    private JButton undo = new JButton("Undo");
    private JButton save = new JButton("Save");               //buttons for game functions (save, load, clear, quit, undo, help)
    private JButton load = new JButton("Load");
    private JButton quit = new JButton("Quit");
    
    private JTextPane top0 = element.makeTextPane(true);
    private JTextPane top1 = element.makeTextPane(true);      //textPanes to display game data for columns
    private JTextPane top2 = element.makeTextPane(true);
    private JTextPane top3 = element.makeTextPane(true);
    private JTextPane top4 = element.makeTextPane(true);
    
    
    private JTextPane left0 = element.makeTextPane(false);
    private JTextPane left1 = element.makeTextPane(false);       //textPanes to display game data for rows
    private JTextPane left2 = element.makeTextPane(false);
    private JTextPane left3 = element.makeTextPane(false);
    private JTextPane left4 = element.makeTextPane(false);
 
    
    

    /**
     * Constructor for objects of class NonogramGUI
     */
    public NonogramGUI()
    {
        super("Nonogram");
        game = new NonogramUI();
        makeFrame();
        rowPatArray = game.rowPatReturn();            //getting game data for rows (patterns) from NonogramUI class
        colPatArray = game.colPatReturn();           //getting game data for columns (patterns) from NonogramUI class
        
        gameDataAssign(left0, rowPatArray.get(0).getNums(), true);
        gameDataAssign(left1, rowPatArray.get(1).getNums(), true);
        gameDataAssign(left2, rowPatArray.get(2).getNums(), true);    //assigning row game data to text panes
        gameDataAssign(left3, rowPatArray.get(3).getNums(), true);
        gameDataAssign(left4, rowPatArray.get(4).getNums(), true);
        
        gameDataAssign(top0, colPatArray.get(0).getNums(), false);
        gameDataAssign(top1, colPatArray.get(1).getNums(), false);
        gameDataAssign(top2, colPatArray.get(2).getNums(), false);     //assigning column game data to text panes
        gameDataAssign(top3, colPatArray.get(3).getNums(), false);
        gameDataAssign(top4, colPatArray.get(4).getNums(), false);
    }
    
    /**
     * method to assign game data to columns and rows
     */
    private void gameDataAssign(JTextPane tp, int[] nums, boolean s){       
        StringBuilder sb = new StringBuilder();
        for (int i : nums) {
           sb.append(i);
           if(s == true){
               sb.append("  ");
           } else {
               sb.append("\n"); 
           }
        }
        String result = sb.toString();
        tp.setText(result); 
    }
    
    /**
     * //displaying game GUI and adding action listeners to it
     */
    private void makeFrame(){                
        displayPanel.add(displayLabel);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusField.setEditable(false);
        statusField.setBackground(Color.BLACK);
        statusField.setForeground(Color.WHITE);
        statusField.setText("Fill");
        statusField.setFont(new Font("Arial", Font.PLAIN, 20));
        statusField.setHorizontalAlignment(JTextField.CENTER);
        
        gamePanel.add(btn00);
        gamePanel.add(btn01);
        gamePanel.add(btn02);
        gamePanel.add(btn03);
        gamePanel.add(btn04);
        gamePanel.add(btn10);
        gamePanel.add(btn11);
        gamePanel.add(btn12);
        gamePanel.add(btn13);
        gamePanel.add(btn14);
        gamePanel.add(btn20);
        gamePanel.add(btn21);
        gamePanel.add(btn22);
        gamePanel.add(btn23);
        gamePanel.add(btn24);
        gamePanel.add(btn30);
        gamePanel.add(btn31);
        gamePanel.add(btn32);
        gamePanel.add(btn33);
        gamePanel.add(btn34);
        gamePanel.add(btn40);
        gamePanel.add(btn41);
        gamePanel.add(btn42);
        gamePanel.add(btn43);
        gamePanel.add(btn44);
        
        btn00.addActionListener(this);
        btn01.addActionListener(this);
        btn02.addActionListener(this);
        btn03.addActionListener(this);
        btn04.addActionListener(this);
        btn10.addActionListener(this);
        btn11.addActionListener(this);
        btn12.addActionListener(this);
        btn13.addActionListener(this);
        btn14.addActionListener(this);
        btn20.addActionListener(this);
        btn21.addActionListener(this);
        btn22.addActionListener(this);
        btn23.addActionListener(this);
        btn24.addActionListener(this);
        btn30.addActionListener(this);
        btn31.addActionListener(this);
        btn32.addActionListener(this);
        btn33.addActionListener(this);
        btn34.addActionListener(this);
        btn40.addActionListener(this);
        btn41.addActionListener(this);
        btn42.addActionListener(this);
        btn43.addActionListener(this);
        btn44.addActionListener(this);        
        
        topData0.add(top0);
        topData1.add(top1);
        topData2.add(top2);
        topData3.add(top3);
        topData4.add(top4);
        
        topDataPanel.add(topData0);
        topDataPanel.add(topData1);
        topDataPanel.add(topData2);
        topDataPanel.add(topData3);
        topDataPanel.add(topData4);
        
        leftDataPanel.add(left0);
        leftDataPanel.add(left1);
        leftDataPanel.add(left2);
        leftDataPanel.add(left3);
        leftDataPanel.add(left4);
     
        selectionPanel.add(fill);
        selectionPanel.add(empty);
        selectionPanel.add(unknown);
        
        fill.addActionListener(this);
        empty.addActionListener(this);
        unknown.addActionListener(this);
        
        btnPanel.add(help);
        btnPanel.add(clear);
        btnPanel.add(undo);
        btnPanel.add(save);
        btnPanel.add(load);
        btnPanel.add(quit);
        
        help.addActionListener(this);
        clear.addActionListener(this);
        undo.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
        quit.addActionListener(this);
        
        statusPanel.add(statusLabel, BorderLayout.NORTH);
        statusPanel.add(statusField, BorderLayout.CENTER);
        
        displayPanel.setBounds(0, 0, 525, 90);
        topDataPanel.setBounds(100, 100, 250, 100);
        gamePanel.setBounds(100,200, 250, 250);
        leftDataPanel.setBounds(0, 200, 100, 250);
        selectionPanel.setBounds(0, 475, 350, 50);
        btnPanel.setBounds(375, 100, 150, 200);
        statusPanel.setBounds(375, 325, 150, 125);
        
        add(displayPanel);
        add(topDataPanel);
        add(gamePanel);
        add(leftDataPanel);
        add(selectionPanel);
        add(btnPanel);
        add(statusPanel);
        
        setLayout(null);
        setSize(550,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
    }
    
    /**
     * //adding all action listeners
     */
    public void actionPerformed(ActionEvent evt){         
        if(evt.getSource() == btn00){
            getAction(btn00, 0, 0);
        } else if(evt.getSource() == btn01){
            getAction(btn01, 0, 1);
        } else if(evt.getSource() == btn02){
            getAction(btn02, 0, 2);
        } else if(evt.getSource() == btn03){
            getAction(btn03, 0, 3);
        } else if(evt.getSource() == btn04){
            getAction(btn04, 0, 4);
        } else if(evt.getSource() == btn10){
            getAction(btn10, 1, 0);
        } else if(evt.getSource() == btn11){
            getAction(btn11, 1, 1);
        } else if(evt.getSource() == btn12){
            getAction(btn12, 1, 2);
        } else if(evt.getSource() == btn13){
            getAction(btn13, 1, 3);
        } else if(evt.getSource() == btn14){
            getAction(btn14, 1, 4);
        } else if(evt.getSource() == btn20){
            getAction(btn20, 2, 0);
        } else if(evt.getSource() == btn21){
            getAction(btn21, 2, 1);
        } else if(evt.getSource() == btn22){
            getAction(btn22, 2, 2);
        } else if(evt.getSource() == btn23){
            getAction(btn23, 2, 3);
        } else if(evt.getSource() == btn24){
            getAction(btn24, 2, 4);
        } else if(evt.getSource() == btn30){
            getAction(btn30, 3, 0);
        } else if(evt.getSource() == btn31){
            getAction(btn31, 3, 1);
        } else if(evt.getSource() == btn32){
            getAction(btn32, 3, 2);
        } else if(evt.getSource() == btn33){
            getAction(btn33, 3, 3);
        } else if(evt.getSource() == btn34){
            getAction(btn34, 3, 4);
        } else if(evt.getSource() == btn40){
            getAction(btn40, 4, 0);
        } else if(evt.getSource() == btn41){
            getAction(btn41, 4, 1);
        } else if(evt.getSource() == btn42){
            getAction(btn42, 4, 2);
        } else if(evt.getSource() == btn43){
            getAction(btn43, 4, 3);
        } else if(evt.getSource() == btn44){
            getAction(btn44, 4, 4);
        } else if(evt.getSource() == fill){
            color = new Color(0, 0, 0);
            markUnknown = false;
            statusField.setBackground(Color.BLACK);
            statusField.setForeground(Color.WHITE);
            statusField.setText("Fill");
        } else if(evt.getSource() == empty){
            color = new Color(255, 255, 255);
            markUnknown = false;
            statusField.setBackground(Color.WHITE);
            statusField.setForeground(Color.BLACK);
            statusField.setText("EMPTY");
        } else if(evt.getSource() == unknown){
            color = new Color(255, 255, 255);
            markUnknown = true;
            statusField.setBackground(Color.WHITE);
            statusField.setForeground(Color.BLACK);
            statusField.setText("Unknown ?");
        } else if(evt.getSource() == help){
            HelpPopUp msg = new HelpPopUp();
        } else if(evt.getSource() == clear){
            clear();
            game.clear();
            gameStatus();
            displayLabel.setText("Click on Game Cells to Play !!");
        } else if(evt.getSource() == undo){
            game.undo();
            clear();
            load();
            gameStatus();
        } else if(evt.getSource() == save){
            game.save();
            displayLabel.setText("Game Saved !!");
        } else if(evt.getSource() == load){
            clear();
            game.load();
            load();
            displayLabel.setText("Game Loaded from File !!");
            gameStatus();
        } else if(evt.getSource() == quit){
            System.exit(0);
        } 

    }
    
    /**
     * this method changes the colour and text of game cells according to the current status of the game and
     * pass the moves to move() method in NonogramUI class
     */
    private void getAction(JButton btn, int i, int j){          
        if(btn.getBackground().equals(new Color(211, 211, 211))){    
            btn.setBackground(color);
            if(color.equals(new Color(0, 0, 0))){
                Assign move = new Assign(i , j, 1);
                game.move(move);
            } else if(color.equals(new Color(255, 255, 255))){
                if(markUnknown == false){
                    Assign move = new Assign(i , j, 0);
                    game.move(move);
                } else if(markUnknown == true){
                    Assign move = new Assign(i , j, 2);
                    btn.setText("?");
                    btn.setForeground(Color.BLACK);
                    game.move(move);
                }   
            }
        }
        gameStatus();
    }
    
    /**
     * this method loads the game to all the game cells from the game saved data loaded from file
     */
    private void load(){
        stack = game.stackReturn();
        for(Assign a : stack){
            loadMove(a.getRow(), a.getCol(), a.getState());
        }
        gameStatus();
    }
    
    /**
     * this method triggers the saved cells and change their status as per the saved data 
     */
    private void loadMove(int row, int col, int state){
        if(row == 0 && col == 0){
            btnSetState(btn00, state);    
        }else if(row == 0 && col == 1){
            btnSetState(btn01, state);
        } else if(row == 0 && col == 2){
            btnSetState(btn02, state);
        } else if(row == 0 && col == 3){
            btnSetState(btn03, state);
        } else if(row == 0 && col == 4){
            btnSetState(btn04, state);
        } else if(row == 1 && col == 0){
            btnSetState(btn10, state);
        } else if(row == 1 && col == 1){
            btnSetState(btn11, state);
        } else if(row == 1 && col == 2){
            btnSetState(btn12, state);
        } else if(row == 1 && col == 3){
            btnSetState(btn13, state);
        } else if(row == 1 && col == 4){
            btnSetState(btn14, state);
        } else if(row == 2 && col == 0){
            btnSetState(btn20, state);
        } else if(row == 2 && col == 1){
            btnSetState(btn21, state);
        } else if(row == 2 && col == 2){
            btnSetState(btn22, state);
        } else if(row == 2 && col == 3){
            btnSetState(btn23, state);
        } else if(row == 2 && col == 4){
            btnSetState(btn24, state);
        } else if(row == 3 && col == 0){
            btnSetState(btn30, state);
        } else if(row == 3 && col == 1){
            btnSetState(btn31, state);
        } else if(row == 3 && col == 2){
            btnSetState(btn32, state);
        } else if(row == 3 && col == 3){
            btnSetState(btn33, state);
        } else if(row == 3 && col == 4){
            btnSetState(btn34, state);
        } else if(row == 4 && col == 0){
            btnSetState(btn40, state);
        } else if(row == 4 && col == 1){
            btnSetState(btn41, state);
        } else if(row == 4 && col == 2){
            btnSetState(btn42, state);
        } else if(row == 4 && col == 3){
            btnSetState(btn43, state);
        } else if(row == 4 && col == 4){
            btnSetState(btn44, state);
        } 
    }
    
    /**
     * this method chages the color and text of a given cell according to a given status
     */
    private void btnSetState(JButton btn, int state){
        if(state == 0){
            btn.setBackground(new Color(255, 255, 255));
        } else if(state == 1){
            btn.setBackground(new Color(0, 0, 0));
        } else if(state == 2){
            btn.setBackground(new Color(255, 255, 255));
            btn.setText("?");
        }
    }
    
    /**
     * this method clears the status of all game cells
     */
    private void clear(){
        resetCell(btn00);
        resetCell(btn01);
        resetCell(btn02);
        resetCell(btn03);
        resetCell(btn04);
        resetCell(btn10);
        resetCell(btn11);
        resetCell(btn12);
        resetCell(btn13);
        resetCell(btn14);
        resetCell(btn20);
        resetCell(btn21);
        resetCell(btn22);
        resetCell(btn23);
        resetCell(btn24);
        resetCell(btn30);
        resetCell(btn31);
        resetCell(btn32);
        resetCell(btn33);
        resetCell(btn34);
        resetCell(btn40);
        resetCell(btn41);
        resetCell(btn42);
        resetCell(btn43);
        resetCell(btn44);
    }
    
    /**
     * this method clears the status of a given game cell
     */
    private void resetCell(JButton btn){
        btn.setBackground(new Color(211, 211, 211));
        btn.setText("");
    }
    
    /**
     * this method checks win status of the game and displays it in the Display text
     */
    private void gameStatus(){
        rowColStatusAll();
        if(game.isWin() == true){
            displayLabel.setText("Puzzle is Solved !!");
        }
    }
    
    /**
     * this method chages the colour of all game pattern data columns and rows
     */
    private void rowColStatusAll(){
        rowColStatus(true, 0, left0);
        rowColStatus(true, 1, left1);
        rowColStatus(true, 2, left2);
        rowColStatus(true, 3, left3);
        rowColStatus(true, 4, left4);
        rowColStatus(false, 0, top0);
        rowColStatus(false, 1, top1);
        rowColStatus(false, 2, top2);
        rowColStatus(false, 3, top3);
        rowColStatus(false, 4, top4);
    }
    
    /**
     * this method colour of a given game pattern data row or column according to the column or row is solved or not
     * value for solved or not is imported from alertChar() method in NonogramUI class
     */
    private void rowColStatus(boolean isRow, int idx, JTextPane pane){
        char i = game.alertChar(isRow, idx);
        if(i == '*'){
            pane.setBackground(Color.ORANGE);
            displayLabel.setText("Hooray...!!");
        } else if(i == '?'){
            pane.setBackground(Color.RED);
            displayLabel.setText("Ooh !!");
        } else {
            pane.setBackground(Color.WHITE);
        }
    }
    
    /**
     * main method to run the game
     */
    public static void main(String[] args) {
        NonogramGUI ui = new NonogramGUI();
    }
}



