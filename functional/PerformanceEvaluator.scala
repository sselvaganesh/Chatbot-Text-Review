package functional

/**
 * @author SSP
 * This class implements scala methods to implement text tokenization
 * and generate a score
 *
 */
class PerformanceEvaluator {

  var name1: String = "csr:"
  var name2: String = "client:"
  var evalPerson: String = "client:"

  var flg1: Boolean = false
  var flg2: Boolean = false

  var wordArray: Array[String] = new Array[String](10)
  var rateArray: Array[Int] = new Array[Int](10)

  wordArray = Array("excellent", "good", "satisfied", "bad", "worst", "waiting", "awful")
  rateArray = Array(10, 7, 3, -5, -10, -3, -9)

  //--------------------------------------------------

  //Identify csr's conversation from the whole chat
  def set(x: String) = {
    if (x == name1) flg1 = true; flg2 = false
    if (x == name2) flg1 = false; flg2 = true
  }

  //Match the person's name with conversation words
  def getName(x: String): String = if (flg1) (x + " " + name1) else return (x + " " + name2)

  //Check whether whose chat has to be analyzed
  def evalName(y: String) = if (y.endsWith(evalPerson)) true else false
  
  //Match the chat words and get the weightage for that words 
  def matchWord(word: String): Int = {

    if   (wordArray.contains(word)) rateArray(wordArray.indexOf(word))
    else  0

  }

  //Compute the weightage for the complete conversation. 
  def measureWeight(totWords: List[String]): Int = {

    if   (totWords == Nil) 0
    else { set(totWords.head)
           if (evalName(getName(totWords.head))) matchWord(totWords.head) + measureWeight(totWords.tail)
           else measureWeight(totWords.tail)
    	 }

  }

  //Entry point of the SCALA program. 
  def checkperformance(inpdata: String): Int = {
    var wholeList: List[String] = inpdata.split(" ").toList
    var rate: Int = 0
    rate = measureWeight(wholeList)
    return rate
  }

}