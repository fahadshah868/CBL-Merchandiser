package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.PrimaryShelf
import com.ct.qa.struct.QuestionData
import com.ct.qa.struct.TposmBrand
import com.ct.qa.struct.TposmDeployment
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.stringtemplate.v4.compiler.CodeGenerator.region_return

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class LoadDataKeywords {

	//load tposm types sheet
	def static loadTposmTypesSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.TPOSMTYPES_SHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load brands sheet
	def static loadBrandsProductsSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.BRANDSPRODUCTS_SHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load main categories sheet
	def static loadMainCategoriesSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.MAINCATEGORIES_SHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load Slider Options sheet
	def static loadSliderOptionsSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.SLIDEROPTIONSSHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load Shop actions sheet
	def static loadShopActionsSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.SHOPACTIONSSHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load Survey Questions sheet
	def static loadSurveyQuestionsSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.SURVEYQUESTIONSSHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load primary shelf sheet
	def static loadPrimaryShelfSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.PRIMARYSHELFSHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load tposm deployment sheet
	def static loadTposmDeploymentSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.TPOSMDEPLOYMENT_SHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load hot zone sheet
	def static loadHotZoneSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.HOTZONESHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load secondary display sheet
	def static loadSecondaryDisplaySheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.SECONDARYDISPLAYSHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load hotzone questions list
	def static loadHotZoneQuestionsList(){
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = loadHotZoneSheet()
		ArrayList<QuestionData> questions = new ArrayList<QuestionData>()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<= totalrows; i++){
			QuestionData question = new QuestionData()
			Row row = sheet.getRow(i)
			question.setQuestion(dataformatter.formatCellValue(row.getCell(ProjectConstants.HZ_QUESTION)))
			question.setQuestionoption(dataformatter.formatCellValue(row.getCell(ProjectConstants.HZ_OPTION)))
			question.setQuestionoption_takepicture(dataformatter.formatCellValue(row.getCell(ProjectConstants.HZ_TAKEPICTURE)))
			questions.add(question)
		}
		return questions
	}
	//load secondary display questions list
	def static loadSecondaryDisplayQuestionsList(){
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = loadSecondaryDisplaySheet()
		ArrayList<QuestionData> questions = new ArrayList<QuestionData>()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<= totalrows; i++){
			QuestionData question = new QuestionData()
			Row row = sheet.getRow(i)
			question.setQuestion(dataformatter.formatCellValue(row.getCell(ProjectConstants.SD_QUESTION)))
			question.setQuestionoption(dataformatter.formatCellValue(row.getCell(ProjectConstants.SD_OPTION)))
			question.setQuestionoption_takepicture(dataformatter.formatCellValue(row.getCell(ProjectConstants.SD_TAKEPICTURE)))
			questions.add(question)
		}
		return questions
	}
	//load visited brands sheet
	def static loadTposmDeploymentList(String column, String brand){
		DataFormatter dataformatter = new DataFormatter()
		TposmBrand tposmbrand = new TposmBrand()
		ArrayList<TposmDeployment> tposmdeployments = new ArrayList<TposmDeployment>()
		ArrayList<String> brands = new ArrayList<String>()
		XSSFSheet sheet = loadTposmDeploymentSheet()
		int totalrows = sheet.getLastRowNum()
		if(column.equalsIgnoreCase("brand")){
			for(int i=1; i<= totalrows; i++){
				TposmDeployment tposmdeployment = new TposmDeployment()
				Row row = sheet.getRow(i)
				brands.add(dataformatter.formatCellValue(row.getCell(ProjectConstants.TD_BRAND)))
			}
			return brands
		}
		else{
			for(int i=1; i<= totalrows; i++){
				TposmDeployment tposmdeployment = new TposmDeployment()
				Row row = sheet.getRow(i)
				String ex_brand = dataformatter.formatCellValue(row.getCell(ProjectConstants.TD_BRAND))
				if(ex_brand.equalsIgnoreCase(brand)){
					tposmdeployment.setTposmtype(dataformatter.formatCellValue(row.getCell(ProjectConstants.TD_TPOSMTYPE)))
					tposmdeployment.setTposmvalue(dataformatter.formatCellValue(row.getCell(ProjectConstants.TD_TPOSMVALUE)))
					tposmdeployment.setTposmremark(dataformatter.formatCellValue(row.getCell(ProjectConstants.TD_TPOSMREMARK)))
					tposmdeployment.setTposmremarkvalue(dataformatter.formatCellValue(row.getCell(ProjectConstants.TD_TPOSMREMARKVALUE)))
					tposmdeployment.setOverwrite_tposmremark(dataformatter.formatCellValue(row.getCell(ProjectConstants.TD_OVERWRITE_TPOSMREMARK)))
					tposmdeployment.setOverwrite_tposmremarkvalue(dataformatter.formatCellValue(row.getCell(ProjectConstants.TD_OVERWRITE_TPOSMREMARKVALUE)))
					tposmdeployments.add(tposmdeployment)
				}
			}
			tposmbrand.setTposmdeployments(tposmdeployments)
			return tposmbrand
		}

	}
	//load primary shelf sheet
	def static loadPrimaryShelf_Dimensions(int index){
		PrimaryShelf primaryshelf = new PrimaryShelf()
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<String> expectedquestioncategorylist = new ArrayList<String>()
		XSSFSheet sheet = loadPrimaryShelfSheet()
		Row row = sheet.getRow(index)
		float cbl_width = Float.parseFloat(dataformatter.formatCellValue(row.getCell(ProjectConstants.CBL_WIDTH)))
		float cbl_height = Float.parseFloat(dataformatter.formatCellValue(row.getCell(ProjectConstants.CBL_HEIGHT)))
		float competition_width = Float.parseFloat(dataformatter.formatCellValue(row.getCell(ProjectConstants.COMPETITION_WIDTH)))
		float competition_height = Float.parseFloat(dataformatter.formatCellValue(row.getCell(ProjectConstants.COMPETITION_HEIGHT)))
		float overwrite_cbl_width = Float.parseFloat(dataformatter.formatCellValue(row.getCell(ProjectConstants.OVERWRITE_CBL_WIDTH)))
		float overwrite_cbl_height = Float.parseFloat(dataformatter.formatCellValue(row.getCell(ProjectConstants.OVERWRITE_CBL_HEIGHT)))
		float overwrite_competition_width = Float.parseFloat(dataformatter.formatCellValue(row.getCell(ProjectConstants.OVERWRITE_COMPETITION_WIDTH)))
		float overwrite_competition_height = Float.parseFloat(dataformatter.formatCellValue(row.getCell(ProjectConstants.OVERWRITE_COMPETITION_HEIGHT)))
		if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
			primaryshelf.setCblwidth(cbl_width)
			primaryshelf.setCblheight(cbl_height)
			primaryshelf.setCmpwidth(competition_width)
			primaryshelf.setCmpheight(competition_height)
		}
		else{
			primaryshelf.setOverwrite_cblwidth(overwrite_cbl_width)
			primaryshelf.setOverwrite_cblheight(overwrite_cbl_height)
			primaryshelf.setOverwrite_cmpwidth(overwrite_competition_width)
			primaryshelf.setOverwrite_cmpheight(overwrite_competition_height)
		}
		return primaryshelf
	}
	//load survey question category list
	def static loadSurveyQuestionCategoryList(){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<String> expectedquestioncategorylist = new ArrayList<String>()
		XSSFSheet sheet = loadSurveyQuestionsSheet()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<= totalrows; i++){
			Row row = sheet.getRow(i)
			String questioncategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.SURVEY_QUESTIONCATEGORY))
			expectedquestioncategorylist.add(questioncategory)
		}
		return expectedquestioncategorylist
	}
	//load survey question list
	def static loadSurveyQuestionsList(){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<QuestionData> expectedquestionslist = new ArrayList<QuestionData>()
		XSSFSheet sheet = loadSurveyQuestionsSheet()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<= totalrows; i++){
			Row row = sheet.getRow(i)
			String questioncategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.SURVEY_QUESTIONCATEGORY))
			if(questioncategory.equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SUBCATEGORY)){
				QuestionData question = new QuestionData()
				question.setQuestion(dataformatter.formatCellValue(row.getCell(ProjectConstants.SURVEY_QUESTION)))
				question.setQuestionoption(dataformatter.formatCellValue(row.getCell(ProjectConstants.SURVEY_QUESTIONOPTION)))
				question.setQuestionoption_takepicture(dataformatter.formatCellValue(row.getCell(ProjectConstants.SURVEY_QUESTIONOPTION_TAKEPICTURE)))
				expectedquestionslist.add(question)
			}
		}
		return expectedquestionslist
	}
	//load shop actions
	def static loadShopActionsList(){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<String> expectedshopactionslist = new ArrayList<String>()
		XSSFSheet sheet = loadShopActionsSheet()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<= totalrows; i++){
			Row row = sheet.getRow(i)
			String shopaction = dataformatter.formatCellValue(row.getCell(ProjectConstants.SHOPACTION))
			expectedshopactionslist.add(shopaction)
		}
		return expectedshopactionslist
	}
	//load slider options/items
	def static loadSliderOptions(){
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = loadSliderOptionsSheet()
		int totalrows = sheet.getLastRowNum()
		ArrayList<String> slideroptions = new ArrayList<String>()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			slideroptions.add(dataformatter.formatCellValue(row.getCell(ProjectConstants.SLIDEROPTION)))
		}
		return slideroptions
	}
	//load tposm types
	def static loadTposmTypes(){
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = loadTposmTypesSheet()
		int totalrows = sheet.getLastRowNum()
		ArrayList<String> tposmtypes = new ArrayList<String>()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			tposmtypes.add(dataformatter.formatCellValue(row.getCell(ProjectConstants.TPOSMTYPE)))
		}
		return tposmtypes
	}
	//load brands
	def static loadBrands(){
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = loadBrandsProductsSheet()
		int totalrows = sheet.getLastRowNum()
		ArrayList<String> brands = new ArrayList<String>()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			brands.add(dataformatter.formatCellValue(row.getCell(ProjectConstants.BRAND)))
		}
		ArrayList<String> brandlist = new HashSet<String>(brands)
		return brandlist
	}
	//load brands products
	def static loadBrandProducts(String _brand){
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = loadBrandsProductsSheet()
		int totalrows = sheet.getLastRowNum()
		ArrayList<String> products = new ArrayList<String>()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String brand = dataformatter.formatCellValue(row.getCell(ProjectConstants.BRAND))
			if(brand.equalsIgnoreCase(_brand)){
				products.add(dataformatter.formatCellValue(row.getCell(ProjectConstants.PRODUCT)))
			}
		}
		return products
	}
	//load main categories list
	def static loadMainCategories(){
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = loadMainCategoriesSheet()
		int totalrows = sheet.getLastRowNum()
		ArrayList<String> maincategories = new ArrayList<String>()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			maincategories.add(dataformatter.formatCellValue(row.getCell(ProjectConstants.MAINCATEGORY)))
		}
		return maincategories
	}
}
