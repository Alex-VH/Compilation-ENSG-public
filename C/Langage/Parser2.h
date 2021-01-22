#ifndef PARSER2_H
#define PARSER2_H


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
    printf("Consomme plus\n");
    int a = depiler(p);
    int b = depiler(p);
    empiler(p,a+b);
}

/**
 * @brief Fonction permettant de faire une soustraction
 * 
 * @param p 
 */
void consommeMoins(Pile *p){
    printf("Consomme moins\n");
    int a = depiler(p);
    int b = depiler(p);
    empiler(p,b-a);
}

/**
 * @brief Fonction permettant de faire une multiplication
 * 
 * @param p 
 */
void consommeFois(Pile *p){
    printf("Consomme fois\n");
    int a = depiler(p);
    int b = depiler(p);
    empiler(p,a*b);
}

/**
 * @brief Fonction permettant de faire une division
 * 
 * @param p 
 */
void consommeDivise(Pile *p){
    printf("Consomme divise\n");
    int a = depiler(p);
    int b = depiler(p);
    empiler(p,b/a);
}

/**
 * @brief Fonction permettant d'ajouter un nombre dans la pile
 * 
 * @param p 
 * @param word 
 */
void consommeNombre(Pile *p, ListToken* l){
    if(*l!=NULL){
        printf("Consomme nombre\n");
        int nombre = atoi((*l)->word);
        supprimeTete(l);
        empiler(p,nombre);
    }
    
}

// E := T( (+|-) E )?
bool parseE(ListToken *l, Pile* p){
    printf("Dans parseE\n");
    if(*l!=NULL){
        parseT(l,p);
        if (*l != NULL){
            if(strchr("+",(*l)->word[0])){
                printf("+");
                parseE(&(*l)->suivant,p);
                consommePlus(p);
            }
            if(strchr("-",(*l)->word[0])){
                printf("-");
                parseE(&(*l)->suivant,p);
                consommeMoins(p);
            }
        }
    }
    return true;
}

// T := F( (*|/) T )?
bool parseT(ListToken *l,Pile* p){
    printf("Dans parseT\n");
    if(*l!=NULL){
        parseF(&(*l),p);
        if (*l != NULL){
            if(strchr("*",(*l)->word[0])){
                printf("*");
                parseT(&(*l)->suivant,p);
                consommeFois(p);
            }
            if(strchr("/",(*l)->word[0])){
                printf("/");
                parseT(&(*l)->suivant,p);
                consommeDivise(p);
            }
        }
    }   
    return true;
}

// F := Entier | "(" E ")"
bool parseF(ListToken *l, Pile *p){
    printf("Dans parseF\n");
    if(*l!=NULL){
        if(strchr("0123456789",(*l)->word[0])){
            // printf("T : %c",(*l)->word[0]);
            consommeNombre(p,l);
            
        }
        // else if (strchr("()",(*l)->word[0])){
        else{
            // printf("Parenthese");
            // Retire la premier parenthese
            supprimeTete(l);
            parseE(l,p);
            //retire la deuxieme parenthese
            supprimeSuivant(l);

        }

    }

    
    return true;
}


#endif