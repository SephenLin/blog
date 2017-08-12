	package aode.ssm.blog.util;

import java.awt.print.Printable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
	
	public class ExcelImport {
	
//		public static void main(String[] args) throws Exception{
//			try {
//				List<Student> students = new ArrayList<Student>() ;
//				Workbook wwb = null ;
//				File file = new File("d:/test/testExcel.xls") ;
//				if(!file.exists()) {
//					file.createNewFile() ;
//				}
//				wwb = Workbook.getWorkbook(file) ;
//				Sheet ws = wwb.getSheet("first") ;
//				int clos = ws.getColumns() ;
//				int rows = ws.getRows() ;
//	            for(int i = 1; i < rows; i++) {
//	            	for(int j = 0; j < clos; j++) {
//	            		students.add(new Student(Integer.parseInt(ws.getCell(j++, i).getContents()),
//	            				ws.getCell(j++, i).getContents(),
//	            				ws.getCell(j++, i).getContents())) ;
//	            	}
//	            }
//	            new Service().excelToSql(students) ;
//	           // 关闭Excel工作簿对象
//	            wwb.close();
//			}catch(Exception e) {
//				e.printStackTrace() ;
//				throw new Exception("export 错误!") ;
//			}
//		}
	}
