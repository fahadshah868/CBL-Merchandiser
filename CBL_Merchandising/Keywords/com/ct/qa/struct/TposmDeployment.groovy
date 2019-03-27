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

public class TposmDeployment {
	public String tposmtype
	public String tposmvalue
	public String tposmremark
	public String tposmremarkvalue
	public String overwrite_tposmremark
	public String overwrite_tposmremarkvalue
	public String getTposmtype() {
		return tposmtype;
	}
	public void setTposmtype(String tposmtype) {
		this.tposmtype = tposmtype;
	}
	public String getTposmvalue() {
		return tposmvalue;
	}
	public void setTposmvalue(String tposmvalue) {
		this.tposmvalue = tposmvalue;
	}
	public String getTposmremark() {
		return tposmremark;
	}
	public void setTposmremark(String tposmremark) {
		this.tposmremark = tposmremark;
	}
	public String getTposmremarkvalue() {
		return tposmremarkvalue;
	}
	public void setTposmremarkvalue(String tposmremarkvalue) {
		this.tposmremarkvalue = tposmremarkvalue;
	}
	public String getOverwrite_tposmremark() {
		return overwrite_tposmremark;
	}
	public void setOverwrite_tposmremark(String overwrite_tposmremark) {
		this.overwrite_tposmremark = overwrite_tposmremark;
	}
	public String getOverwrite_tposmremarkvalue() {
		return overwrite_tposmremarkvalue;
	}
	public void setOverwrite_tposmremarkvalue(String overwrite_tposmremarkvalue) {
		this.overwrite_tposmremarkvalue = overwrite_tposmremarkvalue;
	}
}
