package patterns.decorator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextFile implements IFile {
    private String fileName;

    public TextFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(String [] data) {
        try {
            PrintWriter outputFile = new PrintWriter(this.fileName);

            for (String s : data)
                outputFile.print(s + "\n");

            outputFile.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void read() {
        try {
            File inputFile = new File(this.fileName);

            if (inputFile.exists()) {
                Scanner data = new Scanner(inputFile);

                while (data.hasNext())
                    System.out.println(data.next());

                data.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}