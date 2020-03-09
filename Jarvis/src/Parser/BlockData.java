/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import java.util.regex.Pattern;

/**
 *
 * @author asus
 */
public class BlockData {
    
    private Pattern pattern;
    private BlockType type;
    
    public BlockData(Pattern pattern, BlockType type)
    {
        this.pattern = pattern;
        this.type = type;
    }
    
    public Pattern getPattern()
    {
        return pattern;
    }
    
    public BlockType getType()
    {
        return type;
    }
    
}
