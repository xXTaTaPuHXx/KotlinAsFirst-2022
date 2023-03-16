@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import java.io.File
import java.io.FileWriter

// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}

/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun deleteMarked(inputName: String, outputName: String) {
    val newLine = FileWriter(outputName)
    for (oldLine in File(inputName).readLines()) {
        if (oldLine.isEmpty() || oldLine.first() != '_') newLine.write(oldLine + "\n")
    }
    newLine.close()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val inputFile = File(inputName).readLines().joinToString().lowercase()
    return substrings.associateWith { sub ->
        inputFile.windowed(sub.length).count { it == sub.lowercase() }
    }
}


/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (23 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>Или колбаса</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>Фрукты
<ol>
<li>Бананы</li>
<li>Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}

fun foo(InputName: String, registers: List<Int>): List<Int> {
    val registers1 = registers.toMutableList()
    val k = File("C:\\Users\\Alpha&Aidar\\IdeaProjects\\KotlinAsFirst-2022\\InputName.txt").readLines()
    var i = 0
    while (i != k.size) {
        if (!Regex("""(((MOV|SWAP)\sR?\d+)?,\sR\d+)|(GOTO\s\d+)""").matches(k[i])) throw IllegalArgumentException()
        val file1 = k[i].replace(",", "").split(" ").toMutableList()
        val function = file1[0]
        file1.remove(function)
        when {
            (file1[0].replace("R", "").toInt() > registers.size) -> throw IllegalStateException()
            (file1[1].replace("R", "").toInt() > registers.size) -> throw IllegalStateException()
        }
        when (function) {
            "MOV" -> {
                registers1[file1[1][1].digitToInt()] = registers1[file1[0][1].digitToInt()]
            }

            "SWAP" -> {
                val swarp = registers1[file1[0][1].toString().toInt()]
                registers1[file1[0][1].toString().toInt()] = registers1[file1[1][1].toString().toInt()]
                registers1[file1[1][1].toString().toInt()] = swarp
            }

            "GOTO" -> {
                i = file1[0].toInt()
                i--
            }
        }
        i++
    }
    println(registers1)
    return registers1
}

// Ассемблер
fun foo1(InputName1: String, changes: List<String>, outputName: String): String {
    val outputfile = FileWriter(outputName, true)
    val changes1 = changes.toMutableList()
    for (line in File("C:\\Users\\Alpha&Aidar\\IdeaProjects\\KotlinAsFirst-2022\\InputName1.txt").readLines()) {
        outputfile.write(line + "\n")
        val b = mutableListOf<String>()
        for (i in changes1.indices) {
            val a = changes1[i].split(" ").toMutableList()
            b += a
        }
        // if (!Regex("""\d(\s\w+)+""").matches(InputName1)) throw java.lang.IllegalArgumentException()
        val file = line.split(" ").toMutableList()
        val first = file[0].toInt()
        try {

            if (first < b[0].toInt()) {
                outputfile.write(changes1[0] + "\n")
                changes1.remove(changes1[0])
            } else (outputfile.write(changes1[0]))
        } catch (_: IndexOutOfBoundsException) {
            break
        }
    }
    outputfile.close()
    println(outputfile)
    return outputName
}

// Вставить строку в файл
fun foo2(InputName2: String, limit: Int): List<String> {
    val k = File("C:\\Users\\Alpha&Aidar\\IdeaProjects\\KotlinAsFirst-2022\\InputName2.txt").readLines()
    val answer = mutableListOf<String>()
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()
    var line = 0
    while (line != k.size) {
        val a = k[line].split("--")
        val b = a[1].split(",")
        val priority = b[0].replace(" ", "")
        val time = b[1].replace(" ", "")
        val priorityInt = priority.replace("важность", "").toInt() // для сравнения важности
        val timeInt = time.replace("мин", "").toInt() // для сравнения времени
        list1 += priorityInt
        list2 += timeInt
        line++
    }
    while (limit > 0) {
        list1.sortedDescending()
        list2.sortedDescending()
        for (i in list2.indices) {
            val y = Pair(list1[i], list2[i])
            limit - y.second
            answer
        }
        listOf("")
    }
    return listOf()
}

// недоделал
fun foo3(InputName3: String, src: String, dst: String): String {
    val file3 = File(InputName3).readLines()
    var i = 0
    while (i != file3.size) {
        val parts = file3[i].split(" ")
        val transport = parts[0]
        val a = file3[i].split(" ")
        val b = a.subList(2, a.size)
        val srcSplited = src.split(" ")
        val dstSplited = dst.split(" ")
        val srcI = b.indexOf(srcSplited[1])
        val dstI = b.indexOf(dstSplited[1])
        if (srcI != -1 && dstI != -1 && srcI < dstI)
            return "$transport ${parts[1]}"
        i++
    }
    return ""
}
// Маршрутка

fun foo4(InputName2: String, limit: Int): List<String> {
    val fileContent = File(InputName2).readLines()
    val tasks = fileContent.map { taskString ->
        val parts = taskString.split(" -- ")
        val name = parts[0]
        val importance = parts[1].split(", ")[0].split(" ")[1].toInt()
        val time = parts[1].split(", ")[1].split(" ")[0].toInt()
        Triple(name, importance, time)
    }
    val sortedTasks = tasks.sortedByDescending { it.second }
    val possiblePairs = mutableListOf<Pair<Triple<String, Int, Int>, Triple<String, Int, Int>>>()
    for (i in 0 until sortedTasks.size - 1) {
        for (j in i + 1 until sortedTasks.size) {
            val task1 = sortedTasks[i]
            val task2 = sortedTasks[j]
            if (task1.third + task2.third <= limit) {
                possiblePairs.add(Pair(task1, task2))
            }
        }
    }
    return possiblePairs.maxByOrNull { it.first.second + it.second.second }
        ?.let { listOf(it.first.first, it.second.first) } ?: emptyList()
}


fun matrixtask(matrix: List<List<Int>>): List<List<Int>> {
    val list = mutableListOf<Int>()
    val b = matrix[0].size
    for (i in matrix.indices) {
        if (matrix[i].size == b) continue
    }
    for (row in matrix) {
        for (element in row) {
            list.add(element)
            if (list.toSet().size == list.size) return List(b) { col ->
                List(matrix.size) { row ->
                    matrix[row][col]
                }
            }
            else continue
        }
    }
    return listOf()
}

fun rotateMatrixClockwise(matrix: List<List<Int>>): List<List<Int>> {
    val numRows = matrix.size
    val numCols = matrix[0].size
    val matrix1 = List(numRows) { row -> List(numCols) { col -> matrix[col][row] } }.map { it.reversed() }
    if (numRows == numCols) {
        return matrix1
    }
    return listOf()
}

fun sumMatrixRows(matrix: List<List<Int>>): List<Int> {
    matrix.size
    matrix[0].size
    val anwer = mutableListOf<Int>()
    for (i in matrix.indices) {
        anwer += matrix[i].sumOf { it }
    }

    return anwer
}

fun findMaxElement(matrix: List<List<Int>>): Int = matrix.flatten().max()
fun averageMatrix(matrix: List<List<Int>>): List<List<Int>> {
    TODO()
}

fun trianglematrix(matrix: List<List<Int>>): List<List<Int>> {
    var sum = 0
    for (i in matrix[0].indices) {
        val maxIndexStr = matrix[i].maxOf { it }
        val v = matrix[i].indexOf(maxIndexStr)
        for (j in matrix.indices) {
            val c = matrix[i][j]
            sum += c
            val b = 0
        }
    }
    return listOf()
}

fun findMaxPath(matrix: List<List<Int>>): Int {
    val n = matrix.size
    val m = matrix[0].size
    val dp = Array(n) { IntArray(m) }

    // инициализация первой строки и первого столбца
    dp[0][0] = matrix[0][0]
    for (i in 1 until n) {
        dp[i][0] = dp[i - 1][0] + matrix[i][0]
    }
    for (j in 1 until m) {
        dp[0][j] = dp[0][j - 1] + matrix[0][j]
    }

    // заполнение остальной матрицы
    for (i in 1 until n) {
        for (j in 1 until m) {
            dp[i][j] = matrix[i][j] + maxOf(dp[i - 1][j], dp[i][j - 1])
        }
    }

    // возвращаем максимальную сумму элементов пути до правого нижнего угла матрицы
    return dp[n - 1][m - 1]
}

fun stringParsing(numbers: List<String>): Int {
    var answer = 0
    var j = 0
    val split = numbers[0].replace("-", ".").replace("+", ".").split(".")
    try {
        for (i in 0..numbers[0].length) {
            if (numbers[0][i].toString() == "+") {
                answer += split[j].toInt()
                j++
            }
            if (numbers[0][i].toString() == "-") {
                answer += split[j].toInt()
                j++
            }
        }
    } catch (_: StringIndexOutOfBoundsException) {
    }
    return answer
}

fun averageDiagonal(matrix: List<List<Int>>): Double {
    val diagonal = mutableListOf<Int>()
    for (i in matrix.indices) {
        diagonal += matrix[i][i]
    }
    return diagonal.sumOf { it }.toDouble() / diagonal.size
}