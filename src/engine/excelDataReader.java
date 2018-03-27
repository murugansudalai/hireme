package engine;

import java.io.File;
import jxl.Sheet;
import jxl.Workbook;
import engine.readExcelSheet;

public class excelDataReader {

	Object[][] excelDataArray = null;
	Workbook workbk;
	Sheet sheet;
	int rowCount;
	int colCount;

	public Object[][] getExcelData(String WorkBookPath, String shtName, String ScriptName) throws Exception {
		try {
			Workbook workbk = Workbook.getWorkbook(new File(WorkBookPath));
			Sheet sht = workbk.getSheet(shtName);
			rowCount = sht.getRows();
			colCount = sht.getColumns();
			int counter = 0;
			int counter2 = 0;
			readExcelSheet RX = new readExcelSheet();
			Sheet sheet = RX.Excel(WorkBookPath, shtName);
			int row = sheet.getRows();
			int column = 0;
			int Executecolumn = sheet.findCell("TO_BE_EXECUTED").getColumn();
			column = sheet.findCell("SCRIPT_ID").getColumn();

			for (int i = 0; i < row; i++) {
				if (sht.getCell(column, i).getContents().equalsIgnoreCase(ScriptName)
						&& (sht.getCell(Executecolumn, i).getContents().equalsIgnoreCase("YES"))) {
					counter += 1;
				}
			}
			excelDataArray = new String[counter][2];
			for (int i = 0; i < row; i++) {
				if ((sht.getCell(column, i).getContents().equalsIgnoreCase(ScriptName))) {
					if ((sht.getCell(Executecolumn, i).getContents().equalsIgnoreCase("YES"))) {
						excelDataArray[counter2][0] = ScriptName;
						excelDataArray[counter2][1] = Integer.toString(i);
						System.out.println("EXECUTING " + ScriptName + " FROM ROW--------- " + Integer.toString(i));
						counter2++;
					}
					
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (excelDataArray == null) {
			return new String[0][0];
		}
		return (excelDataArray);
	}

}
