/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

/**
 * Sample class to obtain strings without having a proper object
 *
 * @author Alfonso De Masi
 */
public class MessageInformation implements Information {

    private String info;

    /**
     * Constructor of the class
     *
     * @param info the string used to retrive the messages in the language file
     */
    public MessageInformation(String info) {
        this.info = info;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        return this.info;
    }

}
