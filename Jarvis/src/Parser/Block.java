/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Block {
    
    private Block superBlock;
    private Block subBlock;
    private String currentBlock;
    private BlockType type;
    private DataType returnType;
    private ArrayList<Parameter> params;
    
    public Block(Block superBlock, Block subBlock, String currentBlock, BlockType type,ArrayList<Parameter> params, DataType returnType)
    {
        this.superBlock = superBlock;
        this.subBlock = subBlock;
        this.currentBlock = currentBlock;
        this.type = type;
        this.params = params;
        this.returnType = returnType;
    }
    
    public Block getSuperBlock()
    {
        return superBlock;
    }
    
    public Block getSubBlock()
    {
        return subBlock;
    }
    
    public String getBlock()
    {
        return currentBlock;
    }
    
}
