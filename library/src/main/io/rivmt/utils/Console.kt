package io.rivmt.utils

import io.rivmt.utils.utility.Log
import io.rivmt.utils.utility.Constants

abstract class Console {

    fun start(taskEnd: Boolean) {
        
        //Task
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