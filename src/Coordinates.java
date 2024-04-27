
public class Coordinates {
	private int x;	
	private int y;
	Coordinates(int x,int y){
		this.x=x;
		this.y=y;
	}
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
	void setXY(int xc,int yc) {x=xc;y=yc;}
	
	//maybe
	char mazeCoor(char[][] maze) {
		return maze[y][x];
	}

}
