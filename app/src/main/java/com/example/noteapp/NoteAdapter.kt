package com.example.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notetoself.Note

class NoteAdapter(
    private val mainActivity: MainActivity,
    private val noteList: ArrayList<Note>?
) : RecyclerView.Adapter<NoteAdapter.ListItemHolder>() {
    inner class ListItemHolder(view: View) :
        RecyclerView.ViewHolder(view),
        View.OnClickListener {
        internal var title = view.findViewById<View>(R.id.textViewTitle) as TextView
        internal var description = view.findViewById<View>(R.id.textViewDescription) as TextView
        internal var status = view.findViewById<View>(R.id.textViewStatus) as TextView

        init {
            view.isClickable = true
            view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            mainActivity.showNote(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ListItemHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem, parent, false)
        return ListItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ListItemHolder, position: Int) {
        val note = noteList?.get(position)
        holder.title.text = note!!.title
        holder.description!!.toString()
        when {
            note.idea -> holder.status.text =
                mainActivity.resources.getString(R.string.idea_text)

            note.important -> holder.status.text =
                mainActivity.resources.getString(R.string.important_text)

            note.todo -> holder.status.text =
                mainActivity.resources.getString(R.string.todo_next)
        }
    }

    override fun getItemCount(): Int {
        if (noteList != null) {
            return noteList.size
        }
        return -1
    }

}

