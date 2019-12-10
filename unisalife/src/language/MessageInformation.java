/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

/**
 *
 * @author alfon
 */
public  class MessageInformation implements Information{

    private String info;
    
    public MessageInformation(String info) {
        this.info = info;
    }

    
    
    @Override
    public String getInfo() {
        return this.info;
    }
    
}
