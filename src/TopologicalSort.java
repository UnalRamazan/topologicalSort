import java.util.ArrayList;

public class TopologicalSort {
    ReadFile readFile = new ReadFile();
    Graph graph;
    QueueOperations queueOperations = new QueueOperations();
    ArrayList<Integer> controlArraylist = new ArrayList<>();

    public TopologicalSort() {
        create();
    }

    private void create() {
        readFile.readerFromFile("input.txt");//dosyadan verileri alıp list yapısına ekledim
        graph = new Graph(readFile.getSize() + 1);//graph yapısını oluşturdum

        //matrix yapısını oluşturdum
        LinkedListNode walk = readFile.listOperations.getHead();
        while (walk != null) {

            graph.addEdge(walk.getFrom(), walk.getTo());
            walk = walk.getNext();
        }
    }

    public void topologicalSortOperation() {

        String strOutput = "";

        int counter = 1;

        int[] inDegreeSort = getInDegree((readFile.getSize() + 1));

        for (int i = 0; i < inDegreeSort.length; i++) {

            if (inDegreeSort[i] == 0) {
                queueOperations.enQueue(i);
                controlArraylist.add(queueOperations.rear.getNumber());
            }
        }

        while (queueOperations.getFront() != null) {

            int box = queueOperations.deQueue();
            strOutput += box + " ";

            String str = graph.neighbors(box);//komşu vertexleri buldum
            String[] strSplit = str.split(" ");

            counter++;

            if (!str.equals("")) {
                for (String s : strSplit) {
                    inDegreeSort[Integer.parseInt(s)]--;

                    if (inDegreeSort[Integer.parseInt(s)] == 0) {

                        if (comparisonOfElement(s)) {
                            queueOperations.enQueue(Integer.parseInt(s));
                            controlArraylist.add(queueOperations.rear.getNumber());
                        }
                    }
                }
            }
        }

        if (counter <= (readFile.getSize() + 1)) {
            System.out.println("G has a cycle.");
        }

        System.out.println("Output: " + strOutput);
    }

    private int[] getInDegree(int graphSize) {

        int[] isDegreeSort = new int[graphSize];

        for (int k = 0; k < graphSize; k++) {
            isDegreeSort[k] = 0;
        }

        for (int i = 0; i < graphSize; i++) {

            String str = graph.neighbors(i);
            String[] strSplit = str.split(" ");

            if (!str.equals("")) {
                for (String s : strSplit) {

                    isDegreeSort[Integer.parseInt(s)]++;
                }
            }
        }

        return isDegreeSort;
    }

    private boolean comparisonOfElement(String str) {
        boolean control = true;
        for (int i : controlArraylist) {

            if (i == Integer.parseInt(str)) {
                control = false;
            }
        }

        return control;
    }
}