package file;

import java.io.File;

public class HttpFileSystem implements FileSystem {
  private File f;
  private File d;

  @Override
  public boolean exists(String file) {
    f = new File(file);
    return f.exists();
  }

  @Override
  public boolean isFile(String file) {
    f = new File(file);
    return f.isFile();
  }

  @Override
  public boolean isDirectory(String file) {
    f = new File(file);
    return f.isDirectory();
  }

  @Override
  public File[] listFiles(String directory) {
    d = new File(directory);
    return d.listFiles();
  }

  @Override
  public String[] list(String directory) {
    d = new File(directory);
    return d.list();
  }

  @Override
  public String getName(String file) {
    f = new File(file);
    return f.getName();
  }

  @Override
  public String getAbsolutePath(String file) {
    f = new File(file);
    return f.getAbsolutePath();
  }
}