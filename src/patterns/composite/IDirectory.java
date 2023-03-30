package patterns.composite;

public interface IDirectory extends IFile{

    public void add(IFile file);
    public void delete(IFile file);
    public String dir();

}