package org.hcmeynert.pointfrip

//import android.R

//import android.R
import android.os.Bundle
import android.text.Layout
import android.text.Selection
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.File


var vm = VirtualMachine()
var itxt = vm.prelude()
var otxt = vm.toValue(vm.deflines(vm.splitTo(itxt,"\n")))
lateinit var et1: EditText
lateinit var et2: EditText

class MainActivity : AppCompatActivity() {

    var idload: Ident = Ident("load",Nil())
    var idsave: Ident = Ident("save",Nil())
    var idloadtext: Ident = Ident("loadtext",Nil())
    var idsavetext: Ident = Ident("savetext",Nil())

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.pf_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val path = applicationContext.filesDir
        val dir = File(path,"pf")

        when (item.itemId) {
            R.id.clear -> {
                vm = VirtualMachine()
                itxt = vm.prelude()
                otxt = vm.toValue(vm.deflines(vm.splitTo(itxt,"\n")))
                et1.setText("")
                return true  }
            R.id.result  -> {
                //Selection.
                val textToInsert : String = et2.text.toString()
                val start = Math.max(et1.getSelectionStart(), 0)
                val end = Math.max(et1.getSelectionEnd(), 0)
                et1.getText().replace(
                    Math.min(start, end), Math.max(start, end),
                    textToInsert, 0, textToInsert.length  )
                return true  }
            /*
            R.id.load  -> {
                val file = File(dir, "Test.txt")
                val contents = file.readText()
                et1.setText(contents)
                //doit
                return true  }
            */
            /*
            R.id.save  -> {
                if (!dir.exists()) dir.mkdir()
                val file = File(dir,"Test.txt")
                file.writeText("record goes here")
                //doit
                return true  } */
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun doAct(a: Act): Any {
        when (a.num) {
            1.toLong() -> {
                val rget = vm.get(idload,a.data,vm.xit)
                val rname = when (rget) {
                    is Ident  -> rget.pname.substringAfterLast("/")
                    is String -> rget.substringAfterLast("/")
                    else      -> ""     }
                if (rname!="") {
                    val rpath = applicationContext.filesDir
                    val rdir = File(rpath,"pf")
                    if (!rdir.exists()) rdir.mkdir()
                    val rfile = File(rdir, rname)
                    if (rfile.exists()) {
                        val rtxt = rfile.readText()
                        //vm = VirtualMachine()=>vergisst Daten
                        //itxt = vm.prelude()
                        //otxt = vm.toValue(vm.deflines(vm.splitTo(itxt,"\n")))
                        val txt = vm.toValue(vm.deflines(vm.splitTo(rtxt,"\n")))
                        runOnUiThread {  et1.setText(rtxt)  }
                        return vm.run(a.bind,a.data)
                    } else return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idload,"Datei existiert nicht")))
                } else return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idload,"Fehlerhafter Filename")))
            }
            2.toLong() -> {
                val wget = vm.iget(idsave,a.data,vm.xit)
                val wname = when (wget) {
                    is Ident  -> wget.pname.substringAfterLast("/")
                    is String -> wget.substringAfterLast("/")
                    else      -> ""     }
                if (wname!="") {
                    val wpath = applicationContext.filesDir
                    val wdir = File(wpath,"pf")
                    if (!wdir.exists()) wdir.mkdir()
                    val wfile = File(wdir,wname)
                    val wtxt = et1.text.toString()
                    wfile.writeText(wtxt)
                    return vm.run(a.bind,a.data)
                } else return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idsave,"Fehlerhafter Filename")))
            }
            3.toLong() -> {
                val fpath = applicationContext.filesDir
                val fdir = File(fpath,"pf")
                if (!fdir.exists()) fdir.mkdir()
                val flist = fdir.listFiles()
                var f: Any = Nil()
                flist.forEach{
                    f = Cell(it.toString().substringAfterLast("/"),vm.xcons,f)  }
                return vm.run(a.bind,vm.iput(a.data,vm.xit,vm.nreverse(f)))
            }
            4.toLong() -> {
                val rget = vm.iget(idloadtext,a.data,vm.xit)
                val rname = when (rget) {
                    is Ident  -> rget.pname.substringAfterLast("/")
                    is String -> rget.substringAfterLast("/")
                    else      -> ""     }
                if (rname!="") {
                    val rpath = applicationContext.filesDir
                    val rdir = File(rpath,"pf")
                    if (!rdir.exists()) rdir.mkdir()
                    val rfile = File(rdir, rname)
                    if (rfile.exists()) {
                        val rtxt = rfile.readText()
                        return vm.run(a.bind,vm.iput(a.data,vm.xit,rtxt))
                    } else return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idloadtext,"Datei existiert nicht")))
                } else return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idloadtext,"Fehlerhafter Filename")))
            }
            5.toLong() -> {
                val wself = vm.iget(idsavetext,a.data,vm.xself)
                val wpara = vm.iget(idsavetext,a.data,vm.xpara)
                if (wpara !is String)
                    return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idsavetext,"String als Operand[1] erwartet")))
                val wname = when (wself) {
                    is Ident  -> wself.pname.substringAfterLast("/")
                    is String -> wself.substringAfterLast("/")
                    else      -> ""      }
                if (wname!="") {
                    val wpath = applicationContext.filesDir
                    val wdir = File(wpath,"pf")
                    if (!wdir.exists()) wdir.mkdir()
                    val wfile = File(wdir,wname)
                    wfile.writeText(wpara)
                    return vm.run(a.bind,vm.iput(a.data,vm.xit,wname))
                } else return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idsavetext,"Fehlerhafter Filename")))
            }
            is Act     -> {  return "Test:Act"}
            else       -> {  return "Test:else"}
        }
    }

    fun selectline(txt: String,n: Int): String {
        var i: Int = n-1
        var k: Int = n
        val cr: Char = 13.toChar()
        val lf: Char = 10.toChar()
        var quit: Boolean = false
        do {  if (i==-1) quit = true
              else if (txt[i]==lf) quit = true
              else if (txt[i]==cr) quit = true
        else i = i - 1
        } while (!quit)
        quit = false
        do {  if (k>=txt.length) quit = true
              else if (txt[k]==lf) quit = true
              else if (txt[k]==cr) quit = true
        else k = k + 1
        } while (!quit)
        return txt.substring(i+1,k)
    }

    fun getCurrentCursorLine(editText: EditText): Int
    {   val selectionStart: Int = Selection.getSelectionStart(editText.getText())
        val layout = editText.getLayout() as Layout
        if (selectionStart != -1) {
            return layout.getLineForOffset(selectionStart)
        }
        return -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // answer the questions by tipping on the buttons
        //MainKt.getTxt();

        // answer the questions by tipping on the buttons
        val exe: Button = findViewById<View>(R.id.button) as Button
        val comp: Button = findViewById<View>(R.id.button2) as Button
        val par: Button = findViewById<View>(R.id.button3) as Button
        val brk: Button = findViewById<View>(R.id.button4) as Button
        //val rlt: Button = findViewById<View>(R.id.button5) as Button
        val lft: Button = findViewById<View>(R.id.button6) as Button
        val rgt: Button = findViewById<View>(R.id.button7) as Button
        et1 = findViewById<View>(R.id.editTextTextPersonName) as EditText
        et2 = findViewById<View>(R.id.editTextTextPersonName2) as EditText

        //exe.setText("Evaluate")
        //et2.setText(otxt)

        exe.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val txt: String = et1.text.toString()
                val n = Selection.getSelectionStart(et1.getText()) // ???
                val lineN = selectline(txt,n)
                if (runvm) runvm = false
                else {  // hier ist der Thread
                    val thread: Thread = object : Thread(null, null, "pointfrip", 50000000) {
                    override fun run() {
                        var res = vm.calc(lineN)
                        while (res is Act) { res = doAct(res) }
                        runOnUiThread {
                            et2.setText(vm.toValue(res))
                        }  }  }
                    thread.start()  }
            } })

        comp.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Selection.
                val textToInsert : String = "°"
                val start = Math.max(et1.getSelectionStart(), 0)
                val end = Math.max(et1.getSelectionEnd(), 0)
                et1.getText().replace(
                    Math.min(start, end), Math.max(start, end),
                    textToInsert, 0, textToInsert.length  )
            } })

        par.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Selection.
                val textToInsert : String = "()"
                val start = Math.max(et1.getSelectionStart(), 0)
                val end = Math.max(et1.getSelectionEnd(), 0)
                et1.getText().replace(
                    Math.min(start, end), Math.max(start, end),
                    textToInsert, 0, textToInsert.length  )
                val p = et1.getSelectionStart()
                et1.setSelection(p-1)
            } })

        brk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Selection.
                val textToInsert : String = "[]"
                val start = Math.max(et1.getSelectionStart(), 0)
                val end = Math.max(et1.getSelectionEnd(), 0)
                et1.getText().replace(
                    Math.min(start, end), Math.max(start, end),
                    textToInsert, 0, textToInsert.length  )
                val p = et1.getSelectionStart()
                et1.setSelection(p-1)
            } })

        /*
        rlt.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Selection.
                val textToInsert : String = et2.text.toString()
                val start = Math.max(et1.getSelectionStart(), 0)
                val end = Math.max(et1.getSelectionEnd(), 0)
                et1.getText().replace(
                    Math.min(start, end), Math.max(start, end),
                    textToInsert, 0, textToInsert.length  )
            } })
        */

        lft.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Selection.
                val p = et1.getSelectionStart()
                if (p>0) et1.setSelection(p-1)
            } })

        rgt.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Selection.
                val p = et1.getSelectionStart()
                if (p<et1.text.length) et1.setSelection(p+1)
            } })

        //
    }
}
