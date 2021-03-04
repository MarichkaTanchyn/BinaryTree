import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.DoubleStream;

public class Tests {

    ArrayList<String> inputFiles;
    ArrayList<String> outputFiles;

    @Before
    public void before() {
        inputFiles = new ArrayList<>(Arrays.asList("input1", "input2", "input3", "input4", "input5", "input6"));
        outputFiles = new ArrayList<>(Arrays.asList("output1", "output2", "output3", "output4", "output5", "output6"));
    }


    @Test
    public void testInputs() {

        String[] data;
        String line;
        int i = 0;
        for (String inputFile : inputFiles) {
            try {
                BufferedReader brInput = new BufferedReader(new FileReader("src/Test/inputs/" + inputFile));
                BufferedReader brOutput = new BufferedReader(new FileReader("src/Test/outputs/" + outputFiles.get(i++)));

                BinaryTree<String> tree = new BinaryTree<>();

                while ((line = DefaultFileReader.readLine(brInput)) != null) {
                    data = line.split("\\s+");

                    if (data.length == 1) data = new String[]{data[0], ""};
                    tree.add(data[0], data[1]);
                }
                if (inputFile.equals("input3")) {
                    System.out.println(tree.getPaths(tree.head).contains("ZXRKCHIJZPSL"));
                }
                Assert.assertEquals(brOutput.readLine(), tree.getMax(tree.getPaths(tree.head)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
