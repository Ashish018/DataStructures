package com.binaryTree;

import java.util.*;

class BinarySearchTree {

	Node root = null;

	class Node {
		int data;
		Node left, right;

		Node(int data) {
			this.data = data;
		}
	}

	public void insert(int data) {
		root = insertRec(root, data);
	}

	public Node insertRec(Node root, int data) {
		if (root == null)
			return new Node(data);
		else if (root.data < data)
			root.right = insertRec(root.right, data);
		else if (root.data > data)
			root.left = insertRec(root.left, data);
		return root;
	}

	public int countLeafNode() {

		return countLeafNode(root);
	}

	public int countLeafNode(Node root) {
		if (root == null)
			return 0;
		if (root.right == null && root.left == null)
			return 1;
		return countLeafNode(root.left) + countLeafNode(root.right);
	}

	boolean search(Node root, int data) {
		if (root == null)
			return false;
		if (root.data == data)
			return true;
		else if (root.data < data)
			return search(root.right, data);
		else if (root.data > data)
			return search(root.left, data);
		return false;
	}

	public void delete(int data) {
		/* Fill your code here */
		if (root == null)
			System.out.println("Tree is empty");
		else {
			if (!search(root, data))
				System.out.println("Data to be deleted, not found");
			root = deleteRec(root, data);
		}
	}

	public Node deleteRec(Node root, int data) {
		if (root == null)
			return null;
		if (root.data == data) {
			if (root.left == null && root.right == null)
				return null;
			else if (root.left == null || root.right == null) {
				if (root.left == null)
					return root.right;
				return root.left;
			} else {
				Node temp = minValue(root.right);
				root.data = temp.data;
				root.right = deleteRec(root.right, temp.data);
			}
		} else if (root.data < data)
			root.right = deleteRec(root.right, data);
		else if (root.data > data)
			root.left = deleteRec(root.left, data);

		return root;
	}

	public Node minValue(Node root) {
		if (root.left == null)
			return root;
		return minValue(root.left);
	}

	public int size() {

		return sizeRec(root);
	}

	public int sizeRec(Node root) {
		if (root == null)
			return 0;
		return 1 + sizeRec(root.left) + sizeRec(root.right);

	}

	public int maxDepth() {
		return maxRec(root);
	}

	public int maxRec(Node root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 1;
		return maxRec(root.left) > maxRec(root.right) ? (1 + maxRec(root.left)) : (1 + maxRec(root.right));
	}

	public void inorder() {
		inorder(root);

	}

	public void inorder(Node root) {
		if (root != null) {

			inorder(root.left);
			System.out.print(" " + root.data);
			inorder(root.right);
		}
		return;
	}

	public void preorder() {
		preorder(root);
	}

	public void preorder(Node root) {
		if (root != null) {
			System.out.print(" " + root.data);
			preorder(root.left);
			preorder(root.right);
		}
	}

	public void postorder() {
		postorder(root);
	}

	public void postorder(Node root) {
		if (root != null) {
			postorder(root.left);
			postorder(root.right);
			System.out.print(" " + root.data);
		}
	}

}

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BinarySearchTree bst = new BinarySearchTree();
		String ch = "";
		do {
			System.out.println("Enter the element to be inserted in the tree");
			int n = sc.nextInt();
			sc.nextLine();
			bst.insert(n);
			System.out.println("Do you want to insert another element?");
			ch = sc.nextLine();
		} while (ch.equals("yes"));
		System.out.print("The elements in the tree are");
		bst.inorder();
		System.out.println("\nThe size of the tree is "+bst.size());
		System.out.println("\nThe maximum depth of the tree is " + bst.maxDepth());
		
		System.out.println("Enter the element to be deleted :");
        int b=sc.nextInt(); 
        sc.nextLine();
        bst.delete(b);
	}

}
