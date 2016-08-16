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
    }    
    
}
