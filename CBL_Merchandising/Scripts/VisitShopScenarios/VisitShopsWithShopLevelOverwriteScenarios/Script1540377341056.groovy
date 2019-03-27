import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.ct.qa.constants.ProjectConstants as ProjectConstants
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.Mobile as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

Mobile.verifyElementText(findTestObject('DashboardScreenElements/Validate_DashboardScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Dashboard')

CustomKeywords.'com.ct.qa.keywords.CommonKeywords.selectday'()

Mobile.tap(findTestObject('DashboardScreenElements/StartYourDay', [('package') : ProjectConstants.PACKAGENAME]), 0)

Mobile.verifyElementText(findTestObject('Validate_ShopListScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Shops on Route')

CustomKeywords.'com.ct.qa.keywords.ShopVisitingScenariosKeywords.visitShopsWithShopLevel_OverwriteScenarios'()

