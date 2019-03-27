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

public class VisitedShopDataInfo {
	public String shopname
	public String shopchannel
	public String shop_scenario
	public ArrayList<VisitedCategoryData> visitedcategoriesdata

	public VisitedShopDataInfo(){
		this.visitedcategoriesdata = new ArrayList<VisitedCategoryData>()
	}
	public String getShop_scenario() {
		return shop_scenario;
	}
	public void setShop_scenario(String shop_scenario) {
		this.shop_scenario = shop_scenario;
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

	public ArrayList<VisitedCategoryData> getVisitedcategoriesdata() {
		return visitedcategoriesdata;
	}
	public void setVisitedcategoriesdata(VisitedCategoryData visitedcategoriesdata) {
		this.visitedcategoriesdata.add(visitedcategoriesdata);
	}
}
