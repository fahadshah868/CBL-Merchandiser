package com.ct.qa.struct

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class MissingCategoryData {
	public String maincategory
	public MissingTposmCategory missingtposmcategory
	public ArrayList<String> missingsubcategories
	public String missingsubcategories_errormessage
	public String subcategory
	public ArrayList<String> missingitems
	public String missingitems_errormessage
	public String getMaincategory() {
		return maincategory;
	}
	public void setMaincategory(String maincategory) {
		this.maincategory = maincategory;
	}
	public MissingTposmCategory getMissingtposmcategory() {
		return missingtposmcategory;
	}
	public void setMissingtposmcategory(MissingTposmCategory missingtposmcategory) {
		this.missingtposmcategory = missingtposmcategory;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public ArrayList<String> getMissingitems() {
		return missingitems;
	}
	public void setMissingitems(ArrayList<String> missingitems) {
		this.missingitems = missingitems;
	}
	public String getMissingitems_errormessage() {
		return missingitems_errormessage;
	}
	public void setMissingitems_errormessage(String missingitems_errormessage) {
		this.missingitems_errormessage = missingitems_errormessage;
	}
	public ArrayList<String> getMissingsubcategories() {
		return missingsubcategories;
	}
	public void setMissingsubcategories(ArrayList<String> missingsubcategories) {
		this.missingsubcategories = missingsubcategories;
	}
	public String getMissingsubcategories_errormessage() {
		return missingsubcategories_errormessage;
	}
	public void setMissingsubcategories_errormessage(String missingsubcategories_errormessage) {
		this.missingsubcategories_errormessage = missingsubcategories_errormessage;
	}
}
