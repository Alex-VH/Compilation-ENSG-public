#ifndef PARSER_H
#define PARSER_H


#include <stdbool.h>
#include "Lexer.h"
#include "Pile.h"
#include "Token.h"


bool parseE(ListToken *l, Pile *p);
bool parseF(ListToken *l, Pile *p);
bool parseT(ListToken *l, Pile *p);

/**
 * @brief Fonction permettant de faire une addition
 * 
 * @param p 
 */
void consommePlus(Pile* p){
    // printf("Consomme plus\n");
    int a = depiler(p);
    int b = depiler(p);
    printf("%d + %d\n",a,b);
    empiler(p,a+b);
}

/**
 * @brief Fonction permettant de faire une soustraction
 * 
 * @param p 
 */
void consommeMoins(Pile *p){
    // printf("Consomme moins\n");
    int a = depiler(p);
    int b = depiler(p);
    printf("%d - %d\n",b,a);
    empiler(p,b-a);
}

/**
 * @brief Fonction permettant de faire une multiplication
 * 
 * @param p 
 */
void consommeFois(Pile *p){
    // printf("Consomme fois\n");
    int a = depiler(p);
    int b = depiler(p);
    printf("%d * %d\n",a,b);
    empiler(p,a*b);
}

/**
 * @brief Fonction permettant de faire une division
 * 
 * @param p 
 */
void consommeDivise(Pile *p){
    // printf("Consomme divise\n");
    int a = depiler(p);
    int b = depiler(p);
    printf("%d / %d\n",b,a);
    empiler(p,b/a);
}

/**
 * @brief Fonction permettant d'ajouter un nombre dans la pile
 * 
 * @param p 
 * @param word 
 */
void consommeNombre(Pile *p, ListToken* l){
    // printf("Consomme nombre\n");
    int nombre = atoi((*l)->word);
    supprimeTete(l);
    empiler(p,nombre);
    
}

// E := F {+ F}
bool parseE(ListToken *l, Pile* p){
    printf("Parse E\n");
    parseF(l,p);
    while(((*l)!= NULL) && (strchr("+",(*l)->word[0]) || strchr("-",(*l)->word[0]))){
        
        if(strchr("+",(*l)->word[0])){
            printf("+\n");
            parseF(&(*l)->suivant,p);
            consommePlus(p);
            supprimeTete(l);
                
        }
        else if(strchr("-",(*l)->word[0])){
            printf("-\n");
            parseF(&(*l)->suivant,p);
            consommeMoins(p);
            supprimeTete(l);
        }
        
    }
    return true;
}

// F := T{*T}
bool parseF(ListToken *l,Pile* p){
    printf("Parse F\n");
    parseT(l,p);
    while ((*l)!=NULL && (strchr("*",(*l)->word[0]) || strchr("/",(*l)->word[0]))){
        // afficheToken((*l));
        
        if(strchr("*",(*l)->word[0])){
            printf("*\n");
            parseT(&(*l)->suivant,p);
            consommeFois(p);
            supprimeTete(l);
        }
        else if(strchr("/",(*l)->word[0])){
            printf("/\n");
            parseT(&(*l)->suivant,p);
            consommeDivise(p);
            supprimeTete(l);
        }
        
        // printf("%c\n",(*l)->word[0]);
    } ;
  
    return true;
}

// T := Entier | ( E )
bool parseT(ListToken *l, Pile *p){
    // printf("Dans parseT\n");
    printf("Parse T\n");
    if(*l!=NULL){
        if(strchr("0123456789",(*l)->word[0])){
            consommeNombre(p,l);
        }
        else if(strchr("()",(*l)->word[0])){
            // Retire la premier parenthese
            supprimeTete(l);
            parseE(l,p);
            //retire la deuxieme parenthese
            supprimeTete(l);
            
        }

    }
    return true;
}


#endif