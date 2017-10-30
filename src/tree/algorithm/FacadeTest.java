package tree.algorithm;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import tree.Tree;

public class FacadeTest {
	
	@Test
	public void depthFirstSearch() {
		Tree<?> tree = new Tree<>();
		Tree<?>.Node nodeA = tree.createNode();
		Tree<?>.Node nodeB = tree.createNode();
		Tree<?>.Node nodeC = tree.createNode();
		Tree<?>.Node nodeD = tree.createNode();
		Tree<?>.Node nodeE = tree.createNode();
		Tree<?>.Node nodeF = tree.createNode();
		Tree<?>.Node nodeG = tree.createNode();
		Tree<?>.Node nodeH = tree.createNode();
		Tree<?>.Node nodeI = tree.createNode();
		Tree<?>.Node nodeJ = tree.createNode();
		Tree<?>.Node nodeK = tree.createNode();
		
		tree.setRoot(nodeA);
		nodeA.addChild(nodeB);
		nodeA.addChild(nodeC);
		nodeB.addChild(nodeD);
		nodeB.addChild(nodeE);
		nodeD.addChild(nodeH);
		nodeE.addChild(nodeI);
		nodeE.addChild(nodeJ);
		nodeC.addChild(nodeF);
		nodeC.addChild(nodeG);
		nodeF.addChild(nodeK);
		
		Set<Tree<?>.Node> endNodesId = new HashSet<>();
		endNodesId.add(nodeJ);
		endNodesId.add(nodeF);
		
		List<Tree<?>.Node> resultPath = new LinkedList<>();
		Tree<?>.Node nodeId = (Tree<?>.Node) Facade.depthFirstSearch(tree, endNodesId, null, resultPath, null);
		
		assertEquals(nodeId, nodeJ);
		assertEquals(resultPath.get(0), nodeA);
		assertEquals(resultPath.get(1), nodeB);
		assertEquals(resultPath.get(2), nodeE);
		assertEquals(resultPath.get(3), nodeJ);
	}
	
	@Test
	public void breadthFirstSearch() {
		Tree<?> tree = new Tree<>();
		Tree<?>.Node nodeA = tree.createNode();
		Tree<?>.Node nodeB = tree.createNode();
		Tree<?>.Node nodeC = tree.createNode();
		Tree<?>.Node nodeD = tree.createNode();
		Tree<?>.Node nodeE = tree.createNode();
		Tree<?>.Node nodeF = tree.createNode();
		Tree<?>.Node nodeG = tree.createNode();
		Tree<?>.Node nodeH = tree.createNode();
		Tree<?>.Node nodeI = tree.createNode();
		Tree<?>.Node nodeJ = tree.createNode();
		Tree<?>.Node nodeK = tree.createNode();
		
		tree.setRoot(nodeA);
		nodeA.addChild(nodeB);
		nodeA.addChild(nodeC);
		nodeB.addChild(nodeD);
		nodeB.addChild(nodeE);
		nodeD.addChild(nodeH);
		nodeE.addChild(nodeI);
		nodeE.addChild(nodeJ);
		nodeC.addChild(nodeF);
		nodeC.addChild(nodeG);
		nodeF.addChild(nodeK);
		
		Set<Tree<?>.Node> endNodesId = new HashSet<>();
		endNodesId.add(nodeJ);
		endNodesId.add(nodeF);
		
		List<Tree<?>.Node> resultPath = new LinkedList<>();
		Tree<?>.Node nodeId = (Tree<?>.Node) Facade.breadthFirstSearch(tree, endNodesId, null, resultPath, null);
		
		assertEquals(nodeId, nodeF);
		assertEquals(resultPath.get(0), nodeA);
		assertEquals(resultPath.get(1), nodeC);
		assertEquals(resultPath.get(2), nodeF);
	}
	
	@Test
	public void uniformCostSearch() {
		
		class UniformCostContent implements WeightableG {
			private int mWeight = 0;
			
			public UniformCostContent(int weight) { mWeight = weight; }
			
			@Override
			public int getWeightG() { return mWeight; }
		}
		
		Tree<UniformCostContent> tree = new Tree<>();
		Tree<UniformCostContent>.Node nodeA = tree.createNode(new UniformCostContent(0));
		Tree<UniformCostContent>.Node nodeB = tree.createNode(new UniformCostContent(1));
		Tree<UniformCostContent>.Node nodeC = tree.createNode(new UniformCostContent(5));
		Tree<UniformCostContent>.Node nodeD = tree.createNode(new UniformCostContent(6));
		Tree<UniformCostContent>.Node nodeE = tree.createNode(new UniformCostContent(10));
		Tree<UniformCostContent>.Node nodeF = tree.createNode(new UniformCostContent(5));
		Tree<UniformCostContent>.Node nodeG = tree.createNode(new UniformCostContent(1));
		
		tree.setRoot(nodeA);
		nodeA.addChild(nodeB);
		nodeA.addChild(nodeC);
		nodeA.addChild(nodeD);
		nodeB.addChild(nodeE);
		nodeC.addChild(nodeF);
		nodeD.addChild(nodeG);
		
		Set<Tree<?>.Node> endNodesId = new HashSet<>();
		endNodesId.add(nodeE);
		endNodesId.add(nodeF);
		
		List<Tree<?>.Node> resultPath = new LinkedList<>();
		Tree<?>.Node nodeId = (Tree<?>.Node) Facade.uniformCostSearch(tree, endNodesId, null, resultPath, null);
		
		assertEquals(nodeId, nodeF);
		assertEquals(resultPath.get(0), nodeA);
		assertEquals(resultPath.get(1), nodeC);
		assertEquals(resultPath.get(2), nodeF);
	}
	
	@Test
	public void greedyBestFirstSearch() {
		class GreedyBestFirstContent implements WeightableH {
			private int mHeuristicWeight = 0;
			
			public GreedyBestFirstContent(int heuristicWeight) { mHeuristicWeight = heuristicWeight; }
			
			@Override
			public int getWeightH() { return mHeuristicWeight; }
		}
		
		Tree<GreedyBestFirstContent> tree = new Tree<>();
		Tree<GreedyBestFirstContent>.Node nodeA = tree.createNode(new GreedyBestFirstContent(0));
		Tree<GreedyBestFirstContent>.Node nodeB = tree.createNode(new GreedyBestFirstContent(4));
		Tree<GreedyBestFirstContent>.Node nodeC = tree.createNode(new GreedyBestFirstContent(5));
		Tree<GreedyBestFirstContent>.Node nodeD = tree.createNode(new GreedyBestFirstContent(6));
		Tree<GreedyBestFirstContent>.Node nodeE = tree.createNode(new GreedyBestFirstContent(4));
		Tree<GreedyBestFirstContent>.Node nodeF = tree.createNode(new GreedyBestFirstContent(1));
		Tree<GreedyBestFirstContent>.Node nodeG = tree.createNode(new GreedyBestFirstContent(1));
		
		tree.setRoot(nodeA);
		nodeA.addChild(nodeB);
		nodeA.addChild(nodeC);
		nodeA.addChild(nodeD);
		nodeB.addChild(nodeE);
		nodeC.addChild(nodeF);
		nodeD.addChild(nodeG);
		
		Set<Tree<?>.Node> endNodesId = new HashSet<>();
		endNodesId.add(nodeE);
		endNodesId.add(nodeF);
		
		List<Tree<?>.Node> resultPath = new LinkedList<>();
		Tree<?>.Node nodeId = (Tree<?>.Node) Facade.greedyBestFirstSearch(tree, endNodesId, null, resultPath, null);
		
		assertEquals(nodeId, nodeE);
		assertEquals(resultPath.get(0), nodeA);
		assertEquals(resultPath.get(1), nodeB);
		assertEquals(resultPath.get(2), nodeE);
	}
	
	@Test
	public void aStarSearch() {
		class AStarContent implements WeightableF {
			private int mWeight = 0;
			private int mHeuristicWeight = 0;
			
			public AStarContent(int weight, int heuristicWeight) { 
				mWeight = weight;
				mHeuristicWeight = heuristicWeight; 
			}
			
			@Override
			public int getWeightH() { return mHeuristicWeight; }

			@Override
			public int getWeightG() { return mWeight; }
		}
		
		Tree<AStarContent> tree = new Tree<>();
		Tree<AStarContent>.Node nodeA = tree.createNode(new AStarContent(0,10));
		Tree<AStarContent>.Node nodeB = tree.createNode(new AStarContent(5,5));
		Tree<AStarContent>.Node nodeC = tree.createNode(new AStarContent(8,1));
		Tree<AStarContent>.Node nodeD = tree.createNode(new AStarContent(2,3));
		Tree<AStarContent>.Node nodeE = tree.createNode(new AStarContent(6,0));
		Tree<AStarContent>.Node nodeF = tree.createNode(new AStarContent(1,0));
		Tree<AStarContent>.Node nodeG = tree.createNode(new AStarContent(9,0));
		
		tree.setRoot(nodeA);
		nodeA.addChild(nodeB);
		nodeA.addChild(nodeC);
		nodeA.addChild(nodeD);
		nodeB.addChild(nodeE);
		nodeC.addChild(nodeF);
		nodeD.addChild(nodeG);
		
		Set<Tree<?>.Node> endNodesId = new HashSet<>();
		endNodesId.add(nodeE);
		endNodesId.add(nodeF);
		
		List<Tree<?>.Node> resultPath = new LinkedList<>();
		Tree<?>.Node nodeId = (Tree<?>.Node) Facade.aStarSearch(tree, endNodesId, null, resultPath, null);
		
		assertEquals(nodeId, nodeF);
		assertEquals(resultPath.get(0), nodeA);
		assertEquals(resultPath.get(1), nodeC);
		assertEquals(resultPath.get(2), nodeF);
	}
	
	@Test
	public void depthFirstSearch2() {
		Tree<?> tree = new Tree<>();
		Tree<?>.Node nodeA = tree.createNode();
		Tree<?>.Node nodeB = tree.createNode();
		Tree<?>.Node nodeC = tree.createNode();
		Tree<?>.Node nodeD = tree.createNode();
		Tree<?>.Node nodeE = tree.createNode();
		Tree<?>.Node nodeF = tree.createNode();
		Tree<?>.Node nodeG = tree.createNode();
		Tree<?>.Node nodeH = tree.createNode();
		Tree<?>.Node nodeI = tree.createNode();
		Tree<?>.Node nodeJ = tree.createNode();
		Tree<?>.Node nodeK = tree.createNode();
		
		tree.setRoot(nodeA);
		nodeA.addChild(nodeB);
		nodeA.addChild(nodeC);
		nodeB.addChild(nodeD);
		nodeB.addChild(nodeE);
		nodeD.addChild(nodeH);
		nodeE.addChild(nodeI);
		nodeE.addChild(nodeJ);
		nodeC.addChild(nodeF);
		nodeC.addChild(nodeG);
		nodeF.addChild(nodeK);
		
		Set<Tree<?>.Node> endNodesId = new HashSet<>();
		endNodesId.add(nodeJ);
		endNodesId.add(nodeF);
		
		List<Tree<?>.Node> resultPath = new LinkedList<>();
		Tree<?>.Node nodeId = (Tree<?>.Node) Facade.depthFirstSearch(tree, null, new Stopable<Tree<?>.Node>() {
			@Override
			public boolean isFinal(Tree<?>.Node t) {
				return endNodesId.contains(t);
			}
		}, resultPath, null);
		
		assertEquals(nodeId, nodeJ);
		assertEquals(resultPath.get(0), nodeA);
		assertEquals(resultPath.get(1), nodeB);
		assertEquals(resultPath.get(2), nodeE);
		assertEquals(resultPath.get(3), nodeJ);
	}
	
	@Test
	public void breadthFirstSearch2() {
		Tree<?> tree = new Tree<>();
		Tree<?>.Node nodeA = tree.createNode();
		Tree<?>.Node nodeB = tree.createNode();
		Tree<?>.Node nodeC = tree.createNode();
		Tree<?>.Node nodeD = tree.createNode();
		Tree<?>.Node nodeE = tree.createNode();
		Tree<?>.Node nodeF = tree.createNode();
		Tree<?>.Node nodeG = tree.createNode();
		Tree<?>.Node nodeH = tree.createNode();
		Tree<?>.Node nodeI = tree.createNode();
		Tree<?>.Node nodeJ = tree.createNode();
		Tree<?>.Node nodeK = tree.createNode();
		
		tree.setRoot(nodeA);
		nodeA.addChild(nodeB);
		nodeA.addChild(nodeC);
		nodeB.addChild(nodeD);
		nodeB.addChild(nodeE);
		nodeD.addChild(nodeH);
		nodeE.addChild(nodeI);
		nodeE.addChild(nodeJ);
		nodeC.addChild(nodeF);
		nodeC.addChild(nodeG);
		nodeF.addChild(nodeK);
		
		Set<Tree<?>.Node> endNodesId = new HashSet<>();
		endNodesId.add(nodeJ);
		endNodesId.add(nodeF);
		
		List<Tree<?>.Node> resultPath = new LinkedList<>();
		Tree<?>.Node nodeId = (Tree<?>.Node) Facade.breadthFirstSearch(tree, null, new Stopable<Tree<?>.Node>() {
			@Override
			public boolean isFinal(Tree<?>.Node t) {
				return endNodesId.contains(t);
			}
		}, resultPath, null);
		
		assertEquals(nodeId, nodeF);
		assertEquals(resultPath.get(0), nodeA);
		assertEquals(resultPath.get(1), nodeC);
		assertEquals(resultPath.get(2), nodeF);
	}
	
	@Test
	public void uniformCostSearch2() {
		
		class UniformCostContent implements WeightableG {
			private int mWeight = 0;
			
			public UniformCostContent(int weight) { mWeight = weight; }
			
			@Override
			public int getWeightG() { return mWeight; }
		}
		
		Tree<UniformCostContent> tree = new Tree<>();
		Tree<UniformCostContent>.Node nodeA = tree.createNode(new UniformCostContent(0));
		Tree<UniformCostContent>.Node nodeB = tree.createNode(new UniformCostContent(1));
		Tree<UniformCostContent>.Node nodeC = tree.createNode(new UniformCostContent(5));
		Tree<UniformCostContent>.Node nodeD = tree.createNode(new UniformCostContent(6));
		Tree<UniformCostContent>.Node nodeE = tree.createNode(new UniformCostContent(10));
		Tree<UniformCostContent>.Node nodeF = tree.createNode(new UniformCostContent(5));
		Tree<UniformCostContent>.Node nodeG = tree.createNode(new UniformCostContent(1));
		
		tree.setRoot(nodeA);
		nodeA.addChild(nodeB);
		nodeA.addChild(nodeC);
		nodeA.addChild(nodeD);
		nodeB.addChild(nodeE);
		nodeC.addChild(nodeF);
		nodeD.addChild(nodeG);
		
		Set<Tree<?>.Node> endNodesId = new HashSet<>();
		endNodesId.add(nodeE);
		endNodesId.add(nodeF);
		
		List<Tree<?>.Node> resultPath = new LinkedList<>();
		Tree<?>.Node nodeId = (Tree<?>.Node) Facade.uniformCostSearch(tree, null, new Stopable<Tree<?>.Node>() {
			@Override
			public boolean isFinal(Tree<?>.Node t) {
				return endNodesId.contains(t);
			}
		}, resultPath, null);
		
		assertEquals(nodeId, nodeF);
		assertEquals(resultPath.get(0), nodeA);
		assertEquals(resultPath.get(1), nodeC);
		assertEquals(resultPath.get(2), nodeF);
	}
	
	@Test
	public void greedyBestFirstSearch2() {
		class GreedyBestFirstContent implements WeightableH {
			private int mHeuristicWeight = 0;
			
			public GreedyBestFirstContent(int heuristicWeight) { mHeuristicWeight = heuristicWeight; }
			
			@Override
			public int getWeightH() { return mHeuristicWeight; }
		}
		
		Tree<GreedyBestFirstContent> tree = new Tree<>();
		Tree<GreedyBestFirstContent>.Node nodeA = tree.createNode(new GreedyBestFirstContent(0));
		Tree<GreedyBestFirstContent>.Node nodeB = tree.createNode(new GreedyBestFirstContent(4));
		Tree<GreedyBestFirstContent>.Node nodeC = tree.createNode(new GreedyBestFirstContent(5));
		Tree<GreedyBestFirstContent>.Node nodeD = tree.createNode(new GreedyBestFirstContent(6));
		Tree<GreedyBestFirstContent>.Node nodeE = tree.createNode(new GreedyBestFirstContent(4));
		Tree<GreedyBestFirstContent>.Node nodeF = tree.createNode(new GreedyBestFirstContent(1));
		Tree<GreedyBestFirstContent>.Node nodeG = tree.createNode(new GreedyBestFirstContent(1));
		
		tree.setRoot(nodeA);
		nodeA.addChild(nodeB);
		nodeA.addChild(nodeC);
		nodeA.addChild(nodeD);
		nodeB.addChild(nodeE);
		nodeC.addChild(nodeF);
		nodeD.addChild(nodeG);
		
		Set<Tree<?>.Node> endNodesId = new HashSet<>();
		endNodesId.add(nodeE);
		endNodesId.add(nodeF);
		
		List<Tree<?>.Node> resultPath = new LinkedList<>();
		Tree<?>.Node nodeId = (Tree<?>.Node) Facade.greedyBestFirstSearch(tree, null, new Stopable<Tree<?>.Node>() {
			@Override
			public boolean isFinal(Tree<?>.Node t) {
				return endNodesId.contains(t);
			}
		}, resultPath, null);
		
		assertEquals(nodeId, nodeE);
		assertEquals(resultPath.get(0), nodeA);
		assertEquals(resultPath.get(1), nodeB);
		assertEquals(resultPath.get(2), nodeE);
	}
	
	@Test
	public void aStarSearch2() {
		class AStarContent implements WeightableF {
			private int mWeight = 0;
			private int mHeuristicWeight = 0;
			
			public AStarContent(int weight, int heuristicWeight) { 
				mWeight = weight;
				mHeuristicWeight = heuristicWeight; 
			}
			
			@Override
			public int getWeightH() { return mHeuristicWeight; }

			@Override
			public int getWeightG() { return mWeight; }
		}
		
		Tree<AStarContent> tree = new Tree<>();
		Tree<AStarContent>.Node nodeA = tree.createNode(new AStarContent(0,10));
		Tree<AStarContent>.Node nodeB = tree.createNode(new AStarContent(5,5));
		Tree<AStarContent>.Node nodeC = tree.createNode(new AStarContent(8,1));
		Tree<AStarContent>.Node nodeD = tree.createNode(new AStarContent(2,3));
		Tree<AStarContent>.Node nodeE = tree.createNode(new AStarContent(6,0));
		Tree<AStarContent>.Node nodeF = tree.createNode(new AStarContent(1,0));
		Tree<AStarContent>.Node nodeG = tree.createNode(new AStarContent(9,0));
		
		tree.setRoot(nodeA);
		nodeA.addChild(nodeB);
		nodeA.addChild(nodeC);
		nodeA.addChild(nodeD);
		nodeB.addChild(nodeE);
		nodeC.addChild(nodeF);
		nodeD.addChild(nodeG);
		
		Set<Tree<?>.Node> endNodesId = new HashSet<>();
		endNodesId.add(nodeE);
		endNodesId.add(nodeF);
		
		List<Tree<?>.Node> resultPath = new LinkedList<>();
		Tree<?>.Node nodeId = (Tree<?>.Node) Facade.aStarSearch(tree, null, new Stopable<Tree<?>.Node>() {
			@Override
			public boolean isFinal(Tree<?>.Node t) {
				return endNodesId.contains(t);
			}
		}, resultPath, null);
		
		assertEquals(nodeId, nodeF);
		assertEquals(resultPath.get(0), nodeA);
		assertEquals(resultPath.get(1), nodeC);
		assertEquals(resultPath.get(2), nodeF);
	}
}
