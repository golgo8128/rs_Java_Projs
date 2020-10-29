package org.cytoscape.rsCy3App.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MetabPPI_EdgeTable_Info1_2 {

    public static final String[] clsnam_edgeattr
        = { "String", "String", "String", "Integer", "String",
            "String", "String", "String", "String", "String",
            "String" };   

    public static final String[] colnam_edgeattr
        = { "Node 1", "Node 2", "Diseases", "weight", "Tissues", "Experimental throughput", "Interaction type", "PubMed IDs", "EC numbers", "Pathways", "Localizations" };

    public static Object[][] assemble_ObjArrayParts(){

        ArrayList<Object[]> arrayList = new ArrayList<Object[]>();     

        Class<?> klass = MetabPPI_EdgeTable_Info1_2.class;
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
            { "261726", "4000", "", 1, "", "High", "PPI", "IDs:22412018" },
            { "261726", "7316", "", 6, "", "High", "PPI", "IDs:21890473;21963094;21139048;22505724;23000965;21906983" },
            { "261726", "6652", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "261726", "25895", "", 2, "", "Low/High", "PPI", "IDs:23349634" },
            { "261726", "7001", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "261726", "5531", "", 2, "", "High", "PPI", "IDs:17353931;16085932" },
            { "261726", "5537", "", 3, "", "Low", "PPI", "IDs:16085932;24255178" },
            { "261726", "8452", "", 1, "", "High", "PPI", "IDs:21145461" },
            { "261726", "351", "", 1, "", "High", "PPI", "IDs:21832049" },
            { "261726", "523", "", 1, "", "High", "PPI", "IDs:22939629" }
        };

        return objarray;

    }

    private static Object[][] getObjArrayPart1(){
                 
        final Object[][] objarray = {
            { "261726", "29101", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "261726", "5052", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "261726", "5516", "", 2, "", "Low", "PPI", "IDs:16085932" },
            { "375", "1017", "", 1, "", "High", "PPI", "IDs:21319273" },
            { "375", "64210", "", 1, "", "High", "PPI", "IDs:22678362" },
            { "375", "22820", "", 2, "", "Low", "PPI", "IDs:10720466" },
            { "375", "10537", "", 1, "", "High", "PPI", "IDs:22797925" },
            { "375", "2869", "", 1, "", "High", "PPI", "IDs:22952844" },
            { "375", "7316", "", 10, "", "Low/High", "PPI", "IDs:21890473;21139048;16196087;22053931;21853274;18781797;21963094;23000965;21906983" },
            { "375", "7412", "", 1, "", "High", "PPI", "IDs:19738201" }
        };

        return objarray;

    }

    private static Object[][] getObjArrayPart2(){
                 
        final Object[][] objarray = {
            { "375", "8925", "", 2, "", "Low", "PPI", "IDs:8861955" },
            { "375", "1937", "", 1, "", "High", "PPI", "IDs:16169070" },
            { "375", "5337", "", 2, "", "Low", "PPI", "IDs:9688545" },
            { "375", "4988", "", 2, "", "Low", "PPI", "IDs:12519790" },
            { "375", "5898", "", 2, "", "Low", "PPI", "IDs:9688545" },
            { "375", "5338", "", 3, "", "Low", "PPI", "IDs:10801846;11373276" },
            { "375", "1386", "", 1, "", "High", "PPI", "IDs:22304920" },
            { "375", "26270", "", 1, "", "High", "PPI", "IDs:22268729" },
            { "375", "3676", "", 1, "", "High", "PPI", "IDs:22623428" },
            { "375", "9266", "", 2, "", "Low", "PPI", "IDs:9417041" }
        };

        return objarray;

    }

    private static Object[][] getObjArrayPart3(){
                 
        final Object[][] objarray = {
            { "4026", "112398", "", 2, "", "Low", "PPI", "IDs:22286099" },
            { "4026", "1994", "", 1, "", "High", "PPI", "IDs:19322201" },
            { "4026", "55163", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "4026", "1207", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "4026", "7316", "", 1, "", "High", "PPI", "IDs:21139048" },
            { "4026", "5518", "", 2, "", "Low", "PPI", "IDs:19156129" },
            { "4026", "1374", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "4026", "3309", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "4026", "9551", "", 1, "", "High", "PPI", "IDs:16169070" },
            { "4026", "7428", "", 2, "", "Low", "PPI", "IDs:22286099" }
        };

        return objarray;

    }

    private static Object[][] getObjArrayPart4(){
                 
        final Object[][] objarray = {
            { "4025", "HMDB12915", "Carcinoma, Lewis Lung;Fanconi Anemia;Azotemia;Lupus Erythematosus, Systemic;Anemia, Hemolytic;HIV Infections;Dermatitis Herpetiformis;Hearing Loss;Cholecystitis, Acute;Thrombophilia;Glomerulonephritis, IGA;Anetoderma;Carcinoma, Ehrlich Tumor;Ichthyosis Vulgaris;Plague;Chronic Periodontitis;Hypotension;Hydatidiform Mole;Respiratory Tract Infections;Prostatic Neoplasms;Hypoglycemia;Thyroid Nodule;Sinusitis;Xerostomia;Common Variable Immunodeficiency;Head Injuries, Closed;Embolism;Neuralgia;Neoplasms, Squamous Cell;Kernicterus;Renal Insufficiency, Chronic;Anemia, Iron-Deficiency;Epilepsy;Stomach Diseases;Congenital Hypothyroidism;Decompression Sickness;glutathione peroxidase deficiency;Mumps;Hepatitis, Chronic;Paraganglioma;Vasculitis;Tuberculosis, Pleural;Ventricular Fibrillation;Herpes Zoster;Macular Degeneration;Carcinoma 256, Walker;Whooping Cough;Hypertension, Pulmonary;Schistosomiasis;Milk Hypersensitivity;Carcinoma in Situ;Bronchopneumonia;Hyperthyroidism;Carcinoma, Neuroendocrine;Celiac Disease;Insulinoma;Tuberculosis, Meningeal;Lymphoma, T-Cell, Cutaneous;Bradycardia;Pulpitis;Keratosis;Anemia, Refractory, with Excess of Blasts;Papilloma;Heart Neoplasms;Anemia, Aplastic;Metrorrhagia;Equine Infectious Anemia;Arthritis, Experimental;Thrombocytopenia;Melanoma;Mucocele;Dental Pulp Calcification;Spondylitis, Ankylosing;Cardiovascular Diseases;Granular Cell Tumor;Reperfusion Injury;Lymphopenia;Exanthema;Pheochromocytoma;Neoplasms, Glandular and Epithelial;Agranulocytosis;Cholera;Sialadenitis;Varicocele;Breast Neoplasms;Choriocarcinoma;Gastroenteritis;Odontogenic Cysts;Liver Cirrhosis;Striatonigral Degeneration;Rosacea;Cholesteatoma;Hyperaldosteronism;Carcinoma, Squamous Cell;Cerebral Amyloid Angiopathy;Pemphigus;Limbic Encephalitis;Prenatal Injuries;Death, Sudden, Cardiac;Mastitis;Food Hypersensitivity;beta-Thalassemia;Systemic Vasculitis;Carcinosarcoma;Adenoma, Oxyphilic;Cholangiocarcinoma;Chediak-Higashi Syndrome;Keloid;Ovarian Neoplasms;Infertility, Male;Shock, Septic;Periodontitis;Cryptococcosis;Liver Neoplasms, Experimental;Hypoproteinemia;Myeloproliferative Disorders;Vitamin E Deficiency;Neuritis, Autoimmune, Experimental;Carcinoma, Hepatocellular;Mouth Neoplasms;Leiomyoma;Rhabdomyosarcoma, Embryonal;Nervous System Diseases;Intracranial Arteriosclerosis;Leukoencephalopathy, Progressive Multifocal;Thyrotoxicosis;Silicosis;Hirschsprung Disease;Facial Paralysis;Plant Diseases;Cardiomegaly;Adenocarcinoma, Mucinous;Paget Disease, Extramammary;Myocardial Ischemia;Oral Ulcer;Leukostasis;Carcinoma, Transitional Cell;Adrenocortical Adenoma;Multiple Sclerosis;Cardiomyopathy, Dilated;Olfactory Nerve Injuries;Optic Neuritis;Craniopharyngioma;Immunoblastic Lymphadenopathy;Melanoma, Experimental;Retinal Diseases;Thalassemia;Fasciitis;Porphyrias;Retinal Detachment;Pelvic Inflammatory Disease;Fabry Disease;Fetal Resorption;Encephalitis;Lymphadenitis;Phenylketonurias;Alveolitis, Extrinsic Allergic;Sarcoidosis;Mercury Poisoning, Nervous System;Aggressive Periodontitis;Pulmonary Eosinophilia;Oculomotor Nerve Injuries;Rheumatic Diseases;Dentigerous Cyst;Carcinoma, Acinar Cell;Encephalomyelitis, Autoimmune, Experimental;Myelodysplastic Syndromes;Activated Protein C Resistance;Mastocytosis, Systemic;Psoriasis;Chronic Pain;Fetal Growth Retardation;Lymphoma, T-Cell;Phlebitis;Gastritis, Atrophic;Common Cold;Leukemia, Myelogenous, Chronic, BCR-ABL Positive;Kidney Neoplasms;Gyrate Atrophy;Esophageal Neoplasms;Anaphylaxis;Eye Injuries;Hematologic Neoplasms;Parathyroid Neoplasms;Lyme Disease;Adenocarcinoma, Bronchiolo-Alveolar;Thrombosis;Heart Rupture;Cytomegalovirus Infections;Diabetic Neuropathies;Leprosy;Cell Transformation, Neoplastic;Rocky Mountain Spotted Fever;Diabetes Mellitus;Obesity;Paratuberculosis;Pancreatic Neoplasms;Brain Neoplasms;Sarcoma, Avian;Vesicular Stomatitis;Keratitis;Acquired Immunodeficiency Syndrome;Dental Caries;Leptospirosis;Iron Overload;Hypereosinophilic Syndrome;Rheumatic Nodule;Thymoma;Scleroderma, Limited;Leukemia, Hairy Cell;Coccidioidomycosis;Hepatitis;Neutropenia;Uremia;Gigantism;Malaria;Mesothelioma;Urticaria Pigmentosa;Granuloma;Chagas Disease;Carcinoma;Myasthenia Gravis;Endometrial Neoplasms;Larva Migrans;Memory Disorders;Strabismus;Varicose Veins;Angina Pectoris;Esotropia;Alkaptonuria;Syringomyelia;Eosinophilia;Arthritis, Rheumatoid;Newcastle Disease;Endometriosis;Goiter, Endemic;Alzheimer Disease;Red-Cell Aplasia, Pure;Neoplasms;Nevus, Pigmented;Leiomyosarcoma;Ataxia Telangiectasia;Papilledema;Infarction, Middle Cerebral Artery;Laryngeal Neoplasms;Hypertrophy, Left Ventricular;Carcinoma, Small Cell;Leukemia, Megakaryoblastic, Acute;Airway Obstruction;Hypersensitivity;Pulmonary Disease, Chronic Obstructive;Fusariosis;Cysts;Coronary Occlusion;Starvation;Atherosclerosis;Mycosis Fungoides;Tooth Injuries;Condylomata Acuminata;Endomyocardial Fibrosis;Osteoarthritis;Aphasia;Cystic Fibrosis;Leprosy, Lepromatous;Warm Ischemia;Tonsillitis;Neoplasm Micrometastasis;Leukemia, Promyelocytic, Acute;Hyperglycemia;Autoimmune Diseases;Meningoencephalitis;Candidiasis, Oral;Gangliosidosis, GM1;Neuronal Ceroid-Lipofuscinoses;Craniofacial Abnormalities;Pancreatitis;Reoviridae Infections;Adrenocortical Hyperfunction;Skin Diseases;Hypercholesterolemia;Pseudolymphoma;Hearing Loss, Sensorineural;Acute Lung Injury;Empyema, Tuberculous;Urinary Tract Infections;Pleurisy;Dermatitis, Allergic Contact;Serum Sickness;Acromegaly;Adenocarcinoma;Keratosis, Actinic;Colitis, Ulcerative;Anemia, Refractory;Leukemia, Erythroblastic, Acute;Confusion;Multiple Endocrine Neoplasia Type 2a;Candidiasis;Hyperalgesia;Teratoma;Leukemia, Myeloid;Hepatitis C, Chronic;Lymphoma, Mantle-Cell;Cholestasis, Extrahepatic;Glomerulonephritis;Bronchitis, Chronic;Abruptio Placentae;Endolymphatic Hydrops;Tuberculosis, Lymph Node;Aneurysm;Nephritis;Amyloidosis;Scoliosis;Meningitis;Tetanus;Synovitis;Cervical Intraepithelial Neoplasia;Cholecystitis;Leukocytosis;Photosensitivity Disorders;Menorrhagia;Hepatic Encephalopathy;peroxidase deficiency;Hemangioma;Contracture;Hemangioblastoma;Anemia, Pernicious;Foot-and-Mouth Disease;Paget's Disease, Mammary;Cholestasis;Pituitary Neoplasms;Trichinellosis;Acne Vulgaris;Brain Injuries;Tachycardia;Glomerulonephritis, Membranous;Fluorosis, Dental;Toxoplasmosis;Nasal Polyps;Amyotrophic Lateral Sclerosis;Paramyxoviridae Infections;Vaccinia;Tremor;Mycoses;Sarcoma, Myeloid;Pneumonia;Vascular System Injuries;Retinal Degeneration;Myocarditis;Leukemia, Mast-Cell;Fetal Diseases;Enteritis;unspecific monooxygenase deficiency;Crigler-Najjar Syndrome;Adenoma, Pleomorphic;Classical Swine Fever;Mercury Poisoning;Giant Cell Tumors;Stomach Ulcer;Parapsoriasis;Kidney Failure, Chronic;Sarcoidosis, Pulmonary;Influenza in Birds;Pancreatitis, Chronic;Silicotuberculosis;Sepsis;Goiter, Nodular;Aleutian Mink Disease;Neurofibrosarcoma;Friedreich Ataxia;Radicular Cyst;Carcinoid Tumor;Encephalitis, Tick-Borne;Sarcoma;Adrenocortical Carcinoma;myeloperoxidase deficiency;Distemper;Leukoencephalopathies;Papilloma, Choroid Plexus;Scrapie;Diabetic Retinopathy;Urinary Bladder Neoplasms;Lung Neoplasms;Rubella;Hemosiderosis;Parotitis;Beriberi;Purpura, Thrombocytopenic, Idiopathic;Addison Disease;Bowen's Disease;Hodgkin Disease;Tuberculosis;Hemoglobinuria;Pleuropneumonia;Hypogonadism;Typhoid Fever;Dental Deposits;iodide peroxidase deficiency;Paralysis;Renal Insufficiency;Neurofibroma;Cryptorchidism;Dengue;Angina, Stable;Drug-Induced Liver Injury;Churg-Strauss Syndrome;Paragonimiasis;Albinism;Gastritis;Hypersensitivity, Immediate;Rhinitis;Anemia, Sickle Cell;Dehydration;Carcinoma, Intraductal, Noninfiltrating;Hypertension;Prostatic Hyperplasia;Herpes Simplex;Lymphatic Diseases;Subarachnoid Hemorrhage;Cerebrovascular Disorders;Idiopathic Pulmonary Fibrosis;Dermatitis, Atopic;Pleural Effusion;Myxoma;Pre-Eclampsia;Rotavirus Infections;Vitiligo;Adenocarcinoma, Papillary;Lung Diseases, Interstitial;Empyema;Syphilis;Wound Infection;Coronary Artery Disease;Thyroiditis, Autoimmune;Leukemia, Monocytic, Acute;Pain;Neuroma;Leukemia;Infection;Hyperparathyroidism;Leukoplakia;Dent Disease;Cushing Syndrome;Carcinoma, Endometrioid;Phyllodes Tumor;Cerebral Hemorrhage;Pneumoconiosis;Carcinoma, Renal Cell;Glaucoma;Gas Gangrene;Ophthalmoplegia;Polycythemia Vera;Purpura;Leukoplakia, Hairy;Liver Cirrhosis, Alcoholic;Myocardial Infarction;Parkinson Disease;Astrocytoma;Optic Nerve Injuries;Subacute Sclerosing Panencephalitis;Hyperlipidemias;Leukemia, Myelomonocytic, Acute;Fibroadenoma;Cholelithiasis;Favism;Abscess;Bronchiectasis;Asthma;Hashimoto Disease;Spondylarthropathies;Liver Failure, Acute;Peripheral Nerve Injuries;Vascular Diseases;Border Disease;Thyroiditis;Ependymoma;Pythiosis;Parkinsonian Disorders;Fascioliasis;Polycystic Ovary Syndrome;Wasting Syndrome;Liver Cirrhosis, Biliary;Craniocerebral Trauma;Stomach Neoplasms;Lichen Planus;Bronchial Spasm;Tuberculosis, Pulmonary;Heart Failure;Connective Tissue Diseases;Lung Diseases;Liver Diseases;Wegener Granulomatosis;Mycoplasma Infections;Precursor Cell Lymphoblastic Leukemia-Lymphoma;Leishmaniasis, Mucocutaneous;Hyperhomocysteinemia;Down Syndrome;Abortion, Spontaneous;Intracranial Hemorrhage, Hypertensive;Facial Nerve Injuries;Postpartum Thyroiditis;Medulloblastoma;Hepatitis E;Giardiasis;Hepatitis A;Hepatitis B;Hepatitis C;Thyroid Diseases;Fibrosarcoma;Brain Diseases;Duodenal Ulcer;Diabetes Insipidus;Hemangiosarcoma;Glioma;Peptic Ulcer;Polyneuropathies;Myositis;Stroke;Bacterial Infections;Proteinuria;Hemorrhagic Fever with Renal Syndrome;Intracranial Hemorrhages;catalase deficiency;Rinderpest;Lymphoproliferative Disorders;Acute Kidney Injury;Unconsciousness;Periodontal Diseases;Marek Disease;Chickenpox;Cerebral Palsy;Spinal Cord Compression;Aspergillosis;Thyroiditis, Subacute;Hyperemesis Gravidarum;Rabies;Lupus Erythematosus, Discoid;Leukemia, Myeloid, Acute;Primary Myelofibrosis;Nematode Infections;Cystitis;Muscular Dystrophies;Tuberculosis, Osteoarticular;Foot Ulcer;Cardiomyopathy, Hypertrophic;Acrospiroma;Primary Ovarian Insufficiency;Pyelonephritis;Nephrotic Syndrome;Multiple Myeloma;Olivopontocerebellar Atrophies;Keratoconjunctivitis Sicca;Pneumonia, Pneumocystis;Diffuse Axonal Injury;Peste-des-Petits-Ruminants;Diphtheria;Demyelinating Diseases;Wilms Tumor;Anemia, Hypochromic;Toxoplasmosis, Cerebral;Granulomatous Disease, Chronic;Crohn Disease;Gingival Diseases;Encephalomyelitis;Rheumatic Heart Disease;Chorea;Skin Neoplasms;Malaria, Cerebral;Colonic Neoplasms;Hypothyroidism;Histoplasmosis;Anemia;Arthritis, Reactive;Meningioma;Endocarditis;Eosinophilic Granuloma;Neurodegenerative Diseases;Reproductive Tract Infections;Sarcoma, Clear Cell;Brucellosis, Bovine;Stomatitis;Carcinoma, Embryonal;Keratosis, Seborrheic;Neuroblastoma;Adenoma;Histiocytosis, Langerhans-Cell;Prolactinoma;Oropharyngeal Neoplasms;Leukopenia;Inflammatory Bowel Diseases;Dengue Hemorrhagic Fever;Histiocytic Sarcoma;Hydrocephalus;Peritonitis;Blast Crisis;Pseudorabies;Encephalitis, Herpes Simplex;Echinococcosis;Fallopian Tube Diseases;Neuritis;Otitis Media;Heart Arrest;Leukemia, Prolymphocytic;Small Cell Lung Carcinoma;Hyponatremia;Seizures;Klinefelter Syndrome;Otosclerosis;Nevus, Intradermal;Head and Neck Neoplasms;Peripheral Nervous System Diseases;Plasmacytoma;Diabetic Foot;Tularemia;Retinoblastoma;Pregnancy, Prolonged;Neoplasm Metastasis;Embolism, Air;Neurofibromatoses;Mucositis;Drug Hypersensitivity;Tuberous Sclerosis;Brain Ischemia;Measles;Opportunistic Infections;Niemann-Pick Diseases;Spinal Cord Injuries;Coma;Colitis;Acatalasia;Colorectal Neoplasms;Stupor;Diabetes Mellitus, Experimental;Pemphigoid, Bullous;Carcinoma, Ductal;Influenza, Human;Nephrosis;Telangiectasis;Uveitis;Cataract;Enzootic Bovine Leukosis;Lung Injury;Hypersensitivity, Delayed;Deafness;Hemoglobinopathies;Ureteral Obstruction;Lymphoma, B-Cell;Fibrocystic Breast Disease;Dental Plaque;Ganglion Cysts;Lymphoma, Non-Hodgkin;Diabetic Nephropathies;Thyroid Neoplasms;Burkitt Lymphoma;Schistosomiasis mansoni;Dermatitis;Polycythemia;Rhabdomyosarcoma;Arthus Reaction;Cysticercosis;Endotoxemia;Graves Disease;Hematuria;Carcinoma, Papillary;Pyuria;Goiter;Hematologic Diseases;Lupus Nephritis;Arthritis;Myelitis;Leishmaniasis, Cutaneous;Spinocerebellar Degenerations;Insulin Resistance;Erythema;Lipodystrophy;Leishmaniasis, Visceral;Lymphoma;Diabetes Mellitus, Type 1;Diabetes Mellitus, Type 2;Blister;Uterine Cervicitis;Keratoacanthoma;Infertility;Leukemia, Lymphocytic, Chronic, B-Cell;Nephroma, Mesoblastic;Coinfection;Rhinitis, Allergic, Seasonal;Malnutrition;Carcinoma, Large Cell;Ameloblastoma;Gout;Leukemia, Biphenotypic, Acute;Keratoconus;Muscular Dystrophy, Duchenne;Giant Lymph Node Hyperplasia;Osteosarcoma;Blastomycosis;Brain Edema;Glioblastoma;Keratitis, Herpetic;Hypotension, Orthostatic;Gingivitis;Potassium Deficiency;Lymphedema;Carcinoma, Basal Cell;Warts;Urticaria;Heart Diseases", 2, "placenta;heart;leukocyte;spleen;thymus;small intestine;colon;liver;cervical mucus;lung;blood vessel wall;neutrophil;pancreas", "", "Metabolism", "", "1.11.1.7", "Phenylalanine metabolism;Phenylpropanoid biosynthesis;Biosynthesis of secondary metabolites;betanidin degradation;Microbial metabolism in diverse environments;Metabolic pathways;Methane metabolism", "cytoplasm" },
            { "4025", "HMDB03654", "Carcinoma, Lewis Lung;Fanconi Anemia;Azotemia;Lupus Erythematosus, Systemic;Anemia, Hemolytic;HIV Infections;Dermatitis Herpetiformis;Hearing Loss;Cholecystitis, Acute;Thrombophilia;Glomerulonephritis, IGA;Anetoderma;Carcinoma, Ehrlich Tumor;Ichthyosis Vulgaris;Plague;Chronic Periodontitis;Hypotension;Hydatidiform Mole;Respiratory Tract Infections;Prostatic Neoplasms;Hypoglycemia;Thyroid Nodule;Sinusitis;Xerostomia;Common Variable Immunodeficiency;Head Injuries, Closed;Embolism;Neuralgia;Neoplasms, Squamous Cell;Kernicterus;Renal Insufficiency, Chronic;Anemia, Iron-Deficiency;Epilepsy;Stomach Diseases;Congenital Hypothyroidism;Decompression Sickness;glutathione peroxidase deficiency;Mumps;Hepatitis, Chronic;Paraganglioma;Vasculitis;Tuberculosis, Pleural;Ventricular Fibrillation;Herpes Zoster;Macular Degeneration;Carcinoma 256, Walker;Whooping Cough;Hypertension, Pulmonary;Schistosomiasis;Milk Hypersensitivity;Carcinoma in Situ;Bronchopneumonia;Hyperthyroidism;Carcinoma, Neuroendocrine;Celiac Disease;Insulinoma;Tuberculosis, Meningeal;Lymphoma, T-Cell, Cutaneous;Bradycardia;Pulpitis;Keratosis;Anemia, Refractory, with Excess of Blasts;Papilloma;Heart Neoplasms;Anemia, Aplastic;Metrorrhagia;Equine Infectious Anemia;Arthritis, Experimental;Thrombocytopenia;Melanoma;Mucocele;Dental Pulp Calcification;Spondylitis, Ankylosing;Cardiovascular Diseases;Granular Cell Tumor;Reperfusion Injury;Lymphopenia;Exanthema;Pheochromocytoma;Neoplasms, Glandular and Epithelial;Agranulocytosis;Cholera;Sialadenitis;Varicocele;Breast Neoplasms;Choriocarcinoma;Gastroenteritis;Odontogenic Cysts;Liver Cirrhosis;Striatonigral Degeneration;Rosacea;Cholesteatoma;Hyperaldosteronism;Carcinoma, Squamous Cell;Cerebral Amyloid Angiopathy;Pemphigus;Limbic Encephalitis;Prenatal Injuries;Death, Sudden, Cardiac;Mastitis;Food Hypersensitivity;beta-Thalassemia;Systemic Vasculitis;Carcinosarcoma;Adenoma, Oxyphilic;Cholangiocarcinoma;Chediak-Higashi Syndrome;Keloid;Ovarian Neoplasms;Infertility, Male;Shock, Septic;Periodontitis;Cryptococcosis;Liver Neoplasms, Experimental;Hypoproteinemia;Myeloproliferative Disorders;Vitamin E Deficiency;Neuritis, Autoimmune, Experimental;Carcinoma, Hepatocellular;Mouth Neoplasms;Leiomyoma;Rhabdomyosarcoma, Embryonal;Nervous System Diseases;Intracranial Arteriosclerosis;Leukoencephalopathy, Progressive Multifocal;Thyrotoxicosis;Silicosis;Hirschsprung Disease;Facial Paralysis;Plant Diseases;Cardiomegaly;Adenocarcinoma, Mucinous;Paget Disease, Extramammary;Myocardial Ischemia;Oral Ulcer;Leukostasis;Carcinoma, Transitional Cell;Adrenocortical Adenoma;Multiple Sclerosis;Cardiomyopathy, Dilated;Olfactory Nerve Injuries;Optic Neuritis;Craniopharyngioma;Immunoblastic Lymphadenopathy;Melanoma, Experimental;Retinal Diseases;Thalassemia;Fasciitis;Porphyrias;Retinal Detachment;Pelvic Inflammatory Disease;Fabry Disease;Fetal Resorption;Encephalitis;Lymphadenitis;Phenylketonurias;Alveolitis, Extrinsic Allergic;Sarcoidosis;Mercury Poisoning, Nervous System;Aggressive Periodontitis;Pulmonary Eosinophilia;Oculomotor Nerve Injuries;Rheumatic Diseases;Dentigerous Cyst;Carcinoma, Acinar Cell;Encephalomyelitis, Autoimmune, Experimental;Myelodysplastic Syndromes;Activated Protein C Resistance;Mastocytosis, Systemic;Psoriasis;Chronic Pain;Fetal Growth Retardation;Lymphoma, T-Cell;Phlebitis;Gastritis, Atrophic;Common Cold;Leukemia, Myelogenous, Chronic, BCR-ABL Positive;Kidney Neoplasms;Gyrate Atrophy;Esophageal Neoplasms;Anaphylaxis;Eye Injuries;Hematologic Neoplasms;Parathyroid Neoplasms;Lyme Disease;Adenocarcinoma, Bronchiolo-Alveolar;Thrombosis;Heart Rupture;Cytomegalovirus Infections;Diabetic Neuropathies;Leprosy;Cell Transformation, Neoplastic;Rocky Mountain Spotted Fever;Diabetes Mellitus;Obesity;Paratuberculosis;Pancreatic Neoplasms;Brain Neoplasms;Sarcoma, Avian;Vesicular Stomatitis;Keratitis;Acquired Immunodeficiency Syndrome;Dental Caries;Leptospirosis;Iron Overload;Hypereosinophilic Syndrome;Rheumatic Nodule;Thymoma;Scleroderma, Limited;Leukemia, Hairy Cell;Coccidioidomycosis;Hepatitis;Neutropenia;Uremia;Gigantism;Malaria;Mesothelioma;Urticaria Pigmentosa;Granuloma;Chagas Disease;Carcinoma;Myasthenia Gravis;Endometrial Neoplasms;Larva Migrans;Memory Disorders;Strabismus;Varicose Veins;Angina Pectoris;Esotropia;Alkaptonuria;Syringomyelia;Eosinophilia;Arthritis, Rheumatoid;Newcastle Disease;Endometriosis;Goiter, Endemic;Alzheimer Disease;Red-Cell Aplasia, Pure;Neoplasms;Nevus, Pigmented;Leiomyosarcoma;Ataxia Telangiectasia;Papilledema;Infarction, Middle Cerebral Artery;Laryngeal Neoplasms;Hypertrophy, Left Ventricular;Carcinoma, Small Cell;Leukemia, Megakaryoblastic, Acute;Airway Obstruction;Hypersensitivity;Pulmonary Disease, Chronic Obstructive;Fusariosis;Cysts;Coronary Occlusion;Starvation;Atherosclerosis;Mycosis Fungoides;Tooth Injuries;Condylomata Acuminata;Endomyocardial Fibrosis;Osteoarthritis;Aphasia;Cystic Fibrosis;Leprosy, Lepromatous;Warm Ischemia;Tonsillitis;Neoplasm Micrometastasis;Leukemia, Promyelocytic, Acute;Hyperglycemia;Autoimmune Diseases;Meningoencephalitis;Candidiasis, Oral;Gangliosidosis, GM1;Neuronal Ceroid-Lipofuscinoses;Craniofacial Abnormalities;Pancreatitis;Reoviridae Infections;Adrenocortical Hyperfunction;Skin Diseases;Hypercholesterolemia;Pseudolymphoma;Hearing Loss, Sensorineural;Acute Lung Injury;Empyema, Tuberculous;Urinary Tract Infections;Pleurisy;Dermatitis, Allergic Contact;Serum Sickness;Acromegaly;Adenocarcinoma;Keratosis, Actinic;Colitis, Ulcerative;Anemia, Refractory;Leukemia, Erythroblastic, Acute;Confusion;Multiple Endocrine Neoplasia Type 2a;Candidiasis;Hyperalgesia;Teratoma;Leukemia, Myeloid;Hepatitis C, Chronic;Lymphoma, Mantle-Cell;Cholestasis, Extrahepatic;Glomerulonephritis;Bronchitis, Chronic;Abruptio Placentae;Endolymphatic Hydrops;Tuberculosis, Lymph Node;Aneurysm;Nephritis;Amyloidosis;Scoliosis;Meningitis;Tetanus;Synovitis;Cervical Intraepithelial Neoplasia;Cholecystitis;Leukocytosis;Photosensitivity Disorders;Menorrhagia;Hepatic Encephalopathy;peroxidase deficiency;Hemangioma;Contracture;Hemangioblastoma;Anemia, Pernicious;Foot-and-Mouth Disease;Paget's Disease, Mammary;Cholestasis;Pituitary Neoplasms;Trichinellosis;Acne Vulgaris;Brain Injuries;Tachycardia;Glomerulonephritis, Membranous;Fluorosis, Dental;Toxoplasmosis;Nasal Polyps;Amyotrophic Lateral Sclerosis;Paramyxoviridae Infections;Vaccinia;Tremor;Mycoses;Sarcoma, Myeloid;Pneumonia;Vascular System Injuries;Retinal Degeneration;Myocarditis;Leukemia, Mast-Cell;Fetal Diseases;Enteritis;unspecific monooxygenase deficiency;Crigler-Najjar Syndrome;Adenoma, Pleomorphic;Classical Swine Fever;Mercury Poisoning;Giant Cell Tumors;Stomach Ulcer;Parapsoriasis;Kidney Failure, Chronic;Sarcoidosis, Pulmonary;Influenza in Birds;Pancreatitis, Chronic;Silicotuberculosis;Sepsis;Goiter, Nodular;Aleutian Mink Disease;Neurofibrosarcoma;Friedreich Ataxia;Radicular Cyst;Carcinoid Tumor;Encephalitis, Tick-Borne;Sarcoma;Adrenocortical Carcinoma;myeloperoxidase deficiency;Distemper;Leukoencephalopathies;Papilloma, Choroid Plexus;Scrapie;Diabetic Retinopathy;Urinary Bladder Neoplasms;Lung Neoplasms;Rubella;Hemosiderosis;Parotitis;Beriberi;Purpura, Thrombocytopenic, Idiopathic;Addison Disease;Bowen's Disease;Hodgkin Disease;Tuberculosis;Hemoglobinuria;Pleuropneumonia;Hypogonadism;Typhoid Fever;Dental Deposits;iodide peroxidase deficiency;Paralysis;Renal Insufficiency;Neurofibroma;Cryptorchidism;Dengue;Angina, Stable;Drug-Induced Liver Injury;Churg-Strauss Syndrome;Paragonimiasis;Albinism;Gastritis;Hypersensitivity, Immediate;Rhinitis;Anemia, Sickle Cell;Dehydration;Carcinoma, Intraductal, Noninfiltrating;Hypertension;Prostatic Hyperplasia;Herpes Simplex;Lymphatic Diseases;Subarachnoid Hemorrhage;Cerebrovascular Disorders;Idiopathic Pulmonary Fibrosis;Dermatitis, Atopic;Pleural Effusion;Myxoma;Pre-Eclampsia;Rotavirus Infections;Vitiligo;Adenocarcinoma, Papillary;Lung Diseases, Interstitial;Empyema;Syphilis;Wound Infection;Coronary Artery Disease;Thyroiditis, Autoimmune;Leukemia, Monocytic, Acute;Pain;Neuroma;Leukemia;Infection;Hyperparathyroidism;Leukoplakia;Dent Disease;Cushing Syndrome;Carcinoma, Endometrioid;Phyllodes Tumor;Cerebral Hemorrhage;Pneumoconiosis;Carcinoma, Renal Cell;Glaucoma;Gas Gangrene;Ophthalmoplegia;Polycythemia Vera;Purpura;Leukoplakia, Hairy;Liver Cirrhosis, Alcoholic;Myocardial Infarction;Parkinson Disease;Astrocytoma;Optic Nerve Injuries;Subacute Sclerosing Panencephalitis;Hyperlipidemias;Leukemia, Myelomonocytic, Acute;Fibroadenoma;Cholelithiasis;Favism;Abscess;Bronchiectasis;Asthma;Hashimoto Disease;Spondylarthropathies;Liver Failure, Acute;Peripheral Nerve Injuries;Vascular Diseases;Border Disease;Thyroiditis;Ependymoma;Pythiosis;Parkinsonian Disorders;Fascioliasis;Polycystic Ovary Syndrome;Wasting Syndrome;Liver Cirrhosis, Biliary;Craniocerebral Trauma;Stomach Neoplasms;Lichen Planus;Bronchial Spasm;Tuberculosis, Pulmonary;Heart Failure;Connective Tissue Diseases;Lung Diseases;Liver Diseases;Wegener Granulomatosis;Mycoplasma Infections;Precursor Cell Lymphoblastic Leukemia-Lymphoma;Leishmaniasis, Mucocutaneous;Hyperhomocysteinemia;Down Syndrome;Abortion, Spontaneous;Intracranial Hemorrhage, Hypertensive;Facial Nerve Injuries;Postpartum Thyroiditis;Medulloblastoma;Hepatitis E;Giardiasis;Hepatitis A;Hepatitis B;Hepatitis C;Thyroid Diseases;Fibrosarcoma;Brain Diseases;Duodenal Ulcer;Diabetes Insipidus;Hemangiosarcoma;Glioma;Peptic Ulcer;Polyneuropathies;Myositis;Stroke;Bacterial Infections;Proteinuria;Hemorrhagic Fever with Renal Syndrome;Intracranial Hemorrhages;catalase deficiency;Rinderpest;Lymphoproliferative Disorders;Acute Kidney Injury;Unconsciousness;Periodontal Diseases;Marek Disease;Chickenpox;Cerebral Palsy;Spinal Cord Compression;Aspergillosis;Thyroiditis, Subacute;Hyperemesis Gravidarum;Rabies;Lupus Erythematosus, Discoid;Leukemia, Myeloid, Acute;Primary Myelofibrosis;Nematode Infections;Cystitis;Muscular Dystrophies;Tuberculosis, Osteoarticular;Foot Ulcer;Cardiomyopathy, Hypertrophic;Acrospiroma;Primary Ovarian Insufficiency;Pyelonephritis;Nephrotic Syndrome;Multiple Myeloma;Olivopontocerebellar Atrophies;Keratoconjunctivitis Sicca;Pneumonia, Pneumocystis;Diffuse Axonal Injury;Peste-des-Petits-Ruminants;Diphtheria;Demyelinating Diseases;Wilms Tumor;Anemia, Hypochromic;Toxoplasmosis, Cerebral;Granulomatous Disease, Chronic;Crohn Disease;Gingival Diseases;Encephalomyelitis;Rheumatic Heart Disease;Chorea;Skin Neoplasms;Malaria, Cerebral;Colonic Neoplasms;Hypothyroidism;Histoplasmosis;Anemia;Arthritis, Reactive;Meningioma;Endocarditis;Eosinophilic Granuloma;Neurodegenerative Diseases;Reproductive Tract Infections;Sarcoma, Clear Cell;Brucellosis, Bovine;Stomatitis;Carcinoma, Embryonal;Keratosis, Seborrheic;Neuroblastoma;Adenoma;Histiocytosis, Langerhans-Cell;Prolactinoma;Oropharyngeal Neoplasms;Leukopenia;Inflammatory Bowel Diseases;Dengue Hemorrhagic Fever;Histiocytic Sarcoma;Hydrocephalus;Peritonitis;Blast Crisis;Pseudorabies;Encephalitis, Herpes Simplex;Echinococcosis;Fallopian Tube Diseases;Neuritis;Otitis Media;Heart Arrest;Leukemia, Prolymphocytic;Small Cell Lung Carcinoma;Hyponatremia;Seizures;Klinefelter Syndrome;Otosclerosis;Nevus, Intradermal;Head and Neck Neoplasms;Peripheral Nervous System Diseases;Plasmacytoma;Diabetic Foot;Tularemia;Retinoblastoma;Pregnancy, Prolonged;Neoplasm Metastasis;Embolism, Air;Neurofibromatoses;Mucositis;Drug Hypersensitivity;Tuberous Sclerosis;Brain Ischemia;Measles;Opportunistic Infections;Niemann-Pick Diseases;Spinal Cord Injuries;Coma;Colitis;Acatalasia;Colorectal Neoplasms;Stupor;Diabetes Mellitus, Experimental;Pemphigoid, Bullous;Carcinoma, Ductal;Influenza, Human;Nephrosis;Telangiectasis;Uveitis;Cataract;Enzootic Bovine Leukosis;Lung Injury;Hypersensitivity, Delayed;Deafness;Hemoglobinopathies;Ureteral Obstruction;Lymphoma, B-Cell;Fibrocystic Breast Disease;Dental Plaque;Ganglion Cysts;Lymphoma, Non-Hodgkin;Diabetic Nephropathies;Thyroid Neoplasms;Burkitt Lymphoma;Schistosomiasis mansoni;Dermatitis;Polycythemia;Rhabdomyosarcoma;Arthus Reaction;Cysticercosis;Endotoxemia;Graves Disease;Hematuria;Carcinoma, Papillary;Pyuria;Goiter;Hematologic Diseases;Lupus Nephritis;Arthritis;Myelitis;Leishmaniasis, Cutaneous;Spinocerebellar Degenerations;Insulin Resistance;Erythema;Lipodystrophy;Leishmaniasis, Visceral;Lymphoma;Diabetes Mellitus, Type 1;Diabetes Mellitus, Type 2;Blister;Uterine Cervicitis;Keratoacanthoma;Infertility;Leukemia, Lymphocytic, Chronic, B-Cell;Nephroma, Mesoblastic;Coinfection;Rhinitis, Allergic, Seasonal;Malnutrition;Carcinoma, Large Cell;Ameloblastoma;Gout;Leukemia, Biphenotypic, Acute;Keratoconus;Muscular Dystrophy, Duchenne;Giant Lymph Node Hyperplasia;Osteosarcoma;Blastomycosis;Brain Edema;Glioblastoma;Keratitis, Herpetic;Hypotension, Orthostatic;Gingivitis;Potassium Deficiency;Lymphedema;Carcinoma, Basal Cell;Warts;Urticaria;Heart Diseases", 2, "placenta;heart;leukocyte;spleen;thymus;small intestine;colon;liver;cervical mucus;lung;blood vessel wall;neutrophil;pancreas", "", "Metabolism", "", "1.11.1.7", "Phenylalanine metabolism;Phenylpropanoid biosynthesis;Biosynthesis of secondary metabolites;betanidin degradation;Microbial metabolism in diverse environments;Metabolic pathways;Methane metabolism", "cytoplasm" },
            { "4025", "7316", "", 1, "", "High", "PPI", "IDs:21139048" },
            { "4025", "HMDB13070", "Carcinoma, Lewis Lung;Fanconi Anemia;Azotemia;Lupus Erythematosus, Systemic;Anemia, Hemolytic;HIV Infections;Dermatitis Herpetiformis;Hearing Loss;Cholecystitis, Acute;Thrombophilia;Glomerulonephritis, IGA;Anetoderma;Carcinoma, Ehrlich Tumor;Ichthyosis Vulgaris;Plague;Chronic Periodontitis;Hypotension;Hydatidiform Mole;Respiratory Tract Infections;Prostatic Neoplasms;Hypoglycemia;Thyroid Nodule;Sinusitis;Xerostomia;Common Variable Immunodeficiency;Head Injuries, Closed;Embolism;Neuralgia;Neoplasms, Squamous Cell;Kernicterus;Renal Insufficiency, Chronic;Anemia, Iron-Deficiency;Epilepsy;Stomach Diseases;Congenital Hypothyroidism;Decompression Sickness;glutathione peroxidase deficiency;Mumps;Hepatitis, Chronic;Paraganglioma;Vasculitis;Tuberculosis, Pleural;Ventricular Fibrillation;Herpes Zoster;Macular Degeneration;Carcinoma 256, Walker;Whooping Cough;Hypertension, Pulmonary;Schistosomiasis;Milk Hypersensitivity;Carcinoma in Situ;Bronchopneumonia;Hyperthyroidism;Carcinoma, Neuroendocrine;Celiac Disease;Insulinoma;Tuberculosis, Meningeal;Lymphoma, T-Cell, Cutaneous;Bradycardia;Pulpitis;Keratosis;Anemia, Refractory, with Excess of Blasts;Papilloma;Heart Neoplasms;Anemia, Aplastic;Metrorrhagia;Equine Infectious Anemia;Arthritis, Experimental;Thrombocytopenia;Melanoma;Mucocele;Dental Pulp Calcification;Spondylitis, Ankylosing;Cardiovascular Diseases;Granular Cell Tumor;Reperfusion Injury;Lymphopenia;Exanthema;Pheochromocytoma;Neoplasms, Glandular and Epithelial;Agranulocytosis;Cholera;Sialadenitis;Varicocele;Breast Neoplasms;Choriocarcinoma;Gastroenteritis;Odontogenic Cysts;Liver Cirrhosis;Striatonigral Degeneration;Rosacea;Cholesteatoma;Hyperaldosteronism;Carcinoma, Squamous Cell;Cerebral Amyloid Angiopathy;Pemphigus;Limbic Encephalitis;Prenatal Injuries;Death, Sudden, Cardiac;Mastitis;Food Hypersensitivity;beta-Thalassemia;Systemic Vasculitis;Carcinosarcoma;Adenoma, Oxyphilic;Cholangiocarcinoma;Chediak-Higashi Syndrome;Keloid;Ovarian Neoplasms;Infertility, Male;Shock, Septic;Periodontitis;Cryptococcosis;Liver Neoplasms, Experimental;Hypoproteinemia;Myeloproliferative Disorders;Vitamin E Deficiency;Neuritis, Autoimmune, Experimental;Carcinoma, Hepatocellular;Mouth Neoplasms;Leiomyoma;Rhabdomyosarcoma, Embryonal;Nervous System Diseases;Intracranial Arteriosclerosis;Leukoencephalopathy, Progressive Multifocal;Thyrotoxicosis;Silicosis;Hirschsprung Disease;Facial Paralysis;Plant Diseases;Cardiomegaly;Adenocarcinoma, Mucinous;Paget Disease, Extramammary;Myocardial Ischemia;Oral Ulcer;Leukostasis;Carcinoma, Transitional Cell;Adrenocortical Adenoma;Multiple Sclerosis;Cardiomyopathy, Dilated;Olfactory Nerve Injuries;Optic Neuritis;Craniopharyngioma;Immunoblastic Lymphadenopathy;Melanoma, Experimental;Retinal Diseases;Thalassemia;Fasciitis;Porphyrias;Retinal Detachment;Pelvic Inflammatory Disease;Fabry Disease;Fetal Resorption;Encephalitis;Lymphadenitis;Phenylketonurias;Alveolitis, Extrinsic Allergic;Sarcoidosis;Mercury Poisoning, Nervous System;Aggressive Periodontitis;Pulmonary Eosinophilia;Oculomotor Nerve Injuries;Rheumatic Diseases;Dentigerous Cyst;Carcinoma, Acinar Cell;Encephalomyelitis, Autoimmune, Experimental;Myelodysplastic Syndromes;Activated Protein C Resistance;Mastocytosis, Systemic;Psoriasis;Chronic Pain;Fetal Growth Retardation;Lymphoma, T-Cell;Phlebitis;Gastritis, Atrophic;Common Cold;Leukemia, Myelogenous, Chronic, BCR-ABL Positive;Kidney Neoplasms;Gyrate Atrophy;Esophageal Neoplasms;Anaphylaxis;Eye Injuries;Hematologic Neoplasms;Parathyroid Neoplasms;Lyme Disease;Adenocarcinoma, Bronchiolo-Alveolar;Thrombosis;Heart Rupture;Cytomegalovirus Infections;Diabetic Neuropathies;Leprosy;Cell Transformation, Neoplastic;Rocky Mountain Spotted Fever;Diabetes Mellitus;Obesity;Paratuberculosis;Pancreatic Neoplasms;Brain Neoplasms;Sarcoma, Avian;Vesicular Stomatitis;Keratitis;Acquired Immunodeficiency Syndrome;Dental Caries;Leptospirosis;Iron Overload;Hypereosinophilic Syndrome;Rheumatic Nodule;Thymoma;Scleroderma, Limited;Leukemia, Hairy Cell;Coccidioidomycosis;Hepatitis;Neutropenia;Uremia;Gigantism;Malaria;Mesothelioma;Urticaria Pigmentosa;Granuloma;Chagas Disease;Carcinoma;Myasthenia Gravis;Endometrial Neoplasms;Larva Migrans;Memory Disorders;Strabismus;Varicose Veins;Angina Pectoris;Esotropia;Alkaptonuria;Syringomyelia;Eosinophilia;Arthritis, Rheumatoid;Newcastle Disease;Endometriosis;Goiter, Endemic;Alzheimer Disease;Red-Cell Aplasia, Pure;Neoplasms;Nevus, Pigmented;Leiomyosarcoma;Ataxia Telangiectasia;Papilledema;Infarction, Middle Cerebral Artery;Laryngeal Neoplasms;Hypertrophy, Left Ventricular;Carcinoma, Small Cell;Leukemia, Megakaryoblastic, Acute;Airway Obstruction;Hypersensitivity;Pulmonary Disease, Chronic Obstructive;Fusariosis;Cysts;Coronary Occlusion;Starvation;Atherosclerosis;Mycosis Fungoides;Tooth Injuries;Condylomata Acuminata;Endomyocardial Fibrosis;Osteoarthritis;Aphasia;Cystic Fibrosis;Leprosy, Lepromatous;Warm Ischemia;Tonsillitis;Neoplasm Micrometastasis;Leukemia, Promyelocytic, Acute;Hyperglycemia;Autoimmune Diseases;Meningoencephalitis;Candidiasis, Oral;Gangliosidosis, GM1;Neuronal Ceroid-Lipofuscinoses;Craniofacial Abnormalities;Pancreatitis;Reoviridae Infections;Adrenocortical Hyperfunction;Skin Diseases;Hypercholesterolemia;Pseudolymphoma;Hearing Loss, Sensorineural;Acute Lung Injury;Empyema, Tuberculous;Urinary Tract Infections;Pleurisy;Dermatitis, Allergic Contact;Serum Sickness;Acromegaly;Adenocarcinoma;Keratosis, Actinic;Colitis, Ulcerative;Anemia, Refractory;Leukemia, Erythroblastic, Acute;Confusion;Multiple Endocrine Neoplasia Type 2a;Candidiasis;Hyperalgesia;Teratoma;Leukemia, Myeloid;Hepatitis C, Chronic;Lymphoma, Mantle-Cell;Cholestasis, Extrahepatic;Glomerulonephritis;Bronchitis, Chronic;Abruptio Placentae;Endolymphatic Hydrops;Tuberculosis, Lymph Node;Aneurysm;Nephritis;Amyloidosis;Scoliosis;Meningitis;Tetanus;Synovitis;Cervical Intraepithelial Neoplasia;Cholecystitis;Leukocytosis;Photosensitivity Disorders;Menorrhagia;Hepatic Encephalopathy;peroxidase deficiency;Hemangioma;Contracture;Hemangioblastoma;Anemia, Pernicious;Foot-and-Mouth Disease;Paget's Disease, Mammary;Cholestasis;Pituitary Neoplasms;Trichinellosis;Acne Vulgaris;Brain Injuries;Tachycardia;Glomerulonephritis, Membranous;Fluorosis, Dental;Toxoplasmosis;Nasal Polyps;Amyotrophic Lateral Sclerosis;Paramyxoviridae Infections;Vaccinia;Tremor;Mycoses;Sarcoma, Myeloid;Pneumonia;Vascular System Injuries;Retinal Degeneration;Myocarditis;Leukemia, Mast-Cell;Fetal Diseases;Enteritis;unspecific monooxygenase deficiency;Crigler-Najjar Syndrome;Adenoma, Pleomorphic;Classical Swine Fever;Mercury Poisoning;Giant Cell Tumors;Stomach Ulcer;Parapsoriasis;Kidney Failure, Chronic;Sarcoidosis, Pulmonary;Influenza in Birds;Pancreatitis, Chronic;Silicotuberculosis;Sepsis;Goiter, Nodular;Aleutian Mink Disease;Neurofibrosarcoma;Friedreich Ataxia;Radicular Cyst;Carcinoid Tumor;Encephalitis, Tick-Borne;Sarcoma;Adrenocortical Carcinoma;myeloperoxidase deficiency;Distemper;Leukoencephalopathies;Papilloma, Choroid Plexus;Scrapie;Diabetic Retinopathy;Urinary Bladder Neoplasms;Lung Neoplasms;Rubella;Hemosiderosis;Parotitis;Beriberi;Purpura, Thrombocytopenic, Idiopathic;Addison Disease;Bowen's Disease;Hodgkin Disease;Tuberculosis;Hemoglobinuria;Pleuropneumonia;Hypogonadism;Typhoid Fever;Dental Deposits;iodide peroxidase deficiency;Paralysis;Renal Insufficiency;Neurofibroma;Cryptorchidism;Dengue;Angina, Stable;Drug-Induced Liver Injury;Churg-Strauss Syndrome;Paragonimiasis;Albinism;Gastritis;Hypersensitivity, Immediate;Rhinitis;Anemia, Sickle Cell;Dehydration;Carcinoma, Intraductal, Noninfiltrating;Hypertension;Prostatic Hyperplasia;Herpes Simplex;Lymphatic Diseases;Subarachnoid Hemorrhage;Cerebrovascular Disorders;Idiopathic Pulmonary Fibrosis;Dermatitis, Atopic;Pleural Effusion;Myxoma;Pre-Eclampsia;Rotavirus Infections;Vitiligo;Adenocarcinoma, Papillary;Lung Diseases, Interstitial;Empyema;Syphilis;Wound Infection;Coronary Artery Disease;Thyroiditis, Autoimmune;Leukemia, Monocytic, Acute;Pain;Neuroma;Leukemia;Infection;Hyperparathyroidism;Leukoplakia;Dent Disease;Cushing Syndrome;Carcinoma, Endometrioid;Phyllodes Tumor;Cerebral Hemorrhage;Pneumoconiosis;Carcinoma, Renal Cell;Glaucoma;Gas Gangrene;Ophthalmoplegia;Polycythemia Vera;Purpura;Leukoplakia, Hairy;Liver Cirrhosis, Alcoholic;Myocardial Infarction;Parkinson Disease;Astrocytoma;Optic Nerve Injuries;Subacute Sclerosing Panencephalitis;Hyperlipidemias;Leukemia, Myelomonocytic, Acute;Fibroadenoma;Cholelithiasis;Favism;Abscess;Bronchiectasis;Asthma;Hashimoto Disease;Spondylarthropathies;Liver Failure, Acute;Peripheral Nerve Injuries;Vascular Diseases;Border Disease;Thyroiditis;Ependymoma;Pythiosis;Parkinsonian Disorders;Fascioliasis;Polycystic Ovary Syndrome;Wasting Syndrome;Liver Cirrhosis, Biliary;Craniocerebral Trauma;Stomach Neoplasms;Lichen Planus;Bronchial Spasm;Tuberculosis, Pulmonary;Heart Failure;Connective Tissue Diseases;Lung Diseases;Liver Diseases;Wegener Granulomatosis;Mycoplasma Infections;Precursor Cell Lymphoblastic Leukemia-Lymphoma;Leishmaniasis, Mucocutaneous;Hyperhomocysteinemia;Down Syndrome;Abortion, Spontaneous;Intracranial Hemorrhage, Hypertensive;Facial Nerve Injuries;Postpartum Thyroiditis;Medulloblastoma;Hepatitis E;Giardiasis;Hepatitis A;Hepatitis B;Hepatitis C;Thyroid Diseases;Fibrosarcoma;Brain Diseases;Duodenal Ulcer;Diabetes Insipidus;Hemangiosarcoma;Glioma;Peptic Ulcer;Polyneuropathies;Myositis;Stroke;Bacterial Infections;Proteinuria;Hemorrhagic Fever with Renal Syndrome;Intracranial Hemorrhages;catalase deficiency;Rinderpest;Lymphoproliferative Disorders;Acute Kidney Injury;Unconsciousness;Periodontal Diseases;Marek Disease;Chickenpox;Cerebral Palsy;Spinal Cord Compression;Aspergillosis;Thyroiditis, Subacute;Hyperemesis Gravidarum;Rabies;Lupus Erythematosus, Discoid;Leukemia, Myeloid, Acute;Primary Myelofibrosis;Nematode Infections;Cystitis;Muscular Dystrophies;Tuberculosis, Osteoarticular;Foot Ulcer;Cardiomyopathy, Hypertrophic;Acrospiroma;Primary Ovarian Insufficiency;Pyelonephritis;Nephrotic Syndrome;Multiple Myeloma;Olivopontocerebellar Atrophies;Keratoconjunctivitis Sicca;Pneumonia, Pneumocystis;Diffuse Axonal Injury;Peste-des-Petits-Ruminants;Diphtheria;Demyelinating Diseases;Wilms Tumor;Anemia, Hypochromic;Toxoplasmosis, Cerebral;Granulomatous Disease, Chronic;Crohn Disease;Gingival Diseases;Encephalomyelitis;Rheumatic Heart Disease;Chorea;Skin Neoplasms;Malaria, Cerebral;Colonic Neoplasms;Hypothyroidism;Histoplasmosis;Anemia;Arthritis, Reactive;Meningioma;Endocarditis;Eosinophilic Granuloma;Neurodegenerative Diseases;Reproductive Tract Infections;Sarcoma, Clear Cell;Brucellosis, Bovine;Stomatitis;Carcinoma, Embryonal;Keratosis, Seborrheic;Neuroblastoma;Adenoma;Histiocytosis, Langerhans-Cell;Prolactinoma;Oropharyngeal Neoplasms;Leukopenia;Inflammatory Bowel Diseases;Dengue Hemorrhagic Fever;Histiocytic Sarcoma;Hydrocephalus;Peritonitis;Blast Crisis;Pseudorabies;Encephalitis, Herpes Simplex;Echinococcosis;Fallopian Tube Diseases;Neuritis;Otitis Media;Heart Arrest;Leukemia, Prolymphocytic;Small Cell Lung Carcinoma;Hyponatremia;Seizures;Klinefelter Syndrome;Otosclerosis;Nevus, Intradermal;Head and Neck Neoplasms;Peripheral Nervous System Diseases;Plasmacytoma;Diabetic Foot;Tularemia;Retinoblastoma;Pregnancy, Prolonged;Neoplasm Metastasis;Embolism, Air;Neurofibromatoses;Mucositis;Drug Hypersensitivity;Tuberous Sclerosis;Brain Ischemia;Measles;Opportunistic Infections;Niemann-Pick Diseases;Spinal Cord Injuries;Coma;Colitis;Acatalasia;Colorectal Neoplasms;Stupor;Diabetes Mellitus, Experimental;Pemphigoid, Bullous;Carcinoma, Ductal;Influenza, Human;Nephrosis;Telangiectasis;Uveitis;Cataract;Enzootic Bovine Leukosis;Lung Injury;Hypersensitivity, Delayed;Deafness;Hemoglobinopathies;Ureteral Obstruction;Lymphoma, B-Cell;Fibrocystic Breast Disease;Dental Plaque;Ganglion Cysts;Lymphoma, Non-Hodgkin;Diabetic Nephropathies;Thyroid Neoplasms;Burkitt Lymphoma;Schistosomiasis mansoni;Dermatitis;Polycythemia;Rhabdomyosarcoma;Arthus Reaction;Cysticercosis;Endotoxemia;Graves Disease;Hematuria;Carcinoma, Papillary;Pyuria;Goiter;Hematologic Diseases;Lupus Nephritis;Arthritis;Myelitis;Leishmaniasis, Cutaneous;Spinocerebellar Degenerations;Insulin Resistance;Erythema;Lipodystrophy;Leishmaniasis, Visceral;Lymphoma;Diabetes Mellitus, Type 1;Diabetes Mellitus, Type 2;Blister;Uterine Cervicitis;Keratoacanthoma;Infertility;Leukemia, Lymphocytic, Chronic, B-Cell;Nephroma, Mesoblastic;Coinfection;Rhinitis, Allergic, Seasonal;Malnutrition;Carcinoma, Large Cell;Ameloblastoma;Gout;Leukemia, Biphenotypic, Acute;Keratoconus;Muscular Dystrophy, Duchenne;Giant Lymph Node Hyperplasia;Osteosarcoma;Blastomycosis;Brain Edema;Glioblastoma;Keratitis, Herpetic;Hypotension, Orthostatic;Gingivitis;Potassium Deficiency;Lymphedema;Carcinoma, Basal Cell;Warts;Urticaria;Heart Diseases", 2, "placenta;heart;leukocyte;spleen;thymus;small intestine;colon;liver;cervical mucus;lung;blood vessel wall;neutrophil;pancreas", "", "Metabolism", "", "1.11.1.7", "Phenylalanine metabolism;Phenylpropanoid biosynthesis;Biosynthesis of secondary metabolites;betanidin degradation;Microbial metabolism in diverse environments;Metabolic pathways;Methane metabolism", "cytoplasm" },
            { "84913", "6929", "", 1, "", "High", "PPI", "IDs:22354994" },
            { "84913", "1577", "", 1, "", "High", "PPI", "IDs:21988832" },
            { "28998", "5018", "", 2, "", "Low", "PPI", "IDs:20601428" },
            { "28998", "1738", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "64975", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "64978", "", 1, "", "High", "PPI", "IDs:22939629" }
        };

        return objarray;

    }

    private static Object[][] getObjArrayPart5(){
                 
        final Object[][] objarray = {
            { "28998", "4967", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "65003", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "65005", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "65080", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "84311", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "5250", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "29088", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "54148", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "23256", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "2335", "", 1, "", "High", "PPI", "IDs:19738201" }
        };

        return objarray;

    }

    private static Object[][] getObjArrayPart6(){
                 
        final Object[][] objarray = {
            { "28998", "6150", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "64965", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "3396", "", 2, "", "High", "PPI", "IDs:20186120;22939629" },
            { "28998", "7316", "", 2, "", "High", "PPI", "IDs:21139048;21890473" },
            { "28998", "4719", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "64928", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "3192", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "28957", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "28998", "28977", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "55290", "9131", "", 1, "", "High", "PPI", "IDs:17353931" }
        };

        return objarray;

    }

    private static Object[][] getObjArrayPart7(){
                 
        final Object[][] objarray = {
            { "55290", "54726", "", 1, "", "High", "PPI", "IDs:17353931" },
            { "55290", "5825", "", 1, "", "High", "PPI", "IDs:17353931" },
            { "55290", "8813", "", 1, "", "High", "PPI", "IDs:17353931" },
            { "55290", "10985", "", 1, "", "High", "PPI", "IDs:17353931" },
            { "55290", "55627", "", 1, "", "High", "PPI", "IDs:17353931" },
            { "55290", "7316", "", 1, "", "High", "PPI", "IDs:21906983" },
            { "55290", "4141", "", 1, "", "High", "PPI", "IDs:17353931" },
            { "55290", "55215", "", 1, "", "High", "PPI", "IDs:17353931" },
            { "55290", "6520", "", 1, "", "High", "PPI", "IDs:17353931" },
            { "55290", "50628", "", 1, "", "High", "PPI", "IDs:17353931" }
        };

        return objarray;

    }

    private static Object[][] getObjArrayPart8(){
                 
        final Object[][] objarray = {
            { "55290", "23039", "", 1, "", "High", "PPI", "IDs:17353931" },
            { "55290", "23451", "", 1, "", "High", "PPI", "IDs:17353931" },
            { "55290", "51495", "", 1, "", "High", "PPI", "IDs:17353931" },
            { "55290", "22820", "", 1, "", "High", "PPI", "IDs:17353931" },
            { "55290", "6908", "", 2, "", "Low", "PPI", "IDs:11564744" },
            { "55291", "7316", "", 6, "", "High", "PPI", "IDs:21890473;21963094;22053931;21139048;21987572;21906983" },
            { "55291", "79885", "", 1, "", "High", "PPI", "IDs:23752268" },
            { "55291", "2869", "", 1, "", "High", "PPI", "IDs:22952844" },
            { "55291", "22870", "", 1, "", "High", "PPI", "IDs:24255178" },
            { "55291", "3066", "", 2, "", "Low", "PPI", "IDs:9885572" }
        };

        return objarray;

    }

    private static Object[][] getObjArrayPart9(){
                 
        final Object[][] objarray = {
            { "55291", "3065", "", 2, "", "Low", "PPI", "IDs:9885572" },
            { "55291", "1894", "", 1, "", "High", "PPI", "IDs:22990118" },
            { "55291", "1891", "", 1, "", "High", "PPI", "IDs:24255178" },
            { "55291", "23243", "", 3, "", "High", "PPI", "IDs:24255178" },
            { "55291", "5198", "", 1, "", "High", "PPI", "IDs:22939629" },
            { "55291", "26277", "", 1, "", "High", "PPI", "IDs:21044950" },
            { "55291", "25862", "", 1, "", "High", "PPI", "IDs:19615732" },
            { "55291", "54386", "", 1, "", "High", "PPI", "IDs:21044950" },
            { "55291", "9113", "", 2, "", "Low", "PPI", "IDs:22641346" },
            { "55291", "672", "", 1, "", "High", "PPI", "IDs:22990118" }
        };

        return objarray;

    }

    private static Object[][] getObjArrayPart10(){
                 
        final Object[][] objarray = {
            { "55291", "7023", "", 1, "", "High", "PPI", "IDs:19505873" },
            { "55291", "5537", "", 6, "", "High", "PPI", "IDs:20065038;24255178;22939629;21187329" }
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
