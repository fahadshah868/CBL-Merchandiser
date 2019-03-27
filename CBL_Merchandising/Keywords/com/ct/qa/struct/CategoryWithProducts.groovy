package com.ct.qa.struct

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.ArrayList

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

public class CategoryWithProducts {
	public String category
	public ArrayList<ProductData> productsdata
	public ArrayList<QuestionData> surveyquestions
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public ArrayList<ProductData> getProductsdata() {
		return productsdata;
	}
	public void setProductsdata(ArrayList<ProductData> productsdata) {
		this.productsdata = productsdata;
	}
	public ArrayList<QuestionData> getSurveyquestions() {
		return surveyquestions;
	}
	public void setSurveyquestions(ArrayList<QuestionData> surveyquestions) {
		this.surveyquestions = surveyquestions;
	}
}
