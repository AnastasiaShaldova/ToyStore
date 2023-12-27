import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyQueue {
    private static final String OUTPUT_FILE_PATH = "result.txt";
    private PriorityQueue<Toy> toyQueue;

    public ToyQueue(String toyData1, String toyData2, String toyData3) {
        toyQueue = new PriorityQueue<>();
        addToy(toyData1);
        addToy(toyData2);
        addToy(toyData3);
    }

    private void addToy(String toyData) {
        String[] splitData = toyData.split(" ");
        int id = Integer.parseInt(splitData[0]);
        String name = splitData[1];
        int weight = Integer.parseInt(splitData[2]);
        toyQueue.add(new Toy(id, name, weight));
    }

    public void processToys() {
        try {
            FileWriter fileWriter = new FileWriter(OUTPUT_FILE_PATH);
            Random random = new Random();

            for (int i = 0; i < 10; i++) {
                int randomNumber = random.nextInt(10) + 1;

                Toy toy = getToy(randomNumber);
                fileWriter.write(toy.getId() + " " + toy.getName() + " " + toy.getWeight() + "\n");
            }

            fileWriter.close();
            System.out.println("Results written to " + OUTPUT_FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the results to the file.");
            e.printStackTrace();
        }
    }

    private Toy getToy(int randomNumber) {
        int totalWeight = 0;
        for (Toy toy : toyQueue) {
            totalWeight += toy.getWeight();
        }

        int randomWeight = new Random().nextInt(totalWeight);
        int cumulativeWeight = 0;
        for (Toy toy : toyQueue) {
            cumulativeWeight += toy.getWeight();
            if (randomWeight < cumulativeWeight) {
                return toy;
            }
        }

        return toyQueue.peek();
    }

    public static void main(String[] args) {
        String toyData1 = "1 конструктор 2";
        String toyData2 = "2 робот 2";
        String toyData3 = "3 кукла 6";

        

        ToyQueue toyQueue = new ToyQueue(toyData1, toyData2, toyData3);
        toyQueue.processToys();
    }
}