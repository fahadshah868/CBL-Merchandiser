package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.CategoryWithProducts
import com.ct.qa.struct.MissingCategoryData
import com.ct.qa.struct.ProductData
import com.ct.qa.struct.QuestionData
import com.ct.qa.struct.UnmatchedItems
import com.ct.qa.struct.VisitedCategoryData
import com.ct.qa.struct.VisitedShopDataInfo
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

public class SurveyKeywords {

	@Keyword
	def visitQuestionCategories(int flag){
		MissingCategoryData missingcategory = new MissingCategoryData()
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareSurveyQuestionCategories()
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setMissingsubcategories(unmatcheditems.getItems())
					missingcategory.setMissingsubcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setMissingsubcategories(unmatcheditems.getItems())
					missingcategory.setMissingsubcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setMissingsubcategories(unmatcheditems.getItems())
					missingcategory.setMissingsubcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else{
		}
		int questioncategorieslist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= questioncategorieslist; i++){
			MobileElement questioncategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SUBCATEGORY = questioncategory.getText()
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			Mobile.verifyElementExist(findTestObject('ShopOpen/Survey/SubmitButton', [('package') : ProjectConstants.PACKAGENAME]), 0)
			if(flag == 1){
				visitQuestionsWithYesRemark()
			}
			else{
				visitQuestionsWithNoRemark()
			}
			Mobile.tap(findTestObject('ShopOpen/Survey/SubmitButton', [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementText(findTestObject('ShopOpen/Survey/Validate_QuestionCategoriesScreen', [('package') : ProjectConstants.PACKAGENAME]),
			'Question Category')
		}
	}
	@Keyword
	def visitQuestionsWithYesRemark(){
		MissingCategoryData missingcategory = new MissingCategoryData()
		int index = 0
		ArrayList<String> displayedquestions = new ArrayList<String>()
		ArrayList<String> expectedquestions = new ArrayList<String>()
		ArrayList<QuestionData> visitedquestions = new ArrayList<QuestionData>()
		ArrayList<QuestionData> expectedquestionslist = LoadDataKeywords.loadSurveyQuestionsList()
		for(int i=0; i< expectedquestionslist.size(); i++){
			expectedquestions.add(expectedquestionslist.get(i).getQuestion())
		}
		int questionlist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
			for(int i=1; i<= questionlist; i++){
				QuestionData visitedquestion = new QuestionData()
				ArrayList<QuestionData> expectedsimilarquestions = new ArrayList<QuestionData>()
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				visitedquestion.setQuestion(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					QuestionData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext)){
						expectedsimilarquestions.add(expectedquestionslist.get(j))
					}
				}
				if(expectedsimilarquestions.size() > 0){
					if(expectedsimilarquestions.size() == 1){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						visitedquestion.setQuestionoption("Y")
						if(expectedsimilarquestions.get(0).getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
							visitedquestion.setQuestionoption_takepicture("Y")
						}
						else{
							visitedquestion.setQuestionoption_takepicture("N")
						}
					}
					else{
						boolean flag = false
						for(int q=0; q< expectedsimilarquestions.size(); q++){
							QuestionData _question = expectedsimilarquestions.get(q)
							if(_question.getQuestionoption().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("Y")
								if(_question.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
									flag = true
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								break
							}
						}
						if(flag == false){
							visitedquestion.setQuestionoption_takepicture("N")
						}
					}
				}
				else{
					visitedquestion.setQuestionoption("Y")
					visitedquestion.setQuestionoption_takepicture("Not Mention")
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
				visitedquestions.add(visitedquestion)
			}
		}
		else{
			for(int i=1; i<= questionlist; i++){
				QuestionData visitedquestion = new QuestionData()
				ArrayList<QuestionData> expectedsimilarquestions = new ArrayList<QuestionData>()
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				visitedquestion.setQuestion(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					QuestionData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext)){
						expectedsimilarquestions.add(expectedquestionslist.get(j))
					}
				}
				if(expectedsimilarquestions.size() > 0){
					if(expectedsimilarquestions.size() == 1){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						visitedquestion.setQuestionoption("Y")
						if(expectedsimilarquestions.get(0).getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
							visitedquestion.setQuestionoption_takepicture("Y")
						}
						else{
							visitedquestion.setQuestionoption_takepicture("N")
						}
					}
					else{
						boolean flag = false
						for(int q=0; q< expectedsimilarquestions.size(); q++){
							QuestionData _question = expectedsimilarquestions.get(q)
							if(_question.getQuestionoption().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("Y")
								if(_question.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
									flag = true
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								break
							}
						}
						if(flag == false){
							visitedquestion.setOverwrite_questionoption_takepicture("N")
						}
					}
				}
				else{
					visitedquestion.setOverwrite_questionoption("Y")
					visitedquestion.setOverwrite_questionoption_takepicture("Not Mention")
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
				visitedquestions.add(visitedquestion)
			}
		}
		ArrayList<String> _expectedquestions = new HashSet<String>(expectedquestions)
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareLists(_expectedquestions, displayedquestions)
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
					missingcategory.setMissingitems(unmatcheditems.getItems())
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
					missingcategory.setMissingitems(unmatcheditems.getItems())
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
					missingcategory.setMissingitems(unmatcheditems.getItems())
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else{
		}
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
										ArrayList<QuestionData> ex_qauestionsdata = ex_categorywithproduct.getSurveyquestions()
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
									categoryproduct.setSurveyquestions(visitedquestions)
									ex_visitedcategory.setCategorywithproducts(categoryproduct)
									break
								}
							}
							else{
								CategoryWithProducts categoryproduct = new CategoryWithProducts()
								categoryproduct.setCategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
								categoryproduct.setSurveyquestions(visitedquestions)
								ex_visitedcategory.setCategorywithproducts(categoryproduct)
								break
							}
						}
					}
					if(visitedcategory_flag == false){
						VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
						CategoryWithProducts categoryproduct = new CategoryWithProducts()
						categoryproduct.setCategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
						categoryproduct.setSurveyquestions(visitedquestions)
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
					categoryproduct.setSurveyquestions(visitedquestions)
					visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					visitedcategorydata.setCategorywithproducts(categoryproduct)
					ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
					break
				}
			}
		}
	}
	@Keyword
	def visitQuestionsWithNoRemark(){
		MissingCategoryData missingcategory = new MissingCategoryData()
		int index = 0
		ArrayList<String> displayedquestions = new ArrayList<String>()
		ArrayList<String> expectedquestions = new ArrayList<String>()
		ArrayList<QuestionData> visitedquestions = new ArrayList<QuestionData>()
		ArrayList<QuestionData> expectedquestionslist = LoadDataKeywords.loadSurveyQuestionsList()
		for(int i=0; i< expectedquestionslist.size(); i++){
			expectedquestions.add(expectedquestionslist.get(i).getQuestion())
		}
		int questionlist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
			for(int i=1; i<= questionlist; i++){
				QuestionData visitedquestion = new QuestionData()
				ArrayList<QuestionData> expectedsimilarquestions = new ArrayList<QuestionData>()
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				visitedquestion.setQuestion(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					QuestionData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext)){
						expectedsimilarquestions.add(expectedquestionslist.get(j))
					}
				}
				if(expectedsimilarquestions.size() > 0){
					if(expectedsimilarquestions.size() == 1){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						visitedquestion.setQuestionoption("Y")
						if(expectedsimilarquestions.get(0).getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
							visitedquestion.setQuestionoption_takepicture("Y")
						}
						else{
							visitedquestion.setQuestionoption_takepicture("N")
						}
					}
					else{
						boolean flag = false
						for(int q=0; q< expectedsimilarquestions.size(); q++){
							QuestionData _question = expectedsimilarquestions.get(q)
							if(_question.getQuestionoption().equalsIgnoreCase("N")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("N")
								if(_question.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
									flag = true
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								break
							}
						}
						if(flag == false){
							visitedquestion.setQuestionoption_takepicture("N")
						}
					}
				}
				else{
					visitedquestion.setQuestionoption("Y")
					visitedquestion.setQuestionoption_takepicture("Not Mention")
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
				visitedquestions.add(visitedquestion)
			}
		}
		else{
			for(int i=1; i<= questionlist; i++){
				QuestionData visitedquestion = new QuestionData()
				ArrayList<QuestionData> expectedsimilarquestions = new ArrayList<QuestionData>()
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				visitedquestion.setQuestion(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					QuestionData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext)){
						expectedsimilarquestions.add(expectedquestionslist.get(j))
					}
				}
				if(expectedsimilarquestions.size() > 0){
					if(expectedsimilarquestions.size() == 1){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						visitedquestion.setQuestionoption("Y")
						if(expectedsimilarquestions.get(0).getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
							visitedquestion.setQuestionoption_takepicture("Y")
						}
						else{
							visitedquestion.setQuestionoption_takepicture("N")
						}
					}
					else{
						boolean flag = false
						for(int q=0; q< expectedsimilarquestions.size(); q++){
							QuestionData _question = expectedsimilarquestions.get(q)
							if(_question.getQuestionoption().equalsIgnoreCase("N")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("N")
								if(_question.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
									flag = true
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								break
							}
						}
						if(flag == false){
							visitedquestion.setOverwrite_questionoption_takepicture("N")
						}
					}
				}
				else{
					visitedquestion.setOverwrite_questionoption("Y")
					visitedquestion.setOverwrite_questionoption_takepicture("Not Mention")
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
				visitedquestions.add(visitedquestion)
			}
		}
		ArrayList<String> _expectedquestions = new HashSet<String>(expectedquestions)
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareLists(_expectedquestions, displayedquestions)
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
					missingcategory.setMissingitems(unmatcheditems.getItems())
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
					missingcategory.setMissingitems(unmatcheditems.getItems())
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
					missingcategory.setMissingitems(unmatcheditems.getItems())
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else{
		}
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
										ArrayList<QuestionData> ex_qauestionsdata = ex_categorywithproduct.getSurveyquestions()
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
									categoryproduct.setSurveyquestions(visitedquestions)
									ex_visitedcategory.setCategorywithproducts(categoryproduct)
									break
								}
							}
							else{
								CategoryWithProducts categoryproduct = new CategoryWithProducts()
								categoryproduct.setCategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
								categoryproduct.setSurveyquestions(visitedquestions)
								ex_visitedcategory.setCategorywithproducts(categoryproduct)
								break
							}
						}
					}
					if(visitedcategory_flag == false){
						VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
						CategoryWithProducts categoryproduct = new CategoryWithProducts()
						categoryproduct.setCategory(ProjectConstants.CURRENTVISITING_SUBCATEGORY)
						categoryproduct.setSurveyquestions(visitedquestions)
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
					categoryproduct.setSurveyquestions(visitedquestions)
					visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					visitedcategorydata.setCategorywithproducts(categoryproduct)
					ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
					break
				}
			}
		}
	}
}
