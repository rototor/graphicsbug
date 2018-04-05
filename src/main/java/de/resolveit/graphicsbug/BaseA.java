package de.resolveit.graphicsbug;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BaseA {
	protected static void deleteColumns(Sheet sheet, String... columnsToDelete) {
		List<String> columns = new ArrayList<>(Arrays.asList(columnsToDelete));
		columns.sort(String::compareTo);
		Collections.reverse(columns);
		columns.forEach(c -> deleteColumn(sheet, c));
	}

	private static int getColumnIndex(String col) {
		return 0;
	}

	protected static void deleteColumn(Sheet sheet, String columnToDelete) {
		for (int rId = 0; rId <= sheet.getLastRowNum(); rId++) {
			Row row = sheet.getRow(rId);
			int columnToDeleteNum = getColumnIndex(columnToDelete);
			for (int cID = columnToDeleteNum; cID < row.getLastCellNum(); cID++) {
				Cell cOld = row.getCell(cID);
				if (cOld != null) {
					row.removeCell(cOld);
				}
				Cell cNext = row.getCell(cID + 1);
				if (cNext != null) {
					Cell cNew = row.createCell(cID, cNext.getCellTypeEnum());
					//cloneCell(cNew, cNext);
					// Set the column width only on the first row.
					// Other wise the second row will overwrite the original column width set
					// previously.
					if (rId == 0) {
						sheet.setColumnWidth(cID, sheet.getColumnWidth(cID + 1));

					}
				}
			}
		}
	}

}
