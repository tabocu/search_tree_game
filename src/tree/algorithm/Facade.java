package tree.algorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import tree.Tree;

public final class Facade {
	
	private Facade() {}
	
	public static <T> Tree<T>.Node depthFirstSearch(Tree <T> tree, Set<Tree<T>.Node> endNodesId, Stopable<Tree<T>.Node> stopCondition, List<Tree<T>.Node> resultPath) {
		if (resultPath != null)
			resultPath.clear();
		
		Stack<Tree<T>.Node> nextNodes = new Stack<>();
		nextNodes.push(tree.getRoot());
		
		Tree<T>.Node currentNode = null;
		while (!nextNodes.empty()) {
			currentNode = nextNodes.pop();
			if ((endNodesId != null && endNodesId.contains(currentNode)) || 
					(stopCondition != null && stopCondition.isFinal(currentNode))) {
				if (resultPath != null)
					getPath(currentNode,resultPath);
				return currentNode;
			}
			
			Iterator<Tree<T>.Node> it = currentNode.getChildren().descendingIterator();
			while (it.hasNext()) nextNodes.add(it.next());
		}
		return null;
	}
	
	public static <T> Tree<T>.Node breadthFirstSearch(Tree <T> tree, Set<Tree<T>.Node> endNodesId, Stopable<Tree<T>.Node> stopCondition, List<Tree<T>.Node> resultPath) {
		if (resultPath != null)
			resultPath.clear();
		
		LinkedList<Tree<T>.Node> nextNodes = new LinkedList<>();
		nextNodes.push(tree.getRoot());
		
		Tree<T>.Node currentNode = null;
		while (!nextNodes.isEmpty()) {
			currentNode = nextNodes.poll();
			if ((endNodesId!= null && endNodesId.contains(currentNode)) || 
					(stopCondition != null && stopCondition.isFinal(currentNode))) {
				if (resultPath != null)
					getPath(currentNode,resultPath);
				return currentNode;
			}
			
			Iterator<Tree<T>.Node> it = currentNode.getChildren().iterator();
			while (it.hasNext()) nextNodes.add(it.next());
		}
		return null;
	}
	
	public static <T extends WeightableG> Tree<T>.Node uniformCostSearch(Tree <T> tree, Set<Tree<T>.Node> endNodesId, Stopable<Tree<T>.Node> stopCondition, List<Tree<T>.Node> resultPath) {
		
		class UniformCostItem implements Comparable<UniformCostItem> {
			
			public UniformCostItem(Tree<T>.Node node, int cost) { 
				this.node = node; this.cost = cost; 
			}
			
			Tree<T>.Node node;
			int cost;
			
			@Override
			public int compareTo(UniformCostItem o) {
				int result = cost - o.cost;
				if (result == 0)
					return (int) (node.getId() - o.node.getId());
				else
					return result;
			}
		}
		
		if (resultPath != null)
			resultPath.clear();
		
		LinkedList<UniformCostItem> uniformCostList = new LinkedList<>();
		uniformCostList.addLast(new UniformCostItem(tree.getRoot(),tree.getRoot().getContent().getWeightG()));
		
		while (!uniformCostList.isEmpty()) {
			Collections.sort(uniformCostList);
			UniformCostItem uniformCostItem = uniformCostList.poll();
			if ((endNodesId != null && endNodesId.contains(uniformCostItem.node)) || 
					(stopCondition != null && stopCondition.isFinal(uniformCostItem.node))) {
				if (resultPath != null)
					getPath(uniformCostItem.node,resultPath);
				return uniformCostItem.node;
			}
			Set<Tree<T>.Node> nodeSet = uniformCostItem.node.getChildren();
			for (Tree<T>.Node node : nodeSet) {
				uniformCostList.addLast(new UniformCostItem(node,node.getContent().getWeightG() + uniformCostItem.cost));
			}
		}
		return null;
	}
	
	public static <T extends WeightableH> Tree<T>.Node greedyBestFirstSearch(Tree <T> tree, Set<Tree<T>.Node> endNodesId, Stopable<Tree<T>.Node> stopCondition, List<Tree<T>.Node> resultPath) {
		
		if (resultPath != null)
			resultPath.clear();
		
		LinkedList<Tree<T>.Node> nextNodes = new LinkedList<>();
		nextNodes.push(tree.getRoot());
		
		Tree<T>.Node currentNode = null;
		while (!nextNodes.isEmpty()) {
			
			Collections.sort(nextNodes,new Comparator<Tree<T>.Node>() {
				@Override
				public int compare(Tree<T>.Node o1,
						Tree<T>.Node o2) {
					int result = o1.getContent().getWeightH() - o2.getContent().getWeightH();
					if (result == 0)
						return (int) (o1.getId() - o2.getId());
					else
						return result;
				}	
			});

			currentNode = nextNodes.poll();
				
			if ((endNodesId!= null && endNodesId.contains(currentNode)) || 
					(stopCondition != null && stopCondition.isFinal(currentNode))) {
				if (resultPath != null)
					getPath(currentNode,resultPath);
				return currentNode;
			}
			nextNodes.addAll(currentNode.getChildren());
		}
		return null;
	}
	
	public static <T extends WeightableF> Tree<T>.Node aStarSearch(Tree <T> tree, Set<Tree<T>.Node> endNodesId, Stopable<Tree<T>.Node> stopCondition, List<Tree<T>.Node> resultPath) {
		class AStarItem implements Comparable<AStarItem> {
			
			public AStarItem(Tree<T>.Node node, int cost) { 
				this.node = node; this.cost = cost; 
			}
			
			Tree<T>.Node node;
			int cost;
			
			@Override
			public int compareTo(AStarItem o) {
				int result = (cost + node.getContent().getWeightH()) - (o.cost + o.node.getContent().getWeightH());
				if (result == 0)
					return (int) (node.getId() - o.node.getId());
				else
					return result;
			}
		}
		
		if (resultPath != null)
			resultPath.clear();
		
		LinkedList<AStarItem> aStartList = new LinkedList<>();
		aStartList.addLast(new AStarItem(tree.getRoot(),tree.getRoot().getContent().getWeightG()));
		
		while (!aStartList.isEmpty()) {
			Collections.sort(aStartList);
			AStarItem aStarItem = aStartList.poll();
			if ((endNodesId!= null && endNodesId.contains(aStarItem.node)) || 
					(stopCondition != null && stopCondition.isFinal(aStarItem.node))) {
				if (resultPath != null)
					getPath(aStarItem.node,resultPath);
				return aStarItem.node;
			}
			Set<Tree<T>.Node> nodeSet = aStarItem.node.getChildren();
			for (Tree<T>.Node node : nodeSet) {
				aStartList.addLast(new AStarItem(node,node.getContent().getWeightG() + aStarItem.cost));
			}
		}
		return null;
	}
	
	private static <T> void getPath(Tree<T>.Node endingNode, List<Tree<T>.Node> resultPath) {
		while (!endingNode.isRoot()) {
			resultPath.add(0, endingNode);
			endingNode = endingNode.getParent();
		}
		resultPath.add(0,endingNode);
	}
}
