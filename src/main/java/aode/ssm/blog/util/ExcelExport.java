	package aode.ssm.blog.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aode.ssm.blog.po.User;
import jxl.Workbook;
import jxl.write.*;

	public class ExcelExport {

		public void user_export_excel(List<User> users,File file) throws Exception{
			try {
				WritableWorkbook wwb = null ;
//				File file1 = new File("d:/userExcel") ; // 文件夹
//				if(!file1.exists()) {
//					file1.mkdir() ;
//				}
//				File file3 = new File("d:/userExcel/testExcel.xls") ; // 文件夹
//				if(!file3.exists()) {
//					file3.createNewFile() ;
//				}else {
//					file3.delete();
//					file3.createNewFile();
//				}
				wwb = Workbook.createWorkbook(file) ;
				WritableSheet ws = wwb.createSheet("first", 0) ;
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				//要插入到的Excel表格的行号，默认从0开始
	            Label labelId= new Label(0, 0, "编号(id)");//表示第
	            Label labelName= new Label(1, 0, "姓名(name)");
	            Label labelAccount= new Label(2, 0, "账号(account)");
				Label labelPassword= new Label(3, 0, "密码(password)");//表示第
				Label labelPhone= new Label(4, 0, "手机(phone)");
				Label labelEmail= new Label(5, 0, "邮箱(email)");//表示第
				Label labelHeadUrl= new Label(6, 0, "头像路劲(headUrl)");
				Label labelHeadName= new Label(7, 0, "图片名称(headName)");
				Label labelTraffics= new Label(8, 0, "浏览量(sex)");
				Label labelTime= new Label(9, 0, "账号创建时间(time)");//表示第
				Label labelIntroduction= new Label(10, 0, "自我介绍(introduction)");
				Label labelLevel= new Label(11, 0, "等级(level)");
	            ws.addCell(labelId);
	            ws.addCell(labelName);
	            ws.addCell(labelAccount);
				ws.addCell(labelPassword);
				ws.addCell(labelPhone);
				ws.addCell(labelEmail);
				ws.addCell(labelHeadUrl);
				ws.addCell(labelHeadName);
				ws.addCell(labelTraffics);
				ws.addCell(labelTime);
				ws.addCell(labelIntroduction);
				ws.addCell(labelLevel);
	            for (int i = 0; i < users.size(); i++) {
					Label labelId_temp= new Label(0, i + 1, users.get(i).getId().toString());//表示第
					Label labelName_temp= new Label(1, i + 1, users.get(i).getName());
					Label labelAccount_temp= new Label(2, i + 1, users.get(i).getAccount());
					Label labelPassword_temp= new Label(3, i + 1, users.get(i).getPassword());//表示第
					Label labelPhone_temp= new Label(4, i + 1, users.get(i).getPhone());
					Label labelEmail_temp= new Label(5, i + 1, users.get(i).getEmail());//表示第
					Label labelHeadUrl_temp= new Label(6, i + 1, users.get(i).getHeadUrl());
					Label labelHeadName_temp= new Label(7, i + 1, users.get(i).getHeadName());
					Label labelTraffics_temp= new Label(8, i + 1, users.get(i).getTraffics() + "");
					Label labelTime_temp= new Label(9, i + 1, simpleDateFormat.format(users.get(i).getTime()));//表示第
					Label labelIntroduction_temp= new Label(10, i + 1, users.get(i).getIntroduction());
					Label labelLevel_temp= new Label(11, i + 1, users.get(i).getLevel() + "");
					ws.addCell(labelId_temp);
					ws.addCell(labelName_temp);
					ws.addCell(labelAccount_temp);
					ws.addCell(labelPassword_temp);
					ws.addCell(labelPhone_temp);
					ws.addCell(labelEmail_temp);
					ws.addCell(labelHeadUrl_temp);
					ws.addCell(labelHeadName_temp);
					ws.addCell(labelTraffics_temp);
					ws.addCell(labelTime_temp);
					ws.addCell(labelIntroduction_temp);
					ws.addCell(labelLevel_temp);
	            }
	          //写进文档
	            wwb.write();
	           // 关闭Excel工作簿对象
	            wwb.close();
			}catch(Exception e) {
				e.printStackTrace() ;
				throw new Exception("export 错误!") ;
			}
		}
	}
