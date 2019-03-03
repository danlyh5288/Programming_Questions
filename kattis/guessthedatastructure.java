import java.util.*;
import java.io.*;

public class guessthedatastructure {


    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String num_row;
            while (!(num_row = br.readLine()).equals(" ")) {
                int lines = Integer.parseInt(num_row);
                String type = "impossible";

                int[] operation = new int[lines];
                int[] numbers = new int[lines];

                HashSet<String> set = new HashSet<>();

                for (int i = 0; i < lines; i++) {
                    String[] row = br.readLine().split(" ");
                    operation[i] = Integer.parseInt(row[0]);
                    numbers[i] = Integer.parseInt(row[1]);
                }
                if (lines == 0) {
                    System.out.println("impossible");
                    continue;
                }
                if(lines == 1 && operation[0] == 1){
                    type = "not sure";
                }
                if (checkStack(operation, numbers)) {
                    set.add("stack");
                    type = "stack";
                }
                if (checkQueue(operation, numbers)) {
                    set.add("queue");
                    type = "queue";
                }
                if (checkPQ(operation, numbers)) {
                    set.add("priority queue");
                    type = "priority queue";
                }

                if (set.size() > 1) {
                    System.out.println("not sure");
                } else {
                    System.out.println(type);
                }
            }
        } catch (Exception e) {
            System.exit(0);
        }
    }

    private static boolean checkStack(int[] operation, int[] numbers) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < operation.length; i++) {
            if (operation[i] == 1) {
                stack.push(numbers[i]);
            } else {
                if (stack.size() == 0 || stack.pop() != numbers[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkQueue(int[] operation, int[] numbers) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < operation.length; i++) {
            if (operation[i] == 1) {
                queue.offer(numbers[i]);
            } else {
                if (queue.size() == 0 || queue.poll() != numbers[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkPQ(int[] operation, int[] numbers) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < operation.length; i++) {
            if (operation[i] == 1) {
                pq.offer(numbers[i]);
            } else {
                if (pq.size() == 0 || pq.poll() != numbers[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
