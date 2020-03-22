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
public class return_Block extends Block
{
    private BlockType type;
    private String block;
    private String returnValue;
    private String code = "1000";
    
    
    
    public return_Block(Block superBlock, String block, String returnValue, BlockType type) {
        super(superBlock, block, type);
        this.type = type;
        this.block = block;
        this.returnValue = returnValue;
    }
    
    public String getCode(){
        return code;
    }

    @Override
    public BlockType getType() {
        return type;
    }

    @Override
    public String getBlock() {
        return block;
    }
    
    public String getVal()
    {
        return returnValue;
    }
    
}
