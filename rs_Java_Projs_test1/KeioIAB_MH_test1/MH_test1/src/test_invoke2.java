
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
		}
		
		mh.closeProject();
		
		
	}

}
