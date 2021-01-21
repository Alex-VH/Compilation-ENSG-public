/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensg.compilers.ast;

/**
 *
 * @author cyann
 */
public interface Visitor {
    
    void visite(Addition ast);
    void visite(Substration ast);
    void visite(Multiplication ast);
    void visite(Division ast);

    void visite(Minus ast);
    
    void visite(Number ast);
    
    void visite(Print ast);
    
}
