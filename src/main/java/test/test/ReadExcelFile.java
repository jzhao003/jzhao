package test.test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcelFile {
	public static final String SAMPLE_XLSX_FILE_PATH = "/Users/jzhao/Downloads/sss.xls";
	
	public static void main(String[] args) {
		try {
			new ReadExcelFile().readExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readExcel() throws IOException {
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
//		workbook.getSheetAt(0).getRow(rownum)
//		workbook.getSheetAt(0).getc
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
		
		Sheet sheet = workbook.getSheetAt(0);
		
		System.out.println(sheet.getRow(3).getCell(3));
		Iterator<Row> rowIterator = sheet.rowIterator();
		
		if (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			System.out.println(row.getCell(2));
			System.out.println(row.getCell(3));
		}
	}
}
