package patterns.composite;

import java.util.List;
import java.util.ArrayList;

public class Directory implements IDirectory {
    private String name;
    private int bytes;
    private List<IFile> files;

    public Directory(String name) {
        // constructor method
        this.name = name;
        this.bytes = 0;
        this.files = new ArrayList<IFile>();
    }

    public void add(IFile file) {
        // adds a file to the list of files
        files.add(file);
        bytes = this.bytes + file.getBytes();
    }


    public void delete(IFile file) {
        // deletes a file
        files.remove(file);
        this.bytes = this.bytes - file.getBytes();
    }

    public String dir() {
        // shows the files in the directory
        String s = "Directory " + this.name + " contains: \n";
        for(IFile file : files) {
            s = s + "\n" + file.getName() + "\t" + file.getBytes() + " bytes";
        }
        s = s + "\n\n" + this.bytes + " bytes \n";

        return s;
    }

    public String getName() {
        return this.name + " <dir>";
    }

    @Override
    public int getBytes() {
        return this.bytes;
    }
}