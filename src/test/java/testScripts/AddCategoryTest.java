package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericUtilities.BaseClass;
import genericUtilities.IConstantPath;
import pomPages.HomePage;

public class AddCategoryTest extends BaseClass {
	@Test
	public void addCategoryTest() {
		SoftAssert soft = new SoftAssert();
		
		home.clickCoursesTab();
		home.clickCategoryLink();
		soft.assertEquals(category.getPageHeader(),"Category");
		
		courseList.clickNewButton();
		soft.assertEquals(addCategory.getPageHeader(), "Add New Category");
		Map<String, String> map = excel.readFromExcel("Add Category");
		addCategory.setName(map.get("Name"));
		addCategory.clickSave();
		
		soft.assertEquals(category.getsuccessMessage(), "Success!");
		category.deleteCategory(web, map.get("Name"));
		soft.assertEquals(category.getsuccessMessage(), "Success!");
		if(category.getsuccessMessage().equals("Success!"))
			excel.updateTestStatus("Add Category","Pass", IConstantPath.EXCEL_PATH);
		else
			excel.updateTestStatus("Add Category","Fail" , IConstantPath.EXCEL_PATH);
		soft.assertAll();
		
	}
	

}
