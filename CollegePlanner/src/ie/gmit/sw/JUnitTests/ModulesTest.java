package ie.gmit.sw.JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.Modules.*;

public class ModulesTest {
	ModuleData moduleData = new ModuleData();
	ModuleGroup moduleGroup = new ModuleGroup();
	
	@Test
	public void test() {
		Module module1 = new Module("Title", "Lecturer", 2.0, 1);
		Module module2 = new Module("Title", "Lecturer", 3.0, 1);
		Module module3 = new Module("Title", "Lecturer", 4.0, 1);
		Module module4 = new Module("Title", "Lecturer", 5.0, 1);
		
		moduleGroup.addModule(module1);
		moduleGroup.addModule(module2);
		moduleGroup.addModule(module3);
		moduleGroup.addModule(module4);
		
		ModuleDetails moduleDetails = new ModuleDetails("Title", "GradeTitle", "02/04/2017", 70, 90, 63);
		ModuleDetails moduleDetails1 = new ModuleDetails("Title", "GradeTitle", "02/04/2017", 70, 30, 21);
		ModuleDetails moduleDetails2 = new ModuleDetails("Title", "GradeTitle", "02/04/2017", 70, 40, 28);
		
		moduleData.addModule(moduleDetails);
		moduleData.addModule(moduleDetails1);
		moduleData.addModule(moduleDetails2);
		
		//Check the entries in the lists
		assertEquals(4, moduleGroup.getModuleList().size());
		assertEquals(3, moduleData.getModuleGrades().size());
		assertEquals(moduleGroup.getModuleList().get(1).getAverage(), 3.0, 0.0);
		assertEquals(moduleData.getModuleGrades().get(0).getGrade(), 63.0, 0.0);
	}

}
