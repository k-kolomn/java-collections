import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main3 {
    public static void main(String[] args) {
        LinkedList.Node<Integer> head = new LinkedList.Node<>();
        head.setData(0);


        LinkedList.Node<Integer> tail = head;

        for (int i = 0; i < 30; i++) {
            LinkedList.Node<Integer> newNode = new LinkedList.Node<>();
            newNode.setData(i + 1);

            tail.setNext(newNode);
            newNode.setPrevious(tail);

            tail = newNode;
        }


//        aaa(head);
        printByIndex(head, tail, 10);
        printByIndex(head, tail, 20);
        printByIndex(head, tail, 15);
        printByIndex(head, tail, 25);
    }

    public static void aaa(LinkedList.Node<Integer> node) {
        LinkedList.Node<Integer> currentNode = node;
        int counter = 0;

        while (currentNode != null) {

            System.out.println(counter + " -> " + currentNode.getData());

            counter++;
            currentNode = currentNode.getNext();
        }
    }

    public static void printByIndex(
            LinkedList.Node<Integer> head,
            LinkedList.Node<Integer> tail,
            int index
    ) {
        int size = 31;

        if (index >= size) throw new IndexOutOfBoundsException("Pashol v zhopy!");

        int counter;
        LinkedList.Node<Integer> currentNode;
        Operator<Integer> counterOperator;
        Operator<LinkedList.Node<Integer>> currentNodeOperator;

        if (index < size / 2) {
            counter = 0;
            currentNode = head;

            counterOperator = (i) -> i + 1;
            currentNodeOperator = LinkedList.Node::getNext;
        } else {
            counter = size - 1;
            currentNode = tail;

            counterOperator = (i) -> i - 1;
            currentNodeOperator = LinkedList.Node::getPrevious;
        }

        while (currentNode != null) {
            if (counter == index) {
                System.out.println(counter + " -> " + currentNode.getData());
                break;
            }

            counter = counterOperator.accept(counter);
            currentNode = currentNodeOperator.accept(currentNode);
        }

        System.out.println();
        System.out.println();
    }




}

interface CarEngine {}

class C {
    public class A {}

    public static class B {}
}

class Audi {

    public static class Engine implements CarEngine {}
}

class Mercedes {
    public static class Engine implements CarEngine {}
}
