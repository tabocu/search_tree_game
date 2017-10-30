package game;

import java.util.concurrent.ThreadLocalRandom;

import tree.algorithm.WeightableF;

import java.util.ArrayList;
import java.util.List;

public class State implements WeightableF {
	public static final String [][] SOLVED_STATE = {{"1", "2", "3"},
			                                        {"8", " ", "4"},
			                                        {"7", "6", "5"}};
	
	private String [][] mCurrentState;
	
	private int mWeightG = 1;
	private int mWeightH = -1;
	
	public State() {
		this(SOLVED_STATE);
	}
	
	public State(State s) {
		this(s.mCurrentState);
	}
	
	public State(String [][] currentState) {
		mCurrentState = new String [3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				mCurrentState[i][j] = currentState[i][j];
	}
	
	public void shuffle() {
		int oldI = 1, oldJ = 1, newI, newJ;
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
				if (mCurrentState[i][j] == " ") {
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
		return mCurrentState[0][0] + " " + mCurrentState[0][1] + " " + mCurrentState[0][2] + "\n" +
			   mCurrentState[1][0] + " " + mCurrentState[1][1] + " " + mCurrentState[1][2] + "\n" +
			   mCurrentState[2][0] + " " + mCurrentState[2][1] + " " + mCurrentState[2][2] + "\n";
	}
	
	public void swap(int oldI, int oldJ, int newI, int newJ) { 
		String piece = mCurrentState[oldI][oldJ];
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
				switch (mCurrentState[i][j]) {
					case "1":
						mWeightH += Math.abs(i - 0) + Math.abs(j - 0);
						break;
					case "2":
						mWeightH += Math.abs(i - 0) + Math.abs(j - 1);
						break;
					case "3":
						mWeightH += Math.abs(i - 0) + Math.abs(j - 2);
						break;
					case "4":
						mWeightH += Math.abs(i - 1) + Math.abs(j - 2);
						break;
					case "5":
						mWeightH += Math.abs(i - 2) + Math.abs(j - 2);
						break;
					case "6":
						mWeightH += Math.abs(i - 2) + Math.abs(j - 1);
						break;
					case "7":
						mWeightH += Math.abs(i - 2) + Math.abs(j - 0);
						break;
					case "8":
						mWeightH += Math.abs(i - 1) + Math.abs(j - 0);
						break;
				}
			}
		}
		
		return mWeightH;
	}
	
	@Override
	public int hashCode() {
		int serial = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (mCurrentState[i][j] == " ")
					serial += 9;
				else
					serial += mCurrentState[i][j].charAt(0) - '0';
				
				if (i != 2 || j != 2)
					serial *= 10;
			}
		
		return serial;
	}
}
