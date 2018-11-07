//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx;



//#line 18 "Parser.y"
import java.io.*;
import java.util.*;
import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando.*;
import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr.*;
//#line 22 "ParserCompiladorX.java"




public class ParserCompiladorX
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:ASTNo
String   yytext;//user variable to return contextual strings
ASTNo yyval; //used to return semantic vals from action routines
ASTNo yylval;//the 'lval' (result) I got from yylex()
ASTNo valstk[] = new ASTNo[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new ASTNo();
  yylval=new ASTNo();
  valptr=-1;
}
final void val_push(ASTNo val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    ASTNo[] newstack = new ASTNo[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final ASTNo val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final ASTNo val_peek(int relative)
{
  return valstk[valptr-relative];
}
final ASTNo dup_yyval(ASTNo val)
{
  return val;
}
//#### end semantic value section ####
public final static short TK_WRITE=257;
public final static short TK_READ=258;
public final static short TK_IF=259;
public final static short TK_FI=260;
public final static short TK_END=261;
public final static short TK_THEN=262;
public final static short TK_ELSE=263;
public final static short TK_FALSE=264;
public final static short TK_TRUE=265;
public final static short IDENTIFICADOR=266;
public final static short NUMERO=267;
public final static short STRING=268;
public final static short PONTO_FLUTUANTE=269;
public final static short TK_ATRIBUICAO=270;
public final static short TK_INT=271;
public final static short TK_REAL=272;
public final static short TK_BOOL=273;
public final static short TK_DECL=274;
public final static short TK_FOR=275;
public final static short TK_FROM=276;
public final static short TK_TO=277;
public final static short TK_DO=278;
public final static short TK_DONE=279;
public final static short TK_WHILE=280;
public final static short TK_DIFERENTE=281;
public final static short TK_MAIORIGUAL=282;
public final static short TK_MENORIGUAL=283;
public final static short TK_AND=284;
public final static short TK_OR=285;
public final static short TK_NOT=286;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    7,
    7,    7,    7,    7,    6,    6,    6,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    5,    5,    5,
    5,    5,    5,    5,    5,    8,    8,    8,    8,    8,
    8,    8,    8,    9,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,
};
final static short yylen[] = {                            2,
    1,    2,    3,    0,    2,    2,    5,    7,    9,    5,
    3,    6,    6,    9,    2,    4,    4,    8,    4,    7,
    2,    4,    4,    8,    4,    7,    4,    3,    3,    0,
    1,    1,    1,    4,    0,    1,    1,    0,    3,    3,
    2,    3,    3,    3,    3,    3,    3,    0,    3,    3,
    3,    3,    3,    3,    1,    0,    3,    3,    3,    3,
    3,    3,    1,    0,    0,    1,    3,    3,    3,    3,
    3,    3,    3,    4,    3,    1,    1,    3,    3,    2,
    3,    3,    3,    3,    3,    3,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    6,    0,   32,   33,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    2,    0,    0,    0,   80,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   41,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   29,   28,    0,    0,    0,    0,
    0,    0,    0,    3,    0,   73,    0,    0,   72,    0,
    0,    0,    0,    0,   17,    0,    0,    0,    0,    0,
    0,    0,   86,   83,   84,   78,   79,   81,   82,   85,
    0,    0,    0,    0,    0,    0,    0,   39,   40,   47,
   44,   45,   42,   43,   46,    0,    0,    0,    0,   27,
    0,    0,    0,    0,   34,    7,    0,    0,    0,    0,
    0,    0,   10,    0,    0,    0,    0,    0,   36,   37,
    0,    0,    0,    0,    8,    0,   20,   26,    0,    0,
    0,    0,    0,    9,
};
final static short yydgoto[] = {                          8,
    9,   10,   26,   97,   18,  151,   19,   20,    0,
};
final static short yysindex[] = {                       800,
  -36, -264, -174,  -88,  -24,  -24, -174,    0,  800,  -45,
  -35,    0,  -29,    0, -121,  -24,  792,  276,  706,  831,
    0,  -72,    0,    0, -121, -253,  232,  -40,  -24, 1026,
  284,  -21,  -30,    0,  -24,  -14,  -24,    0,  344,  -24,
  -24,  -24,  -24,  -24,  -32,  -24,  -24,  -24,  -24,  -24,
 -121, -121, -121, -121, -121, -121, -121, -121,  -24,  -24,
  -24,  -24,  -24,  -24,    0,  800, -174, -174, -121, -121,
 -121, -121, -121, -121,    0,    0,  -33,  890,  372,  -24,
 -152,  -24,  800,    0,  730,    0,  913,  276,    0,  855,
   36,   36,  -11,  -11,    0,  890,  890,  157,  157,   -5,
   -5,  276,    0,    0,    0,    0,    0,    0,    0,    0,
  413,  413,   -3,   -3,  831,  766,  776,    0,    0,    0,
    0,    0,    0,    0,    0,  -24, -227,  -42,  -34,    0,
  310,  670,    0,  -28,    0,    0,  800,  786,  -18, -256,
 -256,  -24,    0,   25,  787,    0,  -23,  890,    0,    0,
  -16,   -8,  -19,  -24,    0,  -24,    0,    0,  800,  890,
  878,  727,    0,    0,
};
final static short yyrindex[] = {                        20,
  147,    0,  537,    0,  153,  255,  694,    0,    1,    0,
    3,   29,   35,   56,  458,  126,   43,   82,    0,  427,
    0,   -7,    0,    0, -203,    0,    0,  147,  319,    0,
    0,    0,    0,    0,  319,    0,  147,    0,    0,   66,
   66,   66,   66,   66,  147,   66,   66,   66,   66,   66,
  458,  458,  458,  458,  458,  458,  458,  458,   92,   92,
   92,   92,   92,  319,    0,   20,  522,  522, -203, -203,
 -203, -203, -203, -203,    0,    0,  380,   45,    0,   66,
    0,  401,   20,    0,    0,    0,   46,  -41,    0,  586,
  567,  578,  465,  487,    0,  484,    0,  609,  614,  512,
  520,  639,    0,    0,    0,    0,    0,    0,    0,    0,
  665,  686,  548,  573,  692,    0,   20,    0,    0,    0,
    0,    0,    0,    0,    0,  319,    0,   71,   72,    0,
    0,   20,  119,  147,    0,    0,   20,    0,  147,   39,
   39,  409,    0,    0,   20,  430,  380,   79,    0,    0,
    0,    0,    0,  147,    0,  319,    0,    0,   20,   83,
    0,   20,  436,    0,
};
final static short yygindex[] = {                         0,
  -53,   12,   -1,  866,  173,    6,  958,  935,    0,
};
final static int YYTABLESIZE=1087;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         16,
    1,   21,   29,   16,   36,   32,   36,   16,   66,  149,
  150,   16,  117,   34,   37,   16,   36,   16,   64,   76,
   33,   16,   43,   41,   80,   42,   86,   44,   84,  132,
   67,   68,   80,   31,   31,   31,   31,   31,   50,   31,
   63,   40,  139,   66,   66,   66,   66,   66,  140,   66,
   31,   31,   31,   31,   31,   35,  141,  126,   30,    4,
   66,   66,   31,   31,   31,  118,  119,  156,  154,   55,
   55,   55,   55,   55,   30,   55,  157,   43,    4,   80,
   30,   30,   44,  145,  158,   31,   55,   55,   32,   32,
   32,   22,   23,    5,   24,   66,   63,   63,   63,   63,
   63,   15,   63,   11,   22,  162,   48,   48,   48,   48,
   48,   25,   48,   63,   63,   33,   33,   33,  128,  129,
  130,   55,   76,   48,   48,   30,   30,   30,   33,   19,
   25,   35,   56,   48,   48,   48,   48,   12,   48,   76,
   76,   18,   76,   33,   22,   23,  152,   24,   63,   56,
   56,   30,   30,   30,    0,    0,   33,    0,   48,   74,
   74,   74,   74,   74,    0,   74,   48,   48,   48,   48,
   48,    0,   48,   33,   76,    0,   74,   74,   34,   34,
   34,   28,    0,    0,   56,   30,   30,   30,   48,   48,
   48,   48,    0,   48,   48,   48,   48,   48,   48,   48,
   50,    0,    0,   49,    0,   48,   30,   30,   30,   88,
   48,   74,   30,   30,   30,    0,    0,    0,   98,   99,
  100,  101,  102,   75,   76,   77,   12,    0,   14,   11,
   12,   13,   14,   11,   12,   95,   14,   11,   12,  144,
   14,   11,   12,    0,   14,   15,    0,  147,   12,   15,
   14,    0,    0,   15,   31,    0,   83,   15,  159,    0,
    0,   15,   67,   68,    0,    0,    0,   15,   31,   31,
   31,    0,    0,   31,   31,   31,   31,   31,   66,   66,
   66,    0,    0,   31,   31,   31,   31,   31,    0,    0,
    0,   72,   74,   73,    0,    0,   48,   48,   48,   48,
    0,   48,    0,    0,   55,   55,   55,    0,    0,   32,
   32,   32,   32,   32,   30,   30,   30,   48,   46,   50,
   47,    0,   49,    0,    0,   43,   41,   80,   42,    0,
   44,   63,   63,   63,    0,    0,   33,   33,   33,   33,
   33,   48,   48,   48,   40,    0,   30,   30,   30,   30,
   30,   43,   41,   80,   42,    0,   44,   76,   76,   76,
   48,   48,   48,   48,    0,   48,    0,   56,   56,   56,
   40,    0,   30,   30,   30,   30,   30,    0,   30,   30,
   30,    0,    0,    0,   89,   43,   41,   80,   42,    0,
   44,    0,    0,    0,   74,   74,   74,    0,    0,   34,
   34,   34,   34,   34,   40,    0,   30,   30,   30,   30,
   30,   48,    0,   43,   41,   80,   42,    0,   44,    0,
    0,   66,   66,   66,   66,    0,   66,   30,   30,   30,
   30,   30,   40,   30,   30,   30,   30,   30,   66,   31,
   31,   31,   48,   48,   48,   48,    0,   48,    0,    0,
   48,   48,   48,   48,   61,   48,   63,    0,    0,   62,
   30,   30,   30,    0,  127,    0,    0,   77,   30,   30,
   30,   74,   74,   74,   74,    0,   74,   74,   74,   74,
   74,    0,   74,    0,   77,   77,    0,   77,   13,   34,
   34,   34,    0,    0,   14,   34,   34,   34,   30,   30,
   30,   30,   30,    0,   30,   70,   70,   70,    0,   70,
    0,   70,   69,   70,   71,   30,   30,    0,   30,   77,
    0,    0,   70,   70,   67,   70,    0,   71,   71,   71,
   48,   71,    0,   71,    0,   30,   30,   30,   30,   30,
    0,   67,   67,    0,   71,   71,    0,   71,    0,    0,
   30,    0,   52,   52,   52,    0,   52,   70,   52,   82,
   53,   53,   53,    0,   53,    0,   53,    0,    0,   52,
   52,    0,   52,    0,    0,    0,   67,   53,   53,   71,
   53,   30,   30,   30,    0,    0,  142,    0,   60,   60,
   60,    0,   60,    0,   60,    0,   30,   30,   30,   30,
   30,   30,   30,   30,   52,   60,   60,   68,   60,   68,
    0,   68,   53,   61,   61,   61,    0,   61,   69,   61,
   69,    0,   69,    0,   68,   68,   54,   68,    0,    0,
   61,   61,    0,   61,    0,   69,   69,    0,   69,    0,
   60,    0,    0,   54,   54,    0,   54,    0,    0,   50,
    0,   50,    0,   50,   51,    0,   51,    0,   51,   68,
   31,   31,   31,   31,   31,   61,   50,   50,    0,   50,
   69,   51,   51,    0,   51,    0,    0,   48,   54,   49,
    0,   30,   30,   30,   30,   30,   48,    0,    0,   30,
   30,   30,   30,   30,    0,    0,   49,   49,    0,   49,
    0,   50,   77,   77,   77,   58,   51,   58,    0,   58,
   34,   34,   34,   34,   34,    0,   34,   34,   34,   34,
   34,    0,   58,   58,    0,   58,   59,    0,   59,    0,
   59,   49,   57,   30,   30,   30,    0,    0,    0,    0,
   70,   70,   70,   59,   59,    0,   59,    0,    0,   57,
   57,    0,   57,   30,   30,   30,    0,   58,    0,   67,
   67,   67,   71,   71,   71,   56,   58,   57,    0,    0,
    0,   43,   41,   80,   42,    0,   44,    0,   59,    0,
    0,    0,    0,   38,   57,    0,    0,   52,   52,   52,
   40,    0,    0,    0,    0,   53,   53,   53,   38,   38,
    0,    0,   30,   30,   30,   38,   38,   43,   41,   80,
   42,    0,   44,    0,    0,    0,    0,   30,   30,   30,
   38,   38,  133,   60,   60,   60,   40,   43,   41,   80,
   42,    0,   44,   43,   41,   45,   42,    0,   44,    0,
    0,    0,   68,   68,   68,    0,   40,    0,   61,   61,
   61,    0,   40,   69,   69,   69,    0,    0,  135,    0,
    0,   54,   54,   54,    0,    0,   17,    0,    0,    0,
   30,   31,   61,   59,   63,   60,    0,   62,  146,    0,
    0,   39,    0,    0,   50,   50,   50,    0,    0,   51,
   51,   51,    0,   78,   79,    0,   43,   41,   80,   42,
   85,   44,   87,    0,    0,   90,   91,   92,   93,   94,
   96,    0,    0,    0,   49,   49,   49,    0,    0,   43,
   41,   80,   42,    0,   44,    0,    1,    2,    3,  116,
    0,   43,   41,   80,   42,    4,   44,    0,   40,    0,
   58,   58,   58,    5,    6,   96,    0,  131,  143,    7,
   40,    0,    0,    0,   43,   41,  134,   42,    0,   44,
   27,   59,   59,   59,   27,    0,    0,   57,   57,   57,
  163,   38,   38,   40,   30,   30,   30,   38,   38,    0,
    0,    0,   65,    1,    2,    3,   51,   52,   53,   54,
   55,  138,    4,  111,  112,  113,  114,  115,    0,   96,
    5,    6,    0,    0,  148,  164,    7,  153,  103,  104,
  105,  106,  107,  108,  109,  110,    0,    0,    0,  160,
    0,  161,    0,    0,   27,   27,  120,  121,  122,  123,
  124,  125,    1,    2,    3,  136,    0,    0,  137,    0,
    0,    4,    0,    1,    2,    3,  155,    0,    0,    5,
    6,    0,    4,    0,    0,    7,    1,    2,    3,    0,
    5,    6,    0,    0,    0,    4,    7,   43,   41,   80,
   42,    0,   44,    5,    6,    0,    0,    0,    0,    7,
    0,    0,    0,   81,    0,    0,   40,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,  266,   91,   40,   40,    7,   40,   40,  262,  266,
  267,   40,   66,   59,   44,   40,   40,   59,   91,   61,
    9,   40,   42,   43,   44,   45,   41,   47,   59,   83,
  284,  285,   44,   41,   42,   43,   44,   45,   44,   47,
   44,   61,  270,   41,   42,   43,   44,   45,   91,   47,
   58,   59,   60,   61,   62,   91,   91,   91,  262,   59,
   58,   59,   60,   61,   62,   67,   68,   91,   44,   41,
   42,   43,   44,   45,  278,   47,   93,   42,   59,   44,
  284,  285,   47,  137,   93,   93,   58,   59,   60,   61,
   62,  266,  267,   59,  269,   93,   41,   42,   43,   44,
   45,   59,   47,   59,   59,  159,   41,   42,   43,   44,
   45,  286,   47,   58,   59,   60,   61,   62,  271,  272,
  273,   93,   41,   58,   59,   60,   61,   62,  117,   59,
   59,   93,   41,   42,   43,   44,   45,   59,   47,   58,
   59,   59,   61,  132,  266,  267,  141,  269,   93,   58,
   59,   60,   61,   62,   -1,   -1,  145,   -1,   93,   41,
   42,   43,   44,   45,   -1,   47,   41,   42,   43,   44,
   45,   -1,   47,  162,   93,   -1,   58,   59,   60,   61,
   62,  270,   -1,   -1,   93,   60,   61,   62,   42,   43,
   44,   45,   -1,   47,   42,   43,   44,   45,   42,   47,
   44,   -1,   -1,   47,   -1,   59,   60,   61,   62,   37,
   58,   93,   60,   61,   62,   -1,   -1,   -1,   46,   47,
   48,   49,   50,  264,  265,  266,  267,   -1,  269,  266,
  267,  268,  269,  266,  267,  268,  269,  266,  267,  268,
  269,  266,  267,   -1,  269,  286,   -1,  266,  267,  286,
  269,   -1,   -1,  286,  262,   -1,  278,  286,  278,   -1,
   -1,  286,  284,  285,   -1,   -1,   -1,  286,  276,  277,
  278,   -1,   -1,  281,  282,  283,  284,  285,  276,  277,
  278,   -1,   -1,  281,  282,  283,  284,  285,   -1,   -1,
   -1,   60,   61,   62,   -1,   -1,   42,   43,   44,   45,
   -1,   47,   -1,   -1,  276,  277,  278,   -1,   -1,  281,
  282,  283,  284,  285,   60,   61,   62,   42,   43,   44,
   45,   -1,   47,   -1,   -1,   42,   43,   44,   45,   -1,
   47,  276,  277,  278,   -1,   -1,  281,  282,  283,  284,
  285,  276,  277,  278,   61,   -1,  281,  282,  283,  284,
  285,   42,   43,   44,   45,   -1,   47,  276,  277,  278,
   42,   43,   44,   45,   -1,   47,   -1,  276,  277,  278,
   61,   -1,  281,  282,  283,  284,  285,   -1,   60,   61,
   62,   -1,   -1,   -1,   41,   42,   43,   44,   45,   -1,
   47,   -1,   -1,   -1,  276,  277,  278,   -1,   -1,  281,
  282,  283,  284,  285,   61,   -1,  281,  282,  283,  284,
  285,   93,   -1,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   42,   43,   44,   45,   -1,   47,  281,  282,  283,
  284,  285,   61,  281,  282,  283,  284,  285,   59,   60,
   61,   62,   42,   43,   44,   45,   -1,   47,   -1,   -1,
   42,   43,   44,   45,   42,   47,   44,   -1,   -1,   47,
   60,   61,   62,   -1,   93,   -1,   -1,   41,   60,   61,
   62,   42,   43,   44,   45,   -1,   47,   42,   43,   44,
   45,   -1,   47,   -1,   58,   59,   -1,   61,   59,   60,
   61,   62,   -1,   -1,   59,   60,   61,   62,   41,   42,
   43,   44,   45,   -1,   47,   41,   42,   43,   -1,   45,
   -1,   47,  281,  282,  283,   58,   59,   -1,   61,   93,
   -1,   -1,   58,   59,   41,   61,   -1,   41,   42,   43,
  276,   45,   -1,   47,   -1,  281,  282,  283,  284,  285,
   -1,   58,   59,   -1,   58,   59,   -1,   61,   -1,   -1,
   93,   -1,   41,   42,   43,   -1,   45,   93,   47,  276,
   41,   42,   43,   -1,   45,   -1,   47,   -1,   -1,   58,
   59,   -1,   61,   -1,   -1,   -1,   93,   58,   59,   93,
   61,   60,   61,   62,   -1,   -1,  277,   -1,   41,   42,
   43,   -1,   45,   -1,   47,   -1,   60,   61,   62,  281,
  282,  283,  284,  285,   93,   58,   59,   41,   61,   43,
   -1,   45,   93,   41,   42,   43,   -1,   45,   41,   47,
   43,   -1,   45,   -1,   58,   59,   41,   61,   -1,   -1,
   58,   59,   -1,   61,   -1,   58,   59,   -1,   61,   -1,
   93,   -1,   -1,   58,   59,   -1,   61,   -1,   -1,   41,
   -1,   43,   -1,   45,   41,   -1,   43,   -1,   45,   93,
  281,  282,  283,  284,  285,   93,   58,   59,   -1,   61,
   93,   58,   59,   -1,   61,   -1,   -1,  277,   93,   41,
   -1,  281,  282,  283,  284,  285,  278,   -1,   -1,  281,
  282,  283,  284,  285,   -1,   -1,   58,   59,   -1,   61,
   -1,   93,  276,  277,  278,   41,   93,   43,   -1,   45,
  281,  282,  283,  284,  285,   -1,  281,  282,  283,  284,
  285,   -1,   58,   59,   -1,   61,   41,   -1,   43,   -1,
   45,   93,   41,  276,  277,  278,   -1,   -1,   -1,   -1,
  276,  277,  278,   58,   59,   -1,   61,   -1,   -1,   58,
   59,   -1,   61,   60,   61,   62,   -1,   93,   -1,  276,
  277,  278,  276,  277,  278,   60,   61,   62,   -1,   -1,
   -1,   42,   43,   44,   45,   -1,   47,   -1,   93,   -1,
   -1,   -1,   -1,  262,   93,   -1,   -1,  276,  277,  278,
   61,   -1,   -1,   -1,   -1,  276,  277,  278,  262,  278,
   -1,   -1,  281,  282,  283,  284,  285,   42,   43,   44,
   45,   -1,   47,   -1,   -1,   -1,   -1,  281,  282,  283,
  284,  285,   93,  276,  277,  278,   61,   42,   43,   44,
   45,   -1,   47,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,  276,  277,  278,   -1,   61,   -1,  276,  277,
  278,   -1,   61,  276,  277,  278,   -1,   -1,   93,   -1,
   -1,  276,  277,  278,   -1,   -1,    1,   -1,   -1,   -1,
    5,    6,   42,   43,   44,   45,   -1,   47,   93,   -1,
   -1,   16,   -1,   -1,  276,  277,  278,   -1,   -1,  276,
  277,  278,   -1,   28,   29,   -1,   42,   43,   44,   45,
   35,   47,   37,   -1,   -1,   40,   41,   42,   43,   44,
   45,   -1,   -1,   -1,  276,  277,  278,   -1,   -1,   42,
   43,   44,   45,   -1,   47,   -1,  257,  258,  259,   64,
   -1,   42,   43,   44,   45,  266,   47,   -1,   61,   -1,
  276,  277,  278,  274,  275,   80,   -1,   82,  279,  280,
   61,   -1,   -1,   -1,   42,   43,   44,   45,   -1,   47,
    3,  276,  277,  278,    7,   -1,   -1,  276,  277,  278,
   93,  278,   15,   61,  281,  282,  283,  284,  285,   -1,
   -1,   -1,   25,  257,  258,  259,  281,  282,  283,  284,
  285,  126,  266,   59,   60,   61,   62,   63,   -1,  134,
  274,  275,   -1,   -1,  139,  279,  280,  142,   51,   52,
   53,   54,   55,   56,   57,   58,   -1,   -1,   -1,  154,
   -1,  156,   -1,   -1,   67,   68,   69,   70,   71,   72,
   73,   74,  257,  258,  259,  260,   -1,   -1,  263,   -1,
   -1,  266,   -1,  257,  258,  259,  260,   -1,   -1,  274,
  275,   -1,  266,   -1,   -1,  280,  257,  258,  259,   -1,
  274,  275,   -1,   -1,   -1,  266,  280,   42,   43,   44,
   45,   -1,   47,  274,  275,   -1,   -1,   -1,   -1,  280,
   -1,   -1,   -1,   58,   -1,   -1,   61,
};
}
final static short YYFINAL=8;
final static short YYMAXTOKEN=286;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"TK_WRITE","TK_READ","TK_IF","TK_FI",
"TK_END","TK_THEN","TK_ELSE","TK_FALSE","TK_TRUE","IDENTIFICADOR","NUMERO",
"STRING","PONTO_FLUTUANTE","TK_ATRIBUICAO","TK_INT","TK_REAL","TK_BOOL",
"TK_DECL","TK_FOR","TK_FROM","TK_TO","TK_DO","TK_DONE","TK_WHILE",
"TK_DIFERENTE","TK_MAIORIGUAL","TK_MENORIGUAL","TK_AND","TK_OR","TK_NOT",
};
final static String yyrule[] = {
"$accept : main",
"main : lista_comandos",
"lista_comandos : comando ';'",
"lista_comandos : lista_comandos comando ';'",
"comando :",
"comando : TK_WRITE STRING",
"comando : TK_READ IDENTIFICADOR",
"comando : TK_IF exprCondicao TK_THEN lista_comandos TK_FI",
"comando : TK_IF exprCondicao TK_THEN lista_comandos TK_ELSE lista_comandos TK_FI",
"comando : TK_FOR expr TK_FROM expr TK_TO expr TK_DO lista_comandos TK_DONE",
"comando : TK_WHILE exprCondicao TK_DO lista_comandos TK_DONE",
"comando : IDENTIFICADOR TK_ATRIBUICAO expr",
"comando : IDENTIFICADOR '[' expr ']' TK_ATRIBUICAO expr",
"comando : IDENTIFICADOR TK_ATRIBUICAO IDENTIFICADOR '[' expr ']'",
"comando : IDENTIFICADOR '[' expr ']' TK_ATRIBUICAO IDENTIFICADOR '[' expr ']'",
"comando : TK_WRITE expr",
"comando : TK_WRITE STRING ',' exprInt",
"comando : TK_WRITE expr ',' STRING",
"comando : TK_WRITE STRING ',' expr ',' STRING ',' expr",
"comando : TK_DECL expr ':' TK_INT",
"comando : TK_DECL expr ':' TK_INT '[' exprVetor ']'",
"comando : TK_WRITE expr",
"comando : TK_WRITE STRING ',' expr",
"comando : TK_WRITE expr ',' STRING",
"comando : TK_WRITE STRING ',' expr ',' STRING ',' expr",
"comando : TK_DECL expr ':' TK_REAL",
"comando : TK_DECL expr ':' TK_REAL '[' exprVetor ']'",
"comando : TK_DECL expr ':' TK_BOOL",
"comando : IDENTIFICADOR TK_ATRIBUICAO TK_TRUE",
"comando : IDENTIFICADOR TK_ATRIBUICAO TK_FALSE",
"exprBinario :",
"exprBinario : IDENTIFICADOR",
"exprBinario : NUMERO",
"exprBinario : PONTO_FLUTUANTE",
"exprBinario : IDENTIFICADOR '[' expr ']'",
"exprVetor :",
"exprVetor : IDENTIFICADOR",
"exprVetor : NUMERO",
"exprCondicao :",
"exprCondicao : exprCondicao TK_AND exprCondicao",
"exprCondicao : exprCondicao TK_OR exprCondicao",
"exprCondicao : TK_NOT exprBinario",
"exprCondicao : exprBinario '<' exprBinario",
"exprCondicao : exprBinario '>' exprBinario",
"exprCondicao : exprBinario TK_MAIORIGUAL exprBinario",
"exprCondicao : exprBinario TK_MENORIGUAL exprBinario",
"exprCondicao : exprBinario '=' exprBinario",
"exprCondicao : exprBinario TK_DIFERENTE exprBinario",
"exprInt :",
"exprInt : exprInt ',' exprInt",
"exprInt : exprInt '+' exprInt",
"exprInt : exprInt '-' exprInt",
"exprInt : exprInt '*' exprInt",
"exprInt : exprInt '/' exprInt",
"exprInt : expr '=' expr",
"exprInt : NUMERO",
"exprFloat :",
"exprFloat : exprFloat ',' exprFloat",
"exprFloat : exprFloat '+' exprFloat",
"exprFloat : exprFloat '-' exprFloat",
"exprFloat : exprFloat '*' exprFloat",
"exprFloat : exprFloat '/' exprFloat",
"exprFloat : expr '=' expr",
"exprFloat : PONTO_FLUTUANTE",
"exprBool :",
"expr :",
"expr : IDENTIFICADOR",
"expr : expr ',' expr",
"expr : expr '+' expr",
"expr : expr '-' expr",
"expr : expr '*' expr",
"expr : expr '/' expr",
"expr : '(' expr ')'",
"expr : IDENTIFICADOR '(' ')'",
"expr : IDENTIFICADOR '[' expr ']'",
"expr : expr '=' expr",
"expr : exprInt",
"expr : exprFloat",
"expr : exprBinario TK_AND exprBinario",
"expr : exprBinario TK_OR exprBinario",
"expr : TK_NOT exprBinario",
"expr : exprBinario '<' exprBinario",
"expr : exprBinario '>' exprBinario",
"expr : exprBinario TK_MAIORIGUAL exprBinario",
"expr : exprBinario TK_MENORIGUAL exprBinario",
"expr : exprBinario '=' exprBinario",
"expr : exprBinario TK_DIFERENTE exprBinario",
};

//#line 150 "Parser.y"
/* PARTE INTERNA DA CLASSE */

private ASTNo raiz;

private ScannerCompiladorX scanner;

public ParserCompiladorX(Reader reader) {
    scanner = new ScannerCompiladorX(reader,this);
}

private int yylex() throws IOException {
    return scanner.yylex();
}

private void yyerror(String msg) throws Exception {
    throw new Exception(msg);
}

private ASTComando buscarUltimoComando(ASTComando cmd) {
	while(cmd.getProximo() != null) {
		cmd = cmd.getProximo();
	}

	return cmd;
}

public void interpretar() throws Exception {
    yydebug=true;
	yyparse();
	raiz.interpretar(new HashMap<String,Object>());
}

public void compilar() throws Exception {
	HashSet<String> tabelaSimbolo = new HashSet<String>();
	PrintWriter printWriter;
    String output;
	String saida;

	yyparse();
	saida = raiz.compilar(tabelaSimbolo);
	
	output  = "#include <stdio.h> " + "\n";
	output += "#include <stdlib.h> " + "\n";
	
        output += "enum boolean{true = 1, false = 0};typedef  enum boolean  bool;\n";
        
        output += "int main() { " + "\n";
	

	for(int i=0;i<tabelaSimbolo.size();i++) {
		output += tabelaSimbolo.toArray()[i];

                System.out.println("Posicao " + i + ":" + tabelaSimbolo.toArray()[i]);
		if(i < tabelaSimbolo.size()-1) {
                        
			output += ",";
		}
	}

        
        output += saida;
        //output += ";";
	output += "\n" + "return 0;";
        output += "\n";
        output += "}";

	printWriter = new PrintWriter("/tmp/cOutput.c","UTF-8");
	printWriter.print(output);
	printWriter.close();
}
//#line 624 "ParserCompiladorX.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
throws Exception
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 27 "Parser.y"
{ raiz = val_peek(0); }
break;
case 2:
//#line 30 "Parser.y"
{ yyval = val_peek(1); }
break;
case 3:
//#line 31 "Parser.y"
{ yyval = val_peek(2); buscarUltimoComando((ASTComando)yyval).setProximo((ASTComando)val_peek(1)); }
break;
case 5:
//#line 35 "Parser.y"
{ yyval = new ASTWriteString(((Token)val_peek(0)).getLexema()); }
break;
case 6:
//#line 36 "Parser.y"
{ yyval = new ASTRead(((Token)val_peek(0)).getLexema()); }
break;
case 7:
//#line 37 "Parser.y"
{ yyval = new ASTIf((ASTExpressao)val_peek(3),(ASTComando)val_peek(1)); }
break;
case 8:
//#line 38 "Parser.y"
{ yyval = new ASTIf((ASTExpressao)val_peek(5),(ASTComando)val_peek(3),(ASTComando)val_peek(1)); }
break;
case 9:
//#line 39 "Parser.y"
{ yyval = new ASTFor((ASTExpressao)val_peek(7),(ASTExpressao)val_peek(5),(ASTExpressao)val_peek(3),(ASTComando)val_peek(1)); }
break;
case 10:
//#line 40 "Parser.y"
{  yyval = new ASTWhile((ASTExpressao)val_peek(3),(ASTComando)val_peek(1));  }
break;
case 11:
//#line 42 "Parser.y"
{ yyval = new ASTAtribuicao(((Token)val_peek(2)).getLexema(),(ASTExpressao)val_peek(0)); }
break;
case 12:
//#line 44 "Parser.y"
{ yyval = new ASTAtribuicaoVetId(((Token)val_peek(5)).getLexema(),(ASTExpressao)val_peek(0),(ASTExpressao)val_peek(3)); }
break;
case 13:
//#line 45 "Parser.y"
{ yyval = new ASTAtribuicaoIdVet(((Token)val_peek(5)).getLexema(),((Token)val_peek(3)).getLexema(),(ASTExpressao)val_peek(1)); }
break;
case 14:
//#line 46 "Parser.y"
{ yyval = new ASTAtribuicaoVetVet(((Token)val_peek(8)).getLexema(),(ASTExpressao)val_peek(6),((Token)val_peek(3)).getLexema(),(ASTExpressao)val_peek(1)); }
break;
case 15:
//#line 49 "Parser.y"
{ yyval = new ASTWriteExprInt((ASTExpressao)val_peek(0)); }
break;
case 16:
//#line 50 "Parser.y"
{ yyval = new ASTWriteStringExprInt(((Token)val_peek(2)).getLexema(),(ASTExpressao)val_peek(0)); }
break;
case 17:
//#line 51 "Parser.y"
{ yyval = new ASTWriteExprStringInt(((Token)val_peek(0)).getLexema(),(ASTExpressao)val_peek(2)); }
break;
case 18:
//#line 52 "Parser.y"
{ yyval = new ASTWriteSESEInt(((Token)val_peek(6)).getLexema(),(ASTExpressao)val_peek(4), ((Token)val_peek(2)).getLexema(),(ASTExpressao)val_peek(0)); }
break;
case 19:
//#line 53 "Parser.y"
{ yyval = new ASTDeclInt((ASTExpressao)val_peek(2)); }
break;
case 20:
//#line 54 "Parser.y"
{ yyval = new ASTDeclIntVetor((ASTExpressao)val_peek(5),(ASTExpressao)val_peek(1)); }
break;
case 21:
//#line 57 "Parser.y"
{ yyval = new ASTWriteExpr((ASTExpressao)val_peek(0)); }
break;
case 22:
//#line 58 "Parser.y"
{ yyval = new ASTWriteStringExpr(((Token)val_peek(2)).getLexema(),(ASTExpressao)val_peek(0)); }
break;
case 23:
//#line 59 "Parser.y"
{ yyval = new ASTWriteExprString(((Token)val_peek(0)).getLexema(),(ASTExpressao)val_peek(2)); }
break;
case 24:
//#line 60 "Parser.y"
{ yyval = new ASTWriteSESE(((Token)val_peek(6)).getLexema(),(ASTExpressao)val_peek(4), ((Token)val_peek(2)).getLexema(),(ASTExpressao)val_peek(0)); }
break;
case 25:
//#line 61 "Parser.y"
{ yyval = new ASTDeclReal((ASTExpressao)val_peek(2)); }
break;
case 26:
//#line 62 "Parser.y"
{ yyval = new ASTDeclRealVetor((ASTExpressao)val_peek(5),(ASTExpressao)val_peek(1)); }
break;
case 27:
//#line 66 "Parser.y"
{ yyval = new ASTDeclBool((ASTExpressao)val_peek(2)); }
break;
case 28:
//#line 67 "Parser.y"
{ yyval = new ASTAtribuicaoTrue(((Token)val_peek(2)).getLexema()); }
break;
case 29:
//#line 68 "Parser.y"
{ yyval = new ASTAtribuicaoFalse(((Token)val_peek(2)).getLexema()); }
break;
case 31:
//#line 73 "Parser.y"
{ yyval = new ASTAcessoVariavel(((Token)val_peek(0)).getLexema()); }
break;
case 32:
//#line 74 "Parser.y"
{ yyval = new ASTNumero(new Integer(((Token)val_peek(0)).getLexema())); }
break;
case 33:
//#line 75 "Parser.y"
{ yyval = new ASTPontoFlutuante(new Double(((Token)val_peek(0)).getLexema())); }
break;
case 34:
//#line 76 "Parser.y"
{ yyval = new ASTVetor((ASTExpressao)val_peek(1),((Token)val_peek(3)).getLexema()); }
break;
case 36:
//#line 80 "Parser.y"
{ yyval = new ASTAcessoVariavel(((Token)val_peek(0)).getLexema()); }
break;
case 37:
//#line 81 "Parser.y"
{ yyval = new ASTNumero(new Integer(((Token)val_peek(0)).getLexema())); }
break;
case 39:
//#line 85 "Parser.y"
{ yyval = new ASTAnd((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 40:
//#line 86 "Parser.y"
{ yyval = new ASTOr((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 41:
//#line 87 "Parser.y"
{ yyval = new ASTNot((ASTExpressao)val_peek(0)); }
break;
case 42:
//#line 88 "Parser.y"
{ yyval = new ASTMenor((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 43:
//#line 89 "Parser.y"
{ yyval = new ASTMaior((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 44:
//#line 90 "Parser.y"
{ yyval = new ASTMaiorIgual((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 45:
//#line 91 "Parser.y"
{ yyval = new ASTMenorIgual((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 46:
//#line 92 "Parser.y"
{ yyval = new ASTCompara((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 47:
//#line 93 "Parser.y"
{ yyval = new ASTDiferente((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 49:
//#line 97 "Parser.y"
{ yyval = new ASTSeparacao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 50:
//#line 98 "Parser.y"
{ yyval = new ASTSoma((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 51:
//#line 99 "Parser.y"
{ yyval = new ASTSubtracao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 52:
//#line 100 "Parser.y"
{ yyval = new ASTMultiplicacao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 53:
//#line 101 "Parser.y"
{ yyval = new ASTDivisao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 54:
//#line 102 "Parser.y"
{ yyval = new ASTCompara((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0));}
break;
case 55:
//#line 103 "Parser.y"
{ yyval = new ASTNumero(new Integer(((Token)val_peek(0)).getLexema())); }
break;
case 57:
//#line 107 "Parser.y"
{ yyval = new ASTSeparacao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 58:
//#line 108 "Parser.y"
{ yyval = new ASTSoma((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 59:
//#line 109 "Parser.y"
{ yyval = new ASTSubtracao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 60:
//#line 110 "Parser.y"
{ yyval = new ASTMultiplicacao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 61:
//#line 111 "Parser.y"
{ yyval = new ASTDivisao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 62:
//#line 112 "Parser.y"
{ yyval = new ASTCompara((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0));}
break;
case 63:
//#line 113 "Parser.y"
{ yyval = new ASTPontoFlutuante(new Double(((Token)val_peek(0)).getLexema())); }
break;
case 66:
//#line 121 "Parser.y"
{ yyval = new ASTAcessoVariavel(((Token)val_peek(0)).getLexema()); }
break;
case 67:
//#line 123 "Parser.y"
{ yyval = new ASTSeparacao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 68:
//#line 124 "Parser.y"
{ yyval = new ASTSoma((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 69:
//#line 125 "Parser.y"
{ yyval = new ASTSubtracao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 70:
//#line 126 "Parser.y"
{ yyval = new ASTMultiplicacao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 71:
//#line 127 "Parser.y"
{ yyval = new ASTDivisao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 72:
//#line 128 "Parser.y"
{ yyval = val_peek(1); }
break;
case 73:
//#line 129 "Parser.y"
{ yyval = new ASTFuncao(((Token)val_peek(2)).getLexema()); }
break;
case 74:
//#line 130 "Parser.y"
{ yyval = new ASTVetor((ASTExpressao)val_peek(1),((Token)val_peek(3)).getLexema()); }
break;
case 75:
//#line 131 "Parser.y"
{ yyval = new ASTCompara((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0));}
break;
case 76:
//#line 133 "Parser.y"
{ yyval = val_peek(0); }
break;
case 77:
//#line 134 "Parser.y"
{ yyval = val_peek(0); }
break;
case 78:
//#line 138 "Parser.y"
{ yyval = new ASTAnd((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 79:
//#line 139 "Parser.y"
{ yyval = new ASTOr((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 80:
//#line 140 "Parser.y"
{ yyval = new ASTNot((ASTExpressao)val_peek(0)); }
break;
case 81:
//#line 141 "Parser.y"
{ yyval = new ASTMenor((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 82:
//#line 142 "Parser.y"
{ yyval = new ASTMaior((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 83:
//#line 143 "Parser.y"
{ yyval = new ASTMaiorIgual((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 84:
//#line 144 "Parser.y"
{ yyval = new ASTMenorIgual((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 85:
//#line 145 "Parser.y"
{ yyval = new ASTCompara((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 86:
//#line 146 "Parser.y"
{ yyval = new ASTDiferente((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
//#line 1085 "ParserCompiladorX.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
//## The -Jnorun option was used ##
//## end of method run() ########################################



//## Constructors ###############################################
//## The -Jnoconstruct option was used ##
//###############################################################



}
//################### END OF CLASS ##############################
