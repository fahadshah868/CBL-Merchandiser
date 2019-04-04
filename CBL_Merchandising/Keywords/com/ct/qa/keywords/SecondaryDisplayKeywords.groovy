package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.CategoryWithProducts
import com.ct.qa.struct.MissingCategoryData
import com.ct.qa.struct.QuestionData
import com.ct.qa.struct.VisitedCategoryData
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
import io.appium.java_client.MobileElement

public class SecondaryDisplayKeywords {

	@Keyword
	def visitSecondaryDisplayQuestions(){
		ProjectConstants.CURRENTVISITING_SUBCATEGORY = ""
		ArrayList<String> unmatchedquestions = new ArrayList<String>()
		ArrayList<QuestionData> visitedquestions = new ArrayList<QuestionData>()
		ArrayList<QuestionData> expectedquestions = LoadDataKeywords.loadSecondaryDisplayQuestionsList()
		int totalquestions = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<= totalquestions; i++){
			QuestionData _question = new QuestionData()
			MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String questiontext = question.getText()
			_question.setQuestion(questiontext)
			question.click()
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/SecondaryDisplay/Validate_QuestionOptionsPopup", [('package') : ProjectConstants.PACKAGENAME]), questiontext)
			int totaloptions = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/*").size()
			MobileElement questionoption = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String questionoption_text = questionoption.getText()
			questionoption.click()
			boolean flag = false
			for(int j=0; j< expectedquestions.size(); j++){
				QuestionData expectedquestion = expectedquestions.get(j)
				if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext) && expectedquestion.getQuestionoption().equalsIgnoreCase(questionoption_text)){
					flag = true
					if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
						_question.setQuestionoption(questionoption_text)
						if(expectedquestion.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
							_question.setQuestionoption_takepicture("Y")
						}
						else{
							_question.setQuestionoption_takepicture("N")
						}
					}
					else{
						_question.setOverwrite_questionoption(questionoption_text)
						if(expectedquestion.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
							_question.setOverwrite_questionoption_takepicture("Y")
						}
						else{
							_question.setOverwrite_questionoption_takepicture("N")
						}
					}
					visitedquestions.add(_question)
					break
				}
			}
			if(flag == false){
				CommonKeywords.takePicture()
				unmatchedquestions.add(questiontext)
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					_question.setQuestionoption(questionoption_text)
					_question.setQuestionoption_takepicture("Not Mention")
				}
				else{
					_question.setOverwrite_questionoption(questionoption_text)
					_question.setQuestionoption_takepicture("Not Mention")
				}
				visitedquestions.add(_question)
			}
			Mobile.verifyElementText(findTestObject('ShopOpen/SecondaryDisplay/Validate_SecondaryDisplayScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Secondary Display')
			totalquestions = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		}
		//missing data
		if(unmatchedquestions.size() != 0){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					MissingCategoryData missingcategory = new MissingCategoryData()
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory("")
					missingcategory.setMissingitems(unmatchedquestions)
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		// add visited questions
		//visited data
		for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
			if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
				ArrayList<VisitedCategoryData> ex_visitedcategoriesdata = ProjectConstants.visitedshopdatainfo.get(j).getVisitedcategoriesdata()
				if(ex_visitedcategoriesdata.size() != 0){
					boolean visitedcategory_flag = false
					for(int i=0; i< ex_visitedcategoriesdata.size(); i++){
						VisitedCategoryData ex_visitedcategory = ex_visitedcategoriesdata.get(i)
						if(ex_visitedcategory.getMaincategory().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_MAINCATEGORY)){
							visitedcategory_flag = true
							ArrayList<CategoryWithProducts> ex_categorywithproducts = ex_visitedcategory.getCategorywithproducts()
							if(ex_categorywithproducts.size() != 0){
								boolean categorywithproduct_flag = false
								for(int k=0; k< ex_categorywithproducts.size(); k++){
									CategoryWithProducts ex_categorywithproduct = ex_categorywithproducts.get(k)
									if(ex_categorywithproduct.getCategory().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SUBCATEGORY)){
										categorywithproduct_flag = true
										ArrayList<QuestionData> ex_qauestionsdata = ex_categorywithproduct.getQuestions()
										for(int m=0; m< ex_qauestionsdata.size(); m++){
											QuestionData ex_qauestiondata = ex_qauestionsdata.get(m)
											for(int n=0; n< visitedquestions.size(); n++){
												QuestionData ds_questiondata = visitedquestions.get(n)
												if(ex_qauestiondata.getQuestion().equalsIgnoreCase(ds_questiondata.getQuestion())){
													if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
														ex_qauestiondata.setQuestionoption(ds_questiondata.getQuestionoption())
														ex_qauestiondata.setQuestionoption_takepicture(ds_questiondata.getQuestionoption_takepicture())
														break
													}
													else{
														ex_qauestiondata.setOverwrite_questionoption(ds_questiondata.getOverwrite_questionoption())
														ex_qauestiondata.setOverwrite_questionoption_takepicture(ds_questiondata.getOverwrite_questionoption_takepicture())
														break
													}
												}
											}

										}
									}
								}
								if(categorywithproduct_flag == false){
									CategoryWithProducts categoryproduct = new CategoryWithProducts()
									categoryproduct.setCategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
									categoryproduct.setQuestions(visitedquestions)
									ex_visitedcategory.setCategorywithproducts(categoryproduct)
									break
								}
							}
							else{
								CategoryWithProducts categoryproduct = new CategoryWithProducts()
								categoryproduct.setCategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
								categoryproduct.setQuestions(visitedquestions)
								ex_visitedcategory.setCategorywithproducts(categoryproduct)
								break
							}
						}
					}
					if(visitedcategory_flag == false){
						VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
						CategoryWithProducts categoryproduct = new CategoryWithProducts()
						categoryproduct.setCategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
						categoryproduct.setQuestions(visitedquestions)
						visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
						visitedcategorydata.setCategorywithproducts(categoryproduct)
						ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
						break
					}
				}
				else{
					VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
					CategoryWithProducts categoryproduct = new CategoryWithProducts()
					categoryproduct.setCategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
					categoryproduct.setQuestions(visitedquestions)
					visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					visitedcategorydata.setCategorywithproducts(categoryproduct)
					ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
					break
				}
			}
		}
	}
	@Keyword
	def overwriteSecondaryDisplayQuestions(){
		ProjectConstants.CURRENTVISITING_SUBCATEGORY = ""
		ArrayList<String> unmatchedquestions = new ArrayList<String>()
		ArrayList<QuestionData> visitedquestions = new ArrayList<QuestionData>()
		ArrayList<QuestionData> expectedquestions = LoadDataKeywords.loadSecondaryDisplayQuestionsList()
		int totalquestions = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		MobileElement questionoption
		String questionoption_text
		for(int i=1; i<= totalquestions; i++){
			QuestionData _question = new QuestionData()
			MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String questiontext = question.getText()
			_question.setQuestion(questiontext)
			question.click()
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/SecondaryDisplay/Validate_QuestionOptionsPopup", [('package') : ProjectConstants.PACKAGENAME]), questiontext)
			int totaloptions = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/*").size()
			if(totaloptions > 1){
				questionoption = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/android.widget.LinearLayout[2]/android.widget.TextView[1]")
				questionoption_text = questionoption.getText()
			}
			else{
				questionoption = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				questionoption_text = questionoption.getText()
			}
			questionoption.click()
			boolean flag = false
			for(int j=0; j< expectedquestions.size(); j++){
				QuestionData expectedquestion = expectedquestions.get(j)
				if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext) && expectedquestion.getQuestionoption().equalsIgnoreCase(questionoption_text)){
					flag = true
					if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
						_question.setQuestionoption(questionoption_text)
						if(expectedquestion.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
							_question.setQuestionoption_takepicture("Y")
						}
						else{
							_question.setQuestionoption_takepicture("N")
						}
					}
					else{
						_question.setOverwrite_questionoption(questionoption_text)
						if(expectedquestion.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
							_question.setOverwrite_questionoption_takepicture("Y")
						}
						else{
							_question.setOverwrite_questionoption_takepicture("N")
						}
					}
					visitedquestions.add(_question)
					break
				}
			}
			if(flag == false){
				CommonKeywords.takePicture()
				unmatchedquestions.add(questiontext)
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					_question.setQuestionoption(questionoption_text)
					_question.setQuestionoption_takepicture("Not Mention")
				}
				else{
					_question.setOverwrite_questionoption(questionoption_text)
					_question.setQuestionoption_takepicture("Not Mention")
				}
				visitedquestions.add(_question)
			}
			Mobile.verifyElementText(findTestObject('ShopOpen/SecondaryDisplay/Validate_SecondaryDisplayScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Secondary Display')
			totalquestions = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		}
		//missing data
		if(unmatchedquestions.size() != 0){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					MissingCategoryData missingcategory = new MissingCategoryData()
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory("")
					missingcategory.setMissingitems(unmatchedquestions)
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		// add visited questions
		//visited data
		for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
			if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
				ArrayList<VisitedCategoryData> ex_visitedcategoriesdata = ProjectConstants.visitedshopdatainfo.get(j).getVisitedcategoriesdata()
				if(ex_visitedcategoriesdata.size() != 0){
					boolean visitedcategory_flag = false
					for(int i=0; i< ex_visitedcategoriesdata.size(); i++){
						VisitedCategoryData ex_visitedcategory = ex_visitedcategoriesdata.get(i)
						if(ex_visitedcategory.getMaincategory().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_MAINCATEGORY)){
							visitedcategory_flag = true
							ArrayList<CategoryWithProducts> ex_categorywithproducts = ex_visitedcategory.getCategorywithproducts()
							if(ex_categorywithproducts.size() != 0){
								boolean categorywithproduct_flag = false
								for(int k=0; k< ex_categorywithproducts.size(); k++){
									CategoryWithProducts ex_categorywithproduct = ex_categorywithproducts.get(k)
									if(ex_categorywithproduct.getCategory().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SUBCATEGORY)){
										categorywithproduct_flag = true
										ArrayList<QuestionData> ex_qauestionsdata = ex_categorywithproduct.getQuestions()
										for(int m=0; m< ex_qauestionsdata.size(); m++){
											QuestionData ex_qauestiondata = ex_qauestionsdata.get(m)
											for(int n=0; n< visitedquestions.size(); n++){
												QuestionData ds_questiondata = visitedquestions.get(n)
												if(ex_qauestiondata.getQuestion().equalsIgnoreCase(ds_questiondata.getQuestion())){
													if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
														ex_qauestiondata.setQuestionoption(ds_questiondata.getQuestionoption())
														ex_qauestiondata.setQuestionoption_takepicture(ds_questiondata.getQuestionoption_takepicture())
														break
													}
													else{
														ex_qauestiondata.setOverwrite_questionoption(ds_questiondata.getOverwrite_questionoption())
														ex_qauestiondata.setOverwrite_questionoption_takepicture(ds_questiondata.getOverwrite_questionoption_takepicture())
														break
													}
												}
											}

										}
									}
								}
								if(categorywithproduct_flag == false){
									CategoryWithProducts categoryproduct = new CategoryWithProducts()
									categoryproduct.setCategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
									categoryproduct.setQuestions(visitedquestions)
									ex_visitedcategory.setCategorywithproducts(categoryproduct)
									break
								}
							}
							else{
								CategoryWithProducts categoryproduct = new CategoryWithProducts()
								categoryproduct.setCategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
								categoryproduct.setQuestions(visitedquestions)
								ex_visitedcategory.setCategorywithproducts(categoryproduct)
								break
							}
						}
					}
					if(visitedcategory_flag == false){
						VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
						CategoryWithProducts categoryproduct = new CategoryWithProducts()
						categoryproduct.setCategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
						categoryproduct.setQuestions(visitedquestions)
						visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
						visitedcategorydata.setCategorywithproducts(categoryproduct)
						ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
						break
					}
				}
				else{
					VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
					CategoryWithProducts categoryproduct = new CategoryWithProducts()
					categoryproduct.setCategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
					categoryproduct.setQuestions(visitedquestions)
					visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					visitedcategorydata.setCategorywithproducts(categoryproduct)
					ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
					break
				}
			}
		}
	}
}
