/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Davide
 * this class allow the test class to access to an element of a gui using a name
 */
public class TestUtils {
    static int counter;
    
    public static Component getChildNamed(Component parent, String name){
       if (name.equals(parent.getName()))
           return parent;
       if(parent instanceof Container){
           Component[] children = (parent instanceof JMenu)?
                   ((JMenu)parent).getMenuComponents():
                   ((Container)parent).getComponents();
           
           for(int i=0;i<children.length;i++){
               Component child= getChildNamed(children[i],name);
               if( child!=null)
                   return child;
               
           }
       }
       return null;
    }
    
    public static Component getChildIndexed(Component parent, String name,int index){
        counter=0;
        //step in only owned window and ignore its components in JFRAME
        if(parent instanceof Window){
            Component[] children= ((Window)parent).getOwnedWindows();
            
            for(int i=0;i<children.length;++i){
                //take only active windows
                if(children[i] instanceof Window &&
                        !((Window)children[i]).isActive()){
                            continue;
                }
                Component child= getChildIndexedInternal(children[i],name,index);
                if(child !=null)
                    return child;
                
                
            }
        }
        return null;
    }
    
    private static Component getChildIndexedInternal(Component parent, String name,int index){
        if(parent.getClass().toString().endsWith(name)){
            if(counter== index)
                return parent;
            ++counter;
        }
        if(parent instanceof Container){
            Component[] children=(parent instanceof JMenu)?
                    ((JMenu)parent).getMenuComponents():
                    ((Container)parent).getComponents();
            
            for(int i=0;i<children.length;++i){
                Component child= getChildIndexedInternal(children[i],name,index);
                if( child !=null)
                    return child;
            }
        }
        return null;
    }
    
}
