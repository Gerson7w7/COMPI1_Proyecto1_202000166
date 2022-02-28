package analizadores;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
%unicode
%ignorecase
%init{
    yyline = 1;
    yycolumn = 1;
%init}
ALFANUMERICO = ([A-Za-z_])[A-Za-z0-9_]+
NUMERO = [0-9]+([.][0-9]+)?
IGNORAR = [ ,\t,\r,\n]+
COMENTARIOS = "<!"(.|\n)*"!>"|"//".*
STRING = \"[^\"]*\"
RANGO = ."~".|.(",".)+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }

    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%
"{" {return new Symbol(sym.LlaveAbre, yychar, yyline, yytext());}
"}" {return new Symbol(sym.LlaveCierre, yychar, yyline, yytext());}
"CONJ" {return new Symbol(sym.Conjunto, yychar, yyline, yytext());}
"%%" {return new Symbol(sym.Separador, yychar, yyline, yytext());}
":" {return new Symbol(sym.DosPuntos, yychar, yyline, yytext());}
"->" {return new Symbol(sym.Asignador, yychar, yyline, yytext());}
";" {return new Symbol(sym.PuntoComa, yychar, yyline, yytext());}
"." {return new Symbol(sym.Punto, yychar, yyline, yytext());}
"*" {return new Symbol(sym.Asterisco, yychar, yyline, yytext());}
"\"" {return new Symbol(sym.Comilla, yychar, yyline, yytext());}
"|" {return new Symbol(sym.Barra, yychar, yyline, yytext());}
"-" {return new Symbol(sym.Negativo, yychar, yyline, yytext());}
"+" {return new Symbol(sym.Mas, yychar, yyline, yytext());}
"?" {return new Symbol(sym.Interrogacion, yychar, yyline, yytext());}
"," {return new Symbol(sym.Coma, yychar, yyline, yytext());}
"\'" {return new Symbol(sym.ComillaSimple, yychar, yyline, yytext());}

{ALFANUMERICO} {return new Symbol(sym.Identificador, yychar, yyline, yytext());}
{NUMERO} {return new Symbol(sym.Numero, yychar, yyline, yytext());}
{STRING} {return new Symbol(sym.Cadena, yychar, yyline, yytext());}
{RANGO} {return new Symbol(sym.Rango, yychar, yyline, yytext());}
{IGNORAR} {/*Ignore*/}
{COMENTARIOS} {/*Ignore*/}
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
