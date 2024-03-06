package command;

import java.util.Deque;
import java.util.LinkedList;

public class Deq {

	public static void main(String[] args) {
		Deque<String> d = new LinkedList<String>();
        d.add("5");
        d.add("6");
        d.add("7");
        d.offerLast("2");
        d.offerLast("2");
        d.offerLast("2");
        d.offerLast("2");
        d.offerLast("2");
        System.out.println("The deque is: " + d);

	}

}
