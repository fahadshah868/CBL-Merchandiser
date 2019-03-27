import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

Mobile.verifyElementText(findTestObject('DashboardScreenElements/Validate_DashboardScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Dashboard')

Mobile.swipe(2, 250, 400, 250)

Mobile.swipe(2, 250, 400, 250)

Mobile.verifyElementExist(findTestObject('SliderOptions/Validate_SliderItemsScreen' , [('package') : ProjectConstants.PACKAGENAME]), 0)

CustomKeywords.'com.ct.qa.keywords.SliderOptionsKeywords.valideSliderOptions'()

Mobile.swipe(5, 200, 5, 500)

Mobile.swipe(5, 200, 5, 500)

CustomKeywords.'com.ct.qa.keywords.SliderOptionsKeywords.findSliderOption'('Update Profile')

CustomKeywords.'com.ct.qa.keywords.SliderOptionsKeywords.findSliderOption'('Out of Route')

CustomKeywords.'com.ct.qa.keywords.SliderOptionsKeywords.findSliderOption'('Info')

Mobile.swipe(320, 5, 2, 5)

Mobile.swipe(320, 5, 2, 5)

CustomKeywords.'com.ct.qa.keywords.SliderOptionsKeywords.displayMissingSliderOptionsInReport'()

