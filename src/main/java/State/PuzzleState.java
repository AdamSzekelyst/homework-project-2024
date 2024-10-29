package State;

import puzzle.TwoPhaseMoveState;

import java.util.*;

/**
 * Represents the states of the rolling dice puzzle.
 */
public class PuzzleState implements TwoPhaseMoveState<Position> {

    /**
     * Represents the game board. Empty squares are represented by 0s.
     */
    private int[][] board = {{KING, BISHOP, BISHOP},
            {ROOK, ROOK, BLANK_SPACE}};

    /**
     * Represents the numeric representation of the King chess piece.
     */
    private static final int KING = 1;

    /**
     * Represents the numeric representation of the Bishop chess piece.
     */
    private static final int BISHOP = 2;

    /**
     * Represents the numeric representation of the Rook chess piece.
     */
    private static final int ROOK = 3;

    /**
     * Represents the numeric representation of the empty space on the board.
     */
    private static final int BLANK_SPACE = 0;

    /**
     * The number of rows in the game board.
     */
    public int ROWS = board.length;

    /**
     * The number of columns in the game board.
     */
    public int COLS = board[0].length;

    /**
     * Determines if the specified position is within the boundaries of the board.
     *
     * @param position The position to check.
     * @return true if the position is within the board's limits.
     */
    public boolean isOnBoard(Position position) {
        return position.col() >= 0 && position.col() < COLS && position.row() >= 0 && position.row() < ROWS;
    }

    /**
     * Checks if a piece at a given position can move in the specified direction based on the type of piece.
     *
     * @param position  The current position of the piece.
     * @param direction The direction in which the piece is intended to move.
     * @return true if the piece can legally move in the specified direction.
     */
    public boolean canPieceMoveInTheDirection(Position position, Direction direction) {
        int cell = board[position.row()][position.col()];
        return switch (direction) {
            case UP, DOWN, LEFT, RIGHT -> cell == KING || cell == ROOK;
            case UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT -> cell == KING || cell == BISHOP;
            default -> false;
        };
    }

    /**
     * Determines if the destination cell of a move is empty.
     *
     * @param position The two-phase move defining the start and end positions.
     * @return true if the destination cell is empty.
     */
    public boolean isTheDestinationEmpty(TwoPhaseMove<Position> position) {
        return board[position.to().row()][position.to().col()] == BLANK_SPACE;
    }

    /**
     * Checks if a move is legal from a specified position.
     *
     * @param position The current position of a piece.
     * @return true if there is at least one legal move from the given position.
     */
    @Override
    public boolean isLegalToMoveFrom(Position position) {
        if (isOnBoard(position)) {
            for (Direction direction : Direction.values()) {
                if (canPieceMoveInTheDirection(position, direction)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the puzzle is solved, which means the board matches the target configuration.
     *
     * @return true if the puzzle is in the solved state.
     */
    @Override
    public boolean isSolved() {
        int[][] endBoard = {{BISHOP, BISHOP, BLANK_SPACE},
                            {ROOK, ROOK, KING}};

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 3; j++)
                if (endBoard[i][j] != board[i][j])
                    return false;
        return true;
    }

    /**
     * Evaluates if a move is legal.
     *
     * @param positionTwoPhaseMove The move to evaluate, containing start and end positions.
     * @return true if the move is legal.
     */
    @Override
    public boolean isLegalMove(TwoPhaseMove<Position> positionTwoPhaseMove) {
        try {
            var dx = positionTwoPhaseMove.to().row() - positionTwoPhaseMove.from().row();
            var dy = positionTwoPhaseMove.to().col() - positionTwoPhaseMove.from().col();
            var direction = Direction.of(dx,dy);
            return isTheDestinationEmpty(positionTwoPhaseMove) && canPieceMoveInTheDirection(positionTwoPhaseMove.from(),direction);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Executes a move on the board, updating the board's state.
     *
     * @param positionTwoPhaseMove The move to execute.
     */
    @Override
    public void makeMove(TwoPhaseMove<Position> positionTwoPhaseMove) {
        Position start = positionTwoPhaseMove.from();
        Position end = positionTwoPhaseMove.to();
        int pieceToMove = board[start.row()][start.col()];
        board[end.row()][end.col()] = pieceToMove;
        board[start.row()][start.col()] = BLANK_SPACE;
    }

    /**
     * Generates all legal moves from the current state of the board.
     *
     * @return A set of all possible legal moves.
     */
    @Override
    public Set<TwoPhaseMove<Position>> getLegalMoves() {
        Set<TwoPhaseMove<Position>> legalMoves = new HashSet<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Position currentPosition = new Position(row, col);
                for (Direction direction : EnumSet.allOf(Direction.class)) {
                    if (canPieceMoveInTheDirection(currentPosition, direction)) {
                        Position newPosition = currentPosition.move(direction);
                        if (isOnBoard(newPosition)) {
                            TwoPhaseMove<Position> potentialMove = new TwoPhaseMove<>(currentPosition, newPosition);
                            if (isLegalMove(potentialMove)) {
                                legalMoves.add(potentialMove);
                            }
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    /**
     * Creates a deep copy of the current puzzle state.
     *
     * @return A new instance of PuzzleState with the same board configuration.
     */
    @Override
    public PuzzleState clone() {
        try {
            PuzzleState cloned = (PuzzleState) super.clone();
            cloned.board = new int[this.board.length][];
            for (int i = 0; i < this.board.length; i++) {
                cloned.board[i] = this.board[i].clone();
            }
            return cloned;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuzzleState that = (PuzzleState) o;
        return Objects.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PuzzleState:\n");
        for (int[] row : board) {
            for (int cell : row) {
                switch (cell) {
                    case KING:
                        builder.append(" K ");
                        break;
                    case BISHOP:
                        builder.append(" B ");
                        break;
                    case ROOK:
                        builder.append(" R ");
                        break;
                    case BLANK_SPACE:
                        builder.append(" - ");
                        break;
                    default:
                        builder.append("Hiba");
                        break;
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}