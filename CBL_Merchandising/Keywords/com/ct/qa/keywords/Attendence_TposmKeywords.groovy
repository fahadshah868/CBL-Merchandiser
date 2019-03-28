package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.TposmBrand
import com.ct.qa.struct.TposmDeployment
import com.ct.qa.struct.UnmatchedItems
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
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement

public class Attendence_TposmKeywords {

	AppiumDriver<MobileElement> driver = ProjectConstants.DRIVER;

	@Keyword
	def visitAttendance(){
		int attendance_remarks = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.RadioGroup[1]/*").size()
		for(int i=1; i<= attendance_remarks; i++){
			MobileElement remark = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton["+i+"]")
			String remark_text = remark.getText()
			if(remark_text.equalsIgnoreCase("Start from Distribution")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton["+i+"]").click()
				break
			}
		}
	}
	@Keyword
	def validateBrands(){
		int index
		ArrayList<String> displayed_brands = new ArrayList<String>()
		ArrayList<String> expected_brands = LoadDataKeywords.loadBrands()
		int allbrands = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= allbrands; i++){
			MobileElement brand = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			displayed_brands.add(brand.getText())
		}
		while(true){
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String itemtextbeforeswipe = itembeforeswipe.getText()
			Mobile.swipe(0, 292, 0, 200)
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String itemtextafterswipe = itemafterswipe.getText()
			if(itemtextbeforeswipe.equals(itemtextafterswipe)){
				break
			}
			else{
				displayed_brands.add(itemtextafterswipe)
			}
		}
		UnmatchedItems unmatched_items = CompareDataKeywords.compareLists(expected_brands, displayed_brands)
		if(unmatched_items.getStatus() == 2){
			ProjectConstants.missingtposmcategories.setBrands(unmatched_items.getItems())
			ProjectConstants.missingtposmcategories.setErrormessage_brands(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
		}
		else if(unmatched_items.getStatus() == 1){
			ProjectConstants.missingtposmcategories.setBrands(unmatched_items.getItems())
			ProjectConstants.missingtposmcategories.setErrormessage_brands(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
		}
		else if(unmatched_items.getStatus() == -1){
			ProjectConstants.missingtposmcategories.setBrands(unmatched_items.getItems())
			ProjectConstants.missingtposmcategories.setErrormessage_brands(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
		}
	}
	@Keyword
	def visitTposmCategories(){
		int index
		ArrayList<String> brands = LoadDataKeywords.loadTposmDeploymentList("brand","")
		ArrayList<String> expectedbrands = new HashSet<String>(brands)
		for(int j=0; j< expectedbrands.size(); j++){
			Mobile.swipe(0, 100, 0, 500)
			Mobile.swipe(0, 100, 0, 500)
			int allbrands = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			boolean brand_flag = false
			for(int i=1; i<= allbrands; i++){
				MobileElement brand = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				String brandtext = brand.getText()
				if(expectedbrands.get(j).equalsIgnoreCase(brandtext)){
					brand_flag = true
					brand.click()
					Mobile.verifyElementText(findTestObject("Object Repository/Attendence_Tposm/Validate_TposmTypeScreen", [('package') : ProjectConstants.PACKAGENAME]), "TPOSM TYPE")
					if(j == 0){
						validateTposmCategoryTypes()
					}
					visitTposmCategoryTypes(brandtext)
					Mobile.tap(findTestObject("Object Repository/Attendence_Tposm/TposmCategory_ProceedButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.verifyElementText(findTestObject('Attendence_Tposm/Validate_TposmCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]), 'TPOSM CATEGORY')
					break
				}
			}
			if(brand_flag == false){
				while(true){
					index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
					MobileElement itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
					String itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 292, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
					MobileElement itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
					String itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						if(expectedbrands.get(j).equalsIgnoreCase(itemtextafterswipe)){
							itemafterswipe.click()
							Mobile.verifyElementText(findTestObject("Object Repository/Attendence_Tposm/Validate_TposmTypeScreen", [('package') : ProjectConstants.PACKAGENAME]), "TPOSM TYPE")
							if(j == 0){
								validateTposmCategoryTypes()
							}
							visitTposmCategoryTypes(itemtextafterswipe)
							Mobile.tap(findTestObject("Object Repository/Attendence_Tposm/TposmCategory_ProceedButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
							Mobile.verifyElementText(findTestObject('Attendence_Tposm/Validate_TposmCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]), 'TPOSM CATEGORY')
							break
						}
					}
				}
			}
		}
	}
	def validateTposmCategoryTypes(){
		int index
		ArrayList<String> displayed_types = new ArrayList<String>()
		ArrayList<String> expected_types = LoadDataKeywords.loadTposmTypes()
		int totaltypes = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= totaltypes; i++){
			MobileElement type = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			displayed_types.add(type.getText())
		}
		while(true){
			index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String itemtextbeforeswipe = itembeforeswipe.getText()
			Mobile.swipe(0, 314, 0, 200)
			index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String itemtextafterswipe = itemafterswipe.getText()
			if(itemtextbeforeswipe.equals(itemtextafterswipe)){
				break
			}
			else{
				displayed_types.add(itemtextafterswipe)
			}
		}
		UnmatchedItems unmatched_items = CompareDataKeywords.compareLists(expected_types, displayed_types)
		if(unmatched_items.getStatus() == 2){
			ProjectConstants.missingtposmcategories.setTposmtypes(unmatched_items.getItems())
			ProjectConstants.missingtposmcategories.setErrormessage_tposmtypes(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
		}
		else if(unmatched_items.getStatus() == 1){
			ProjectConstants.missingtposmcategories.setTposmtypes(unmatched_items.getItems())
			ProjectConstants.missingtposmcategories.setErrormessage_tposmtypes(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
		}
		else if(unmatched_items.getStatus() == -1){
			ProjectConstants.missingtposmcategories.setTposmtypes(unmatched_items.getItems())
			ProjectConstants.missingtposmcategories.setErrormessage_tposmtypes(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
		}
	}
	def visitTposmCategoryTypes(String brand){
		Mobile.swipe(0, 100, 0, 500)
		Mobile.swipe(0, 100, 0, 500)
		TposmBrand tposmbrand = LoadDataKeywords.loadTposmDeploymentList("all",brand)
		ArrayList<TposmDeployment> tposmdeployments = tposmbrand.getTposmdeployments()
		int totaltypes = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=0; i< tposmdeployments.size(); i++){
			TposmDeployment tposmdeployment = tposmdeployments.get(i)

			for(int j=1; j<= totaltypes; j++){
				MobileElement type = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]/android.widget.TextView[1]")
				String typetext = type.getText()
				MobileElement tposm_textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]/android.widget.EditText[1]")
				if(typetext.equalsIgnoreCase(tposmdeployment.getTposmtype())){
					tposm_textfield.setValue(tposmdeployment.getTposmvalue())
					Mobile.hideKeyboard()
					break
				}
			}
		}
	}
}
