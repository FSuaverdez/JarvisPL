/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Translator;

import JARVIS.Blocks.Block;
import JARVIS.Parsers.Parse;

/**
 *
 * @author asus
 */
public class Translate
{

    private String str;
    private String string = "";

    public Translate(String string)
    {
        this.str = string;
    }

    public String compile()
    {

        Parse parser = new Parse(str);
        Block type = null;
        while (parser.hasMoreBlocks())
        {
            type = parser.parse();
        }
        
        if(parser.didJarvisEnd())
        {
            while (type.getSuper() != null)
            {
                type = type.getSuper();
            }
            traverse(type);
            return string;
        }
        
        return (string = "**ERROR: Couldn't Translate >> endJARVIS missing**");
    }

    private void traverse(Block block)
    {
        string += translate(block) + "\n";
        for (Block sub : block.getSub())
        {
            traverse(sub);
        }
    }

    private String translate(Block block)
    {
        String code = block.getCode();
        String s = "";

        if (code.equals("0000"))//Jarvis
        {
            JARVIS_Translator j = new JARVIS_Translator(block);
            s = j.translate();
        }
        else if (code.equals("0001"))//Method
        {
            Method_Translator m = new Method_Translator(block);
            s = m.translate();
        }
        else if (code.equals("0010"))//Display
        {
            Display_Translator d = new Display_Translator(block);
            s = d.translate();
        }
        else if (code.equals("0011"))//Read
        {
            
        }
        else if (code.equals("0100"))//Var Dec
        {
            VarDeclaration_Translator v = new VarDeclaration_Translator(block);
            s = v.translate();
        }
        else if (code.equals("0101"))//Var Init
        {
            
        }
        else if (code.equals("0110"))//While Loop
        {
            
        }
        else if (code.equals("0111"))//Do While
        {

        }
        else if (code.equals("1000"))//return
        {
            return_Translator r = new return_Translator(block);
            s = r.translate();
        }
        else if (code.equals("1001"))//end Jarvis
        {
            endJARVIS_Translator ej = new endJARVIS_Translator(block);
            s = ej.translate();
        }
        else if (code.equals("1010"))//end Method
        {
            endMethod_Translator em = new endMethod_Translator(block);
            s = em.translate();
        }
        else if (code.equals("1011"))//End For
        {

        }
        else if (code.equals("1100"))//End While
        {

        }
        else if (code.equals("1101"))//End Do
        {

        }
        else if (code.equals("1110"))//Method Call
        {

        }
        else if (code.equals("1111"))//Di Ko Na Alam
        {

        }
        
        return s;
    }
}
