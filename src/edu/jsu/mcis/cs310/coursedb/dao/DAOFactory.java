package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DAOFactory {
   private static final String PROPERTY_URL = "url";
   private static final String PROPERTY_DRIVER = "driver";
   private static final String PROPERTY_USERNAME = "username";
   private static final String PROPERTY_PASSWORD = "password";
   private final String url;
   private final String username;
   private final String password;
   private Connection conn;

   public DAOFactory(String prefix) {
      DAOProperties properties = new DAOProperties(prefix);
      this.url = properties.getProperty("url");
      this.username = properties.getProperty("username");
      this.password = properties.getProperty("password");

      try {
         this.conn = DriverManager.getConnection(this.url, this.username, this.password);
      } catch (Exception var4) {
         this.conn = null;
         var4.printStackTrace();
      }

   }

   Connection getConnection() {
      return this.conn;
   }

   public boolean isClosed() {
      boolean isClosed = true;

      try {
         isClosed = this.conn.isClosed();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      return isClosed;
   }

   public RegistrationDAO getRegistrationDAO() {
      return new RegistrationDAO(this);
   }

   public SectionDAO getSectionDAO() {
      return new SectionDAO(this);
   }

   public StudentDAO getStudentDAO() {
      return new StudentDAO(this);
   }
}
