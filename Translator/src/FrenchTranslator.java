import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.Entry;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.EncryptedDocumentException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FrenchTranslator {
	private static final Charset CHARSET = Charset.forName("UTF-8");
	private static final String FIND_REPLACE_XLSX = "C:\\Users\\elcot\\Desktop\\Desktop downloads\\Translator\\dic1.xlsx";
	private static final int FINDCOL = 0;
	private static final int REPLACECOL = 1;

	public void replaceTextFile(String inFile, String outFile) throws IOException {
		List<FindReplaceStr> list = getFindReplaceList();
		Path path = (Path) Paths.get(inFile);
		String str = readFile(path);
		
		for (FindReplaceStr item : list) {
			str = str.replace(item.findStr, item.replaceStr);
		}
		Path topath = (Path) Paths.get(outFile);
		BufferedWriter writer = Files.newBufferedWriter(topath, CHARSET);
		writer.write(str);
		writer.close();
	}

	private String readFile(Path path) throws IOException {
		byte[] bytes = Files.readAllBytes((java.nio.file.Path) path);
		return new String(bytes, CHARSET);
	}

	private List<FindReplaceStr> getFindReplaceList() throws EncryptedDocumentException, IOException {
		Workbook workbook = WorkbookFactory.create(new File(FIND_REPLACE_XLSX));
		Sheet sheet = workbook.getSheetAt(0);
		DataFormatter dataformatter = new DataFormatter();
		int rows = sheet.getPhysicalNumberOfRows();
		List<FindReplaceStr> list = new ArrayList<FindReplaceStr>();
		for (int i = 1; i < rows; i++) {
			Row row = sheet.getRow(i);
			list.add(new FindReplaceStr(dataformatter.formatCellValue(row.getCell(FINDCOL)),(dataformatter
					.formatCellValue(row.getCell(REPLACECOL)))));
		}
		return list;
	}
}
