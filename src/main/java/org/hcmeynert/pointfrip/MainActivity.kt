package org.hcmeynert.pointfrip

//import android.R
import android.os.Bundle
import android.text.Layout
import android.text.Selection
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

var vm = VirtualMachine()
var initxt = vm.prelude()
// lateinit var et2: EditText

class MainActivity : AppCompatActivity() {

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
        val rlt: Button = findViewById<View>(R.id.button5) as Button
        val lft: Button = findViewById<View>(R.id.button6) as Button
        val rgt: Button = findViewById<View>(R.id.button7) as Button
        val et1 = findViewById<View>(R.id.editTextTextPersonName) as EditText
        val et2 = findViewById<View>(R.id.editTextTextPersonName2) as EditText

        //exe.setText("Evaluate")
        //et1.()

        exe.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val txt: String = et1.text.toString()
                val n = Selection.getSelectionStart(et1.getText()) // ???
                val lineN = selectline(txt,n)
                if (runningvm) runningvm = false
                else {  // hier ist der Thread
                    val thread: Thread = object : Thread(null, null, "pointfrip", 50000000) {
                    override fun run() {
                        val res = vm.toValue(vm.calc(lineN))
                        runOnUiThread {
                            et2.setText(res)
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
