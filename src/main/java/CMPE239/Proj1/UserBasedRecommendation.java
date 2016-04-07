//package CMPE239.Proj1;
//
//import java.io.File;
//import java.io.FileInputStream;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.*;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//
//import java.util.*;
//
//
//
//
//public class UserBasedRecommendation {
//
//	public static void main(String[] args) {
//		//data structure
//		Map<Long,Map<String,Integer>> preference = new HashMap<Long, Map<String,Integer>>();
//		double id;
//		//read csv
//		 try
//	        {
//	            FileInputStream file = new FileInputStream(new File("D:/II Semester/239/MovieNight.xlsx"));
//	 
//	            //Create Workbook instance holding reference to .xlsx file
//	            XSSFWorkbook workbook = new XSSFWorkbook(file);
//	 
//	            //Get first/desired sheet from the workbook
//	            XSSFSheet sheet = workbook.getSheetAt(0);
//	 
//	            //Iterate through each rows one by one
//	            Iterator<Row> rowIterator = sheet.iterator();
//	            while (rowIterator.hasNext()) 
//	            {
//	                Row row = rowIterator.next();
//	                
//	                if(row.getRowNum()==0 )
//	                    	   continue; //just skip the rows if row number is 0 or 1
//	                
//	                //For each row, iterate through all the columns
//	                Iterator<Cell> cellIterator = row.cellIterator();
//	                 
//	                while (cellIterator.hasNext()) 
//	                {
//	                    Cell cell = cellIterator.next();
//	                    
//	                    if((cell.getColumnIndex() == 0)||(cell.getColumnIndex() == 2)||(cell.getColumnIndex() == 3)||(cell.getColumnIndex() == 4))
//	                    	continue;
//	                    
//	                    
//	                    //Check the cell type and format accordingly
//	                    HashMap<String,Integer> ratingMap = new HashMap<String, Integer>();
//	                    
//	                    if(cell.getColumnIndex() == 1)
//	                    {
//	                    	id = cell.getNumericCellValue();
//	                    	continue;
//	                    }
//	                    	 
//	                    if(cell.getCellType() == cell.CELL_TYPE_NUMERIC)
//	                    {
//	                    	ratingMap.add(cell.)
//	                    }
//	                    	
//	                    
////	                    switch (cell.getCellType()) 
////	                    {
////	                        case Cell.CELL_TYPE_NUMERIC:
////	                            System.out.print(cell.getNumericCellValue() + "t");
////	                            break;
////	                        case Cell.CELL_TYPE_STRING:
////	                            System.out.print(cell.getStringCellValue() + "t");
////	                            break;
////	                    }
//	                }
//	                System.out.println("");
//	            }
//	            file.close();
//	        } 
//	        catch (Exception e) 
//	        {
//	            e.printStackTrace();
//	        }
//	    }
//		
//		//
//		
//		
//		
//
////		try{
////			
////			DataModel model = new FileDataModel(new File("D:/II Semester/239/dataset.csv"));
////			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
////			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
////			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
////			List<RecommendedItem> recommendations = recommender.recommend(2, 3);
////			for (RecommendedItem recommendation : recommendations) {
////			  System.out.println(recommendation);
////			}
////			
////		}
////		catch(Exception e)
////		{
////			System.out.println("There was an error");
////			e.printStackTrace();
////		}
//
//	}
//
//
