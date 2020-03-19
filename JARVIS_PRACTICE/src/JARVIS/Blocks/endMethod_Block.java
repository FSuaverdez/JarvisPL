/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Blocks;

import JARVIS.Other.BlockType;

/**
 *
 * @author JMDC
 */
public class endMethod_Block extends Block{

    private BlockType type;
    private String block;
    
    
    public endMethod_Block(Block superBlock, String block, BlockType type) {
        super(superBlock, block, type);
        this.block = block;
        this.type = type;
    }

    @Override
    public BlockType getType() {
        
        return type;
    }

    @Override
    public String getBlock() 
    {
        return block;
    }
    
}
