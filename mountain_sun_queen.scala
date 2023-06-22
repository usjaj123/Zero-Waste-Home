//This code implements a program to facilitate the transition to a zero waste lifestyle.

// Importing the necessary libraries and packages

import scala.collection.mutable
import java.util.Date
import java.io._

//Creating the case classes to represent the data

//class to represent a household item
case class Item(name: String, quantity: Int)

//class to represent a shopping trip 
case class Trip(date: Date, items: Seq[Item])

//class to represent the overall zero waste status of the household
case class ZeroWasteHome(itemsList: Seq[Item], tripsList: Seq[Trip])

//Creating the companion object
object ZeroWasteHome {
  
  //method to initialize a new Zero Waste Home
  def init()  = {
    ZeroWasteHome(Seq(), Seq())
  }

  //method to add a shopping trip to the Zero Waste Home
  def addTrip(home: ZeroWasteHome, date: Date, items: Seq[Item]): ZeroWasteHome = {
    ZeroWasteHome(itemsList = home.itemsList ++ items, 
                  tripsList = home.tripsList :+ Trip(date = date, items = items))
  }

  //method to remove an item from the Zero Waste Home
  def removeItem(home: ZeroWasteHome, itemName: String): ZeroWasteHome = {
    ZeroWasteHome(itemsList = home.itemsList.filter(item => item.name != itemName), //Remove the item from the list
                  tripsList = home.tripsList) //Don't mutate the trips list
  }

  //method to view the history of all shopping trips of the Zero Waste Home
  def viewHistory(home:ZeroWasteHome): Unit = {
    home.tripsList.foreach { trip => 
      println("Date: " + trip.date)
      println("Items:")
      trip.items.foreach { item =>
        println(item.name + " x " + item.quantity)
      }
    }
  }

  //method to save the Zero Waste Home data to a file 
  def save(home: ZeroWasteHome, filename: String): Unit = { 
    val OUTPUT_FILE = filename + ".obj"
    val outputStream = new ObjectOutputStream(new FileOutputStream(OUTPUT_FILE))
    outputStream.writeObject(home)
    outputStream.close()
  }

  //method to load the Zero Waste Home data from a file 
  def load(filename: String): ZeroWasteHome = {
    val INPUT_FILE = filename + ".obj"
    val inputStream = new ObjectInputStream(new FileInputStream(INPUT_FILE))
    val home = inputStream.readObject().asInstanceOf[ZeroWasteHome]
    inputStream.close()
    home
  }

}