package game;

import java.util.concurrent.ThreadLocalRandom;

import tree.algorithm.WeightableF;

import java.util.ArrayList;
import java.util.List;

public class State implements WeightableF {
	public static final int [][] SOLVED_STATE = {{1, 2, 3},
			                                     {4, 5, 6},
			                                     {7, 8, 0}};
	
	public static final int [] SOLVED_STATE_I;
	public static final int [] SOLVED_STATE_J;
	
	static {
		SOLVED_STATE_I = new int[9];
		SOLVED_STATE_J = new int[9];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				SOLVED_STATE_I[SOLVED_STATE[i][j]] = i;
				SOLVED_STATE_J[SOLVED_STATE[i][j]] = j;
			}		
	}
	
	
	private int [][] mCurrentState;
	
	private int mWeightG = 1;
	private int mWeightH = -1;
	
	public State() {
		this(SOLVED_STATE);
	}
	
	public State(State s) {
		this(s.mCurrentState);
	}
	
	public State(int [][] currentState) {
		mCurrentState = new int [3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				mCurrentState[i][j] = currentState[i][j];
	}
	
	public void shuffle() {
		int oldI = SOLVED_STATE_I[0], oldJ = SOLVED_STATE_J[0], newI, newJ;
		for (int x = 0; x < 1000; x++) {
			newI = ThreadLocalRandom.current().nextInt(0, 3);
			newJ = ThreadLocalRandom.current().nextInt(0, 3);
			if ((oldI == newI && Math.abs(oldJ - newJ) == 1) || 
					(oldJ == newJ && Math.abs(oldI - newI) == 1)) {
				swap(oldI,oldJ,newI,newJ);
				oldI = newI;
				oldJ = newJ;
			}
			
		}
	}
	
	public List<State> getNextStates() {
		int xI = 0, xJ = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (mCurrentState[i][j] == 0) {
					xI = i;
					xJ = j;
				}
		
		List<State> stateList = new ArrayList<>();
		
		if (0 <= xI-1) {
			State newState = (State) clone();
			newState.swap(xI, xJ, xI-1, xJ);
			stateList.add(newState);
		}
		
		if (0 <= xJ-1) {
			State newState = (State) clone();
			newState.swap(xI, xJ, xI, xJ-1);
			stateList.add(newState);
		}
		
		if (xI+1 < 3) {
			State newState = (State) clone();
			newState.swap(xI, xJ, xI+1, xJ);
			stateList.add(newState);
		}
		
		if (xJ+1 < 3) {
			State newState = (State) clone();
			newState.swap(xI, xJ, xI, xJ+1);
			stateList.add(newState);
		}
		
		return stateList;
	}
	
	public boolean isSolved() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (mCurrentState[i][j] != SOLVED_STATE[i][j])
					return false;
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		State state = (State) o;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (state.mCurrentState[i][j] != mCurrentState[i][j])
					return false;
		return true;
	}
	
	@Override
	public Object clone() {
		State clonedState = new State();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				clonedState.mCurrentState[i][j] = mCurrentState[i][j];
		return clonedState;
	}
	
	@Override
	public String toString() {
		return toString(0,0) + " " + toString(0,1) + " " + toString(0,2) + "\n" +
			   toString(1,0) + " " + toString(1,1) + " " + toString(1,2) + "\n" +
			   toString(2,0) + " " + toString(2,1) + " " + toString(2,2) + "\n";
	}
	
	public String toString(int i, int j) {
		if (mCurrentState[i][j] == 0)
			return " ";
		else
			return "" + mCurrentState[i][j];
	}
	
	public void swap(int oldI, int oldJ, int newI, int newJ) { 
		int piece = mCurrentState[oldI][oldJ];
		mCurrentState[oldI][oldJ] = mCurrentState[newI][newJ];
		mCurrentState[newI][newJ] = piece;
	}

	@Override
	public int getWeightG() {
		return mWeightG;
	}
	
	@Override
	public int getWeightH() {
		if (mWeightH != -1)
			return mWeightH;
		
		mWeightH = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				mWeightH += Math.abs(i - SOLVED_STATE_I[mCurrentState[i][j]]) + Math.abs(j - SOLVED_STATE_J[mCurrentState[i][j]]);
			}
		}
		return mWeightH;
	}
	
	@Override
	public int hashCode() {
		int serial = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				serial *= 10;
				serial += mCurrentState[i][j];	
			}
		
		return serial;
	}
}
