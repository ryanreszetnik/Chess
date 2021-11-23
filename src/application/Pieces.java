package application;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class Pieces {
	public int x;
	public int y;
	Boolean white;
	boolean firstmove;
	boolean facingUp;
	String type;
	Image Dfault = new Image(getClass().getResource("Default.png").toExternalForm(), 46, 46, false, false);
	Image image = Dfault;
	public static boolean[][] safeBlack = new boolean[8][8];
	public static boolean[][] safeWhite = new boolean[8][8];
	Image Black_King = new Image(getClass().getResource("Black_King.png").toExternalForm(), 46, 46, false, false);
	Image Black_Queen = new Image(getClass().getResource("Black_Queen.png").toExternalForm(), 46, 46, false, false);
	Image Black_Rook = new Image(getClass().getResource("Black_Rook.png").toExternalForm(), 40, 46, false, false);
	Image Black_Bishop = new Image(getClass().getResource("Black_Bishop.png").toExternalForm(), 46, 46, false, false);
	Image Black_Knight = new Image(getClass().getResource("Black_Knight.png").toExternalForm(), 46, 46, false, false);
	Image Black_Pawn = new Image(getClass().getResource("Black_Pawn.png").toExternalForm(), 35, 46, false, false);

	Image White_King = new Image(getClass().getResource("White_King.png").toExternalForm(), 46, 46, false, false);
	Image White_Queen = new Image(getClass().getResource("White_Queen.png").toExternalForm(), 46, 46, false, false);
	Image White_Rook = new Image(getClass().getResource("White_Rook.png").toExternalForm(), 40, 46, false, false);
	Image White_Bishop = new Image(getClass().getResource("White_Bishop.png").toExternalForm(), 46, 46, false, false);
	Image White_Knight = new Image(getClass().getResource("White_Knight.png").toExternalForm(), 46, 46, false, false);
	Image White_Pawn = new Image(getClass().getResource("White_Pawn.png").toExternalForm(), 35, 46, false, false);

	public Pieces(String type, Boolean white, int x, int y) {
		this.type = type;
		this.white = white;
		this.x = x;
		this.y = y;
		firstmove = true;

		switch (type) {
		case "Pawn":
			if (white) {
				image = White_Pawn;
				facingUp = true;
			} else {
				image = Black_Pawn;
			}
			break;
		case "King":
			if (white) {
				image = White_King;
			} else {
				image = Black_King;
			}
			break;
		case "Queen":
			if (white) {
				image = White_Queen;
			} else {
				image = Black_Queen;
			}
			break;
		case "Bishop":
			if (white) {
				image = White_Bishop;
			} else {
				image = Black_Bishop;
			}
			break;
		case "Knight":
			if (white) {
				image = White_Knight;
			} else {
				image = Black_Knight;
			}
			break;
		case "Rook":
			if (white) {
				image = White_Rook;
			} else {
				image = Black_Rook;
			}
			break;
		default:
			image = null;
		}

	}

	public boolean[][] move(Button[][] temp, boolean[][] haspiece, boolean[][] isWhite, ArrayList<Pieces> parts){// boolean kingmove[][]) {
		boolean pieces[][] = new boolean[temp.length][temp[0].length];
		boolean running = true;
		switch (type) {
		case "Pawn":
			if (facingUp) {
				if (y < 8 && haspiece[x][y - 1] == false) {
					pieces[x][y - 1] = true;
					if (firstmove && haspiece[x][y - 2] == false) {
						pieces[x][y - 2] = true;
					}
				}
				if(x < 7 && isWhite[x][y]!=isWhite[x+1][y-1]&&haspiece[x+1][y-1]){
					pieces[x+1][y-1] = true;
				}
				if(x > 0 && isWhite[x][y]!=isWhite[x-1][y-1]&&haspiece[x-1][y-1]){
					pieces[x-1][y-1] = true;
				}
				
			} else if (y > 0 && haspiece[x][y + 1] == false) {

				pieces[x][y + 1] = true;
				if (firstmove && haspiece[x][y + 2] == false) {
					pieces[x][y + 2] = true;
				}
				if(x < 7 && isWhite[x][y]!=isWhite[x+1][y+1]&&haspiece[x+1][y+1]){
					pieces[x+1][y+1] = true;
				}
				if(x > 0 && isWhite[x][y]!=isWhite[x-1][y+1]&&haspiece[x-1][y+1]){
					pieces[x-1][y+1] = true;
				}

			}
			break;
		case "King":
			running = true;
			for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, 7) && running; i++) {
				for (int p = Math.max(y - 1, 0); p <= Math.min(y + 1, 7)&& running; p++) {
					if (haspiece[i][p] == false){ //&& !kingmove[i][p]) {
						pieces[i][p] = true;
					}else if(isWhite[x][y]!=isWhite[i][p]){
						pieces[i][p] = true;
					}
				}
			}
			for(int i = 0;i < parts.size(); i++){
				if(firstmove && parts.get(i).type == "Rook"&&parts.get(i).firstmove){
					if(parts.get(i).x == 0 &&haspiece[x+1][y]==false&&haspiece[x+2][y]==false){
						pieces[x+2][y] = true;
					}else if( haspiece[x-1][y]==false&&haspiece[x-2][y]==false){
						pieces[x-2][y] = true;
					}
				}
			}
			break;
		case "Queen":
			running = true;
			for (int i = x; i >= 0 && running; i--) {
				if (haspiece[i][y] == false) {
					pieces[i][y] = true;
				}else if(isWhite[x][y]!=isWhite[i][y]){
					pieces[i][y] = true;
					break;
				}
				else if (i != x) {
					running = false;
				}

			}
			running = true;
			for (int i = x; i < 8 && running; i++) {
				if (haspiece[i][y] == false) {
					pieces[i][y] = true;
				} else if(isWhite[x][y]!=isWhite[i][y]){
					pieces[i][y] = true;
					break;
				}else if (i != x) {
					running = false;
				}

			}
			running = true;
			for (int i = y; i < 8 && running; i++) {
				if (haspiece[x][i] == false) {
					pieces[x][i] = true;
				} else if(isWhite[x][y]!=isWhite[x][i]){
					pieces[x][i] = true;
					break;
				}else if (i != y) {
					running = false;
				}

			}
			running = true;
			for (int i = y; i >= 0 && running; i--) {
				if (haspiece[x][i] == false) {
					pieces[x][i] = true;
				} else if(isWhite[x][y]!=isWhite[x][i]){
					pieces[x][i] = true;
					break;
				}else if (i != y) {
					running = false;
				}

			}
			
		case "Bishop":
			running = true;
			if (x != 0 && y != 0) {
				for (int i = x; i >= 0 && running; i--) {// up left
					System.out.println(i+"---"+(y - (x - i)));
					if (haspiece[i][y - (x - i)] == false) {
						pieces[i][y - (x - i)] = true;
					} else if(isWhite[x][y]!=isWhite[i][y - (x - i)]){
						pieces[i][y - (x - i)] = true;
						break;
					}else if (i != x) {
						running = false;
					}
					if (y - (x - i) == 0) {
						running = false;
					}
				}
			}
			running = true;
			if (x != 7 && y != 0) {

				for (int i = x; i < 8 && running; i++) {// up right
					System.out.println("upright");
					if (haspiece[i][y + (x - i)] == false) {
						pieces[i][y + (x - i)] = true;
					} else if(isWhite[x][y]!=isWhite[i][y + (x - i)]){
						pieces[i][y + (x - i)] = true;
						break;
					}else if (i != x) {
						running = false;
					}
					if (y + (x - i) == 0) {
						running = false;
					}
					// if(haspiece[i][y] == true){
					// running = false;
					// }
				}
			}
			running = true;
			if (x != 0 && y != 7) {
				for (int i = x; i >= 0 && running; i--) {// down left
					if (haspiece[i][y + (x - i)] == false) {
						pieces[i][y + (x - i)] = true;
					} else if(isWhite[x][y]!=isWhite[i][y + (x - i)]){
						pieces[i][y + (x - i)] = true;
						break;
					}else if (i != x) {
						running = false;
					}
					if (y + (x - i) == 7) {
						running = false;
					}
				}
			}
			running = true;
			if (x != 7 && y != 7) {
				for (int i = x; i < 8 && running; i++) {// down right
					System.out.println(i + "===" + (y - (x - i)));
					if (haspiece[i][y - (x - i)] == false) {
						pieces[i][y - (x - i)] = true;
					} else if(isWhite[x][y]!=isWhite[i][y - (x - i)]){
						pieces[i][y - (x - i)] = true;
						break;
					}else if (i != x) {
						running = false;
					}
					if ((y - (x - i)) == 7) {
						running = false;
					}
				}
			}

			break;

		case "Knight":
			int tempX[] = { 1, 1, -1, -1, 2, 2, -2, -2 };
			int tempY[] = { 2, -2, 2, -2, 1, -1, 1, -1 };
			for (int i = 0; i < 8; i++) {
				if (tempX[i] + x >= 0 && tempX[i] + x < 8 && tempY[i] + y >= 0 && tempY[i] + y < 8) {
					if (haspiece[tempX[i] + x][tempY[i] + y] == false) {
						pieces[tempX[i] + x][tempY[i] + y] = true;
					}else if(isWhite[x][y]!=isWhite[tempX[i] + x][tempY[i] + y]){
						pieces[tempX[i] + x][tempY[i] + y] = true;
					}

				}
			}

			break;
		case "Rook":
			running = true;
			for (int i = x; i >= 0 && running; i--) {
				if (haspiece[i][y] == false) {
					pieces[i][y] = true;
				}else if(isWhite[x][y]!=isWhite[i][y]){
					pieces[i][y] = true;
					break;
				}
				else if (i != x) {
					running = false;
				}

			}
			running = true;
			for (int i = x; i < 8 && running; i++) {
				if (haspiece[i][y] == false) {
					pieces[i][y] = true;
				} else if(isWhite[x][y]!=isWhite[i][y]){
					pieces[i][y] = true;
					break;
				}else if (i != x) {
					running = false;
				}

			}
			running = true;
			for (int i = y; i < 8 && running; i++) {
				if (haspiece[x][i] == false) {
					pieces[x][i] = true;
				} else if(isWhite[x][y]!=isWhite[x][i]){
					pieces[x][i] = true;
					break;
				}else if (i != y) {
					running = false;
				}

			}
			running = true;
			for (int i = y; i >= 0 && running; i--) {
				if (haspiece[x][i] == false) {
					pieces[x][i] = true;
				} else if(isWhite[x][y]!=isWhite[x][i]){
					pieces[x][i] = true;
					break;
				}else if (i != y) {
					running = false;
				}

			}
		}

		return pieces;
	}
	

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	

}
