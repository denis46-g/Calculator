package mmcs.assignment3_calculator.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

var subDisplay = "" // Промежуточная строка вывода
var lastNumber = Double.MIN_VALUE
var lastOper = Operation.SUB

fun recomputeSubDisplay(num: MutableList<String>, oper: MutableList<Operation>) {

    var sub_num = mutableListOf<String>()
    for(i in 0 until num.size)
        sub_num.add(num[i])
    var sub_oper = mutableListOf<Operation>()
    for(i in 0 until oper.size)
        sub_oper.add(oper[i])

    lastNumber = sub_num[sub_num.size - 1].toDouble()

    var i = 0
    // деление и умножение
    while(i < sub_oper.size) {
        if (sub_oper[i] == Operation.MUL || sub_oper[i] == Operation.DIV) {
            if(sub_oper[i] == Operation.DIV && sub_num[i+1].toDouble() == 0.0) {
                subDisplay = ""
                return
            }
            if(sub_oper[i] == Operation.MUL)
                sub_num[i] = (sub_num[i].toDouble() * sub_num[i+1].toDouble()).toString()
            else
                sub_num[i] = (sub_num[i].toDouble() / sub_num[i+1].toDouble()).toString()
            sub_num.removeAt(i+1)
            if(sub_oper.size == 1)
                lastOper = sub_oper[0]
            sub_oper.removeAt(i)
        }
        else i++
    }

    // сложение и вычитание
    while(sub_oper.size > 0) {
        if(sub_oper[0] == Operation.ADD)
            sub_num[0] = (sub_num[0].toDouble() + sub_num[1].toDouble()).toString()
        else
            sub_num[0] = (sub_num[0].toDouble() - sub_num[1].toDouble()).toString()
        sub_num.removeAt(1)
        if(sub_oper.size == 1)
            lastOper = sub_oper[0]
        sub_oper.removeAt(0)
    }

    //результат
    if(sub_num[0].toDouble()-sub_num[0].toDouble().toInt() == 0.0)
        subDisplay = "${sub_num[0].toDouble().toInt()}"
    else
        subDisplay = sub_num[0]
}

class CalculatorViewModel: BaseObservable(), Calculator {
    override var display = ObservableField<String>()

    //#0F4ADF - синий цвет
    //#403C3C - тёмный цвет
    private var numbers: MutableList<String> = mutableListOf()
    private var operations: MutableList<Operation> = mutableListOf()
    private val dictOperations = mutableMapOf<Operation, String>(Operation.ADD to "+",
                                                                 Operation.SUB to "-",
                                                                 Operation.MUL to "×",
                                                                 Operation.DIV to "÷")


    override fun addDigit(dig: Int) {
        val current = display.get()
        if(current != null && current != ""){
            if(current == "0"){
                display.set(dig.toString())
                numbers[numbers.size - 1] = "$dig"
            }
            else if(current == "-0" || current == "-") {
                display.set("-$dig")
                if(numbers.size == 0)
                    numbers.add("-$dig")
                else
                    numbers[numbers.size - 1] = numbers[numbers.size - 1] + "-$dig"
            }
            else if(current[current.length - 1] == '0' && current[current.length - 2] != '.' && !current[current.length - 2].isDigit()){
                display.set(current.take(current.length - 1) + dig.toString())
                numbers[numbers.size - 1] = "$dig"
            }
            else if(current[current.length-1].isDigit() || current[current.length-1] == '.') {
                display.set(display.get() + dig.toString())
                numbers[numbers.size - 1] = numbers[numbers.size - 1] + "$dig"
            }
            else{
                display.set(display.get() + dig.toString())
                numbers.add("$dig")
            }
        }
        else{
            display.set("" + dig.toString())
            numbers.add("$dig")
        }


        if(numbers.size >= 2 && operations.size >= 1 && numbers.size == operations.size + 1)
            recomputeSubDisplay(numbers, operations)
        else
            subDisplay = ""
    }

    override fun addPoint() {
        val current = display.get()
        if(current != null && current != ""){
            if(current[current.length-1].isDigit()){
                var indexPoint = current.indexOfLast { it == '.' }
                var indexOperation = current.indexOfLast { it == '+' || it == '-' || it == '÷' || it == '×' }
                if (indexPoint < indexOperation || indexPoint == -1 && indexOperation == -1){
                    display.set(display.get() + '.')
                    numbers[numbers.size - 1] = numbers[numbers.size - 1] + '.'
                }
            }
            else if(dictOperations.values.contains(current[current.length-1].toString())) {
                if(current != "-"){
                    display.set(display.get() + "0.")
                    numbers.add("0.")
                }
                else{
                    display.set(display.get() + "0.")
                    numbers.add("-0.")
                }
            }
            else if(current[current.length - 1] != '.'){
                display.set(display.get() + "0.")
                numbers.add("-0.")
            }
        }
        else{
            display.set("0.")
            numbers.add("0.")
        }

        if(numbers.size >= 2 && operations.size >= 1 && numbers.size == operations.size + 1)
            recomputeSubDisplay(numbers, operations)
        else
            subDisplay = ""
    }

    override fun addOperation(op: Operation) {
        val current = display.get()
        if(current != null && current != ""){
            if(current[current.length - 1].isDigit() || current[current.length - 1] == '.'){
                operations.add(op)
                display.set(current + dictOperations[op])
            }
            else if (current == "-"){
                if(op == Operation.ADD)
                    display.set("")
                else display.set(current)
            }
            else {
                display.set(current.take(current.length - 1) + dictOperations[op])
                operations[operations.size - 1] = op
            }

        }
        else if (op == Operation.SUB){
            display.set("-")
        }
        subDisplay = ""
    }

    override fun compute() {
        if(subDisplay != "" && numbers.size >=2 &&
            operations.size == numbers.size - 1 &&
            subDisplay != "Недопустимый формат" && subDisplay != "На 0 делить нельзя")
        {
            display.set(subDisplay)
            numbers = mutableListOf(subDisplay)
            operations = mutableListOf()
            subDisplay = ""
        }
        else if (display.get()!=null && display.get() != ""){
            if(numbers.size == 1 && lastNumber > Double.MIN_VALUE) {
                if(lastOper == Operation.ADD) {
                    var f = display.get()!!.toDouble() + lastNumber
                    var d = f.toInt()
                    if(f-d == 0.0)
                        display.set(d.toString())
                    else
                        display.set(f.toString())
                }
                else if(lastOper == Operation.SUB){
                    var f = display.get()!!.toDouble() - lastNumber
                    var d = f.toInt()
                    if(f-d == 0.0)
                        display.set(d.toString())
                    else
                        display.set(f.toString())
                }
                else if(lastOper == Operation.MUL){
                    var f = display.get()!!.toDouble() * lastNumber
                    var d = f.toInt()
                    if(f-d == 0.0)
                        display.set(d.toString())
                    else
                        display.set(f.toString())
                }
                else{
                    var f = display.get()!!.toDouble() / lastNumber
                    var d = f.toInt()
                    if(f-d == 0.0)
                        display.set(d.toString())
                    else
                        display.set(f.toString())
                }
                numbers[0] = display.get()!!
            }
            if(operations.size > 0 && operations.size == numbers.size)
                subDisplay = "Недопустимый формат"
            else if(numbers.size >= 2 && operations.size == numbers.size - 1)
                subDisplay = "На 0 делить нельзя"
        }
    }

    override fun clear() {
        val current = display.get()
        if (current != null && current != ""){
            if(current[current.length - 1].isDigit()) {
                val lastNum = numbers[numbers.size - 1]
                if(lastNum.length > 1)
                    numbers[numbers.size - 1] = lastNum.take(lastNum.length - 1)
                else numbers.removeAt(numbers.size - 1)
            }
            else if(current[current.length - 1] == '.') {
                val lastNum = numbers[numbers.size - 1]
                numbers[numbers.size - 1] = lastNum.take(lastNum.length - 1)
            }
            else if (operations.size > 0)
                operations.removeAt(operations.size - 1)
            else
                numbers[0] = ""

            display.set(current.take(current.length - 1))
            if(numbers.size >= 2 && operations.size >= 1 && numbers.size == operations.size + 1)
                recomputeSubDisplay(numbers, operations)
            else
                subDisplay = ""
        }
    }

    override fun reset() {
        display.set("")
        subDisplay = ""
        numbers = mutableListOf()
        operations = mutableListOf()
    }
}