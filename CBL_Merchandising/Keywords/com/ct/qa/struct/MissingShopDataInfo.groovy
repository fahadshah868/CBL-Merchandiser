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

public class MissingShopDataInfo {
	public String shopname
	public String shopchannel
	public String scenario
	public ArrayList<String> missingshopactions
	public String missingshopactions_errormessage
	public ArrayList<String> missingshopcategories
	public String missingshopcategories_errormessage
	public ArrayList<MissingCategoryData> missingcategoriesdata

	public MissingShopDataInfo(){
		this.missingcategoriesdata = new ArrayList<MissingCategoryData>()
	}

	public String getMissingshopactions_errormessage() {
		return missingshopactions_errormessage;
	}
	public void setMissingshopactions_errormessage(String missingshopactions_errormessage) {
		this.missingshopactions_errormessage = missingshopactions_errormessage;
	}
	public ArrayList<String> getMissing_auditquestions() {
		return missing_auditquestions;
	}
	public void setMissing_auditquestions(ArrayList<String> missing_auditquestions) {
		this.missing_auditquestions = missing_auditquestions;
	}
	public String getMissing_auditquestions_errormessage() {
		return missing_auditquestions_errormessage;
	}
	public void setMissing_auditquestions_errormessage(String missing_auditquestions_errormessage) {
		this.missing_auditquestions_errormessage = missing_auditquestions_errormessage;
	}
	public ArrayList<String> getMissingshopactions() {
		return missingshopactions;
	}
	public void setMissingshopactions(ArrayList<String> missingshopactions) {
		this.missingshopactions = missingshopactions;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getShopchannel() {
		return shopchannel;
	}
	public void setShopchannel(String shopchannel) {
		this.shopchannel = shopchannel;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public ArrayList<String> getMissingshopcategories() {
		return missingshopcategories;
	}
	public void setMissingshopcategories(ArrayList<String> missingshopcategories) {
		this.missingshopcategories = missingshopcategories;
	}
	public String getMissingshopcategories_errormessage() {
		return missingshopcategories_errormessage;
	}
	public void setMissingshopcategories_errormessage(String missingshopcategories_errormessage) {
		this.missingshopcategories_errormessage = missingshopcategories_errormessage;
	}
	public ArrayList<MissingCategoryData> getMissingCategoriesData() {
		return missingcategoriesdata;
	}
	public void setMissingCategoriesData(MissingCategoryData missingcategorydata) {
		this.missingcategoriesdata.add(missingcategorydata)
	}
}
