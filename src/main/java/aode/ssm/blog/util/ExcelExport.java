	package aode.ssm.blog.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
	
	public class ExcelExport {
//
//		public static void main(String[] args) throws Exception{
//			try {
//				List<Student> student = new Service().sqlToExcel() ;
//				WritableWorkbook wwb = null ;
//				File file = new File("d:/test/testExcel.xls") ;
//				if(!file.exists()) {
//					file.createNewFile() ;
//				}
//				wwb = Workbook.createWorkbook(file) ;
//				WritableSheet ws = wwb.createSheet("first", 0) ;
//				//要插入到的Excel表格的行号，默认从0开始
//	            Label labelId= new Label(0, 0, "编号(id)");//表示第
//	            Label labelName= new Label(1, 0, "姓名(name)");
//	            Label labelSex= new Label(2, 0, "性别(sex)");
//	            ws.addCell(labelId);
//	            ws.addCell(labelName);
//	            ws.addCell(labelSex);
//	            for (int i = 0; i < student.size(); i++) {
//	                Label labelId_i= new Label(0, i+1, student.get(i).getId()+"");
//	                Label labelName_i= new Label(1, i+1, student.get(i).getName());
//	                Label labelSex_i= new Label(2, i+1, student.get(i).getSex());
//	                ws.addCell(labelId_i);
//	                ws.addCell(labelName_i);
//	                ws.addCell(labelSex_i);
//	            }
//	          //写进文档
//	            wwb.write();
//	           // 关闭Excel工作簿对象
//	            wwb.close();
//			}catch(Exception e) {
//				e.printStackTrace() ;
//				throw new Exception("export 错误!") ;
//			}
//		}
	}
