/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author asus
 */
public class Tokenizer {

    private ArrayList<TokenData> tokenDatas;
    private String str;
    private Token lastToken;

    public Tokenizer(String str) {
        tokenDatas = new ArrayList<>();

        this.str = str;

        tokenDatas.add(new TokenData(Pattern.compile("^([\\/][\\/].*[\\n]?)"), TokenType.COMMENT));
        tokenDatas.add(new TokenData(Pattern.compile("^([\\/][\\*]([\\n]*.*[\\n]*)*[\\*][\\/])"), TokenType.COMMENT));
        

        for (String s : new String[]{"string", "int", "boolean", "char", "double", "float", "void"}) {
            tokenDatas.add(new TokenData(Pattern.compile("^(" + s + ")"), TokenType.DATA_TYPES));
        }

        for (String s : new String[]{"if", "else", "else if"}) {
            tokenDatas.add(new TokenData(Pattern.compile("^(" + s + ")"), TokenType.CONDITIONAL_STATEMENTS));
        }

        for (String s : new String[]{"display", "read", "JARVIS", "return", "method"}) {
            tokenDatas.add(new TokenData(Pattern.compile("^(" + s + ")"), TokenType.RESERVED_WORDS));
        }

        for (String s : new String[]{"for", "while", "do"}) {
            tokenDatas.add(new TokenData(Pattern.compile("^(" + s + ")"), TokenType.LOOP_STATEMENTS));
        }

        tokenDatas.add(new TokenData(Pattern.compile("^([a-zA-z][a-zA-Z0-9]*)"), TokenType.IDENTIFIER));
        tokenDatas.add(new TokenData(Pattern.compile("^((-)?[0-9]*[\\.][0-9]?[0-9]*)"), TokenType.DECIMAL_LITERAL));
        tokenDatas.add(new TokenData(Pattern.compile("^((-)?[0-9]+)"), TokenType.INTEGER_LITERAL));
        tokenDatas.add(new TokenData(Pattern.compile("^(\".*\")"), TokenType.STRING_LITERAL));
        tokenDatas.add(new TokenData(Pattern.compile("^(\'.?\')"), TokenType.CHAR_LITERAL));

        for (String s : new String[]{"[\\>][\\=]?", "[\\<][\\=]?", "[\\!][\\=]", "[\\=][\\=]"}) {
            tokenDatas.add(new TokenData(Pattern.compile("^(" + s + ")"), TokenType.RELATIONAL_OPERATORS));
        }

        for (String s : new String[]{"[\\+][\\+]", "[\\-][\\-]"}) {
            tokenDatas.add(new TokenData(Pattern.compile("^(" + s + ")"), TokenType.INC_DEC_OPERATOR));
        }

        for (String s : new String[]{"[\\+][\\=]", "[\\-][\\=]", "[\\*][\\=]", "[\\/][\\=]", "\\-", "\\+", "\\/", "\\*", "\\="}) {
            tokenDatas.add(new TokenData(Pattern.compile("^(" + s + ")"), TokenType.ARITHMETIC_OPERATORS));
        }

        for (String s : new String[]{"[\\&][\\&]?", "[\\|][\\|]?", "[\\!]"}) {
            tokenDatas.add(new TokenData(Pattern.compile("^(" + s + ")"), TokenType.LOGICAL_OPERATORS));
        }

        for (String s : new String[]{"\\.", "\\,", "\\(", "\\)", "\\;", "\\:", "\\{", "\\}"}) {
            tokenDatas.add(new TokenData(Pattern.compile("^(" + s + ")"), TokenType.TOKEN));
        }
        
        tokenDatas.add(new TokenData(Pattern.compile("^(.*)"), TokenType.INVALID));

    }

    public Token nextToken() {
        str = str.trim();

        if (str.isEmpty()) {
            return (lastToken = new Token("", TokenType.EMPTY));
        }

        for (TokenData data : tokenDatas) {
            Matcher matcher = data.getPattern().matcher(str);
            if (matcher.find()) {
                String token = matcher.group().trim();
                str = matcher.replaceFirst("");

                if (data.getType() == TokenType.STRING_LITERAL) {
                    return (lastToken = new Token(token.substring(1, token.length() - 1), TokenType.STRING_LITERAL));
                } else if (data.getType() == TokenType.CHAR_LITERAL) {
                    return (lastToken = new Token(token.substring(1, token.length() - 1), TokenType.CHAR_LITERAL));
                } else  {
                    return (lastToken = new Token(token, data.getType()));
                }
            }
        }

        
        throw new IllegalStateException( "Invalid Token: "+str);
        
        
        
    }

    public boolean hasNext() {
        return !str.isEmpty();
    }

    public String getCurrToken() {
        if (null != lastToken.getType()) {
            switch (lastToken.getType()) {
                case IDENTIFIER:
                    return "Identifier";
                case DECIMAL_LITERAL:
                    return "Decimal";
                case INTEGER_LITERAL:
                    return "Integer";
                case STRING_LITERAL:
                    return "String";
                case CHAR_LITERAL:
                    return "Character";
                case TOKEN:
                    return "Token";
                case RESERVED_WORDS:
                    return "Reserved Word";
                case ARITHMETIC_OPERATORS:
                    return "Arithmetic Operator";
                case DATA_TYPES:
                    return "Data Type";
                case LOGICAL_OPERATORS:
                    return "Logical Operator";
                case RELATIONAL_OPERATORS:
                    return "Relational Operator";
                case CONDITIONAL_STATEMENTS:
                    return "Conditional Statements";
                case LOOP_STATEMENTS:
                    return "Loop Statements";
                case COMMENT:
                    return "Comment Statements";
                case INC_DEC_OPERATOR:
                    return "Increment/Decrement";
                case INVALID:
                    return "Invalid Token";
                default:
                    break;
            }
        }

        return "Empty";
    }

}
