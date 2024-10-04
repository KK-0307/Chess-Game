# Chess-Game
This project is a Java-based implementation of the classic board game Chess. It includes all chess pieces and implements standard chess rules, providing a simple text-based environment for gameplay.

## Features

- **Turn-Based Chess Gameplay**: Play the game of chess in a text-based interface, alternating turns between two players.
- **All Standard Chess Rules**: Implements the full set of chess rules, including check, checkmate, stalemate, pawn promotion, and castling.
- **Piece-Specific Movement**: Each piece follows the correct movement logic, from pawns moving forward and capturing diagonally to knights' L-shaped movements and the versatile queen.
- **Checkmate and Stalemate Detection**: The game recognizes when a player has won or when the match ends in a stalemate.

## Project Structure

- **`ChessGame.java`**: The main class that handles gameplay, player input, and overall game flow.
- **`ChessBoard.java`**: Manages the state of the chessboard, checking for valid moves, piece placement, and game-over conditions (check, checkmate, stalemate).
- **`Piece.java`**: Abstract class that defines the base behavior for all chess pieces.
- **Individual Piece Classes**:
  - **`Pawn.java`**: Handles pawn movement and captures.
  - **`Rook.java`**: Handles rook movement (vertical and horizontal).
  - **`Bishop.java`**: Handles bishop movement (diagonal).
  - **`Knight.java`**: Handles knight movement (L-shaped moves).
  - **`Queen.java`**: Handles queen movement (combining rook and bishop moves).
  - **`King.java`**: Handles king movement and checks for check/checkmate.
- **`Move.java`**: Handles the representation and validation of moves in the game.

