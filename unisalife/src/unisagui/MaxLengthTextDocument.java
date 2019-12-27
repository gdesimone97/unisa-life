/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Davide
 * This class control the number of char in a  String 
 * and is a PlainDocument
 */
 public class MaxLengthTextDocument extends PlainDocument {

    private int maxChars=2;

    public void setMaxChars(int maxChars) {
        this.maxChars = maxChars;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {
        // the length of string that will be created is getLength() + str.length()
        if (str != null && (getLength() + str.length() < maxChars)) {
            super.insertString(offs, str, a);
        }
    }

}
