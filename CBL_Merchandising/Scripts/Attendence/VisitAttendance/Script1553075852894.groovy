import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.ct.qa.constants.ProjectConstants as ProjectConstants
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

'validate dashboard screen appearance'
Mobile.verifyElementText(findTestObject('DashboardScreenElements/Validate_DashboardScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Dashboard')

'select the current day for further proceed'
CustomKeywords.'com.ct.qa.keywords.CommonKeywords.selectday'()

'tap on start your day button'
Mobile.tap(findTestObject('DashboardScreenElements/StartYourDay', [('package') : ProjectConstants.PACKAGENAME]), 0)

Mobile.verifyElementText(findTestObject('Attendence_Tposm/Validate_AttendenceScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Attendance')

Mobile.tap(findTestObject('Attendence_Tposm/TakeSelfie_ImageViewButton', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

CustomKeywords.'com.ct.qa.keywords.CommonKeywords.takePicture'()

CustomKeywords.'com.ct.qa.keywords.Attendence_TposmKeywords.visitAttendance'()

Mobile.tap(findTestObject('Attendence_Tposm/Attendence_SubmitButton', [('package') : ProjectConstants.PACKAGENAME]), 0)

Mobile.delay(15, FailureHandling.STOP_ON_FAILURE)

'validate map screen appearance'
Mobile.verifyElementExist(findTestObject('CommonScreenElements/Validate_MapScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'tap on check in button through map'
Mobile.tap(findTestObject('CommonScreenElements/Location_CheckIn', [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate confirm popup appearance'
Mobile.verifyElementExist(findTestObject('CommonScreenElements/Validate_InfoPopUP', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'tap on confirm popup no button'
Mobile.tap(findTestObject('CommonScreenElements/InfoPopUp_YesButton', [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate channel summary detail screen appearance'
Mobile.verifyElementText(findTestObject('Attendence_Tposm/Validate_RouteInfoScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Route Info')

'tap on next button to further proceed'
Mobile.tap(findTestObject('Attendence_Tposm/RouteInfoNextButton', [('package') : ProjectConstants.PACKAGENAME]), 0)

Mobile.verifyElementText(findTestObject('Attendence_Tposm/Validate_TposmCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'TPSOM CATEGORY')

not_run: CustomKeywords.'com.ct.qa.keywords.Attendence_TposmKeywords.validateBrands'()

CustomKeywords.'com.ct.qa.keywords.Attendence_TposmKeywords.visitTposmCategories'()

Mobile.tap(findTestObject('Attendence_Tposm/TposmCategory_ProceedButton', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'validate dashboard screen appearance'
Mobile.verifyElementText(findTestObject('DashboardScreenElements/Validate_DashboardScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Dashboard')

CustomKeywords.'com.ct.qa.keywords.DisplayReportKeywords.displayDataInReport'()

