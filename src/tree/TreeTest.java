package tree;

import org.junit.Test;

public class TreeTest {

	@Test
	public void countingTest() {
		Tree<Integer> tree = new Tree<>();
		tree.createNode();
		tree.createNode();
		tree.createNode();
		tree.createNode();
		tree.createNode();
		int nodeCount = tree.getNodeCount();
		assert(nodeCount == 5);
	}
	
	@Test
	public void rootTest() {
		Tree<Integer> tree = new Tree<>();
		Tree<Integer>.Node nodeA = tree.createNode();
		Tree<Integer>.Node nodeB = tree.createNode();
		tree.setRoot(nodeA);
		assert(nodeA.isRoot() && !nodeB.isRoot());
	}
	
	@Test
	public void structureTest() {
		Tree<Integer> tree = new Tree<>();
		Tree<Integer>.Node nodeA = tree.createNode();
		Tree<Integer>.Node nodeB = tree.createNode();
		Tree<Integer>.Node nodeC = tree.createNode();
		Tree<Integer>.Node nodeD = tree.createNode();
		Tree<Integer>.Node nodeE = tree.createNode();
		tree.setRoot(nodeA);
		nodeA.addChild(nodeB);
		nodeA.addChild(nodeC);
		nodeB.addChild(nodeD);
		nodeB.addChild(nodeE);
		
		assert(nodeA.getParent() == null);
		assert(tree.getRoot() == nodeA);
		
		assert(nodeA.isRoot() && !nodeA.isLeaf() && nodeA.getChildrenCount() == 2);
		assert(!nodeB.isRoot() && !nodeB.isLeaf() && nodeB.getChildrenCount() == 2);
		assert(!nodeC.isRoot() && nodeC.isLeaf() && nodeC.getChildrenCount() == 0);
		assert(!nodeD.isRoot() && nodeD.isLeaf() && nodeD.getChildrenCount() == 0);
		
		assert(nodeA.getChildren().contains(nodeB) && nodeA.getChildren().contains(nodeC));
		assert(!nodeA.getChildren().contains(nodeD) && !nodeA.getChildren().contains(nodeE));
		
		assert(nodeB.getChildren().contains(nodeD) && nodeB.getChildren().contains(nodeE));
		assert(!nodeB.getChildren().contains(nodeA) && !nodeB.getChildren().contains(nodeC));
		
		assert(!nodeA.hasParent());
		assert(nodeB.getParent() == nodeA && nodeC.getParent() == nodeA);
	}

}
