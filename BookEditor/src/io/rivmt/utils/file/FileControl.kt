package io.rivmt.utils.file

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.InputStream
import java.lang.Exception
import java.io.FileNotFoundException

import io.rivmt.modulator.language.Japanese2Korean
import io.rivmt.modulator.language.name.NameObject
import io.rivmt.utils.utility.Log

class FileControl {
    
    companion object {
        
        fun loadTextFile(fileName: String?): MutableList<String> {
            return try {
                Log.v("${fileName}을 불러왔습니다")
                fileName?.let { readText(it) }!!
            } catch (e: FileNotFoundException) {
                Log.v("파일이 존재하지 않습니다")
                mutableListOf()
            }
        }
    
        private fun readText(fn: String): MutableList<String> {
            val inputStream: InputStream = File(fn).inputStream()
            val lineList = mutableListOf<String>()

            inputStream.bufferedReader().forEachLine {
                lineList.add(it)
            }

            inputStream.close()

            return lineList
        }
        
        fun loadNameListFile(fileName: String?): MutableList<NameObject> {
            return try {
                Log.v("${fileName}을 불러왔습니다")
                fileName?.let { FileControl.readName(it) }!!
            } catch (e: FileNotFoundException) {
                Log.v("파일이 존재하지 않습니다")
                mutableListOf()
            }
        }
    
        private fun readName(fn: String): MutableList<NameObject> {
            val inputStream: InputStream = File(fn).inputStream()
            val nameList = mutableListOf<NameObject>()

            inputStream.bufferedReader().forEachLine {
                try {
                    val obj = NameObject(
                        it.split("&")[0],
                        Japanese2Korean.makeNameCases(it.split("&")[0]),
                        it.split("&")[1]
                    )
                    nameList.add(obj)
                    Log.d(obj.toString())
                } catch (e: Exception) {
                    Log.e("${it}를 파싱하는데 실패했습니다.\n$e")
                }
            }

            inputStream.close()

            return nameList
        }

        fun saveText(fn: String?, text: MutableList<String>) {
            try {
                val file = File("$fn")

                file.createNewFile()

                val writer = BufferedWriter(FileWriter(file))

                for(item in text) {
                    if (item != "") {
                        writer.append("$item\n")
                    }
                }

                writer.close()

            } catch (e: Exception) {
                Log.e("파일 ${fn}을(를) 저장하는데 실패했습니다\n$e")
                return
            }

        }

    }
}