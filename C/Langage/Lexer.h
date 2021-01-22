#ifndef LEXER_H
#define LEXER_H

#define ALPHA "abcdefghijklmnopqrstuvwxyz"
#define NUM "0123456789"
#define SYMB "+-*/=()."
#define NULL_ " \t\r\n"

#include "Token.h"
#include "Source.h"
#include <stdbool.h>
#include <stdio.h>


typedef struct lexer{
    ListToken listToken;
}Lexer;

/**
 * @brief Fonction permettant de créer un nouveau pointeur vers un lexer initialisé
 * 
 * @return Lexer* 
 */
Lexer* initLexer(){
    Lexer* lexer = (Lexer*)malloc(sizeof(Lexer));
    if(lexer != NULL){
        lexer->listToken = NULL;
    }
    return lexer;
}

/**
 * @brief Verifie si le caractere actuel de la source correcspond a un caractere de la chaine
 * 
 * @param s 
 * @param chaine 
 * @return true 
 * @return false 
 */
bool testChar(Source *s, char* chaine){
    if((s == NULL) || (isOutOfBound(s))){
        return false;
    }
    return strchr(chaine,currentChar(s));
}

// Digit = [0-9]
bool testDigit(Source* s){
    return testChar(s,(char*)NUM);
}

// Letter = [a-z]
bool testLetter(Source* s){
    return testChar(s,(char*)ALPHA);
}

// Symb = '-' | '+' | '*' | '/' | '=' | '(' | ')' '.'
bool testSymbol(Source* s){
    return testChar(s,(char*)SYMB);
}

// Null = ' ' | '\t' | '\n' | '\r'
bool testNull(Source* s){
    return testChar(s,(char*)NULL_);
}

// Entier = Digit+
bool parseEntier(Source* s){
    if(!testDigit(s)){
        return false;
    }
    do{
        next(s);
    }while(!isOutOfBound(s) && testDigit(s));
    return true;
}


bool parseSymbol(Source* s, Lexer *l){
    if(!testSymbol(s)){
        return false;
    }
    next(s);
    return insereEnTete(&(l->listToken),consumeWord(s),SYMBOL);
}

// Reel =  Entier ['.'[Entier]]
bool parseReel(Source* s, Lexer* l){
    if(!testDigit(s)){
        return false;
    }
    parseEntier(s);
    if(!testChar(s,(char*)".")){
        return insereEnTete(&(l->listToken),consumeWord(s),NUMBER);
    }
    next(s);
    if(testDigit(s)){
        parseEntier(s);
    }
    return insereEnTete(&(l->listToken),consumeWord(s),NUMBER);

}


void removeNull(Source* s){
    while (!isOutOfBound(s) && testNull(s)){
        next(s);
        consumeWord(s);
    }
}


void parseExpr(Source* s, Lexer *l){
    while(!isOutOfBound(s)){
        removeNull(s);
        parseReel(s,l);
        parseSymbol(s,l);
    }
}

#endif