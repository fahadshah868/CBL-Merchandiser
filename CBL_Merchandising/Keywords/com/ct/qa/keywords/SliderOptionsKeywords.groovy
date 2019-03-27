package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mysql.jdbc.DatabaseMetaData.SingleStringIterator
import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.MissingSliderOptions
import com.ct.qa.struct.UnmatchedItems

import internal.GlobalVariable
import io.appium.java_client.MobileElement

public class SliderOptionsKeywords {
	@Keyword
	def valideSliderOptions(){
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareSliderOptions()
		if(unmatcheditems.getStatus() == 2){
			ProjectConstants.missingslideroptions.setMissingslideroptions(unmatcheditems.getItems())
			ProjectConstants.missingslideroptions.setMessageformissingslideroptions(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
		}
		else if(unmatcheditems.getStatus() == 1){
			ProjectConstants.missingslideroptions.setMissingslideroptions(unmatcheditems.getItems())
			ProjectConstants.missingslideroptions.setMessageformissingslideroptions(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
		}
		else if(unmatcheditems.getStatus() == -1){
			ProjectConstants.missingslideroptions.setMissingslideroptions(unmatcheditems.getItems())
			ProjectConstants.missingslideroptions.setMessageformissingslideroptions(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
		}
		else{
		}
	}
	@Keyword
	def displayMissingSliderOptionsInReport(){
		if(!ProjectConstants.missingslideroptions.getMissingslideroptions().isEmpty()){
			String message = "\n\n-----------------------------------------------------Missing Slider Options------------------------------------------------------------------------------------------\n\n"+
					String.format("%-30s", "Slider Options:")
			for(int i=0; i<ProjectConstants.missingslideroptions.getMissingslideroptions().size() ;i++){
				message = message +
						ProjectConstants.missingslideroptions.getMissingslideroptions().get(i)+", "
			}
			message = message + "\n" +ProjectConstants.missingslideroptions.getMessageformissingslideroptions()+
					"\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n"
			KeywordUtil.logInfo(message)
		}
		else{}
	}
	@Keyword
	def findSliderOption(String option){
		Mobile.swipe(5, 200, 5, 500)
		Mobile.swipe(5, 200, 5, 500)
		int index = 0
		boolean flag = false
		int slidertotaloptions = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=slidertotaloptions; i++){
			MobileElement slideroption = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String slideroptionname = slideroption.getText()
			if(slideroptionname.equalsIgnoreCase(option)){
				flag = true
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				if(option.equalsIgnoreCase("Update Profile")){
					Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/UpdateProfile_EnterEmail" , [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/UpdateProfile_EnterPhone" , [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/UpdateProfile_EnterCNIC" , [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.pressBack()
					Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/Validate_SliderItemsScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				}
				else if(option.equalsIgnoreCase("Out of Route")){
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_OutOfRoute_RouteListScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Route LIST')
					findRoute()
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_OutOfRoute_ShopOnRouteScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Shops on Route')
					Mobile.pressBack()
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_OutOfRoute_RouteListScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Route LIST')
					Mobile.pressBack()
					Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/Validate_SliderItemsScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				}
				else if(option.equalsIgnoreCase("Info")){
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_LoginCode' , [('package') : ProjectConstants.PACKAGENAME]), 'Login Code')
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_LoginTime' , [('package') : ProjectConstants.PACKAGENAME]), 'Login Time')
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_SyncAttempted' , [('package') : ProjectConstants.PACKAGENAME]), 'Sync Attempted')
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_SyncSuccessful' , [('package') : ProjectConstants.PACKAGENAME]), 'Sync Successful')
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_RefreshRoutes' , [('package') : ProjectConstants.PACKAGENAME]), 'Refresh Routes')
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_BuildVersion' , [('package') : ProjectConstants.PACKAGENAME]), 'Build Version')
					Mobile.pressBack()
					Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/Validate_SliderItemsScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				}
				break
			}
			else{}
		}
		if(flag == false){
			Mobile.swipe(0, 255, 0, 200)
			while(true){
				index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/*").size()
				MobileElement slideroptionbeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String slideroptionnamebeforeswipe = slideroptionbeforeswipe.getText()
				Mobile.swipe(0, 284, 0, 200)
				index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/*").size()
				MobileElement slideroptionafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String slideroptionnameafterswipe = slideroptionafterswipe.getText()
				if(slideroptionnamebeforeswipe.equals(slideroptionnameafterswipe)){
					break
				}
				else{
					if(slideroptionnameafterswipe.equalsIgnoreCase(option)){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
						if(option.equalsIgnoreCase("Update Profile")){
							Mobile.verifyElementText(findTestObject('SliderOptions/UpdateProfile_EnterEmail' , [('package') : ProjectConstants.PACKAGENAME]), 'Enter email')
							Mobile.verifyElementText(findTestObject('SliderOptions/UpdateProfile_EnterPhone' , [('package') : ProjectConstants.PACKAGENAME]), 'Enter Phone')
							Mobile.verifyElementText(findTestObject('SliderOptions/UpdateProfile_EnterCNIC' , [('package') : ProjectConstants.PACKAGENAME]), 'Enter CNIC')
							Mobile.verifyElementText(findTestObject('SliderOptions/UpdateProfile_SubmitButton' , [('package') : ProjectConstants.PACKAGENAME]), 'Update Profile')
							Mobile.pressBack()
							Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/Validate_SliderItemsScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
						}
						else if(option.equalsIgnoreCase("Out of Route")){
							Mobile.verifyElementText(findTestObject('SliderOptions/Validate_OutOfRoute_RouteListScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Route LIST')
							findRoute()
							Mobile.verifyElementText(findTestObject('SliderOptions/Validate_OutOfRoute_ShopOnRouteScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Shops on Route')
							Mobile.pressBack()
							Mobile.verifyElementText(findTestObject('SliderOptions/Validate_OutOfRoute_RouteListScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Route LIST')
							Mobile.pressBack()
							Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/Validate_SliderItemsScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
						}
						else if(option.equalsIgnoreCase("Info")){
							Mobile.verifyElementText(findTestObject('SliderOptions/Validate_LoginCode' , [('package') : ProjectConstants.PACKAGENAME]), 'Login Code')
							Mobile.verifyElementText(findTestObject('SliderOptions/Validate_LoginTime' , [('package') : ProjectConstants.PACKAGENAME]), 'Login Time')
							Mobile.verifyElementText(findTestObject('SliderOptions/Validate_SyncAttempted' , [('package') : ProjectConstants.PACKAGENAME]), 'Sync Attempted')
							Mobile.verifyElementText(findTestObject('SliderOptions/Validate_SyncSuccessful' , [('package') : ProjectConstants.PACKAGENAME]), 'Sync Successful')
							Mobile.verifyElementText(findTestObject('SliderOptions/Validate_RefreshRoutes' , [('package') : ProjectConstants.PACKAGENAME]), 'Refresh Routes')
							Mobile.verifyElementText(findTestObject('SliderOptions/Validate_BuildVersion' , [('package') : ProjectConstants.PACKAGENAME]), 'Build Version')
							Mobile.pressBack()
							Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/Validate_SliderItemsScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
						}
						break
					}
					else{}
				}
			}
		}
		else{}
	}
	@Keyword
	def findRoute(){
		int totalroutes = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalroutes; i++){
			MobileElement route = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String routename = route.getText()
			if(routename.equalsIgnoreCase("Monday")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				break
			}
		}
	}
}
