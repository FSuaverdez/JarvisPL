/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Translator;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.VarDeclaration_Block;

/**
 *
 * @author giode
 */
public class VarDeclaration_Translator extends Translator{

    private VarDeclaration_Block block;
    
    public VarDeclaration_Translator(Block block) {
        super(block);
        this.block = (VarDeclaration_Block)block;
    }

    @Override
    String translate() {
        String str = block.getBlock() + "; ";
        return str;
        
    }
    
}
