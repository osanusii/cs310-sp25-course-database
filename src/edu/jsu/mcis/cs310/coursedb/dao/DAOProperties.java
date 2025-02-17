package edu.jsu.mcis.cs310.coursedb.dao;

import java.io.InputStream;
import java.util.Properties;

public class DAOProperties {
   private static final String PROPERTIES_FILE = "dao.properties";
   private static final Properties PROPERTIES = new Properties();
   private final String prefix;

   DAOProperties(String prefix) {
      this.prefix = prefix;
   }

   String getProperty(String key) {
      String fullKey = this.prefix + "." + key;
      String property = PROPERTIES.getProperty(fullKey);
      if (property == null || property.trim().length() == 0) {
         property = null;
      }

      return property;
   }

   static {
      InputStream file = DAOProperties.class.getResourceAsStream("dao.properties");

      try {
         PROPERTIES.load(file);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }
}
