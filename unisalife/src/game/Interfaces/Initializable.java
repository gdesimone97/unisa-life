/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Interfaces;

/**
 *
 * @author 1997g
 */
public interface Initializable {

    public void init() throws InitException;

    public class InitException extends RuntimeException {
        private String error;
        
        public InitException(String error) {
            this.error = error;
        }
        
        public String toString() {
            return error;
        }
        
    }
}
