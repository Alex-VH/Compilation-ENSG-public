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
void consommeNombre(Pile *p, char* word){
    // printf("Consomme nombre\n");
    int nombre = atoi(word);
    empiler(p,nombre);
}

// E := F( (+|-) F)?
bool parseE(ListToken *l, Pile* p){
    // printf("Dans parseE\n");
    if(l!=NULL){
        parseF(l,p);
        if ((*l)->suivant != NULL){
            if(strchr("+",(*l)->suivant->word[0])){
                // printf("+");
                parseF(&(*l)->suivant,p);
                consommePlus(p);
            }
            if(strchr("-",(*l)->suivant->word[0])){
                // printf("-");
                parseF(&(*l)->suivant,p);
                consommeMoins(p);
            }
        }
    }
    return true;
}

// F := T( (*|/) T )?
bool parseF(ListToken *l,Pile* p){
    // printf("Dans parseF\n");
    if(l!=NULL){
        parseT(&(*l),p);
        if ((*l)->suivant != NULL){
            if(strchr("*",(*l)->suivant->word[0])){
                // printf("*");
                parseT(&(*l)->suivant,p);
                consommeFois(p);
            }
            if(strchr("/",(*l)->suivant->word[0])){
                // printf("/");
                parseT(&(*l)->suivant,p);
                consommeDivise(p);
            }
        }
    }   
    return true;
}

// T := Entier | ( E )
bool parseT(ListToken *l, Pile *p){
    // printf("Dans parseT\n");
    if(l!=NULL){
        if(strchr("0123456789",(*l)->word[0])){
            consommeNombre(p,(*l)->word);
        }
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