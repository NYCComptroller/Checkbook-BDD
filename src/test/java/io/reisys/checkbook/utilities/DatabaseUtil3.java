package io.reisys.checkbook.utilities;

import java.math.BigDecimal;
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

public class DatabaseUtil3 {

	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static String query = null;
	private static String query2 = null;

	// Establishes connection
	public static void connectToDatabase() throws ClassNotFoundException, SQLException {
		if (con == null) {
			EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
			String URL = variables.getProperty("database.connection.url3");
			Properties props = new Properties();
			props.setProperty("user", variables.getProperty("database.user3"));
			props.setProperty("password", variables.getProperty("database.password3"));
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
		query = "SELECT year_value, year_id FROM ref_year " + "WHERE year_value > 2009 AND year_value <= "
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
			if (stmt != null)
				stmt.close();
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
				+ "FROM agreement_snapshot WHERE document_code_id IN (5,1,2,7) " + "AND registered_year = " + year
				+ " AND original_version_flag = 'Y'";

		// Selects from different table
		query2 = "SELECT SUM(maximum_contract_amount) sumContractAmount "
				+ "FROM agreement_snapshot_cy WHERE document_code_id IN (5,1,2,7) " + "AND registered_year = " + year
				+ " AND original_version_flag = 'Y'";

		rs = amountQueryHelper(yearTypeVal);
		return rs.getInt("sumContractAmount");
	}

	private static String formatNumber(BigDecimal num) {
		String formattedNum = null;
		String moneyChar = null;
		int count = 0;

		while (num.compareTo(new BigDecimal(999)) >= 0) {
			num = num.divide(new BigDecimal(1000));
			count++;
		}

		switch (count) {
		case 1:
			moneyChar = "K";
			break;
		case 2:
			moneyChar = "M";
			break;
		case 3:
			moneyChar = "B";
			break;
		case 4:
			moneyChar = "T";
			break;
		case 5:
			moneyChar = "Q";
			break;
		default:
			moneyChar = "";
			break;
		}

		formattedNum = num.setScale(1, BigDecimal.ROUND_HALF_UP).toString();

		return "$" + formattedNum + moneyChar;
	}

	private static String formatNumber2(BigDecimal num) {
		String formattedNum = null;
		String moneyChar = null;
		int count = 0;

		while (num.compareTo(new BigDecimal(999)) >= 0) {
			num = num.divide(new BigDecimal(1000));
			count++;
		}

		switch (count) {
		case 1:
			moneyChar = "K";
			break;
		case 2:
			moneyChar = "M";
			break;
		case 3:
			moneyChar = "B";
			break;
		case 4:
			moneyChar = "T";
			break;
		case 5:
			moneyChar = "Q";
			break;
		default:
			moneyChar = "";
			break;
		}

		formattedNum = num.setScale(2, BigDecimal.ROUND_HALF_UP).toString();

		return "$" + formattedNum + moneyChar;
	}
	//

	// Payroll Spending widget Details count

	public static int getPayrollSpendingDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select(  (select count(*)   from disbursement_line_item_Details  where   spending_category_id = 2 and  fiscal_year= 2016 )  ) aCount ";

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	// Capital Spending widget Details count

	public static int getCapitalSpendingDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select(  (select count(*)   from disbursement_line_item_Details  where   spending_category_id = 3 and  fiscal_year= 2016 ) ) aCount";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	public static int getCapitalSpendingContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select(  (select count(*)   from disbursement_line_item_Details  where spending_category_id = 3 and fiscal_year= 2016 and contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1')))  aCount";

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	// MWBECapital Spending widget Details count

	public static int getMWBECapitalSpendingDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select(  (select count(*)   from disbursement_line_item_Details  where   spending_category_id = 3  and   minority_type_id in (2,3,4,5,9)  and fiscal_year= 2016 ) ) aCount";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	public static int geMWBECapitalSpendingContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select(  (select count(*)   from disbursement_line_item_Details  where spending_category_id = 3 and   minority_type_id in (2,3,4,5,9) and fiscal_year= 2016 and contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1')))  aCount";

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}
	// Contract Spending widget Details count

	public static int getContractSpendingDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select(  (select count(*)   from disbursement_line_item_Details  where   spending_category_id = 1 and  fiscal_year= 2016 ) + "
				+ "  (select count(*) from subcontract_spending_Details where fiscal_year = 2016 )  ) aCount";

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	public static int getContractSpendingContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		// query = "select( (select count(*) from disbursement_line_item_Details where
		// spending_category_id = 1 and fiscal_year= 2016 and contract_document_code in
		// ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1'))) aCount";
		query = " select(  (select count(*)   from disbursement_line_item_Details  where spending_category_id = 1"
				+ " and fiscal_year= 2016 and contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1')) +"
				+ " (select count(*) from subContract_spending_details  where fiscal_year =2016 and spending_category_id = 1 "
				+ "and contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1')))  acount";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	//// MWBE Contract Spending widget Details count
	public static int getMWBEContractSpendingDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select count(*) aCount from disbursement_line_item_Details  where   spending_category_id = 1"
				+ " and minority_type_id in (2,3,4,5,9)" + "and   fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	public static int getMWBEContractSpendingContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		// query = "select( (select count(*) from disbursement_line_item_Details where
		// spending_category_id = 1 and fiscal_year= 2016 and contract_document_code in
		// ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1'))) aCount";
		query = "   select count(*) aCount  from disbursement_line_item_Details  where spending_category_id = 1"
				+ "  and minority_type_id in (2,3,4,5,9) and contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') "
				+ "and   fiscal_year = " + year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	public static int getMWBEContractSpendingSubVendorsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select count(*) aCount from subcontract_spending_Details  where   spending_category_id = 1"
				+ " and minority_type_id in (2,3,4,5,9)" + "and   fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	// Trust agency Spending widget Details count

	public static int getTrustAgencySpendingDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = " select count(*) aCount  from disbursement_line_item_Details  where   spending_category_id = 5 "
				+ "and   fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	public static int getTrustAgencySpendingContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select count(*) aCount from disbursement_line_item_Details  where spending_category_id = 5 and  contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1')"
				+ "and   fiscal_year = " + year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	// MWBE Trust agency Spending widget Details count

	public static int getMWBETrustAgencySpendingDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select count(*) aCount  from disbursement_line_item_Details  where   spending_category_id = 5  and minority_type_id in (2,3,4,5,9) "
				+ "and   fiscal_year = " + year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	public static int getMWBETrustAgencySpendingContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select count(*)  aCount  from disbursement_line_item_Details  where spending_category_id = 5 and  minority_type_id in (2,3,4,5,9)  and contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1')"
				+ "and   fiscal_year = " + year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	// Other Spending widget Details count

	public static int getOtherSpendingDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select count(*) aCount  from disbursement_line_item_Details  where   spending_category_id = 4 "
				+ "and   fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	// MWBE Other Spending widget Details count

	public static int getMWBEOtherSpendingDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select count(*)  aCount  from disbursement_line_item_Details  where   spending_category_id = 4 and  minority_type_id in (2,3,4,5,9)"
				+ "and   fiscal_year = " + year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}

		return count;

	}

	// Spending details amounts

	public static String getTotalSpendingMWBEDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details"
				+ " WHERE  minority_type_id in (2,3,4,5,9) and  fiscal_year = " + year;
		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getTotalSpendingMWBESubVendorsDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM subcontract_spending_details"
				+ " WHERE  minority_type_id in (2,3,4,5,9) and  fiscal_year = " + year;
		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getPayrollSpendingMWBEDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='2' and  minority_type_id in (2,3,4,5,9) and   fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getContractsSpendingMWBEDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='1' and  minority_type_id in (2,3,4,5,9) and   fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getContractsSpendingMWBESubVendorsDetailsAmount(int year, char yearTypeVal)
			throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM subcontract_spending_details "
				+ " WHERE spending_category_id='1' and  minority_type_id in (2,3,4,5,9) and   fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getCapitalContractsSpendingMWBEDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='3' and  minority_type_id in (2,3,4,5,9) and   fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getTrustAgencySpendingMWBEDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='5' and  minority_type_id in (2,3,4,5,9) and  fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getOtherSpendingMWBEDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='4' and  minority_type_id in (2,3,4,5,9) and  fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	// Spending Widget Contracts widget details amounts

	public static String getTotalSpendingMWBEContractsDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details"
				+ " WHERE   contract_document_code in( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and  minority_type_id in (2,3,4,5,9) and  fiscal_year = "
				+ year;
		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getContractsSpendingMWBEContractsDetailsAmount(int year, char yearTypeVal)
			throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='1'  and contract_document_code in( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and  minority_type_id in (2,3,4,5,9) and   fiscal_year = "
				+ year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getCapitalContractsSpendingMWBEContractsDetailsAmount(int year, char yearTypeVal)
			throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='3' and  contract_document_code in( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and  minority_type_id in (2,3,4,5,9) and   fiscal_year = "
				+ year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getTrustAgencySpendingMWBEContractsDetailsAmount(int year, char yearTypeVal)
			throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='5' andcontract_document_code in( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and  minority_type_id in (2,3,4,5,9) and   fiscal_year = "
				+ year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	// Spending Amount
	public static String getSpendingAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details"
				+ " WHERE fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getCapitalSpendingAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details"
				+ " WHERE  spending_category_id='3' and fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getPayrollSpendingAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details"
				+ " WHERE  spending_category_id='2' and fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getContractSpendingAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details"
				+ " WHERE  spending_category_id='1' and fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getOtherSpendingAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt FROM disbursement_line_item_details"
				+ " WHERE  spending_category_id='4' and fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getTrustAgencySpendingAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details"
				+ " WHERE  spending_category_id='5' and fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	// Spending details amounts

	public static String getTotalSpendingDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details"
				+ " WHERE fiscal_year = " + year;
		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getPayrollSpendingDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='2' and  fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getContractsSpendingDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='1' and  fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getCapitalContractsSpendingDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='3' and   fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getTrustAgencySpendingDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='5' and  fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getOtherSpendingDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='4' and  fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	// Spending Widget Contracts widget details amounts

	public static String getTotalSpendingContractsDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details"
				+ " WHERE   contract_document_code in( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and fiscal_year = "
				+ year;
		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getContractsSpendingContractsDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='1'  and contract_document_code in( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and  fiscal_year = "
				+ year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getCapitalContractsSpendingContractsDetailsAmount(int year, char yearTypeVal)
			throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='3' and  contract_document_code in( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and  fiscal_year = "
				+ year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getTrustAgencySpendingContractsDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM disbursement_line_item_details "
				+ " WHERE spending_category_id='5' and contract_document_code in( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and   fiscal_year = "
				+ year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	// Total Spending widget counts

	public static int getTotalSpendingAgenciesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct agency_id) aCount from disbursement_line_item_details where fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTotalSpendingChecksCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(*) aCount from disbursement_line_item_details where fiscal_year =" + year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTotalSpendingPrimeVendorsCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct vendor_id) aCount from disbursement_line_item_details where fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTotalSpendingExpCategoriesCount(int year, char yearTypeVal) throws SQLException {

		// query = "SELECT COUNT(distinct expenditure_object_id) aCount from
		// disbursement_line_item_details where fiscal_year =" + year ;
		query = "SELECT COUNT(distinct expenditure_object_code) aCount from disbursement_line_item_details where fiscal_year ="
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTotalSpendingContractsCount(int year, char yearTypeVal) throws SQLException {
		query = " SELECT count( distinct ( COALESCE(master_contract_number,contract_number)  )) aCount FROM disbursement_line_item_details"
				+ " WHERE  contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1')   and fiscal_year = "
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// Payroll Spending widget counts

	public static int getPayrollSpendingAgenciesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct agency_id) aCount from disbursement_line_item_details where spending_category_id='2'  and fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getPayrollSpendingExpCategoriesCount(int year, char yearTypeVal) throws SQLException {
		// query = "SELECT COUNT(distinct expenditure_object_id) aCount from
		// disbursement_line_item_details where spending_category_id='2' and fiscal_year
		// =" + year ;
		query = "SELECT COUNT(distinct expenditure_object_code) aCount from disbursement_line_item_details where spending_category_id='2' and fiscal_year ="
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// Capital Spending widget counts

	public static int getCapitalSpendingChecksCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(*) aCount from disbursement_line_item_details where spending_category_id='3' and fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getCapitalSpendingAgenciesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct agency_id) aCount from disbursement_line_item_details where spending_category_id='3'  and fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getCapitalSpendingPrimeVendorsCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct vendor_id) aCount from disbursement_line_item_details where spending_category_id='3' and fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getCapitalSpendingExpCategoriesCount(int year, char yearTypeVal) throws SQLException {
		// query = "SELECT COUNT(distinct expenditure_object_id) aCount from
		// disbursement_line_item_details where spending_category_id='3' and fiscal_year
		// =" + year ;
		query = "SELECT COUNT(distinct expenditure_object_code) aCount from disbursement_line_item_details where spending_category_id='3' and fiscal_year ="
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getCapitalSpendingContractsCount(int year, char yearTypeVal) throws SQLException {
		query = " SELECT count( distinct ( COALESCE(master_contract_number,contract_number)  )) aCount FROM disbursement_line_item_details"
				+ " WHERE  contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and  spending_category_id='3'  and fiscal_year = "
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// Contract Spending widget counts

	public static int getContractSpendingChecksCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(*) aCount from disbursement_line_item_details where spending_category_id='1' and fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getContractSpendingPrimeVendorsCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct vendor_id) aCount from disbursement_line_item_details where spending_category_id='1' and fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getContractSpendingAgenciesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct agency_id) aCount from disbursement_line_item_details where spending_category_id='1'  and fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getContractSpendingExpCategoriesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct expenditure_object_id) aCount from disbursement_line_item_details where spending_category_id='1' and fiscal_year ="
				+ year;
		// query= "SELECT COUNT(distinct expenditure_object_code) aCount from
		// disbursement_line_item_details where spending_category_id='1' and fiscal_year
		// =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getContractSpendingContractsCount(int year, char yearTypeVal) throws SQLException {
		query = " SELECT count( distinct ( COALESCE(master_contract_number,contract_number)  )) aCount FROM disbursement_line_item_details"
				+ " WHERE  contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and  spending_category_id='1'  and fiscal_year = "
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// TrustAgency Spending widget counts

	public static int getTrustAgencySpendingChecksCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(*) aCount from disbursement_line_item_details where spending_category_id='5' and fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTrustAgencySpendingPrimeVendorsCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct vendor_id) aCount from disbursement_line_item_details where spending_category_id='5' and fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTrustAgencySpendingAgenciesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct agency_id) aCount from disbursement_line_item_details where spending_category_id='5'  and fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTrustAgencySpendingExpCategoriesCount(int year, char yearTypeVal) throws SQLException {
		// query = "SELECT COUNT(distinct expenditure_object_id) aCount from
		// disbursement_line_item_details where spending_category_id='5' and fiscal_year
		// =" + year ;
		query = "SELECT COUNT(distinct expenditure_object_code) aCount from disbursement_line_item_details where spending_category_id='5' and fiscal_year ="
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTrustAgencySpendingContractsCount(int year, char yearTypeVal) throws SQLException {
		// query = "SELECT count(DISTINCT document_id) as aCount FROM"
		// + " aggregateon_mwbe_spending_contract WHERE type_of_year = 'B' AND
		// spending_category_id='5' and year_id = "+ year;

		query = " SELECT count( distinct ( COALESCE(master_contract_number,contract_number)  )) aCount FROM disbursement_line_item_details"
				+ " WHERE  contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and  spending_category_id='5'  and fiscal_year = "
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// Other Spending widget counts

	public static int getOtherSpendingChecksCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(*) aCount from disbursement_line_item_details where spending_category_id='4' and fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getOtherSpendingPrimeVendorsCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct vendor_id) aCount from disbursement_line_item_details where spending_category_id='4' and fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getOtherSpendingAgenciesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct agency_id) aCount from disbursement_line_item_details where spending_category_id='4'  and fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getOtherSpendingExpCategoriesCount(int year, char yearTypeVal) throws SQLException {
		// query = "SELECT COUNT(distinct expenditure_object_id) aCount from
		// disbursement_line_item_details where spending_category_id='4' and fiscal_year
		// =" + year ;
		query = "SELECT COUNT(distinct expenditure_object_code) aCount from disbursement_line_item_details where spending_category_id='4' and fiscal_year ="
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// Spending widget Details count

	public static int getTotalSpendingDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select(  (select count(*)   from disbursement_line_item_Details  where fiscal_year= 2016 ) + "
				+ "  (select count(*) from subcontract_spending_Details where fiscal_year = 2016 )  ) aCount";

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTotalSpendingContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select(  (select count(*)   from disbursement_line_item_Details  where fiscal_year= 2016 and contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1')) + "
				+ "  (select count(*) from subcontract_spending_Details where fiscal_year = 2016 and contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1'))  ) aCount";

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// MWBE
	// Total Spending widget counts

	public static int getTotalSpendingMWBEAgenciesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct agency_id) aCount from disbursement_line_item_details where minority_type_id in (2,3,4,5,9) and  fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTotalSpendingMWBEChecksCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(*) aCount from disbursement_line_item_details where minority_type_id in (2,3,4,5,9)  and  fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTotalSpendingMWBEPrimeVendorsCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct vendor_id) aCount from disbursement_line_item_details where minority_type_id in (2,3,4,5,9)  and  fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTotalSpendingMWBEExpCategoriesCount(int year, char yearTypeVal) throws SQLException {

		query = "SELECT COUNT(distinct expenditure_object_id) aCount from disbursement_line_item_details where minority_type_id in (2,3,4,5,9)  and  fiscal_year ="
				+ year;
		// query = "SELECT COUNT(distinct expenditure_object_code) aCount from
		// disbursement_line_item_details where fiscal_year =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTotalSpendingMWBEContractsCount(int year, char yearTypeVal) throws SQLException {
		query = " SELECT count( distinct ( COALESCE(master_contract_number,contract_number)  )) aCount FROM disbursement_line_item_details"
				+ " WHERE  contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1')   and  minority_type_id in (2,3,4,5,9)  and fiscal_year = "
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTotalSpendingMWBEIndustriesCount(int year, char yearTypeVal) throws SQLException {
		query = " SELECT count( distinct  industry_type_id ) aCount FROM disbursement_line_item_details"
				+ " WHERE   minority_type_id in (2,3,4,5,9)  and fiscal_year = " + year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTotalSpendingMWBESubVendorsCount(int year, char yearTypeVal) throws SQLException {
		query = " SELECT count( distinct  vendor_id ) aCount FROM subcontract_spending_Details"
				+ " WHERE   minority_type_id in (2,3,4,5,9)  and fiscal_year = " + year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// Payroll Spending widget counts

	public static int getPayrollSpendingMWBEAgenciesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct agency_id) aCount from disbursement_line_item_details where spending_category_id='2'   and minority_type_id in (2,3,4,5,9)  and fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getPayrollSpendingMWBEExpCategoriesCount(int year, char yearTypeVal) throws SQLException {
		// query = "SELECT COUNT(distinct expenditure_object_id) aCount from
		// disbursement_line_item_details where spending_category_id='2' and fiscal_year
		// =" + year ;
		query = "SELECT COUNT(distinct expenditure_object_code) aCount from disbursement_line_item_details where spending_category_id='2' and minority_type_id in (2,3,4,5,9)  and  fiscal_year ="
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// MWBE Capital Spending widget counts

	public static int getCapitalSpendingMWBEChecksCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(*) aCount from disbursement_line_item_details where spending_category_id='3' and  minority_type_id in (2,3,4,5,9)  and fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getCapitalSpendingMWBEAgenciesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct agency_id) aCount from disbursement_line_item_details where spending_category_id='3'  and  minority_type_id in (2,3,4,5,9)  and fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getCapitalSpendingMWBEPrimeVendorsCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct vendor_id) aCount from disbursement_line_item_details where spending_category_id='3' and minority_type_id in (2,3,4,5,9)  and  fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getCapitalSpendingMWBEExpCategoriesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct expenditure_object_id) aCount from disbursement_line_item_details where spending_category_id='3' and minority_type_id in (2,3,4,5,9)  and  fiscal_year ="
				+ year;
		// query = "SELECT COUNT(distinct expenditure_object_code) aCount from
		// disbursement_line_item_details where spending_category_id='3' and fiscal_year
		// =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getCapitalSpendingMWBEContractsCount(int year, char yearTypeVal) throws SQLException {
		query = " SELECT count( distinct ( COALESCE(master_contract_number,contract_number)  )) aCount FROM disbursement_line_item_details"
				+ " WHERE  contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and  spending_category_id='3'  and minority_type_id in (2,3,4,5,9)  and  fiscal_year = "
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// MWBE Contract Spending widget counts

	public static int getContractSpendingMWBEChecksCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(*) aCount from disbursement_line_item_details where spending_category_id='1' and  minority_type_id in (2,3,4,5,9)  and fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getContractSpendingMWBEPrimeVendorsCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct vendor_id) aCount from disbursement_line_item_details where spending_category_id='1' and minority_type_id in (2,3,4,5,9)  and  fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getContractSpendingMWBEAgenciesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct agency_id) aCount from disbursement_line_item_details where spending_category_id='1'  and minority_type_id in (2,3,4,5,9)  and  fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getContractSpendingMWBEExpCategoriesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct expenditure_object_id) aCount from disbursement_line_item_details where spending_category_id='1' and  minority_type_id in (2,3,4,5,9)  and fiscal_year ="
				+ year;
		// query= "SELECT COUNT(distinct expenditure_object_code) aCount from
		// disbursement_line_item_details where spending_category_id='1' and fiscal_year
		// =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getContractSpendingMWBEContractsCount(int year, char yearTypeVal) throws SQLException {
		query = " SELECT count( distinct ( COALESCE(master_contract_number,contract_number)  )) aCount FROM disbursement_line_item_details"
				+ " WHERE  contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and  spending_category_id='1'  and minority_type_id in (2,3,4,5,9)  and  fiscal_year = "
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// MWBE TrustAgency Spending widget counts

	public static int getTrustAgencySpendingMWBEChecksCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(*) aCount from disbursement_line_item_details where spending_category_id='5' and  minority_type_id in (2,3,4,5,9)  and fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTrustAgencySpendingMWBEPrimeVendorsCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct vendor_id) aCount from disbursement_line_item_details where spending_category_id='5' and  minority_type_id in (2,3,4,5,9)  and fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTrustAgencySpendingMWBEAgenciesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct agency_id) aCount from disbursement_line_item_details where spending_category_id='5'  and minority_type_id in (2,3,4,5,9)  and  fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTrustAgencySpendingMWBEExpCategoriesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct expenditure_object_id) aCount from disbursement_line_item_details where spending_category_id='5' and minority_type_id in (2,3,4,5,9)  and  fiscal_year ="
				+ year;
		// query = "SELECT COUNT(distinct expenditure_object_code) aCount from
		// disbursement_line_item_details where spending_category_id='5' and fiscal_year
		// =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTrustAgencySpendingMWBEContractsCount(int year, char yearTypeVal) throws SQLException {
		// query = "SELECT count(DISTINCT document_id) as aCount FROM"
		// + " aggregateon_mwbe_spending_contract WHERE type_of_year = 'B' AND
		// spending_category_id='5' and year_id = "+ year;

		query = " SELECT count( distinct ( COALESCE(master_contract_number,contract_number)  )) aCount FROM disbursement_line_item_details"
				+ " WHERE  contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1') and  spending_category_id='5'  and minority_type_id in (2,3,4,5,9)  and  fiscal_year = "
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// MWBE Other Spending widget counts

	public static int getOtherSpendingMWBEChecksCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(*) aCount from disbursement_line_item_details where spending_category_id='4' and minority_type_id in (2,3,4,5,9)  and fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getOtherSpendingMWBEPrimeVendorsCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct vendor_id) aCount from disbursement_line_item_details where spending_category_id='4' and minority_type_id in (2,3,4,5,9)  and  fiscal_year ="
				+ year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getOtherSpendingMWBEAgenciesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct agency_id) aCount from disbursement_line_item_details where spending_category_id='4'  and  minority_type_id in (2,3,4,5,9)  and fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getOtherSpendingMWBEExpCategoriesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct expenditure_object_id) aCount from disbursement_line_item_details where spending_category_id='4' and minority_type_id in (2,3,4,5,9)  and  fiscal_year ="
				+ year;
		// query = "SELECT COUNT(distinct expenditure_object_code) aCount from
		// disbursement_line_item_details where spending_category_id='4' and fiscal_year
		// =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// Spending widget Details count

	public static int getTotalSpendingMWBEDetailsCount(int year, char yearTypeVal) throws SQLException {
		// query = "select( (select count(*) from disbursement_line_item_Details where
		// minority_type_id in (2,3,4,5,9) and fiscal_year= 2016 ) + "
		// +" (select count(*) from subcontract_spending_Details where minority_type_id
		// in (2,3,4,5,9) and fiscal_year = 2016 ) ) aCount";
		query = "select count(*) aCount  from disbursement_line_item_Details  where minority_type_id in (2,3,4,5,9)  and  fiscal_year= "
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTotalSpendingMWBEContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		// query = "select( (select count(*) from disbursement_line_item_Details where
		// minority_type_id in (2,3,4,5,9) and fiscal_year= 2016 and
		// contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1',
		// 'DO1','MA1','MMA1')) + "
		// +" (select count(*) from subcontract_spending_Details where minority_type_id
		// in (2,3,4,5,9) and fiscal_year = 2016 and contract_document_code in ( 'CT1',
		// 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1')) ) aCount";
		query = "select count(*) aCount  from disbursement_line_item_Details  where  minority_type_id in (2,3,4,5,9)  and contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1','PO1')   and  fiscal_year ="
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getTotalSpendingMWBESubVendorsDetailsCount(int year, char yearTypeVal) throws SQLException {
		// query = "select( (select count(*) from disbursement_line_item_Details where
		// minority_type_id in (2,3,4,5,9) and fiscal_year= 2016 ) + "
		// +" (select count(*) from subcontract_spending_Details where minority_type_id
		// in (2,3,4,5,9) and fiscal_year = 2016 ) ) aCount";
		query = "select count(*) aCount  from subcontract_spending_Details  where minority_type_id in (2,3,4,5,9)  and  fiscal_year= "
				+ year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// Sub Vendors Spending

	// Total Spending widget counts

	public static int getSubVendorsTotalSpendingAgenciesCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct agency_id) aCount from subcontract_spending_details where fiscal_year =" + year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getSubVendorsTotalSpendingChecksCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(*) aCount from subcontract_spending_details where fiscal_year =" + year;
		// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
		// fiscal_year =" + year ;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getSubVendorsTotalSpendingPrimeVendorsCount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT COUNT(distinct prime_vendor_id) aCount from subcontract_spending_details where fiscal_year ="
				+ year;

		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getSubVendorsTotalSpendingSubVendorsCount(int year, char yearTypeVal) throws SQLException {

		query = "SELECT COUNT(distinct vendor_id) aCount from subcontract_spending_details where fiscal_year =" + year;
		// query = "SELECT COUNT(distinct expenditure_object_code) aCount from
		// disbursement_line_item_details where fiscal_year =" + year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getSubVendorsTotalSpendingSubContractsCount(int year, char yearTypeVal) throws SQLException {
		query = " SELECT count(*) aCount from (select  distinct reference_document_number,contract_number ,Sub_contract_id FROM subcontract_spending_details"
				+ " WHERE  contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1')   and fiscal_year = "
				+ year + ") a";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getSubVendorsTotalSpendingSubContractsdetailsCount(int year, char yearTypeVal)
			throws SQLException {
		query = " select count(*) aCount from subcontract_spending_details where "
				+ " contract_document_code in ( 'CT1', 'CTA1', 'POD', 'POC', 'PCC1', 'DO1','MA1','MMA1')"
				+ "fiscal_year = " + year;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	// Spending Amount
	public static String getSubVendorsSpendingAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM subcontract_spending_details"
				+ " WHERE fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static String getSubVendorsTotalSpendingDetailsAmount(int year, char yearTypeVal) throws SQLException {
		query = "SELECT SUM(check_amount) sumSpendingAmt " + "FROM subcontract_spending_details"
				+ " WHERE fiscal_year = " + year;

		rs = amountQueryHelper(yearTypeVal);

		BigDecimal totalSpendingAmount = new BigDecimal(0);

		while (rs.next()) {
			totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
		}
		return formatNumber2(totalSpendingAmount);
		// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	

	/// Active expense contracts details page

	public static int getAEContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = " select  ((select  count(*) from agreement_snapshot  where "
				+ "	 document_code_id in (1,2) and (2016 between starting_year and ending_year)"
				+ "	    and (2016 between effective_begin_year and effective_end_year)) +"
				+ "	    ( select  count(*) from sub_agreement_snapshot  where contract_number in "
				+ "	 ( select distinct contract_number  from agreement_snapshot  where "
				+ "	 document_code_id in (1,2) and (2016 between starting_year and ending_year)"
				+ "    and (2016 between effective_begin_year and effective_end_year)) and latest_flag ='Y')) aCount";
		rs = amountQueryHelper(yearTypeVal);
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getAEAllContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = " select  ((select  count(*) from agreement_snapshot  where "
				+ "	 document_code_id in (1,2,5) and (2016 between starting_year and ending_year)"
				+ "	    and (2016 between effective_begin_year and effective_end_year)) +"
				+ "	    ( select  count(*) from sub_agreement_snapshot  where contract_number in "
				+ "	 ( select distinct contract_number  from agreement_snapshot  where "
				+ "	 document_code_id in (1,2,5) and (2016 between starting_year and ending_year)"
				+ "    and (2016 between effective_begin_year and effective_end_year)) and latest_flag ='Y')) aCount";
		rs = amountQueryHelper(yearTypeVal);
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getAEMasterContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(distinct contract_number ) aCount  from agreement_snapshot"
				+ "   where  document_code_id in (5,6) "
				+ "and (2016 between effective_begin_year and effective_end_year)"
				+ "and (2016 between starting_year and ending_year)";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getAEMasterContractsModificationDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(distinct contract_number ) aCount  from agreement_snapshot"
				+ "   where  document_code_id in (5,6) "
				+ "and (2016 between effective_begin_year and effective_end_year)"
				+ "and (2016 between starting_year and ending_year)"
				+ "and maximum_contract_amount <> original_contract_amount";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getAEContractsModificationDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select (( select  count(distinct contract_number )from agreement_snapshot"
				+ "   where  document_code_id in (1,2) " + "and (2016 between starting_year and ending_year)"
				+ "and maximum_contract_amount <> original_contract_amount"
				+ "	    and (2016 between effective_begin_year and effective_end_year)) +"
				+ "	    ( select  count(*) from sub_agreement_snapshot  where contract_number in "
				+ "	 ( select distinct contract_number  from agreement_snapshot  where "
				+ "	 document_code_id in (1,2) and (2016 between starting_year and ending_year)"
				+ "and maximum_contract_amount <> original_contract_amount"
				+ "    and (2016 between effective_begin_year and effective_end_year)) and latest_flag ='Y')) aCount";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	

	// Registered Expense Contracts Details
	public static int getREContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(distinct contract_number ) aCount  from agreement_snapshot"
				+ "   where  document_code_id in (1,2) " + "and registered_year = 2016"
				+ "and (2016 between starting_year and ending_year)";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getREContractsMasterDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(distinct contract_number ) aCount  from agreement_snapshot"
				+ "   where  document_code_id in (5,6) " + "and registered_year = 2016"
				+ "and (2016 between starting_year and ending_year)";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getREContractsMasterModificationsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(distinct contract_number ) aCount  from agreement_snapshot"
				+ "   where  document_code_id in (5,6) " + "and registered_year = 2016"
				+ "and (2016 between starting_year and ending_year)"
				+ "and maximum_contract_amount <> original_contract_amount";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getREContractsModificationsDetailsCount(int year, char yearTypeVal) throws SQLException {

		query = "select (( select  count(distinct contract_number )from agreement_snapshot"
				+ "   where  document_code_id in (1,2) " + "and (2016 between starting_year and ending_year)"
				+ "and maximum_contract_amount <> original_contract_amount" + " and( registered_year = 2016) ) +"
				+ "	    ( select  count(*) from sub_agreement_snapshot  where contract_number in "
				+ "	 ( select distinct contract_number  from agreement_snapshot  where "
				+ "	 document_code_id in (1,2) and (2016 between starting_year and ending_year)"
				+ "and maximum_contract_amount <> original_contract_amount"
				+ "    and ( registered_year = 2016)) and latest_flag ='Y')) aCount";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getREAllContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select ((select  count(distinct contract_number ) aCount  from agreement_snapshot"
				+ "   where  document_code_id in (1,2) " + "and (2016 between starting_year and ending_year)"
				+ "and (registered_year = 2016)) +"
				+ "	    ( select  count(*) from sub_agreement_snapshot  where contract_number in "
				+ "	 ( select distinct contract_number  from agreement_snapshot  where "
				+ "	 document_code_id in (1,2) and (2016 between starting_year and ending_year)"
				+ "    and (registered_year = 2016)) and latest_flag ='Y')) aCount";
		;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getREAll1ContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = " select  ((select  count(*) from agreement_snapshot  where "
				+ "	 document_code_id in (1,2,5) and (2016 between starting_year and ending_year)"
				+ "	    and (registered_year = 2016)) +"
				+ "	    ( select  count(*) from sub_agreement_snapshot  where contract_number in "
				+ "	 ( select distinct contract_number  from agreement_snapshot  where "
				+ "	 document_code_id in (1,2,5) and (2016 between starting_year and ending_year)"
				+ "    and (registered_year = 2016)) and latest_flag ='Y')) aCount";
		rs = amountQueryHelper(yearTypeVal);

		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}
	// Registered Revenue Details pages

	public static int getRRContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(distinct contract_number ) aCount  from agreement_snapshot"
				+ "   where  document_code_id in (7) " + "and registered_year = 2016"
				+ "and (2016 between starting_year and ending_year)";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	public static int getRRContractsModificationsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(distinct contract_number ) aCount  from agreement_snapshot"
				+ "   where  document_code_id in (7) " + "and registered_year = 2016"
				+ "and (2016 between starting_year and ending_year)"
				+ "and maximum_contract_amount <> original_contract_amount";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

				
				
			//NYCEDC Spending	
				public static int getNYCEDCTotalSpendingAllTransactionCount(char yearTypeVal) throws SQLException {
					query = "select COUNT(*) aCount from  disbursement_line_item_details";
					// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
					// fiscal_year =" + year ;

							rs = amountQueryHelper(yearTypeVal);
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("aCount");
					}
					return count;
				}
				
				//NYCEDC Spending	
				public static int getNYCEDCTotalSpendingTransactionCount(char yearTypeVal) throws SQLException {
					query = "select COUNT(*) aCount from  disbursement_line_item_details where agency_id ='9000'";
					// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
					// fiscal_year =" + year ;

							rs = amountQueryHelper(yearTypeVal);
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("aCount");
					}
					return count;
				}
				
				public static int getNYCEDCTotalSpendingTransactionCount(int year, char yearTypeVal) throws SQLException {
					// query = "SELECT COUNT(*) aCount " +
					// "FROM payroll where fiscal_year= " + year ;

					query = "select COUNT(*) aCount from  disbursement_line_item_details where fiscal_year = " + year;
					// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
					// fiscal_year =" + year ;

							rs = amountQueryHelper(yearTypeVal);
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("aCount");
					}
					return count;
				}
				
		
				
				//NYCEDC Contracts	
				public static int getNYCEDCAEContractsAllTransactionCount(char yearTypeVal) throws SQLException {
					query = "SELECT COUNT(*) aCount  from contracts_detailed_transactions where " 
							+ "latest_flag ='Y' and status_flag ='A' and type_of_year ='B' and if_for_all_years ='Y' and is_vendor_flag ='N'";
					// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
					// fiscal_year =" + year ;

							rs = amountQueryHelper(yearTypeVal);
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("aCount");
					}
					return count;
				}
				
	
		
}

