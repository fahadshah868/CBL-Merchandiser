package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.PrimaryShelf
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

public class PrimaryShelfKeywords {

	@Keyword
	def visitPrimaryShelf(){
		int shelfcount = ProjectConstants.SHELF_COUNT
		for(int i=1; i<= shelfcount; i++){
			Mobile.tap(findTestObject("Object Repository/ShopOpen/PrimaryShelf/AddShelf_Button", [('package') : ProjectConstants.PACKAGENAME]), 0)
			int totalshelfs = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement shelf = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.FrameLayout["+totalshelfs+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String shelftext = shelf.getText()
			shelf.click()
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/PrimaryShelf/Validate_ShelfScreen", [('package') : ProjectConstants.PACKAGENAME]), shelftext)
			PrimaryShelf primaryshelfdimensions = LoadDataKeywords.loadPrimaryShelf_Dimensions(totalshelfs)
			primaryshelfdimensions.setShelf(shelftext)
			if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
				MobileElement cbl_width = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]")
				cbl_width.clear()
				cbl_width.setValue(primaryshelfdimensions.getCblwidth().toString())
				MobileElement cbl_height = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[2]")
				cbl_width.clear()
				cbl_height.setValue(primaryshelfdimensions.getCblheight().toString())
				MobileElement cmp_width = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[4]/android.widget.EditText[1]")
				cbl_width.clear()
				cmp_width.setValue(primaryshelfdimensions.getCmpwidth().toString())
				MobileElement cmp_height = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[4]/android.widget.EditText[2]")
				cbl_width.clear()
				cmp_height.setValue(primaryshelfdimensions.getCmpheight().toString())
			}
			else{
				MobileElement cbl_width = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]")
				cbl_width.clear()
				cbl_width.setValue(primaryshelfdimensions.getOverwrite_cblwidth().toString())
				MobileElement cbl_height = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[2]")
				cbl_width.clear()
				cbl_height.setValue(primaryshelfdimensions.getOverwrite_cblheight().toString())
				MobileElement cmp_width = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[4]/android.widget.EditText[1]")
				cbl_width.clear()
				cmp_width.setValue(primaryshelfdimensions.getOverwrite_cmpwidth().toString())
				MobileElement cmp_height = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[4]/android.widget.EditText[2]")
				cbl_width.clear()
				cmp_height.setValue(primaryshelfdimensions.getOverwrite_cmpheight().toString())
			}
			addVisitedPrimaryShelf(primaryshelfdimensions)
			Mobile.hideKeyboard()
			Mobile.tap(findTestObject("Object Repository/ShopOpen/PrimaryShelf/Shelf_DoneButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementText(findTestObject('ShopOpen/PrimaryShelf/Validate_ShelfListScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Shelf List')
		}
	}
	@Keyword
	def overwritePrimaryShelf(){
		int totalshelfs = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= totalshelfs; i++){
			MobileElement shelf = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.FrameLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String shelftext = shelf.getText()
			shelf.click()
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/PrimaryShelf/Validate_ShelfScreen", [('package') : ProjectConstants.PACKAGENAME]), shelftext)
			PrimaryShelf primaryshelfdimensions = LoadDataKeywords.loadPrimaryShelf_Dimensions(i)
			primaryshelfdimensions.setShelf(shelftext)
			if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
				MobileElement cbl_width = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]")
				cbl_width.clear()
				cbl_width.setValue(primaryshelfdimensions.getCblwidth().toString())
				MobileElement cbl_height = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[2]")
				cbl_width.clear()
				cbl_height.setValue(primaryshelfdimensions.getCblheight().toString())
				MobileElement cmp_width = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[4]/android.widget.EditText[1]")
				cbl_width.clear()
				cmp_width.setValue(primaryshelfdimensions.getCmpwidth().toString())
				MobileElement cmp_height = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[4]/android.widget.EditText[2]")
				cbl_width.clear()
				cmp_height.setValue(primaryshelfdimensions.getCmpheight().toString())
			}
			else{
				MobileElement cbl_width = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]")
				cbl_width.clear()
				cbl_width.setValue(primaryshelfdimensions.getOverwrite_cblwidth().toString())
				MobileElement cbl_height = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[2]")
				cbl_width.clear()
				cbl_height.setValue(primaryshelfdimensions.getOverwrite_cblheight().toString())
				MobileElement cmp_width = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[4]/android.widget.EditText[1]")
				cbl_width.clear()
				cmp_width.setValue(primaryshelfdimensions.getOverwrite_cmpwidth().toString())
				MobileElement cmp_height = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[4]/android.widget.EditText[2]")
				cbl_width.clear()
				cmp_height.setValue(primaryshelfdimensions.getOverwrite_cmpheight().toString())
			}
			addVisitedPrimaryShelf(primaryshelfdimensions)
			Mobile.hideKeyboard()
			Mobile.tap(findTestObject("Object Repository/ShopOpen/PrimaryShelf/Shelf_DoneButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementText(findTestObject('ShopOpen/PrimaryShelf/Validate_ShelfListScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Shelf List')
		}
	}
	def addVisitedPrimaryShelf(PrimaryShelf primaryshelf){
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
							boolean shelf_flag = false
							ArrayList<PrimaryShelf> ex_primaryshelfs = ex_visitedcategory.getPrimaryshelf()
							for(int k=0; k< ex_primaryshelfs.size(); k++){
								PrimaryShelf ex_primaryshelf = ex_primaryshelfs.get(k)
								if(ex_primaryshelf.getShelf().equalsIgnoreCase(primaryshelf.getShelf())){
									shelf_flag = true
									if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
										ex_primaryshelf.setCblwidth(primaryshelf.getCblwidth())
										ex_primaryshelf.setCblheight(primaryshelf.getCblheight())
										ex_primaryshelf.setCmpwidth(primaryshelf.getCmpwidth())
										ex_primaryshelf.setCmpheight(primaryshelf.getCmpheight())
									}
									else{
										ex_primaryshelf.setOverwrite_cblwidth(primaryshelf.getOverwrite_cblwidth())
										ex_primaryshelf.setOverwrite_cblheight(primaryshelf.getOverwrite_cblheight())
										ex_primaryshelf.setOverwrite_cmpwidth(primaryshelf.getOverwrite_cmpwidth())
										ex_primaryshelf.setOverwrite_cmpheight(primaryshelf.getOverwrite_cmpheight())
									}
								}
							}
							if(shelf_flag == false){
								ex_visitedcategory.setPrimaryshelf(primaryshelf)
							}
						}
					}
					if(visitedcategory_flag == false){
						VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
						visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
						visitedcategorydata.setPrimaryshelf(primaryshelf)
						ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
						break
					}
				}
				else{
					VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
					visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					visitedcategorydata.setPrimaryshelf(primaryshelf)
					ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
					break
				}
			}
		}
	}
}
