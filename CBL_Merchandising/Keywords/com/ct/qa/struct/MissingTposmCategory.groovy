package com.ct.qa.struct

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class MissingTposmCategory {
	public ArrayList<String> brands
	public String errormessage_brands
	public ArrayList<String> tposmtypes
	public String errormessage_tposmtypes
	public ArrayList<String> getBrands() {
		return brands;
	}
	public void setBrands(ArrayList<String> brands) {
		this.brands = brands;
	}
	public String getErrormessage_brands() {
		return errormessage_brands;
	}
	public void setErrormessage_brands(String errormessage_brands) {
		this.errormessage_brands = errormessage_brands;
	}
	public ArrayList<String> getTposmtypes() {
		return tposmtypes;
	}
	public void setTposmtypes(ArrayList<String> tposmtypes) {
		this.tposmtypes = tposmtypes;
	}
	public String getErrormessage_tposmtypes() {
		return errormessage_tposmtypes;
	}
	public void setErrormessage_tposmtypes(String errormessage_tposmtypes) {
		this.errormessage_tposmtypes = errormessage_tposmtypes;
	}
}
