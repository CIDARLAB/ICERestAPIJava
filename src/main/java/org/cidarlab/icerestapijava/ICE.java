/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.icerestapijava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import lombok.Getter;
import org.cidarlab.icerestapijava.DOM.AccessToken;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author prash
 */
public class ICE {

    @Getter
    private String url = "https://public-registry.jbei.org";

    public ICE() {
        String url = "https://public-registry.jbei.org";
    }

    public ICE(String endpointURL) {
        while (endpointURL.endsWith("/")) {
            endpointURL = endpointURL.substring(0, endpointURL.length() - 2);
        }
        this.url = endpointURL;
    }

   

    //<editor-fold desc="Access Token">
    public AccessToken createAccessToken(String email, String password) {

        String inputString = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";
        
        try {
            URL url = new URL(this.url + "/rest/accesstokens");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setInstanceFollowRedirects(false);

            
            OutputStream os = conn.getOutputStream();
            os.write(inputString.getBytes());
            os.flush();

            if (conn.getResponseCode() == 200) {
                System.out.println("SUCCESS!");
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                String alloutput = "";
                while ((output = br.readLine()) != null) {
                    alloutput += output;
                }
                
                JSONObject jsonresponse = new JSONObject(alloutput);
                return new AccessToken(jsonresponse.getString("email"),jsonresponse.getString("sessionId"),jsonresponse.getString("firstName"),jsonresponse.getString("lastName"), jsonresponse.getString("institution"), jsonresponse.getString("description"), jsonresponse.getString("accountType"), jsonresponse.getBoolean("isAdmin"),jsonresponse.getInt("newMessageCount"));
            }

            if (conn.getResponseCode() == 400) {
                System.out.println("bad formatting");
                return null;
            }
            if (conn.getResponseCode() == 401) {
                System.out.println("unauthorized user");
                return null;
            }
            if (conn.getResponseCode() == 500) {
                System.out.println("Server Error");
                return null;
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public AccessToken getAccessToken(String sessionId) {

        
        try {
            URL url = new URL(this.url + "/rest/accesstokens");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("X-ICE-Authentication-SessionId", sessionId);
            conn.setInstanceFollowRedirects(false);
            
            if (conn.getResponseCode() == 200) {
                System.out.println("SUCCESS!");
                
                //print result
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                String alloutput = "";
                while ((output = br.readLine()) != null) {
                    alloutput += output;
                }
                System.out.println(alloutput);
                JSONObject jsonresponse = new JSONObject(alloutput);
                return new AccessToken(jsonresponse.getString("email"),jsonresponse.getString("firstName"),jsonresponse.getString("lastName"), jsonresponse.getString("institution"), jsonresponse.getString("description"), jsonresponse.getString("accountType"), jsonresponse.getBoolean("isAdmin"),jsonresponse.getInt("newMessageCount"));
            }

            if (conn.getResponseCode() == 400) {
                System.out.println("bad formatting");
                return null;
            }
            if (conn.getResponseCode() == 401) {
                System.out.println("unauthorized user");
                return null;
            }
            if (conn.getResponseCode() == 403) {
                System.out.println("forbidden");
                return null;
            }
            if (conn.getResponseCode() == 500) {
                System.out.println("Server Error");
                return null;
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    //</editor-fold>

    //<editor-fold desc="Part">
    
    public JSONObject getPart(String sessionId, String partId) {
        try {
            URL url = new URL(this.url + "/rest/parts/" + partId);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("X-ICE-Authentication-SessionId", sessionId);
            conn.setInstanceFollowRedirects(false);
            
            if (conn.getResponseCode() == 200) {
                System.out.println("SUCCESS!");
                
                //print result
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                String alloutput = "";
                while ((output = br.readLine()) != null) {
                    alloutput += output;
                }
                System.out.println(alloutput);
                JSONObject jsonresponse = new JSONObject(alloutput);
                return jsonresponse;
            }

            if (conn.getResponseCode() == 400) {
                System.out.println("bad formatting");
                return null;
            }
            if (conn.getResponseCode() == 401) {
                System.out.println("unauthorized user");
                return null;
            }
            if (conn.getResponseCode() == 403) {
                System.out.println("forbidden");
                return null;
            }
            if (conn.getResponseCode() == 404) {
                System.out.println("not found");
                return null;
            }
            if (conn.getResponseCode() == 500) {
                System.out.println("Server Error");
                return null;
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    
    }
    
    
    /*
    public Part getPart(String sessionId, String partId) {
        
        try {
            URL url = new URL(this.url + "/rest/parts/" + partId);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("X-ICE-Authentication-SessionId", sessionId);
            conn.setInstanceFollowRedirects(false);
            
            if (conn.getResponseCode() == 200) {
                System.out.println("SUCCESS!");
                
                //print result
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                String alloutput = "";
                while ((output = br.readLine()) != null) {
                    alloutput += output;
                }
                System.out.println(alloutput);
                JSONObject jsonresponse = new JSONObject(alloutput);
                System.out.println(jsonresponse);
                return null;
            }

            if (conn.getResponseCode() == 400) {
                System.out.println("bad formatting");
                return null;
            }
            if (conn.getResponseCode() == 401) {
                System.out.println("unauthorized user");
                return null;
            }
            if (conn.getResponseCode() == 403) {
                System.out.println("forbidden");
                return null;
            }
            if (conn.getResponseCode() == 404) {
                System.out.println("not found");
                return null;
            }
            if (conn.getResponseCode() == 500) {
                System.out.println("Server Error");
                return null;
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    */

    //</editor-fold>
    public JSONObject createPart(String sessionId, String jsonString) {
        return createPart(sessionId, "");
    }
    public JSONObject createPart(String sessionId, String source, String jsonString) {
        try {
            URL url;
            if (source != "") url = new URL(this.url + "/rest/parts/?source=" + source);
            else url = new URL(this.url + "/rest/parts");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("X-ICE-Authentication-SessionId", sessionId);
            conn.setInstanceFollowRedirects(false);
            
            OutputStream os = conn.getOutputStream();
            os.write(jsonString.getBytes());
            os.flush();
            
            if (conn.getResponseCode() == 200) {
                System.out.println("SUCCESS!");
                              
                //print result
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                String alloutput = "";
                while ((output = br.readLine()) != null) {
                    alloutput += output;
                }
                System.out.println(alloutput);
                JSONObject jsonresponse = new JSONObject(alloutput);
                return jsonresponse;
            }

            if (conn.getResponseCode() == 400) {
                System.out.println("bad formatting");
                return null;
            }
            if (conn.getResponseCode() == 401) {
                System.out.println("unauthorized user");
                return null;
            }
            if (conn.getResponseCode() == 403) {
                System.out.println("forbidden");
                return null;
            }
            if (conn.getResponseCode() == 404) {
                System.out.println("not found");
                return null;
            }
            if (conn.getResponseCode() == 500) {
                System.out.println("Server Error");
                return null;
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    public JSONObject updatePart(String sessionId, String partId, String jsonString) {
        try {
            URL url = new URL(this.url + "/rest/parts/" + partId);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("X-ICE-Authentication-SessionId", sessionId);
            conn.setInstanceFollowRedirects(false);
            
            OutputStream os = conn.getOutputStream();
            os.write(jsonString.getBytes());
            os.flush();
            
            if (conn.getResponseCode() == 200) {
                System.out.println("SUCCESS!");
                
                //print result
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                String alloutput = "";
                while ((output = br.readLine()) != null) {
                    alloutput += output;
                }
                System.out.println(alloutput);
                JSONObject jsonresponse = new JSONObject(alloutput);
                return jsonresponse;
            }

            if (conn.getResponseCode() == 400) {
                System.out.println("bad formatting");
                return null;
            }
            if (conn.getResponseCode() == 401) {
                System.out.println("unauthorized user");
                return null;
            }
            if (conn.getResponseCode() == 403) {
                System.out.println("forbidden");
                return null;
            }
            if (conn.getResponseCode() == 404) {
                System.out.println("not found");
                return null;
            }
            if (conn.getResponseCode() == 500) {
                System.out.println("Server Error");
                return null;
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public JSONArray getPartPermissions(String sessionId, String partId) {
        try {
            URL url = new URL(this.url + "/rest/parts/" + partId + "/permissions");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("X-ICE-Authentication-SessionId", sessionId);
            conn.setInstanceFollowRedirects(false);
            
            if (conn.getResponseCode() == 200) {
                System.out.println("SUCCESS!");
                
                //print result
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                String alloutput = "";
                while ((output = br.readLine()) != null) {
                    alloutput += output;
                }
                System.out.println(alloutput);
                JSONArray jsonresponse = new JSONArray(alloutput);
                return jsonresponse;
            }

            if (conn.getResponseCode() == 400) {
                System.out.println("bad formatting");
                return null;
            }
            if (conn.getResponseCode() == 401) {
                System.out.println("unauthorized user");
                return null;
            }
            if (conn.getResponseCode() == 403) {
                System.out.println("forbidden");
                return null;
            }
            if (conn.getResponseCode() == 404) {
                System.out.println("not found");
                return null;
            }
            if (conn.getResponseCode() == 500) {
                System.out.println("Server Error");
                return null;
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public JSONObject addPartPermission(String sessionId, String partId, String jsonString) {
        try {
            URL url = new URL(this.url + "/rest/parts/" + partId + "/permissions");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("X-ICE-Authentication-SessionId", sessionId);
            conn.setInstanceFollowRedirects(false);
            
            OutputStream os = conn.getOutputStream();
            os.write(jsonString.getBytes());
            os.flush();
            
            if (conn.getResponseCode() == 200) {
                System.out.println("SUCCESS!");
                
                //print result
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                String alloutput = "";
                while ((output = br.readLine()) != null) {
                    alloutput += output;
                }
                System.out.println(alloutput);
                JSONObject jsonresponse = new JSONObject(alloutput);
                return jsonresponse;
            }

            if (conn.getResponseCode() == 400) {
                System.out.println("bad formatting");
                return null;
            }
            if (conn.getResponseCode() == 401) {
                System.out.println("unauthorized user");
                return null;
            }
            if (conn.getResponseCode() == 403) {
                System.out.println("forbidden");
                return null;
            }
            if (conn.getResponseCode() == 404) {
                System.out.println("not found");
                return null;
            }
            if (conn.getResponseCode() == 500) {
                System.out.println("Server Error");
                return null;
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void removePartPermission(String sessionId, String partId, String permissionId) {
        try {
            URL url = new URL(this.url + "/rest/parts/" + partId + "/permissions/" + permissionId);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("X-ICE-Authentication-SessionId", sessionId);
            conn.setInstanceFollowRedirects(false);
            
            if (conn.getResponseCode() == 200) {
                System.out.println("SUCCESS!");
            }

            if (conn.getResponseCode() == 400) {
                System.out.println("bad formatting");
            }
            if (conn.getResponseCode() == 401) {
                System.out.println("unauthorized user");
            }
            if (conn.getResponseCode() == 403) {
                System.out.println("forbidden");
            }
            if (conn.getResponseCode() == 404) {
                System.out.println("not found");
            }
            if (conn.getResponseCode() == 500) {
                System.out.println("Server Error");
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Incorrect documenation? Same as Add part experiment?
    /*public JSONObject getPartExperiments(String sessionId, String partId, String jsonString) {
        try {
            URL url = new URL(this.url + "/rest/parts/" + partId + "/experiments");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("X-ICE-Authentication-SessionId", sessionId);
            conn.setInstanceFollowRedirects(false);
            
            OutputStream os = conn.getOutputStream();
            os.write(jsonString.getBytes());
            os.flush();
            
            if (conn.getResponseCode() == 200) {
                System.out.println("SUCCESS!");
                
                //print result
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                String alloutput = "";
                while ((output = br.readLine()) != null) {
                    alloutput += output;
                }
                System.out.println(alloutput);
                JSONObject jsonresponse = new JSONObject(alloutput);
                return jsonresponse;
            }

            if (conn.getResponseCode() == 400) {
                System.out.println("bad formatting");
                return null;
            }
            if (conn.getResponseCode() == 401) {
                System.out.println("unauthorized user");
                return null;
            }
            if (conn.getResponseCode() == 403) {
                System.out.println("forbidden");
                return null;
            }
            if (conn.getResponseCode() == 404) {
                System.out.println("not found");
                return null;
            }
            if (conn.getResponseCode() == 500) {
                System.out.println("Server Error");
                return null;
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }*/
    public JSONObject createPartExperiments(String sessionId, String partId, String jsonString) {
        try {
            URL url = new URL(this.url + "/rest/parts/" + partId + "/experiments");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("X-ICE-Authentication-SessionId", sessionId);
            conn.setInstanceFollowRedirects(false);
            
            OutputStream os = conn.getOutputStream();
            os.write(jsonString.getBytes());
            os.flush();
            
            if (conn.getResponseCode() == 200) {
                System.out.println("SUCCESS!");
                
                //print result
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                String alloutput = "";
                while ((output = br.readLine()) != null) {
                    alloutput += output;
                }
                System.out.println(alloutput);
                JSONObject jsonresponse = new JSONObject(alloutput);
                return jsonresponse;
            }

            if (conn.getResponseCode() == 400) {
                System.out.println("bad formatting");
                return null;
            }
            if (conn.getResponseCode() == 401) {
                System.out.println("unauthorized user");
                return null;
            }
            if (conn.getResponseCode() == 403) {
                System.out.println("forbidden");
                return null;
            }
            if (conn.getResponseCode() == 404) {
                System.out.println("not found");
                return null;
            }
            if (conn.getResponseCode() == 500) {
                System.out.println("Server Error");
                return null;
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    public void deletePartExperiments(String sessionId, String partId, String experimentId) {
        try {
            URL url = new URL(this.url + "/rest/parts/" + partId + "/experiments/" + experimentId);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("X-ICE-Authentication-SessionId", sessionId);
            conn.setInstanceFollowRedirects(false);
            
            if (conn.getResponseCode() == 200) {
                System.out.println("SUCCESS!");
            }

            if (conn.getResponseCode() == 400) {
                System.out.println("bad formatting");
            }
            if (conn.getResponseCode() == 401) {
                System.out.println("unauthorized user");
            }
            if (conn.getResponseCode() == 403) {
                System.out.println("forbidden");
            }
            if (conn.getResponseCode() == 404) {
                System.out.println("not found");
            }
            if (conn.getResponseCode() == 500) {
                System.out.println("Server Error");
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void linkPartToAnother(String sessionId, String partId, String linkType) {
        try {
            URL url = new URL(this.url + "/rest/parts/" + partId + "/links?linkType=");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("X-ICE-Authentication-SessionId", sessionId);
            conn.setInstanceFollowRedirects(false);
            
            if (conn.getResponseCode() == 200) {
                System.out.println("SUCCESS!");
            }

            if (conn.getResponseCode() == 400) {
                System.out.println("bad formatting");
            }
            if (conn.getResponseCode() == 401) {
                System.out.println("unauthorized user");
            }
            if (conn.getResponseCode() == 403) {
                System.out.println("forbidden");
            }
            if (conn.getResponseCode() == 404) {
                System.out.println("not found");
            }
            if (conn.getResponseCode() == 500) {
                System.out.println("Server Error");
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Incorrect Documentation
    //public void removeLinkBetweenParts(String sessionId, String partId, String linkType) {}
    
}
