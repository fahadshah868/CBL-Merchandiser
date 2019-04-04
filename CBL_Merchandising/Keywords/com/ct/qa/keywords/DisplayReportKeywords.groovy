package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.CategoryWithProducts
import com.ct.qa.struct.MissingCategoryData
import com.ct.qa.struct.MissingShopDataInfo
import com.ct.qa.struct.MissingTposmCategory
import com.ct.qa.struct.PrimaryShelf
import com.ct.qa.struct.ProductData
import com.ct.qa.struct.QuestionData
import com.ct.qa.struct.TposmBrand
import com.ct.qa.struct.TposmDeployment
import com.ct.qa.struct.VisitedCategoryData
import com.ct.qa.struct.VisitedShopDataInfo
import com.googlecode.javacv.FrameGrabber.Array
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class DisplayReportKeywords {

	@Keyword
	def static displayDataInReport(){
		String message = "\n\n---------------------------------------------Missing Shop Data-----------------------------------------------------------------------------------------------------\n\n"+
				"\n\n<------------------------------------------------------------------------------------------------------------------------------------------------------->\n\n"
		for(int i=0; i<ProjectConstants.missingshopdatainfo.size(); i++){
			boolean flag = false
			MissingShopDataInfo missingshopdatainfo = ProjectConstants.missingshopdatainfo.get(i)
			if(missingshopdatainfo != null){
				if(missingshopdatainfo.getMissingshopcategories() != null){
					if(flag == false){
						message = message+"\n\n"+
								String.format("%-11s%-60s%-60s","Shop Name:",missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n\n"+
								String.format("%-30s%-100s", "Visiting Scenarios:",missingshopdatainfo.getScenario())+
								"\n\n" + String.format("%-30s", "Shop Categories:")
						for(int j=0; j<missingshopdatainfo.getMissingshopcategories().size(); j++){
							message = message+missingshopdatainfo.getMissingshopcategories().get(j)+",   "
						}
						message = message+"\n"+missingshopdatainfo.getMissingshopcategories_errormessage()
						flag = true
					}
					else{
					}
					message = message + "\n\n" + String.format("%-30s%-60s%-30s","","<---------------------------------------------------------------------->","")+"\n\n"
				}
				if(missingshopdatainfo.getMissingshopactions() != null){
					if(flag == false){
						message = message+"\n\n"+
								String.format("%-11s%-60s%-60s","Shop Name:",missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n\n"+
								String.format("%-30s%-100s", "Visiting Scenarios:",missingshopdatainfo.getScenario())+
								"\n\n" + String.format("%-30s", "Shop Actions:")
						for(int j=0; j<missingshopdatainfo.getMissingshopactions().size(); j++){
							message = message+missingshopdatainfo.getMissingshopactions().get(j)+",   "
						}
						message = message+"\n"+missingshopdatainfo.getMissingshopactions_errormessage()
						flag = true
					}
					else{
						message = message+"\n\n"+
								String.format("%-30s", "Shop Actions:")
						for(int j=0; j<missingshopdatainfo.getMissingshopactions().size(); j++){
							message = message+missingshopdatainfo.getMissingshopactions().get(j)+",   "
						}
						message = message+"\n"+missingshopdatainfo.getMissingshopactions_errormessage()
					}
					message = message + "\n\n" + String.format("%-30s%-60s%-30s","","<---------------------------------------------------------------------->","")+"\n\n"
				}
				ArrayList<MissingCategoryData> missingcategoriesdata = missingshopdatainfo.getMissingCategoriesData()
				if(missingcategoriesdata != null){
					for(int k=0; k< missingcategoriesdata.size(); k++){
						MissingCategoryData missingcategorydata = missingcategoriesdata.get(k)
						if(missingcategorydata.getMaincategory().equalsIgnoreCase("Brand Availability")){
							if(missingcategorydata.getMissingtposmcategory() != null){
								if(flag == false){
									message = message+"\n\n"+
											String.format("%-11s%-60s%-60s","Shop Name:",missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n\n"+
											String.format("%-30s%-100s", "Visiting Scenarios:",missingshopdatainfo.getScenario())+"\n\n"+
											String.format("%-30s%-60s", "Main Category:", missingcategorydata.getMaincategory())+"\n"+
											String.format("%-30s", "Brands:")
									for(int j=0; j< missingcategorydata.getMissingtposmcategory().getBrands().size(); j++){
										message = message + missingcategorydata.getMissingtposmcategory().getBrands().get(j)+",   "
									}
									message = message+"\n"+missingcategorydata.getMissingtposmcategory().getErrormessage_brands()
									flag = true
								}
								else{
									message = message+"\n\n"+
											String.format("%-30s%-60s", "Main Category:", missingcategorydata.getMaincategory())+"\n"+
											String.format("%-30s", "Brands:")
									for(int j=0; j< missingcategorydata.getMissingtposmcategory().getBrands().size(); j++){
										message = message + missingcategorydata.getMissingtposmcategory().getBrands().get(j)+",   "
									}
									message = message+"\n"+missingcategorydata.getMissingtposmcategory().getErrormessage_brands()
								}
							}
							if(missingcategorydata.getMissingitems() != null){
								if(flag == false){
									message = message+"\n\n"+
											String.format("%-11s%-60s%-60s","Shop Name:",missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n\n"+
											String.format("%-30s%-100s", "Visiting Scenarios:",missingshopdatainfo.getScenario())+"\n\n"+
											String.format("%-30s%-60s", "Main Category:", missingcategorydata.getMaincategory())+"\n"+
											String.format("%-30s%-60s", "Brand:", missingcategorydata.getSubcategory())+"\n"+
											String.format("%-30s", "Products:")
									for(int j=0; j< missingcategorydata.getMissingitems().size(); j++){
										message = message + missingcategorydata.getMissingitems().get(j)+",   "
									}
									message = message+"\n"+missingcategorydata.getMissingitems_errormessage()
									flag = true
								}
								else{
									message = message+"\n\n"+
											String.format("%-30s%-60s", "Main Category:", missingcategorydata.getMaincategory())+"\n"+
											String.format("%-30s%-60s", "Brand:", missingcategorydata.getSubcategory())+"\n"+
											String.format("%-30s", "Products:")
									for(int j=0; j< missingcategorydata.getMissingitems().size(); j++){
										message = message + missingcategorydata.getMissingitems().get(j)+",   "
									}
									message = message+"\n"+missingcategorydata.getMissingitems_errormessage()
								}
							}
							message = message + "\n\n" + String.format("%-30s%-60s%-30s","","<---------------------------------------------------------------------->","")+"\n\n"
						}
						else if(missingcategorydata.getMaincategory().equalsIgnoreCase("TPOSM Deployment")){
							if(missingcategorydata.getMissingsubcategories() != null){
								if(flag == false){
									message = message+"\n\n"+
											String.format("%-11s%-60s%-60s","Shop Name:",missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n\n"+
											String.format("%-30s%-100s", "Visiting Scenarios:",missingshopdatainfo.getScenario())+"\n\n"+
											String.format("%-30s%-60s", "Main Category:", missingcategorydata.getMaincategory())+"\n"+
											String.format("%-30s", "Brands:")
									for(int j=0; j< missingcategorydata.getMissingsubcategories().size(); j++){
										message = message + missingcategorydata.getMissingsubcategories().get(j)+",   "
									}
									message = message+"\n"+missingcategorydata.getMissingsubcategories_errormessage()
									flag = true
								}
								else{
									message = message+"\n\n"+
											String.format("%-30s%-60s", "Main Category:", missingcategorydata.getMaincategory())+"\n"+
											String.format("%-30s", "Brands:")
									for(int j=0; j< missingcategorydata.getMissingsubcategories().size(); j++){
										message = message + missingcategorydata.getMissingsubcategories().get(j)+",   "
									}
									message = message+"\n"+missingcategorydata.getMissingsubcategories_errormessage()
								}
							}
							if(missingcategorydata.getMissingitems() != null){
								if(flag == false){
									message = message+"\n\n"+
											String.format("%-11s%-60s%-60s","Shop Name:",missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n\n"+
											String.format("%-30s%-100s", "Visiting Scenarios:",missingshopdatainfo.getScenario())+"\n\n"+
											String.format("%-30s%-60s", "Main Category:", missingcategorydata.getMaincategory())+"\n"+
											String.format("%-30s%-60s", "Brand:", missingcategorydata.getSubcategory())+"\n"+
											String.format("%-30s", "Tposm Types:")
									for(int j=0; j< missingcategorydata.getMissingitems().size(); j++){
										message = message + missingcategorydata.getMissingitems().get(j)+",   "
									}
									message = message+"\n"+missingcategorydata.getMissingitems_errormessage()
									flag = true
								}
								else{
									message = message+"\n\n"+
											String.format("%-30s%-60s", "Main Category:", missingcategorydata.getMaincategory())+"\n"+
											String.format("%-30s%-60s", "Brand:", missingcategorydata.getSubcategory())+"\n"+
											String.format("%-30s", "Products:")
									for(int j=0; j< missingcategorydata.getMissingitems().size(); j++){
										message = message + missingcategorydata.getMissingitems().get(j)+",   "
									}
									message = message+"\n"+missingcategorydata.getMissingitems_errormessage()
								}
							}
							message = message + "\n\n" + String.format("%-30s%-60s%-30s","","<---------------------------------------------------------------------->","")+"\n\n"
						}
						else if(missingcategorydata.getMaincategory().equalsIgnoreCase("Survey") || missingcategorydata.getMaincategory().equalsIgnoreCase("Hot Zone") || missingcategorydata.getMaincategory().equalsIgnoreCase("Secondary Display")){
							if(missingcategorydata.getMissingsubcategories() != null){
								if(flag == false){
									message = message+"\n\n"+
											String.format("%-11s%-60s%-60s","Shop Name:",missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n\n"+
											String.format("%-30s%-100s", "Visiting Scenarios:",missingshopdatainfo.getScenario())+"\n\n"+
											String.format("%-30s%-60s", "Main Category:", missingcategorydata.getMaincategory())+"\n"+
											String.format("%-30s", "Question Categories:")
									for(int j=0; j< missingcategorydata.getMissingsubcategories().size(); j++){
										message = message + missingcategorydata.getMissingsubcategories().get(j)+",   "
									}
									message = message+"\n"+missingcategorydata.getMissingsubcategories_errormessage()
									flag = true
								}
								else{
									message = message+"\n\n"+
											String.format("%-30s%-60s", "Main Category:", missingcategorydata.getMaincategory())+"\n"+
											String.format("%-30s", "Question Categories:")
									for(int j=0; j< missingcategorydata.getMissingsubcategories().size(); j++){
										message = message + missingcategorydata.getMissingsubcategories().get(j)+",   "
									}
									message = message+"\n"+missingcategorydata.getMissingsubcategories_errormessage()
								}
							}
							if(missingcategorydata.getMissingitems() != null){
								if(flag == false){
									message = message+"\n\n"+
											String.format("%-11s%-60s%-60s","Shop Name:",missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n\n"+
											String.format("%-30s%-100s", "Visiting Scenarios:",missingshopdatainfo.getScenario())+"\n\n"+
											String.format("%-30s%-60s", "Main Category:", missingcategorydata.getMaincategory())+"\n"+
											String.format("%-30s%-60s", "Question Category:", missingcategorydata.getSubcategory())+"\n"+
											String.format("%-30s", "Questions:")
									for(int j=0; j< missingcategorydata.getMissingitems().size(); j++){
										message = message + missingcategorydata.getMissingitems().get(j)+",   "
									}
									message = message+"\n"+missingcategorydata.getMissingitems_errormessage()
									flag = true
								}
								else{
									message = message+"\n\n"+
											String.format("%-30s%-60s", "Main Category:", missingcategorydata.getMaincategory())+"\n"+
											String.format("%-30s%-60s", "Question Category:", missingcategorydata.getSubcategory())+"\n"+
											String.format("%-30s", "Questions:")
									for(int j=0; j< missingcategorydata.getMissingitems().size(); j++){
										message = message + missingcategorydata.getMissingitems().get(j)+",   "
									}
									message = message+"\n"+missingcategorydata.getMissingitems_errormessage()
								}
							}
							message = message + "\n\n" + String.format("%-30s%-60s%-30s","","<---------------------------------------------------------------------->","")+"\n\n"
						}
					}
				}
				if(flag != false){
					message = message+"\n\n<------------------------------------------------------------------------------------------------------------------------------------------------------->\n\n"
					KeywordUtil.logInfo(message)
					message = ""
				}
				else{
				}
			}
		}
		message = "\n\n\n---------------------------------------------Visited Shop Data-----------------------------------------------------------------------------------------------------\n\n"+
				"<-------------------------------------------------------------------------------------------------------------------------------------->"
		for(int i=0; i<ProjectConstants.visitedshopdatainfo.size(); i++){
			VisitedShopDataInfo visitedshopdatainfo = ProjectConstants.visitedshopdatainfo.get(i)
			if(visitedshopdatainfo != null){
				message = message+"\n\n"+
						String.format("%-11s%-60s%-60s","Shop Name:",visitedshopdatainfo.getShopname(),visitedshopdatainfo.getShopchannel())+"\n\n"+
						String.format("%-40s%-100s","Shop Visiting Scenarios:",visitedshopdatainfo.getShop_scenario())
				if(visitedshopdatainfo.getVisitedcategoriesdata() != null){
					for(int j=0; j< visitedshopdatainfo.getVisitedcategoriesdata().size(); j++){
						VisitedCategoryData visitedcategorydata = visitedshopdatainfo.getVisitedcategoriesdata().get(j)
						if(visitedcategorydata.getMaincategory().equalsIgnoreCase("Brand Availability")){
							message = message+ "\n\n" +
									String.format("%-30s%-130s", "Main Category:",visitedcategorydata.getMaincategory())
							ArrayList<CategoryWithProducts> categorywithproducts = visitedcategorydata.getCategorywithproducts()
							for(int k=0; k< categorywithproducts.size(); k++){
								CategoryWithProducts categorywithproduct = categorywithproducts.get(k)
								message = message+ "\n\n" +
										String.format("%-30s%-130s", "Brand:",categorywithproduct.getCategory())+"\n"+
										String.format("%-50s%-30s%-30s", "Product:","Remark","Overwrite Remark")+"\n"
								ArrayList<ProductData> productsdata = categorywithproduct.getProductsdata()
								for(int m=0; m< productsdata.size(); m++){
									ProductData productdata = productsdata.get(m)
									message = message +
											String.format("%-50s%-30s%-30s", productdata.getProduct(),productdata.getRemark(),productdata.getOverwrite_remark()) + "\n"
								}
							}
						}
						else if(visitedcategorydata.getMaincategory().equalsIgnoreCase("Survey") || visitedcategorydata.getMaincategory().equalsIgnoreCase("Hot Zone") || visitedcategorydata.getMaincategory().equalsIgnoreCase("Secondary Display")){
							message = message+ "\n\n" +
									String.format("%-30s%-130s", "Main Category:",visitedcategorydata.getMaincategory())
							ArrayList<CategoryWithProducts> categorywithproducts = visitedcategorydata.getCategorywithproducts()
							for(int k=0; k< categorywithproducts.size(); k++){
								CategoryWithProducts categorywithproduct = categorywithproducts.get(k)
								message = message+ "\n\n" +
										String.format("%-30s%-130s", "Question Category:",categorywithproduct.getCategory())+"\n"+
										String.format("%-50s%-25s%-25s%-25s%-25s", "Question:","Selected Option","Picture Remark","Overwrite","Overwrite")+"\n"+
										String.format("%-50s%-25s%-25s%-25s%-25s", "","","","Selected Option","Picture Remark")+"\n"
								ArrayList<QuestionData> questionsdata = categorywithproduct.getQuestions()
								for(int m=0; m< questionsdata.size(); m++){
									QuestionData questiondata = questionsdata.get(m)
									message = message +
											String.format("%-50s%-25s%-25s%-25s%-25s", questiondata.getQuestion(),questiondata.getQuestionoption(),questiondata.getQuestionoption_takepicture(),questiondata.getOverwrite_questionoption(),questiondata.getOverwrite_questionoption_takepicture()) + "\n"
								}
							}
						}
						else if(visitedcategorydata.getMaincategory().equalsIgnoreCase("Primary Shelf")){
							message = message+ "\n\n" +
									String.format("%-30s%-130s", "Main Category:",visitedcategorydata.getMaincategory())+"\n\n"+
									String.format("%-35s%-35s%-35s%-35s%-35s", "Shelf:","CBL W/H","CMP W/H","Overwrite CBL W/H","Overwrite CMP W/H")
							ArrayList<PrimaryShelf> primaryshelfs = visitedcategorydata.getPrimaryshelf()
							float cbl_width, cbl_height, cmp_width, cmp_height, overwrite_cbl_width, overwrite_cbl_height, overwrite_cmp_width, overwrite_cmp_height
							for(int k=0; k< primaryshelfs.size(); k++){
								PrimaryShelf primaryshelf = primaryshelfs.get(k)
								cbl_width = cbl_width + primaryshelf.getCblwidth()
								cbl_height = cbl_height + primaryshelf.getCblheight()
								cmp_width = cmp_width + primaryshelf.getCmpwidth()
								cmp_height = cmp_height + primaryshelf.getCmpheight()
								overwrite_cbl_width = overwrite_cbl_width + primaryshelf.getOverwrite_cblwidth()
								overwrite_cbl_height = overwrite_cbl_height + primaryshelf.getOverwrite_cblheight()
								overwrite_cmp_width = overwrite_cmp_width + primaryshelf.getOverwrite_cmpwidth()
								overwrite_cmp_height = overwrite_cmp_height + primaryshelf.getOverwrite_cmpheight()
								message = message+ "\n" +
										String.format("%-35s%-35s%-35s%-35s%-35s", primaryshelf.getShelf(), primaryshelf.getCblwidth()+"/"+primaryshelf.getCblheight(),primaryshelf.getCmpwidth()+"/"+primaryshelf.getCmpheight(),primaryshelf.getOverwrite_cblwidth()+"/"+primaryshelf.getOverwrite_cblheight(),primaryshelf.getOverwrite_cmpwidth()+"/"+primaryshelf.getOverwrite_cmpheight())
							}
							float sos = ((cbl_width * cbl_height) / ((cbl_width * cbl_height) + (cmp_width * cmp_height))) * 100
							float overwrite_sos = ((overwrite_cbl_width * overwrite_cbl_height) / ((overwrite_cbl_width * overwrite_cbl_height) + (overwrite_cmp_width * overwrite_cmp_height))) * 100
							message = message + "\n\n" +
									String.format("%-35s%-25s","Share Of Shelf:",sos) + "\n" +
									String.format("%-35s%-25s","Overwrite Share Of Shelf:",overwrite_sos)
						}
						else if(visitedcategorydata.getMaincategory().equalsIgnoreCase("TPOSM Deployment")){
							message = message+ "\n\n" +
									String.format("%-30s%-130s", "Main Category:",visitedcategorydata.getMaincategory())
							ArrayList<TposmBrand> tposmbrands = visitedcategorydata.getTposmbrands()
							for(int m=0; m< tposmbrands.size(); m++){
								TposmBrand tposmbrand = tposmbrands.get(m)
								message = message +"\n\n"+ String.format("%-30s%-130s","Brand",tposmbrand.getBrand())+"\n\n"+
										String.format("%-30s%-30s%-30s%-30s%-30s","Tposm Type","Tposm Remark","Remark Value","Overwrite Tposm Remark","Overwrite Remark Value")+"\n"
								ArrayList<TposmDeployment> tposmdeployments = tposmbrand.getTposmdeployments()
								for(int n=0; n< tposmdeployments.size(); n++){
									TposmDeployment tposmdeployment = tposmdeployments.get(n)
									message = message +
											String.format("%-30s%-30s%-30s%-30s%-30s",tposmdeployment.getTposmtype(),tposmdeployment.getTposmremark(),tposmdeployment.getTposmremarkvalue(),tposmdeployment.getOverwrite_tposmremark(),tposmdeployment.getOverwrite_tposmremarkvalue())+"\n"
								}
							}
						}
					}
				}
				message = message + "\n\n<-------------------------------------------------------------------------------------------------------------------------------------->\n\n"
				KeywordUtil.logInfo(message)
				message = ""
			}
		}
	}
}
