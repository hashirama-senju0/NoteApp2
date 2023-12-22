package com.example.notetoself

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.noteapp.MainActivity
import com.example.noteapp.R

class DialogNewNote : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater=requireActivity().layoutInflater
        val dialogView=inflater.inflate(R.layout.dialog_new_note,null)
        val editTitle=dialogView.findViewById(R.id.editTitle) as EditText
        val editDescription=dialogView.findViewById(R.id.editDescription) as EditText
        val checkboxIdea=dialogView.findViewById(R.id.checkBoxIdea) as CheckBox
        val checkBoxTodo=dialogView.findViewById(R.id.checkBoxTodo) as CheckBox
        val checkBoxImportant=dialogView.findViewById(R.id.checkBoxImportant) as CheckBox
        val btnCancel=dialogView.findViewById(R.id.btnCancel) as Button
        val btnOK=dialogView.findViewById(R.id.btnOK) as Button
        builder.setView(dialogView).setMessage("Add a new note")
        btnCancel.setOnClickListener{
            dismiss()
        }
        btnOK.setOnClickListener{
            val newnote=Note()
            newnote.title=editTitle.text.toString()
            newnote.description=editDescription.text.toString()
            newnote.idea=checkboxIdea.isChecked
            newnote.important=checkBoxImportant.isChecked
            newnote.todo=checkBoxTodo.isChecked
            val callingActivity=activity as MainActivity?
            callingActivity!!.createNewNote(newnote)
            dismiss()
        }
        return builder.create()

    }
}