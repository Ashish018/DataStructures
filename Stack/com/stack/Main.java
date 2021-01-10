package com.stack;

import java.util.*;

class Stack {

	/* Initialize the value of top to -1 and initialize the value of maxsize */
	int top = -1;
	int stack[];
	int size;

	Stack(int n) {
		this.size = n;
		stack = new int[n];
	}

	public void push(int data) {
		if (top == (size - 1))
			System.out.println("Stack is full");
		else
			stack[++top] = data;

	}

	public int pop() {
		if (top == -1) {
			System.out.println("Stack is empty");
			return -1000;
		} else
			return stack[top--];
	}

	public void display() {
		if (top == -1)
			System.out.print(" {}");
		else
			for (int i = 0; i <= top; i++)
				System.out.print(" " + stack[i]);
		System.out.println();
	}

}

public class Main {

	public static void main(String[] args) {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the maximum size of the stack");
		int n = sc.nextInt();
		Stack s = new Stack(n);
		do {
			System.out.println("Choice 1 : Push\nChoice 2 : Pop\nChoice 3 : Display\nAny other choice : Exit");
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			switch (choice) {
			case 1: {
				System.out.println("Enter the element to be pushed");
				int a = sc.nextInt();
				sc.nextLine();
				s.push(a);
				break;
			}
			case 2: {
				int b = s.pop();
				if (b != -1000)
					System.out.println("The popped element is " + b);
				break;
			}
			case 3: {
				System.out.print("The contents of the stack are");
				s.display();
				break;
			}
			default: {
				System.exit(0);
				break;
			}
			}
		} while (choice < 4);
	}

}
