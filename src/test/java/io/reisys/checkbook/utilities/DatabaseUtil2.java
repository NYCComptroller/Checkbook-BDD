package io.reisys.checkbook.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class DatabaseUtil2 {

  private static Connection con = null;
  private static Statement stmt = null;
  private static ResultSet rs = null;
  private static String query = null;
  private static String query2 = null;

  // Establishes connection
  public static void connectToDatabase() throws ClassNotFoundException, SQLException {
    if (con == null) {
      EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
      String URL = variables.getProperty("database.connection.url");
      Properties props = new Properties();
      props.setProperty("user", variables.getProperty("database.user"));
      props.setProperty("password", variables.getProperty("database.password"));
      props.setProperty("ssl", "false");
      Class.forName("org.postgresql.Driver");
      con = DriverManager.getConnection(URL, props);
    }
  }

  // Closes connection
  public static void closeDatabase() throws SQLException {
    con.close();
    con = null;
  }

  // Returns an array of all the years from 2009 till current
  public static Map<String, ArrayList<Integer>> getYears() throws SQLException {
    Calendar now = Calendar.getInstance();
    Map<String, ArrayList<Integer>> years = new TreeMap<>();
    ArrayList<Integer> yearValues = new ArrayList<>();
    ArrayList<Integer> yearID = new ArrayList<>();

    // Query to get years
    query =
        "SELECT year_value, year_id FROM ref_year " + "WHERE year_value > 2009 AND year_value <= "
            + now.get(Calendar.YEAR) + "ORDER BY year_value";

    try { // Runs the query
      stmt = con.createStatement();
      rs = stmt.executeQuery(query);

      // Loops through and adds query results into arrayLists
      while (rs.next()) {
        yearValues.add(rs.getInt("year_value"));
        yearID.add(rs.getInt("year_id"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (stmt != null) {
        stmt.close();
      }
    }

    // Add both arrayLists into map
    years.put("YEAR ID", yearID);
    years.put("YEAR VALUE", yearValues);

    return years;
  }

  private static ResultSet amountQueryHelper(char yearTypeVal) throws SQLException {
    try {
      stmt = con.createStatement();

      if (Character.toUpperCase(yearTypeVal) == 'B') {
        rs = stmt.executeQuery(query);
      } else {
        rs = stmt.executeQuery(query2);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rs;
  }



  public static int getContractAmount(int year, char yearTypeVal) throws SQLException {
    query = "SELECT SUM(maximum_contract_amount) sumContractAmount "
        + "FROM agreement_snapshot WHERE document_code_id IN (5,1,2,7) " + "AND registered_year = "
        + year + " AND original_version_flag = 'Y'";

    // Selects from different table
    query2 = "SELECT SUM(maximum_contract_amount) sumContractAmount "
        + "FROM agreement_snapshot_cy WHERE document_code_id IN (5,1,2,7) "
        + "AND registered_year = " + year + " AND original_version_flag = 'Y'";

    rs = amountQueryHelper(yearTypeVal);
    return rs.getInt("sumContractAmount");
  }
  // Nycha Contracts Blanket agreement widget count


  public static int getNychaBlanketContractsCount(int year, char yearTypeVal) throws SQLException {
    query = "select  count(distinct contract_number ) aCount  from all_agreements"
        + "   where  agreement_type_id in (1) " + "and (2019 between star_year and end_year)";
    rs = amountQueryHelper(yearTypeVal);
    int count = 0;
    while (rs.next()) {
      count = rs.getInt("aCount");
    }
    return count;
  }


  // Capital Spending widget Details count

  public static int getCapitalSpendingDetailsCount(int year, char yearTypeVal) throws SQLException {
    query =
        "select(  (select count(*)   from disbursement_line_item_Details  where   spending_category_id = 3 and  fiscal_year= 2016 ) ) aCount";
    rs = amountQueryHelper(yearTypeVal);
    int count = 0;
    while (rs.next()) {
      count = rs.getInt("aCount");
    }

    return count;

  }

  public static int getCapitalSpendingContractsDetailsCount(int year, char yearTypeVal)
      throws SQLException {
    query =
        "select(  (select count(*)   from disbursement_line_item_Details  where spending_category_id = 3 and fiscal_year= 2016 and contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1')))  aCount";

    rs = amountQueryHelper(yearTypeVal);
    int count = 0;
    while (rs.next()) {
      count = rs.getInt("aCount");
    }

    return count;

  }
}
