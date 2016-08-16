/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.icerestapijava.DOM;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author prash
 */
public class AccessToken {
    
    @Getter
    private final String email;
    
    @Getter
    private String sessionId;
    
    @Getter
    private final String firstName;
    
    @Getter
    private final String lastName;
    
    @Getter
    private final boolean isAdmin;
    
    @Getter
    private final int newMessageCount;
   
    @Getter
    private final String institution;
    
    @Getter
    private final String description;
    
    @Getter
    private final String accountType;
    
    
    public AccessToken(String _email, String _sessionId, String _firstName, String _lastName, String _institution, String _description, String _accountType, boolean _isAdmin, int _newMessageCount){
        this.email = _email;
        this.sessionId = _sessionId;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.isAdmin = _isAdmin;
        this.accountType = _accountType;
        this.description = _description;
        this.institution = _institution;
        this.newMessageCount = _newMessageCount;
    }
    
    public AccessToken(String _email, String _firstName, String _lastName, String _institution, String _description, String _accountType, boolean _isAdmin, int _newMessageCount){
        this.email = _email;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.isAdmin = _isAdmin;
        this.accountType = _accountType;
        this.description = _description;
        this.institution = _institution;
        this.newMessageCount = _newMessageCount;
    }
    
    
}
