//программа сообщает пользователю, если он ошибся с вводом данных, но останавливается, я потом уже увидел
//что она не должна останавливаться, это легко сделать через цикл, но мне было уже лень переделывать

import kotlin.math.sqrt
import kotlin.math.pow

fun main() {

    println("введите координаты первой точки: ")
    var x = readln()?.toDoubleOrNull() ?: throw IllegalArgumentException("неверный ввод")
    var y = readln()?.toDoubleOrNull() ?: throw IllegalArgumentException("неверный ввод")
    val dot1 = Dot(x, y)

    println("введите координаты второй точки: ")
    x = readln()?.toDoubleOrNull() ?: throw IllegalArgumentException("неверный ввод")
    y = readln()?.toDoubleOrNull() ?: throw IllegalArgumentException("неверный ввод")
    val dot2 = Dot(x, y)

    println("введите координаты третьей точки: ")
    x = readln()?.toDoubleOrNull() ?: throw IllegalArgumentException("неверный ввод")
    y = readln()?.toDoubleOrNull() ?: throw IllegalArgumentException("неверный ввод")
    val dot3 = Dot(x, y)

    val triangle = Triangle(dot1, dot2, dot3)

    println("введите координаты точки, которую хотите проверить: ")
    x = readln()?.toDoubleOrNull() ?: throw IllegalArgumentException("неверный ввод")
    y = readln()?.toDoubleOrNull() ?: throw IllegalArgumentException("неверный ввод")
    val whereDot = Dot(x, y)

    val subTriangle1 = Triangle(whereDot, dot2, dot3)
    val subTriangle2 = Triangle(dot1, whereDot, dot3)
    val subTriangle3 = Triangle(dot1, dot2, whereDot)

    val sumOfAreaOfSubTriangle: Int = subTriangle1.CalcArea().toInt() + subTriangle2.CalcArea().toInt() + subTriangle3.CalcArea().toInt()


    print(triangle.toString() + "\nего периметр: ${triangle.calcPerimetr()}\nего площадь: ${triangle.CalcArea()}")
    if (sumOfAreaOfSubTriangle == triangle.CalcArea().toInt()) println("\nочка находиться внутри треугольника") else println("\nточка находиться снаружи треугольника")

}



class Dot(X:Double, Y:Double){

    public val _X = X
    public val _Y = Y

    override fun toString(): String {
        return "(${_X.toInt()}, ${_Y.toInt()})"
    }
}



class Triangle(X:Dot, Y:Dot, Z:Dot){

    private val _X = X
    private val _Y = Y
    private val _Z = Z

    private val _XY = sqrt(((_X._X - _Y._X).pow(2)) + ((_Y._Y - _X._Y).pow(2)))
    private val _YZ = sqrt(((_Y._X - _Z._X).pow(2)) + ((_Z._Y - _Y._Y).pow(2)))
    private val _ZX = sqrt(((_Z._X - _X._X).pow(2)) + ((_X._Y - _Z._Y).pow(2)))

    fun calcPerimetr():Double =_XY + _YZ +_ZX

    fun CalcArea():Double {

        val p:Double = this.calcPerimetr() / 2
        val h = sqrt(p * (p - _XY) * (p - _YZ) * (p - _ZX)) / _XY

        return (_XY * h) / 2
    }

    override fun toString(): String {
        return "треугольник с вершинами: ${this._X.toString()}, ${this._Y.toString()}, ${this._Z.toString()} создан!"
    }
}