#ifndef PILE_H
#define PILE_H


#include <stdlib.h>
#include <stdio.h>

typedef struct element{
    int nombre;
    struct element* suivant;
}Element;

typedef struct pile
{
    Element* premier;
}Pile;


/**
 * @brief Fonction permettant de créer un pointeur vers un element initialisé
 * 
 * @return Element* 
 */
Element* initElement(){
    Element *e = (Element*)malloc(sizeof(Element));
    if(e == NULL){
        exit(EXIT_FAILURE);
    }
    e->suivant = NULL;
    return e;
}

/**
 * @brief Fonction permettant de créer un pointeur vers une pile initialisée
 * 
 * @return Pile* 
 */
Pile* initPile(){
    Pile* p = (Pile*)malloc(sizeof(Pile));
    if(p == NULL){
        exit(EXIT_FAILURE);
    }
    p->premier = NULL;
    return p;
}


/**
 * @brief Fonction permettant d'empiler un nouvel élément
 * 
 * @param pile 
 * @param nvNombre 
 */
void empiler(Pile *pile, int nvNombre)
{
    Element *nouveau = initElement();
    if (pile == NULL || nouveau == NULL)
    {
        exit(EXIT_FAILURE);
    }
    printf("Empile %d \n",nvNombre);
    // printf("%d",nouveau->nombre);
    nouveau->nombre = nvNombre;
    nouveau->suivant = pile->premier;
    pile->premier = nouveau;
}


/**
 * @brief Fonction permettant de dépiler un élement
 * 
 * @param pile 
 * @return int 
 */
int depiler(Pile *pile)
{
    // printf("Dans dépile");
    if (pile == NULL)
    {
        printf("Pile vide");
        exit(EXIT_FAILURE);
    }

    int nombreDepile = 0;
    Element *elementDepile = pile->premier;

    if (pile != NULL && pile->premier != NULL)
    {
        
        nombreDepile = elementDepile->nombre;
        printf("Depile %d \n",nombreDepile);
        pile->premier = elementDepile->suivant;
        free(elementDepile);
    }

    return nombreDepile;
}


/**
 * @brief Fonction permettant d'afficher une pile
 * 
 * @param pile 
 */
void afficherPile(Pile *pile)
{
    if (pile == NULL)
    {
        exit(EXIT_FAILURE);
    }
    Element *actuel = pile->premier;

    while (actuel != NULL)
    {
        printf("%d\n", actuel->nombre);
        actuel = actuel->suivant;
    }

    printf("\n");
}

#endif