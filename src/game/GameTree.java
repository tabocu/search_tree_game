package game;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import tree.Tree;
import tree.algorithm.Stopable;

public class GameTree extends Tree<State> implements Stopable<Tree<State>.Node> {
	
	Set<State> mStateSet;
	boolean mAllowRepetition = false;
	
	public GameTree() {
		super();
		State initialState = new State();
		initialState.shuffle();
		initState(initialState);
	}
	
	public GameTree(State initialState) {
		super();
		initState(initialState);
	}
	
	private void initState(State initialState) {
		mStateSet = new HashSet<>();
		mStateSet.add(initialState);
		Tree<State>.Node initialStateNode = createNode(initialState);
		setRoot(initialStateNode);
	}
	
	public void setAllowRepetition(boolean allow) {
		mAllowRepetition = allow;
	}
	
	public boolean getAllowRepetition() {
		return mAllowRepetition;
	}
	
	public TreeSet<Node> getChildren(Node parent) {
		if (!parent.getContent().isSolved()) {
			List<State> nextStates = parent.getContent().getNextStates();
			for (State state : nextStates) {
				if (mAllowRepetition) {
					parent.addChild(createNode(state));
				} else if (!mStateSet.contains(state) || state.isSolved()) {
					mStateSet.add(state);
					parent.addChild(createNode(state));
				}
			}
		}
		return super.getChildren(parent);
	}

	@Override
	public boolean isFinal(Tree<State>.Node t) {
		return t.getContent().isSolved();
	}
}

