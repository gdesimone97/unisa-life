/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

/**
 *
 * @author virginia e davide
 */
public class RequestManager {
    
    private String info;
    
    protected RequestManager(){
        
    }
    
    public void showRequest(String id){
        this.setInfo(id);
        
    }
    
    private void setInfo(String id){
        info = id;
    }
    
    public String getInfo(){
        return info;
    }
    
}
