include ("include/tree-traversal.include.al");

set stack = array() {};
pushs(stack, nodeA);

while (!stack.isEmpty()) {
	
	set node = pops(stack);
	
	node.setColor(algo.color.GREEN);

	set children = node.getChildren();
	for (set i = children.length() - 1; i >= 0; i--) {
		pushs(stack, children[i]);
	}
	
	node.setColor(algo.color.CYAN);
	accept(node); // ACCEPT
	
}
