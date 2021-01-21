// init algo
algo.hide();
ui.clearLog();
algo.setTextSize(50);
set LETTER_SPACE = 75;
set TEXT_Y = algo.getTop() - 50;
set indent = 0;

// init text
set formula = object () {
	set text;
	set focusIndex = -1;
	
	set draw = function () {
		set x = - (text.length() * LETTER_SPACE) / 2;
		
		for (set i = 0; i < text.length(); i++) {
			set c = if (i == focusIndex) algo.color.CYAN else algo.color.WHITE;
			algo.setColor(c);
			algo.goTo(x, TEXT_Y);
			algo.text(text.getChar(i));
			x += LETTER_SPACE;
		}
	}
};

set graph = object () {
	set node = object () {
		
		set x;
		set y;
		set text;
		set isVisible;
		set isFocused;
		
		set draw = function () {
			if (!isVisible) return;
			set c = if (isFocused) algo.color.CYAN else algo.color.WHITE;
			algo.setColor(c);
			algo.goTo(x, y);
			algo.disc(150);
			algo.setRGB(81,77,74);
			algo.disc(125);
			algo.setColor(c);
			algo.goTo(x-4, y-8);
			algo.text(text);
		};
		
	};
	
	set arc = object() {
		
		set id1;
		set id2;
		
		set draw = function () {
			set node1 = graph.nodes[id1];
			set node2 = graph.nodes[id2];
			
			if (!node1.isVisible || !node2.isVisible) return;
			algo.setColor(algo.color.WHITE);
			algo.setStroke(10);
			algo.goTo(node1.x, node1.y);
			algo.lineTo(node2.x, node2.y);
		};
		
	};
	
	set nodes = array() {
		node.clone(-150, 50, "2", false, false),
		node.clone(0, -150, "*", false, false),
		node.clone(0, 250, "2", false, false),
		node.clone(150, 50, "+", false, false),
		node.clone(300, 250, "2", false, false)
	};
	
	set arcs = array() {
		arc.clone(1, 0),
		arc.clone(1, 3),
		arc.clone(3, 2),
		arc.clone(3, 4)
	};
	
	set setVisible = function (id) {
		nodes[id].isVisible = true;
	};
	
	set setFocus = function (id) {
		nodes.each ((item) { item.isFocused = false; });
		if (id.between(0, nodes.length())) {
			nodes[id].isFocused = true;
		}
	};
	
	set draw = function () {
		arcs.each((item) { item.draw() });
		nodes.each((item) { item.draw() });
	};
	
};

set parseNext = function(isGraph, name, lexem) {
	util.log("".create(" ", indent) .. "parse " .. name .. "(" .. lexem .. ")");
	formula.focusIndex = formulaFocus++;
	formula.draw();
	
	if (isGraph) {
		graph.setVisible(graphFocus++);
		graph.setFocus(graphFocus);
		graph.draw();
		graph.setFocus(-1);
	}
}

set logger = function (lexems, name, f) {
	util.warn ("".create(" ", indent) .. ">>>>> " .. name);
	indent++;
	set result = f(lexems);
	indent--;
	util.warn ("".create(" ", indent) .. "<<<<< " .. name);
	return result;
}
