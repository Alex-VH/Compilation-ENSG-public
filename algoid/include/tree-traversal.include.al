/*
Copyright 2013 @ Yann Caron

Cette oeuvre est mise à disposition sous licence Attribution - 
Pas d’Utilisation Commerciale - Partage dans les Mêmes Conditions 3.0 France. 
Pour voir une copie de cette licence, visitez http://creativecommons.org/licenses/by-nc-sa/3.0/fr/ 
ou écrivez à Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
*/

set tree = graphr.createTree(0, 0, 1, 1);
set nodeA = tree.createRoot("A");
set nodeB = tree.createChild(nodeA, "B");
set nodeC = tree.createChild(nodeA, "C");
set nodeD = tree.createChild(nodeB, "D");
set nodeE = tree.createChild(nodeB, "E");
set nodeF = tree.createChild(nodeC, "F");
set nodeG = tree.createChild(nodeE, "G");
set nodeH = tree.createChild(nodeE, "H");

set gStack = graphr.createStack(0, 0, 0.1, 1);

set pushs = function (stack, node) {
	stack.add(node);
	gStack.pushLast(node.getText());
}

set pops = function (stack) {
	gStack.popLast();
	return stack.pop();
}

set accept = function (node) {
	util.log(node)
}
