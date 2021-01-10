package com.queue;

import java.util.*;

class Queue {

	int front = 0, rear = 0, queue[], num = 0, size = 5;

	Queue() {
		queue = new int[5];
	}

	public void enQueue(int data) {
		if (num == (size))
			System.out.println("Queue is full");
		else {
			num++;
			rear = (rear) % size;
			queue[rear++] = data;
		}
	}

	public int deQueue() {

		if (front == rear && front != -1) {
			System.out.println("Queue is empty");
			return -1000;
		} else {
			num--;
			front = (front) % size;
			return queue[front++];
		}
	}

	public void display() {
		if (num != 0)
			for (int i = front; i % size != rear; i++)
				System.out.print(" " + queue[i % size]);
		else
			System.out.print(" {}");
		System.out.println();
	}

}

public class Main {

	public static void main(String[] args) {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		Queue q = new Queue();
		do {
			System.out.println(
					"Choice 1 : Enter element into Queue\nChoice 2 : Delete element from Queue\nChoice 3 : Display\nAny other choice : Exit");
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			switch (choice) {
			case 1: {
				System.out.println("Enter the element to be inserted/entered");
				int a = sc.nextInt();
				sc.nextLine();
				q.enQueue(a);
				break;
			}
			case 2: {
				int b = q.deQueue();
				if (b != -1000)
					System.out.println("The deleted element is " + b);
				break;
			}
			case 3: {
				System.out.print("The contents of the queue are");
				q.display();
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
