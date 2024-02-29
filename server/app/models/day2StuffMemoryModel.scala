package models


object day2StuffMemoryModel{
    private val people = List[String]("Noah", "James")
def nameExists(username : String) : Boolean ={
    people.contains(username)
}
}

