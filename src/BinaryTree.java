import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class BinaryTree<T> {

    private static class Node<T> {
        T data;
        Node<T> parent;
        Node<T> left;
        Node<T> right;
        boolean isLeave;

        private static int idCounter = 0;
        private final int id;

        public Node(T data, Node<T> parent, Node<T> left, Node<T> right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.id = ++idCounter;
            isLeave = true;
        }

        void checkLeave() {
            if (right != null || left != null) isLeave = false;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    Node<T> head;

    public BinaryTree(Node<T> head) {
        this.head = head;
    }

    public BinaryTree() {
        head = constructEmptyNode();
    }

    public void add(T data, String place) { // RR -- place
        Node<T> tmp = head;
        Node<T> prev = null;

        for (int i = 0; i < place.length(); i++) {
            prev = tmp;
            tmp.checkLeave();
            if (place.substring(i, i+1).toLowerCase().equals("r")) {
                // push to the right
                if (tmp.right == null) {
                    tmp.right = constructEmptyNode();
                }
                tmp = tmp.right;
            } else {
                // push to the left
                if (tmp.left == null) {
                    tmp.left = constructEmptyNode();
                }
                tmp = tmp.left;
            }
        }

        tmp.data = data;
        tmp.parent = prev;
    }

    public String pathFromLeaveToRoot(Node<T> node) {
        Node<T> tmp = node;
        String path = "";
        while (tmp != null) {
            path += tmp;
            tmp = tmp.parent;
        }
        return path;
    }

    public String getPaths(Node<T> node) {
        if (node == null) return "";
        else if (node.isLeave) {
            return pathFromLeaveToRoot(node);
        }
        return getPaths(node.right) + " " + getPaths(node.left);
    }


    private Node<T> constructEmptyNode() {
        return new Node<>(null, null, null, null);
    }

    public String getMax(String inputs) {
        return Arrays.stream(inputs.split("\\s+")).max(String::compareTo).orElse("");
    }

    public static void main(String[] args) {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.populate(tree,"input1");
        String output = tree.getMax(tree.getPaths(tree.head));
        System.out.println(output);
    }

    public void populate(BinaryTree<String> tree, String input) {

        String[] data;
        String line;
        try {
            BufferedReader brInput = new BufferedReader(new FileReader("src/Test/inputs/" + input));

            while ((line = DefaultFileReader.readLine(brInput)) != null) {
                data = line.split("\\s+");

                if (data.length == 1) data = new String[]{data[0], ""};
                tree.add(data[0], data[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class DefaultFileReader {
    public static String readLine(BufferedReader br) throws IOException {
        return br.readLine();
    }
}
