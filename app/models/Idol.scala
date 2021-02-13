package models

import javax.inject.Inject

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.{AbstractController, ControllerComponents}
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

case class Idol(id: Int, name: String)

class IdolTable(tag: Tag) extends Table[Idol](tag, "idols") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def * = (id, name) <> (Idol.tupled, Idol.unapply)
}

class Idols @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents) (implicit ec: ExecutionContext) extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile]
{
    val idols = TableQuery[IdolTable]

    def listAll: Future[Seq[Idol]] = {
        dbConfig.db.run(idols.result)
    }
}