class LineObject(var text: String) {
    var flags = mutableMapOf<Int, Boolean>()
    
    companion object {
        const val FLAGS_MANUAL_MODIFICATION_NEEDED = 1;
        const val FLAGS_MACHINE_HANDLED = 2;
    }
    
    
}