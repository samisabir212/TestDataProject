package com.w2a.suite.bankmanager.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.utilities.Constants;
import com.w2a.utilities.DataProviders;
import com.w2a.utilities.DataUtil;
import com.w2a.utilities.ExcelReader;

public class AddCustomerTest {


	/*
	* this add customer test is the bankmanager test
	*
	* only a bank manager can add a customer
	*
	* */


	
	@Test(dataProviderClass=DataProviders.class,dataProvider="bankManagerDP")
	public void addCustomerTest(Hashtable<String,String> data){
		
		/*
		 * Suite
		 * TestCase
		 * Data
		 * 
		 */



		//read this excel path sheet
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);

		//
		DataUtil.checkExecution("BankManagerSuite", "AddCustomerTest", data.get("Runmode"), excel);

	}
	
	


}
