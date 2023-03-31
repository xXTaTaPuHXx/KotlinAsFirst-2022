@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1


// Урок 9: проектирование классов
// Максимальное количество баллов = 40 (без очень трудных задач = 15)

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая (2 балла)
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    val matrix = List(height) { List(width) { e } }
    return MatrixImpl(matrix)
}

/**
 * Средняя сложность (считается двумя задачами в 3 балла каждая)
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(private val matrix: List<List<E>>) : Matrix<E> {

    override val height: Int = matrix.size

    override val width: Int = matrix[0].size

    override fun get(row: Int, column: Int): E = matrix[row][column]

    override fun get(cell: Cell): E = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
    }

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?) = other is MatrixImpl<*> && height == other.height && width == other.width

    override fun toString(): String {
        val newString = StringBuilder()
        newString.append("[")
        for (i in matrix.indices) {
            newString.append("[")
            for (j in matrix[i].indices) {
                newString.append(matrix[i][j])
            }
            newString.append("]")
        }
        newString.append("]")
        return newString.toString()
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        return result
    }
}

