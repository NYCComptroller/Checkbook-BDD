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
			String URL = variables.getProperty("database.connection.url2");
			Properties props = new Properties();
			props.setProperty("user", variables.getProperty("database.user2"));
			props.setProperty("password", variables.getProperty("database.password2"));
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

	
	//NYCHA Contracts widget counts
	public static int getNychaBlanketContractsCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(distinct contract_id ) aCount  from all_agreements"
				+ "   where  agreement_type_id in (1) " 
				+ "and ("+ year + "between start_year and end_year)";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}
	
	public static int getNychaPlannedContractsCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(distinct contract_id ) aCount  from all_agreements"
				+ "   where  agreement_type_id in (2) " 
				+ "and ("+ year + "between start_year and end_year)";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}
	
	public static int getNychaPurchaseOrderContractsCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(distinct contract_id ) aCount  from all_agreement_transactions"
				+ "   where  agreement_type_id in (3) " 
				+ "and release_approved_year="+ year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}

	
	public static int getNychaContractsVendorsCount(int year, char yearTypeVal) throws SQLException {
		query =	"select count(distinct vendor) aCount from ( select distinct vendor_id vendor  from all_agreement_transactions"
				+" where  agreement_type_id in (3)" 
				+ " and release_approved_year ="+ year + " union all select  distinct vendor_id vendor  from all_agreements"
				+" where  agreement_type_id in (1,2) and ("+ year +" between start_year and end_year) )a";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}
	
	public static int getNychaContractsAwardMethodsCount(int year, char yearTypeVal) throws SQLException {
		query =	"select count(distinct aw) aCount from ( select distinct award_method_id aw from all_agreement_transactions"
				+" where  agreement_type_id in (3)" 
				+ " and release_approved_year ="+ year + " union all select  distinct award_method_id aw  from all_agreements"
				+" where  agreement_type_id in (1,2) and ("+ year +" between start_year and end_year) )a";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}
	public static int getNychaContractsDepartmentsCount(int year, char yearTypeVal) throws SQLException {
		query =	"select count(distinct dept) aCount from ( select distinct department_id dept from all_agreement_transactions"
				+" where  agreement_type_id in (3)" 
				+ " and release_approved_year ="+ year + " union all select  distinct department_id dept  from all_agreements"
				+" where  agreement_type_id in (1,2) and ("+ year +" between start_year and end_year) )a";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}
	
	public static int getNychaContractsRespCenterCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(distinct responsibility_center_id ) aCount  from all_agreement_transactions"
				+ "   where  agreement_type_id in (1,2,3) " 
				+ "and release_approved_year="+ year ;
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}
	public static int getNychaContractsCount(int year, char yearTypeVal) throws SQLException {
		query =	"select ( select count(distinct contract_id) a from all_agreement_transactions"
				+" where  agreement_type_id in (3)" 
				+ " and release_approved_year ="+ year + ") + ( select  count(distinct contract_id) b  from all_agreements"
				+" where  agreement_type_id in (1,2) and ("+ year +" between start_year and end_year) ) aCount";
		rs = amountQueryHelper(yearTypeVal);
		
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}
	
		
	public static int getNychaBlanketModContractsCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(distinct contract_id ) aCount  from all_agreements"
				+ "   where  agreement_type_id in (1) and total_amount <> original_amount  " 
				+ "and ("+ year + "between start_year and end_year)";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}
	
	public static int getNychaPlannedModContractsCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(distinct contract_id ) aCount  from all_agreements"
				+ "   where  agreement_type_id in (2) and total_amount <> original_amount " 
				+ "and ("+ year + "between start_year and end_year)";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}
	
//Nycha Contracts widget details count	
	public static int getNychaBlanketContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
		query = "select  count(*) aCount  from all_agreement_transactions_by_release_by_line"
				+ "   where  agreement_type_id in (1)  " 
				+ "and ("+ year + "between agreement_start_year and agreement_end_year)";
		rs = amountQueryHelper(yearTypeVal);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("aCount");
		}
		return count;
	}
	
		public static int getNychaPlannedContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
			query = "select  count(*) aCount  from all_agreement_transactions_by_release_by_line"
					+ "   where  agreement_type_id in (2)  " 
					+ "and ("+ year + "between agreement_start_year and agreement_end_year)";
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
		public static int getNychaContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
			query = "select  count(*) aCount  from all_agreement_transactions_by_release_by_line"
					+ "   where  agreement_type_id in (1,2,3)" 
					+ "and ("+ year + "between agreement_start_year and agreement_end_year)";
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
		
		public static int getNychaContractsAllDetailsCount(char yearTypeVal) throws SQLException {
			query = "select  count(*) aCount  from all_agreement_transactions_by_release_by_line"
					+ "   where  agreement_type_id in (1,2,3)" ;
				
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
		
		
		
		public static int getNychaPOContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
			query = "select  count(*) aCount  from all_agreement_transactions_by_release_by_line"
					+ "   where  agreement_type_id in (3)" 
					+ "and ("+ year + "between agreement_start_year and agreement_end_year)";
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
		
		public static int getNychaBlanketModContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
			query = "select  count(*) aCount  from all_agreement_transactions_by_release_by_line"
					+ "   where  agreement_type_id in (1) and  agreement_total_amount <> agreement_original_amount " 
					+ "and ("+ year + "between agreement_start_year and agreement_end_year)";
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
		
		public static int getNychaPlannedModContractsDetailsCount(int year, char yearTypeVal) throws SQLException {
			query = "select  count(*) aCount  from all_agreement_transactions_by_release_by_line"
					+ "   where  agreement_type_id in (2) and  agreement_total_amount <> agreement_original_amount " 
					+ "and ("+ year + "between agreement_start_year and agreement_end_year)";
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
	
	
	
		// NYCHA Total Spending widget counts
		public static int getNYCHATotalSpendingChecksCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(*) aCount from( select distinct document_id, issue_date,vendor_name from all_disbursement_transactions "
		           + "where  check_status NOT IN ('VOIDED') and issue_date_year ="+ year +") a" ;
			// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
			// fiscal_year =" + year ;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}	

		public static int getNYCHATotalSpendingIndustriesCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct industry_type_id) aCount from all_disbursement_transactions where issue_date_year =" + year;
			// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
			// fiscal_year =" + year ;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}	

		public static int getNYCHATotalSpendingExpCategoriesCount(int year, char yearTypeVal) throws SQLException {

			// query = "SELECT COUNT(distinct expenditure_object_id) aCount from
			// disbursement_line_item_details where fiscal_year =" + year ;
			query = " SELECT COUNT(distinct expenditure_type_id) aCount from all_disbursement_transactions where issue_date_year = " + year;
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}

		public static int getNYCHATotalSpendingContractsCount(int year, char yearTypeVal) throws SQLException {
			query = " SELECT count( distinct contract_id) aCount FROM all_disbursement_transactions where issue_date_year ="
					+ year;
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}

	

		public static int getNYCHATotalSpendingFundingSourceCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct funding_source_id) aCount from all_disbursement_transactions where issue_date_year ="
					+ year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}

		

	
		public static int getNYCHATotalSpendingRespCentersCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct responsibility_center_id) aCount from all_disbursement_transactions where issue_date_year ="
					+ year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}

		public static int getNYCHATotalSpendingVendorsCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct vendor_id) aCount from all_disbursement_transactions where issue_date_year ="
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
		// Spending Amount
		public static String getNYCHASpendingAmount(int year, char yearTypeVal) throws SQLException {
			query =  "select sum(a.suma+b.sumb) sumSpendingAmt  from (select sum(adj_distribution_line_amount) suma"
			 + " from all_disbursement_transactions where   issue_date_year = " + year +
			 ") a,   (select sum(check_amount) sumb from all_disbursement_transactions where"
			 + "  issue_date_year =" + year + " and spending_Category_id= '2') b";

			rs = amountQueryHelper(yearTypeVal);

			BigDecimal totalSpendingAmount = new BigDecimal(0);

			while (rs.next()) {
				totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
			}
			return formatNumber(totalSpendingAmount);
			// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
		}

		public static int getNYCHATotalSpendingTransactionCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(*) aCount from all_disbursement_transactions where issue_date_year =" + year;
			// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
			// fiscal_year =" + year ;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
		
		public static int getNYCHATotalSpendingAllTransactionCount(char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(*) aCount from all_disbursement_transactions";
			// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
			// fiscal_year =" + year ;

					rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
		
		

		// Spending details amounts

		public static String getNYCHATotalSpendingDetailsAmount(int year, char yearTypeVal) throws SQLException {
			query =  "select sum(a.suma+b.sumb) sumSpendingAmt  from (select sum(adj_distribution_line_amount) suma"
					 + " from all_disbursement_transactions where   issue_date_year = " + year +
					 ") a,   (select sum(check_amount) sumb from all_disbursement_transactions where"
					 + "  issue_date_year =" + year + " and spending_Category_id= '2') b";
			rs = amountQueryHelper(yearTypeVal);

			BigDecimal totalSpendingAmount = new BigDecimal(0);

			while (rs.next()) {
				totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
			}
			return formatNumber2(totalSpendingAmount);
			// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
		}
		
		
		// Payroll Spending details amounts
		public static String getNYCHAPayrollSpendingAmount(int year, char yearTypeVal) throws SQLException {
			query =  "select sum(check_amount) sumSpendingAmt from all_disbursement_transactions where "
					 + "spending_category_id = 2 and issue_date_year =" + year ;

			rs = amountQueryHelper(yearTypeVal);

			BigDecimal totalSpendingAmount = new BigDecimal(0);

			while (rs.next()) {
				totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
			}
			return formatNumber(totalSpendingAmount);
			// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
		}
		public static String getNYCHAPayrollSpendingDetailsAmount(int year, char yearTypeVal) throws SQLException {
			query =  "select sum(check_amount) sumSpendingAmt from all_disbursement_transactions where "
					 + "spending_category_id = 2 and issue_date_year =" + year ;
			rs = amountQueryHelper(yearTypeVal);

			BigDecimal totalSpendingAmount = new BigDecimal(0);

			while (rs.next()) {
				totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
			}
			return formatNumber2(totalSpendingAmount);
			// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
		}
		
		public static int getNYCHAPayrollSpendingTransactionCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(*) aCount from all_disbursement_transactions where spending_Category_id= '2' and issue_date_year =" + year;
			// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
			// fiscal_year =" + year ;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}

		public static int getNYCHAPayrollSpendingDepartmentsCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct department_id) aCount from all_disbursement_transactions where spending_category_id ='2' and issue_date_year =" + year;
			// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
			// fiscal_year =" + year ;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}

	

		public static int getNYCHAPayrollSpendingExpCategoriesCount(int year, char yearTypeVal) throws SQLException {

			// query = "SELECT COUNT(distinct expenditure_object_id) aCount from
			// disbursement_line_item_details where fiscal_year =" + year ;
			query = " SELECT COUNT(distinct expenditure_type_id) aCount from all_disbursement_transactions where spending_category_id ='2' and issue_date_year = " + year;
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
		public static int getNYCHAPayrollSpendingDetailsCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(*) aCount from all_disbursement_transactions where spending_Category_id= '2' and issue_date_year = " + year;
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}

			return count;

		}
	
	//

		public static int getNYCHAContractSpendingChecksCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(*) aCount from( select distinct document_id, issue_date,vendor_name from all_disbursement_transactions "
			           + "where spending_category_id = '3' and issue_date_year ="+ year +") a" ;
				// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
				//
			// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
			// fiscal_year =" + year ;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}

		public static int getNYCHAContractSpendingIndustriesCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct industry_type_id) aCount from all_disbursement_transactions where spending_category_id = '3' and issue_date_year =" + year;
			// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
			// fiscal_year =" + year ;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}	

		public static int getNYCHAContractSpendingExpCategoriesCount(int year, char yearTypeVal) throws SQLException {

			// query = "SELECT COUNT(distinct expenditure_object_id) aCount from
			// disbursement_line_item_details where fiscal_year =" + year ;
			query = " SELECT COUNT(distinct expenditure_type_id) aCount from all_disbursement_transactions where spending_category_id = '3' and issue_date_year = " + year;
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}

		public static int getNYCHAContractSpendingContractsCount(int year, char yearTypeVal) throws SQLException {
			query = " SELECT count( distinct contract_id) aCount FROM all_disbursement_transactions where  spending_category_id = '3' and issue_date_year ="
					+ year;
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
	
		public static int getNYCHAContractSpendingFundingSourceCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct funding_source_id) aCount from all_disbursement_transactions where spending_category_id = '3' and issue_date_year ="
					+ year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
	
		public static int getNYCHAContractSpendingRespCentersCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct responsibility_center_id) aCount from all_disbursement_transactions where spending_category_id = '3' and issue_date_year ="
					+ year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}

		public static int getNYCHAContractSpendingVendorsCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct vendor_id) aCount from all_disbursement_transactions where spending_category_id = '3' and issue_date_year ="
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
		// Spending Amount
		public static String getNYCHAContractSpendingAmount(int year, char yearTypeVal) throws SQLException {
			query =  "select sum(adj_distribution_line_amount)  sumSpendingAmt "
					 + " from all_disbursement_transactions where  spending_Category_id= '3'and  issue_date_year = " + year ;
				

			rs = amountQueryHelper(yearTypeVal);

			BigDecimal totalSpendingAmount = new BigDecimal(0);

			while (rs.next()) {
				totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
			}
			return formatNumber(totalSpendingAmount);
			// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
		}

		public static int getNYCHAContractSpendingTransactionCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(*) aCount from all_disbursement_transactions where  spending_Category_id= '3'and  issue_date_year =" + year;
			// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
			// fiscal_year =" + year ;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
		

		// Spending details amounts

		public static String getNYCHAContractSpendingDetailsAmount(int year, char yearTypeVal) throws SQLException {
			query =  "select sum(adj_distribution_line_amount)  sumSpendingAmt "
					 + " from all_disbursement_transactions where  spending_Category_id= '3'and  issue_date_year = " + year ;
					
			rs = amountQueryHelper(yearTypeVal);

			BigDecimal totalSpendingAmount = new BigDecimal(0);

			while (rs.next()) {
				totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
			}
			return formatNumber2(totalSpendingAmount);
			// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
		}
		
		//

		public static int getNYCHASection8SpendingChecksCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(*) aCount from( select distinct document_id, issue_date,vendor_name from all_disbursement_transactions "
			           + "where spending_category_id = '1' and issue_date_year ="+ year +") a" ;
				// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
				//
			// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
			// fiscal_year =" + year ;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
	

		public static int getNYCHASection8SpendingExpCategoriesCount(int year, char yearTypeVal) throws SQLException {

			// query = "SELECT COUNT(distinct expenditure_object_id) aCount from
			// disbursement_line_item_details where fiscal_year =" + year ;
			query = " SELECT COUNT(distinct expenditure_type_id) aCount from all_disbursement_transactions where spending_category_id = '1' and issue_date_year = " + year;
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}

	
		public static int getNYCHASection8SpendingFundingSourceCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct funding_source_id) aCount from all_disbursement_transactions where spending_category_id = '1' and issue_date_year ="
					+ year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
	
		public static int getNYCHASection8SpendingRespCentersCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct responsibility_center_id) aCount from all_disbursement_transactions where spending_category_id = '1' and issue_date_year ="
					+ year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}

		public static int getNYCHASection8SpendingVendorsCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct vendor_id) aCount from all_disbursement_transactions where spending_category_id = '1' and issue_date_year ="
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
		// Spending Amount
		public static String getNYCHASection8SpendingAmount(int year, char yearTypeVal) throws SQLException {
			query =  "select sum(adj_distribution_line_amount)  sumSpendingAmt "
					 + " from all_disbursement_transactions where  spending_Category_id= '1'and  issue_date_year = " + year ;
				

			rs = amountQueryHelper(yearTypeVal);

			BigDecimal totalSpendingAmount = new BigDecimal(0);

			while (rs.next()) {
				totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
			}
			return formatNumber(totalSpendingAmount);
			// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
		}

		public static int getNYCHASection8SpendingTransactionCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(*) aCount from all_disbursement_transactions where  spending_Category_id= '1'and  issue_date_year =" + year;
			// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
			// fiscal_year =" + year ;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
		

		// Spending details amounts

		public static String getNYCHASection8SpendingDetailsAmount(int year, char yearTypeVal) throws SQLException {
			query =  "select sum(adj_distribution_line_amount)  sumSpendingAmt "
					 + " from all_disbursement_transactions where  spending_Category_id= '1'and  issue_date_year = " + year ;
					
			rs = amountQueryHelper(yearTypeVal);

			BigDecimal totalSpendingAmount = new BigDecimal(0);

			while (rs.next()) {
				totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
			}
			return formatNumber2(totalSpendingAmount);
			// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
		}
		
		public static int getNYCHAOtherSpendingChecksCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(*) aCount from( select distinct document_id, issue_date,vendor_name from all_disbursement_transactions "
			           + "where spending_category_id = '4' and issue_date_year ="+ year +") a" ;
				// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
				//
			// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
			// fiscal_year =" + year ;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
	

		public static int getNYCHAOtherSpendingExpCategoriesCount(int year, char yearTypeVal) throws SQLException {

			// query = "SELECT COUNT(distinct expenditure_object_id) aCount from
			// disbursement_line_item_details where fiscal_year =" + year ;
			query = " SELECT COUNT(distinct expenditure_type_id) aCount from all_disbursement_transactions where spending_category_id = '4' and issue_date_year = " + year;
			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}

	
		public static int getNYCHAOtherSpendingFundingSourceCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct funding_source_id) aCount from all_disbursement_transactions where spending_category_id = '4' and issue_date_year ="
					+ year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
	
		public static int getNYCHAOtherSpendingRespCentersCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct responsibility_center_id) aCount from all_disbursement_transactions where spending_category_id = '4' and issue_date_year ="
					+ year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}

		public static int getNYCHAOtherSpendingVendorsCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(distinct vendor_id) aCount from all_disbursement_transactions where spending_category_id = '4' and issue_date_year ="
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
		// Spending Amount
		public static String getNYCHAOtherSpendingAmount(int year, char yearTypeVal) throws SQLException {
			query =  "select sum(adj_distribution_line_amount)  sumSpendingAmt "
					 + " from all_disbursement_transactions where  spending_Category_id= '4'and  issue_date_year = " + year ;
				

			rs = amountQueryHelper(yearTypeVal);

			BigDecimal totalSpendingAmount = new BigDecimal(0);

			while (rs.next()) {
				totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
			}
			return formatNumber(totalSpendingAmount);
			// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
		}

		public static int getNYCHAOtherSpendingTransactionCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(*) aCount from all_disbursement_transactions where  spending_Category_id= '4'and  issue_date_year =" + year;
			// query2 = "SELECT COUNT(*) aCount from disbursement_line_item_details where
			// fiscal_year =" + year ;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}
			return count;
		}
		

		// Spending details amounts

		public static String getNYCHAOtherSpendingDetailsAmount(int year, char yearTypeVal) throws SQLException {
			query =  "select sum(adj_distribution_line_amount)  sumSpendingAmt "
					 + " from all_disbursement_transactions where  spending_Category_id= '4'and  issue_date_year = " + year ;
					
			rs = amountQueryHelper(yearTypeVal);

			BigDecimal totalSpendingAmount = new BigDecimal(0);

			while (rs.next()) {
				totalSpendingAmount = rs.getBigDecimal("sumSpendingAmt");
			}
			return formatNumber2(totalSpendingAmount);
			// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
		}
		
	//NYCHA budget widgets
		
		// Budget Widgets

		public static int getNYCHABudgetProgramsCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(Distinct(program_phase_id)) aCount FROM  budget where budget_fiscal_year= " + year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}

			return count;

		}

		public static int getNYCHABudgetExpenseCategoriesCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(Distinct(expenditure_type_id)) aCount FROM  budget where budget_fiscal_year= " + year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}

			return count;
		}

		public static int getNYCHABudgetRespCentersCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(Distinct(responsibility_center_id)) aCount FROM  budget where budget_fiscal_year= " + year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}

			return count;
		}
		public static int getNYCHABudgetFundingSourceCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(Distinct(funding_source_id)) aCount FROM  budget where budget_fiscal_year= " + year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}

			return count;
		}
		
		public static int getNYCHABudgetProjectsCount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(Distinct(gl_project_id)) aCount FROM  budget where budget_fiscal_year= " + year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}

			return count;
		}
		public static String getNYCHABudgetAmount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT SUM(modified_budget) sumBudgetAmt FROM budget WHERE budget_fiscal_year = " + year;

			rs = amountQueryHelper(yearTypeVal);

			BigDecimal totalBudgetAmount = new BigDecimal(0);

			while (rs.next()) {
				totalBudgetAmount = rs.getBigDecimal("sumBudgetAmt");
			}
			return formatNumber(totalBudgetAmount);
			// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
		}

		public static String getNYCHABudgetDetailsAmount(int year, char yearTypeVal) throws SQLException {
			query = "SELECT SUM(SUM(modified_budget) sumBudgetAmt "
					+ "FROM budget WHERE budget_fiscal_year = " + year;

			rs = amountQueryHelper(yearTypeVal);

			BigDecimal totalBudgetAmount = new BigDecimal(0);

			while (rs.next()) {
				totalBudgetAmount = rs.getBigDecimal("sumBudgetAmt");
			}
			return formatNumber2(totalBudgetAmount);
			// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
		}

		public static int getNYCHABudgetDetailsCount1(int year, char yearTypeVal) throws SQLException {
			query = "SELECT COUNT(*)  aCount FROM  budget where budget_fiscal_year= " + year;

			rs = amountQueryHelper(yearTypeVal);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("aCount");
			}

			return count;

		}
				
		//NYCHA Revenue widgets
		
				// Revenue Widgets

				public static int getNYCHARevenueProgramsCount(int year, char yearTypeVal) throws SQLException {
					query = "SELECT COUNT(Distinct(program_phase_id)) aCount FROM  Revenue where budget_fiscal_year= " + year;

					rs = amountQueryHelper(yearTypeVal);
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("aCount");
					}

					return count;

				}

				public static int getNYCHARevenueExpenseCategoriesCount(int year, char yearTypeVal) throws SQLException {
					query = "SELECT COUNT(Distinct(expenditure_type_id)) aCount FROM  Revenue where budget_fiscal_year= " + year;

					rs = amountQueryHelper(yearTypeVal);
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("aCount");
					}

					return count;
				}

				public static int getNYCHARevenueRespCentersCount(int year, char yearTypeVal) throws SQLException {
					query = "SELECT COUNT(Distinct(responsibility_center_id)) aCount FROM  Revenue where budget_fiscal_year= " + year;

					rs = amountQueryHelper(yearTypeVal);
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("aCount");
					}

					return count;
				}
				public static int getNYCHARevenueFundingSourceCount(int year, char yearTypeVal) throws SQLException {
					query = "SELECT COUNT(Distinct(funding_source_id)) aCount FROM  Revenue where budget_fiscal_year= " + year;

					rs = amountQueryHelper(yearTypeVal);
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("aCount");
					}

					return count;
				}
				
				public static int getNYCHARevenueProjectsCount(int year, char yearTypeVal) throws SQLException {
					query = "SELECT COUNT(Distinct(gl_project_id)) aCount FROM  Revenue where budget_fiscal_year= " + year;

					rs = amountQueryHelper(yearTypeVal);
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("aCount");
					}

					return count;
				}
				public static String getNYCHARevenueAmount(int year, char yearTypeVal) throws SQLException {
					query = "SELECT SUM(revenue_amount) sumBudgetAmt FROM Revenue WHERE budget_fiscal_year = " + year;

					rs = amountQueryHelper(yearTypeVal);

					BigDecimal totalBudgetAmount = new BigDecimal(0);

					while (rs.next()) {
						totalBudgetAmount = rs.getBigDecimal("sumBudgetAmt");
					}
					return formatNumber(totalBudgetAmount);
					// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
				}

				
		
				public static String getNYCHARevenueDetailsAmount(int year, char yearTypeVal) throws SQLException {
					query = "SELECT SUM(SUM(revenue_amount) sumBudgetAmt "
							+ "FROM Revenue WHERE budget_fiscal_year = " + year;

					rs = amountQueryHelper(yearTypeVal);

					BigDecimal totalBudgetAmount = new BigDecimal(0);

					while (rs.next()) {
						totalBudgetAmount = rs.getBigDecimal("sumBudgetAmt");
					}
					return formatNumber2(totalBudgetAmount);
					// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
				}

				public static int getNYCHARevenueDetailsCount(int year, char yearTypeVal) throws SQLException {
					query = "SELECT COUNT(*)  aCount FROM  Revenue where budget_fiscal_year= " + year;

					rs = amountQueryHelper(yearTypeVal);
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("aCount");
					}

					return count;

				}
				
				
				

				/// NYCHA Payroll widget sqls

				public static int getNYCHAPayrollAgenciesCount(int year, char yearTypeVal) throws SQLException {
					query = "SELECT COUNT(Distinct(agency_id)) aCount " + "FROM  payroll where calendar_fiscal_year= " + year;

					rs = amountQueryHelper(yearTypeVal);
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("aCount");
					}
					return count;
				}
				

				public static int getNYCHAPayrollSalCount(int year, char yearTypeVal) throws SQLException {
					query = "SELECT COUNT(Distinct employee_number) aCount from ("
							+ "SELECT latest_emp.employee_number,latest_emp.pay_date,latest_emp.calendar_fiscal_year, emp.amount_basis_id FROM "
							+ "Payroll emp JOIN ( SELECT max(pay_date) as pay_date, employee_number,calendar_fiscal_year FROM payroll where calendar_fiscal_year = "
							+ year + " GROUP BY employee_number,calendar_fiscal_year )"
							+ "latest_emp ON latest_emp.pay_date = emp.pay_date AND latest_emp.employee_number = emp.employee_number AND latest_emp.calendar_fiscal_year = emp.calendar_fiscal_year and emp.amount_basis_id =1 ) a";

					/*
					 * query = "SELECT COUNT(Distinct(employee_number)) aCount " +
					 * "FROM  payroll where fiscal_year= " + year ;
					 */
					rs = amountQueryHelper(yearTypeVal);
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("aCount");
					}
					return count;
				}

				public static String getNYCHAPayrollAmount(int year, char yearTypeVal) throws SQLException {
					query = "SELECT sum(gross_pay)  sumPayrollAmt " + "FROM Payroll" + " WHERE calendar_fiscal_year = " + year;

					rs = amountQueryHelper(yearTypeVal);

					BigDecimal totalPayrollAmount = new BigDecimal(0);

					while (rs.next()) {
						totalPayrollAmount = rs.getBigDecimal("sumPayrollAmt");
					}
					return formatNumber(totalPayrollAmount);
					// .divide(new BigDecimal(1000000000)).setScale(1, BigDecimal.ROUND_HALF_UP);
				}

				public static int getNYCHAPayrollDetailsCount(int year, char yearTypeVal) throws SQLException {
					// query = "SELECT COUNT(*) aCount " +
					// "FROM payroll where fiscal_year= " + year ;

					query = "select count(*) aCount from ("
							+ "SELECT employee_id , agency_id,civil_service_title, sum(base_pay)as base_pay, sum(gross_pay),"
							+ " max(annual_salary) as annual_salary, sum(overtime_pay)as overtime_pay, sum(other_payments)as OT,"
							+ "(CASE WHEN amount_basis_id=1 THEN 'Salaried' ELSE 'Non-Salaried' END) as type_of_employment "
							+ "FROM payroll WHERE calendar_fiscal_year = " + year + " and amount_basis_id in (1,2,3)	GROUP BY 1,2,3,9 ) a";

					query2 = "select count(*) aCount from payroll WHERE  amount_basis_id in (1,2,3) and  calendar_fiscal_year = " + year;
					
					rs = amountQueryHelper(yearTypeVal);
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("aCount");
					}
					return count;
				}

				public static int getNYCHAPayrollTitleDetailsCount(int year, char yearTypeVal) throws SQLException {
					// query = "SELECT COUNT(*) aCount " +
					// "FROM payroll where fiscal_year= " + year ;

					query = "select count(*) aCount from ("
							+ "SELECT civil_service_title,count(distinct employee_ID),sum(gross_pay) as gross_pay_ytd ,sum(base_pay) as base_pay_ytd,"
							+ "sum(other_payments) as other_payments_ytd ,sum(overtime_pay) as overtime_pay_ytd "
							+ "FROM payroll WHERE calendar_fiscal_year = " + year + " and amount_basis_id in (1) group by 1 ) a";

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
				
				//Ciywide Spending
				public static int getCitywideTotalSpendingAllTransactionCount(char yearTypeVal) throws SQLException {
					query = "SELECT COUNT(*) aCount from all_disbursement_transactions";
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

