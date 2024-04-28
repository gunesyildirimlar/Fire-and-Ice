import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class FireandIce {
    static enigma.console.Console cn = Enigma.getConsole("Hallo", 100, 23, 25, 6);
    
    public KeyListener klis;

	// ------ Standard variables for keyboard ------
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)
	char[][] maze;
	Player player;
	int px;
    int py;
    int time;
    int loopcount;
    Random rnd;
    Color randomColor =new Color(16,230,23);
    TextAttributes yellowAttributes=new TextAttributes(Color.yellow);
    FireandIce() throws Exception {
    	
    	//ateş hasarı kontrolünde ateşin yanında player var mı diye bakabilirsin
    	
        // Game Maze
       maze = new char[][]{{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '1', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', ' ', ' ', ' ', '#', '#', '#', '#', ' ', ' ', ' ', '#', '#', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '1', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', ' ', ' ', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', '@', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '2', ' ', '#', ' ', ' ', ' ', '-', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', ' ', ' ', '1', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', '-', '-', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', '1', '3', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', '-', '-', '-', ' ', ' ', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+', '+', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '-', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '-', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+', '+', '+', '#'},
        {'#', '3', ' ', ' ', ' ', ' ', '#', '#', '#', '#', 'C', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', ' ', '-', '-', '-', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', '#', '#', '#', ' ', '@', ' ', '#', ' ', ' ', '+', '+', '+', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', '@', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '-', '-', '-', '-', '-', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '+', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', ' ', '3', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '2', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '-', '-', ' ', ' ', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+', '#'},
        {'#', '1', ' ', '#', '#', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', ' ', ' ', '-', '-', ' ', ' ', ' ', '#', '#', '#', '#', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '+', '+', '+', '+', '+', '+', '+', '+', '+', '#'},
        {'#', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '-', '-', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', 'C', ' ', ' ', '#', ' ', ' ', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '-', ' ', ' ', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#'},
        {'#', ' ', ' ', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', '#', '#', '#', '-', '-', '-', ' ', '#', ' ', '-', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '-', '-', '-', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '-', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};
        for (int i = 0; i <  maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                cn.getTextWindow().setCursorPosition(j,i);
                
                cn.getTextWindow().output(maze[i][j]);
            }
        }
        
        
        
        
        
		klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {//silinebilir
			}

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
			}

			public void keyReleased(KeyEvent e) {//silinebilir
			}
		};
		FireandIce.cn.getTextWindow().addKeyListener(klis);
		// ----------------------------------------------------
		

			
        time=0;
        loopcount=0;
        rnd=new Random();
        px=rnd.nextInt(52);
        py=rnd.nextInt(22);
        while(maze[py][px]=='#') {
        	px=rnd.nextInt(52);
            py=rnd.nextInt(22);
        }
        player=new Player(px, py);
        updateMaze('P',yellowAttributes );
    
        
        while (player.getHealth()>0) {
        	
        	checkDamage();

			if (keypr == 1) { // if keyboard button pressed
				if (rkey == KeyEvent.VK_LEFT || rkey == KeyEvent.VK_RIGHT || rkey == KeyEvent.VK_UP
						|| rkey == KeyEvent.VK_DOWN) {
					if (rkey == KeyEvent.VK_LEFT) {
						
						if(maze[py][px-1]!='#'&&maze[py][px-1]!='+'&&maze[py][px-1]!='-') {
							updateMaze( ' ', null);
							px--;
							checkTreasure();
							checkIcePack();
							updateMaze('P',yellowAttributes );
						}
						player.setDirection(-1,0);//direction left to use packed ice
					}
					
					if (rkey == KeyEvent.VK_RIGHT) {
						if(maze[py][px+1]!='#'&&maze[py][px+1]!='+'&&maze[py][px+1]!='-') {
						updateMaze( ' ', null);
						px++;
						checkTreasure();
						checkIcePack();
						updateMaze('P',yellowAttributes );
						}
						player.setDirection(1,0);
					}
					if (rkey == KeyEvent.VK_UP) { 
						if(maze[py-1][px]!='#'&&maze[py-1][px]!='+'&&maze[py-1][px]!='-') {
						updateMaze(' ', null);
						py--;
						checkTreasure();
						checkIcePack();
						updateMaze('P',yellowAttributes );
						}
						player.setDirection(0,-1);
					}
					if (rkey == KeyEvent.VK_DOWN) {
						if(maze[py+1][px]!='#'&&maze[py+1][px]!='+'&&maze[py+1][px]!='-') {
							updateMaze(  ' ', null);
							py++;
							checkTreasure();
							checkIcePack();
							updateMaze(  'P',yellowAttributes );
							}
						player.setDirection(0,1);
					}

				}
				
			

				keypr = 0; // last action
			}
			cn.getTextWindow().setCursorPosition(55, 10);
        	System.out.println("P.Health : "+player.getHealth());
        	boolean zerodeleted=false;//To avoid printing 0 999 times
        	if (player.getHealth()!=1000&&!zerodeleted) {
        		zerodeleted=true;
    			cn.getTextWindow().setCursorPosition(69, 10);
    			System.out.println(" ");
			}
			cn.getTextWindow().setCursorPosition(55, 15);
        	System.out.println("P.Score  : "+player.getScore());
			cn.getTextWindow().setCursorPosition(55, 20);
        	System.out.println("P.Ice    : "+player.getPackedIceCount());
        	Thread.sleep(100);
        	loopcount++;
        	if (loopcount%10==0) {
				time++;
			}
		}
        
        
    }
    
    void checkTreasure() {
    	if(maze[py][px]=='1') {
    		player.addScore(3);
    	}
    	else if(maze[py][px]=='2') {
    		player.addScore(10);
    	}
    	else if(maze[py][px]=='3') {
    		player.addScore(30);
    	}
    }
    void checkIcePack() {
    	if(maze[py][px]=='@') {
    		player.addPackedIce();
    	}
    }
    void checkDamage() {//bir kere kullandım çok da gerekli değil ama şık
    	if(maze[py][px-1]=='-'||maze[py-1][px]=='-'||maze[py+1][px]=='-'||maze[py][px+1]=='-') {
			player.decreaseHealth(1);
		}
		else if(maze[py][px-1]=='C'||maze[py-1][px]=='C'||maze[py+1][px]=='C'||maze[py][px+1]=='C') {
			player.decreaseHealth(50);
		}
    }
    
    
    // Maze Update Function
    public void updateMaze( char ch, enigma.console.TextAttributes color){
        maze[py][px] = ch;
        cn.getTextWindow().output(px,py, ch,color);
    }
    // Usage
    //updateMaze('A', red);


    }
