package com.example.notetoself

import org.json.JSONException
import org.json.JSONObject

class Note {
    private val JSON_TITLE="title"
    private val JSON_DESCRIPTION="description"
    private val JSON_IDEA="idea"
    private val JSON_TODO="todo"
    private val JSON_IMPORTANT="important"

    @Throws(JSONException::class)
    constructor(jo:JSONObject){
        title=jo.getString(JSON_TITLE)
        description=jo.getString(JSON_DESCRIPTION)
        idea=jo.getBoolean(JSON_IDEA)
        todo=jo.getBoolean(JSON_TODO)
        important=jo.getBoolean(JSON_IMPORTANT)
    }
    constructor(){

    }
    @Throws(JSONException::class)
    fun convertToJSON():JSONObject{
        val jo=JSONObject()
        jo.put(JSON_TITLE,title)
        jo.put(JSON_DESCRIPTION,description)
        jo.put(JSON_IDEA,idea)
        jo.put(JSON_IMPORTANT,important)
        jo.put(JSON_TODO,todo )
        return jo
    }
    var title: String? = null
    var description: String? = null
    var idea: Boolean = false
    var todo: Boolean = false
    var important: Boolean = false
}
