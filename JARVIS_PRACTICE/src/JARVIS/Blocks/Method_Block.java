/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Blocks;

import JARVIS.Other.BlockType;
import JARVIS.Other.DataType;
import JARVIS.Other.Parameter;
import java.util.ArrayList;

/**
 *
 * @author JMDC
 */
public class Method_Block extends Block{
    
    private String name;
    private String block;
    private DataType returnType;
    private BlockType type;
    private ArrayList<Parameter> parameters;
    
    public Method_Block(Block superBlock, String name, DataType returnType, ArrayList<Parameter> parameters, String block, BlockType type) 
    {
        super(superBlock, block, type);
        
        this.block = block;
        this.type = type;
        this.name = name;
        this.returnType =  returnType;
        this.parameters = parameters;
    }
    
    public void setSubBlock(Block subBlock)
    {
        super.setSub(subBlock);
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
}
