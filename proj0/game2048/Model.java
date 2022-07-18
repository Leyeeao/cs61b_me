package game2048;

import java.util.Formatter;
import java.util.Observable;

import static game2048.Side.NORTH;


/** The state of a game of 2048.
 *  @author Meiting
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board _board;
    /** Current score. */
    private int _score;
    /** Maximum score so far.  Updated when game ends. */
    private int _maxScore;
    /** True iff game is ended. */
    private boolean _gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        // TODO: Fill in this constructor.
        _board = new Board(size);
        _score = 0;
        _maxScore = 0;
        _gameOver = false;

    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        // TODO: Fill in this constructor.
        _board = new Board(rawValues, score);
        _score = score;
        _maxScore = maxScore;
        _gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     */
    public Tile tile(int col, int row) {
        return _board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return _board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (_gameOver) {
            _maxScore = Math.max(_score, _maxScore);
        }
        return _gameOver;
    }

    /** Return the current score. */
    public int score() {
        return _score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return _maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        _score = 0;
        _gameOver = false;
        _board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        _board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Fill in this function.
        _board.setViewingPerspective(side);
        for (int col = 0; col < size(); col++) {
            boolean ch = forOneCol(col);
            if (ch) {
                changed = true;
            }
        }

        _board.setViewingPerspective(Side.NORTH);
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;

    }

    /** processes a single colomn for one time.
     *
     * @param col
     * 1. don't forget to check if the move is valid. Update the variable changed.
     * 2. if merge happens, update the score.
     */
    private boolean forOneCol(int col) {
        boolean res = false;
        int[] v = new int[size()];
        boolean[] merged = new boolean[size()];
        for (int row = size() - 1; row >= 0; row--) {
            Tile t = _board.tile(col, row);
            // get the desired row number.
            if (t == null) {continue;}
            if (row == size() - 1) {
                v[row] = t.value();
                continue;
            }
            int nrow = row;
            while (nrow < size()-1 && v[nrow + 1] == 0) {
                nrow = nrow + 1;
            }
            if (nrow < size()-1 && t.value() == v[nrow+1] && !merged[nrow + 1]) {
                nrow = nrow + 1;
            }

            boolean mer = _board.move(col, nrow, t);
            if (mer) {
                _score += _board.tile(col, nrow).value();
                v[nrow] = _board.tile(col, nrow).value();
                merged[nrow] = true;
                t = null;
            }
            if (t != null) {
                v[nrow] = t.value();
            }
            if (nrow != row) {
                res = true;
            }

        }
        return res;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        _gameOver = checkGameOver(_board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        for (int r = 0; r < b.size(); r++) {
            for (int c = 0; c < b.size(); c++){
                if (b.tile(c, r) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        for (int r = 0; r < b.size(); r++) {
            for (int c = 0; c < b.size(); c++) {
                if (b.tile(c,r) == null) {
                    continue;
                }
                if (b.tile(c,r).value() == MAX_PIECE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        if (emptySpaceExists(b)) {
            return true;
        }
        for (int r = 0; r < b.size(); r++) {
            for (int c = 0; c < b.size(); c++) {
                Tile current = b.tile(c, r);
                Tile[] adjacent = new Tile[2];
                if (c <= b.size() - 2) {
                    adjacent[0] = b.tile(c+1, r);
                }
                if (r <= b.size() - 2) {
                    adjacent[1] = b.tile(c, r+1);
                }
                for (Tile t : adjacent) {
                    if (t != null) {
                        if (t.value() == current.value()) {
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    /** Returns the model as a string, used for debugging. */
    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    /** Returns whether two models are equal. */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    /** Returns hash code of Model’s string. */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
