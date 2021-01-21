include("ParsingAlgorithms.include.al");

// Main program
formula.text = "2*(2+2)";

set formulaFocus = -1;
set graphFocus = -1;

set parseExpr = function (lexems) {
	
	// typical sequence
	if (!parseTerm(lexems)) return false;
	if (!parseExprPrime(lexems)) return false;
	return true;
	
};

set parseExprPrime = function (lexems) {

	// typical optional
	if (lexems[0] == "+" || lexems[0] == "-") {
		parseNext(true, "TERM ", lexems[0]);
		lexems.remove(0);
		
		parseFactor(lexems);
		parseTermPrime(lexems);
	}

}

set parseTerm = function (lexems) {
	
	// typical sequence
	if (!parseFactor(lexems)) return false;
	if (!parseTermPrime(lexems)) return false;
	
	return true;
	
}

set parseTermPrime = function (lexems) {
	
	// typical optional
	if (lexems[0] == "*" || lexems[0] == "/") {
		parseNext(true, "TERM ", lexems[0]);
		lexems.remove(0);
		
		parseFactor(lexems);
		parseTermPrime(lexems);
	}
	
	return true;
}

set parseFactor = function (lexems) {
	
	// typical choice
	if (parseParenthesis(lexems)) return true;
	if (parseNumber(lexems)) return true;
	return false;
}

set parseParenthesis = function (lexems) {
	
	// typical sequence
	if (lexems[0] != "(") return false;
	parseNext(false, "PARENTHESIS", lexems[0]);
	lexems.pop(0);
	if (!parseExpr(lexems)) return false;
	if (lexems[0] != ")") return false;
	parseNext(false, "PARENTHESIS", lexems[0]);
	lexems.pop(0);
	
	return true;
}

set parseNumber = function (lexems) {
	
	// typical terminal
	if (lexems.isEmpty()) return false;
	set lexem = lexems[0];
	if (lexem != "2") return false;
	lexems.remove(0);
	
	parseNext(true, "NUMBER ", lexem);
	
	return true;
}

// dont't panic !
parseExpr = parseExpr.decorate(logger, lexems).setParameter("name", "Expr");
parseExprPrime = parseExprPrime.decorate(logger, lexems).setParameter("name", "Expr prime'");
parseTerm = parseTerm.decorate(logger, lexems).setParameter("name", "Term");
parseTermPrime = parseTermPrime.decorate(logger, lexems).setParameter("name", "Term prime'");
parseFactor = parseFactor.decorate(logger, lexems).setParameter("name", "Factor");

set lexems = formula.text.split("");

parseExpr(lexems);
