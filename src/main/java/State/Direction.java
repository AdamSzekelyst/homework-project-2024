package State;

/**
 * Represents the 8 directions that the chess pieces can do.
 */
public enum Direction {

    UP(-1, 0),
    UP_RIGHT(-1, 1),
    RIGHT(0, 1),
    DOWN_RIGHT(1, 1),
    DOWN(1, 0),
    DOWN_LEFT(1, -1),
    LEFT(0, -1),
    UP_LEFT(-1, -1);

    private final int rowChange;
    private final int colChange;

    /**
     * Constructs a direction with specified row and column changes.
     *
     * @param rowChange the change in the row coordinate for this direction
     * @param colChange the change in the column coordinate for this direction
     */
    Direction(int rowChange, int colChange) {
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    /**
     * Returns the change in the row coordinate when moving in this direction.
     *
     * @return the row change associated with this direction
     */
    public int getRowChange() {
        return rowChange;
    }

    /**
     * Returns the change in the column coordinate when moving in this direction.
     *
     * @return the column change associated with this direction
     */
    public int getColChange() {
        return colChange;
    }

    /**
     * Determines the direction based on the specified row and column changes.
     *
     * @param rowChange the change in the row coordinate
     * @param colChange the change in the column coordinate
     * @return the direction corresponding to the specified changes
     * @throws IllegalArgumentException if no valid direction corresponds to the given changes
     */
    public static Direction of(int rowChange, int colChange) {
        for (Direction direction : values()) {
            if (direction.rowChange == rowChange && direction.colChange == colChange) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid row or column change for any known direction");
    }

}