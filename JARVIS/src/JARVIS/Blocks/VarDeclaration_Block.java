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
    private String code = "0100";
    private boolean isInit;
    
    public VarDeclaration_Block(Block superBlock, String block, BlockType type, DataType varType, String name, boolean isInit) {
        super(superBlock, block, type);
        this.block = block;
        this.type = type;
        this.varType = varType;
        this.name = name;
        this.isInit = isInit;
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
    
    public DataType getVarType()
    {
        return varType;
    }
    
    public String getName()
    {
        return name;
    }
    
    public boolean isInit()
    {
        return isInit;
    }
    
}
