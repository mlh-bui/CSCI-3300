package patterns.composite;

public class TestProgram {

    public static void main(String[] args) {
        IFile textFile1 = new TextFile("users.txt", 1048576);
        IFile textFile2 = new TextFile("notes.txt", 2097152);

        IFile binaryFile1 = new BinaryFile("data.bin", 31457280);
        IFile binaryFile2 = new BinaryFile("image1.jpg", 5242880);
        IFile binaryFile3 = new BinaryFile("image2.jpg", 10485760);
        IFile binaryFile4 = new BinaryFile("image3.jpg", 15728640);

        IDirectory directory1 = new Directory("Documents");

        directory1.add(textFile1);
        directory1.add(textFile2);
        directory1.add(binaryFile1);
        directory1.add(binaryFile2);
        directory1.add(binaryFile3);
        directory1.add(binaryFile4);

        System.out.println(directory1.dir());

        directory1.delete(binaryFile1);

        System.out.println(directory1.dir());

        IDirectory directory2 = new Directory("Documents and binary data");

        directory2.add(directory1);
        directory2.add(binaryFile1);

        System.out.println(directory2.dir());
    }

}