package section1

//import scala.collection.Set
//import scala.collection.mutable.Set

object FuncSet {

  /**
    * Der Typalias `Set` ist definiert als eine Funktion Int auf Boolean
    */
  type Set = Int => Boolean

  /**
    * Das leere Set ist definiert als eine Funktion, die immer false liefert.
    * Der Eingabeparameter kann hier ignoriert werden. Daher können wir `_`
    * schreiben.
    *
    * @return
    */
  def emptySet: Set = _ => false

  /**
    * Ein Set der Größe 1 kann als Funktion definiert werden,
    * welche ein übergebenes Element mit einem definierten
    * Wert vergleicht.
    *
    * @param element
    * @return
    */
  //def singleSet(element: Int): Set = ???
  def singleSet(element: Int): Set = i => i == element

  /**
    * Contains liefert true, wenn ein Set ein Element beinhaltet,
    * false, wenn es das nicht tut.
    *
    * @param s
    * @param element
    * @return
    */
  //def contains(s: Set, element: Int): Boolean = ???
  def contains(s: Set, element: Int): Boolean = s(element)

  /**
    * Union gibt ein neues Set zurück, welches alle Elemente
    * aus den beiden übergebenen Sets beinhaltet.
    *
    * @param s
    * @param t
    * @return
    */
  //def union(s: Set, t: Set): Set = ???
  //def union(s: Set, t: Set): Set = Set(s, t)
  def union(s: Set, t: Set): Set = element => s(element) || t(element)

  /*
  def union(s: Set, t: Set): Set = {
    val element: Set => s(element) + t(element)
      println(element)
      element
  }
  */

  /**
    * Intersect gibt ein neues Set zurück, welches
    * alle Elemente beinhaltet, die in beiden übergebenen
    * Sets vorkommen.
    *
    * @param s
    * @param t
    * @return
    */
  //def intersect(s: Set, t: Set): Set = ???
  def intersect(s: Set, t: Set): Set = element => s(element) && t(element)

  /**
    * Diff gibt ein neues Set zurück, welches alle Elemente
    * beinhaltet, die in `s` vorkommen, aber nicht in `t`.
    *
    * @param s
    * @param t
    * @return
    */
  //def diff(s: Set, t: Set): Set = ???
  //def diff(s: Set, t: Set): Set = s.&~(t)
  def diff(s: Set, t: Set): Set = element => s(element) && !t(element)

  /**
    * Filter gibt ein neues Set zurück, welches alle Elemente
    * beinhaltet, für die die Funktion `f` true zurück gibt.
    *
    * @param s
    * @param f
    * @return
    */
  //def filter(s: Set, f: Int => Boolean): Set = ???
  def filter(s: Set, f: Int => Boolean): Set = element => f(element)


  /**
    * Da wir nicht auf einer "richtigen" ;-) Datenstruktur arbeiten,
    * müssen wir für die folgenen Methoden einen kleinen Trick anwenden
    * und führen einen maximalen und minimalen Wertebereich für das Set
    * ein. -/+ 1000
    */
  val bound = 160

  /**
    * Forall prüft ob alle Elemente im Set auf das Prädikat `p` passt.
    * Hinweis: Zu prüfen ist im Wertebereich -/+ `bound`
    *
    * @param s
    * @param p
    * @return
    */
  //def forall(s: Set, p: Int => Boolean): Boolean = ???

  // for all: true -->
  //def forall(s: Set, p: Int => Boolean): Boolean = true

  // for all: true -->
  //def forall(s: Set, p: Int => Boolean): Boolean = { for(i <- Range(-bound, bound)) println(i); true }

  def forall(s: Set, p: Int => Boolean): Boolean = {
    var ret = true
    for(i <- Range(-bound, bound)) {
      val set1: Set = filter(singleSet(i), p)
      val bol1: Boolean = s(i)
      ret = bol1 == set1(i)
      println("[ " + i + "]  bol1: '" + bol1 + "', set1: '" + set1(i) + "' --> " + ret)
    }
    ret
    //!ret
  }

  /**
    * Exists prüft ob mindestens ein Element im Set auf das Prädikat
    * `p` passt. Hinweis: Zu prüfen ist im Wertebereich -/+ `bound`.
    *
    * @param s
    * @param p
    * @return
    */
  //def exists(s: Set, p: Int => Boolean): Boolean = ???
  //def exists(s: Set, p: Int => Boolean): Boolean = element => s(element => element == p)
  def exists(s: Set, p: Int => Boolean): Boolean = {
    var ret = false
    for (i <- Range(-bound, bound)) {
      ret = if (p(i)) {
        return true
      } else {
        false
      }
    }
    ret
  }

  /**
    * Map gibt ein neues Set zurück, bei dem jedes Element im Set
    * mit der Funktion `f` transfomiert wurde.
    * Hinweis: Zu prüfen ist im Wertebereich -/+ `bound`.
    *
    * @param s
    * @param f
    * @return
    */
  //def map(s: Set, f: Int => Int): Set = ???
  def map(s: Set, f: Int => Int): Set = {
    var set: Set = Nil

    for(i <- Range(-bound, bound)) {
      val set1: Set = filter(singleSet(f(i)), s)
      //set = union(set, set1) // --> <function1>
      set = set1

      //println("[ " + i + "]  f(i): '" + f(i) + "' --> " + set1(i))
      /*
        [ -151]  f(i): '-302' --> false
        [ -150]  f(i): '-300' --> true

        [ 10]  f(i): '20' --> true

        [ 14]  f(i): '28' --> false
        [ 15]  f(i): '30' --> true
        [ 16]  f(i): '32' --> false
      */
    }
    for(i <- Range(-bound, bound)) {
      println("[ " + i + "]  set(i): '" + set(i) + "'")
      /*
        [ -151]  set(i): 'false'
        [ -150]  set(i): 'true'
        [ -149]  set(i): 'false'

        [ 9]  set(i): 'false'
        [ 10]  set(i): 'true'
        [ 11]  set(i): 'false'

        [ 14]  set(i): 'false'
        [ 15]  set(i): 'true'
        [ 16]  set(i): 'false'
       */
    }
    set
    // java.lang.IndexOutOfBoundsException: 20
  }

  /**
    * toString liefert eine Stringrepräsentation des Sets.
    * Hinweis: Zu prüfen ist im Wertebereich -/+ `bound`.
    *
    * @param s
    * @return
    */
  def createString(s: Set): String = ???

}
