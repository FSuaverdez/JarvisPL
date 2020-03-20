/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Blocks;

import JARVIS.Other.BlockType;
import JARVIS.Other.DataType;

/**
 *
 * @author JMDC
 */
public class VarDeclaration_Block extends Block{

    private String block;
    private BlockType type;
    private DataType varType;
    private String name;
    
    public VarDeclaration_Block(Block superBlock, String block, BlockType type, DataType varType, String name) {
        super(superBlock, block, type);
        this.block = block;
        this.type = type;
        this.varType = varType;
        this.name = name;
    }

    @Override
    public BlockType getType() {
        return type;
    }

    @Override
    public String getBlock() {
        return block;
    }
    
    public DataType getVarType()
    {
        return varType;
    }
    
    public String getName()
    {
        return name;
    }
    
    
    
}
