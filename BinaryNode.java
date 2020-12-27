package Ejercicio_ArbolAVL_Tweets;

public class BinaryNode{
	
	Object element;
	BinaryNode left;
	BinaryNode right;

	public BinaryNode(Object ele) {
		this.element = ele;
		this.left = null;
		this.right = null;
	}
	
	public BinaryNode(Object ele, BinaryNode l, BinaryNode r) {
		this.element = ele;
		this.left = l;
		this.right = r;
	}
}
