/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo_Languages;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yann Caron
 */
public class Paradigm {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<> ();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        
        System.out.println("Procedural :");
        for (int i = 0; i < strings.size(); i++) {
            if ( !"b".equals(strings.get(i))) {
                System.out.println(String.format("My value is : %s", strings.get(i)));
            }
        }
        
        System.out.println("Fonctionel :");
        strings.stream()
                .filter(e -> !"b".equals(e))
                .map(e -> String.format("My value is : %s", e))
                .forEach(System.out::println);
    }
    
    // LEXER
    // num :== ('0'..'9')
    // real :== num { num } [ '.' num {num} ]
    // char :== ('a' .. 'z' | 'A' .. 'Z' | '_')
    // ident :== char { num | char }
    
    // PARSER
    // Declaration :== "String" ident ";"

    
}
