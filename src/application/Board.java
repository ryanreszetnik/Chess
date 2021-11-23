package application;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Board {
	public Button squares[][];
	public Button whitePromote[][] = new Button[2][2];
	public Button blackPromote[][] = new Button[2][2];
	
	public boolean highlighted[][];
	public boolean haspiece[][];
	public boolean isWhite[][];
	public int selectedPieceX;
	public int selectedPieceY;
	public boolean selected;
	ArrayList<Pieces> parts = new ArrayList();
	Image White_Queen = new Image(getClass().getResource("White_Queen.png").toExternalForm(), 46, 46, false, false);
	Image White_Rook = new Image(getClass().getResource("White_Rook.png").toExternalForm(), 40, 46, false, false);
	Image White_Bishop = new Image(getClass().getResource("White_Bishop.png").toExternalForm(), 46, 46, false, false);
	Image White_Knight = new Image(getClass().getResource("White_Knight.png").toExternalForm(), 46, 46, false, false);
	
	Image Black_Queen = new Image(getClass().getResource("Black_Queen.png").toExternalForm(), 46, 46, false, false);
	Image Black_Rook = new Image(getClass().getResource("Black_Rook.png").toExternalForm(), 40, 46, false, false);
	Image Black_Bishop = new Image(getClass().getResource("Black_Bishop.png").toExternalForm(), 46, 46, false, false);
	Image Black_Knight = new Image(getClass().getResource("Black_Knight.png").toExternalForm(), 46, 46, false, false);

	public Board(int xsize, int ysize, int tsize) {
		
		
		for (int i = 0; i < 2; i++) {
			for (int p = 0; p < 2; p++) {
				whitePromote[i][p] = new Button();
				blackPromote[i][p] = new Button();
				int size = tsize / Math.max(xsize, ysize);
				whitePromote[i][p].setPrefSize(size, size);
				whitePromote[i][p].setMinSize(size, size);
				whitePromote[i][p].setMaxSize(size, size);
				blackPromote[i][p].setPrefSize(size, size);
				blackPromote[i][p].setMinSize(size, size);
				blackPromote[i][p].setMaxSize(size, size);
				
				whitePromote[i][p].setStyle(
							" -fx-background-color: rgb(255, 255, 255); -fx-border-color: black; -fx-border-width: 1px;");
				blackPromote[i][p].setStyle(
						" -fx-background-color: rgb(255, 255, 255); -fx-border-color: black; -fx-border-width: 1px;");
				
					
				}

			
		}
		whitePromote[0][0].setGraphic(new ImageView(White_Queen));
		whitePromote[0][1].setGraphic(new ImageView(White_Bishop));
		whitePromote[1][0].setGraphic(new ImageView(White_Rook));
		whitePromote[1][1].setGraphic(new ImageView(White_Knight));
		blackPromote[0][0].setGraphic(new ImageView(Black_Queen));
		blackPromote[0][1].setGraphic(new ImageView(Black_Bishop));
		blackPromote[1][0].setGraphic(new ImageView(Black_Rook));
		blackPromote[1][1].setGraphic(new ImageView(Black_Knight));
		
		squares = new Button[xsize][ysize];
		// pieces = new Pieces[xsize][ysize];
		highlighted = new boolean[xsize][ysize];
		haspiece = new boolean[xsize][ysize];
		isWhite = new boolean[xsize][ysize];
		// selectedPiece = new boolean[xsize][ysize];
		boolean state = false;
		parts.add(new Pieces("King", false, 4, 0));
		parts.add(new Pieces("Queen", false, 3, 0));
		parts.add(new Pieces("Rook", false, 0, 0));
		parts.add(new Pieces("Rook", false, 7, 0));
		parts.add(new Pieces("Bishop", false, 2, 0));
		parts.add(new Pieces("Bishop", false, 5, 0));
		parts.add(new Pieces("Knight", false, 1, 0));
		parts.add(new Pieces("Knight", false, 6, 0));

		for (int i = 0; i < 8; i++) {
			parts.add(new Pieces("Pawn", false, i, 1));
			parts.add(new Pieces("Pawn", true, i, 6));
			haspiece[i][0] = true;
			haspiece[i][1] = true;
			haspiece[i][6] = true;
			haspiece[i][7] = true;
			isWhite[i][6] = true;
			isWhite[i][7] = true;
		}

		parts.add(new Pieces("King", true, 4, 7));
		parts.add(new Pieces("Queen", true, 3, 7));
		parts.add(new Pieces("Rook", true, 0, 7));
		parts.add(new Pieces("Rook", true, 7, 7));
		parts.add(new Pieces("Bishop", true, 2, 7));
		parts.add(new Pieces("Bishop", true, 5, 7));
		parts.add(new Pieces("Knight", true, 1, 7));
		parts.add(new Pieces("Knight", true, 6, 7));

		for (int i = 0; i < squares.length; i++) {
			for (int p = 0; p < squares[i].length; p++) {
				squares[i][p] = new Button();

				int size = tsize / Math.max(xsize, ysize);
				squares[i][p].setPrefSize(size, size);
				squares[i][p].setMinSize(size, size);
				squares[i][p].setMaxSize(size, size);
				if (state) {
					squares[i][p].setStyle(
							" -fx-background-color: rgb(165, 117, 81); -fx-border-color: black; -fx-border-width: 1px;");
					state = false;
				} else {
					squares[i][p].setStyle(
							" -fx-background-color: rgb(236, 208, 166);-fx-border-color: black; -fx-border-width: 1px;");
					state = true;
				}

			}
			if (state) {
				state = false;
			} else {
				state = true;
			}
		}
		updateDisplay();

	}

	public void resetColor() {
		boolean state = false;
		for (int i = 0; i < squares.length; i++) {
			for (int p = 0; p < squares[i].length; p++) {
				highlighted[i][p] = false;
				selectedPieceY = -1;
				selectedPieceX = -1;
				if (state) {
					squares[i][p].setStyle(
							" -fx-background-color: rgb(165, 117, 81); -fx-border-color: black; -fx-border-width: 1px;");
					state = false;
				} else {
					squares[i][p].setStyle(
							" -fx-background-color: rgb(236, 208, 166); -fx-border-color: black; -fx-border-width: 1px;");
					state = true;
				}

			}
			if (state) {
				state = false;
			} else {
				state = true;
			}
		}
	}

	public void move(int x, int y) {

		resetColor();
		boolean iswhite = piece(x, y).white;
		boolean possible[][] = piece(x, y).move(squares, haspiece, isWhite, parts);
		selectedPieceX = x;
		selectedPieceY = y;
		squares[x][y]
				.setStyle(" -fx-background-color: rgb(10, 200, 10); -fx-border-color: black; -fx-border-width: 1px;");
		for (int i = 0; i < squares.length; i++) {
			for (int p = 0; p < squares[i].length; p++) {
				if (possible[i][p]) {
					squares[i][p].setStyle(
							" -fx-background-color: rgb(165, 10, 10); -fx-border-color: black; -fx-border-width: 1px;");
					highlighted[i][p] = true;
				}
			}
		}
	}

	public void movePiece(int x1, int y1, int x2, int y2) {
		if (haspiece[x2][y2]) {
			parts.remove(pieceplace(x2, y2));
		}
		if (piece(x1, y1).type == "King" && selectedPieceX - x2 == 2) {
			movePiece(0, selectedPieceY, selectedPieceX - 1, selectedPieceY);
		} else if (piece(selectedPieceX, selectedPieceY).type == "King" && selectedPieceX - x2 == -2) {
			movePiece(7, selectedPieceY, selectedPieceX + 1, selectedPieceY);
		}
		piece(x1, y1).firstmove = false;
		piece(x1, y1).setXY(x2, y2);
		System.out.println(selectedPieceX + " " + selectedPieceY);
		haspiece[x1][y1] = false;
		isWhite[x2][y2] = isWhite[x1][y1];
		haspiece[x2][y2] = true;
		updateDisplay();
		resetColor();
		
	}

	public void updateDisplay() {
		for (int i = 0; i < squares.length; i++) {
			for (int p = 0; p < squares[i].length; p++) {
				squares[i][p].setGraphic(null);
			}
		}
		for (int i = 0; i < parts.size(); i++) {
			squares[parts.get(i).x][parts.get(i).y].setGraphic(new ImageView(parts.get(i).image));
		}
	}

	public Pieces piece(int x1, int y1) {
		for (int i = 0; i < parts.size(); i++) {
			if (parts.get(i).x == x1 && parts.get(i).y == y1) {
				System.out.println(parts.get(i));
				return parts.get(i);

			}
		}
		System.out.println("Break");
		return null;
	}

	public int pieceplace(int x1, int y1) {
		for (int i = 0; i < parts.size(); i++) {
			if (parts.get(i).x == x1 && parts.get(i).y == y1) {
				System.out.println(parts.get(i));
				return i;

			}
		}
		System.out.println("Break");
		return -1;
	}

	public boolean[][] checkCheck(boolean white) {
		boolean[][] allowed = new boolean[8][8];
		Pieces bking;
		Pieces wking;
		for (int i = 0; i < parts.size(); i++) {
			if (parts.get(i).type == "King") {
				if (parts.get(i).white) {
					wking = parts.get(i);
				} else {
					bking = parts.get(i);
				}
			}
		}
		for (int i = 0; i < parts.size(); i++) {
			if (parts.get(i).white && !white) {
				boolean temp[][] = parts.get(i).move(squares, haspiece, isWhite, parts);
				for (int a = 0; a < 8; a++) {
					for (int b = 0; b < 8; b++) {
						if (temp[a][b] == true) {
							allowed[a][b] = true;
						}
					}
				}
			} else if (!parts.get(i).white && white) {
				boolean temp[][] = parts.get(i).move(squares, haspiece, isWhite, parts);
				for (int a = 0; a < 8; a++) {
					for (int b = 0; b < 8; b++) {
						if (temp[a][b] == true) {
							allowed[a][b] = true;
						}
					}
				}
			}

		}
		for (int i = 0; i < 8; i++) {
			for (int p = 0; p < 8; p++) {
				if (allowed[i][p]) {
					allowed[i][p] = false;
				} else {
					allowed[i][p] = true;
				}
			}
		}
		return allowed;
	}
	
}
