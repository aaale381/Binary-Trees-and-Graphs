package Ejercicio_ArbolAVL_Tweets;

import java.util.*;

public class BinaryTree implements IBinaryTree{
	public BinaryNode root;
//Dada una raíz a la que se le han insertado nodos, se podrá manipular dicho arbol resultante llamando a este constructor
	public BinaryTree(BinaryNode root) {
		this.root=root;
	}
//Para crear un árbol desde 0. Esta implementación permite que el árbol tenga nodos con clases de información distintas
	public BinaryTree(Object ele) {
		this.root = new BinaryNode(ele);
	}
//Devuelve el número total de nodos del árbol	
	@Override
	public int size() {
		int count=0;
		if(!isEmpty()) {
			count=tam(this.root,this.root.left,this.root.right);
		}
		return count;
	}
	private int tam(BinaryNode bn, BinaryNode left, BinaryNode right) {
		int count =1;
		if(left!=null)
			count+=tam(left, left.left,left.right);
		if(right!=null)
			count += tam(right, right.left,right.right);						//Tonto el que lo lea
		
		return count ;
	}
	/*
	    private int tam(BinaryNode left, BinaryNode right) {
        int count = 1;
        if (left != null) {
            count += tam(left.getLeft(), left.getRight());
        }
        if (right != null) {
            count += tam(right.getLeft(), right.getRight());
        }
        return count;
    }
	*/
	
	
//Devuelve True si el árbol está vacío
	@Override
	public boolean isEmpty() {
		return this.root==null;
	}
//Devuelve la raíz del árbol
	@Override
	public BinaryNode root() {
		return this.root;
	}
//Debuelve el padre de un nodo dado
	@Override
	public BinaryNode parent(BinaryNode node) {
		return parent(this.root,node,null);
	}
	private BinaryNode parent(BinaryNode root,BinaryNode node, BinaryNode parent) {
		BinaryNode bn;
		
		if(root==null)return null;
		
		if(node.equals(root))
			bn=parent;
		else {
			bn=parent(root.left,node, root);
			if(bn==null)
				bn=parent(root.right,node,root);
		}
		return bn;
	}
//Devuelve una colección con los hijos de un nodo dado
	@Override
	public Collection<BinaryNode> children(BinaryNode node) {
		Collection<BinaryNode> children= new ArrayList<BinaryNode>();
		if(hasLeft(node))
			children.add(node.left);
		if(hasRight(node))
			children.add(node.right);
			
		return children;
	}
//Devuelve verdadero en caso de que un nodo tenga ramas
	@Override
	public boolean isInternal(BinaryNode node) {
		return (hasLeft(node) || hasRight(node));
	}
//Devuelve verdadero en caso de un nodo sea una hoja
	@Override
	public boolean isExternal(BinaryNode node) {
		return (!hasLeft(node) && !hasRight(node));
	}
//Devuelve verdadero si el nodo dado es la raíz del árbol
	@Override
	public boolean isRoot(BinaryNode node) {
		return node==this.root;
	}
//Devuelve el nodo izquierdo del nodo dado
	@Override
	public BinaryNode left(BinaryNode node) {
		return node.left;
	}
//Devuelve el nodo derecho del nodo dado
	@Override
	public BinaryNode right(BinaryNode node) {
		return node.right;
	}
//Devuelve verdadero si el nodo dado tiene rama izquierda
	@Override
	public boolean hasLeft(BinaryNode node) {
		return node.left!=null;
	}
//Devuelve verdadero si el nodo dado tiene rama derecha
	@Override
	public boolean hasRight(BinaryNode node) {
		return node.right!=null;
	}
//Estos métodos tienen un toString para que se vea que se ha hecho bien
//Devuelve un iterador sobre los ELEMENTOS del árbol, haciendo el recorrido in order (left,root,right)
	@Override
	public Iterator<Object> elementsInOrder() {
		Collection<Object> elementsIO = new ArrayList<Object>();
		elementsIO=elementsInOrder(elementsIO,this.root);
		return elementsIO.iterator();
	}
	private Collection<Object> elementsInOrder(Collection<Object> elementsIO, BinaryNode node) {
		if(isEmpty())return elementsIO;
		
		if(hasLeft(node)) 
			elementsInOrder(elementsIO,node.left);
		
		elementsIO.add(node.element);
		
		if(hasRight(node)) 
			elementsInOrder(elementsIO,node.right);
		
		return elementsIO;
	}
//Devuelve un iterador sobre los ELEMENTOS del árbol, haciendo el recorrido PreOrder(root,left,right)
	@Override
	public Iterator<Object> elementsPreOrder() {
	Collection<Object> elementsPre = new ArrayList<Object>();
	elementsPre= elementsPreOrder(elementsPre,this.root);
	return  elementsPre.iterator();
	}
	private Collection<Object> elementsPreOrder(Collection<Object> elementsPre, BinaryNode node) {
		if(isEmpty())return null;
	
		elementsPre.add(node.element);
		if(hasLeft(node)) 
			elementsPreOrder(elementsPre,node.left);	
		if(hasRight(node))
			elementsPreOrder(elementsPre, node.right);
		
		return elementsPre;
		
	}
//Devuelve un iterador sobre los ELEMENTOS del árbol, haciendo el recorrido post order (left, right,root)
	@Override
	public Iterator<Object> elementsPostOrder() {
		Collection<Object> elementsPost = new ArrayList<Object>();
		elementsPost=elementsPostOrder(elementsPost,this.root);
		return elementsPost.iterator();
	}
	private Collection<Object> elementsPostOrder(Collection<Object> elementsPost, BinaryNode node) {
		if(isEmpty())return null;
		
		if(hasLeft(node)) 
			elementsPostOrder(elementsPost,node.left);	
		
		if(hasRight(node))
			elementsPostOrder(elementsPost, node.right);
		
		elementsPost.add(node.element);
		
		return elementsPost;
		
	}
//Devuelve un iterador sobre los NODOS del árbol, en orden
	@Override
	public Iterator<BinaryNode> nodesInOrder() {
		Collection<BinaryNode> nodes = new ArrayList<BinaryNode>();
		nodes=nodesInOrder(nodes,this.root);
		return nodes.iterator();
	}
	private Collection<BinaryNode> nodesInOrder(Collection<BinaryNode> nodes, BinaryNode node) {
		if(isEmpty())return nodes;
		
		
		if(hasLeft(node)) 
			nodesInOrder(nodes,node.left);		
	
			nodes.add(node);
		if(hasRight(node)) 
			nodesInOrder(nodes,node.right);
		
		return nodes;
	}
//Devuelve un iterador sobre los ELEMENTOS del árbol, pre orden
	@Override
	public Iterator<BinaryNode> nodesPreOrder() {
		Collection<BinaryNode> nodes = new ArrayList<BinaryNode>();
		nodes=nodesPreOrder(nodes,this.root);
		return nodes.iterator();
	}
	private Collection<BinaryNode> nodesPreOrder(Collection<BinaryNode> nodes, BinaryNode node) {
		if(isEmpty())return nodes;
		
	
			nodes.add(node);
		
		if(hasLeft(node)) 
			nodesPreOrder(nodes,node.left);		
		
		if(hasRight(node)) 
			nodesPreOrder(nodes,node.right);
		
		return nodes;
	}
//Devuelve un iterador sobre los ELEMENTOS del árbol, post orden
	@Override
	public Iterator<BinaryNode> nodesPostOrder() {
		Collection<BinaryNode> nodes = new ArrayList<BinaryNode>();
		nodes=nodesPostOrder(nodes,this.root);
		return nodes.iterator();
	}
	private Collection<BinaryNode> nodesPostOrder(Collection<BinaryNode> nodes, BinaryNode node) {
		if(isEmpty())return nodes;
		
		if(hasLeft(node)) 
			nodesPostOrder(nodes,node.left);	
		if(hasRight(node)) 
			nodesPostOrder(nodes,node.right);
		
			nodes.add(node);
		return nodes;
	}
//Inserta un nuevo nodo a la izquierda o a la derecha de otro nodo	
	@Override
	public void insertLeft(BinaryNode node, Object newElement) {
	if(!hasLeft(node))
		node.left= new BinaryNode(newElement);
		
	}
	@Override
	public void insertRight(BinaryNode node, Object newElement) {
		if(!hasRight(node))
			node.right= new BinaryNode(newElement);
	}
//Borra un nodo y lo reemplaza con su hijo, si tiene 2 hijos no se puede borrar
	public void removeNode(BinaryNode node) {
		List<BinaryNode> children = (List<BinaryNode>) children(node);
		if(children.size()<2) {
			if(right(parent(node)).equals(node))
				parent(node).right = children.get(0);
			else 
				parent(node).left = children.get(0);
		}
	}
	public void printTreeInOrder() {
		Collection<Object> elementsIO = new ArrayList<Object>();
		elementsIO=elementsInOrder(elementsIO,this.root);
		System.out.println(elementsIO.toString());
	}
	public void printTreePreOrder() {
		Collection<Object> elementsPre = new ArrayList<Object>();
		elementsPre= elementsPreOrder(elementsPre,this.root);
		System.out.println(elementsPre.toString());
	}
	public void printTreePostOrder() {
		Collection<Object> elementsPost = new ArrayList<Object>();
		elementsPost=elementsPostOrder(elementsPost,this.root);
		System.out.println(elementsPost.toString());
	}
//Creador de árboles de números enteros
/*
	public void insertadorDeNodos() {
		BinaryNode nodo;
		Scanner io = new Scanner(System.in);
		char c;
		int value;
		boolean b=true;
		System.out.println("Teclee 'exit' para parar la inserción");
		
		while(b) {
			List<Object> l = (List<Object>)elementsInOrder(new ArrayList<Object>(),this.root);
			List<BinaryNode> nodos = (List<BinaryNode>)nodesInOrder(new ArrayList<BinaryNode>(),this.root);
			System.out.println(l.toString());
			System.out.println("Seleccione el nodo");
			nodo = nodos.get(io.nextInt());
			System.out.println("Nuevo valor");
			value = io.nextInt();
			System.out.println("Seleccionar posición Left(l) o Right(r)");
			c = io.next().charAt(0);
			switch(c) {
			case 'l': insertLeft(nodo,value);
					  break;
			case 'r': insertRight(nodo,value);
					  break;
			default: System.out.println("No se insertó nada, saliendo...");
					b=false;
					 break;
			}
			
		}
		io.close();
	}
*/
		
	}


