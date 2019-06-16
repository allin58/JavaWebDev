package by.taining.task4.dal;

import java.io.File;

public class DALParser implements DAL {


  public File getFile(String name) {


      return new File(name);
  }

}
