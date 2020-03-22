/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Parsers;

import JARVIS.Blocks.Block;

/**
 *
 * @author JMDC
 */
public abstract class Parser<T extends Block>{
 
    private String str;
    
    public Parser(String str)
    {
        this.str = str;
    }
    
    public abstract T Parse();
    
    public abstract String getRemaining();
    
    public abstract boolean didParse();
    
}
