package eager

import net.fwbrasil.activate.ActivateContext
import net.fwbrasil.activate.storage.relational.PooledJdbcRelationalStorage
import net.fwbrasil.activate.storage.relational.idiom.postgresqlDialect

object activateContext extends ActivateContext {
  val storage = new PooledJdbcRelationalStorage {
    val jdbcDriver =  "org.postgresql.Driver"
    val user =        Some("eager")
    val password =    Some("eager")
    val url =         "jdbc:postgresql://localhost:5432/eager"
    val dialect =     postgresqlDialect
  }
}

import activateContext._

object EagerTest extends App {
  import eager.entities._

  Entities.run
}

