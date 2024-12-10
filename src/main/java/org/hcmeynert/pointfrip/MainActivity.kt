package org.hcmeynert.pointfrip

//import android.R

//import android.R
// Activity
import android.app.Activity
import android.content.*
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Layout
import android.text.Selection
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
// ActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.net.toFile
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.*


var vm = VirtualMachine()
var itxt = vm.prelude()
var otxt = vm.toValue(vm.deflines(vm.splitTo(itxt,"\n")))


class MainActivity : AppCompatActivity() {

    lateinit var et1: EditText
    lateinit var et2: EditText

    var idload: Ident = Ident("load",Nil())
    var idsave: Ident = Ident("save",Nil())
    var idloadtext: Ident = Ident("loadtext",Nil())
    var idsavetext: Ident = Ident("savetext",Nil())
    var idfremove = Ident("fremove",Nil())
    var idviewurl = Ident("viewurl",Nil())

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.pf_menu, menu)
        return true
    }

    /*
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            //val selectedFile = data?.data // The URI with the location of the file
            var txt: String = ""
            var ln: String = ""
            data?.data?.let {
                contentResolver.openInputStream(it)
            }?.let {
                val r = BufferedReader(InputStreamReader(it))
                while (true) {
                    val line: String? = r.readLine() ?: break
                    txt = txt + ln + line
                    ln = "\n"
                }
            }
            val etxt = vm.toValue(vm.deflines(vm.splitTo(txt,"\n")))

            //val selectedFile = data.getData() // The URI with the location of the file

            //val inputStream = getContentResolver().openInputStream(selectedFile)
            //val allText = inputStream.bufferedReader().use(BufferedReader::readText)

            val fname = selectedFile?.getPath()?.substringAfterLast(":/")

            val rfile = File(fname) // ?.substringBeforeLast("/"),fname?.substringAfterLast("/"))
            if (rfile.exists()) {
                val rtxt = rfile.readText()
                //val etxt = vm.toValue(vm.deflines(vm.splitTo(rtxt,"\n")))
                et1.setText(rtxt)
                //et2.setText(txt)
            } else {
                et1.setText("dont exs")
            }

            et1.setText(txt)
            et2.setText(etxt)
            //doSomeOperations()
        }
    }
*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == RESULT_OK) {
            var txt: String = ""
            var ln: String = ""
            //val selectedFile = data?.data // The URI with the location of the file
            data?.data?.let {
                contentResolver.openInputStream(it)
            }?.let {
                val r = BufferedReader(InputStreamReader(it))
                while (true) {
                    val line: String? = r.readLine() ?: break
                    txt = txt + ln + line
                    ln = "\n"
                }  }
            val etxt = vm.toValue(vm.deflines(vm.splitTo(txt,"\n")))
            et1.setText(txt)
            et2.setText(etxt)
        }  }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val path = applicationContext.filesDir
        val dir = File(path,"pf")

        when (item.itemId) {
            R.id.result  -> {
                //Selection.
                val textToInsert : String = et2.text.toString()
                val start = Math.max(et1.getSelectionStart(), 0)
                val end = Math.max(et1.getSelectionEnd(), 0)
                et1.getText().replace(
                    Math.min(start, end), Math.max(start, end),
                    textToInsert, 0, textToInsert.length  )
                return true  }
            R.id.clear -> {
                vm = VirtualMachine()
                itxt = vm.prelude()
                otxt = vm.toValue(vm.deflines(vm.splitTo(itxt,"\n")))
                et1.setText("")
                return true  }
            R.id.load  -> {
                /*
                val file = File(dir, "Test.txt")
                val contents = file.readText()
                et1.setText(contents)
                //doit
                */

                val intent = Intent()
                    .setType("*/*")
                    .setAction(Intent.ACTION_GET_CONTENT)
                //resultLauncher.launch(Intent.createChooser(intent, "Select a file"))
                startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
                return true  }
            R.id.copy  -> {
                //et1.selectAll()

                val textToCopy = et1.text
                val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("text", textToCopy)
                clipboardManager.setPrimaryClip(clipData)

                /*
                if (!dir.exists()) dir.mkdir()
                val file = File(dir,"Test.txt")
                file.writeText("record goes here")
                //doit
                */
                return true  }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun doAct(a: Act): Any {
        try {
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
                    } else return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idload,"File not found")))
                } else return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idload,"Error in filename")))
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
                } else return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idsave,"Error in filename")))
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
                    } else return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idloadtext,"File not found")))
                } else return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idloadtext,"Error in filename")))
            }
            5.toLong() -> {
                val wself = vm.iget(idsavetext,a.data,vm.xself)
                val wpara = vm.iget(idsavetext,a.data,vm.xpara)
                if (wpara !is String)
                    return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idsavetext,"String expected as operand[1]")))
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
                } else return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idsavetext,"Error in filename")))
            }
            6.toLong() -> {
                val fnm = vm.iget(idfremove,a.data,vm.xit)
                val rname = when (fnm) {
                    is Ident  -> fnm.pname.substringAfterLast("/")
                    is String -> fnm.substringAfterLast("/")
                    else      -> ""     }
                if (rname=="") return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idfremove,"Error in filename")))
                val rpath = applicationContext.filesDir
                val rdir = File(rpath,"pf")
                if (!rdir.exists()) rdir.mkdir()
                val rfile = File(rdir, rname)
                if (rfile.exists() && rfile.isFile) {
                    rfile.delete()
                    return vm.run(a.bind,vm.iput(a.data,vm.xit,!rfile.exists()))
                } else return vm.run(a.bind,vm.iput(a.data,vm.xit,false))
            }
            7.toLong() -> {
                var url = vm.iget(idviewurl,a.data,vm.xit)
                if (url !is String)
                    return vm.run(a.bind,vm.iput(a.data,vm.xit,Error(idviewurl,"String expected")))
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://$url"
                }
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)
                return vm.run(a.bind,a.data)
            }
            8.toLong() -> {
                val d = Date().toString()
                return vm.run(a.bind,vm.iput(a.data,vm.xit,d))
            }
            is Act     -> {  return "Test:Act"}
            else       -> {  return "Test:else"}
        }
        } catch (e: Exception) {  return Error(vm.idipr,e.message as String)  }
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
        et1 = findViewById<View>(R.id.editTextInText) as EditText
        et2 = findViewById<View>(R.id.editTextOutText) as EditText

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
