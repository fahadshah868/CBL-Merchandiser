package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.ArrayList

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.CategoryWithProducts
import com.ct.qa.struct.MissingCategoryData
import com.ct.qa.struct.MissingShopDataInfo
import com.ct.qa.struct.MissingTposmCategory
import com.ct.qa.struct.ProductData
import com.ct.qa.struct.UnmatchedItems
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

public class BrandAvailability {

	@Keyword
	def validateBrands(){
		int index
		ArrayList<String> displayed_brands = new ArrayList<String>()
		ArrayList<String> expected_brands = LoadDataKeywords.loadBrands()
		int allbrands = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= allbrands; i++){
			MobileElement brand = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			displayed_brands.add(brand.getText())
		}
		while(true){
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String itemtextbeforeswipe = itembeforeswipe.getText()
			Mobile.swipe(0, 293, 0, 200)
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
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
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					MissingCategoryData missingcategory = new MissingCategoryData()
					MissingTposmCategory missingtposmcategory = new MissingTposmCategory()
					missingtposmcategory.setBrands(unmatched_items.getItems())
					missingtposmcategory.setErrormessage_brands(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setMissingtposmcategory(missingtposmcategory)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else if(unmatched_items.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					MissingCategoryData missingcategory = new MissingCategoryData()
					MissingTposmCategory missingtposmcategory = new MissingTposmCategory()
					missingtposmcategory.setBrands(unmatched_items.getItems())
					missingtposmcategory.setErrormessage_brands(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setMissingtposmcategory(missingtposmcategory)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else if(unmatched_items.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					MissingCategoryData missingcategory = new MissingCategoryData()
					MissingTposmCategory missingtposmcategory = new MissingTposmCategory()
					missingtposmcategory.setBrands(unmatched_items.getItems())
					missingtposmcategory.setErrormessage_brands(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setMissingtposmcategory(missingtposmcategory)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
	}
	@Keyword
	def visitBrands(int flag){
		Mobile.swipe(0, 200, 0, 600)
		Mobile.swipe(0, 200, 0, 600)
		int index
		int brand_number
		int allbrands = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= allbrands; i++){
			brand_number = i
			MobileElement brand = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String brandtext = brand.getText()
			brand.click()
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/BrandAvailability/Validate_AvailabilityScreen", [('package') : ProjectConstants.PACKAGENAME]), "Availability")
			Mobile.tap(findTestObject("Object Repository/ShopOpen/BrandAvailability/BrandAvailability", [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/BrandAvailability/Validate_ProductsAvailabilityScreen", [('package') : ProjectConstants.PACKAGENAME]), "Availability")
			if(flag == 1){
				visitBrandProductsWith_No_Yes_Remarks(brandtext, i)
			}
			else{
				visitBrandProductsWith_Yes_No_Remarks(brandtext, i)
			}
			Mobile.tap(findTestObject("Object Repository/ShopOpen/BrandAvailability/ProductsAvailbility_SubmitButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/BrandAvailability/Validate_AvailabilityScreen", [('package') : ProjectConstants.PACKAGENAME]), "Availability")
			Mobile.pressBack()
			Mobile.verifyElementText(findTestObject('ShopOpen/BrandAvailability/Validate_BrandAvailbility_BrandsScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Category:Brand Availability')
		}
		while(true){
			brand_number = brand_number + 1
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String itemtextbeforeswipe = itembeforeswipe.getText()
			Mobile.swipe(0, 293, 0, 200)
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String itemtextafterswipe = itemafterswipe.getText()
			if(itemtextbeforeswipe.equals(itemtextafterswipe)){
				break
			}
			else{
				itemafterswipe.click()
				Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/BrandAvailability/Validate_AvailabilityScreen", [('package') : ProjectConstants.PACKAGENAME]), "Availability")
				Mobile.tap(findTestObject("Object Repository/ShopOpen/BrandAvailability/BrandAvailability", [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/BrandAvailability/Validate_ProductsAvailabilityScreen", [('package') : ProjectConstants.PACKAGENAME]), "Availability")
				if(flag == 1){
					visitBrandProductsWith_No_Yes_Remarks(itemtextafterswipe, brand_number)
				}
				else{
					visitBrandProductsWith_Yes_No_Remarks(itemtextafterswipe, brand_number)
				}
				Mobile.tap(findTestObject("Object Repository/ShopOpen/BrandAvailability/ProductsAvailbility_SubmitButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/BrandAvailability/Validate_AvailabilityScreen", [('package') : ProjectConstants.PACKAGENAME]), "Availability")
				Mobile.pressBack()
				Mobile.verifyElementText(findTestObject('ShopOpen/BrandAvailability/Validate_BrandAvailbility_BrandsScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Category:Brand Availability')
			}
		}
	}
	def visitBrandProductsWith_No_Yes_Remarks(String brandtext, int brand){
		MobileElement itembeforeswipe
		String itemtextbeforeswipe
		MobileElement itemafterswipe
		String itemtextafterswipe
		ArrayList<String> displayedproducts = new ArrayList<String>()
		ArrayList<ProductData> displayedproducts_withremark = new ArrayList<ProductData>()
		ArrayList<String> expectedproducts = LoadDataKeywords.loadBrandProducts(brandtext)
		int index
		int totalproducts = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
		if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
			if(brand%2 == 0){
				for(int i=1; i<= totalproducts; i++){
					ProductData productdata = new ProductData()
					MobileElement product = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					displayedproducts.add(product.getText())
					productdata.setProduct(product.getText())
					productdata.setRemark("yes")
					displayedproducts_withremark.add(productdata)
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[1]").click()
				}
				while(true){
					ProductData productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 273, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						displayedproducts.add(itemtextafterswipe)
						productdata.setProduct(itemtextafterswipe)
						productdata.setRemark("yes")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[1]").click()
					}
					//again swipe with different values
					productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 274, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						displayedproducts.add(itemtextafterswipe)
						productdata.setProduct(itemtextafterswipe)
						productdata.setRemark("yes")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[1]").click()
					}
				}
			}
			else{
				for(int i=1; i<= totalproducts; i++){
					ProductData productdata = new ProductData()
					MobileElement product = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					displayedproducts.add(product.getText())
					productdata.setProduct(product.getText())
					productdata.setRemark("no")
					displayedproducts_withremark.add(productdata)
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[2]").click()
				}
				while(true){
					ProductData productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 273, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						displayedproducts.add(itemtextafterswipe)
						productdata.setProduct(itemtextafterswipe)
						productdata.setRemark("no")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[2]").click()
					}
					//again swipe with different values
					productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 274, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						displayedproducts.add(itemtextafterswipe)
						productdata.setProduct(itemtextafterswipe)
						productdata.setRemark("no")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[2]").click()
					}
				}
			}
		}
		else{
			if(brand%2 == 0){
				for(int i=1; i<= totalproducts; i++){
					ProductData productdata = new ProductData()
					MobileElement product = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					productdata.setProduct(product.getText())
					productdata.setOverwrite_remark("yes")
					displayedproducts_withremark.add(productdata)
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[1]").click()
				}
				while(true){
					ProductData productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 273, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						productdata.setProduct(itemtextafterswipe)
						productdata.setOverwrite_remark("yes")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[1]").click()
					}
					//again swipe with different values
					productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 274, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						productdata.setProduct(itemtextafterswipe)
						productdata.setOverwrite_remark("yes")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[1]").click()
					}
				}
			}
			else{
				for(int i=1; i<= totalproducts; i++){
					ProductData productdata = new ProductData()
					MobileElement product = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					productdata.setProduct(product.getText())
					productdata.setOverwrite_remark("no")
					displayedproducts_withremark.add(productdata)
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[2]").click()
				}
				while(true){
					ProductData productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 273, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						productdata.setProduct(itemtextafterswipe)
						productdata.setOverwrite_remark("no")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[2]").click()
					}
					//again swipe with different values
					productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 274, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						productdata.setProduct(itemtextafterswipe)
						productdata.setOverwrite_remark("no")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[2]").click()
					}
				}
			}
		}
		UnmatchedItems unmatched_items = CompareDataKeywords.compareLists(expectedproducts, displayedproducts)
		if(unmatched_items.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					MissingCategoryData missingcategory = new MissingCategoryData()
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory(brandtext)
					missingcategory.setMissingitems(unmatched_items.getItems())
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else if(unmatched_items.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					MissingCategoryData missingcategory = new MissingCategoryData()
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory(brandtext)
					missingcategory.setMissingitems(unmatched_items.getItems())
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else if(unmatched_items.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					MissingCategoryData missingcategory = new MissingCategoryData()
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory(brandtext)
					missingcategory.setMissingitems(unmatched_items.getItems())
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
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
									if(ex_categorywithproduct.getCategory().equalsIgnoreCase(brandtext)){
										categorywithproduct_flag = true
										ArrayList<ProductData> ex_productsdata = ex_categorywithproduct.getProductsdata()
										for(int m=0; m< ex_productsdata.size(); m++){
											ProductData ex_productdata = ex_productsdata.get(m)
											for(int n=0; n< displayedproducts_withremark.size(); n++){
												ProductData ds_productdata = displayedproducts_withremark.get(n)
												if(ex_productdata.getProduct().equalsIgnoreCase(ds_productdata.getProduct())){
													if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
														ex_productdata.setRemark(ds_productdata.getRemark())
														break
													}
													else{
														ex_productdata.setOverwrite_remark(ds_productdata.getOverwrite_remark())
														break
													}
												}
											}

										}
									}
								}
								if(categorywithproduct_flag == false){
									CategoryWithProducts categoryproduct = new CategoryWithProducts()
									categoryproduct.setCategory(brandtext)
									categoryproduct.setProductsdata(displayedproducts_withremark)
									ex_visitedcategory.setCategorywithproducts(categoryproduct)
									break
								}
							}
							else{
								CategoryWithProducts categoryproduct = new CategoryWithProducts()
								categoryproduct.setCategory(brandtext)
								categoryproduct.setProductsdata(displayedproducts_withremark)
								ex_visitedcategory.setCategorywithproducts(categoryproduct)
								break
							}
						}
					}
					if(visitedcategory_flag == false){
						VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
						CategoryWithProducts categoryproduct = new CategoryWithProducts()
						categoryproduct.setCategory(brandtext)
						categoryproduct.setProductsdata(displayedproducts_withremark)
						visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
						visitedcategorydata.setCategorywithproducts(categoryproduct)
						ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
						break
					}
				}
				else{
					VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
					CategoryWithProducts categoryproduct = new CategoryWithProducts()
					categoryproduct.setCategory(brandtext)
					categoryproduct.setProductsdata(displayedproducts_withremark)
					visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					visitedcategorydata.setCategorywithproducts(categoryproduct)
					ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
					break
				}
			}
		}
	}
	def visitBrandProductsWith_Yes_No_Remarks(String brandtext, int brand){
		MobileElement itembeforeswipe
		String itemtextbeforeswipe
		MobileElement itemafterswipe
		String itemtextafterswipe
		ArrayList<String> displayedproducts = new ArrayList<String>()
		ArrayList<ProductData> displayedproducts_withremark = new ArrayList<ProductData>()
		ArrayList<String> expectedproducts = LoadDataKeywords.loadBrandProducts(brandtext)
		int index
		int totalproducts = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
		if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
			if(brand%2 != 0){
				for(int i=1; i<= totalproducts; i++){
					ProductData productdata = new ProductData()
					MobileElement product = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					displayedproducts.add(product.getText())
					productdata.setProduct(product.getText())
					productdata.setRemark("yes")
					displayedproducts_withremark.add(productdata)
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[1]").click()
				}
				while(true){
					ProductData productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 273, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						displayedproducts.add(itemtextafterswipe)
						productdata.setProduct(itemtextafterswipe)
						productdata.setRemark("yes")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[1]").click()
					}
					//again swipe with different values
					productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 274, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						displayedproducts.add(itemtextafterswipe)
						productdata.setProduct(itemtextafterswipe)
						productdata.setRemark("yes")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[1]").click()
					}
				}
			}
			else{
				for(int i=1; i<= totalproducts; i++){
					ProductData productdata = new ProductData()
					MobileElement product = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					displayedproducts.add(product.getText())
					productdata.setProduct(product.getText())
					productdata.setRemark("no")
					displayedproducts_withremark.add(productdata)
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[2]").click()
				}
				while(true){
					ProductData productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 273, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						displayedproducts.add(itemtextafterswipe)
						productdata.setProduct(itemtextafterswipe)
						productdata.setRemark("no")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[2]").click()
					}
					//again swipe with different values
					productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 274, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						displayedproducts.add(itemtextafterswipe)
						productdata.setProduct(itemtextafterswipe)
						productdata.setRemark("no")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[2]").click()
					}
				}
			}
		}
		else{
			if(brand%2 != 0){
				for(int i=1; i<= totalproducts; i++){
					ProductData productdata = new ProductData()
					MobileElement product = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					productdata.setProduct(product.getText())
					productdata.setOverwrite_remark("yes")
					displayedproducts_withremark.add(productdata)
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[1]").click()
				}
				while(true){
					ProductData productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 273, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						productdata.setProduct(itemtextafterswipe)
						productdata.setOverwrite_remark("yes")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[1]").click()
					}
					//again swipe with different values
					productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 274, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						productdata.setProduct(itemtextafterswipe)
						productdata.setOverwrite_remark("yes")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[1]").click()
					}
				}
			}
			else{
				for(int i=1; i<= totalproducts; i++){
					ProductData productdata = new ProductData()
					MobileElement product = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					productdata.setProduct(product.getText())
					productdata.setOverwrite_remark("no")
					displayedproducts_withremark.add(productdata)
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[2]").click()
				}
				while(true){
					ProductData productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 273, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						productdata.setProduct(itemtextafterswipe)
						productdata.setOverwrite_remark("no")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[2]").click()
					}
					//again swipe with different values
					productdata = new ProductData()
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextbeforeswipe = itembeforeswipe.getText()
					Mobile.swipe(0, 274, 0, 200)
					index = ProjectConstants.DRIVER.findElementsByClassName("android.widget.RadioGroup").size()
					itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
					itemtextafterswipe = itemafterswipe.getText()
					if(itemtextbeforeswipe.equals(itemtextafterswipe)){
						break
					}
					else{
						productdata.setProduct(itemtextafterswipe)
						productdata.setOverwrite_remark("no")
						displayedproducts_withremark.add(productdata)
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RadioGroup[1]/android.widget.RadioButton[2]").click()
					}
				}
			}
		}
		UnmatchedItems unmatched_items = CompareDataKeywords.compareLists(expectedproducts, displayedproducts)
		if(unmatched_items.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					MissingCategoryData missingcategory = new MissingCategoryData()
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory(brandtext)
					missingcategory.setMissingitems(unmatched_items.getItems())
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else if(unmatched_items.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					MissingCategoryData missingcategory = new MissingCategoryData()
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory(brandtext)
					missingcategory.setMissingitems(unmatched_items.getItems())
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
		}
		else if(unmatched_items.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					MissingCategoryData missingcategory = new MissingCategoryData()
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setSubcategory(brandtext)
					missingcategory.setMissingitems(unmatched_items.getItems())
					missingcategory.setMissingitems_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory)
					break
				}
				else{
				}
			}
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
									if(ex_categorywithproduct.getCategory().equalsIgnoreCase(brandtext)){
										categorywithproduct_flag = true
										ArrayList<ProductData> ex_productsdata = ex_categorywithproduct.getProductsdata()
										for(int m=0; m< ex_productsdata.size(); m++){
											ProductData ex_productdata = ex_productsdata.get(m)
											for(int n=0; n< displayedproducts_withremark.size(); n++){
												ProductData ds_productdata = displayedproducts_withremark.get(n)
												if(ex_productdata.getProduct().equalsIgnoreCase(ds_productdata.getProduct())){
													if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
														ex_productdata.setRemark(ds_productdata.getRemark())
														break
													}
													else{
														ex_productdata.setOverwrite_remark(ds_productdata.getOverwrite_remark())
														break
													}
												}
											}

										}
									}
								}
								if(categorywithproduct_flag == false){
									CategoryWithProducts categoryproduct = new CategoryWithProducts()
									categoryproduct.setCategory(brandtext)
									categoryproduct.setProductsdata(displayedproducts_withremark)
									ex_visitedcategory.setCategorywithproducts(categoryproduct)
									break
								}
							}
							else{
								CategoryWithProducts categoryproduct = new CategoryWithProducts()
								categoryproduct.setCategory(brandtext)
								categoryproduct.setProductsdata(displayedproducts_withremark)
								ex_visitedcategory.setCategorywithproducts(categoryproduct)
								break
							}
						}
					}
					if(visitedcategory_flag == false){
						VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
						CategoryWithProducts categoryproduct = new CategoryWithProducts()
						categoryproduct.setCategory(brandtext)
						categoryproduct.setProductsdata(displayedproducts_withremark)
						visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
						visitedcategorydata.setCategorywithproducts(categoryproduct)
						ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
						break
					}
				}
				else{
					VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
					CategoryWithProducts categoryproduct = new CategoryWithProducts()
					categoryproduct.setCategory(brandtext)
					categoryproduct.setProductsdata(displayedproducts_withremark)
					visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					visitedcategorydata.setCategorywithproducts(categoryproduct)
					ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
					break
				}
			}
		}
	}
}
