package com.ct.qa.constants

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.keywords.LoadDataKeywords
import com.ct.qa.struct.MissingShopDataInfo
import com.ct.qa.struct.MissingSliderOptions
import com.ct.qa.struct.MissingTposmCategory
import com.ct.qa.struct.VisitedShopDataInfo
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import static java.util.Arrays.asList;

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.Point
import org.openqa.selenium.remote.server.handler.AcceptAlert

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class ProjectConstants {

	//variables for excel file and sheets
	public static final String EXCEL_FILEPATH = "F:\\Git Projects\\CBL-Merchandiser\\CBL_Merchandising\\CBL_Merchandising.xlsx"
	public static final String TPOSMTYPES_SHEET = "Tposm Types"
	public static final String TPOSMDEPLOYMENT_SHEET = "Tposm Deployment"
	public static final String BRANDSPRODUCTS_SHEET = "Brands Products"
	public static final String MAINCATEGORIES_SHEET = "Main Categories"
	public static final String SLIDEROPTIONSSHEET = "Slider Options"
	public static final String SHOPACTIONSSHEET = "Shop Actions"
	public static final String SURVEYQUESTIONSSHEET = "Survey Questions"
	public static final String HOTZONESHEET = "Hot Zone"
	public static final String SECONDARYDISPLAYSHEET = "Secondary Display"
	public static final String PRIMARYSHELFSHEET = "Primary Shelf"

	public static final AppiumDriver<MobileElement> DRIVER = MobileDriverFactory.getDriver()

	//variables for display messages
	//products comparison messages
	public static final String MESSAGEFOR_ITEMSARE_MORE = "above items are displaying on device more than to expected items..."
	public static final String MESSAGEFOR_ITEMSARE_MISSING = "above items are missing on device..."
	public static final String MESSAGEFOR_ITEMSARE_NOTMATCH = "above items are display on device not match with expected items..."

	//package name for elements
	public static final String PACKAGENAME = "com.concavetech.cbl"

	//variables for excel sheet columns index
	//tposm types columns
	public static final int TPOSMTYPE
	//tposm deployment columns
	public static final int TD_BRAND
	public static final int TD_TPOSMTYPE
	public static final int TD_TPOSMVALUE
	public static final int TD_TPOSMREMARK
	public static final int TD_TPOSMREMARKVALUE
	public static final int TD_OVERWRITE_TPOSMREMARK
	public static final int TD_OVERWRITE_TPOSMREMARKVALUE
	//brands products columns
	public static final int BRAND
	public static final int PRODUCT
	//main categories columns
	public static final int MAINCATEGORY
	//shop actions columns
	public static final int SHOPACTION
	//slider options columns
	public static final int SLIDEROPTION
	//audit questions columns
	public static final int SURVEY_QUESTIONCATEGORY
	public static final int SURVEY_QUESTION
	public static final int SURVEY_QUESTIONOPTION
	public static final int SURVEY_QUESTIONOPTION_TAKEPICTURE
	//hot zone columns
	public static final int HZ_QUESTION
	public static final int HZ_OPTION
	public static final int HZ_TAKEPICTURE
	//secondary display columns
	public static final int SD_QUESTION
	public static final int SD_OPTION
	public static final int SD_TAKEPICTURE
	//primary shelf columns
	public static final int SHELF
	public static final int CBL_WIDTH
	public static final int CBL_HEIGHT
	public static final int COMPETITION_WIDTH
	public static final int COMPETITION_HEIGHT
	public static final int OVERWRITE_CBL_WIDTH
	public static final int OVERWRITE_CBL_HEIGHT
	public static final int OVERWRITE_COMPETITION_WIDTH
	public static final int OVERWRITE_COMPETITION_HEIGHT
	public static final int SHELF_COUNT

	//variables for current visiting shop channels, chiller and categories
	public static String CURRENTVISITING_SHOPNAME = ""
	public static String CURRENTVISITING_SHOPCHANNEL = ""
	public static String CURRENTVISITING_MAINCATEGORY = ""
	public static String CURRENTVISITING_SUBCATEGORY = ""
	public static String SCENARIO = ""
	public static int SHOP_ATTEMPT = 0

	//list for containing shop info
	public static ArrayList<MissingShopDataInfo> missingshopdatainfo = new ArrayList<MissingShopDataInfo>()
	public static ArrayList<VisitedShopDataInfo> visitedshopdatainfo = new ArrayList<MissingShopDataInfo>()
	public static MissingSliderOptions missingslideroptions = new MissingSliderOptions()
	public static MissingTposmCategory missingtposmcategories = new MissingTposmCategory()

	//initialization of sheet columns index
	static{

		XSSFSheet tposmtypessheet = LoadDataKeywords.loadTposmTypesSheet()
		XSSFSheet brandssheet = LoadDataKeywords.loadBrandsProductsSheet()
		XSSFSheet slideroptionssheet = LoadDataKeywords.loadSliderOptionsSheet()
		XSSFSheet shopactionssheet = LoadDataKeywords.loadShopActionsSheet()
		XSSFSheet auditquestionssheet = LoadDataKeywords.loadSurveyQuestionsSheet()
		XSSFSheet maincategoriessheet = LoadDataKeywords.loadMainCategoriesSheet()
		XSSFSheet primaryshelfsheet = LoadDataKeywords.loadPrimaryShelfSheet()
		XSSFSheet tposmdeploymentsheet = LoadDataKeywords.loadTposmDeploymentSheet()
		XSSFSheet hotzonesheet = LoadDataKeywords.loadHotZoneSheet()
		XSSFSheet secondarydisplaysheet = LoadDataKeywords.loadSecondaryDisplaySheet()

		Row tposmtypessheetheaderrow = tposmtypessheet.getRow(0)
		Row brandssheetheaderrow = brandssheet.getRow(0)
		Row slideroptionssheetheaderrow = slideroptionssheet.getRow(0)
		Row shopactionssheetheaderrow = shopactionssheet.getRow(0)
		Row auditquestionssheetheaderrow = auditquestionssheet.getRow(0)
		Row maincategoriessheetheaderrow = maincategoriessheet.getRow(0)
		Row primaryshelfsheetheaderrow = primaryshelfsheet.getRow(0)
		Row tposmdeploymentsheetheaderrow = tposmdeploymentsheet.getRow(0)
		Row hotzonesheetheaderrow = hotzonesheet.getRow(0)
		Row secondarydisplaysheetheaderrow = secondarydisplaysheet.getRow(0)


		int tposmtypessheettotalcolumns = tposmtypessheetheaderrow.getLastCellNum()
		int brandssheettotalcolumns = brandssheetheaderrow.getLastCellNum()
		int slideroptionssheettotalcolumns = slideroptionssheetheaderrow.getLastCellNum()
		int shopactionssheettotalcolumns = shopactionssheetheaderrow.getLastCellNum()
		int auditquestionssheettotalcolumns = auditquestionssheetheaderrow.getLastCellNum()
		int maincategoriessheettotalcolumns = maincategoriessheetheaderrow.getLastCellNum()
		int primaryshelfsheettotalcolumns = primaryshelfsheetheaderrow.getLastCellNum()
		int tposmdeploymentsheettotalcolumns = tposmdeploymentsheetheaderrow.getLastCellNum()
		int hotzonesheettotalcolumns = hotzonesheetheaderrow.getLastCellNum()
		int secondarydisplaysheettotalcolumns = secondarydisplaysheetheaderrow.getLastCellNum()

		SHELF_COUNT = primaryshelfsheet.getPhysicalNumberOfRows() - 1

		//load column indexes
		for(int cellnumber=0; cellnumber<slideroptionssheettotalcolumns; cellnumber++ ){
			String columnname = slideroptionssheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Slider Option")){
				SLIDEROPTION = cellnumber
			}
			else{
			}
		}
		for(int cellnumber=0; cellnumber<shopactionssheettotalcolumns; cellnumber++ ){
			String columnname = shopactionssheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Shop Action")){
				SHOPACTION = cellnumber
			}
			else{
			}
		}
		for(int cellnumber=0; cellnumber<auditquestionssheettotalcolumns; cellnumber++ ){
			String columnname = auditquestionssheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Question Category")){
				SURVEY_QUESTIONCATEGORY = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Question")){
				SURVEY_QUESTION = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Option")){
				SURVEY_QUESTIONOPTION = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Take Picture")){
				SURVEY_QUESTIONOPTION_TAKEPICTURE = cellnumber
			}
			else{
			}
		}
		for(int cellnumber=0; cellnumber<tposmtypessheettotalcolumns; cellnumber++ ){
			String columnname = tposmtypessheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Tposm Type")){
				TPOSMTYPE = cellnumber
			}
		}
		for(int cellnumber=0; cellnumber<brandssheettotalcolumns; cellnumber++ ){
			String columnname = brandssheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Brand")){
				BRAND = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Product")){
				PRODUCT = cellnumber
			}
		}
		for(int cellnumber=0; cellnumber<maincategoriessheettotalcolumns; cellnumber++ ){
			String columnname = maincategoriessheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Main Category")){
				MAINCATEGORY = cellnumber
			}
		}
		for(int cellnumber=0; cellnumber<primaryshelfsheettotalcolumns; cellnumber++ ){
			String columnname = primaryshelfsheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Shelf")){
				SHELF = cellnumber
			}
			else if(columnname.equalsIgnoreCase("CBL Width")){
				CBL_WIDTH = cellnumber
			}
			else if(columnname.equalsIgnoreCase("CBL Height")){
				CBL_HEIGHT = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Competition Width")){
				COMPETITION_WIDTH = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Competition Height")){
				COMPETITION_HEIGHT = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite CBL Width")){
				OVERWRITE_CBL_WIDTH = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite CBL Height")){
				OVERWRITE_CBL_HEIGHT = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Competition Width")){
				OVERWRITE_COMPETITION_WIDTH = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Competition Height")){
				OVERWRITE_COMPETITION_HEIGHT = cellnumber
			}
		}
		for(int cellnumber=0; cellnumber<tposmdeploymentsheettotalcolumns; cellnumber++ ){
			String columnname = tposmdeploymentsheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Brand")){
				TD_BRAND = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Tposm Type")){
				TD_TPOSMTYPE = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Tposm Value")){
				TD_TPOSMVALUE = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Tposm Remark")){
				TD_TPOSMREMARK = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Tposm Remark Value")){
				TD_TPOSMREMARKVALUE = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Tposm Remark")){
				TD_OVERWRITE_TPOSMREMARK = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Tposm Remark Value")){
				TD_OVERWRITE_TPOSMREMARKVALUE = cellnumber
			}
		}
		for(int cellnumber=0; cellnumber<hotzonesheettotalcolumns; cellnumber++ ){
			String columnname = hotzonesheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Question")){
				HZ_QUESTION = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Option")){
				HZ_OPTION = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Take Picture")){
				HZ_TAKEPICTURE = cellnumber
			}
		}
		for(int cellnumber=0; cellnumber<secondarydisplaysheettotalcolumns; cellnumber++ ){
			String columnname = secondarydisplaysheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Question")){
				SD_QUESTION = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Option")){
				SD_OPTION = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Take Picture")){
				SD_TAKEPICTURE = cellnumber
			}
		}
	}
}
