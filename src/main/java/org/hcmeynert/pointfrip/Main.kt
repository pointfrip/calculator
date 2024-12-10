package org.hcmeynert.pointfrip

// import java.math.BigDecimal
// import java.math.RoundingMode

public var runvm: Boolean = false

// ----- constants

const val ctquote: String   = "'"
const val ctquote2: String  = "" + 34.toChar()
const val ctivar: String    = "#"
const val ctdef: String     = "=="
const val ctddot: String    = ".."
const val ctcomment: String = "//"
const val ctcomp: String    = "°"
const val ctcombine: String = "Combine"
const val cterror: String   = "Error"
const val ctact: String     = "Act"
const val cttrue: String    = "true" // ???
const val ctfalse: String   = "false" // ???
const val ctspecial: String = "()[]{},;" + ctquote + ctquote2 + ctivar + ctcomp // unvollständig
const val ctnumber: String  = "0123456789_."
const val ctcapital: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ"  // unvollständig
const val ctsmall:  String  = "abcdefghijklmnopqrstuvwxyzäöüß" // unvollständig
const val ctnormal: String  = ctnumber + ctsmall + ctcapital

// ----- error messages

const val ecomddotnoend: String   = ".. expects only one subsequent value"
const val ecomnbconsnoduo: String = "Backchaining awaits a duo in the compiler stack"
const val einitarrayoverflow: String = "the pcounter exceeds the array size"
const val ecomtableunexpend: String = "unexpected ending, ) missing"
const val ecomonlybracketend: String = "] without [ in front"
const val ecomonlycurlyend: String = "} without { before"
const val ecomddotnoinfix: String = ".. not allowed at infix position"
const val ecomintnoint: String = "Integer value in [ ] missing"
const val ecomintexpint: String = "Integer value expected in [...]"
const val ecomintnobracketend: String = "] missing in integer value"
const val ecomquoteunexpend: String = "unexpected end at Quote"
const val ecomonlyparenend: String = ") without ( before"
const val ecomquoteunexpddot: String = "unexpected .. in Quote"
const val ecomstringunexpend: String = "unexpected end of String scan"
const val ecomivarunexpend: String = "unexpected end at Ivar"
const val ecomivarnosyntax: String = "no Ivar-Syntax: #"
const val ecomivaridentexp: String = "expected the type Ident for Ivar syntax"

const val eselnotinrange: String = "Access outside the table"
const val eseltableexp: String = "Expected a Table for access"
const val eevalcalcstop: String  = "CALC-STOP"
const val eevalnoprim: String = "Primitive not in FuncTable"
const val eevalidentunbound: String = "Ident is not bound"

const val ecbnocombine: String = "Operation expects the type Combine"
const val ecbprop2exp: String = "Prop expected with 2 elements as Term"
const val ecbpropastermexp: String = "Prop expected as Term"
const val ecbop1expprop: String = "Prop expected for operand[1]"
const val ecbconsexp: String = "; expected"
const val ecblistorcombiexp: String = "List or Combine type expected"

const val eopboolasop0exp: String = "Bool expected for operand[0]"
const val eopboolasop1exp: String = "Bool expected for operand[1]"
const val eoppropasop0exp: String = "Prop expected for operand[0]"
const val eoppropasop1exp: String = "Prop expected for operand[1]"
const val eopnumasop0exp: String = "Number expected as operand[0]"
const val eopnumasop1exp: String = "Number expected for operand[1]"
const val eopintasop0exp: String = "Integer expected as operand[0]"
const val eopintasop1exp: String = "Integer expected as operand[1]"
const val eoprealasop0exp: String = "Real expected as operand[0]"
const val eoprealasop1exp: String = "Real expected as operand[1]"
const val eopidentasop0exp: String = "Ident type expected for operand[0]"
const val eopidentasop1exp: String = "Ident type expected for operand[1]"
const val eopintoractasop0exp: String = "Integer or Act expected as operand[0]"
const val eopdictasop1exp: String = "Dict expected as operand[1]"
const val eopactasop0exp: String = "Act expected for operand[0]"
const val eoptableorlistasop0exp: String = "Table or list expected as operand[0]"
const val eoppropornullasop0exp: String = "Prop or null expected as operand[0]"
const val eoplistasop0exp: String = "List expected for operand[0]"
const val eoplistasop1exp: String = "List expected for operand[1]"
const val eopidentasfstofop1exp: String = "Ident expected for [0]°operand[1]"
const val eoplist2asop1exp: String = "List with 2 elements expected for operand[1]"
const val eopstringasop0exp: String = "String expected as operand[0]"
const val eopstringasop1exp: String = "String expected as operand[1]"
const val eopdivbyzero: String = "Division by zero"
const val eoptypenocompare: String = "Types not comparable"
const val eopunknownerr: String = "unknown error"

const val efnundef: String = "Function is not defined"
const val efnnocombine: String = "Function expects the type Combine"
const val efnerrinrev: String = "Error in reverse"
const val efnlist3exp: String = "Expected list of 3 items"
const val efnnumexp: String = "Number expected as type"
const val efnstringexp: String = "String expected as type"
const val efnidentexp: String = "Ident expected as type"
const val efnboolexp: String = "Bool expected as type"
const val efnpropnullstringexp: String = "Prop, null or string expected as type"
const val efnselnotinrange: String = "Access outside the table"
const val efnseltableexp: String = "Expected for access a Table"
const val efntableforidentgetexp: String = "Table expected for Ident-Get"
const val efntableforgetexp: String = "Table expected for Get"
const val efndictexp: String = "Dict expected as argument"
const val efnstringasarg0exp: String = "String expected for argument[0]"
const val efnnumasarg1exp: String = "Number expected for argument[1]"
const val efnnumasarg2exp: String = "Number expected for argument[2]"
const val efnlistofstringsexp: String = "List of strings expected"
const val efnunknownerr: String = "unknown error"

// -------------------------------------- datatypes as classes ---------------------------------------

class Nil {
    constructor ()
}

class Ident {
    val pname: String
    var value: Any
    var info: String
    constructor (pn: String, v: Any) { pname = pn;  value = v;  info = "" }
}

class Cell {
    var infix: Any
    var head: Any
    var tail: Any
    constructor (hd: Any, inf: Any, tl: Any) { infix = inf;  head = hd;  tail = tl }
}

class Quote {
    val value: Any
    constructor (qt: Any) { value = qt }
}

class Ivar {
    val value: Ident
    constructor (iv: Ident) { value = iv }
}

/* class Comment {
    val value: Any    // ==> //"comment..." ???????
    constructor (cv: Any) { value = cv }
} */

class Combine {
    val term: Any
    val arg: Any
    constructor (tm: Any, ag: Any) { term = tm;  arg = ag }
}

class Error {
    val eident: Any
    val value: Any
    constructor (id: Any, str: Any) { eident = id; value = str }
}

class Act {
    val num: Any
    val data: Any
    var bind: Any
    constructor (n: Any, dt: Any, bd: Any) { num = n; data = dt; bind = bd }
}

class VirtualMachine {

// ----- initialisation

    var maxfunc: Long = 500
    // var func: Array<(Any)->Any> = Array(maxfunc, { initElement(it) })
    var func: Array<() -> Unit> = Array(maxfunc.toInt(), { initElement(it) })

    var pcounter: Long = 0
    var identlist: Any = Nil()

    var xcons: Ident = Ident(";",Nil())
    var idundef: Ident = newidentfunc("undef",::fundef)
    var idid: Ident = newidentfunc("id",::fid)
    var idhead: Ident = newidentfunc("head",::fhead)
    var idtail: Ident = newidentfunc("tail",::ftail)
    var idinfix: Ident = newidentfunc("infix",::finfix)
    var idprop: Ident = newidentfunc("prop",::fprop)
    var idterm: Ident = newidentfunc("term",::fterm)
    var idarg: Ident = newidentfunc("arg",::farg)
    var idtype: Ident = newidentfunc("type",::ftype)
    var idat: Ident = newidentfunc("at",::fat)
    var idee: Ident = newidentfunc("ee",::fee)
    var idswee: Ident = newidentfunc("swee",::fswee)
    var idcomma: Ident = newidentfunc(",",::fcomma)
    var idapp: Ident = newidentfunc("app",::fapp)
    var idadd: Ident = newidentfunc("+",::fadd)
    var idsub: Ident = newidentfunc("-",::fsub)
    var idmul: Ident = newidentfunc("*",::fmul)
    var idmul2: Ident = newidentfunc("×",::fmul)
    var iddiv: Ident = newidentfunc("/",::fdiv)
    var iddiv2: Ident = newidentfunc("÷",::fdiv)
    var idpow: Ident = newidentfunc("^",::fpow)
    var ididiv: Ident = newidentfunc("idiv",::fidiv)
    var idimod: Ident = newidentfunc("imod",::fimod)
    var idpred: Ident = newidentfunc("pred",::fpred)
    var idsucc: Ident = newidentfunc("succ",::fsucc)
    var idsign: Ident = newidentfunc("sign",::fsign)
    var idabs: Ident = newidentfunc("abs",::fabs)
    var idneg: Ident = newidentfunc("neg",::fneg)
    var idneg2: Ident = newidentfunc("_",::fneg)
    var idfloor: Ident = newidentfunc("floor",::ffloor)
    var idceil: Ident = newidentfunc("ceil",::fceil)
    var idfloat: Ident = newidentfunc("float",::ffloat)
    var idround: Ident = newidentfunc("round",::fround)
    var idtrunc: Ident = newidentfunc("trunc",::ftrunc)  // ?
    var idroundto: Ident = newidentfunc("roundto",::froundto)
    var idexp: Ident = newidentfunc("exp",::fexp)
    var idln: Ident = newidentfunc("ln",::fln)
    var idlg: Ident = newidentfunc("lg",::flg)
    var idsq: Ident = newidentfunc("sq",::fsq)
    var idsqrt: Ident = newidentfunc("sqrt",::fsqrt)
    var idcbrt: Ident = newidentfunc("cbrt",::fcbrt)
    var idpi: Ident = newidentfunc("pi",::fpi)
    var id2pi: Ident = newidentfunc("2pi",::f2pi)
    var idsin: Ident = newidentfunc("sin",::fsin)
    var idcos: Ident = newidentfunc("cos",::fcos)
    var idtan: Ident = newidentfunc("tan",::ftan)
    var idarcsin: Ident = newidentfunc("arcsin",::farcsin)
    var idarccos: Ident = newidentfunc("arccos",::farccos)
    var idarctan: Ident = newidentfunc("arctan",::farctan)
    var idarctan2: Ident = newidentfunc("arctan2",::farctan2)
    var idsinh: Ident = newidentfunc("sinh",::fsinh)
    var idcosh: Ident = newidentfunc("cosh",::fcosh)
    var idtanh: Ident = newidentfunc("tanh",::ftanh)
 // var idrandom: Ident = newidentfunc("random",::frandom)
    var iddeg: Ident = newidentfunc("deg",::fdeg)
    var idrad: Ident = newidentfunc("rad",::frad)
    var ideq: Ident = newidentfunc("=",::feq)
    var idneq: Ident = newidentfunc("!=",::fneq)
    var idneq2: Ident = newidentfunc("<>",::fneq)
    var idlt: Ident = newidentfunc("<",::flt)
    var idgt: Ident = newidentfunc(">",::fgt)
    var idle: Ident = newidentfunc("<=",::fle)
    var idge: Ident = newidentfunc(">=",::fge)
    var idmin: Ident = newidentfunc("min",::fmin)
    var idmax: Ident = newidentfunc("max",::fmax)
    var idnot: Ident = newidentfunc("not",::fnot)
    var idand: Ident = newidentfunc("and",::fand)
    var idor: Ident = newidentfunc("or",::f_or)
    var idxor: Ident = newidentfunc("xor",::fxor)
    var idisatom: Ident = newidentfunc("isatom",::fisAtom)
    var idisnull: Ident = newidentfunc("isnull",::fisNull)
    var idisprop: Ident = newidentfunc("isprop",::fisProp)
    var idislist: Ident = newidentfunc("islist",::fisList)
    var idisnum: Ident = newidentfunc("isnum",::fisNum)
    var idiszero: Ident = newidentfunc("iszero",::fisZero)
    var idispos: Ident = newidentfunc("ispos",::fisPos)
    var idisneg: Ident = newidentfunc("isneg",::fisNeg)
    var idisident: Ident = newidentfunc("isident",::fisIdent)
    var idisint: Ident = newidentfunc("isint",::fisInt)
    var idisreal: Ident = newidentfunc("isreal",::fisReal)
    var idisstring: Ident = newidentfunc("isstring",::fisString)
    var idiscons: Ident = newidentfunc("iscons",::fisCons)
    var idisquote: Ident = newidentfunc("isquote",::fisQuote)
    var idisivar: Ident = newidentfunc("isivar",::fisIvar)
    var idiscombi: Ident = newidentfunc("iscombi",::fisCombi)
    var idisact: Ident = newidentfunc("isact",::fisAct)
    var idisbool: Ident = newidentfunc("isbool",::fisBool)
    var idisbound: Ident = newidentfunc("isbound",::fisBound)
    var idisundef: Ident = newidentfunc("isundef",::fisundef)
    var idcons: Ident = newidentfunc("cons",::fcons)
    var idapplic: Ident = newidentfunc(":",::fapplic)
    var idcompose: Ident = newidentfunc("°",::fcompose)
    var idcompose2: Ident = newidentfunc("o",::fcompose)
    var idcompose3: Ident = newidentfunc("∘",::fcompose)
    var idcond: Ident = newidentfunc("->",::fcond)
    var idwhile: Ident = newidentfunc("->*",::fwhile)
    var idaa: Ident = newidentfunc("aa",::faa)
    var idins: Ident = newidentfunc("\\",::fins)
    var idtry: Ident = newidentfunc("try",::ftry)
    var idname: Ident = newidentfunc("name",::fname)
    var idbody: Ident = newidentfunc("body",::fbody)
    var idinfo: Ident = newidentfunc("info",::finfo)  // ??? noch nötig?
    var ididentlist: Ident = newidentfunc("identlist",::fidentlist)
    var idquote: Ident = newidentfunc("quote",::fquote)
    var iderror: Ident = newidentfunc("error",::ferror)
    var idcomp: Ident = newidentfunc("comp",::fcomp)
    var idact: Ident = newidentfunc("act",::fact)
    var idbind: Ident = newidentfunc("bind",::fbind)
    var idiota: Ident = newidentfunc("iota",::fiota)
    var idto: Ident = newidentfunc("to",::fto)
    var idreverse: Ident = newidentfunc("reverse",::freverse)
    var idmake: Ident = newidentfunc("make",::fmake)
    var idtake: Ident = newidentfunc("take",::ftake)
    var iddrop: Ident = newidentfunc("drop",::fdrop)
    var idpplus: Ident = newidentfunc("++",::fpplus)
    var idiget: Ident = newidentfunc("iget",::figet)
    var idiput: Ident = newidentfunc("iput",::fiput)
    var idget: Ident = newidentfunc("get",::fget)
    var idput: Ident = newidentfunc("put",::fput)
    var idkeys: Ident = newidentfunc("keys",::fkeys)
    var idvalues: Ident = newidentfunc("values",::fvalues)
    var idit: Ident = newidentfunc("it",::fit)
    var idcount: Ident = newidentfunc("count",::fcount)
    var idfind: Ident = newidentfunc("find",::ffind)
    var idin: Ident = newidentfunc("in",::fin)
    var idlength: Ident = newidentfunc("length",::flength)
    var idsubstring: Ident = newidentfunc("substring",::fsubstring)
    var idconcat: Ident = newidentfunc("&",::fconcat)
    var idconcat2: Ident = newidentfunc("concat",::fconcat)
    var idindexof: Ident = newidentfunc("indexof",::findexof)
    var idtrim: Ident = newidentfunc("trim",::ftrim)
    var idtriml: Ident = newidentfunc("triml",::ftriml)
    var idtrimr: Ident = newidentfunc("trimr",::ftrimr)
    var idupper: Ident = newidentfunc("upper",::fupper)
    var idlower: Ident = newidentfunc("lower",::flower)
    var idcapitalize: Ident = newidentfunc("capitalize",::fcapitalize)
    var idchar: Ident = newidentfunc("char",::fchar)
    var idunicode: Ident = newidentfunc("unicode",::funicode)
    var idparse: Ident = newidentfunc("parse",::fparse)
    var idvalue: Ident = newidentfunc("value",::fvalue)
    var idstring: Ident = newidentfunc("string",::fstring)
    var idunpack: Ident = newidentfunc("unpack",::funpack)
    var idsplit: Ident = newidentfunc("split",::fsplit)
    var idjoin: Ident = newidentfunc("join",::fjoin)
    var idstopvm: Ident = newidentfunc("stopvm",::fstopvm)
    var iddump: Ident = newidentfunc("dump",::fdump)
    var idsavedump: Ident = newidentfunc("savedump",::fsavedump) // for test
    var idhelpinfo: Ident = newidentfunc("helpinfo",::fhelpinfo)

// ----- no-func-idents

    var xnil: Any = Nil()
    var xreserve: Ident = identlistPut("_reserve",Nil())
    var xundef: Ident = identlistPut("_undef",xreserve)
    var xnull: Ident = identlistPut("_null",xreserve)
    var xprop: Ident = identlistPut("_prop",xreserve)
    var xinteger: Ident = identlistPut("_integer",xreserve)
    var xreal: Ident = identlistPut("_real",xreserve)
    var xstring: Ident = identlistPut("_string",xreserve)
    var xident: Ident = identlistPut("_ident",xreserve)
    var xquote: Ident = identlistPut("_quote",xreserve)
    var xivar: Ident = identlistPut("_ivar",xreserve)
    // xcomment
    var xcombine: Ident = identlistPut("_combine",xreserve)
    // xerror
    var xact: Ident = identlistPut("_act",xreserve)
    var xbool: Ident = identlistPut("_bool",xreserve)
    var xmark: Ident = Ident("_mark",Nil())  // besser nicht in identlist
    var iddef: Ident = identlistPut(ctdef,idid) // xreserve)
    var xddot: Ident = Ident(ctddot,Nil())       // besser nicht in identlist
    var idipr: Ident = Ident("interpreter",Nil()) // ?
    var xsingle: Ident = identlistPut("_s",Nil())
    var xsuper: Ident = identlistPut("_super",Nil())
    var xit: Ident = identlistPut("_it",Nil())  // Nil?
    var xself: Ident = identlistPut("_self",Nil())
    var xpara: Ident = identlistPut("_para",Nil())


    constructor () {
        init()
    }

    fun init() {
        xcons.value = idcons.value
        identlist = Cell(xcons,xcons,identlist)
        //
    }

    // fun initElement(x: Int): (Any) -> Any { return ::fundef }
    fun initElement(x: Int): () -> Unit { return ::fundef }

    fun newidentfunc(pn: String, fn: ()->Unit ): Ident {
        pcounter = pcounter + 1
        if (pcounter >= maxfunc) throw Exception(einitarrayoverflow)
        func[pcounter.toInt()] = fn
        val id: Ident = Ident(pn,-pcounter)
        identlist = Cell(id,xcons,identlist)
        return id  }

    fun identlistPut(pn: String, v: Any): Ident {
        val id: Ident = Ident(pn,v)
        identlist = Cell(id,xcons,identlist)
        return id  }

// ------------------------------------------ list-to-text ------------------------------------------

    fun toTable(t: Any): String {
        var str: String = ""
        var sp: String = ""
        var i: Any = t
        while (i !is Nil) {
            if (i is Cell) {
                str = str + sp + toValue(i.head) + " " + toValue(i.infix)
                i = i.tail }
            else return str + sp + ".. " + toValue(i)
            sp = " " }
        return str  }

    fun toDoubleString(x: Any): String {
        val str = x.toString().replace("-","_")
        val len = str.length
        if (len>=2) { if (str.substring(len-2,len) == ".0") return str.substring(0,len-2) }
        return str  }

    /* fun toCommentString(x: Any): String {
        if (x is Ident) return "// " + toValue(x)
        else return "//" + toValue(x)    // noch Probleme ?
    } */

    fun toValue(i: Any): String {
        when (i) {
            is Nil     -> return "( )"
            is Cell    -> return "(" + toTable(i) + ")"
            is Int     -> return "[ " + i.toString().replace("-","_") + " ]"
            is Long    -> return "[" + i.toString().replace("-","_") + "]"
            is Double  -> return toDoubleString(i) // i.toString().replace("-","_")
            is String  -> return ctquote2 + i + ctquote2
            is Ident   -> return i.pname
            is Quote   -> return ctquote + toValue(i.value)
            is Ivar    -> return ctivar + toValue(i.value)
            // is Comment -> return toCommentString(i.value)
            is Combine -> return "{"+ctcombine+" "+toValue(i.term)+" "+toValue(i.arg)+"}"
            is Error   -> return "{"+cterror+" "+toValue(i.eident)+" "+toValue(i.value)+"}"
            is Act     -> return "{"+ctact+" "+toValue(i.num)+" "+toValue(i.data)+" "+toValue(i.bind)+"}"
            true       -> return cttrue
            false      -> return ctfalse
            else       -> return "{printerror}"
        } }

// ----- service functions
    fun isLong(str: String) = (str.toLongOrNull() != null)
    fun isDouble(str: String) = (str.toDoubleOrNull() != null)

    // true und false extra compilieren !!!
    var ix: Int = 0
    var txt: String = ""
    var item: String = ""
    var cstack: Any = Nil()

// --------------------------------------------- scanner ---------------------------------------------

    fun scanItem(): String {
        var ch: String = " "
        var quit : Boolean
        while ((ix<txt.length) and (ch<=" ")) {
            ch = txt.substring(ix,ix+1)
            ix = ix.inc() }
        if (ch<=" ") return ""
        if (ctspecial.indexOf(ch)>=0) return ch
        ix = ix.dec()
        val k: Int = ix    // val k: Int ,für größere Zahlen ?
        if (ctnormal.indexOf(ch)>=0)
            do { ix = ix.inc()
                if (ix>=txt.length) { quit = true }
                else { ch = txt.substring(ix,ix+1)
                    quit = !(ctnormal.indexOf(ch)>=0) }
            } while (!quit)
        else do { ix = ix.inc()
            if (ix>=txt.length) { quit = true }
            else { ch = txt.substring(ix,ix+1)
                quit = (  (ctnormal.indexOf(ch)>=0)
                        or (ctspecial.indexOf(ch)>=0)
                        or (ch<=" ")  ) }
        } while (!quit)
        return txt.substring(k,ix) }

// -------------------------------------------- compiler ---------------------------------------------

    fun clistPut(x: Any) { cstack = Cell(x,xcons,cstack) }

    fun comComment() {  if (item == ctcomment) {  ix = txt.length;  item = ""  }  }

    fun cbackcons(i: Cell): Cell {
        var stack: Cell = i
        var p: Any
        var q: Any
        var ref: Any = Nil()
        while (stack.head!=xmark) {
            q = stack
            stack = stack.tail as Cell
            p = stack
            stack = stack.tail as Cell
            if (p.head==xddot) {
                if (ref !is Nil) throw Exception(ecomddotnoend)
                ref = q.head
            } else { p.tail = ref
                p.infix = q.head
                ref = p } }
        stack.head = ref
        return stack  }

    fun comTable() {
        cstack = Cell(xmark,xcons,cstack)
        item = scanItem()
        comComment()
        while (item!=")") {
            when (item) {  // head
                ""       -> throw Exception(ecomtableunexpend)
                "("      -> comTable()
                //  ")"    ---> while
                "["      -> comInt()
                "]"      -> throw Exception(ecomonlybracketend)
                "{"      -> comCurly()
                "}"      -> throw Exception(ecomonlycurlyend)
                ctquote  -> comQuote()
                ctquote2 -> comString()
                ctivar   -> comIvar()
                ctdef    -> clistPut(iddef) // ???
                ctddot   -> clistPut(xddot)
                cttrue   -> clistPut(true)
                ctfalse  -> clistPut(false)
                else     -> atom(item) }
            item = scanItem()
            comComment()
            when (item) {  // infix
                ""       -> throw Exception(ecomtableunexpend)
                "("      -> comTable()
                ")"      -> { clistPut(xsingle); break }
                "["      -> comInt()
                "]"      -> throw Exception(ecomonlybracketend)
                "{"      -> comCurly()
                "}"      -> throw Exception(ecomonlycurlyend)
                ctquote  -> comQuote()
                ctquote2 -> comString()
                ctivar   -> comIvar()
                ctdef    -> clistPut(iddef)
                ctddot   -> throw Exception(ecomddotnoinfix)
                cttrue   -> clistPut(true)
                ctfalse  -> clistPut(false)
                else     -> atom(item) }
            item = scanItem()
            comComment() }
        cstack = cbackcons(cstack as Cell)  }

    fun comInt() {
        item = scanItem()
        if (item=="]") throw Exception(ecomintnoint)
        val numstr: String = item.replace("_","-")
        if (isLong(numstr)) clistPut(numstr.toLong())
        else throw Exception(ecomintexpint)
        item = scanItem()
        if (item!="]") throw Exception(ecomintnobracketend)
    }

    fun comCurly() {
        clistPut("{")
    }

    fun comQuote() {
        item = scanItem()
        comComment()
        when (item) {
            ""       -> throw Exception(ecomquoteunexpend)
            "("      -> comTable()
            ")"      -> throw Exception(ecomonlyparenend)
            "["      -> comInt()
            "]"      -> throw Exception(ecomonlybracketend)
            "{"      -> comCurly()
            "}"      -> throw Exception(ecomonlycurlyend)
            ctquote  -> comQuote()
            ctquote2 -> comString()
            ctivar   -> comIvar()
            ctdef    -> clistPut(iddef) // ???
            ctddot   -> throw Exception(ecomquoteunexpddot)
            cttrue   -> clistPut(true)
            ctfalse  -> clistPut(false)
            else     -> atom(item) }
        (cstack as Cell).head = Quote((cstack as Cell).head)  }

    fun comString() {
        val k = ix
        do {  ix = ix.inc()  // backslash einbauen!
              if (ix>txt.length) throw Exception(ecomstringunexpend)
        } while (txt.substring(ix-1,ix)!=ctquote2)
        clistPut(txt.substring(k,ix-1))
    }

    fun comIvar() {
        item = scanItem()
        comComment()
        when (item) {
            ""      -> throw Exception(ecomivarunexpend)
            "(", ")", "[", "]", "{", "}", ctquote, ctquote2, ctivar, ctddot, cttrue,
            ctfalse -> throw Exception(ecomivarnosyntax+item+" ")
            else    -> atom(item)
        }
        if ((cstack as Cell).head is Ident)
            (cstack as Cell).head = Ivar((cstack as Cell).head as Ident)
        else throw Exception(ecomivaridentexp)
    }

    fun findIdent(str: String): Any {
        var seq: Any = identlist
        var found: Any = Nil()
        while ((seq is Cell) and (found is Nil)) {
            if (((seq as Cell).head as Ident).pname == str) found = seq.head
            else seq = seq.tail }
        return found  }

    fun toIdent(str: String): Any {
        val id: Any = findIdent(str)
        // println("find = ${toValue(id)}") // auskommentieren
        if (id is Nil) return identlistPut(str,xreserve)
        else return id  }

    fun atom(str: String) {
        val numstr: String = str.replace("_","-")
        if (isDouble(numstr)) clistPut(numstr.toDouble())
        else clistPut(toIdent(str))  }

    fun parse(str: String): Any {
        // var ix: Int = 0 // größere Zahlen?
        // var it: String
        // var cstack: Any = Nil()
        txt = str
        ix = 0
        cstack = Nil()
        item = scanItem()
        comComment()
        while (item != "") {
            when (item) {  // head
                //  ""      ---> while
                "("       -> comTable()
                ")"       -> throw Exception(ecomonlyparenend)
                "["       -> comInt()
                "]"       -> throw Exception(ecomonlybracketend)
                "{"       -> comCurly()
                "}"       -> throw Exception(ecomonlycurlyend)
                ctquote   -> comQuote()
                ctquote2  -> comString()
                ctivar    -> comIvar()
                ctdef     -> clistPut(iddef) // ??? oder exception oder _s einfügen?
                ctddot    -> clistPut(xddot)
                cttrue    -> clistPut(true)
                ctfalse   -> clistPut(false)
                else      -> atom(item) }
            item = scanItem()
            comComment()
            when (item) {  // infix
                ""        -> clistPut(xsingle)
                "("       -> comTable()
                ")"       -> throw Exception(ecomonlyparenend)
                "["       -> comInt()
                "]"       -> throw Exception(ecomonlybracketend)
                "{"       -> comCurly()
                "}"       -> throw Exception(ecomonlycurlyend)
                ctquote   -> comQuote()
                ctquote2  -> comString()
                ctivar    -> comIvar()
                ctdef     -> clistPut(iddef)
                ctddot    -> throw Exception(ecomddotnoinfix)
                cttrue    -> clistPut(true)
                ctfalse   -> clistPut(false)
                else      -> atom(item) }
            item = scanItem()
            comComment() }
        txt = ""
        item = ""
        val res: Any = cstack
        cstack = Nil()
        return nreverse(res)  }  // außerhalb try verwenden?

    fun nbackcons(i: Any): Any {
        var stack: Any = i
        var p: Any; var q: Any
        var ref: Any = Nil()
        while (stack is Cell) {
            q = stack
            stack = stack.tail
            if (stack is Cell) {
                p = stack
                stack = stack.tail
                if (p.head == xddot) {
                    if (ref !is Nil) throw Exception(ecomddotnoend)
                    ref = q.head
                } else { p.tail = ref
                    p.infix = q.head
                    ref = p }
            } else throw Exception(ecomnbconsnoduo)
        }
        return ref  }

    fun nreverse(i: Any): Any {
        var n: Any = i
        var p: Any
        var reseq: Any = Nil()
        while (n is Cell) {
            p = n
            n = n.tail
            p.tail = reseq
            reseq = p }
        return reseq  }

    fun reverse(i: Any): Any {
        var x: Any = i
        var y: Any = Nil()
        while (x is Cell) {  y = Cell(x.head,x.infix,y);  x = x.tail  }
        if (x is Error) return x
        else return y  }

// ------------------------------------------- interpreter -------------------------------------------
// ----- AST-Interpreter

    var eindex: Long = 0
    var ecall: Int = 0
    var efun: Any = Nil()    // ... nach Gebrauch auf Nil() setzen (?)
    var etop: Any = Nil()
    var equit: Boolean = true

    fun eselect(i: Long) {
        var found: Boolean = false
        var n: Long = i
        while ((etop is Cell) and !found) {
            if (n>0) {  etop = (etop as Cell).tail;  n = n - 1  }
            else     {  etop = (etop as Cell).head;  found = true  }
        }
        if (!found) {
            if (etop !is Error) {
                if (etop is Nil) etop = Error(idipr,eselnotinrange+" - ["+i+"] ")
                else etop = Error(idipr,eseltableexp)
        } } }

    fun eval() {
        var x: Any = xnil  // Nil()
        ecall = 0
        do {  equit = true
            if (!runvm) throw Exception(eevalcalcstop)
            when (efun) {
                is Cell    -> {  x = (efun as Cell).infix
                                 if (x == xsingle) efun = (efun as Cell).head
                                 else {  etop = Combine(efun,etop);  efun = x  }
                                 equit = false  }
                // xcons     -> econs() // ???
                is Ident   -> {  x = (efun as Ident).value
                                 if (x is Long) {
                                     eindex = x
                                     if (eindex >= 0) eselect(eindex)
                                     else if (eindex > -maxfunc) ecall = eindex.toInt()
                                     else etop = Error(idipr,eevalnoprim)
                                 } else if (x == xreserve) {
                                     etop = Error(idipr,eevalidentunbound+" - "+toValue(efun)+" ")
                                 } else {  efun = x  ;  equit = false  }
                              } // hier xcons prüfen (schneller) ?
                is Long    -> {  eindex = efun as Long
                                 if (eindex >= 0) eselect(eindex)
                                 else if (eindex > -maxfunc) ecall = eindex.toInt()
                                 else etop = Error(idipr,eevalnoprim)
                              }
                is Quote   -> {  if (etop !is Error) etop = (efun as Quote).value  }
                is Ivar    -> {  if (etop !is Error) etop = iget(idipr,etop,(efun as Ivar).value)  }
                //is Combine -> { }
                //is Act     -> { }
                else       -> {  if (etop !is Error) etop = efun  }  // is Double,Nil,String,true,false,Error
            }
        } while (!equit)
        // nach Gebrauch ... efun = xnil
    }

    fun econs() {
        when (etop) {
            is Combine -> etop = (etop as Combine).term
            is Error -> { } // etop = etop
            else -> etop = xcons.value
        } }

    fun run(fn: Any, top: Any): Any {
        runvm = true
        efun = fn
        etop = top
        do {  eval()
              if (ecall != 0) func[-ecall]()
        } while (!equit)
        val res: Any = etop
        runvm = false
        efun = Nil()
        etop = Nil()
        return res  }

// ----- primitive functions

    //  {Combine (func1 : ident1 _s) xx}
    fun fapplic() {
        when (etop) {
            is Combine -> {  val x: Combine = etop as Combine
                if (x.term is Cell) {
                    if (x.arg is Error) etop = x.arg
                    else {  etop = x.term.tail
                        if (etop is Cell) {
                            efun = x.term.head
                            etop = (etop as Cell).head
                            equit = false
                        } else etop = Error(idapplic,ecbprop2exp)
                    }
                } else etop = Error(idapplic,ecbpropastermexp)  }
            is Error   -> { }
            else       -> etop = Error(idapplic,ecbnocombine)
        }  }

    fun fcompose() {
        when (etop) {
            is Combine -> {  val x: Combine = etop as Combine
                if (x.term is Cell) {
                    efun = x.term.tail
                    etop = x.arg
                    if (etop !is Error) {
                        do {  eval()
                              if (ecall != 0) func[-ecall]()
                        } while (!equit)
                        if (etop !is Error) {  efun = x.term.head
                                               equit = false  }  }
                } else etop = Error(idcompose,ecbpropastermexp)  }
            is Error   -> { }
            else       -> etop = Error(idcompose,ecbnocombine)
        } }

    // {Combine (err try a ; b c) xyz}
    fun ftry() {
        when (etop) {
            is Combine -> {
                if ((etop as Combine).term is Cell) {
                    val t: Cell = (etop as Combine).term as Cell
                    val a: Any = (etop as Combine).arg
                    if (a is Error) etop = a
                    else {  efun = t.head
                            etop = a
                            do {  eval()
                                  if (ecall != 0) func[-ecall]()
                            } while (!equit)
                            val x: Any = etop
                            efun = t.tail
                            etop =  a
                            do {  eval()
                                  if (ecall != 0) func[-ecall]()
                            } while (!equit)
                            if (etop is Cell) {
                                if (x is Error) {
                                    efun = (etop as Cell).head
                                    etop = Cell(Cell(x.eident,iderror,x.value),xcons,Cell(a,xcons,Nil()))
                                    equit = false
                                } else {  efun = (etop as Cell).tail
                                          etop = Cell(x,xcons,Cell(a,xcons,Nil()))
                                          equit = false  }
                            } else if (etop !is Error)
                                       etop = Error(idtry,ecbop1expprop)  }
                } else etop = Error(idtry,ecbpropastermexp)  }
            is Error   -> { }
            else       -> etop = Error(idtry,ecbnocombine)
        }  }

    // {Combine (isprop -> a ; b c) xyz}
    fun fcond() {
        when (etop) {
            is Combine -> {
                if ((etop as Combine).term is Cell) {
                    val t: Cell = (etop as Combine).term as Cell
                    val a: Any = (etop as Combine).arg
                    if (a is Error) etop = a
                    else {  efun = t.head
                            etop = a
                            do {  eval()
                                  if (ecall != 0) func[-ecall]()
                            } while (!equit)
                            if (etop !is Error) {
                                val x: Any = etop
                                efun = t.tail
                                etop = a
                                do {  eval()
                                      if (ecall != 0) func[-ecall]()
                                } while (!equit)
                                if (etop is Cell) {
                                    when (x) {
                                        true  -> {  efun = (etop as Cell).head
                                                    etop = a
                                                    equit = false  }
                                        false -> {  efun = (etop as Cell).tail
                                                    etop = a
                                                    equit = false  }
                                        else  -> etop = Error(idcond,eopboolasop0exp)
                                } } else if (etop !is Error)
                                             etop = Error(idcond,eoppropasop1exp)
                            } }
                } else etop = Error(idcond,ecbpropastermexp)  }
            is Error   -> { }
            else       -> etop = Error(idcond,ecbnocombine)
        } }

    // {Combine (p ->* b ) a}
    fun fwhile() {
        when (etop) {
            is Combine -> {
                if ((etop as Combine).term is Cell) {
                    val p: Any = ((etop as Combine).term as Cell).head
                    val b: Any = ((etop as Combine).term as Cell).tail
                    var a: Any = (etop as Combine).arg
                    if (a is Error) etop = a
                    else {  efun = p
                            etop = a
                            do {  eval()
                                  if (ecall != 0) func[-ecall]()
                            } while (!equit)
                            if (etop !is Error) {
                                if (etop is Boolean) {
                                    while (etop as Boolean) {
                                        efun = b
                                        etop = a
                                        do {  eval()
                                              if (ecall != 0) func[-ecall]()
                                        } while (!equit)
                                        if (etop is Error) break
                                        a = etop
                                        efun = p
                                        do {  eval()
                                              if (ecall != 0) func[-ecall]()
                                        } while (!equit)
                                        if (etop is Error) break
                                        if (etop !is Boolean) {
                                            etop = Error(idwhile,eopboolasop0exp)
                                            break
                                        }  }
                                    if (etop !is Error) etop = a
                                } else etop = Error(idwhile,eopboolasop0exp)
                            }  }
                } else etop = Error(idwhile,ecbpropastermexp)  }
            is Error   -> { }
            else       -> etop = Error(idwhile,ecbnocombine)
        }  }

    // {combine (f aa) list}
    fun faa() {
        when (etop) {
            is Combine -> {  if ((etop as Combine).term is Cell) {
                                 val f = ((etop as Combine).term as Cell).head
                                 var list = (etop as Combine).arg
                                 var res: Any = Nil()
                                 if (list is Error) etop = list
                                 else {  while (list is Cell) {
                                               efun = f
                                               etop = list.head
                                               do {  eval()
                                                     if (ecall != 0) func[-ecall]()
                                               } while (!equit)
                                               if (etop is Error) break
                                               res = Cell(etop,xcons,res)
                                               list = list.tail  }
                                         if (etop !is Error) etop = nreverse(res)  }
                             } else etop = Error(idaa,ecbpropastermexp)  }
            is Error   -> { }
            else       -> etop = Error(idaa,ecbnocombine)
        } }

    // {Combine (f \) xyz}
    fun fins() {
        when (etop) {
            is Combine -> {
                if ((etop as Combine).term is Cell) {
                    val f: Any = ((etop as Combine).term as Cell).head
                    var list: Any = (etop as Combine).arg
                    if (list is Cell) {
                        list = reverse(list)
                        if (list is Cell) {
                            var res: Any = list.head
                            list = list.tail
                            while (list is Cell) {
                                efun = f
                                etop = Cell(list.head,xcons,Cell(res,xcons,Nil()))
                                do {  eval()
                                      if (ecall != 0) func[-ecall]()
                                } while (!equit)
                                if (etop is Error) break
                                res = etop
                                list = list.tail  }
                            if (etop !is Error) etop = res
                        } else etop = Error(idins,efnerrinrev)
                    } else if (list is Error) etop = list
                      else etop = xundef
                } else etop = Error(idins,ecbpropastermexp)  }
            is Error   -> { }
            else       -> etop = Error(idins,ecbnocombine)
        } }

    fun ee(ide: Ident) {
        when (etop) {
            is Combine -> {
                val x: Combine = etop as Combine
                if (x.term is Cell) {
                    efun = x.term.head
                    etop = x.arg
                    if (etop !is Error) {
                        do {  eval()
                              if (ecall != 0) func[-ecall]()
                        } while (!equit)
                        if (etop !is Error) {
                            val y: Any = etop
                            efun = x.term.tail
                            etop = x.arg
                            do {  eval()
                                  if (ecall != 0) func[-ecall]()
                            } while (!equit)
                            efun = y
                        }  }
                } else etop = Error(ide,ecbpropastermexp)
            }
            is Cell    -> {
                if ((etop as Cell).infix == xcons) {
                    efun = (etop as Cell).head
                    etop = (etop as Cell).tail
                    if (etop is Cell) {
                        if ((etop as Cell).infix == xcons) {
                            etop = (etop as Cell).head
                        } else if (etop !is Error) etop = Error(ide,ecbconsexp)
                    } else etop = Error(ide,ecbconsexp)
                } else etop = Error(ide,ecbconsexp)
            }
            is Error   -> { }
            else       -> etop = Error(ide,ecblistorcombiexp)
        } }

    fun fundef() {  if (etop !is Error) etop = Error(idipr,efnundef)  }

    fun fid()    {  }  // etop = etop

    fun fhead() {
        when (etop) {
            is Cell  -> etop = (etop as Cell).head
            is Error -> { }
            else     -> etop = Nil()
        } }

    fun ftail() {
        when (etop) {
            is Cell  -> etop = (etop as Cell).tail
            is Error -> { }
            else     -> etop = Nil()
        } }

    fun finfix() {
        when (etop) {
            is Cell  -> etop = (etop as Cell).infix
            is Error -> { }
            else     -> etop = Nil()
        } }

    fun fprop() {
        when (etop) {
            is Cell  -> {
                if ((etop as Cell).infix == xcons) {
                    val x: Any = (etop as Cell).head
                    etop = (etop as Cell).tail
                    if (etop is Cell) {
                        if ((etop as Cell).infix == xcons) {
                            val y: Any = (etop as Cell).head
                            etop = (etop as Cell).tail
                            if (etop is Cell) {
                                if ((etop as Cell).infix == xcons) {
                                    etop = Cell(x,y,(etop as Cell).head)
                                } else etop = Error(idprop,efnlist3exp)
                            } else if (etop !is Error) etop = Error(idprop,efnlist3exp)
                        } else etop = Error(idprop,efnlist3exp)
                    } else if (etop !is Error) etop = Error(idprop,efnlist3exp)
                } else etop = Error(idprop,efnlist3exp)  }
            is Error -> { }
            else     -> etop = Error(idprop,efnlist3exp)
        } }

    fun fterm() {
        when (etop) {
            is Combine -> etop = (etop as Combine).term
            is Error   -> { }
            else       -> etop = Error(idterm,efnnocombine)
        } }

    fun farg() {
        when (etop) {
            is Combine -> etop = (etop as Combine).arg
            is Error   -> { }
            else       -> etop = Error(idarg,efnnocombine)
        } }

    fun ftype() {
        when (etop) {
            is Nil     -> etop = xnull
            is Cell    -> etop = xprop
            is Long    -> etop = xinteger
            is Double  -> etop = xreal
            is String  -> etop = xstring
            is Ident   -> etop = xident
            is Quote   -> etop = xquote
            is Ivar    -> etop = xivar
            // is Comment
            is Combine -> etop = xcombine
            is Error   -> { }
            is Act     -> etop = xact
            true       -> etop = xbool
            false      -> etop = xbool
            else       -> etop = xundef
        } }

    fun selectAt(i: Long) {
        var found: Boolean = false
        var n: Long = i
        while ((etop is Cell) and !found) {
            if (n>0) {  etop = (etop as Cell).tail;  n = n - 1  }
            else     {  etop = (etop as Cell).head;  found = true  }
        }
        if (!found) {
            if (etop !is Error) {
                if (etop is Nil) etop = Error(idat,efnselnotinrange+" - "+toValue(i))
                else etop = Error(idat,efnseltableexp)
            } } }

    fun fat() {
        ee(idat)
        if (etop !is Error) {
            if (efun is Cell) {
                when (etop) {
                    is Double -> {  val i: Long = Math.round(etop as Double)
                                    etop = efun
                                    if (i >= 0.toLong()) selectAt(i)
                                    else etop = Error(idat,efnselnotinrange+" - "+toValue(i))
                                 }
                    is Long   -> {  val i: Long = etop as Long
                                    etop = efun
                                    if (i >= 0.toLong()) selectAt(i)
                                    else etop = Error(idat,efnselnotinrange+" - "+toValue(i))
                                 }
                    else      -> etop = Error(idat,eopnumasop1exp)
                }
            } else etop = Error(idat,eoppropasop0exp)  }
        efun = xnil  }

    fun fee() {
        ee(idee)
        if (etop !is Error) etop = Cell(efun,xcons,Cell(etop,xcons,Nil()))
        efun = xnil  }

    fun fswee() {
        ee(idswee)
        if (etop !is Error) etop = Cell(etop,xcons,Cell(efun,xcons,Nil()))
        efun = xnil  }

    fun fcomma() {
        ee(idcomma)
        if (etop !is Error) etop = Cell(efun,xcons,etop)
        efun = xnil  }

    fun fapp() {
        ee(idapp)
        if (etop is Error) efun = xnil
        else equit = false  }

    fun fadd() {
        ee(idadd)
        if (etop !is Error) {
            when (efun) {
                is Double -> {  if (etop is Double) etop = (efun as Double) + (etop as Double)
                                else etop = Error(idadd,eoprealasop1exp)  }
                is Long   -> {  if (etop is Long) etop = (efun as Long) + (etop as Long)
                                else etop = Error(idadd,eopintasop1exp)  }
                else      -> etop = Error(idadd,eopnumasop0exp)
            } }
        efun = xnil  }

    fun fsub() {
        ee(idsub)
        if (etop !is Error) {
            when (efun) {
                is Double -> {  if (etop is Double) etop = (efun as Double) - (etop as Double)
                                else etop = Error(idsub,eoprealasop1exp)  }
                is Long   -> {  if (etop is Long) etop = (efun as Long) - (etop as Long)
                                else etop = Error(idsub,eopintasop1exp)  }
                else      -> etop = Error(idsub,eopnumasop0exp)
            } }
        efun = xnil  }

    fun fmul() {
        ee(idmul)
        if (etop !is Error) {
            when (efun) {
                is Double -> {  if (etop is Double) etop = (efun as Double) * (etop as Double)
                                else etop = Error(idmul,eoprealasop1exp)  }
                is Long   -> {  if (etop is Long) etop = (efun as Long) * (etop as Long)
                                else etop = Error(idmul,eopintasop1exp)  }
                else      -> etop = Error(idmul,eopnumasop0exp)
            } }
        efun = xnil  }

    fun fdiv() {
        ee(iddiv)
        var x: Double = 0.0
        var y: Double = 0.0
        var z: Any = 0
        if (etop !is Error) {
            when (efun) {
                is Double -> x = efun as Double
                is Long   -> x = (efun as Long).toDouble()
                else      -> z = Error(iddiv,eopnumasop0exp)
            }
            when (etop) {
                is Double -> y = etop as Double
                is Long   -> y = (etop as Long).toDouble()
                else      -> z = Error(iddiv,eopnumasop1exp)
            }
            if (z is Error) etop = z
            else try {  if (y != 0.0) etop = x/y
                        else etop = Error(iddiv,eopdivbyzero)  }  // Infinity?
                 catch (e: Exception) {  etop = Error(iddiv,e.message as String)  }
        }
        efun = xnil  }

    fun fpow() {
        ee(idpow)
        if (etop !is Error) {
            when (efun) {
                is Double -> {  if (etop is Double)
                                    etop = Math.pow((efun as Double),(etop as Double))
                                else etop = Error(idpow,eoprealasop1exp)  }
                is Long   -> {  if (etop is Long)
                                    etop = Math.pow(((efun as Long).toDouble()),((etop as Long).toDouble())).toLong()
                                else etop = Error(idpow,eopintasop1exp)  }
                else      -> etop = Error(idpow,eopnumasop0exp)
            } }
        efun = xnil  }

    fun fidiv() {
        ee(ididiv)
        if (etop !is Error) {
            if (efun is Long) {
                if (etop is Long) etop = (efun as Long).div(etop as Long)
                else etop = Error(ididiv,eopintasop1exp)
            } else etop = Error(ididiv,eopintasop0exp)
        }
        efun = xnil  }

    fun fimod() {
        ee(idimod)
        if (etop !is Error) {
            if (efun is Long) {
                if (etop is Long) etop = (efun as Long).mod(etop as Long)
                else etop = Error(idimod,eopintasop1exp)
            } else etop = Error(idimod,eopintasop0exp)
        }
        efun = xnil  }

    fun fpred() {
        when (etop) {
            is Double -> etop = (etop as Double) - 1.0
            is Long   -> etop = (etop as Long) - 1
            is Error  -> { }
            else      -> etop = Error(idpred,efnnumexp)
        } }

    fun fsucc() {
        when (etop) {
            is Double -> etop = (etop as Double) + 1.0
            is Long   -> etop = (etop as Long) + 1
            is Error  -> { }
            else      -> etop = Error(idsucc,efnnumexp)
        } }

    fun fsign() {
        when (etop) {
            is Double -> etop = Math.signum(etop as Double)
            is Long   -> {  if      ((etop as Long) > 0) etop =  1.toLong()
                            else if ((etop as Long) < 0) etop = -1.toLong()
                            else etop = 0.toLong()  }
            is Error  -> { }
            else      -> etop = Error(idsign,efnnumexp)
        } }

    fun fabs() {
        when (etop) {
            is Double -> etop = Math.abs(etop as Double)
            is Long   -> etop = Math.abs(etop as Long)
            is Error  -> { }
            else      -> etop = Error(idabs,efnnumexp)
        } }

    fun fneg() {
        when (etop) {
            is Double -> etop = -(etop as Double)
            is Long   -> etop = -(etop as Long)
            is Error  -> { }
            else      -> etop = Error(idneg,efnnumexp)
        } }

    fun ffloor() {
        when (etop) {
            is Double -> etop = Math.floor(etop as Double)
            is Long   -> etop = (etop as Long).toDouble()
            is Error  -> { }
            else      -> etop = Error(idfloor,efnnumexp)
        } }

    fun fceil() {
        when (etop) {
            is Double -> etop = Math.ceil(etop as Double)
            is Long   -> etop = (etop as Long).toDouble()
            is Error  -> { }
            else      -> etop = Error(idceil,efnnumexp)
        } }

    fun ffloat() {
        when (etop) {
            is Long   -> etop = (etop as Long).toDouble()
            is Double -> { }
            is Error  -> { }
            else      -> etop = Error(idfloat,efnnumexp)
        } }

    fun fround() {
        when (etop) {
            is Double -> etop = Math.round(etop as Double)
            is Long   -> { }
            is Error  -> { }
            else      -> etop = Error(idround,efnnumexp)
        } }

    fun ftrunc() {
        when (etop) {
            is Double -> etop = (etop as Double).toLong()
            is Long   -> { }
            is Error  -> { }
            else      -> etop = Error(idtrunc,efnnumexp)
        } }

    /*
    fun Double.roundToA(numFractionDigits: Int): Double {
        val factor = 10.0.pow(numFractionDigits.toDouble())
        return (this * factor).roundToInt() / factor
    }
    */

    fun Double.roundTo(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return Math.round(this * multiplier) / multiplier
    }

    fun froundto() {
        ee(idroundto)
        if (etop !is Error) {
            if (efun is Double) {
                when (etop) {
                    is Double -> etop = (efun as Double).roundTo(Math.round(etop as Double).toInt())
                    is Long   -> etop = (efun as Double).roundTo((etop as Long).toInt())
                    else      -> etop = Error(idroundto, eopnumasop1exp)
                }  }
            else etop = Error(idroundto,eoprealasop0exp)
        }
        efun = xnil  }

    fun fexp() {
        when (etop) {
            is Double -> etop = Math.exp(etop as Double)
            is Long   -> etop = Math.exp((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idexp,efnnumexp)
        } }

    fun fln() {
        when (etop) {
            is Double -> etop = Math.log(etop as Double)
            is Long   -> etop = Math.log((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idln,efnnumexp)
        } }

    fun flg() {
        when (etop) {
            is Double -> etop = Math.log10(etop as Double)
            is Long   -> etop = Math.log10((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idlg,efnnumexp)
        } }

    fun fsq() {
        when (etop) {
            is Double -> etop = (etop as Double) * (etop as Double)
            is Long   -> etop = (etop as Long) * (etop as Long)
            is Error  -> { }
            else      -> etop = Error(idsq,efnnumexp)
        } }

    fun fsqrt() {
        when (etop) {
            is Double -> etop = Math.sqrt(etop as Double)
            is Long   -> etop = Math.sqrt((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idsqrt,efnnumexp)
        } }

    fun fcbrt() {
        when (etop) {
            is Double -> etop = Math.cbrt(etop as Double)
            is Long   -> etop = Math.cbrt((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idcbrt,efnnumexp)
        } }

    // hypot()
    // e
    // tau

    fun fpi() {
        when (etop) {
            is Error -> { }
            else     -> etop = Math.PI
        } }

    fun f2pi() {  if (etop !is Error) etop = Math.PI + Math.PI  }

    fun fsin() {
        when (etop) {
            is Double -> etop = Math.sin(etop as Double)
            is Long   -> etop = Math.sin((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idsin,efnnumexp)
        } }

    fun fcos() {
        when (etop) {
            is Double -> etop = Math.cos(etop as Double)
            is Long   -> etop = Math.cos((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idcos,efnnumexp)
        } }

    fun ftan() {
        when (etop) {
            is Double -> etop = Math.tan(etop as Double)
            is Long   -> etop = Math.tan((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idtan,efnnumexp)
        } }

    fun farcsin() {
        when (etop) {
            is Double -> etop = Math.asin(etop as Double)  // try ?  'NaN'
            is Long   -> etop = Math.asin((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idarcsin,efnnumexp)
        } }

    fun farccos() {
        when (etop) {
            is Double -> etop = Math.acos(etop as Double)  // try ?   'NaN' wie true oder false?
            is Long   -> etop = Math.acos((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idarccos,efnnumexp)
        } }

    fun farctan() {
        when (etop) {
            is Double -> etop = Math.atan(etop as Double)
            is Long   -> etop = Math.atan((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idarctan,efnnumexp)
        } }

    fun farctan2() {
        ee(idarctan2)
        var x: Double = 0.0
        var y: Double = 0.0
        var z: Any = 0
        if (etop !is Error) {
            when (efun) {
                is Double -> y = efun as Double
                is Long   -> y = (efun as Long).toDouble()
                else      -> z = Error(idarctan2,eopnumasop0exp)
            }
            when (etop) {
                is Double -> x = etop as Double
                is Long   -> x = (etop as Long).toDouble()
                else      -> z = Error(idarctan2,eopnumasop1exp)
            }
            if (z is Error) etop = z
            else etop = Math.atan2(y,x)  }
        efun = xnil  }

    fun fsinh() {
        when (etop) {
            is Double -> etop = Math.sinh(etop as Double)  // try?
            is Long   -> etop = Math.sinh((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idsinh,efnnumexp)
        } }

    fun fcosh() {
        when (etop) {
            is Double -> etop = Math.cosh(etop as Double)  // try?
            is Long   -> etop = Math.cosh((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idcosh,efnnumexp)
        } }

    fun ftanh() {
        when (etop) {
            is Double -> etop = Math.tanh(etop as Double)
            is Long   -> etop = Math.tanh((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idtanh,efnnumexp)
        } }

    // Seiteneffekt!
    // fun frandom() {  if (etop !is Error) etop = Math.random()  }

    fun fdeg() {
        when (etop) {
            is Double -> etop = Math.toDegrees(etop as Double)
            is Long   -> etop = Math.toDegrees((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(iddeg,efnnumexp)
        } }

    fun frad() {
        when (etop) {
            is Double -> etop = Math.toRadians(etop as Double)
            is Long   -> etop = Math.toRadians((etop as Long).toDouble())
            is Error  -> { }
            else      -> etop = Error(idrad,efnnumexp)
        } }

    fun isEq(a: Any,b: Any): Boolean {
        when (b) {
            is Double  -> return (a == b)
            is Long    -> return (a == b)
            is Ident   -> return (a == b)
            is String  -> return (a == b)
            is Boolean -> return (a == b)
            is Nil     -> return (a is Nil)
            is Cell    -> {  if (a !is Cell) return false
                             else if (!isEq(a.head,b.head))   return false
                             else if (!isEq(a.infix,b.infix)) return false
                             else return isEq(a.tail,b.tail)  }             // rekursiv
            is Quote   -> {  if (a !is Quote) return false
                             else return isEq(a.value,b.value)  }
            is Ivar    -> {  if (a !is Ivar) return false
                             else return (a.value == b.value)  }
            //  is Comment
            //  is Combine -> {}                                           // ergänzen !!!
            //  is Act     -> {}
            //  is Error   -> { }
            else       -> return false  // ???
        } }

    fun isLess(a: Any,b: Any,eid: Ident): Any {
        when (b) {
            is Double  -> {  if (a is Double) return (a < b)
                             else return Error(eid,eoptypenocompare)  }
            is Long    -> {  if (a is Long) return (a < b)
                             else return Error(eid,eoptypenocompare)  }
            is Ident   -> {  if (a is Ident) return (a.pname < b.pname)
                             else return Error(eid,eoptypenocompare)  }
            is String  -> {  if (a is String) return (a < b)
                             else return Error(eid,eoptypenocompare)  }
            //  is Boolean -> {}
            //  is Nil     -> {}
            //  is Cell    -> {}
            //  is Quote   -> {}
            //  is Ivar    -> {}
            // is Comment
            //  is Combine -> {}
            //  is Act     -> {}
            //  is Error   -> {}
            else       -> return Error(eid,eoptypenocompare)
        } }

    fun feq() {
        ee(ideq)
        if (etop !is Error) etop = isEq(efun,etop)
        efun = xnil  }

    fun fneq() {
        ee(idneq)
        if (etop !is Error) etop = !isEq(efun,etop)
        efun = xnil  }

    fun flt() {
        ee(idlt)
        if (etop !is Error) etop = isLess(efun,etop,idlt)
        efun = xnil  }

    fun fgt() {
        ee(idgt)
        if (etop !is Error) etop = isLess(etop,efun,idgt)  // ???
        efun = xnil  }

    fun fle() {
        ee(idle)
        if (etop !is Error) {  etop = isLess(etop,efun,idle)  // ???
                               if (etop is Boolean) etop = !(etop as Boolean)  // else is Error
                            }
        efun = xnil  }

    fun fge() {
        ee(idge)
        if (etop !is Error) {  etop = isLess(efun,etop,idge)
                               if (etop is Boolean) etop = !(etop as Boolean)  // else is Error
                            }
        efun = xnil  }

    // <    alle testen !!!
    // kann xnil zu Problemen führen ?

    fun fmin() {
        ee(idmin)
        if (etop !is Error) {
            val z = isLess(efun,etop,idmin)
            when (z) {
                true     -> etop = efun
                false    -> { }  // etop = etop
                is Error -> etop = z
                else     -> etop = Error(idmin,eopunknownerr)
            }  }
        efun = xnil  }

    fun fmax() {
        ee(idmax)
        if (etop !is Error) {
            val z = isLess(efun,etop,idmax)
            when (z) {
                true     -> { }  // etop = etop
                false    -> etop = efun
                is Error -> etop = z
                else     -> etop = Error(idmax,eopunknownerr)
            }  }
        efun = xnil  }

    fun fnot() {
        when (etop) {
            true     -> etop = false
            false    -> etop = true
            is Error -> { }
            else     -> etop = Error(idnot,efnboolexp)
        } }

    fun fand() {
        ee(idand)
        when (etop) {
            true     -> {  when (efun) {
                               true  -> etop = true
                               false -> etop = false
                               else  -> etop = Error(idand,eopboolasop0exp)
                        } }
            false    -> {  if (efun is Boolean) etop = false
                           else etop = Error(idand,eopboolasop0exp)
                        }
            is Error -> { }
            else     -> etop = Error(idand,eopboolasop1exp)  }
        efun = xnil  }

    fun f_or() {
        ee(idor)
        when (etop) {
            true     -> {  if (efun is Boolean) etop = true
                           else etop = Error(idor,eopboolasop0exp)
                        }
            false    -> {  when (efun) {
                               true  -> etop = true
                               false -> etop = false
                               else  -> etop = Error(idor,eopboolasop0exp)
                        } }
            is Error -> { }
            else     -> etop = Error(idor,eopboolasop1exp)  }
        efun = xnil  }

    fun fxor() {
        ee(idxor)
        when (etop) {
            true     -> {  when (efun) {
                               true  -> etop = false
                               false -> etop = true
                               else  -> etop = Error(idxor,eopboolasop0exp)
                        } }
            false    -> {  when (efun) {
                               true  -> etop = true
                               false -> etop = false
                               else  -> etop = Error(idxor,eopboolasop0exp)
                        } }
            is Error -> { }
            else     -> etop = Error(idxor,eopboolasop1exp)  }
        efun = xnil  }

    fun fisAtom() {
        when (etop) {
            is Cell  -> etop = false
            is Error -> { }
            else     -> etop = true // vollständig ?
        } }

    fun fisNull() {  if (etop !is Error) etop = (etop is Nil)  }

    fun fisProp() {
        when (etop) {
            is Cell  -> etop = true
            is Error -> { }
            else     -> etop = false // vollständig ?
        } }

    fun fisList() {
        when (etop) {
            is Cell  -> etop = ((etop as Cell).infix == xcons)
            is Nil   -> etop = true
            is Error -> { }
            else     -> etop = false // vollständig ?
        } }

    fun fisNum() {
        when (etop) {
            is Double -> etop = true
            is Long   -> etop = true
            is Error  -> { }
            else      -> etop = false
        } }

    fun fisZero() {
        when (etop) {
            is Double -> etop = ((etop as Double) == 0.0)
            is Long   -> etop = ((etop as Long) == 0.toLong())
            is Error  -> { }
            else      -> etop = false
        } }

    fun fisPos() {
        when (etop) {
            is Double -> etop = ((etop as Double) > 0.0)
            is Long   -> etop = ((etop as Long) > 0)
            is Error  -> { }
            else      -> etop = Error(idispos,efnnumexp)
        } }

    fun fisNeg() {
        when (etop) {
            is Double -> etop = ((etop as Double) < 0.0)
            is Long   -> etop = ((etop as Long) < 0)
            is Error  -> { }
            else      -> etop = Error(idisneg,efnnumexp)
        } }

    fun fisIdent()  {  if (etop !is Error) etop = (etop is Ident)  }

    fun fisInt()    {  if (etop !is Error) etop = (etop is Long)  }

    fun fisReal()   {  if (etop !is Error) etop = (etop is Double)  }

    fun fisString() {  if (etop !is Error) etop = (etop is String)  }

    fun fisCons()   {  if (etop is Error) { }
                       else if (etop is Cell) etop = ((etop as Cell).infix == xcons)
                       else etop = false  }

    fun fisQuote()  {  if (etop !is Error) etop = (etop is Quote)  }

    fun fisIvar()   {  if (etop !is Error) etop = (etop is Ivar)  }

    fun fisCombi()  {  if (etop !is Error) etop = (etop is Combine)  }

    fun fisAct()    {  if (etop !is Error) etop = (etop is Act)  }

    fun fisBool()   {  if (etop !is Error) etop = (etop is Boolean)  }

    fun fisBound() {
        when (etop) {
            is Ident -> etop = ((etop as Ident).value != xreserve)
            is Error -> { }
            else     -> etop = Error(idisbound,efnidentexp)
        } }

    fun fisundef()  {  if (etop !is Error) etop = (etop == xundef)  }

    fun fcons() { // ???
        when (etop) {
            is Combine -> etop = (etop as Combine).term
            is Error   -> { }
            else       -> etop = Error(idcons,efnnocombine)
        } }

    fun fname() {
        when (etop) {
            is Ident -> etop = (etop as Ident).pname
            is Error -> { }
            else     -> etop = Error(idname,efnidentexp) // ???
        } }

    fun fbody() {
        when (etop) {
            is Ident -> etop = (etop as Ident).value
            is Error -> { }
            else     -> etop = Error(idbody,efnidentexp) // ???
        } }

    fun finfo() {
        when (etop) {
            is Ident -> etop = (etop as Ident).info
            is Error -> { }
            else     -> etop = Error(idinfo,efnidentexp) // ???
        } }

    // out

    fun fidentlist() {  if (etop !is Error) etop = identlist  }

    fun fquote() {  if (etop !is Error) etop = Quote(etop)  }

    fun ferror() {
        ee(iderror)
        if (etop !is Error) {
            if (efun is Ident) etop = Error(efun,etop)
            else etop = Error(iderror,eopidentasop0exp)
        }
        efun = xnil  }

    fun fcomp() {
        ee(idcomp)
        if (etop !is Error) etop = Cell(efun,idcompose,etop)
        efun = xnil  }

    fun fact() {
        ee(idact)
        if (etop !is Error) {
            if ((etop is Cell) or (etop is Nil)) {
                if ((efun is Long) or (efun is Act)) etop = Act(efun,etop,idit)
                else etop = Error(idact,eopintoractasop0exp)
            } else etop = Error(idact,eopdictasop1exp)  }
        efun = xnil  }

    fun fbind() {
        ee(idbind)
        if (etop !is Error) {
            if (efun is Act) {
                etop = Act((efun as Act).num,(efun as Act).data,etop)
            } else etop = Error(idbind,eopactasop0exp)  }
        efun = xnil  }

    // bind (ee, mit Kopie von Act)
    // >>   (in prelude)

    // trans
    // applic (?)
    // filter
    // map
    // map0
    // aa0
    // <- assign
    // try
    // remove (get,put)

    fun fiota() {
        var i: Long
        when (etop) {
            is Double -> {  i = Math.round(etop as Double)
                            etop = Nil()
                            while (i > 0) {  etop = Cell(i.toDouble(),xcons,etop)
                                             i = i - 1
                            } }
            is Long   -> {  i = etop as Long
                            etop = Nil()
                            while (i > 0) {  etop = Cell(i,xcons,etop)
                                             i = i - 1
                            } }
            is Error  -> { }
            else      -> etop = Error(idiota,efnnumexp)
        } }

    fun fto() {
        val i: Long
        var k: Long
        ee(idto)
        if (etop !is Error) {
            when (efun) {
                is Double -> {  if (etop is Double) {
                                    i = Math.round(efun as Double)
                                    k = Math.round(etop as Double)
                                    etop = Nil()
                                    if (i <= k) {
                                        while (i <= k) {  etop = Cell(k.toDouble(),xcons,etop)
                                                          k = k - 1  }
                                    } else {
                                        while (i >= k) {  etop = Cell(k.toDouble(),xcons,etop)
                                                          k = k + 1  }
                                    }
                                } else etop = Error(idto,eoprealasop1exp)  }
                is Long   -> {  if (etop is Long) {
                                    i = efun as Long
                                    k = etop as Long
                                    etop = Nil()
                                    if (i <= k) {
                                        while (i <= k) {  etop = Cell(k,xcons,etop)
                                                          k = k - 1  }
                                    } else {
                                        while (i >= k) {  etop = Cell(k,xcons,etop)
                                                          k = k + 1  }
                                    }
                                } else etop = Error(idto,eopintasop1exp)  }
                else      -> etop = Error(idto,eopnumasop0exp)
            } }
        efun = xnil  }

    fun freverse() {
        when (etop) {
            is Cell   -> etop = reverse(etop)
            is Nil    -> { }
            is String -> etop = (etop as String).reversed()
            is Error  -> { }
            else      -> etop = Error(idreverse,efnpropnullstringexp)
        } }

    fun fmake() {
        ee(idmake)
        var n : Int = 0
        when (etop) {
            is Double -> n = Math.round(etop as Double).toInt()
            is Long   -> n = (etop as Long).toInt()
            is Error  -> { }
            else      -> etop = Error(idmake,eopnumasop1exp)
        }
        if (etop !is Error) {
            var res: Any = Nil()
            while (n > 0) {  n = n.dec()
                             res = Cell(efun,xcons,res)  }
            etop = res
        }
        efun = xnil  }

    fun ftake() {
        var i: Long = 0
        ee(idtake)
        when (etop) {
            is Double -> i = Math.round(etop as Double)
            is Long   -> i = etop as Long
            is Error  -> { }
            else      -> etop = Error(idtake,eopnumasop1exp)
        }
        if (etop !is Error) {
            when (efun) {
                is Cell   -> {  etop = Nil()
                                while ((efun is Cell) and (i > 0)) {
                                    etop = Cell((efun as Cell).head,(efun as Cell).infix,etop)
                                    efun = (efun as Cell).tail
                                    i = i - 1  }
                                etop = nreverse(etop)  }
                is Nil    -> etop = efun
              //is String -> {}
                else      -> etop = Error(idtake,eoptableorlistasop0exp)
            } }
        efun = xnil  }

    fun fdrop() {
        var i: Long = 0
        ee(iddrop)
        when (etop) {
            is Double -> i = Math.round(etop as Double)
            is Long   -> i = etop as Long
            is Error  -> { }
            else      -> etop = Error(idtake,eopnumasop1exp)
        }
        if (etop !is Error) {
            when (efun) {
                is Cell   -> {  while ((efun is Cell) and (i > 0)) {
                                    efun = (efun as Cell).tail
                                    i = i - 1  }
                                if (i > 0) etop = xnil
                                else etop = efun  }
                is Nil    -> etop = efun
              //is String -> {}
                else      -> etop = Error(iddrop,eoptableorlistasop0exp)
            } }
        efun = xnil  }
    
    fun fpplus() {
        ee(idpplus)
        if (etop !is Error) {
            when (efun) {
                is Cell   -> {  efun = reverse(efun)
                                while (efun is Cell) {
                                    etop = Cell((efun as Cell).head,(efun as Cell).infix,etop)
                                    efun = (efun as Cell).tail  }
                                if (efun is Error) etop = efun  }
                is Nil    -> {  }
              //is String -> {}
                else      -> etop = Error(idpplus,eoptableorlistasop0exp)
            } }
        efun = xnil  }

    fun iget(eid: Ident, aggr: Any, i: Ident): Any {
        var a: Any = aggr
        var p: Any
        while (a is Cell) {
            p = a
            do {  if ((a as Cell).infix == i) break
                  else a = a.tail
            } while (a is Cell)
            if (a is Cell) return a.head
            else if (a is Ident) a = a.value  // ???
            else {  do {  if ((p as Cell).infix == xsuper) break
                          else p = p.tail
                    } while (p is Cell)
                    if (p is Cell) a = p.head
                    else return xundef
                 } }
        return Error(eid,efntableforidentgetexp)  }

    /*
                                {  val i: Ident = etop
                                   do {  if (efun.infix = i) break
                                         else efun = efun.tail
                                   } while (efun is Cell)
                                   if (efun is Cell) etop = efun.head
                                   else etop = xundef  }
    */

    fun figet() {
        ee(idiget)
        if (etop !is Error) {
            if (etop is Ident) {
                when (efun) {
                    is Cell  -> etop = iget(idiget,efun,etop as Ident)
                    is Nil   -> etop = xundef
                    else     -> etop = Error(idiget,eoppropornullasop0exp)
                } }
            else etop = Error(idiget,eopidentasop1exp)
        }
        efun = xnil  }

    fun iput(aggr: Any, i: Ident, v: Any): Any {
        var list: Any = Nil()
        var a: Any = aggr
        var p: Any
        while (a is Cell) {
            if (a.infix == i) break
            else {  list = Cell(a.head,a.infix,list)
                    a = a.tail  }  }
        if (a is Cell) a = Cell(v,i,a.tail)
        else a = Cell(v,i,Nil())  // (nil ?)
        while (list is Cell) {  p = list.tail
                                list.tail = a
                                a = list
                                list = p  }
        return a  }

    fun fiput() {
        ee(idiput)
        if (etop !is Error) {
            if (etop !is Cell) etop = Error(idiput,eoplistasop1exp)
            else if ((etop as Cell).head !is Ident) etop = Error(idiput,eopidentasfstofop1exp)
            else {  val i: Ident = (etop as Cell).head as Ident
                    etop = (etop as Cell).tail
                    if (etop !is Cell) etop = Error(idiput,eoplist2asop1exp)
                    else {  val v: Any = (etop as Cell).head
                            etop = iput(efun,i,v)
                         }  }  }
        efun = xnil  }

    fun get(eid: Ident, aggr: Any, i: Any): Any {
        var a: Any = aggr
        var p: Any
        while (a is Cell) {
            p = a
            do {  if (isEq((a as Cell).infix,i)) break
            else a = a.tail
            } while (a is Cell)
            if (a is Cell) return a.head
            else if (a is Ident) a = a.value  // ???
            else {  do {  if ((p as Cell).infix == xsuper) break
            else p = p.tail
            } while (p is Cell)
                if (p is Cell) a = p.head
                else return xundef
            } }
        return Error(eid,efntableforgetexp)  }

    fun fget() {
        ee(idget)
        if (etop !is Error) {
            when (efun) {
                is Cell -> etop = get(idget,efun,etop)
                is Nil  -> etop = xundef
                else    -> etop = Error(idget,eoppropornullasop0exp)
            } }
        efun = xnil  }

    fun put(aggr: Any, i: Any, v: Any): Any {
        var list: Any = Nil()
        var a: Any = aggr
        var p: Any
        while (a is Cell) {
            if (isEq(a.infix,i)) break
            else {  list = Cell(a.head,a.infix,list)
                a = a.tail  }  }
        if (a is Cell) a = Cell(v,i,a.tail)
        else a = Cell(v,i,Nil())  // (nil ?)
        while (list is Cell) {  p = list.tail
            list.tail = a
            a = list
            list = p  }
        return a  }

    fun fput() {
        ee(idput)
        if (etop !is Error) {
            if (etop !is Cell) etop = Error(idput,eoplistasop1exp)
            else {  val i: Any = (etop as Cell).head
                    etop = (etop as Cell).tail
                    if (etop !is Cell) etop = Error(idput,eoplist2asop1exp)
                    else {  val v: Any = (etop as Cell).head
                            etop = put(efun,i,v)
                         }  }  }
        efun = xnil  }

    fun fkeys() {
        when (etop) {
            is Cell  -> {  var list: Any = Nil()
                           do {  list = Cell((etop as Cell).infix,xcons,list)
                                 etop = (etop as Cell).tail
                           } while (etop is Cell)
                           if (etop !is Error) etop = nreverse(list)  }
            is Nil   -> { }
            is Error -> { }
            else     -> etop = Error(idkeys,efndictexp)
        }  }

    fun fvalues() {
        when (etop) {
            is Cell  -> {  var list: Any = Nil()
                           do {  list = Cell((etop as Cell).head,xcons,list)
                                 etop = (etop as Cell).tail
                           } while (etop is Cell)
                           if (etop !is Error) etop = nreverse(list)  }
            is Nil   -> { }
            is Error -> { }
            else     -> etop = Error(idvalues,efndictexp)
        }  }

    fun fit() {
        when (etop) {
            is Cell  -> etop = iget(idit,etop,xit)
            is Nil   -> etop = xundef
            is Error -> { }
            else     -> etop = Error(idit,efndictexp)
        }  }

    fun fcount() {
        ee(idcount)
        if (etop !is Error) {
            when (efun) {
                is Cell -> {  var c: Long = 0
                              do {  if (isEq((efun as Cell).head,etop)) c = c + 1
                                    efun = (efun as Cell).tail
                              } while (efun is Cell)
                              if (efun is Error) etop = efun
                              else etop = c.toDouble()  }
                is Nil  -> etop = 0.toDouble()
                else    -> etop = Error(idcount,eoplistasop0exp)
            }  }
        efun = xnil  }

    fun ffind() {
        ee(idfind)
        if (etop !is Error) {
            when (efun) {
                is Cell -> {  var n: Long = -1
                              var found: Boolean = false
                              do {  n = n + 1
                                    found = isEq((efun as Cell).head,etop)
                                    efun = (efun as Cell).tail
                              } while ((efun is Cell) and !found)
                              if (found) etop = n.toDouble()
                              else if (efun is Error) etop = efun
                              else etop = -1.toDouble()  }
                is Nil  -> etop = -1.toDouble()
                else    -> etop = Error(idfind,eoplistasop0exp)
            }  }
        efun = xnil  }

    fun fin() {
        ee(idin)
        when (etop) {
            is Cell  -> {  var found: Boolean = false
                           do {  found = isEq((etop as Cell).head,efun)
                                 etop = (etop as Cell).tail
                           } while ((etop is Cell) and !found)
                           if (etop !is Error) etop = found  }
            is Nil   -> etop = false
            is Error -> { }
            else     -> etop = Error(idin,eoplistasop1exp)
        }
        efun = xnil  }

    fun flength() {
        when (etop) {
            is Cell   -> {  var n: Int = 0
                            do {  n = n.inc()
                                  etop = (etop as Cell).tail
                            } while (etop is Cell)
                            if (etop !is Error) etop = n.toDouble()  }
            is Nil    -> etop = 0.toDouble()
            is String -> etop = (etop as String).length.toDouble()
            is Error  -> { }
            else      -> etop = Error(idlength,efnpropnullstringexp) // Prop?
        } }

    fun list3ToCell(eid: Ident, p: Any): Any {
        var list: Any = p
        when (list) {
            is Cell  -> {
                if (list.infix == xcons) {
                    val x: Any = list.head
                    list = list.tail
                    if (list is Cell) {
                        if (list.infix == xcons) {
                            val y: Any = list.head
                            list = list.tail
                            if (list is Cell) {
                                if (list.infix == xcons) return Cell(x,y,list.head)
                                else return Error(eid,efnlist3exp)
                            } else if (list is Error) return list
                            else return Error(eid,efnlist3exp)
                        } else return Error(eid,efnlist3exp)
                    } else if (list is Error) return list
                    else return Error(eid,efnlist3exp)
                } else return Error(eid,efnlist3exp)  }
            is Error -> return list
            else     -> return Error(eid,efnlist3exp)
        } }

    fun fsubstring() {
        val c: Any = list3ToCell(idsubstring,etop)
        if (c is Cell) {
            var z: Any = 0
            var i: Int = 0
            var k: Int = 0
            if (c.head is String) {
                val s: String = c.head as String
                when (c.infix) {
                    is Double -> i = Math.round(c.infix as Double).toInt()
                    is Long   -> i = (c.infix as Long).toInt()
                    is Error  -> z = c.infix
                    else      -> z = Error(idsubstring,efnnumasarg1exp)
                }
                if (z !is Error) {
                    when (c.tail) {
                        is Double -> k = Math.round(c.tail as Double).toInt()
                        is Long   -> k = (c.tail as Long).toInt()
                        is Error  -> z = c.tail
                        else      -> z = Error(idsubstring,efnnumasarg2exp)
                    }
                    if (z !is Error) {
                        if (i < 1) i = 1
                        if (i > s.length) i = s.length + 1
                        if (k < 0) k = 0
                        if ((i+k) > s.length) k = s.length + 1 - i
                        etop = s.substring(i-1,i+k-1)
                    } else etop = z
                } else etop = z
            } else etop = Error(idsubstring,efnstringasarg0exp)
        } else if (c is Error) etop = c
        else etop = Error(idsubstring,efnunknownerr)  }

    fun fconcat() {
        ee(idconcat)
        if (etop !is Error) {
            if (efun is String) {
                if (etop is String) etop = (efun as String) + etop
                else etop = Error(idconcat,eopstringasop1exp)
            } else etop = Error(idconcat,eopstringasop0exp)
        }
        efun = xnil  }

    fun findexof() {  // 0 oder 1 ???
        ee(idindexof)
        if (etop !is Error) {
            if (efun is String) {
                if (etop is String)
                    etop = ((efun as String).indexOf((etop as String),0)+1).toDouble()
                else etop = Error(idindexof,eopstringasop1exp)
            } else etop = Error(idindexof,eopstringasop0exp)  }
        efun = xnil  }

    fun ftrim() {
        when (etop) {
            is String -> etop = (etop as String).trim()
            is Error  -> { }
            else      -> etop = Error(idtrim,efnstringexp)
        } }

    fun ftriml() {
        when (etop) {
            is String -> etop = (etop as String).trimStart()
            is Error  -> { }
            else      -> etop = Error(idtriml,efnstringexp)
        } }

    fun ftrimr() {
        when (etop) {
            is String -> etop = (etop as String).trimEnd()
            is Error  -> { }
            else      -> etop = Error(idtrimr,efnstringexp)
        } }

    fun fupper() {
        when (etop) {
            is String -> etop = (etop as String).uppercase()
            is Error  -> { }
            else      -> etop = Error(idupper,efnstringexp)
        } }

    fun flower() {
        when (etop) {
            is String -> etop = (etop as String).lowercase()
            is Error  -> { }
            else      -> etop = Error(idlower,efnstringexp)
        } }

    fun fcapitalize() {
        when (etop) {
            is String -> {  val s: String = etop as String
                            val len: Int = s.length
                            if (len > 0) etop = s[0].uppercase() + s.substring(1,len).lowercase()  }
            is Error  -> { }
            else      -> etop = Error(idcapitalize,efnstringexp)
        } }

    fun fchar() {
        when (etop) {
            is Double -> etop = Math.round((etop as Double).toFloat()).toChar().toString()
            is Long   -> etop = (etop as Long).toInt().toChar().toString()
         // is String -> {?}
            is Error  -> { }
            else      -> etop = Error(idchar,efnnumexp)
        } }

    fun funicode() {
        when (etop) {
            is String -> {  if ((etop as String).length > 0) etop = (etop as String)[0].code.toDouble()
                            else etop = Error(idunicode,"Nichtleeren String erwartet")}
            is Error  -> { }
            else      -> etop = Error(idunicode,efnstringexp)
        } }

    fun fparse() {
        when (etop) {
            is String -> etop = parse(etop as String)                   // try nötig?
            is Error  -> { }
            else      -> etop = Error(idparse,efnstringexp)
        } }

    fun fvalue() {
        when (etop) {
            is String -> {  etop = parse(etop as String)                // try nötig?
                            if (etop is Cell) etop = (etop as Cell).head
                            else if (etop !is Error) etop = xundef  }
            is Error  -> { }
            else      -> etop = Error(idvalue,efnstringexp)
        } }

    fun fstring() {  if (etop !is Error) etop = toValue(etop)  }

    // Stringfunktionen...
    // replace
    // pack

    fun funpack() {
        when (etop) {
            is String -> {  val s: String = etop as String
                            var n: Int = s.length
                            etop = Nil()
                            while (n > 0) {
                                etop = Cell(s.substring(n-1,n),xcons,etop)
                                n = n - 1
                            }  }
            is Error  -> { }
            else      -> etop = Error(idunpack,efnstringexp)
        }  }

    // at
    // init tailr
    // last
    // all
    // any

    fun splitTo(str: String, dm: String): Any {
        var i: Int
        var s: String = str
        var list: Any = Nil()
        while (s.length > 0) {
            i = s.indexOf(dm)
            if (i == -1) {  list = Cell(s,xcons,list); s = ""  }
            else {  list = Cell(s.substring(0,i),xcons,list)
                    s = s.substring(i+(dm.length),s.length)
                 } }
        return nreverse(list)  }

    fun fsplit() {
        ee(idsplit)
        if (etop !is Error) {
            if (efun is String) {
                if (etop is String) etop = splitTo(efun as String,etop as String)
                else etop = Error(idsplit,eopstringasop1exp)
            } else etop = Error(idsplit,eopstringasop0exp)
        }
        efun = xnil  }

    fun fjoin() {
        ee(idjoin)
        if (etop !is Error) {
            if (etop is String) {
                when (efun) {
                    is Cell -> {  var s: String = ""
                                  var hd: String
                                  var tl: Any
                                  do {  tl = (efun as Cell).tail
                                        if ((efun as Cell).head is String) hd = (efun as Cell).head as String
                                        else hd = toValue((efun as Cell).head)
                                        if (tl is Cell) s = s + (hd + etop)
                                        else s = s + hd
                                        efun = tl
                                  } while (efun is Cell)
                                  etop = s  }  // ??? if is Error?
                    is Nil  -> etop = ""
                    else    -> etop = Error(idjoin,eoplistasop0exp)
                }
            } else etop = Error(idjoin,eopstringasop1exp)
        }
        efun = xnil  }

    fun fstopvm() {
        runvm = false
    }

    fun fdump() {
        var p: Any = identlist
        var id: Ident
        var s: String = ""
        var ln: String = ""
        while (p is Cell) {
            id = p.head as Ident
            if (id.value == xreserve) {  s = ("// " + id.pname + ln) + s  }
            else {  s = (id.pname + " == " + toTable(id.value) + ln) + s  }
            ln = "\n"
            p = p.tail
        }
        etop = s }

    // for test
    fun fsavedump() {
        var p: Any = identlist
        var id: Ident
        var s: String = ""
        var ln: String = ""
        while (p is Cell) {
            id = p.head as Ident
            if (id.info != "") {  s = (id.info + ln) + s  }
            ln = "\n"
            p = p.tail
        }
        etop = s }

    fun fhelpinfo() {
        etop = "https://pointfrip.github.io/quickinfo-en.pdf"
    }

    fun deflines(lines: Any): Any {
        var list = lines
        var line: Any
        var ctable: Any
        var id: Ident
        var res: String = ""
        var ln: String = ""
        try {
            while (list is Cell) {
                line = list.head
                list = list.tail
                if (line is String) {
                    ctable = nbackcons(nreverse(parse(line)))
                    if (ctable is Cell) {
                        if ((ctable.head is Ident) and (ctable.infix == iddef)) {
                            id = ctable.head as Ident
                            id.info = line
                            id.value = ctable.tail
                            res = res + (ln + id.pname+" == "+toTable(id.value))
                            ln = "\n"
                        }
                    }
                } else return Error(idipr,efnlistofstringsexp)
            }
            return res
        } catch(e: Exception) {  return Error(idipr,e.message as String)  }
    }

    fun calc(txt: String): Any {
        var ctable: Any = Nil()
        try {  ctable = nbackcons(nreverse(parse(txt)))
               if (ctable is Cell) {
                   if ((ctable.head is Ident) and (ctable.infix == iddef)) {
                       val id: Ident = ctable.head as Ident
                       id.info = txt  // ???
                       id.value = ctable.tail
                       return id.pname+" == "+toTable(id.value)
                   } else return run(ctable,Nil())
               } else return run(ctable,Nil())
        } catch (e: Exception) {  return Error(idipr,e.message as String)  } }

// ----- provisorium

    fun compile(txt: String) {
        var ctable: Any = Nil()

        fun runLine() {
            // keine Def, Leer oder Comment
            if (true) {
                println("Compile-Result = "+toValue(ctable))
                println("Run-Result     = "+toValue(run(ctable,Nil())))
            }
            else println("Compile-Result = "+toValue(ctable))
        }

        try {
            ctable = nbackcons(nreverse(parse(txt)))
            println("Eingabe = "+toValue(ctable))
            if (ctable is Cell) {
                if ((ctable.head is Ident) and (ctable.infix == iddef)) {
                    val id: Ident = ctable.head as Ident
                    id.info = txt
                    id.value = ctable.tail // oder von Seiteneffekt (==) beschreiben lassen
                    println("id.pname = "+toValue(id.pname))
                    println("id.value = "+toValue(id.value))
                    println("id.info  = "+toValue(id.info))
                    // println("Found = "+toValue(id == findIdent("last")))
                } else {
                    println("keine Def sondern runLine:")
                    runLine()
                }
            } else { println("Leer oder Comment als runLine:")
                runLine()
            }
        } catch (e: Exception) { println("Fehler in: "+e.message) }
    }

// ------- prelude text

    fun prelude(): String {
        // "->* ==((head°term) app arg)->(term app (tail°term) app arg);arg"
        return  "" +
                "trans==(isatom°head)->();(head aa),trans°(tail aa)\n" +
                "distl==(trans°([0] make length°[1]) ee [1])°ee\n" +
                "distr==(trans°[0] ee [1] make length°[0])°ee\n" +
                "map0==((isatom°[0]°[0])->();([1] app ((head°[0]),tail)°[0]),(((tail°[0]),tail)°[0]) map0 [1])°ee\n" +
                "aa0==arg map0 head°term\n" +
                "map == reverse°[2]°((isprop°[0])->*(tail°[0]),[1],(([1] app head°[0]),[2]),)°([0],[1],(),)°ee\n" +
                "insl==( (isatom°[0])->'_undef;(isatom°tail°[0])->(head°[0]);(([1] app ([0],[1],)°[0]),tail°tail°[0]) insl [1] )°ee\n" +
                "insr==insrec°([1] ee reverse°[0])°ee\n" +
                "insrec==(isatom°[1])->'_undef;(isatom°tail°[1])->(head°[1]);insrec°[0]ee([0]app([1],[0],)°[1]),tail°tail°[1]\n" +
                "filter==( (isatom°[0])->();([1] app head°[0])->((head°[0]),(tail°[0]) filter [1]) ;(tail°[0]) filter [1] )°ee\n" +
                "<- == (head°term) app reverse°[0]°(((isprop°[1]) and isprop°[2])->*(prop°(head°[2]),(head°[1]),[0],),(tail°[1]),(tail°[2]),)°(),(tail°term) ee arg\n" +
                "isodd == (round imod '[2])='[1]\n" +
                "ismat==isnum°head°head\n" +
                "zero==ismat -> (length zeromat length°head) ; isreal -> 0 ; isint -> '[0] ; isbool -> 'false ; fail°'zero,id,\n" +
                "one ==ismat -> (idmat°length) ; isreal -> 1 ; isint -> '[1] ; isbool -> 'true  ; fail°'one,id,\n" +
                "scale==((isnum°[1]) -> ((* aa0) aa0) ; fail°'scale,id)°ee\n" +
                "zeromat==(((([2] aa0)°(iota°[1]),tail) aa0)°(iota°round°[0]) ,(round°[1]),(zero°[0]),)°ee\n" +
                "idmat  ==((((([0]=[1])->[4];[3]) aa0)°(iota°[1]),id) aa0)°(iota°round),round,zero,one,\n" +
                "negifodd == ((isodd°[0])->(neg°[1]);[1])°ee\n" +
                "droppos==((isnull°[0])->();(iszero°[1])->((tail°[0]) droppos pred°[1]);(head°[0]),(tail°[0]) droppos pred°[1])°ee\n" +
                "droprow==(droppos aa)°distr\n" +
                "det==((isnull°[1])->(head°[0]);[3]°(not°isnull°[0])->*(tail°[0]),[1],(succ°[2]),([3] + [4] negifodd (head°[0])*det°[1] droprow [2]),(succ°[4]),)°head,tail,'[0],(zero°head°head),'[0],\n" +
                "Aij==(([0] droppos [1]°[1]) droprow [0]°[1])°ee\n" +
                "inv==((iszero°[1])->('inv error \"Division by Zero\"); (((([1]°[1]) * (+°[0]) negifodd det°([0]°[1])Aij[0]) aa)aa)°(distr aa)°((distl aa)°[2]distr[2])distr[0]ee(one°[1])/[1])°id,det,('[0],iota°pred°round°length),\n" +
                "add==(((ismat°[0]) and ismat°[1])->((((+ aa)°trans) aa)°trans);((isnum°[0]) and isnum°[1])->([0]+[1]);fail°'add,id)°ee\n" +
                "sub==(((ismat°[0]) and ismat°[1])->((((- aa)°trans) aa)°trans);((isnum°[0]) and isnum°[1])->([0]-[1]);fail°'sub,id)°ee\n" +
                "mul==(((ismat°[0]) and ismat°[1])->([0] MM [1]);((isnum°[0]) and ismat°[1])->([1] scale [0]);((ismat°[0]) and isnum°[1])->([0] scale [1]);((isnum°[0]) and isnum°[1])->([0]*[1]);fail°'mul,id)°ee\n" +
                "fail=='FAIL error id\n" +
                ":= == arg iput (head°term) ee (tail°term) app arg\n" +
                "IP == (+ \\)°(* aa)°trans°ee\n" +
                "MM==((IP aa)aa)°(distl aa)°distr°([0] ee trans°[1])°ee\n" +
                "rnd==(((id roundto 5) aa) aa)\n" +
                ">> == ((head°term) app arg) bind tail°term\n" +
                "load=='[1] act arg iput '_it ee (head°term) app arg\n" +
                "save=='[2] act arg iput '_it ee (head°term) app arg\n" +
                "files=='[3] act id\n" +
                "loadtext=='[4] act arg iput '_it ee (head°term) app arg\n" +
                "savetext=='[5] act (arg iput '_self ee (head°term) app arg) iput '_para ee (tail°term) app arg\n" +
                "fremove=='[6] act arg iput '_it ee (head°term) app arg\n" +
                "viewurl=='[7] act arg iput '_it ee (head°term) app arg\n" +
                "date=='[8] act id\n" +
                "help==helpinfo viewurl\n" +
                "pim==reverse°[2]°(([0]>1)->*(iszero°[0]-[1]*floor°[0]/[1])->(([0]/[1]),[1],([1],[2]),);([0],([1]+1),[2],))°id,2,(),\n" +
                "r==((isneg°[1])->'_undef;(round°[1]) app [0])°arg ee (pred°length°arg)-float°head°term\n" +
                "tailr==id take pred°length\n" +
                "last==isatom->'_undef;(round°pred°length) app id\n" +
                "rotl==isatom->();tail++prop°head,infix,(),\n" +
                "rotr==isatom->();(drop ++ take)°id ee pred°length\n" +
                "times==[2]°((ispos°[1])->*[0],(pred°[1]),([0] app [2]),)°([0],[1])°ee\n" +
                "foldl==(not°isprop°[2]) -> [1] ; foldl ° [0],([0] app [1] ee head°[2]),(tail°[2]),\n" +
                "foldr==foldl ° ([0] comp '([1] ee [0])),[1],(reverse°[2]),\n" +
                "== == '== error \"Cannot define: \" & string°term"
    }

}  // end of class VirtualMachine

fun main() {      //   help:()     new ?      help:name    save:datei1.txt     load:datei1.txt     ???
    val vm = VirtualMachine()    // (fact aa)°0,iota°10
    val txt = vm.prelude()
    println(vm.toValue(vm.deflines(vm.splitTo(txt,"\n"))))
    // init()  // as (Any) -> Any)
    //println(vm.toValue(vm.calc("")))
    //println(vm.toValue(vm.calc("mul==(((ismat°[0]) and ismat°[1])->([0] MM [1]);fail)°ee")))
    //(""last==(isprop°'true)->*[0]°tail°[1]'()")
    //println(vm.toValue(vm.calc("A == ((1;2;3;);(4;5;8;);(7;8;9;);)")))
    //println(vm.toValue(vm.calc("try == id")))
    //println(vm.toValue(vm.calc("lood == '[1] act arg iput '_it ee (head°term) app arg")))
    //println(vm.toValue(vm.calc("savetext=='[5] act (arg iput '_self ee (head°term) app arg) iput '_para ee (head°term) app arg\n")))
    println(vm.toValue(vm.calc("45==46,47,48,")))
    println(vm.toValue(vm.calc("foldr ° '-,100,(iota°10),")))
    //println("hallo.txt".substringAfterLast("/"))
    //"last==(isprop°'true)->*[0]°tail°[1]'()") // hier bei comment _s ?
    //val abc: Any = idreserve
    // Cell(idmark,1234,Combine(Ident("add",123),"test1")) // Cell(Cell("abc",Ivar(abc),Cell(1234,1234.567,Nil())),Ident("°",123),
    // Cell(Ident("ee",456),Comment("ist ein Test"),Nil()))
    //println("Zelle == ${toValue(nbackcons(reverse(x)))}")
    //println("rev   == ${toValue(nreverse(reverse(nbackcons(nreverse(x)))))}")
//    println("identlist == ${vm.toValue(vm.identlist)}")
    //println("unicode(;) = ${';'.code}")
    //println("Kommentar == ${toValue(Comment(fisList(Cell("A",xcons,Nil()))))}")
    //var x = BigDecimal("3.0000000000000000000000")
    //var y = BigDecimal("-7.0000000000000000000000")
    //x = x.div(y) //,1000,RoundingMode.HALF_UP)
    //var i = x.compareTo(BigDecimal("0"))
    //var stringa = x as String
    //var bd = stringa as BigDecimal
    //stringa.min()
    //bd.min()
    //println("Big: x = " + x + " " + y + " i=" + i.toString())
}
