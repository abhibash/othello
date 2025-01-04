//Name: Abhi Bashyal
//Purpose: Othello game


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import java.applet.*;



public class Othello extends Applet implements ActionListener
{
    Panel p_card;  //to hold all of the screens
    Panel card1, card2, card3, card4, card5, card6; //the screens
    CardLayout cdLayout = new CardLayout ();
    JLabel turnPic;
    int turn = 1;
    //grid
    int row = 8;
    int col = 8;
    JButton a[] = new JButton [row * col];
    int b[] [] = {{0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 1, 2, 0, 0, 0},
	    {0, 0, 0, 2, 1, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0}};

    // Player Scores
    int PLAYER_1_SCORE = 0;
    int PLAYER_2_SCORE = 0;

    JLabel score1Text, score2Text;


    //Winner and Loser Icons && Text:
    JLabel winnerIcon, winnerDisplay;
    //JLabel winner, loser;

    //Formatting
    Color backgroundColour = new Color (64, 64, 64);
    Color buttonColour = new Color (120, 75, 75);
    Color buttonText = Color.black;
    Color titleColour = Color.black;
    Font titleFont = new Font ("Arial", Font.PLAIN, 30);
    Font promptFont = new Font ("Arial", Font.PLAIN, 20);



    AudioClip soundFile;


    public void init ()
    {
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	// Plays background music
	soundFile = getAudioClip (getDocumentBase (), "themesong.wav");
	soundFile.loop ();

	// Displays intro and intstruction screens
	start ();
	instruction ();
	screen3 ();
	screen4 ();
	screen5 ();
	resize (500, 700);
	setLayout (new BorderLayout ());
	add ("Center", p_card);


    }


    // Intro Screen
    public void start ()
    { // Screen 1 is set up.
	card1 = new Panel ();
	card1.setBackground (new Color (34, 45, 77));
	JButton introScreen = new JButton (createImageIcon ("startScreen.png"));
	introScreen.setActionCommand ("s2");
	introScreen.addActionListener (this);
	introScreen.setContentAreaFilled (false);
	introScreen.setBorderPainted (false);
	introScreen.setBorder (null);
	card1.add (introScreen);
	p_card.add ("1", card1);
    }


    // Instruction Screen
    public void instruction ()
    { // Screen 2 is set up.
	card2 = new Panel ();
	card2.setBackground (new Color (34, 45, 77));
	JButton instructionScreen = new JButton (createImageIcon ("instructionScreen.png"));
	instructionScreen.setActionCommand ("s3");
	instructionScreen.addActionListener (this);
	instructionScreen.setContentAreaFilled (false);
	instructionScreen.setBorderPainted (false);
	instructionScreen.setBorder (null);
	card2.add (instructionScreen);
	p_card.add ("2", card2);
    }


    // Main Game Screen
    public void screen3 ()
    {
	// Create panel for screen 3
	card3 = new Panel ();
	card3.setBackground (backgroundColour);

	// Create title label with image
	JLabel title = new JLabel (createImageIcon ("title.png"));

	// Create reset button with image
	JButton reset = new JButton (createImageIcon ("reset.png"));
	reset.setActionCommand ("reset");
	reset.addActionListener (this);
	reset.setContentAreaFilled (false);
	reset.setBorderPainted (false);
	reset.setBorder (null);

	// Create forfeit button with image
	JButton forfeit = new JButton (createImageIcon ("forfeit.png"));
	forfeit.setActionCommand ("forfeit");
	forfeit.addActionListener (this);
	forfeit.setContentAreaFilled (false);
	forfeit.setBorderPainted (false);
	forfeit.setBorder (null);

	// Create panel for turn information
	Panel q = new Panel ();
	JLabel current = new JLabel (createImageIcon ("turn.png"));
	current.setForeground (new Color (218, 165, 32));
	turnPic = new JLabel (createImageIcon ("1.png"));

	// Create score labels and icons for players
	score1Text = new JLabel (("  " + PLAYER_1_SCORE + "  "));
	score2Text = new JLabel (("  " + PLAYER_2_SCORE + "  "));
	score1Text.setFont (new Font ("Arial", Font.BOLD, 25));
	score2Text.setFont (new Font ("Arial", Font.BOLD, 25));
	score1Text.setForeground (new Color (218, 165, 32));
	score2Text.setForeground (new Color (218, 165, 32));
	JLabel player1ScoreLabel = new JLabel (createImageIcon ("1.png"));
	JLabel player2ScoreLabel = new JLabel (createImageIcon ("2.png"));
	q.add (current);
	q.add (turnPic);

	// Create grid panel for game board
	Panel p = new Panel (new GridLayout (row, col));
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		// Create buttons for each cell in the grid
		a [move] = new JButton (createImageIcon (b [i] [j] + ".png"));
		a [move].setPreferredSize (new Dimension (60, 60));
		a [move].addActionListener (this);
		a [move].setContentAreaFilled (false);
		a [move].setBorderPainted (false);
		a [move].setBorder (null);
		a [move].setActionCommand ("" + move);
		p.add (a [move]);
		move++;
	    }
	}

	// Add components to card3 panel
	card3.add (title);
	card3.add (q);
	card3.add (p);
	card3.add (reset);
	card3.add (forfeit);
	card3.add (player1ScoreLabel);
	card3.add (score1Text);
	card3.add (player2ScoreLabel);
	card3.add (score2Text);
	redraw ();
	p_card.add ("3", card3);
    }


    // WILL NOT APPEAR; -- !!
    public void screen4 ()
    { //screen 4 is set up.
	card4 = new Panel ();
	card4.setBackground (new Color (34, 45, 77));
	JLabel title = new JLabel ("You Win!");
	JButton next = new JButton ("Next");
	next.setActionCommand ("s5");
	next.addActionListener (this);
	card4.add (title);
	card4.add (next);
	p_card.add ("4", card4);
    }


    // Final Outcome Screen
    public void screen5 ()
    { // Set up Screen 5
	card5 = new Panel ();
	card5.setBackground (new Color (34, 45, 77));

	// Set the winner images as blank
	winnerIcon = new JLabel (createImageIcon (" "));
	winnerDisplay = new JLabel (createImageIcon (" "));

	// Back button
	JButton backToIntro = new JButton (createImageIcon ("backToIntro.png"));
	backToIntro.setActionCommand ("s1");
	backToIntro.addActionListener (this);
	backToIntro.setContentAreaFilled (false);
	backToIntro.setBorderPainted (false);
	backToIntro.setBorder (null);

	// Quit game button
	JButton end = new JButton (createImageIcon ("quit.png"));
	end.setActionCommand ("s6");
	end.addActionListener (this);
	end.setContentAreaFilled (false);
	end.setBorderPainted (false);
	end.setBorder (null);




	card5.add (winnerIcon);
	card5.add (winnerDisplay);
	card5.add (backToIntro);
	card5.add (end);

	p_card.add ("5", card5);
    }



    protected static ImageIcon createImageIcon (String path)
    { //change the red to your class name

	java.net.URL imgURL = Othello.class.getResource ("media/"+path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}
	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }


    public void redraw ()
    {
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [move].setIcon (createImageIcon (b [i] [j] + ".png"));
		move++;
	    }
	}
    }


    public void actionPerformed (ActionEvent e)
    { //moves between the screens
	if (e.getActionCommand ().equals ("s1"))
	{
	    cdLayout.show (p_card, "1");
	    reset ();
	}
	else if (e.getActionCommand ().equals ("s2"))
	    cdLayout.show (p_card, "2");
	else if (e.getActionCommand ().equals ("s3"))
	    cdLayout.show (p_card, "3");
	else if (e.getActionCommand ().equals ("s4"))
	    cdLayout.show (p_card, "4");
	else if (e.getActionCommand ().equals ("s5"))
	    cdLayout.show (p_card, "5");
	else if (e.getActionCommand ().equals ("s6"))
	    System.exit (0);
	else if (e.getActionCommand ().equals ("reset"))
	{
	    reset ();
	}
	else if (e.getActionCommand ().equals ("forfeit"))
	{
	    forfeit ();

	}
	else
	{ //code to handle the game
	    int n = Integer.parseInt (e.getActionCommand ());
	    int x = n / col;
	    int y = n % col;
	    showStatus ("" + canGo (x, y));

	    // Turn Method
	    if (canGo (x, y))
	    {
		move (x, y);
		if (turn == 1)
		{
		    turn = 2;
		    turnPic.setIcon (createImageIcon ("2.png"));
		}
		else if (turn == 2)
		{
		    turn = 1;
		    turnPic.setIcon (createImageIcon ("1.png"));
		}
	    }
	    else
		showStatus ("Sorry, You can't move there.");

	}

	redraw ();
    }


    public boolean canGo (int x, int y)
    { //This checks if a turn is valid
	if (b [x] [y] != 0)
	    return false;
	else if (canGoLeft (x, y) == true)
	    return true;
	else if (canGoRight (x, y) == true)
	    return true;
	else if (canGoDown (x, y) == true)
	    return true;
	else if (canGoUp (x, y) == true)
	    return true;
	else if (canGoUpRight (x, y) == true)
	    return true;
	else if (canGoDownLeft (x, y) == true)
	    return true;
	else if (canGoDownRight (x, y) == true)
	    return true;
	else if (canGoUpLeft (x, y) == true)
	    return true;
	else
	    return false;

    }


    // Change pieces
    public void move (int x, int y)
    {
	b [x] [y] = turn;

	if (canGoLeft (x, y))
	{
	    leftSwap (x, y);

	}
	else if (canGoRight (x, y))
	{
	    rightSwap (x, y);

	}
	else if (canGoDown (x, y))
	{
	    downSwap (x, y);
	}
	else if (canGoUp (x, y))
	{
	    upSwap (x, y);
	}
	else if (canGoUpRight (x, y))
	{
	    upRightSwap (x, y);
	}
	else if (canGoUpLeft (x, y))
	{
	    upLeftSwap (x, y);
	}
	else if (canGoDownLeft (x, y))
	{
	    downLeftSwap (x, y);
	}
	else if (canGoDownRight (x, y))
	{
	    downRightSwap (x, y);
	}

	scoreMethod ();
	winCheck ();
    }


    // Can the player go left'
    public boolean canGoLeft (int x, int y)
    {
	int me = turn;
	int them = 1;

	if (turn == 1)
	    them = 2;
	if (y - 1 < 0)
	{
	    return false;
	}
	else if (y - 1 >= 0 && b [x] [y - 1] == 0)
	{
	    return false;
	}
	else if (y - 1 >= 0 && b [x] [y - 1] == me)
	{
	    return false;
	}
	else
	{
	    int ycopy = y - 1;

	    while (ycopy >= 0 && b [x] [ycopy] == them)
	    {
		ycopy--;
	    }

	    if (ycopy < 0)
	    {
		return false;
	    }
	    else if (ycopy >= 0 && b [x] [ycopy] == 0)
	    {
		return false;
	    }
	    else if (ycopy >= 0 && b [x] [ycopy] == me)
	    {

		return true;
	    }
	}
	return false;
    }


    // Can the player go up'
    public boolean canGoUp (int x, int y)
    {
	int me = turn;
	int them = 1;
	if (turn == 1)
	    them = 2;
	// At edge, can't go
	if (x - 1 < 0)
	{
	    return false;
	}
	else if (x - 1 >= 0 && b [x - 1] [y] == 0)
	{
	    return false;
	}
	else if (x - 1 >= 0 && b [x - 1] [y] == me)
	{
	    return false;
	}
	else
	{
	    int xcopy = x - 1;
	    while (xcopy >= 0 && b [xcopy] [y] == them)
	    {
		xcopy--;
	    }
	    if (xcopy < 0)
		return false;
	    else if (xcopy >= 0 && b [xcopy] [y] == 0)
	    {
		return false;
	    }
	    else if (xcopy >= 0 && b [xcopy] [y] == me)
	    {

		return true;
	    }
	}
	return false;
    }


    // Can the player go down'
    public boolean canGoDown (int x, int y)
    {
	int me = turn;
	int them = 1;
	if (turn == 1)
	    them = 2;
	// At edge, can't go
	if (x + 1 >= row)
	{
	    return false;
	}
	else if (x + 1 < row && b [x + 1] [y] == 0)
	{
	    return false;
	}
	else if (x + 1 < row && b [x + 1] [y] == me)
	{
	    return false;
	}
	else
	{
	    int xcopy = x + 1;
	    while (xcopy < row && b [xcopy] [y] == them)
	    {
		xcopy++;
	    }
	    if (xcopy >= row)
		return false;
	    else if (xcopy < row && b [xcopy] [y] == 0)
	    {
		return false;
	    }
	    else if (xcopy < row && b [xcopy] [y] == me)
	    {

		return true;
	    }
	}
	return false;
    }


    // Can the player go right'
    public boolean canGoRight (int x, int y)
    {
	int me = turn;
	int them = 1;
	if (turn == 1)
	    them = 2;
	if (y + 1 >= col)
	{
	    return false;
	}
	else if (y + 1 < col && b [x] [y + 1] == 0)
	{
	    return false;
	}
	else if (y + 1 < col && b [x] [y + 1] == me)
	{
	    return false;
	}
	else
	{
	    int ycopy = y + 1;

	    while (ycopy < col && b [x] [ycopy] == them)
	    {
		ycopy++;
	    }
	    if (ycopy >= col)
	    {
		return false;
	    }
	    else if (ycopy < col && b [x] [ycopy] == 0)
	    {
		return false;
	    }
	    else if (ycopy < col && b [x] [ycopy] == me)
	    {

		return true;
	    }
	}
	return false;
    }


    // Diagonal Check
    // Can the player go up right'
    public boolean canGoUpRight (int x, int y)
    {
	//check if a player can go in (x,y) based on its right side
	int me = turn;
	int them = 1;
	if (turn == 1)
	    them = 2;


	// At edge, can't go
	if (y + 1 >= col || x - 1 < 0)
	{

	    return false;

	}
	// Nothing to up-right, can't go
	else if (y + 1 < col && x - 1 >= 0 && b [x - 1] [y + 1] == 0)
	{

	    return false;
	}
	// My piece to up-right, can't go
	else if (y + 1 < col && x - 1 >= 0 && b [x - 1] [y + 1] == me)
	{

	    return false;
	}

	// Them to left, need to check further left
	else
	{
	    int ycopy = y + 1;
	    int xcopy = x - 1;

	    while (ycopy < col && xcopy >= 0 && b [xcopy] [ycopy] == them)
	    {
		ycopy++;
		xcopy--;

	    }

	    // Them all the way to the edge
	    if (ycopy >= col || xcopy < 0)
	    {

		return false;
	    }
	    // Them all the way to a blank
	    else if (ycopy < col && xcopy >= 0 && b [xcopy] [ycopy] == 0)
	    {

		return false;
	    }
	    // Them all the way to me
	    else if (ycopy < col && xcopy >= 0 && b [xcopy] [ycopy] == me)
	    {

		return true;
	    }
	}

	return false;
    }


    // Can the player go down left'
    public boolean canGoDownLeft (int x, int y)
    {
	//check if a player can go in (x,y) based on its left side
	int me = turn;
	int them = 1;
	if (turn == 1)
	    them = 2;


	// at edge, can't go
	if (y - 1 < 0 || x + 1 >= row)
	{

	    return false;

	}
	// nothing to up-right, can't go
	else if (y - 1 >= 0 && x + 1 < row && b [x + 1] [y - 1] == 0)
	{

	    return false;
	}
	// my piece to up-right, can't go
	else if (y - 1 >= 0 && x + 1 < row && b [x + 1] [y - 1] == me)
	{

	    return false;
	}

	// them to left, need to check further left
	else
	{
	    int ycopy = y - 1;
	    int xcopy = x + 1;

	    while (ycopy >= 0 && xcopy < row && b [xcopy] [ycopy] == them)
	    {
		ycopy--;
		xcopy++;

	    }

	    // them all the way to the edge
	    if (ycopy < 0 || xcopy >= row)
	    {

		return false;
	    }
	    // them all the way to a blank
	    else if (ycopy >= 0 && xcopy < row && b [xcopy] [ycopy] == 0)
	    {

		return false;
	    }
	    // them all the way to me
	    else if (ycopy >= 0 && xcopy < row && b [xcopy] [ycopy] == me)
	    {

		return true;
	    }
	}

	return false;
    }


    // Can the player go up left'
    public boolean canGoUpLeft (int x, int y)
    {
	//check if a player can go in (x,y) based on its left side
	int me = turn;
	int them = 1;
	if (turn == 1)
	    them = 2;


	// at edge, can't go
	if (y - 1 < 0 || x - 1 < 0)
	{

	    return false;

	}
	// nothing to up-right, can't go
	else if (y - 1 >= 0 && x - 1 >= 0 && b [x - 1] [y - 1] == 0)
	{

	    return false;
	}
	// my piece to up-right, can't go
	else if (y - 1 >= 0 && x - 1 >= 0 && b [x - 1] [y - 1] == me)
	{

	    return false;
	}

	// them to left, need to check further left
	else
	{
	    int ycopy = y - 1;
	    int xcopy = x - 1;

	    while (ycopy >= 0 && xcopy >= 0 && b [xcopy] [ycopy] == them)
	    {
		ycopy--;
		xcopy--;

	    }

	    // them all the way to the edge
	    if (ycopy < 0 || xcopy < 0)
	    {

		return false;
	    }
	    // them all the way to a blank
	    else if (ycopy >= 0 && xcopy >= 0 && b [xcopy] [ycopy] == 0)
	    {

		return false;
	    }
	    // them all the way to me
	    else if (ycopy >= 0 && xcopy >= 0 && b [xcopy] [ycopy] == me)
	    {

		return true;
	    }
	}
	return false;
    }


    // Can the player go down right'
    public boolean canGoDownRight (int x, int y)
    {
	//check if a player can go in (x,y) based on its right side
	int me = turn;
	int them = 1;
	if (turn == 1)
	    them = 2;


	// at edge, can't go
	if (y + 1 >= col || x + 1 >= row)
	{

	    return false;

	}
	// nothing to up-right, can't go
	else if (y + 1 < col && x + 1 < row && b [x + 1] [y + 1] == 0)
	{

	    return false;
	}
	// my piece to up-right, can't go
	else if (y + 1 < col && x + 1 < row && b [x + 1] [y + 1] == me)
	{

	    return false;
	}

	// them to left, need to check further left
	else
	{
	    int ycopy = y + 1;
	    int xcopy = x + 1;

	    while (ycopy < col && xcopy < row && b [xcopy] [ycopy] == them)
	    {
		ycopy++;
		xcopy++;

	    }

	    // them all the way to the edge
	    if (ycopy >= col || xcopy >= row)
	    {

		return false;
	    }
	    // them all the way to a blank
	    else if (ycopy < col && xcopy < row && b [xcopy] [ycopy] == 0)
	    {

		return false;
	    }
	    // them all the way to me
	    else if (ycopy < col && xcopy < row && b [xcopy] [ycopy] == me)
	    {

		return true;
	    }
	}
	return false;
    }


    // left swap
    public void leftSwap (int x, int y)
    {
	int me = turn;
	int them = 1;
	if (turn == 1)
	{
	    them = 2;
	}
	int ycopy = y - 1;
	while (ycopy >= 0 && b [x] [ycopy] == them)
	{
	    b [x] [ycopy] = me;
	    ycopy--;
	}
    }


    // right swap
    public void rightSwap (int x, int y)
    {
	int me = turn;
	int them = 1;
	if (turn == 1)
	{
	    them = 2;
	}
	int ycopy = y + 1;
	while (ycopy < col && b [x] [ycopy] == them)
	{
	    b [x] [ycopy] = me;
	    ycopy++;
	}
    }


    //down swap
    public void downSwap (int x, int y)
    {
	int me = turn;
	int them = 1;
	if (turn == 1)
	{
	    them = 2;
	}
	int xcopy = x + 1;
	while (xcopy < row && b [xcopy] [y] == them)
	{
	    b [xcopy] [y] = me;
	    xcopy++;
	}
    }


    //upswap
    public void upSwap (int x, int y)
    {
	int me = turn;
	int them = 1;
	if (turn == 1)
	{
	    them = 2;
	}
	int xcopy = x - 1;
	while (xcopy >= 0 && b [xcopy] [y] == them)
	{
	    b [xcopy] [y] = me;
	    xcopy--;
	}
    }


    //swapDiag
    //up Right Swap
    public void upRightSwap (int x, int y)
    {
	int me = turn;
	int them = 1;
	if (turn == 1)
	    them = 2;

	int ycopy = y + 1;
	int xcopy = x - 1;

	while (ycopy < col && xcopy >= 0 && b [xcopy] [ycopy] == them)
	{

	    b [xcopy] [ycopy] = me;
	    ycopy++;
	    xcopy--;

	}
    }


    //down Left Swap
    public void downLeftSwap (int x, int y)
    {
	int me = turn;
	int them = 1;
	if (turn == 1)
	    them = 2;

	int ycopy = y - 1;
	int xcopy = x + 1;

	while (ycopy >= 0 && xcopy < row && b [xcopy] [ycopy] == them)
	{

	    b [xcopy] [ycopy] = me;
	    ycopy--;
	    xcopy++;

	}
    }


    //up Left Swap
    public void upLeftSwap (int x, int y)
    {
	int me = turn;
	int them = 1;
	if (turn == 1)
	    them = 2;

	int ycopy = y - 1;
	int xcopy = x - 1;

	while (ycopy >= 0 && xcopy >= 0 && b [xcopy] [ycopy] == them)
	{

	    b [xcopy] [ycopy] = me;
	    ycopy--;
	    xcopy--;

	}
    }


    //down Right Swap
    public void downRightSwap (int x, int y)
    {
	int me = turn;
	int them = 1;
	if (turn == 1)
	    them = 2;

	int ycopy = y + 1;
	int xcopy = x + 1;

	while (ycopy < col && xcopy < row && b [xcopy] [ycopy] == them)
	{

	    b [xcopy] [ycopy] = me;
	    ycopy++;
	    xcopy++;

	}
    }


    // Reset method
    public void reset ()
    {
	// Resets board
	int restartBoard[] [] = {{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 1, 2, 0, 0, 0},
		{0, 0, 0, 2, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0}};

	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		b [i] [j] = restartBoard [i] [j];
	    }
	}
	// Reset player scored
	PLAYER_1_SCORE = 0;
	PLAYER_2_SCORE = 0;
	
	// Updates score display
	score1Text.setText (" " + PLAYER_1_SCORE + "  ");
	score2Text.setText (" " + PLAYER_2_SCORE + "  ");
	redraw ();
    }


    // Score Method
    public void scoreMethod ()
    {

	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
	    {
		if (b [i] [j] == 1)
		    PLAYER_1_SCORE++;
		else if (b [i] [j] == 2)
		    PLAYER_2_SCORE++;
	    }
	
	
	
	score1Text.setText (" " + PLAYER_1_SCORE + "  ");
	score2Text.setText (" " + PLAYER_2_SCORE + "  ");
    }


    // Win Method
    public void winCheck ()
    {

	int counter = 0;
	
	// Iterate through the board
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
	    {
		while (b [i] [j] != 0)
		{
		    counter += 1;
		    
		    // Check if all spots on board are filled
		    if (counter == 64)
		    {
			if (PLAYER_1_SCORE > PLAYER_2_SCORE)
			{ // House Stark (wolf) has a higher score
			    JOptionPane.showMessageDialog (null, "House Stark has won!!!", "alert", JOptionPane.ERROR_MESSAGE);
			    winnerIcon.setIcon (createImageIcon ("StarkWinDisplay.png"));
			    winnerDisplay.setIcon (createImageIcon ("starkWin.png"));
			    cdLayout.show (p_card, "5");
			}
			else if (PLAYER_2_SCORE > PLAYER_1_SCORE)
			{ // House Targaryen (dragon) has a higher score
			    JOptionPane.showMessageDialog (null, "House Targaryen has won!!!", "alert", JOptionPane.ERROR_MESSAGE);
			    winnerIcon.setIcon (createImageIcon ("TargWinDisplay.png"));
			    winnerDisplay.setIcon (createImageIcon ("targWin.png"));
			    cdLayout.show (p_card, "5");
			}
			else if (PLAYER_1_SCORE == PLAYER_2_SCORE)
			{ // Players have the same score
			    JOptionPane.showMessageDialog (null, "Tie; Same Score", "alert", JOptionPane.ERROR_MESSAGE);
			    winnerIcon.setIcon (createImageIcon ("tie.png")); // changing to tie image
			    winnerDisplay.setIcon (createImageIcon (" ")); // meant to be empty
			    cdLayout.show (p_card, "5");
			}
		    }
		    break;
		}
	    }
    }


    //Player Forfeits
    public void forfeit ()
    {

	//if House Start (wolf) forfeits
	if (turn == 1)
	{
	    JOptionPane.showMessageDialog (null, "Stark Forefeit - Targaryen Wins!", "alert", JOptionPane.ERROR_MESSAGE);
	    winnerIcon.setIcon (createImageIcon ("TargWinDisplay.png"));
	    winnerDisplay.setIcon (createImageIcon ("targWIn.png"));
	    cdLayout.show (p_card, "5");
	}
	else
	{
	    //If House Targaryen (dragon) forfeits
	    JOptionPane.showMessageDialog (null, "Targaryen Forefeit - Stark Wins", "alert", JOptionPane.ERROR_MESSAGE);
	    winnerIcon.setIcon (createImageIcon ("StarkWinDisplay.png"));
	    winnerDisplay.setIcon (createImageIcon ("starkwin.png"));
	    cdLayout.show (p_card, "5");
	}
	redraw ();
    }
}
