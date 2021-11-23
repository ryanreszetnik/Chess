package application;

public class Rook {
	public int x;
	public int y;
	public boolean firstmove;
	public boolean white;
	public boolean isPinned;

	public Rook(int x, int y, boolean isWhite) {
		this.x = x;
		this.y = y;
		white = isWhite;
	}

	public boolean[][] RookMove(boolean[][] hasPiece, boolean[][] isWhite) {

		boolean[][] canmove = new boolean[8][8];

		
		return canmove;
	}

	public boolean[][] notSafe() {
		
		return null;
	}
}
