package rough;

import com.w2a.utilities.Constants;
import com.w2a.utilities.ExcelReader;

public class ReadingExlData {

	public static void main(String[] args) {


		ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"/src/test/resources/testdata/BankManagerSuite.xlsx");



		int rows = excel.getRowCount(Constants.DATA_SHEET);
		System.out.println("Total rows are : "+rows);
		

		/*
		* TEST NAME GOES HERE WHEN SETTING UP READER TO TEST WHAT TESTCASE YOU WANT TO TEST'
		* JUST STORED IN A VARIABLE.
		* WE CAN MAKE A MORE COMPLEX WAY OF PARAMETERIZING DIFFERENT TESTCASE NAMES... ETC
		* */
		String testName = "AddCustomerTest";
		
		//Find the test case start row
		
		int testCaseRowNum=1;
		
		
		for(testCaseRowNum=1; testCaseRowNum<=rows; testCaseRowNum++){

			/*reading data from the zero column
			* the testcase name */
			String testCaseName = excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);

			/*if test name then break and come out of loop*/
			if(testCaseName.equalsIgnoreCase(testName))
				break;
			
		}
		
		System.out.println("Test case starts from row num: "+testCaseRowNum);
		
		
		//Checking total rows in test case
		//STARTING TEST AT ROW 2    (  O+2 = 2  )
		int dataStartRowNum = testCaseRowNum+2;
		
		int testRows = 0;
		while(!excel.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum+testRows).equals("")){
			
			testRows++;
		}
		
		
		System.out.println("Total rows of data are : "+testRows);
		
		//Checking total cols in test case
		
		
		int colStartColNum = testCaseRowNum+1;
		int testCols=0;
		
		
		while(!excel.getCellData(Constants.DATA_SHEET, testCols, colStartColNum).equals("")){
			
			testCols++;
			
		}
		
		
		System.out.println("Total cols are : "+testCols);
		
		
		//Printing data
		
		for(int rNum=dataStartRowNum; rNum<(dataStartRowNum+testRows);rNum++){
			
			for(int cNum=0; cNum<testCols; cNum++){
				
				System.out.println(excel.getCellData(Constants.DATA_SHEET, cNum, rNum));
				
			}
			
		}
		
		
	}

}
