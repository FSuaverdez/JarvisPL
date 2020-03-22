/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Translator;

import JARVIS.Blocks.Block;

/**
 *
 * @author asus
 */
public abstract class Translator {

    private Block block;
    public Translator(Block block){
        this.block = block;
    }
    
    abstract String translate();
    
}
