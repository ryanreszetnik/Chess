package application;

public class Pawn {
	public int x;
	public int y;
	public boolean firstmove;
	public int direction;
	public boolean white;
	public boolean isPinned;

	public Pawn(int x, int y, boolean isWhite) {
		this.x = x;
		this.y = y;
		white = isWhite;
		direction = 1;
		if (isWhite) {
			direction = -1;
		}
	}

	public boolean[][] PawnMove(boolean[][] hasPiece, boolean[][] isWhite) {

		boolean[][] canmove = new boolean[8][8];

		if (y != 0 && y != 7) {
			if (hasPiece[x][y + direction] == false) {
				canmove[x][y + direction] = true;
				if (firstmove && hasPiece[x][y + direction * 2] == false) {
					canmove[x][y + direction * 2] = true;
				}
			}
			if (x < 6 && hasPiece[x + 1][y + direction] && isWhite[x + 1][y + direction] != white) {
				canmove[x + 1][y + direction] = true;
			}
			if (x > 0 && hasPiece[x - 1][y + direction] && isWhite[x - 1][y + direction] != white) {
				canmove[x - 1][y + direction] = true;
			}
		}
		return canmove;
	}

	public boolean[][] notSafe() {
		boolean[][] notsafe = new boolean[8][8];
		if (y != 7 && y != 0) {
			if (x < 7) {
				notsafe[x + 1][y + direction] = true;
			}
			if (x > 0) {
				notsafe[x - 1][y + direction] = true;
			}
		}
		return notsafe;
	}
}
