package by.training.task4.dal;

import java.io.File;

/**
 * Class for reading file.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class DALImpl implements DAL {

    /**
     * Maethod for reading file.
     * @param name name of file
     * @return file
     */
  public File getFile(final String name) {

      return new File(name);
  }

}
