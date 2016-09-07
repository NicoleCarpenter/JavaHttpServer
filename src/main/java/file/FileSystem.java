package file;

import java.io.File;

public interface FileSystem {
  public abstract boolean exists(String file);
  public abstract boolean isFile(String file);
  public abstract File[] listFiles(String directory);
  public abstract String[] list(String directory);
  public abstract String getName(String file);
  public abstract String getAbsolutePath(String file);
}