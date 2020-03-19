/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Blocks;

import JARVIS.Other.BlockType;
import JARVIS.Other.Variables;
import java.util.ArrayList;

/**
 *
 * @author JMDC
 */
public class JARVIS_Block extends Block{
    
    private String name;
    private String block;
    private BlockType type;
    private ArrayList<Variables> var;
    
    public JARVIS_Block(Block superBlock, String name, String block, BlockType type) {
        super(superBlock, block, type);
        this.name = name;
        this.block = block;
        this.type = type;
        this.var = new ArrayList<>();
    }
    
    @Override
    public void setSub(Block subBlock)
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
    
    public void addVar(Variables var)
    {
        this.var.add(var);
    }
    
    public ArrayList<Variables> getVar()
    {
        return var;
    }
}
