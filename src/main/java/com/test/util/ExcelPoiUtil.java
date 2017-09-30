package com.test.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.commons.lang3.StringUtils;

public class ExcelPoiUtil<T> {

	/**
	 * 读取excel文件
	 * 
	 * @param path
	 *            文件路径
	 * @param sheetIndex
	 *            工作薄编号
	 * @return
	 */
	public static List<String[]> readExcel(String path, int sheetIndex) {
		List<String[]> dataList = new ArrayList<String[]>();
		try {
			FileInputStream in = new FileInputStream(path);
			dataList = readExcel(in, sheetIndex);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataList;
	}

	/**
	 * 读取excel文件
	 * 
	 * @param InputStream
	 *            流
	 * @param sheetIndex
	 *            工作薄编号，编号从0开始
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<String[]> readExcel(InputStream in, int sheetIndex) {
		List<String[]> dataList = new ArrayList<String[]>();
		Workbook workBook = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			workBook = WorkbookFactory.create(in);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int columnNum = 0;
		Sheet sheet = workBook.getSheetAt(sheetIndex);
		if (sheet.getRow(0) != null) {
			columnNum = sheet.getRow(0).getLastCellNum()
					- sheet.getRow(0).getFirstCellNum();
		}
		int count = 1; // 读取当前行数
		int titleCount = 1; // 标题行
		Iterator<Row> iterator = sheet.rowIterator();
		if (columnNum > 0) {
			while (iterator.hasNext()) {
				if (count == titleCount) {// 跳过标题行
					count++;
					iterator.next();
					continue;
				}
				count++;
				Row row = iterator.next();
				String[] singleRow = new String[columnNum];
				int n = 0;
				for (int i = 0; i < columnNum; i++) {
					Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_BLANK:
						singleRow[n] = "";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						singleRow[n] = Boolean.toString(cell
								.getBooleanCellValue());
						break;
					// 数值
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							singleRow[n] = dateFormat.format(cell
									.getDateCellValue());
						} else {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							String temp = cell.getStringCellValue();
							// 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
							if (temp.indexOf(".") > -1) {
								singleRow[n] = String.valueOf(new Double(temp))
										.trim();
							} else {
								singleRow[n] = temp.trim();
							}
						}
						break;
					case Cell.CELL_TYPE_STRING:
						singleRow[n] = cell.getStringCellValue().trim();
						break;
					case Cell.CELL_TYPE_ERROR:
						singleRow[n] = "";
						break;
					case Cell.CELL_TYPE_FORMULA:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						singleRow[n] = cell.getStringCellValue();
						if (singleRow[n] != null) {
							singleRow[n] = singleRow[n].replaceAll("#N/A", "")
									.trim();
						}
						break;
					default:
						singleRow[n] = "";
						break;
					}
					n++;
				}
				if ("".equals(singleRow[0])) {
					continue;
				}// 如果第一行为空，跳过
				dataList.add(singleRow);
			}
		}
		return dataList;
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出
	 * 
	 * @param title
	 *            表格标题名
	 * @param headersName
	 *            表格属性列名数组
	 * @param headersId
	 *            表格属性列名对应的字段
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	@SuppressWarnings({ "resource", "rawtypes", "unchecked" })
	public void exportExcel(String title, String[] headersName,
			String[] headersId, List<T> dtoList,OutputStream outputstream,Map<String,String> dateFormat){
		String datef = "yyyy-MM-dd";
		// 表头
		Map<Integer, String> map = new HashMap<Integer, String>();
		int key = 0;
		for (int i = 0; i < headersName.length; i++) {
			if (!headersName[i].equals(null)) {
				map.put(key, headersName[i]);
				key++;
			}
		}
		// 字段
		Map<Integer, String> zdMap = new HashMap<Integer, String>();
		int value = 0;
		for (int i = 0; i < headersId.length; i++) {
			if (!headersId[i].equals(null)) {
				zdMap.put(value, headersId[i]);
				value++;
			}
		}
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(title);
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		// 字段
		HSSFRow row1 = sheet.createRow(0);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell1;
		Collection zdC = zdMap.values();
		Iterator<String> itz = zdC.iterator();
		short size = 0;
		while (itz.hasNext()) {
			cell1 = row1.createCell(size);
			cell1.setCellValue(itz.next().toString());
			cell1.setCellStyle(style);
			size++;
		}
		HSSFRow row = sheet.createRow(1);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell;
		Collection c = map.values();
		Iterator<String> it = c.iterator();
		// 根据选择的字段生成表头
		size = 0;
		while (it.hasNext()) {
			cell = row.createCell(size);
			cell.setCellValue(it.next().toString());
			cell.setCellStyle(style);
			size++;
		}
		if(dtoList!=null&&dtoList.size()>0){
			Iterator<T> labIt = dtoList.iterator();
			int zdRow = 1;
			while (labIt.hasNext()) {
				int zdCell = 0;
				zdRow++;
				row = sheet.createRow(zdRow);
				T l = (T) labIt.next();
				// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
				for (short i = 0; i < headersId.length; i++) {
					Class tCls = l.getClass();
					Field field;
					String type="";
					String fieldName="";
					try {
						field = tCls.getDeclaredField(headersId[i]);
						field.setAccessible(true);
						type = field.getType().toString();
						fieldName = field.getName();
					} catch (NoSuchFieldException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SecurityException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					try {
						Method getMethod = tCls.getMethod(getMethodName,
								new Class[] {});
						Object val = getMethod.invoke(l, new Object[] {});
						String textVal = null;
						if (val != null) {
							if(type.endsWith("String")){
								textVal = val.toString();
							}else if(type.endsWith("Date")){
								Iterator itdf = dateFormat.entrySet().iterator();
								String df = datef;
							    while (itdf.hasNext()) {
								    Map.Entry entry = (Map.Entry) itdf.next();
								    Object key1 = entry.getKey();
								    Object value1 = entry.getValue();
								    if(key1!=null&&fieldName.equals(key1.toString())){
								    	df=value1.toString();
								    }
							    }
								Date vald = (Date)val;
								textVal = com.test.util.DateUtil.formatDateTime(vald, df);
							}else if(type.endsWith("boolean")){
								textVal = val.toString();
							}else if(type.endsWith("Integer")){
								textVal = val.toString();
							}
						} else {
							textVal = null;
						}
						row.createCell((short) zdCell)
								.setCellValue(textVal);
						zdCell++;
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		try {
			wb.write(outputstream);
			outputstream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 读取excel文件
	 *
	 * @param InputStream 流
	 * @param sheetIndex 工作薄编号，编号从0开始
	 * @param titleEnNum 标题行行号en
	 * @param titleCnNum 标题行行号cn
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	@SuppressWarnings("deprecation")
	public List<T> readExcelToObj(InputStream in, int sheetIndex,int titleEnNum,int titleCnNum,Class<?> cls) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		List<T> dataList = new ArrayList<T>();
		Workbook workBook = null;
		try {
			workBook = WorkbookFactory.create(in);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int columnNum = 0;
		Sheet sheet = workBook.getSheetAt(sheetIndex);
		//获取列数
		if (sheet.getRow(0) != null) {
			columnNum = sheet.getRow(0).getLastCellNum()
					- sheet.getRow(0).getFirstCellNum();
		}
		//获取标题行en
		List<String> titleEnColumn = new ArrayList<String>();
		//获取标题行cn


		Iterator<Row> iterator = sheet.rowIterator();
		int count = 1;
		if (columnNum > 0) {
			while (iterator.hasNext()) {
				if (count <= titleCnNum) {// 跳过标题行
					if(count == titleEnNum){
						Row row = iterator.next();
						for (int i = 0; i < columnNum; i++) {
							Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							titleEnColumn.add(cell.getStringCellValue());
						}

					}else{
						iterator.next();
					}
					count++;
					continue;
				}
				count++;
				Row row = iterator.next();
				//java反射生成实例对象
				@SuppressWarnings("unchecked")
				T t = (T)cls.newInstance();
				@SuppressWarnings("unused")
				int n = 0;
				for (int i = 0; i < columnNum; i++) {
					Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					String value = "";
					if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
						if (DateUtil.isCellDateFormatted(cell)) {
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							value = dateFormat.format(cell.getDateCellValue());
						}else{
							value = cell.getNumericCellValue()+"";
						}
					}else{
						value = cell.getStringCellValue().trim();
					}
					Field field= cls.getDeclaredField(titleEnColumn.get(i));
					field.setAccessible(true);
					String type = field.getType().toString();
					if(StringUtils.isBlank(value)){
						field.set(t, null);
					}else if(type.endsWith("String")){
						field.set(t, value);
					}else if(type.endsWith("Date")){
						field.set(t, com.test.util.DateUtil.toDate(value, "yyyy-mm-dd"));
					}else if(type.endsWith("boolean")){
						field.set(t, Boolean.parseBoolean(value));
					}else if(type.endsWith("Integer")){
						field.set(t, Integer.parseInt(value));
					}
					n++;
				}
				dataList.add(t);
			}
		}
		return dataList;
	}

	/**
	 * 读取excel文件
	 *
	 * @param in 流
	 * @param sheetIndex 工作薄编号，编号从0开始
	 * @param titleEnNum 标题行行号en
	 * @param titleCnNum 标题行行号cn
	 * @return
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	@SuppressWarnings("deprecation")
	public List<Map<String,Object>> readExcelToObj(InputStream in, int sheetIndex,int titleEnNum,int titleCnNum){
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		Workbook workBook = null;
		try {
			workBook = WorkbookFactory.create(in);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int columnNum = 0;
		Sheet sheet = workBook.getSheetAt(sheetIndex);
		//获取列数
		if (sheet.getRow(0) != null) {
			columnNum = sheet.getRow(0).getLastCellNum()
					- sheet.getRow(0).getFirstCellNum();
		}
		//获取标题行en
		List<String> titleEnColumn = new ArrayList<String>();
		//获取标题行cn


		Iterator<Row> iterator = sheet.rowIterator();
		int count = 1;
		if (columnNum > 0) {
			while (iterator.hasNext()) {
				if (count <= titleCnNum) {// 跳过标题行
					if(count == titleEnNum){
						Row row = iterator.next();
						for (int i = 0; i < columnNum; i++) {
							Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							titleEnColumn.add(cell.getStringCellValue());
						}

					}else{
						iterator.next();
					}
					count++;
					continue;
				}
				count++;
				Row row = iterator.next();
				//java反射生成实例对象
				Map<String,Object> mapResult = new HashMap<String,Object>();
				@SuppressWarnings("unused")
				int n = 0;
				for (int i = 0; i < columnNum; i++) {
					Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					String value = "";
					if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
						if (DateUtil.isCellDateFormatted(cell)) {
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							value = dateFormat.format(cell.getDateCellValue());
						}else{
							value = cell.getNumericCellValue()+"";
						}
					}else{
						value = cell.getStringCellValue().trim();
					}
					if(StringUtils.isBlank(value)){
						mapResult.put(titleEnColumn.get(i), null);
					}else{
						mapResult.put(titleEnColumn.get(i), value);
					}
					n++;
				}
				dataList.add(mapResult);
			}
		}
		return dataList;
	}

	/**
	 * 这是一个通用的方法，可以将放置在List<Map>集合中并且符号一定条件的数据以EXCEL 的形式输出
	 *
	 * @param title
	 *            表格标题名
	 * @param headersName
	 *            表格属性列名数组
	 * @param headersId
	 *            表格属性列名对应的字段
	 * @param dtoList
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象
	 * @param outputstream
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param dateFormat 日期字段格式
	 */
	@SuppressWarnings({ "resource", "rawtypes", "unchecked" })
	public void exportListMapToExcel(String title, String[] headersName,
							String[] headersId, List<Map<String,Object>> dtoList,OutputStream outputstream,Map<String,String> dateFormat){
		// 表头
		Map<Integer, String> map = new HashMap<Integer, String>();
		int key = 0;
		for (int i = 0; i < headersName.length; i++) {
			if (!headersName[i].equals(null)) {
				map.put(key, headersName[i]);
				key++;
			}
		}
		// 字段
		Map<Integer, String> zdMap = new HashMap<Integer, String>();
		int value = 0;
		for (int i = 0; i < headersId.length; i++) {
			if (!headersId[i].equals(null)) {
				zdMap.put(value, headersId[i]);
				value++;
			}
		}
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(title);
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		// 字段
		HSSFRow row1 = sheet.createRow(0);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell1;
		Collection zdC = zdMap.values();
		Iterator<String> itz = zdC.iterator();
		short size = 0;
		while (itz.hasNext()) {
			cell1 = row1.createCell(size);
			cell1.setCellValue(itz.next().toString());
			cell1.setCellStyle(style);
			size++;
		}
		HSSFRow row = sheet.createRow(1);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell;
		Collection c = map.values();
		Iterator<String> it = c.iterator();
		// 根据选择的字段生成表头
		size = 0;
		while (it.hasNext()) {
			cell = row.createCell(size);
			cell.setCellValue(it.next().toString());
			cell.setCellStyle(style);
			size++;
		}
		if(dtoList!=null&&dtoList.size()>0){
			int zdRow = 1;
			for(Map<String,Object> obj:dtoList){
				int zdCell = 0;
				zdRow++;
				row = sheet.createRow(zdRow);
				// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
				for (short i = 0; i < headersId.length; i++) {
					Object textVal = obj.get(headersId[i]);
					if(textVal==null){
						textVal="";
					}else{
						Object formatDateValue = dateFormat.get(headersId[i]);
						if(formatDateValue!=null){
							textVal = com.test.util.DateUtil.formatDateTime(com.test.util.DateUtil.toDate(textVal.toString()), formatDateValue.toString());
						}
					}
					row.createCell((short) zdCell).setCellValue(textVal.toString());
					zdCell++;
				}
			}
		}
		try {
			wb.write(outputstream);
			outputstream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
