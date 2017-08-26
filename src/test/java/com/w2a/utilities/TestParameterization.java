package com.w2a.utilities;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestParameterization {

	/*
	* constantly overloading the getCellData method
	* */

	@Test(dataProvider="getData")
	public void testData(Hashtable<String,String> data){
		
			
		//System.out.println(data.get("Runmode")+"---"+data.get("customer")+"---"+data.get("currency"));
		
		System.out.println(data.get("Runmode")+"---"+data.get("firstname"));
	}

	@DataProvider
	public Object[][] getData() {

		ExcelReader excel = new ExcelReader(
				System.getProperty("user.dir") + "/src/test/resources/testdata/BankManagerSuite.xlsx");

		//total rows of sheet
		int rows = excel.getRowCount(Constants.DATA_SHEET);
		System.out.println("Total rows are : " + rows);


		/************************************TESTNAME ONLY*****************************************************/


		//test case starts at the name of the testName
		String testName = "OpenAccountTest";

		// Find the test case start row
		int testCaseRowNum = 1;

		//stop when testcaserownum is greater than the data written in the rows
		//LOOP ALWAYS BREAKS AND DATA IS RESET
		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

			String testCaseName = excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);

			if (testCaseName.equalsIgnoreCase(testName))
				break;

		}

		System.out.println("Test case starts from row num: " + testCaseRowNum);




		/************************************************************************************/


		// Checking total rows in test case
		int dataStartRowNum = testCaseRowNum + 2;

		int testRows = 0;

		while (!excel.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum + testRows).equals("")) {

			testRows++;
		}

		System.out.println("Total rows of data are : " + testRows);


		/*****************************************************************************/

		// Checking total cols in test case
		//testcaserownum plus 1 will be the rows of the colmns to read
		int colStartColNum = testCaseRowNum + 1;
		int testCols = 0;

		while (!excel.getCellData(Constants.DATA_SHEET, testCols, colStartColNum).equals("")) {

			testCols++;

		}

		System.out.println("Total cols are : " + testCols);

		// Printing data


		//test rows start @ zero and column @ 1
		//rows will stay as is but column will remain 1 because we gave 1 arguement in the testData method hashtable
		//which shoul be equal to the total number of columns and object of 2 dimensional array
		Object[][] data = new Object[testRows][1];

		int i=0;

		//for each row we need to create a hashtable
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

			//creating a hashtable called table of the string type
			Hashtable<String,String> table = new Hashtable<String,String>();
			
			for (int cNum = 0; cNum < testCols; cNum++) {

				//System.out.println(excel.getCellData(Constants.DATA_SHEET, cNum, rNum));
				String testData = excel.getCellData(Constants.DATA_SHEET, cNum, rNum);
				String colName = excel.getCellData(Constants.DATA_SHEET, cNum, colStartColNum);


				//putting all used col name and test data into the hashtable
				/*
				* col name will be the key
				* test data is the value
				* */
				table.put(colName, testData);
			
			}

			data[i][0] = table;
			i++;
					
		}

		return data;
	}

}
