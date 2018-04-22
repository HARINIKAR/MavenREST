package testscripts;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import utility.ReUsableMethods;
import utility.ReUsableMethods;;

public class DriverFile {

	public static void main(String[] args) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String cur_dir = System.getProperty("user.dir");
		String suitePath = cur_dir + "/src/test/java/utility/TestSuite.xls";
		String[][] recData = ReUsableMethods.readXlSheet(suitePath, "Sheet1");
		String testCase,flag,firefoxStatus;
		
		for (int i = 1; i < recData.length; i++) {
			testCase = recData[i][0];
			flag = recData[i][1];
			if(flag.equalsIgnoreCase("y")){
				firefoxStatus=recData[i][2];
				if(firefoxStatus.equalsIgnoreCase("y")){
					Method tc = AutoScripts.class.getMethod(testCase);
					tc.invoke(tc);
				}
			}
		}

	}

}
