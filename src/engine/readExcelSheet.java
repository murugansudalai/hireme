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
	
	
	Object[][] tabArray = null;
	Workbook workbk;
	Sheet sheet;
	int rowCount;
	int [] rowlist;
	// String sheetPath = LoadEnvironment.workingDir+LoadEnvironment.INPUTSHEET;
	int colCount;
	int counter = 0;
	int counter2 = 0;
	

	public Sheet Excel(String filename, String sheetname) throws Exception {
		Sheet sheet = null;
		try {


			WorkbookSettings settings = new WorkbookSettings();
			settings.setLocale(new Locale("en", "EN"));
			settings.setNamesDisabled(true);
			settings.setFormulaAdjust(true);

			Workbook workbook = Workbook.getWorkbook(new File(filename), settings);
			sheet = workbook.getSheet(sheetname);
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		if (sheet == null) {
			System.out.println("NULL SHEET");
		}
		return sheet;
	}
	
	public Object[][] getExcelData(String WorkBookPath, String shtName, String ScriptName) throws Exception {
		try {
			Workbook workbk = Workbook.getWorkbook(new File(WorkBookPath));
			Sheet sheet = workbk.getSheet(shtName);
			rowCount = sheet.getRows();
			colCount = sheet.getColumns();
			int counter = 0;
			int counter2 = 0;
			int row = sheet.getRows();
			int column = 0;
			int Executecolumn = sheet.findCell("TO_BE_EXECUTED").getColumn();
			column = sheet.findCell("SCRIPT_ID").getColumn();

			for (int i = 0; i < row; i++) {
				System.out.println("Scriptname"+sheet.getCell(column, i).getContents());
				if (sheet.getCell(column, i).getContents().equalsIgnoreCase(ScriptName)
						&& (sheet.getCell(Executecolumn, i).getContents().equalsIgnoreCase("YES"))) {
					
					
	
					counter += 1;
				}
			}
			tabArray = new String[counter][2];
			for (int i = 0; i < row; i++) {
				if ((sheet.getCell(column, i).getContents().equalsIgnoreCase(ScriptName))) {
					if ((sheet.getCell(Executecolumn, i).getContents().equalsIgnoreCase("YES"))) {
						tabArray[counter2][0] = ScriptName;
						tabArray[counter2][1] = Integer.toString(i);
						System.out.println("EXECUTING " + ScriptName + " FROM ROW--------- " + Integer.toString(i));
						counter2++;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Problem with Dataprovider");
		}
		if (tabArray == null) {
			return new String[0][0];
		}
		return (tabArray);
	}
	
	public String getnumberofrow(String WorkBookPath, String shtName, String ScriptName) throws Exception {
		try {
			Workbook workbk = Workbook.getWorkbook(new File(WorkBookPath));
			Sheet sheet = workbk.getSheet(shtName);
			rowCount = sheet.getRows();
			colCount = sheet.getColumns();

			int row = sheet.getRows();
			int column = 0;
			int Executecolumn = sheet.findCell("TO_BE_EXECUTED").getColumn();
			column = sheet.findCell("SCRIPT_ID").getColumn();

			rowlist =new int[row];
			
			for (int i = 0; i < row; i++) {
				
				if (sheet.getCell(column, i).getContents().equalsIgnoreCase(ScriptName)
						&& (sheet.getCell(Executecolumn, i).getContents().equalsIgnoreCase("NO"))) {
					
					System.out.println("count"+counter+"i value is"+i);

					
					counter += 1;					
					rowlist[counter] = i;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Problem with Dataprovider");
		}
		
		return Integer.toString(counter);
	}
	
	
	

	public Map<String, String> CreateMapFromExcel(String WorkbookLocation, String SheetName, String Row)
			throws Exception {
		try{
		Map<String, String> map = new HashMap<String, String>();
		

		
		if (Row.equals("0")) {
			//don't do anything and return null
		} else {
			Sheet sheet = Excel(WorkbookLocation, SheetName);

			int column = sheet.getColumns();
			int row = Integer.parseInt(Row);
			for (int i = 1; i < column; i++) {
				String KEY = sheet.getCell(i, 0).getContents();
				
				System.out.println(SheetName+"getcell of "+i+"row"+row);
				
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
