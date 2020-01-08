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

    /**
     * This method has to be implemented to all the managers to be reinitialized
     * 
     * @throws InitException
     */
    public void init() throws InitException;

    /**
     * This exception is thrown in case that the initialization fails
     */
    public class InitException extends RuntimeException {
        private String error;
        
        /**
         * Constructor 
         * 
         * @param error
         */
        public InitException(String error) {
            this.error = error;
        }
        
        @Override
        public String toString() {
            return error;
        }
        
    }
}
