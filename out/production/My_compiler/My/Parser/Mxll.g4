grammar Mxll;

program : (classDefinition | functionDefinition | variableDefinition)* EOF;

classDefinition : 'class' Identifier '{' (functionDefinition | variableDefinition)* '}';

functionDefinition : type? Identifier '(' (parameter (',' parameter)*)? ')' blockStatement;

variableDefinition : type Identifier ('=' expression)? (',' Identifier ('=' expression)?)* ';';

parameter : type Identifier;

statement
    : blockStatement
    | variableDefinition
    | expressionStatement
    | ifStatement
    | forStatement
    | whileStatement
    | jumpStatement
    ;

blockStatement : '{' statement* '}';

expressionStatement : expression? ';';

ifStatement : 'if' '(' expression ')' statement ('else' statement)?;

forStatement : 'for' '(' init=expression? ';' cond=expression? ';' step=expression? ')' statement;

whileStatement : 'while' '(' expression ')' statement;

jumpStatement
	: 'continue' ';'				#continueStatement
	| 'break' ';'					#breakStatement
	| 'return' expression? ';'		#returnStatement
	;

expression
    : 'this'												#thisExpression
	| constant												#constantExpression
    | Identifier											#variableExpression
    | '(' expression ')'									#subExpression
	| expression '.' Identifier								#memberExpression
	| expression '[' expression ']'							#subscriptExpression
	| expression '(' (expression (',' expression)*)? ')'	#functionCallExpression
	| 'new' type ('[' expression ']')* ('['']')*            #newExpression
    | expression op=('++'|'--')								#suffixExpression
	| op=('+'|'-'|'++'|'--') expression						#prefixExpression
	| op=('!'|'~') expression								#prefixExpression
	| expression op=('*'|'/'|'%') expression				#binaryExpression
	| expression op=('+'|'-') expression					#binaryExpression
	| expression op=('<'|'>'|'<='|'>=') expression			#binaryExpression
	| expression op=('=='|'!=') expression					#binaryExpression
	| expression op=('<<'|'>>') expression					#binaryExpression
	| expression op=('&'|'|'|'^') expression				#binaryExpression
	| expression op=('&&'|'||') expression					#binaryExpression
	| <assoc=right> expression '=' expression				#assignmentExpression
	;

type
    : 'void'		#voidType
    | 'bool'        #boolType
    | 'int'         #intType
    | 'string'      #stringType
    | Identifier    #classType
    | type '['']'   #arrayType
    ;

constant
    : ('true' | 'false')    #boolConstant
    | INTEGER               #intConstant
    | STRING                #stringConstant
    | 'null'                #nullConstant
    ;

Identifier : [a-zA-Z_] [a-zA-Z_0-9]*;

INTEGER : [0-9]+;

STRING : '"' CHAR* '"';

fragment
CHAR : ~["\\\n\r] | '\\' ["n\\];

WHITE_SPACE : [ \t\r\n]+ -> skip;

LINE_COMMENT : '//' ~[\r\n]* -> skip;

BLOCK_COMMENT : '/*' .*? '*/' -> skip;
