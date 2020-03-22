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
public class Display_Block extends Block{

    private String block;
    private String toBeDisplayed;
    private BlockType type;
    private String code = "0010";
    
    
    public Display_Block(Block superBlock, String block, BlockType type, String toBeDisplayed) 
    {
        super(superBlock, block, type);
        this.block = block;
        this.toBeDisplayed = toBeDisplayed;
        this.type = type;
    }
    
    public String getCode(){
        return code;
    }

    @Override
    public BlockType getType() 
    {
        return type;
    }

    @Override
    public String getBlock() 
    {
        return block;
    }
    
    public String getDisp()
    {
        return toBeDisplayed;
    }
    
}
