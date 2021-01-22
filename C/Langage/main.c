#include <stdio.h>
#include "Source.h"
#include "Token.h"
#include "Lexer.h"
#include "Parser.h"
// #include "Parser2.h"
#include "Pile.h"


int main(void){

    char* expr = "(2*3)+7";

    Source* source = initSource(expr);
    Lexer* lexer = initLexer();

    parseExpr(source,lexer);
    tailRecReveseLL(&(lexer->listToken));
    afficheToken(lexer->listToken);

    Pile* p = initPile();
    parseE(&(lexer->listToken),p);
    printf("%d\n",p->premier->nombre);
    return 0;
}