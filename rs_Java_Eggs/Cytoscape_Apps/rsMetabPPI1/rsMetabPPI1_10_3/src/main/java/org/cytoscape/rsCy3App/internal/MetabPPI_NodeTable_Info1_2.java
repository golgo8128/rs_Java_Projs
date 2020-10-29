package org.cytoscape.rsCy3App.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class MetabPPI_NodeTable_Info1_2 {

    public static final String[] clsnam_nodeattr
        = { "String", "String", "String", "String", "String", "String" };

    public static final String[] colnam_nodeattr
        = { "Node ID", "Node symbol", "Node type", "KEGG ID", "Annotation", "Pathways" };

    public static Object[][] assemble_ObjArrayParts(){

        ArrayList<Object[]> arrayList = new ArrayList<Object[]>();     

        Class<?> klass = MetabPPI_NodeTable_Info1_2.class;
        Method method[] = klass.getDeclaredMethods();
        
        try {
            for (int i = 0; i < method.length; i++){
                System.out.println(method[i].toString());
                if(method[i].toString().contains("getObjArrayPart")){
                    List<Object[]> list = Arrays.asList((Object[][])method[i].invoke(null));
                    arrayList.addAll(list);
                }
            }
        }
        catch (Throwable exception){
            System.err.println(exception);
        }    

        return (Object[][])arrayList.toArray(new Object[0][0]);

    }


    private static Object[][] getObjArrayPart0(){
                 
        final Object[][] objarray = {
            { "51339", "DACT1", "Protein", "hsa:51339", "dishevelled-binding antagonist of beta-catenin 1" },
            { "261726", "TIPRL", "Protein", "hsa:261726", "TIP41, TOR signaling pathway regulator-like (S. cerevisiae)" },
            { "5718", "PSMD12", "Protein", "hsa:5718", "proteasome (prosome, macropain) 26S subunit, non-ATPase, 12" },
            { "4026", "LPP", "Protein", "hsa:4026", "LIM domain containing preferred translocation partner in lipoma" },
            { "4025", "LPO", "Enzyme", "hsa:4025", "lactoperoxidase" },
            { "84913", "ATOH8", "Protein", "hsa:84913", "atonal homolog 8 (Drosophila)" },
            { "HMDB03550", "Calcidiol", "Metabolite", "cpd:C01561", "25-Hydroxy-cholecalciferol;25-Hydroxycholecalciferol;25-Hydroxyvitamin D3;5,6-cis-25-Hydroxyvitamin D3;9,10-Secocholesta-5,7,10(19)-triene-3b,25-diol;Calcidiol;Calcifediol;Calcifediol anhydrous;Calderol;Didrogyl;Hidroferol" },
            { "28998", "MRPL13", "Protein", "hsa:28998", "mitochondrial ribosomal protein L13" },
            { "5154", "PDGFA", "Protein", "hsa:5154", "platelet-derived growth factor alpha polypeptide" },
            { "55290", "BRF2", "Protein", "hsa:55290", "BRF2, subunit of RNA polymerase III transcription initiation factor, BRF1-like" }
        };
            
        return objarray;
          
    }
    
    private static Object[][] getObjArrayPart1(){
                 
        final Object[][] objarray = {
            { "55291", "PPP6R3", "Protein", "hsa:55291", "protein phosphatase 6, regulatory subunit 3" },
            { "26578", "OSTF1", "Protein", "hsa:26578", "osteoclast stimulating factor 1" },
            { "51268", "PIPOX", "Enzyme", "hsa:51268", "pipecolic acid oxidase" },
            { "727897", "MUC5B", "Protein", "hsa:727897", "mucin 5B, oligomeric mucus/gel-forming" },
            { "5980", "REV3L", "Enzyme", "hsa:5980", "REV3-like, polymerase (DNA directed), zeta, catalytic subunit" },
            { "26576", "SRPK3", "Protein", "hsa:26576", "SRSF protein kinase 3" },
            { "5987", "TRIM27", "Protein", "hsa:5987", "tripartite motif containing 27" },
            { "7515", "XRCC1", "Protein", "hsa:7515", "X-ray repair complementing defective repair in Chinese hamster cells 1" },
            { "7514", "XPO1", "Protein", "hsa:7514", "exportin 1 (CRM1 homolog, yeast)" },
            { "348", "APOE", "Protein", "hsa:348", "apolipoprotein E" }
        };
            
        return objarray;
          
    }
    
    private static Object[][] getObjArrayPart2(){
                 
        final Object[][] objarray = {
            { "3992", "FADS1", "Protein", "hsa:3992", "fatty acid desaturase 1" },
            { "3990", "LIPC", "Enzyme", "hsa:3990", "lipase, hepatic" },
            { "4839", "NOP2", "Protein", "hsa:4839", "NOP2 nucleolar protein" },
            { "51280", "GOLM1", "Protein", "hsa:51280", "golgi membrane protein 1" },
            { "HMDB00193", "Isocitric acid", "Metabolite", "cpd:C00311", "1-Hydroxy-1,2,3-propanetricarboxylate;1-Hydroxy-1,2,3-propanetricarboxylic acid;1-Hydroxypropane-1,2,3-tricarboxylate;1-Hydroxypropane-1,2,3-tricarboxylic acid;1-Hydroxytricarballylate;1-Hydroxytricarballylic acid;3-Carboxy-2,3-dideoxy-1-hydroxypropan-1,2,3-tricarboxylate;3-Carboxy-2,3-dideoxy-1-hydroxypropan-1,2,3-tricarboxylic acid;3-Carboxy-2,3-dideoxy-Pentarate;3-Carboxy-2,3-dideoxy-Pentaric acid;D-Isocitrate;I-CIT;Isocitrate;Threo-D(S)-iso-citrate;Threo-Ds-isocitrate", "Citric Acid Cycle" },
            { "8165", "AKAP1", "Protein", "hsa:8165", "A kinase (PRKA) anchor protein 1" },
            { "65125", "WNK1", "Protein", "hsa:65125", "WNK lysine deficient protein kinase 1" },
            { "4684", "NCAM1", "Protein", "hsa:4684", "neural cell adhesion molecule 1" },
            { "HMDB03911", "3-Aminoisobutanoic acid", "Metabolite", "cpd:C05145", "(+/-)-3-Amino-2-methylpropanoate;(+/-)-3-Amino-2-methylpropanoic acid;(+/-)-3-Aminoisobutyric acid;(+/-)-b-Aminoisobutyric acid;(+/-)-beta-Aminoisobutyric acid;2-Methyl-b-alanine;2-Methyl-beta-alanine;3-Amino-2-methylpropanoate;3-Amino-2-methylpropanoic acid;3-Amino-isobutanoate;3-Amino-isobutanoic acid;3-Aminoisobutanoate;3-Aminoisobutanoic acid;3-Aminoisobutyrate;3-Aminoisobutyric acid;a-Methyl-b-alanine;alpha-Methyl-beta-alanine;b-Aminoisobutyric acid;beta-Aminoisobutyric acid;DL-2-Methyl-b-alanine;DL-2-Methyl-beta-alanine;DL-3-Amino-2-methylpropionic acid;Dl-3-Aminoisobutyric acid;DL-beta-Aminoisobutyric acid", "Pyrimidine Metabolism" },
            { "6150", "MRPL23", "Protein", "hsa:6150", "mitochondrial ribosomal protein L23" }
        };
            
        return objarray;
          
    }
    
    private static Object[][] getObjArrayPart3(){
                 
        final Object[][] objarray = {
            { "HMDB00191", "L-Aspartic acid", "Metabolite", "cpd:C00049", " Asp;(+)-Aspartate;(+)-Aspartic acid;(2S)-Aspartate;(2S)-Aspartic acid;(L)-Aspartate;(L)-Aspartic acid;(R)-2-aminosuccinate;(S)-(+)-Aspartate;(S)-(+)-Aspartic acid;(S)-2-aminosuccinate;(S)-2-aminosuccinic acid;(S)-amino-Butanedioate;(S)-amino-Butanedioic acid;(S)-Aminobutanedioate;(S)-Aminobutanedioic acid;(S)-Aspartate;(S)-Aspartic acid;2-Amino-3-methylsuccinate;2-Amino-3-methylsuccinic acid;2-Aminosuccinate;2-Aminosuccinic acid;alpha-Aminosuccinate;alpha-Aminosuccinic acid;Aminosuccinate;Asparagate;Asparagic acid;Asparaginate;Asparaginic acid;Asparatate;Aspartate;H-Asp-OH;L-(+)-Aspartate;L-(+)-Aspartic acid;L-Aminosuccinate;L-Aminosuccinic acid;L-Asparagate;L-Asparagic acid;L-Asparaginate;L-Asparaginic acid;L-Aspartate", "Ammonia Recycling;Arginine and Proline Metabolism;Aspartate Metabolism;Beta-Alanine Metabolism;Malate-Aspartate Shuttle;Transcription/Translation;Urea Cycle" },
            { "HMDB00259", "Serotonin", "Metabolite", "cpd:C00780", "3-(2-Aminoethyl)-1H-indol-5-ol;3-(2-Aminoethyl)indol-5-ol;3-(b-Aminoethyl)-5-hydroxyindole;5-HT;5-HTA;5-Hydroxy-3-(b-aminoethyl)indole;5-Hydroxy-tryptamine;5-Hydroxyltryptamine;5-Hydroxytriptamine;5-Hydroxytryptamine;Antemovis;DS substance;Enteramin;Enteramine", "Tryptophan Metabolism" },
            { "HMDB00258", "Sucrose", "Metabolite", "cpd:C00089", "(+)-Sucrose;b -D-Fructofuranosyl a-D-glucopyranoside;D-(+)-Saccharose;D-(+)-Sucrose;D-Sucrose;Saccharose;Saccharum;Sucrose;Sugar;Table sugar;White sugar", "Galactose Metabolism;Starch and Sucrose Metabolism" },
            { "55902", "ACSS2", "Enzyme", "hsa:55902", "acyl-CoA synthetase short-chain family member 2" },
            { "HMDB00254", "Succinic acid", "Metabolite", "cpd:C00042", "1,2-Ethanedicarboxylate;1,2-Ethanedicarboxylic acid;1,4-Butanedioate;1,4-Butanedioic acid;Amber acid;Asuccin;Dihydrofumarate;Dihydrofumaric acid;Katasuccin;Succinate;Wormwood acid", "Carnitine Synthesis;Citric Acid Cycle;Glutamate Metabolism;Mitochondrial Electron Transport Chain;Phytanic Acid Peroxisomal Oxidation" },
            { "8468", "FKBP6", "Protein", "hsa:8468", "FK506 binding protein 6, 36kDa" },
            { "HMDB00256", "Squalene", "Metabolite", "cpd:C00751", "(all-E)-2,6,10,15,19,23-hexamethyl-2,6,10,14,18,22-Tetracosahexaene;(E,E,E,E)-Squalene;2,6,10,15,19,23-Hexamethyl-2,6,10,14,18,22-tetracosahexaene;All-trans-Squalene;Nikko Squalane EX;Spinacen;Spinacene;Squalen;Squalene;trans-Squalene", "Steroid Biosynthesis" },
            { "HMDB00251", "Taurine", "Metabolite", "cpd:C00245", "1-Aminoethane-2-sulfonate;1-Aminoethane-2-sulfonic acid;2-Aminoethanesulfonate;2-Aminoethanesulfonic acid;2-Aminoethylsulfonate;2-Aminoethylsulfonic acid;2-Sulfoethylamine;Aminoethylsulfonate;Aminoethylsulfonic acid;b-Aminoethylsulfonate;b-Aminoethylsulfonic acid;beta-Aminoethylsulfonate;beta-Aminoethylsulfonic acid;Taurine", "Bile Acid Biosynthesis;Taurine and Hypotaurine Metabolism" },
            { "55907", "CMAS", "Enzyme", "hsa:55907", "cytidine monophosphate N-acetylneuraminic acid synthetase" },
            { "HMDB00253", "Pregnenolone", "Metabolite", "cpd:C01953", "(3b)-3-hydroxy-Pregn-5-en-20-one;3-Hydroxypregn-5-en-20-one;3b-Hydroxypregn-5-en-20-one;3beta-Hydroxypregn-5-en-20-one;5-Pregnen-3b-ol-20-one;5-Pregnen-3beta-ol-20-one;Natolone;Pregn-5-en-3b-ol-20-one;Pregn-5-ene-3b-ol-20-one;Pregnetan;Pregneton;Pregnolon;Prenolon;Regnosone;Skinostelon", "Steroidogenesis" }
        };
            
        return objarray;
          
    }
    
    private static Object[][] getObjArrayPart4(){
                 
        final Object[][] objarray = {
            { "HMDB00252", "Sphingosine", "Metabolite", "cpd:C00319", "(-)-D-erythro-Sphingosine;(2S,3R)-Sphingosine;(2S,3R,4E)-2-Amino-4-octadecene-1,3-diol;(4E)-Sphingenine;4-Sphingenine;4-trans-Sphingenine;[R-[R*,S*-(E)]]-2-amino-4-Octadecene-1,3-diol", "Sphingolipid Metabolism" },
            { "10965", "ACOT2", "Enzyme", "hsa:10965", "acyl-CoA thioesterase 2" },
            { "10963", "STIP1", "Protein", "hsa:10963", "stress-induced-phosphoprotein 1" },
            { "HMDB00194", "Anserine", "Metabolite", "cpd:C01262", "Anserine;beta-Alanyl-N(pai)-methyl-L-histidine;L-Anserine;L-N-b-Alanyl-3-methyl-Histidine;L-N-beta-Alanyl-3-methyl-Histidine;N-b-Alanyl-3-methyl-L-Histidine;N-beta-Alanyl-3-methyl-L-histidine", "Beta-Alanine Metabolism" },
            { "2318", "FLNC", "Protein", "hsa:2318", "filamin C, gamma" },
            { "2319", "FLOT2", "Protein", "hsa:2319", "flotillin 2" },
            { "4738", "NEDD8", "Protein", "hsa:4738", "neural precursor cell expressed, developmentally down-regulated 8" },
            { "HMDB01347", "Isopentenyl pyrophosphate", "Metabolite", "cpd:C00129", "3-Methyl-3-butenyl pyrophosphate;delta(3)-Isopentenyl-PP;delta-3-Isopentenyl pyrophosphat;Delta3-isopentenyl diphosphate;Delta3-methyl-3-butenyl diphosphate;Diphosphoric acid mono(3-methyl-3-butenyl) ester;IPP;IPR;Isopentenyl diphosphate;Isopentenyl pyrophosphate;Isopentenyl-pp;Mono(3-methyl-3-butenyl) diphosphate", "Steroid Biosynthesis" },
            { "HMDB00496", "3-Methoxy-4-hydroxyphenylglycol glucuronide", "Metabolite", "cpd:C03033", "3-Methoxy-4-hydroxyphenylglycol glucuronide;MHPG glucuronide" },
            { "4831", "NME2", "Enzyme", "hsa:4831", "NME/NM23 nucleoside diphosphate kinase 2" }
        };
            
        return objarray;
          
    }
    
    private static Object[][] getObjArrayPart5(){
                 
        final Object[][] objarray = {
            { "27173", "SLC39A1", "Protein", "hsa:27173", "solute carrier family 39 (zinc transporter), member 1" },
            { "55588", "MED29", "Protein", "hsa:55588", "mediator complex subunit 29" },
            { "4830", "NME1", "Enzyme", "hsa:4830", "NME/NM23 nucleoside diphosphate kinase 1" },
            { "292", "SLC25A5", "Protein", "hsa:292", "solute carrier family 25 (mitochondrial carrier; adenine nucleotide translocator), member 5" },
            { "5859", "QARS", "Enzyme", "hsa:5859", "glutaminyl-tRNA synthetase" },
            { "290", "ANPEP", "Enzyme", "hsa:290", "alanyl (membrane) aminopeptidase" },
            { "26471", "NUPR1", "Protein", "hsa:26471", "nuclear protein, transcriptional regulator, 1" },
            { "4832", "NME3", "Enzyme", "hsa:4832", "NME/NM23 nucleoside diphosphate kinase 3" },
            { "260293", "CYP4X1", "Enzyme", "hsa:260293", "cytochrome P450, family 4, subfamily X, polypeptide 1" },
            { "56052", "ALG1", "Enzyme", "hsa:56052", "ALG1, chitobiosyldiphosphodolichol beta-mannosyltransferase" }
        };
            
        return objarray;
          
    }
    
    private static Object[][] getObjArrayPart6(){
                 
        final Object[][] objarray = {
            { "HMDB00056", "Beta-Alanine", "Metabolite", "cpd:C00099", "2-Carboxyethylamine;3-Amino-Propanoate;3-Amino-Propanoic acid;3-Aminopropanoate;3-Aminopropanoic acid;3-Aminopropionate;3-Aminopropionic acid;Abufene;B-Alanine;b-Aminopropanoate;b-Aminopropanoic acid;b-Aminopropionate;b-Aminopropionic acid;beta Alanine;beta-Alanine;beta-Aminopropanoate;beta-Aminopropanoic acid;beta-Aminopropionate;beta-Aminopropionic acid;Omega-Aminopropionate;Omega-Aminopropionic acid", "Aspartate Metabolism;Beta-Alanine Metabolism;Propanoate Metabolism;Pyrimidine Metabolism" },
            { "270", "AMPD1", "Enzyme", "hsa:270", "adenosine monophosphate deaminase 1" },
            { "271", "AMPD2", "Enzyme", "hsa:271", "adenosine monophosphate deaminase 2" },
            { "272", "AMPD3", "Enzyme", "hsa:272", "adenosine monophosphate deaminase 3" },
            { "273", "AMPH", "Protein", "hsa:273", "amphiphysin" },
            { "55832", "CAND1", "Protein", "hsa:55832", "cullin-associated and neddylation-dissociated 1" },
            { "275", "AMT", "Enzyme", "hsa:275", "aminomethyltransferase" },
            { "276", "AMY1A", "Enzyme", "hsa:276", "amylase, alpha 1A (salivary)" },
            { "277", "AMY1B", "Enzyme", "hsa:277", "amylase, alpha 1B (salivary)" },
            { "278", "AMY1C", "Enzyme", "hsa:278", "amylase, alpha 1C (salivary)" }
        };
            
        return objarray;
          
    }
    
    private static Object[][] getObjArrayPart7(){
                 
        final Object[][] objarray = {
            { "279", "AMY2A", "Enzyme", "hsa:279", "amylase, alpha 2A (pancreatic)" },
            { "4659", "PPP1R12A", "Protein", "hsa:4659", "protein phosphatase 1, regulatory subunit 12A" },
            { "283871", "PGP", "Enzyme", "hsa:283871", "phosphoglycolate phosphatase" },
            { "26156", "RSL1D1", "Protein", "hsa:26156", "ribosomal L1 domain containing 1" },
            { "140890", "SREK1", "Protein", "hsa:140890", "splicing regulatory glutamine/lysine-rich protein 1" },
            { "57617", "VPS18", "Protein", "hsa:57617", "vacuolar protein sorting 18 homolog (S. cerevisiae)" },
            { "30844", "EHD4", "Protein", "hsa:30844", "EH-domain containing 4" },
            { "26151", "NAT9", "Protein", "hsa:26151", "N-acetyltransferase 9 (GCN5-related, putative)" },
            { "2109", "ETFB", "Protein", "hsa:2109", "electron-transfer-flavoprotein, beta polypeptide" },
            { "80201", "HKDC1", "Enzyme", "hsa:80201", "hexokinase domain containing 1" }
        };
            
        return objarray;
          
    }
    
    private static Object[][] getObjArrayPart8(){
                 
        final Object[][] objarray = {
            { "22863", "ATG14", "Protein", "hsa:22863", "autophagy related 14" },
            { "81628", "TSC22D4", "Protein", "hsa:81628", "TSC22 domain family, member 4" },
            { "51116", "MRPS2", "Protein", "hsa:51116", "mitochondrial ribosomal protein S2" },
            { "51110", "LACTB2", "Protein", "hsa:51110", "lactamase, beta 2" },
            { "63935", "PCIF1", "Protein", "hsa:63935", "PDX1 C-terminal inhibiting factor 1" },
            { "81622", "UNC93B1", "Protein", "hsa:81622", "unc-93 homolog B1 (C. elegans)" },
            { "2260", "FGFR1", "Protein", "hsa:2260", "fibroblast growth factor receptor 1" },
            { "51119", "SBDS", "Protein", "hsa:51119", "Shwachman-Bodian-Diamond syndrome" },
            { "81627", "TRMT1L", "Protein", "hsa:81627", "tRNA methyltransferase 1 homolog (S. cerevisiae)-like" },
            { "387923", "SERP2", "Protein", "hsa:387923", "stress-associated endoplasmic reticulum protein family member 2" }
        };
            
        return objarray;
          
    }
    
    private static Object[][] getObjArrayPart9(){
                 
        final Object[][] objarray = {
            { "30849", "PIK3R4", "Protein", "hsa:30849", "phosphoinositide-3-kinase, regulatory subunit 4" },
            { "122618", "PLD4", "Enzyme", "hsa:122618", "phospholipase D family, member 4" },
            { "57479", "PRR12", "Protein", "hsa:57479", "proline rich 12" },
            { "9524", "TECR", "Protein", "hsa:9524", "trans-2,3-enoyl-CoA reductase" },
            { "9525", "VPS4B", "Protein", "hsa:9525", "vacuolar protein sorting 4 homolog B (S. cerevisiae)" },
            { "9526", "MPDU1", "Protein", "hsa:9526", "mannose-P-dolichol utilization defect 1" },
            { "9255", "AIMP1", "Protein", "hsa:9255", "aminoacyl tRNA synthetase complex-interacting multifunctional protein 1" },
            { "9253", "NUMBL", "Protein", "hsa:9253", "numb homolog (Drosophila)-like" },
            { "57473", "ZNF512B", "Protein", "hsa:57473", "zinc finger protein 512B" },
            { "HMDB06895", "3a,7a-Dihydroxy-5b-cholest-24-enoyl-CoA", "Metabolite", "cpd:C05447", "3a,7a-Dihydroxy-5b-cholest-24-enoyl-CoA;3a,7a-Dihydroxy-5b-cholest-24-enoyl-Coenzyme A;3alpha,7alpha-Dihydroxy-5beta-cholest-24-enoyl-CoA;3alpha,7alpha-Dihydroxy-5beta-cholest-24-enoyl-Coenzyme A", "Bile Acid Biosynthesis" }
        };
            
        return objarray;
          
    }
    
    private static Object[][] getObjArrayPart10(){
                 
        final Object[][] objarray = {
            { "HMDB06894", "3a,7a-Dihydroxy-5b-cholestan-26-al", "Metabolite", "cpd:C05445", "3alpha,7alpha-dihydroxy-5beta-cholestan-27-al;5beta-cholestan-27-al-3alpha,7alpha-diol;5beta-cholestane-3alpha,7alpha-diol-27-al", "Bile Acid Biosynthesis" },
            { "HMDB06896", "3a,7a-Dihydroxy-5b-24-oxocholestanoyl-CoA", "Metabolite", "cpd:C05449", "3a,7a-Dihydroxy-5b-24-oxocholestanoyl-CoA;3a,7a-Dihydroxy-5b-24-oxocholestanoyl-Coenzyme A;3alpha,7alpha-Dihydroxy-5beta-24-oxocholestanoyl-CoA;3alpha,7alpha-Dihydroxy-5beta-24-oxocholestanoyl-Coenzyme A" },
            { "HMDB06891", "3a,7a,12a-Trihydroxy-5b-24-oxocholestanoyl-CoA", "Metabolite", "cpd:C05467", "(24R,25R)-3-alpha,7-alpha,12-alpha,24-tetrahydroxy-5-beta-cholestan-26-oyl-CoA;(24R,25R)-3-alpha,7-alpha,12-alpha,24-tetrahydroxy-5-beta-cholestan-26-oyl-Coenzyme A;24-Oxo-25(R)-trihydroxycoprostanoyl-CoA;24-Oxo-25(R)-trihydroxycoprostanoyl-Coenzyme A;25(R)-24-Oxo-3alpha,7alpha,12alpha-trihydroxycoprostanoyl-CoA;25(R)-24-Oxo-3alpha,7alpha,12alpha-trihydroxycoprostanoyl-Coenzyme A;25(R)-3alpha,7alpha,12alpha-trihydroxy-24-oxo-5beta-cholestanoyl-CoA;25(R)-3alpha,7alpha,12alpha-trihydroxy-24-oxo-5beta-cholestanoyl-Coenzyme A;25(R)-3alpha,7alpha,12alpha-trihydroxy-5beta-cholestan-24-on-26-oyl-CoA;25(R)-3alpha,7alpha,12alpha-trihydroxy-5beta-cholestan-24-on-26-oyl-Coenzyme A;25(R)-5beta-cholestane-3alpha,7alpha,12alpha-triol-24-on-26-oyl-CoA;25(R)-5beta-cholestane-3alpha,7alpha,12alpha-triol-24-on-26-oyl-Coenzyme A;3-alpha,7-alpha,12-alpha,24-Tetrahydroxy-5-beta-cholestan-26-oyl-CoA;3-alpha,7-alpha,12-alpha,24-Tetrahydroxy-5-beta-cholestan-26-oyl-Coenzyme A;3-alpha,7-alpha,12-alpha-Trihydroxy-24-oxo-5-beta-cholestanoyl-CoA;3-alpha,7-alpha,12-alpha-Trihydroxy-24-oxo-5-beta-cholestanoyl-Coenzyme A;3a,7a,12a-Trihydroxy-5b-24-oxocholestanoyl-CoA;3a,7a,12a-Trihydroxy-5b-24-oxocholestanoyl-Coenzyme A;3alpha,7alpha,12alpha-Trihydroxy-24-oxo-5beta-cholestanoyl-CoA;3alpha,7alpha,12alpha-Trihydroxy-24-oxo-5beta-cholestanoyl-Coenzyme A;3alpha,7alpha,12alpha-Trihydroxy-5beta-24-oxocholestanoyl-CoA;3alpha,7alpha,12alpha-Trihydroxy-5beta-24-oxocholestanoyl-Coenzyme A;R-TrHOCCoA;R-TrHOCCoenzyme A", "Bile Acid Biosynthesis" },
            { "HMDB06890", "3a,7a,12a,24-Tetrahydroxy-5b-cholestanoyl-CoA", "Metabolite", "cpd:C05450", "(24R,25R)-3-alpha,7-alpha,12-alpha,24-tetrahydroxy-5-beta-cholestanoyl-CoA;(24R,25R)-3-alpha,7-alpha,12-alpha,24-tetrahydroxy-5-beta-cholestanoyl-Coenzyme A;3a,7a,12a,24-Tetrahydroxy-5b-cholestanoyl-CoA;3a,7a,12a,24-Tetrahydroxy-5b-cholestanoyl-Coenzyme A;3a,7a,12a,24z-Tetrahydroxy-5b-cholestanoyl-CoA;3a,7a,12a,24z-Tetrahydroxy-5b-cholestanoyl-Coenzyme A;3alpha,7alpha,12alpha,24-Tetrahydroxy-5beta-cholestanoyl-CoA;3alpha,7alpha,12alpha,24-Tetrahydroxy-5beta-cholestanoyl-Coenzyme A;3alpha,7alpha,12alpha,24zeta-Tetrahydroxy-5beta-cholestanoyl-CoA;3alpha,7alpha,12alpha,24zeta-Tetrahydroxy-5beta-cholestanoyl-Coenzyme A", "Bile Acid Biosynthesis" }
        };
            
        return objarray;
          
    }
    

    public static void main(String[] args) {

        Object obj2d[][] = assemble_ObjArrayParts();
        for(Object[] obj1d:obj2d){
            System.out.println(obj1d);
            for(Object obj:obj1d){
                System.out.println(obj);
            }
        }
        
    }      
      
}
