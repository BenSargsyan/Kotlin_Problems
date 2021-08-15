import java.util.*
import kotlin.math.pow

fun main(){
    val start =3
    val end =19
    println(start.isPrime())
    println("/////////////////////////////////")
    println(gcd(25,5))
    println("/////////////////////////////////")
    println(coPrime(start,end))
    println("/////////////////////////////////")
    println(totient(315))
    println("/////////////////////////////////")
    println(primeFactors(28))
    println("/////////////////////////////////")
    println(primeFactorMultiplicity(315))
    println("//////////////////////////////////")
    println(eulersTotient2(315))
    println("//////////////////////////////////")
    println(listPrimeInRange(start,end))
    println("/////////////////////////////////")
    println(goldbach(33))
    println("/////////////////////////////////")
    goldbachList(5,12)
}

fun Int.isPrime():Boolean  //No.1
{
    for(i in 2 until this/2)
    {
        if(this%i==0)
            return false
    }
    return true
}

fun gcd(x:Int,y:Int) : Int = if (y == 0) {x} else {gcd(y,x%y)} //No.2

fun coPrime(x:Int,y:Int) : Boolean= gcd(x,y)==1 //No.3

fun totient(x:Int): Int //No.4
{
    var count =0
    for(i in 2..x)
    {
        if(coPrime(x,i)) {
            count+=1
        }
    }
    return count
}

fun primeFactors(x:Int):ArrayList<Int> //No.5
{
    var n:Int=x
    val list=ArrayList<Int>()
    for(i in 2..7 )
    {

        while(n%i==0)
        {
            list.add(i)
            n/=i
        }
    }
    return list
}

fun primeFactorMultiplicity(x:Int):HashMap<Int,Int> //No.6
{
    val list=primeFactors(x)
    val map=HashMap<Int,Int>()
    for(i in list)
    {
        map[i] = list.count{ e->e==i}
    }
    return map
}

fun eulersTotient2(x:Int):Int //No.7
{
    var list: ArrayList<Int> = primeFactors(x)
    list= list.toSet().toList() as ArrayList<Int>
    val map = primeFactorMultiplicity(x)
    var phi=1
    var value:Int
    for(i in list)
    {
        value = map.get(i)!!
        phi*=(i-1)*((i.toDouble().pow(value-1)).toInt())
    }
    phi--
    return phi
}

//No.8 ?

fun listPrimeInRange(x:Int,y:Int):List<Int> //No.9
{
    val list=ArrayList<Int>()
    for(i in x..y)
    {
        if(i.isPrime())
        {
            list.add(i)
        }
    }
    return list
}

fun goldbach(x:Int): List<Int> //No.10
{
    val list: ArrayList<Int> = ArrayList<Int>()
    for (i in 1 until x / 2) {
        if (list.size == 2) break
        if (i.isPrime()) {
            if ((x - i).isPrime()) {
                list.add(i)
                list.add(x - i)
            }
        }
    }
    return list
}

fun goldbachList(x:Int,y:Int):ArrayList<Int> //No.11
{
    val list=ArrayList<Int>()
    for(i in x..y)
    {
        if(i%2==0)
        {
            list.add(i)
            println("$i = ${goldbach(i)[0]} + ${goldbach(i)[1]}")
        }
    }
    return list
}
