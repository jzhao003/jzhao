package ssq;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import test.test.DbUtil;

/*
 * Preparetion: 
 * 1.down load excel file from http://www.zhcw.com/kj/qg/ssq/wqcx/
 * 2.go to database folder, create database and tables
 */
public class ImportHistoryRecords {

	public static final String SAMPLE_XLSX_FILE_PATH = "/Users/jzhao/Downloads/sss.xls";

	public List<List<String>> getDataFromExcelFile() {
		// list, as the each row
		List<String> list = null;
		// records, the whole db records
		List<List<String>> records = new ArrayList<List<String>>();

		try {
			Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				list = new ArrayList<String>();
				Row row = rowIterator.next();
				if (!row.getCell(0).getStringCellValue().equals("") && isInteger(row.getCell(0).getStringCellValue())) {
					// get the lottery_date from excel
					list.add(row.getCell(2).getStringCellValue());
					// get red and blue number
					configRedAndBlueNumber(row.getCell(3).getStringCellValue(), list);
					records.add(list);
				}

			}
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return records;
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	private void configRedAndBlueNumber(String cellValue, List<String> list) {
		String[] numbers = cellValue.split(" ");
		List<String> reds = new ArrayList<String>();
		for (int i = 0; i < numbers.length - 1; i++) {
			reds.add(numbers[i]);
		}
		sortRedNumber(reds);

		for (int i = 0; i < numbers.length - 1; i++) {
			list.add(reds.get(i));
		}
		list.add(numbers[numbers.length - 1]);
	}

	private void sortRedNumber(List<String> list) {
		Collections.sort(list);
	}

	public static void main(String[] args) {
		try {
			new ImportHistoryRecords().importHistory();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void importHistory() throws ClassNotFoundException, SQLException {
		List<List<String>> records = getDataFromExcelFile();
		for (int i = 0; i < records.size(); i++) {
			StringBuffer sql = new StringBuffer("insert into ssq values('");
			for (int j = 0; j < records.get(i).size(); j++) {
				sql.append(records.get(i).get(j) + "','");
			}
			sql.deleteCharAt(sql.length() - 1).deleteCharAt(sql.length() - 1);
			sql.append(")");

			DbUtil.executeUpdateSql(sql.toString());
		}

	}

}
