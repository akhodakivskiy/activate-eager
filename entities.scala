package eager.entities

import scala.util.Random

import eager.activateContext._

class FreshMigration extends Migration {
  def timestamp = System.nanoTime

  def up = {
    createTableForAllEntities.ifNotExists
    createInexistentColumnsForAllEntities
    createReferencesForAllEntities.ifNotExists
  }
}

class User(val name: String) extends Entity
class File(val path: String, val user: User) extends Entity
class Thumbnail(val size: Int, val file: File) extends Entity

object Entities {
  def run = {

    def nextRandomId(n: Int) = Random.alphanumeric.take(n).mkString

    val user = transactional(new User(nextRandomId(10)))
    val file = transactional(new File(nextRandomId(20), user))
    val thumbnail = transactional(new Thumbnail(Random.nextInt, file))

    transactional {
      query { (user: User, file: File, thumbnail: Thumbnail) =>
        where((file.user :== user)
          :&& (thumbnail.file :== file))
            .select(user.eager, file.eager, thumbnail.eager)
            .orderBy(user.name)
      }
    } map { case (user, file, thumbnail) =>
      println(transactional(s"{$user.name} - ${file.path} - ${thumbnail.size}"))
    }
  }
}
