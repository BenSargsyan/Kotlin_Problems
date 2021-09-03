fun main() {
    var x=listOf("abc".toList(), "de".toList(), "fgh".toList(), "de".toList(), "ijkl".toList(), "mn".toList(), "o".toList())
    println(x.lengthSort())
}


/////////////////////////////////////////////////////LISTS///////////////////////////////////////////////////////


fun <T>List<T>.last()=this[this.lastIndex] // #1

fun <T>List<T>.penultimate()=this[this.lastIndex-1] // #2

fun <T> nth(n:Int,list:List<T>):T =list[n] // #3

fun <T> length(list:List<T>):Int=list.lastIndex+1 // #4

fun <T> reverse(list:List<T>):List<T> =list.reversed() // #5

fun <T> isPalindrome(list:List<T>):Boolean=list==reverse(list) // #6

fun <T> flatten(list:List<T>): List<Any?> = // #7
    list.flatMap {
        if(it is List<*>) flatten(it as List<Any>) else listOf(it)
    }

fun <T> removeDup(list:List<T>): List<T> = list.toSet().toList() //#8

fun <T> pack(list:List<T>): List<List<T>> //#9
{
    val packed = list.takeWhile { it == list.first() }
    return listOf(packed) + pack(list.drop(packed.size))
}


fun <T> encode(list:List<T>): List<Pair<Int,T>> //#10
{
    return pack(list).map { Pair(it.size,it.last()) }
}

fun<T> List<T>.encodeModified():List<T> { //#11
    pack(this).map { if(it.size==1)
    {
        it.first()
    }
        else
            Pair(it.size,it.first())
    }
    return this
}

fun <T> decode(list: List<Pair<Int, T>>): List<T> = list.flatMap { (count, value) -> List(count) { value } } //#12

/*fun <T> encodeDirect(list:List<T>):List<Pair<Int,T>> //13
{
    var ret= mutableListOf<Pair<Int,T>>()
    var counter=0
    var index=0
    var current:T=list.first()
    for(i in list)
    {
        while(i==current)
        {
         ++counter
        }
        ret[index].first=counter
        ret[index].second=i
        counter=0
        current=i
        }
        return ret
 */


fun <T> duplicate(list:List<T>):List<T> = list.flatMap { listOf(it,it) } //#14

fun <T> duplicateNTimes(list:List<T>,num:Int):List<T>//#15
{
    return generateSequence { list }.take(num).flatten().toList().sortedBy { it.hashCode()}
}

fun <T> List<T>.remove(index:Int):List<T> //#16
{
    var ret=mutableListOf<T>()
    for(i in this)
    {
        if(i==this[index]) {
            continue
        }
        ret.add(i)
    }
    return ret
}

fun <T>List<T>.split(index:Int) = Pair(this.take(index),this.reversed().take(this.size-index).reversed()) //#17

fun <T> List<T>.slice(x:Int,y:Int)= this.subList(x,y) //#18

fun <T> List<T>.rotate(num:Int) = this.drop(num)+this.take(num) //#19

fun <T> List<T>.removeFromList(index:Int):Pair<List<T>,T> = Pair(this.drop(index),this.toMutableList().removeAt(index)) //#20

fun <T> List<T>.insert(index: Int, element:T)= this.toMutableList().add(index,element) //#21

fun <T> List<T>.rangeOfList(first:Int,last:Int) = (first..last).toList() //#22

fun <T> List<T>.randomSelect(count:Int):List<T> //#23
{
    var ret=mutableListOf<T>()
    while(count!=0)
    {
        ret.add(this.random())
    }
    return ret.toList()
}

fun  lotto(index:Int,range:Int):List<Int> = (1..range).toList().randomSelect(index) //#24

fun <T> List<T>.randomPermute():List<T> = this.shuffled() //#25

//#26-?
//#27-?
fun <T> List<List<T>>.lengthSort()=this.sortedBy { it.size }//#28.1

fun <T> List<List<T>>.freqLength() = this.sortedBy { this.groupBy { it.size }.getValue(it.size).size } //#28.2
