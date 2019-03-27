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

public class QuestionData {
	public String question
	public String questionoption
	public String questionoption_takepicture
	public String Overwrite_questionoption
	public String Overwrite_questionoption_takepicture
	public String getOverwrite_questionoption() {
		return Overwrite_questionoption;
	}
	public void setOverwrite_questionoption(String overwrite_questionoption) {
		Overwrite_questionoption = overwrite_questionoption;
	}
	public String getOverwrite_questionoption_takepicture() {
		return Overwrite_questionoption_takepicture;
	}
	public void setOverwrite_questionoption_takepicture(String overwrite_questionoption_takepicture) {
		Overwrite_questionoption_takepicture = overwrite_questionoption_takepicture;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestionoption() {
		return questionoption;
	}
	public void setQuestionoption(String questionoption) {
		this.questionoption = questionoption;
	}
	public String getQuestionoption_takepicture() {
		return questionoption_takepicture;
	}
	public void setQuestionoption_takepicture(String questionoption_takepicture) {
		this.questionoption_takepicture = questionoption_takepicture;
	}
}
