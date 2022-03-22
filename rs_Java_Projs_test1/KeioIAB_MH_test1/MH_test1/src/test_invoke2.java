
import java.io.IOException;

import emon2.EmonException;
import emon2.api.MasterHands;
import emon2.api.AlignmentInfo;

public class test_invoke2 {

	public static void main(String[] args) throws EmonException, IOException {
		// TODO Auto-generated method stub

		System.out.println(System.getenv("RS_TRUNK_DIR"));
		// System.out.println(mh_version);

		MasterHands mh = new MasterHands();
		
		mh.loadProject("D:\\Data\\Metabolome\\Quant_linear_check_STD\\180426_TOF21_checkout\\180426_C_TOF21.mhs");
		
		
		java.util.List<AlignmentInfo> alist = mh.getAlignmentList();
		for(AlignmentInfo each_a : alist) {
			System.out.println(each_a.getName());
			mh.exportCsv(
					each_a.getId(), // resultId
		            "C:\\Users\\golgo\\Desktop\\tmpmhout1_" + each_a.getName() + ".csv", // csvPath
		            false, // printZeroValues
		            true,  // printGroup
		            true,  // printSession
		            true,  // printPeakGroupId
		            true,  // printAnnotationId
		            true,  // printAnnotationName
		            true,  // printAveMz
		            true,  // printAveCorMT
		            true,  // printN
		            true,  // printIs
		            true,  // printCategory
		            true,  // printTscore
		            true,  // printPvalue
		            true,  // printPeakId
		            true,  // printMz
		            true,  // printCorMT
		            true,  // printMT
		            true,  // printIntensity,
		            true,  // printArea
		            true,  // printRelArea
		            true,  // printSn
		            true,  // printNoise
		            true,  // printHeight
		            true,  // printLeftMT
		            true,  // printRightMT
		            true,  // printConc
		            true   // printTag
		            );
		}
		
		mh.closeProject();
		
		
	}

}
