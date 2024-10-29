package Solver;

import puzzle.TwoPhaseMoveState;
import puzzle.solver.BreadthFirstSearch;
import State.*;

public class Runner {
    public static void main(String[] args) {
        var bfs = new BreadthFirstSearch<TwoPhaseMoveState.TwoPhaseMove<Position>>();
        bfs.solveAndPrintSolution(new PuzzleState());
    }
}