/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.icerestapijava;

import org.cidarlab.icerestapijava.DOM.AccessToken;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author prash
 */
public class ICETest {
    
    public ICETest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testICEFlow(){
        ICE ice = new ICE();
        AccessToken token = ice.createAccessToken("prash@bu.edu", "supersafepassword");
        System.out.println(token.getSessionId());
        //ice.getAccessToken(token.getSessionId());
        ice.getPart(token.getSessionId(), "JPUB_007685");
        /*ice.createPart(token.getSessionId(), "{\n" +
"        type: 'PLASMID',\n" +
"        name: \"pNJH00010\",\n" +
"        alias: \"pS8c-gfpuv_sigpep\",\n" +
"        status: 'Complete',\n" +
"        shortDescription: \"gpfuv with a cterminal peptide tag for beta-carboxysome localization (as constructed by Jay Kinney in Cheryl Kerfeld's lab) under the control of PBAD. The BioBrick sites have been removed, in anticipation of further construction steps.\",\n" +
"        references: \"Hillson, N.J., Rosengarten, R.D., and Keasling J.D. (2012) j5 DNA Assembly Design Automation Software. ACS Synthetic Biology 1 (1), 14-21. DOI: 10.1021/sb2000116\",\n" +
"        bioSafetyLevel: 1,\n" +
"        principalInvestigator: \"Nathan J Hillson\",\n" +
"        selectionMarkers: [\"Chloramphenicol\"],\n" +
"        fundingSource: \"DE-AC02-05CH11231\",\n" +
"        plasmidData: {\n" +
"            backbone: \"pBbS8c\",\n" +
"            originOfReplication: \"pSC101\",\n" +
"            promoters: \"Arabinose\",\n" +
"            circular: true\n" +
"        }");*/
        
        ice.getPartPermissions(token.getSessionId(), "JPUB_007685");
    }    
    
}
