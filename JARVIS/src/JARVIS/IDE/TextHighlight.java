/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.IDE;

/**
 *
 * @author asus
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;

public class TextHighlight extends JFrame
{

    private DefaultStyledDocument doc;
    private boolean isDark;
    private final String KEYWORDS = "JARVIS|endJARVIS|Method|endMethod|display|String|int|char|float|boolean";

    private int findLastNonWordChar(String text, int index)
    {
        while (--index >= 0)
        {
            if (String.valueOf(text.charAt(index)).matches("\\W"))
            {
                break;
            }
        }
        return index;
    }

    public void setIsDark(boolean set)
    {
        isDark = set;
        if (isDark == true)
        {
             StyleContext cont = StyleContext.getDefaultStyleContext();
             AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.CYAN);
             AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.WHITE);
            doc = new DefaultStyledDocument()
            {
                public void insertString(int offset, String str, AttributeSet a) throws BadLocationException
                {
                    super.insertString(offset, str, a);

                    String text = getText(0, getLength());
                    int before = findLastNonWordChar(text, offset);
                    if (before < 0)
                    {
                        before = 0;
                    }
                    int after = findFirstNonWordChar(text, offset + str.length());
                    int wordL = before;
                    int wordR = before;

                    while (wordR <= after)
                    {
                        if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W"))
                        {
                            if (text.substring(wordL, wordR).matches("(\\W)*("+KEYWORDS+")"))
                            {
                                setCharacterAttributes(wordL, wordR - wordL, attr, false);
                            }
                            else
                            {
                                setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
                            }
                            wordL = wordR;
                        }
                        wordR++;
                    }
                }

                public void remove(int offs, int len) throws BadLocationException
                {
                    super.remove(offs, len);

                    String text = getText(0, getLength());
                    int before = findLastNonWordChar(text, offs);
                    if (before < 0)
                    {
                        before = 0;
                    }
                    int after = findFirstNonWordChar(text, offs);

                    if (text.substring(before, after).matches("(\\W)*("+KEYWORDS+")"))
                    {
                        setCharacterAttributes(before, after - before, attr, false);
                    }
                    else
                    {
                        setCharacterAttributes(before, after - before, attrBlack, false);
                    }
                }
            };
        }
        else
        {
             StyleContext cont = StyleContext.getDefaultStyleContext();
             AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);
             AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
            doc = new DefaultStyledDocument()
            {
                public void insertString(int offset, String str, AttributeSet a) throws BadLocationException
                {
                    super.insertString(offset, str, a);

                    String text = getText(0, getLength());
                    int before = findLastNonWordChar(text, offset);
                    if (before < 0)
                    {
                        before = 0;
                    }
                    int after = findFirstNonWordChar(text, offset + str.length());
                    int wordL = before;
                    int wordR = before;

                    while (wordR <= after)
                    {
                        if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W"))
                        {
                            if (text.substring(wordL, wordR).matches("(\\W)*("+KEYWORDS+")"))
                            {
                                setCharacterAttributes(wordL, wordR - wordL, attr, false);
                            }
                            else
                            {
                                setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
                            }
                            wordL = wordR;
                        }
                        wordR++;
                    }
                }

                public void remove(int offs, int len) throws BadLocationException
                {
                    super.remove(offs, len);

                    String text = getText(0, getLength());
                    int before = findLastNonWordChar(text, offs);
                    if (before < 0)
                    {
                        before = 0;
                    }
                    int after = findFirstNonWordChar(text, offs);

                    if (text.substring(before, after).matches("(\\W)*("+KEYWORDS+")"))
                    {
                        setCharacterAttributes(before, after - before, attr, false);
                    }
                    else
                    {
                        setCharacterAttributes(before, after - before, attrBlack, false);
                    }
                }
            };
        }
    }

    private int findFirstNonWordChar(String text, int index)
    {
        while (index < text.length())
        {
            if (String.valueOf(text.charAt(index)).matches("\\W"))
            {
                break;
            }
            index++;
        }
        return index;
    }

    public DefaultStyledDocument getDoc()
    {
        return doc;
    }

    public TextHighlight()
    {
        isDark = false;
         StyleContext cont = StyleContext.getDefaultStyleContext();
         AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);
         AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
        doc = new DefaultStyledDocument()
        {
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException
            {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0)
                {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after)
                {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W"))
                    {
                        if (text.substring(wordL, wordR).matches("(\\W)*("+KEYWORDS+")"))
                        {
                            setCharacterAttributes(wordL, wordR - wordL, attr, false);
                        }
                        else
                        {
                            setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
                        }
                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            public void remove(int offs, int len) throws BadLocationException
            {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0)
                {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offs);

                if (text.substring(before, after).matches("(\\W)*("+KEYWORDS+")"))
                {
                    setCharacterAttributes(before, after - before, attr, false);
                }
                else
                {
                    setCharacterAttributes(before, after - before, attrBlack, false);
                }
            }
        };

    }

}
