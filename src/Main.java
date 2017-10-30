import java.util.LinkedList;
import java.util.List;

import game.GameTree;
import game.State;
import tree.Tree;
import tree.algorithm.Facade;
import tree.utils.Statistics;

public class Main {
	
	public static final boolean SHOW_PATH = false;

	public static void main(String[] args) {

		State initialState = new State();
		initialState.shuffle();
		
		System.out.println("Estado inicial:");
		System.out.print(initialState);
		
		depthFirstSearch(initialState);
		breadthFirstSearch(initialState);
		uniformCostSearch(initialState);
		greedyBestFirstSearch(initialState);
		aStarSearch(initialState);	
	}
	
	private static void depthFirstSearch(State state) {
		GameTree gameTreeA = new GameTree(state);
		
		List<Tree<State>.Node> resultPath = new LinkedList<>();
		Statistics statistics = new Statistics();
		
		Tree<State>.Node end = Facade.depthFirstSearch(gameTreeA, null, gameTreeA, resultPath, statistics);
		
		printResult("Depth First Search", resultPath, end, statistics);
	}
	
	private static void breadthFirstSearch(State state) {
		GameTree gameTreeA = new GameTree(state);
		
		List<Tree<State>.Node> resultPath = new LinkedList<>();
		Statistics statistics = new Statistics();

		Tree<State>.Node end = Facade.breadthFirstSearch(gameTreeA, null, gameTreeA, resultPath, statistics);
		
		printResult("Breadth First Search", resultPath, end, statistics);
	}
	
	private static void uniformCostSearch(State state) {
		GameTree gameTreeA = new GameTree(state);
		
		List<Tree<State>.Node> resultPath = new LinkedList<>();
		Statistics statistics = new Statistics();
		
		Tree<State>.Node end = Facade.uniformCostSearch(gameTreeA, null, gameTreeA, resultPath, statistics);
		
		printResult("Uniform Cost Search",resultPath, end, statistics);
	}
	
	private static void greedyBestFirstSearch(State state) {
		GameTree gameTreeA = new GameTree(state);
		
		List<Tree<State>.Node> resultPath = new LinkedList<>();
		Statistics statistics = new Statistics();
		
		Tree<State>.Node end = Facade.greedyBestFirstSearch(gameTreeA, null, gameTreeA, resultPath, statistics);
		
		printResult("Greedy Best First Search", resultPath, end, statistics);
	}
	
	private static void aStarSearch(State state) {
		GameTree gameTreeA = new GameTree(state);
		
		List<Tree<State>.Node> resultPath = new LinkedList<>();
		Statistics statistics = new Statistics();
		
		Tree<State>.Node end = Facade.aStarSearch(gameTreeA, null, gameTreeA, resultPath, statistics);
		
		printResult("Star Search", resultPath, end, statistics);
	}
	
	private static void printResult(String name, List<Tree<State>.Node> resultPath, Tree<State>.Node end, Statistics statistics) {		
		System.out.println("\n" + name);
		
		if (SHOW_PATH) {
			System.out.println("Resultado do caminho: ");
			for (Tree<State>.Node pathItem : resultPath) {
				System.out.println(pathItem.getContent());
			}
		}
		
		System.out.println("Tamanho do caminho: " + resultPath.size());
		System.out.println("Número de nós visitados: " + statistics.getNumberOfIterations());
		System.out.println("Tempo de execução: " + statistics.getTime() + " milisegundos");
	}	
}
