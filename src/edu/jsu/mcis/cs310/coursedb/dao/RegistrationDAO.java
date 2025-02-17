package edu.jsu.mcis.cs310.coursedb.dao;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class RegistrationDAO {
   private final DAOFactory daoFactory;
   JsonArray jsonArray = new JsonArray();

   RegistrationDAO(DAOFactory daoFactory) {
      this.daoFactory = daoFactory;
   }

   public boolean create(int studentid, int termid, int crn) {
      boolean result = false;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
         Connection conn = this.daoFactory.getConnection();
         if (conn.isValid(0)) {
            String CREATE = "INSERT INTO registration (studentid, termid, crn) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(CREATE);
            ps.setInt(1, studentid);
            ps.setInt(2, termid);
            ps.setInt(3, crn);
            int update = ps.executeUpdate();
            if (update > 0) {
               result = true;
            }
         }
      } catch (Exception var22) {
         var22.printStackTrace();
      } finally {
         if (rs != null) {
            try {
               ((ResultSet)rs).close();
            } catch (Exception var21) {
               var21.printStackTrace();
            }
         }

         if (ps != null) {
            try {
               ps.close();
            } catch (Exception var20) {
               var20.printStackTrace();
            }
         }

      }

      return result;
   }

   public boolean delete(int studentid, int termid, int crn) {
      boolean result = false;
      PreparedStatement ps = null;

      try {
         Connection conn = this.daoFactory.getConnection();
         if (conn.isValid(0)) {
            String DELETE = "DELETE FROM registration WHERE studentid = ? AND termid=? AND crn=?";
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, studentid);
            ps.setInt(2, termid);
            ps.setInt(3, crn);
            int update = ps.executeUpdate();
            if (update > 0) {
               result = true;
            }
         }
      } catch (Exception var17) {
         var17.printStackTrace();
      } finally {
         if (ps != null) {
            try {
               ps.close();
            } catch (Exception var16) {
               var16.printStackTrace();
            }
         }

      }

      return result;
   }

   public boolean delete(int studentid, int termid) {
      boolean result = false;
      PreparedStatement ps = null;

      try {
         Connection conn = this.daoFactory.getConnection();
         if (conn.isValid(0)) {
            String DELETE = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, studentid);
            ps.setInt(2, termid);
            int update = ps.executeUpdate();
            if (update > 0) {
               result = true;
            }
         }
      } catch (Exception var16) {
         var16.printStackTrace();
      } finally {
         if (ps != null) {
            try {
               ps.close();
            } catch (Exception var15) {
               var15.printStackTrace();
            }
         }

      }

      return result;
   }

   public String list(int studentid, int termid) {
      String result = null;
      JsonArray jsonArray = new JsonArray();
      PreparedStatement ps = null;
      ResultSet rs = null;
      ResultSetMetaData rsmd = null;

      try {
         Connection conn = this.daoFactory.getConnection();
         if (conn.isValid(0)) {
            String LIST = "SELECT studentid, termid, crn FROM registration WHERE studentid = ? AND termid = ? ORDER BY crn";
            ps = conn.prepareStatement(LIST);
            ps.setInt(2, studentid);
            ps.setInt(1, termid);
            rs = ps.executeQuery();

            while(rs.next()) {
               JsonObject jsonObject = new JsonObject();
               jsonObject.put("studentid", rs.getInt("studentid"));
               jsonObject.put("termid", rs.getInt("termid"));
               jsonObject.put("crn", rs.getInt("crn"));
               jsonArray.add(jsonObject);
            }
         }
      } catch (Exception var23) {
         var23.printStackTrace();
      } finally {
         if (rs != null) {
            try {
               rs.close();
            } catch (Exception var22) {
               var22.printStackTrace();
            }
         }

         if (ps != null) {
            try {
               ps.close();
            } catch (Exception var21) {
               var21.printStackTrace();
            }
         }

      }

      result = jsonArray.toString();
      return result;
   }
}
