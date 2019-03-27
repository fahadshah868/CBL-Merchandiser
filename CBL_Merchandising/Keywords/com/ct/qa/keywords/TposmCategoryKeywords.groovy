package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.MissingCategoryData
import com.ct.qa.struct.TposmBrand
import com.ct.qa.struct.TposmDeployment
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

public class TposmCategoryKeywords {

	@Keyword
	def validateBrands(){
		ArrayList<String> expectedbrands = LoadDataKeywords.loadTposmDeploymentList("brand","")
		ArrayList<String> displayedbrands = new ArrayList<String>()
		int index
		int totalbrands = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= totalbrands; i++){
			MobileElement brand = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String brandtext = brand.getText()
			displayedbrands.add(brandtext)
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
				displayedbrands.add(itemtextafterswipe)
			}
		}
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareLists(expectedbrands, displayedbrands)
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					MissingCategoryData missingcategory = new MissingCategoryData()
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
					MissingCategoryData missingcategory = new MissingCategoryData()
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
					MissingCategoryData missingcategory = new MissingCategoryData()
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
	}
	@Keyword
	def visitBrands(){
		int index
		int totalbrands = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= totalbrands; i++){
			MobileElement brand = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String brandtext = brand.getText()
			ProjectConstants.CURRENTVISITING_SUBCATEGORY = brandtext
			brand.click()
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/TposmDeployment/Validate_TposmTypeScreen", [('package') : ProjectConstants.PACKAGENAME]), "TPSOM TYPE")
			validateAndVisitTposmTypes(brandtext)
			Mobile.tap(findTestObject("Object Repository/ShopOpen/TposmDeployment/ProceedButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementText(findTestObject('ShopOpen/TposmDeployment/Validate_TposmCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]), 'TPSOM CATEGORY')
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
				ProjectConstants.CURRENTVISITING_SUBCATEGORY = itemtextafterswipe
				itemafterswipe.click()
				Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/TposmDeployment/Validate_TposmTypeScreen", [('package') : ProjectConstants.PACKAGENAME]), "TPSOM TYPE")
				validateAndVisitTposmTypes(itemtextafterswipe)
				Mobile.tap(findTestObject("Object Repository/ShopOpen/TposmDeployment/ProceedButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.verifyElementText(findTestObject('ShopOpen/TposmDeployment/Validate_TposmCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]), 'TPSOM CATEGORY')
			}
		}
	}
	def validateAndVisitTposmTypes(String displayedbrand){
		String remark
		String remark_value
		TposmBrand expectedtposmbrand = LoadDataKeywords.loadTposmDeploymentList("all", displayedbrand)
		ArrayList<TposmDeployment> expectedtposmdeployments
		TposmBrand visitedtposmbrand = new TposmBrand()
		ArrayList<TposmDeployment> visitedtposm = new ArrayList<TposmDeployment>()
		ArrayList<String> displayedtypes = new ArrayList<String>()
		int totaltypes = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		//count all tposm types
		for(int i=1; i<= totaltypes; i++){
			TposmDeployment _tposmdeployment = new TposmDeployment()
			MobileElement type = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String typetext = type.getText()
			displayedtypes.add(typetext)
			visitedtposmbrand.setBrand(displayedbrand)
			_tposmdeployment.setTposmtype(typetext)
			// if data is not exists in excel against brand
			if(expectedtposmbrand != null){
				expectedtposmdeployments = expectedtposmbrand.getTposmdeployments()
				boolean flag = false
				//compare displayed tposm types with expected tposm types
				for(int j=1; j<= expectedtposmdeployments.size(); j++) {
					TposmDeployment tposmdeployment = expectedtposmdeployments.get(j)
					String expectedtype = tposmdeployment.getTposmtype()
					if(true){
						flag = true
						type.click()
						Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/TposmDeployment/Validate_TposmRemarksScreen", [('package') : ProjectConstants.PACKAGENAME]), "TPSOM REMARK")
						if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
							remark = tposmdeployment.getTposmremark()
							remark_value = tposmdeployment.getTposmremarkvalue()
							_tposmdeployment.setTposmremark(remark)
							_tposmdeployment.setTposmremarkvalue(remark_value)
							int totalremarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
							for(int k=1; k<= totalremarks; k++){
								MobileElement _remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+k+"]/android.widget.TextView[1]")
								String _remarktext = _remark.getText()
								if(remark.equalsIgnoreCase(_remarktext)){
									_remark.click()
									if(_remarktext.equalsIgnoreCase("Fresh Deployment")){
										Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/TposmDeployment/Validate_EnterQuantityPopup", [('package') : ProjectConstants.PACKAGENAME]), "Add Quantity")
										MobileElement editfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]")
										editfield.setValue(tposmdeployment.getTposmremarkvalue())
										Mobile.hideKeyboard()
										Mobile.tap(findTestObject("Object Repository/ShopOpen/TposmDeployment/popupSubmitButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
									}
									break
								}
							}
						}
						else{
							remark = tposmdeployment.getOverwrite_tposmremark()
							remark_value = tposmdeployment.getOverwrite_tposmremarkvalue()
							_tposmdeployment.setOverwrite_tposmremark(remark)
							_tposmdeployment.setOverwrite_tposmremarkvalue(remark_value)
							int totalremarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
							for(int k=1; k<= totalremarks; k++){
								MobileElement _remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+k+"]/android.widget.TextView[1]")
								String _remarktext = _remark.getText()
								if(remark.equalsIgnoreCase(_remarktext)){
									_remark.click()
									if(_remarktext.equalsIgnoreCase("Fresh Deployment")){
										Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/TposmDeployment/Validate_EnterQuantityPopup", [('package') : ProjectConstants.PACKAGENAME]), "Add Quantity")
										MobileElement editfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]")
										editfield.setValue(tposmdeployment.getOverwrite_tposmremarkvalue())
										Mobile.hideKeyboard()
										Mobile.tap(findTestObject("Object Repository/ShopOpen/TposmDeployment/popupSubmitButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
									}
									break
								}
							}
						}
						Mobile.pressBack()
						Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/TposmDeployment/Validate_TposmTypeScreen", [('package') : ProjectConstants.PACKAGENAME]), "TPSOM TYPE")
						break
					}
				}
				if(flag == false){
					if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
						type.click()
						Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/TposmDeployment/Validate_TposmRemarksScreen", [('package') : ProjectConstants.PACKAGENAME]), "TPSOM REMARK")
						int totalremarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
						for(int k=1; k<= totalremarks; k++){
							MobileElement _remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+k+"]/android.widget.TextView[1]")
							String _remarktext = _remark.getText()
							if(_remarktext.equalsIgnoreCase("Already Deployed")){
								_remark.click()
								_tposmdeployment.setTposmremark(_remarktext)
								break
							}
						}
					}
					else{
						type.click()
						Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/TposmDeployment/Validate_TposmRemarksScreen", [('package') : ProjectConstants.PACKAGENAME]), "TPSOM REMARK")
						int totalremarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
						for(int k=1; k<= totalremarks; k++){
							MobileElement _remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+k+"]/android.widget.TextView[1]")
							String _remarktext = _remark.getText()
							if(_remarktext.equalsIgnoreCase("Already Deployed")){
								_remark.click()
								_tposmdeployment.setOverwrite_tposmremark(_remarktext)
								break
							}
						}
					}
					Mobile.pressBack()
					Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/TposmDeployment/Validate_TposmTypeScreen", [('package') : ProjectConstants.PACKAGENAME]), "TPSOM TYPE")
				}
			}
			else{
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					type.click()
					Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/TposmDeployment/Validate_TposmRemarksScreen", [('package') : ProjectConstants.PACKAGENAME]), "TPSOM REMARK")
					int totalremarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
					for(int k=1; k<= totalremarks; k++){
						MobileElement _remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+k+"]/android.widget.TextView[1]")
						String _remarktext = _remark.getText()
						if(_remarktext.equalsIgnoreCase("Already Deployed")){
							_remark.click()
							_tposmdeployment.setTposmremark(_remarktext)
							break
						}
					}
				}
				else{
					type.click()
					Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/TposmDeployment/Validate_TposmRemarksScreen", [('package') : ProjectConstants.PACKAGENAME]), "TPSOM REMARK")
					int totalremarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
					for(int k=1; k<= totalremarks; k++){
						MobileElement _remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+k+"]/android.widget.TextView[1]")
						String _remarktext = _remark.getText()
						if(_remarktext.equalsIgnoreCase("Already Deployed")){
							_remark.click()
							_tposmdeployment.setOverwrite_tposmremark(_remarktext)
							break
						}
					}
				}
				Mobile.pressBack()
				Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/TposmDeployment/Validate_TposmTypeScreen", [('package') : ProjectConstants.PACKAGENAME]), "TPSOM TYPE")
			}
			visitedtposm.add(_tposmdeployment)
		}
		visitedtposmbrand.setTposmdeployments(visitedtposm)
		ArrayList<String> expectedtypes = new ArrayList<String>()
		for(int i=0; i< expectedtposmdeployments.size(); i++){
			TposmDeployment tposmdeployment = new TposmDeployment()
			expectedtypes.add(tposmdeployment.getTposmtype())
		}
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareLists(expectedtypes, displayedtypes)
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					MissingCategoryData missingcategory = new MissingCategoryData()
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
					MissingCategoryData missingcategory = new MissingCategoryData()
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
					MissingCategoryData missingcategory = new MissingCategoryData()
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
							ArrayList<TposmBrand> ex_tposmbrands = ex_visitedcategory.getTposmbrands()
							if(ex_tposmbrands.size() != 0){
								boolean tposmbrand_flag = false
								for(int k=0; k< ex_tposmbrands.size(); k++){
									TposmBrand ex_tposmbrand = ex_tposmbrands.get(k)
									if(ex_tposmbrand.getBrand().equalsIgnoreCase(visitedtposmbrand.getBrand())){
										tposmbrand_flag = true
										ArrayList<TposmDeployment> ex_tposmdeployments = ex_tposmbrand.getTposmdeployments()
										for(int m=0; m< ex_tposmdeployments.size(); m++){
											TposmDeployment ex_tposmdeployment = ex_tposmdeployments.get(m)
											for(int n=0; n< visitedtposmbrand.getTposmdeployments().size(); n++){
												TposmDeployment ds_tposmdeployment = visitedtposmbrand.getTposmdeployments().get(n)
												if(ex_tposmdeployment.getTposmtype().equalsIgnoreCase(ds_tposmdeployment.getTposmtype())){
													if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
														ex_tposmdeployment.setTposmremark(ds_tposmdeployment.getTposmremark())
														ex_tposmdeployment.setTposmremarkvalue(ds_tposmdeployment.getTposmremarkvalue())
													}
													else{
														ex_tposmdeployment.setOverwrite_tposmremark(ds_tposmdeployment.getOverwrite_tposmremark())
														ex_tposmdeployment.setOverwrite_tposmremarkvalue(ds_tposmdeployment.getOverwrite_tposmremarkvalue())
													}
												}
											}
										}
									}
								}
								if(tposmbrand_flag == false){
									VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
									visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
									visitedcategorydata.setTposmbrands(visitedtposmbrand)
									ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
									break
								}
							}
						}
					}
					if(visitedcategory_flag == false){
						VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
						visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
						visitedcategorydata.setTposmbrands(visitedtposmbrand)
						ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
						break
					}
				}
				else{
					VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
					visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					visitedcategorydata.setTposmbrands(visitedtposmbrand)
					ProjectConstants.visitedshopdatainfo.get(j).setVisitedcategoriesdata(visitedcategorydata)
					break
				}
			}
		}
	}
}
