package by.training.task4.dal;

import java.io.File;

public class DALImpl implements DAL {


  public File getFile(String name) {


      return new File(name);
  }

}
