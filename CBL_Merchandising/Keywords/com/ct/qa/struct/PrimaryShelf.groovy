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

public class PrimaryShelf {
	public String shelf
	public float cblwidth
	public float cblheight
	public float cmpwidth
	public float cmpheight
	public float overwrite_cblwidth
	public float overwrite_cblheight
	public float overwrite_cmpwidth
	public float overwrite_cmpheight
	public String getShelf() {
		return shelf;
	}
	public void setShelf(String shelf) {
		this.shelf = shelf;
	}
	public float getCblwidth() {
		return cblwidth;
	}
	public void setCblwidth(float cblwidth) {
		this.cblwidth = cblwidth;
	}
	public float getCblheight() {
		return cblheight;
	}
	public void setCblheight(float cblheight) {
		this.cblheight = cblheight;
	}
	public float getCmpwidth() {
		return cmpwidth;
	}
	public void setCmpwidth(float cmpwidth) {
		this.cmpwidth = cmpwidth;
	}
	public float getCmpheight() {
		return cmpheight;
	}
	public void setCmpheight(float cmpheight) {
		this.cmpheight = cmpheight;
	}
	public float getOverwrite_cblwidth() {
		return overwrite_cblwidth;
	}
	public void setOverwrite_cblwidth(float overwrite_cblwidth) {
		this.overwrite_cblwidth = overwrite_cblwidth;
	}
	public float getOverwrite_cblheight() {
		return overwrite_cblheight;
	}
	public void setOverwrite_cblheight(float overwrite_cblheight) {
		this.overwrite_cblheight = overwrite_cblheight;
	}
	public float getOverwrite_cmpwidth() {
		return overwrite_cmpwidth;
	}
	public void setOverwrite_cmpwidth(float overwrite_cmpwidth) {
		this.overwrite_cmpwidth = overwrite_cmpwidth;
	}
	public float getOverwrite_cmpheight() {
		return overwrite_cmpheight;
	}
	public void setOverwrite_cmpheight(float overwrite_cmpheight) {
		this.overwrite_cmpheight = overwrite_cmpheight;
	}
	
}
