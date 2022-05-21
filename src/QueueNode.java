public class QueueNode {

    private int number;
    private QueueNode next;

    public QueueNode(int number) {
        this.setNumber(number);
        setNext(null);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public QueueNode getNext() {
        return next;
    }

    public void setNext(QueueNode next) {
        this.next = next;
    }
}