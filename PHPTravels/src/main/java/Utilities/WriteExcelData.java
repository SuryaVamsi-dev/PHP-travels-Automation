package Utilities;


import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelData {
	

	public static void ListOutHotels(List<String> ListedHotels,String loc)
	{
		String fileName = new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date());
		try {
		String ExcelPath=System.getProperty("user.dir")+"\\output\\";
		FileOutputStream output= new FileOutputStream(ExcelPath+"HotelsList"+fileName+".xlsx");
		
		XSSFWorkbook workbook= new XSSFWorkbook();
		XSSFSheet sheet= workbook.createSheet("hotels");
		
//		sheet.setColumnWidth(0, 255*100);
//		XSSFFont font = workbook.createFont();
//		  font.setBold(true);
		  
//		 CellStyle style = workbook.createCellStyle();
//	      style.setFont(font);
	   
		Row row = sheet.createRow(0);  
        Cell cell = row.createCell(0);
        cell.setCellValue("Hotels in "+loc); //, cost for extra columns
        //cell.setCellStyle(style);
        
		int startRow=1;
		for(String HotelNames: ListedHotels )
		{
			sheet.createRow(startRow++).createCell(0).setCellValue(HotelNames);
		}
		workbook.write(output);
		workbook.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
