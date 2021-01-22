#ifndef TOKEN_H
#define TOKEN_H


#include <stdlib.h>
#include "LexemType.h"
#include "string.h"
#include <stdio.h>

typedef struct token
{
    char* word;
    LexemType type;
    struct token* suivant;
}Token, *ListToken;

/**
 * @brief Fonction permettant de créer une liste de token
 * 
 * @param word 
 * @param type 
 * @return ListToken 
 */
ListToken initToken(char* word, LexemType type){
    ListToken token = (ListToken)malloc(sizeof(Token));

    if(token != NULL){
        token->type = type;
        token->word = (char*)malloc(sizeof(char) * strlen(word) + 1);
        strcpy(token->word,word);
        token->suivant = NULL;
    }
    return token;
}


/**
 * @brief Fonction permettant d'insérer un Token au début de la liste de token
 * 
 * @param l 
 * @param word 
 * @param type 
 * @return ListToken 
 */
ListToken insereEnTete(ListToken* l, char* word, LexemType type){

    ListToken tmp;
    tmp = initToken(word,type);
    if (tmp != NULL){
        tmp->suivant = *l;
        *l = tmp;
    }
    return tmp;
}


/**
 * @brief Fonction permettant d'afficher un token
 * 
 * @param token 
 */
void affiche(ListToken token){

    if(token != NULL){
        switch (token->type)
        {
        case KEYWORD:
            printf("Type : KEYWORD ; ");
            break;
        case NUMBER:
            printf("Type : NUMBER ; ");
            break;
        case SYMBOL:
            printf("Type : SYMBOL ; ");
            break;
        default:
            printf("Type : inconnu ; ");
            break;
        }
        printf("%s\n",token->word);
    }  
}


/**
 * @brief Fonction auxiliaire permettant l affichage des tokens
 * 
 * @param token 
 */
void afficheTokenAux(ListToken token){
    if (token != NULL){
        affiche(token);
        afficheTokenAux(token->suivant);
    }
}


/**
 * @brief Fonction permettant d'afficher une liste de token
 * 
 * @param token 
 */
void afficheToken(ListToken token){
    afficheTokenAux(token);
    printf("\n");
}


/**
 * @brief Fonction permettant de supprimer le token suivant de la liste de token
 * 
 * @param l 
 */
void supprimeSuivant(ListToken* l){
    ListToken tmp = NULL;
    if(*l == NULL){
        return;
    }
    if((*l)->suivant != NULL){
        tmp = (*l)->suivant;
        (*l)->suivant = tmp->suivant;
        tmp->suivant = NULL;
        free(tmp);
    }
}


/**
 * @brief Fonction permettant de supprimer le premier token de la liste de token
 * 
 * @param l 
 */
void supprimeTete(ListToken* l){
    ListToken tmp = NULL;
    if(*l == NULL){
        return;
    }
    tmp=*l;
    *l = (*l)->suivant;
    tmp->suivant = NULL;
    free(tmp);
}


/**
 * @brief Fonction auxiliaire permettant de renverser la liste de token
 * 
 * @param actuel 
 * @param precedent 
 * @param tete 
 */
void tailRecRevese(ListToken actuel, ListToken precedent, ListToken* tete){
   if (!actuel->suivant) {
      *tete = actuel;
      actuel->suivant = precedent;
      return;
   }
   ListToken suivant = actuel->suivant;
   actuel->suivant = precedent;
   tailRecRevese(suivant, actuel, tete);
}


/**
 * @brief Fonction permettant de renverser la liste de token
 * 
 * @param tete 
 */
void tailRecReveseLL(ListToken* tete){
   if (!tete)
      return;
   tailRecRevese(*tete, NULL, tete);
}

#endif