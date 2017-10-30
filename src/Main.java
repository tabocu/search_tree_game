import java.util.LinkedList;
import java.util.List;

import game.GameTree;
import game.State;
import tree.Tree;
import tree.algorithm.Facade;

public class Main {
	
	public static final boolean SHOW_PATH = false;

	public static void main(String[] args) {

		State initialState = new State();
		initialState.shuffle();
		
		depthFirstSearch(initialState);
		breadthFirstSearch(initialState);
		uniformCostSearch(initialState);
		greedyBestFirstSearch(initialState);
		aStarSearch(initialState);	
	}
	
	private static void depthFirstSearch(State state) {
		GameTree gameTreeA = new GameTree(state);
		
		List<Tree<State>.Node> resultPath = new LinkedList<>();
		
		long startTime = System.currentTimeMillis();
		Tree<State>.Node end = Facade.depthFirstSearch(gameTreeA, null, gameTreeA, resultPath);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		printResult("Depth First Search", resultPath, end, totalTime);
	}
	
	private static void breadthFirstSearch(State state) {
		GameTree gameTreeA = new GameTree(state);
		
		List<Tree<State>.Node> resultPath = new LinkedList<>();

		long startTime = System.currentTimeMillis();
		Tree<State>.Node end = Facade.breadthFirstSearch(gameTreeA, null, gameTreeA, resultPath);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		printResult("Breadth First Search", resultPath, end, totalTime);
	}
	
	private static void uniformCostSearch(State state) {
		GameTree gameTreeA = new GameTree(state);
		
		List<Tree<State>.Node> resultPath = new LinkedList<>();
		
		long startTime = System.currentTimeMillis();
		Tree<State>.Node end = Facade.uniformCostSearch(gameTreeA, null, gameTreeA, resultPath);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		printResult("Uniform Cost Search",resultPath, end, totalTime);
	}
	
	private static void greedyBestFirstSearch(State state) {
		GameTree gameTreeA = new GameTree(state);
		
		List<Tree<State>.Node> resultPath = new LinkedList<>();
		
		long startTime = System.currentTimeMillis();
		Tree<State>.Node end = Facade.greedyBestFirstSearch(gameTreeA, null, gameTreeA, resultPath);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		printResult("Greedy Best First Search", resultPath, end, totalTime);
	}
	
	private static void aStarSearch(State state) {
		GameTree gameTreeA = new GameTree(state);
		
		List<Tree<State>.Node> resultPath = new LinkedList<>();
		
		long startTime = System.currentTimeMillis();
		Tree<State>.Node end = Facade.aStarSearch(gameTreeA, null, gameTreeA, resultPath);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		printResult("Star Search", resultPath, end, totalTime);
	}
	
	private static void printResult(String name, List<Tree<State>.Node> resultPath, Tree<State>.Node end, long totalTime) {
		//System.out.println(end.getContent());
		
		System.out.println("\n" + name);
		
		if (SHOW_PATH) {
			System.out.println("Resultado do caminho: ");
			for (Tree<State>.Node pathItem : resultPath) {
				System.out.println(pathItem.getContent());
			}
		}
		
		System.out.println("Tamanho do caminho: " + resultPath.size());
		System.out.println("Tempo de execução: " + totalTime + " milisegundos");
	}	
}
