#ifndef SOURCE_H
#define SOURCE_H


#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdio.h>

typedef struct source{
    char* src;
    int cursorSrc;
    int cursorWord;
    char* currentWord;
}Source;

/**
 * @brief Fonction permettant de créer un pointeur vers une source initialisée
 * 
 * @param src 
 * @return Source* 
 */
Source* initSource(char* src){
    Source* source = (Source*)malloc(sizeof(Source));

    if (source != NULL){
        source->src = (char*)malloc( sizeof(char) * (strlen(src) +1) );
        strcpy(source->src,src);
        source->cursorSrc = 0;
        source->cursorWord = 0;
        source->currentWord = (char*)malloc((strlen(src) +1) * sizeof(char));
    }

    return source;
}


/**
 * @brief Fonction permettant de verifier si la source est nulle
 * 
 * @param source 
 * @return true 
 * @return false 
 */
bool isNull(Source* source){
    return (source == NULL);
    // return !source;
}


/**
 * @brief Fonction permettant de vérifier si le curseur de la source a dépassé la taille de 
 * la chaine de caractere de la source
 * 
 * @param source 
 * @return true 
 * @return false 
 */
bool isOutOfBound(Source* source){
    if(source == NULL){
        return false;
    }
    return (source->cursorSrc >= strlen(source->src));
}


/**
 * @brief Fonction permettant de verifier si la chaine de caractere de la source a
 * encore un caractere apres celui actuel 
 * 
 * @param source 
 * @return true 
 * @return false 
 */
bool hasNext(Source *source){
    // Cas de la source nulle 
    if(isNull(source)){
        return false;
    }
    // Cas de la chaine vide 
    if (strlen(source->src) == 0){
        return false;
    }

    // Verifie si le curseur n'est pas arrivé au dernier cran
    return (source->cursorSrc < (strlen(source->src) -1)); 
}


/**
 * @brief Fonction permettant de renvoyer le caractere actuel
 * 
 * @param source 
 * @return char 
 */
char currentChar(Source* source){
    return source->src[source->cursorSrc];
}


/**
 * @brief Fonction permettant de mettre a jour la source en remplissant le mot actuel
 * et mettant a jour les curseurs
 * 
 * @param source 
 */
void next(Source* source){
    if(!isNull(source)){
        source->currentWord[source->cursorWord] = currentChar(source); 
        source->cursorWord = source->cursorWord + 1;
        source->cursorSrc = source->cursorSrc + 1;
    }
}


/**
 * @brief Fonction permettant de renvoyer le mot actuel de la source et de remettre a
 * zero les curseurs et le mot de la source
 * 
 * @param source 
 * @return char* 
 */
char* consumeWord(Source* source){
    char* word = (char*)malloc(sizeof(char)*(source->cursorWord));

    // Ajout du caractere de fin de chaine de caractere pour la copie 
    source->currentWord[source->cursorWord] = '\0';

    strcpy(word,source->currentWord);
    
    // Libere la memoire allouée pour le mot courant et la réatribue
    free(source->currentWord);
    source->currentWord = (char*)malloc(strlen(source->src) * sizeof(char));
    source->cursorWord = 0;
    return word;

}


#endif