package com.example.notetoself

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.noteapp.R

class DialogShowNote : DialogFragment() {
    private var note:Note?=null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder= AlertDialog.Builder(this.requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_show_note,null)
        val txtTitle= dialogView.findViewById(R.id.txttitle) as TextView
        val txtDescription= dialogView.findViewById(R.id.txtDescription) as TextView
        txtTitle.text=note!!.title
        txtDescription.text=note!!.description
        val txtImportant= dialogView.findViewById(R.id.textViewImportant) as TextView
        val txtTodo= dialogView.findViewById(R.id.textViewTodo) as TextView
        val txtIdea= dialogView.findViewById(R.id.textViewIdea) as TextView
        if(!note!!.important) txtImportant.visibility= View.GONE
        if(!note!!.todo) txtImportant.visibility= View.GONE
        if(!note!!.idea) txtImportant.visibility= View.GONE
        val btnOk=dialogView.findViewById(R.id.btnOK) as Button
        builder.setView(dialogView).setMessage("Your Note")
        btnOk.setOnClickListener{
            dismiss()
        }
        return builder.create()
    }
    fun sendNoteSelected(noteSelected:Note){
        note=noteSelected
    }
}