/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

/**
 *
 * @author Giuseppe De Simone
 */
abstract class KeyCommand {

    private Player player = player.getPalyer();

    protected abstract void execute();

    public Player getPlayer() {
        return player;
    }
}
