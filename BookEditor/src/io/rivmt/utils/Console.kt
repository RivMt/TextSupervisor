package io.rivmt.modulator

import io.rivmt.utils.utility.Log
import io.rivmt.utils.utility.Constants
import io.rivmt.utils.utility.Utility
import io.rivmt.utils.file.FileControl
import io.rivmt.modulator.editor.TextEditor
import io.rivmt.modulator.editor.NameEditor
import io.rivmt.modulator.language.name.NameObject

class Console(val title: String, val description: String) {
    

    fun start() {
        Log.v(title)
        Log.v(description)
        
        //Task
        var taskEnd = false //If taskEnd become true, quit program
        var taskCode: Int //Integer inputted by User
        
        do {
            //Show main menu
            showMainMenu()
            
            //Read user input
            taskCode = try {
                Integer.parseInt(readLine()) //Read user inputted integer
            } catch (e: Exception) {
                Constants.CODE_CONSOLE_INVALID //When input is not integer, return INVALID
            }
            
            //Handle task code
            handleTask(taskCode)
            
         } while (!taskEnd)

    }

    abstract fun showMainMenu()
    
    abstract fun readFile()
    
    abstract fun handleTask(taskCode: Int)

}