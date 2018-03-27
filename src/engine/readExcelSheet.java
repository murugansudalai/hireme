package engine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class readExcelSheet {

	public readExcelSheet() {

	}

	public Sheet Excel(String filename, String sheetname) throws Exception {
		Sheet sheet = null;
		try {
			System.out.println("input file name  " + filename);
			System.out.println("InputSheet name  " + sheetname);
			WorkbookSettings settings = new WorkbookSettings();
			settings.setLocale(new Locale("en", "EN"));

			settings.setNamesDisabled(true);
			settings.setFormulaAdjust(true);
			// settings.setMergedCellChecking(true);
			// settings.setCellValidationDisabled(true);

			Workbook workbook = Workbook.getWorkbook(new File(filename), settings);
			sheet = workbook.getSheet(sheetname);
			System.out.println("End of test");
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		if (sheet == null) {
			System.out.println("NULL SHEET");
		}
		return sheet;
	}

	public Map<String, String> CreateMapFromExcel(String WorkbookLocation, String SheetName, String Row)
			throws Exception {
		try{
		Map<String, String> map = new HashMap<String, String>();
		if (Row.equals("-1")) {
			//don't do anything and return null
		} else {
			Sheet sheet = Excel(WorkbookLocation, SheetName);
			System.out.println("is");
			int column = sheet.getColumns();
			int row = Integer.parseInt(Row);
			for (int i = 0; i < column; i++) {
				String KEY = sheet.getCell(i, 0).getContents();
				String VALUE = sheet.getCell(i, row).getContents();
				if (VALUE == null) {
					VALUE = "";
				}
				map.put(KEY, VALUE);
			}
		}
		return map;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
