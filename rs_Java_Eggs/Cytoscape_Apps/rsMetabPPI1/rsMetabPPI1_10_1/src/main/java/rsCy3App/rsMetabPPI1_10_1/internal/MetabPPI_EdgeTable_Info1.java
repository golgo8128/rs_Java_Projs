package rsCy3App.rsMetabPPI1_10_1.internal;


public class MetabPPI_EdgeTable_Info1 {

	public static final String[] clsnam_edgeattr
		= { "String", "String", "String",      "Integer" };
	public static final String[] colnam_edgeattr
		= { "Node 1", "Node 2", "interaction", "weight"  };
	public static final Object[][] values_edgeattr = {
    		{ "Metab1", "Enz1", "Metabolism", 3 },
    		{ "Metab1", "Enz2", "Metabolism (Curated)", 2 },    		
    		{ "Prot1",  "Enz2", "PPI", 1 }
    };	
	
}