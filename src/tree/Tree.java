package tree;

import java.util.HashMap;
import java.util.TreeSet;

public class Tree <T> extends CoreTree {

	private HashMap<Long, Node> mIdToNode = new HashMap<>();
	private TreeSet<Node> mNodeSet = new TreeSet<>();
	
	public class Node implements Comparable<Node> {	
		private long mId;
		private Object mContent;
		
		private Node(long id) { 
			mId = id; 
		}
		
		public long getId() { 
			return mId; 
		}
		
		@SuppressWarnings("unchecked")
		public T getContent() { 
			return (T) mContent; 
		}
		
		public void setContent(T content) { 
			mContent = content; 
		}
		
		public boolean isRoot() {
			return Tree.this.isRoot(this);
		}
		
		public boolean isLeaf() {
			return Tree.this.isLeaf(this);
		}
		
		public boolean hasParent() {
			return Tree.this.hasParent(this);
		}
		
		public Node getParent() {
			return Tree.this.getParent(this);
		}
		
		public void addChild(Node child) {
			Tree.this.addChild(this,child);
		}
		
		public int getChildrenCount() {
			return Tree.this.getChildrenCount(this);
		}
		
		public TreeSet<Node> getChildren() {
			return Tree.this.getChildren(this);
		}

		@Override
		public int compareTo(Tree<T>.Node o) {
			return (int) (this.mId - o.mId);
		}
	}

	public Node createNode(T content) {
		long newId = generateId();
		Node newNode = new Node(newId);
		newNode.setContent(content);
		mIdToNode.put(newId, newNode);
		mNodeSet.add(newNode);
		return newNode;
	}
	
	public Node createNode() {
		return createNode(null);
	}
	
	public void setRoot(Tree<?>.Node rootNode) {
		if (!mNodeSet.contains(rootNode)) 
			throw new RuntimeException("This node doesn't belong to this tree");
		setRootId(rootNode.getId()); 
	}
	
	public Node getRoot() { 
		return mIdToNode.get(getRootId()); 
	}
	
	@SuppressWarnings("unchecked")
	public TreeSet<Node> getAllNodes() {
		return (TreeSet<Tree<T>.Node>) mNodeSet.clone();
	}
	
	public int getNodeCount() {
		return (int) getIdCount();
	}
	
	public TreeSet<Node> getChildren(Node parent) {
		TreeSet<Long> idSet = getChildrenId(parent.getId());
		TreeSet<Node> childrenSet = new TreeSet<>();
		
		for (long id : idSet) 
			childrenSet.add(mIdToNode.get(id));
		
		return childrenSet;
	}
	
	public int getChildrenCount(Node parent) {
		return getChildrenId(parent.getId()).size();
	}
	
	public boolean isRoot(Node node) {
		return getRootId() == node.getId();
	}
	
	public boolean isLeaf(Node node) {
		return getChildrenId(node.getId()).size() == 0;
	}
	
	public boolean hasParent(Node node) {
		long mParentId = getParentId(node.getId());
		return mParentId != -1L;
	}
	
	public Node getParent(Node child) {
		if (!hasParent(child))
			return null;
		
		long mParentId = getParentId(child.getId());
		return mIdToNode.get(mParentId);
	}
	
	public void addChild(Node parent, Node child) {
		if (!mNodeSet.contains(child)) 
			throw new RuntimeException("This node doesn't belong to this tree");
		addChild(parent.getId(),child.getId());
	}
}

abstract class CoreTree {

	private long mIdCounter = 0;
	
	private long mRoot = -1L;
	private HashMap<Long, TreeSet<Long>> mParentToChildren = new HashMap<>();
	private HashMap<Long, Long> mChildToParent = new HashMap<>();
	
	protected void addChild(long idParent, long idChild) {
		
		if (!isIdValid(idParent))
			throw new RuntimeException(String.format("This parent = %1 doesn't exists", idParent));
		
		if (!isIdValid(idChild))
			throw new RuntimeException(String.format("This child = %1 doesn't exists", idChild));
		
		if (idChild == mRoot)
			throw new RuntimeException("Root can not have a parent");
		
		if (idParent == idChild)
			throw new RuntimeException("Parent and child can not be the same");
		
		if (mChildToParent.get(idChild) != -1)
			throw new RuntimeException("The child already has a parent");
		
		mChildToParent.put(idChild, idParent);
		mParentToChildren.get(idParent).add(idChild);
	}
	
	protected long generateId() {
		mParentToChildren.put(mIdCounter, new TreeSet<Long>());
		mChildToParent.put(mIdCounter, -1L);	
		return mIdCounter++; 
	}
	
	protected void setRootId(long rootId) {
		
		if (!isIdValid(rootId))
			throw new RuntimeException(String.format("This root = %1 doesn't exists", rootId));
		
		if (mChildToParent.get(rootId) != -1)
			throw new RuntimeException("Root can not has a parent");
		
		mRoot = rootId; 
	}
	
	private boolean isIdValid(long id) {
		return 0 <= id && id < mIdCounter;
	}
	
	public Long getRootId() { 
		return mRoot; 
	}
	
	public long getParentId(long idChild) {
		return mChildToParent.get(idChild);
	}
	
	public TreeSet<Long> getChildrenId(long idParent) {
		return (TreeSet<Long>) mParentToChildren.get(idParent);
	}
	
	public long getIdCount() {
		return mIdCounter;
	}
}
