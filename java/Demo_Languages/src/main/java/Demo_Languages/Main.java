/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo_Languages;

/**
 *
 * @author Yann Caron
 */
public class Main {


    static boolean testA(Source s) {
        return (s.hasNext() && s.current() == 'a');
    }

    static boolean testB(Source s) {
        return (s.hasNext() && s.current() == 'b');
    }

    static boolean sequenceAB(Source s) {
        if (testA(s)) s.next();
        else return false;

        if (testB(s)) s.next();
        else return false;
        
        return true;
    }
    
    static boolean choiceAB(Source s) {
        if (testA(s)) s.next();
        else if (testB(s)) s.next();
        else return false;
        
        return true;        
    }

    static boolean optionalA(Source s) {
        if (testA(s)) s.next();
        return true;
    }
    
    static boolean repeatOneOrMoreA(Source s) {
        if (!testA(s)) return false;
        
        do {
            s.next();
        } while (testA(s));
        
        return true;
    }
    
        
    static boolean repeatZeroOrMoreA(Source s) {
        while(testA(s)) {
            s.next();
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("Test sequence AB : " + 
                sequenceAB(new Source("ab")));

        System.out.println("Test choice AB : " + 
                choiceAB(new Source("c")));
                
        System.out.println("Test optional A : " + 
                optionalA(new Source("")));
                
        System.out.println("Test repeat one or more A : " + 
                repeatOneOrMoreA(new Source("aaaaa")));

        System.out.println("Test repeat zero or more A : " + 
                repeatZeroOrMoreA(new Source("aaa")));
        
        System.out.println("Test Parse num : " + 
                parseNumber(new Source("0.5")));

    }
    
    // Num = [0-9]
    static boolean testNum(Source s) {
        if (!s.hasNext()) return false;
        return "0123456789".contains(s.current() + "");
    }
    
    // Dot = '.'
    static boolean testDot(Source s) {
        if (!s.hasNext()) return false;
        return s.current() == '.';
    }
    
    // Nums = Num+
    static boolean parseNums(Source s) {
        if (!testNum(s)) return false;
        do {
            s.next();
        } while (testNum(s));
        return true;
    }
    
    // Number = Nums ( '.' Nums )?
    static boolean parseNumber(Source s) {
        if (!parseNums(s)) return false;
        if (testDot(s)) {
            s.next();
            parseNums(s);
        }
        
        return true;
    }

}
