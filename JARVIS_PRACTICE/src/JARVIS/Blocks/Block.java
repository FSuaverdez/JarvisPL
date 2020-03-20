/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Blocks;

import JARVIS.Other.BlockType;
import java.util.ArrayList;

/**
 *
 * @author JMDC
 */
public abstract class Block {
    
    private Block superBlock;
    private String block;
    private ArrayList<Block> subBlock;
    private BlockType type;
    
    public Block(Block superBlock, String block, BlockType type)
    {
        this.block = block;
        this.type = type;
        this.superBlock = superBlock;
        this.subBlock = new ArrayList<>();
    }
    
    public Block getSuper()
    {
        return superBlock;
    }
    
    public void setSub(Block subBlock)
    {
        this.subBlock.add(subBlock);
    }
    
    public ArrayList<Block> getSub()
    {
        return subBlock;
    }
    
    public abstract BlockType getType();
    public abstract String getBlock();
}
