package io.rivmt.modulator

import io.rivmt.utils.utility.Log

public static void main(String[] args) {
    Log.v("텍본 교열 장치")
    Log.v("텍본의 문장 부호, 문단 서식 등을 수정합니다.\n강제개행된 텍스트에 사용하지 마십시오.")
    
    ModulatorConsole().start(taskEnd)
} 