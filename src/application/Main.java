package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/*Things to do
 * 1. check
 * 2.pawn promotion
 * 4. on poisson
 */
public class Main extends Application {
	static boolean whiteTurn = true;
	static String promoteType = "";
	static int choosingx;
	static int choosingy;
	static boolean running = true;

	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			GridPane blackPromoting = new GridPane();
			GridPane whitePromoting = new GridPane();
			Pane pane = new Pane();
			int boardsize = 400;

			boolean turnDone = false;

			Scene scene = new Scene(pane, boardsize, boardsize);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Board board = new Board(8, 8, boardsize);
			running = true;

			for (int first = 0; first < board.squares.length&& running; first++) {
				for (int second = 0; second < board.squares[first].length&& running; second++) {
					int one = first;
					int two = second;

					board.squares[first][second].setOnMouseClicked(event -> {

						boolean finishedTurn = false;
						if (board.highlighted[one][two] && board.haspiece[one][two]
								&& board.isWhite[one][two] != board.isWhite[board.selectedPieceX][board.selectedPieceY]) {
							// taking
							running = false;
							board.movePiece(board.selectedPieceX, board.selectedPieceY, one, two);
							board.updateDisplay();
							finishedTurn = true;
							if (board.piece(one, two).y == 0 && board.piece(one, two).type == "Pawn") {
								board.parts.remove(board.pieceplace(one, two));
								pane.getChildren().add(whitePromoting);
								whitePromoting.setTranslateX(150);
								whitePromoting.setTranslateY(150);
								choosingx = one;
								choosingy = two;
								System.out.println("Promote");
								finishedTurn = true;
								
							}
							if (board.piece(one, two).y == 7 && board.piece(one, two).type == "Pawn") {
								board.parts.remove(board.pieceplace(one, two));
								pane.getChildren().add(blackPromoting);
								blackPromoting.setTranslateX(150);
								blackPromoting.setTranslateY(150);
								choosingx = one;
								choosingy = two;
								System.out.println("Promote");
								finishedTurn = true;
							}
						} else if (board.highlighted[one][two]) {// moving
							System.out.println("move");
							running = false;
							finishedTurn = true;
							board.movePiece(board.selectedPieceX, board.selectedPieceY, one, two);
							if (board.piece(one, two).y == 0 && board.piece(one, two).type == "Pawn") {
								board.parts.remove(board.pieceplace(one, two));

								pane.getChildren().add(whitePromoting);
								whitePromoting.setTranslateX(150);
								whitePromoting.setTranslateY(150);
								choosingx = one;
								choosingy = two;
								System.out.println("Promote");
								finishedTurn = true;
							}
							if (board.piece(one, two).y == 7 && board.piece(one, two).type == "Pawn") {
								board.parts.remove(board.pieceplace(one, two));

								pane.getChildren().add(blackPromoting);
								blackPromoting.setTranslateX(150);
								blackPromoting.setTranslateY(150);
								choosingx = one;
								choosingy = two;
								System.out.println("Promote");
								finishedTurn = true;
							}

							board.updateDisplay();
							finishedTurn = true;
						}
						System.out.println(whiteTurn);
						if (finishedTurn) {
							if (whiteTurn) {
								whiteTurn = false;
							} else {
								whiteTurn = true;
							}
						}
						if (board.isWhite[one][two] == whiteTurn || board.selected) {
							if (one == board.selectedPieceX && two == board.selectedPieceY) {// unselecting
								board.resetColor();
								board.updateDisplay();
								System.out.println("done");
							} else if (board.piece(one, two) != null) {// selecting
								board.move(one, two);
							}

						}

					});
				}
			}
			for (int i = 0; i < 2; i++) {
				for (int p = 0; p < 2; p++) {
					int one = i;
					int two = p;
					board.blackPromote[i][p].setOnMouseClicked(event -> {

						promoteType = one == 0 ? two == 0 ? "Queen" : "Bishop" : two == 0 ? "Rook" : "Knight";
						pane.getChildren().remove(blackPromoting);
						board.parts.add(new Pieces(promoteType, false, choosingx, choosingy));
						board.updateDisplay();
						whiteTurn = true;

					});
					board.whitePromote[i][p].setOnMouseClicked(event -> {
						promoteType = one == 0 ? two == 0 ? "Queen" : "Bishop" : two == 0 ? "Rook" : "Knight";
						pane.getChildren().remove(whitePromoting);
						board.parts.add(new Pieces(promoteType, true, choosingx, choosingy));
						board.updateDisplay();
						whiteTurn = false;

					});
				}
			}
			for (int i = 0; i < board.squares.length; i++) {
				for (int p = 0; p < board.squares[i].length; p++) {
					root.add(board.squares[i][p], i, p);

				}
			}
			for (int i = 0; i < 2; i++) {
				for (int p = 0; p < 2; p++) {
					blackPromoting.add(board.blackPromote[i][p], i, p);
					whitePromoting.add(board.whitePromote[i][p], i, p);
				}
			}

			pane.getChildren().add(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
