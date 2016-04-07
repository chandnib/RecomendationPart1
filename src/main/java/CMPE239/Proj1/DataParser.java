package CMPE239.Proj1;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataParser {
	
	static String filename;
	public static LinkedHashMap<Long, HashMap<String, Float>> getInformation() {
		LinkedHashMap<Long, HashMap<String, Float>> ratingMap = new LinkedHashMap<Long, HashMap<String, Float>>();
		Row MainRow;
		try {
			FileInputStream file = new FileInputStream(
					new File(filename));

			// Create Workbook instance holding reference to xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			MainRow = sheet.getRow(0);

			// Iterate through each rows one by one
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				Row row = sheet.getRow(i);
				long id = (int) row.getCell(1).getNumericCellValue();
				ratingMap.put((long) id, new HashMap<String, Float>());

				for (int j = 5; j < row.getPhysicalNumberOfCells(); j++) {
					Cell cell = row.getCell(j);
					Cell mainCell = MainRow.getCell(j);
					String str = MainRow.getCell(j).getStringCellValue();
					str=str.substring(str.indexOf('[')+1, str.indexOf(']'));
					// Check the cell type and format accordingly
					/*
					 * CellReference ref = new CellReference("E"); Cell c =
					 * row.getCell(ref.getCol());
					 */
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						HashMap<String, Float> map = ratingMap.get((long) id);
						map.put(str, (float) cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						HashMap<String, Float> map1 = ratingMap.get((long) id);
						map1.put(str, (float) 0);
						break;
					}
				}
				
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ratingMap;
	}

	public static ArrayList<Profile> getUserDetails() {
		ArrayList<Profile> profileList = new ArrayList<Profile>();
		HashSet<Long> set = new HashSet<Long>();
		Row MainRow;
		try {
			FileInputStream file = new FileInputStream(
					new File(filename));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			MainRow = sheet.getRow(0);

			// Iterate through each rows one by one
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				Row row = sheet.getRow(i);
				long id = (int) row.getCell(1).getNumericCellValue();
				if (set.add((long) id)) {
					String age = row.getCell(2).getStringCellValue();
					String gender = row.getCell(3).getStringCellValue();
					Profile prof = new Profile();
					prof.setId((long) id);
					prof.setAge(age);
					if (gender.equals("Male"))
						prof.setGender("M");
					else
						prof.setGender("F");
					profileList.add(prof);
				}
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profileList;
	}

	public static ArrayList<String> getMovieList() {
		Row MainRow;
		ArrayList<String> movie = new ArrayList<String>();
		try {
			FileInputStream file = new FileInputStream(
					new File(filename));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			MainRow = sheet.getRow(0);

			for (int i = 5; i < MainRow.getPhysicalNumberOfCells(); i++) {
				String str = MainRow.getCell(i).getStringCellValue();
				movie.add(str.substring(str.indexOf('[')+1, str.indexOf(']')));
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return movie;
	}
}