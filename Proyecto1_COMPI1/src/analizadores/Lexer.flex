package analizadores;
import static analizadores.Tokens.*;
%%
%class Lexer
%type Tokens
ALFANUMERICO = ([A-Za-z_])[A-Za-z0-9_]+
NUMERO = [0-9]+([.][0-9]+)?
IGNORAR = [ ,\t,\r]+
COMENTARIOS = "<!"[^"!>"]*"!>"|"//".*
STRING = \"[^\"]*\"
RANGO = (."~".)|(.(",".)+)|(.(", ".)+)
%{
    public String lexeme;
%}
%%
"{" {lexeme = yytext(); return LlaveAbre;}
"}" {lexeme = yytext(); return LlaveCierre;}
"CONJ" {lexeme = yytext(); return Conjunto;}
"%%" {lexeme = yytext(); return Separador;}
":" {lexeme = yytext(); return DosPuntos;}
"->" {lexeme = yytext(); return Asignador;}
";" {lexeme = yytext(); return PuntoComa;}
"\n" {lexeme = yytext(); return SaltoLinea;}
"." {lexeme = yytext(); return Punto;}
"*" {lexeme = yytext(); return Asterisco;}
"\"" {lexeme = yytext(); return Comilla;}
"|" {lexeme = yytext(); return Barra;}
"-" {lexeme = yytext(); return Negativo;}
"+" {lexeme = yytext(); return Mas;}
"?" {lexeme = yytext(); return Interrogacion;}
"," {lexeme = yytext(); return Coma;}
"\'" {lexeme = yytext(); return ComillaSimple;}

{ALFANUMERICO} {lexeme = yytext(); return Identificador;}
{NUMERO} {lexeme = yytext(); return Numero;}
{STRING} {lexeme = yytext(); return Cadena;}
{RANGO} {lexeme = yytext(); return Rango;}
{IGNORAR} {/*Ignore*/}
{COMENTARIOS} {/*Ignore*/} 
 . {lexeme = yytext(); return ERROR;}