import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.ct.qa.constants.ProjectConstants as ProjectConstants
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

'validate the dashboard screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('DashboardScreenElements/Validate_DashboardScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Dashboard')

'select current day'
CustomKeywords.'com.ct.qa.keywords.CommonKeywords.selectday'()

'tap on start your day button to further proceed'
MobileBuiltInKeywords.tap(findTestObject('DashboardScreenElements/StartYourDay', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'Validate shops list screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('Validate_ShopListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Shops on Route')

'select shops from shops list with "Shop Level" overwriting '
CustomKeywords.'com.ct.qa.keywords.ShopVisitingScenariosKeywords.visitShopsWithShopLevel_OverwritingScenarios'()

