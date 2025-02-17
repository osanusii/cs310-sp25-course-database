package edu.jsu.mcis.cs310.coursedb.dao;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.sql.ResultSet;

public class DAOUtility {
   public static final int TERMID_FA24 = 1;

   public DAOUtility() {
   }

   public static String getResultSetAsJson(ResultSet rs) {
      JsonArray records = new JsonArray();

      try {
         if (rs != null) {
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      return Jsoner.serialize(records);
   }
}
