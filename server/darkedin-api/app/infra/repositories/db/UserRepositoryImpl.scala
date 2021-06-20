package infra.repositories.db

import domain.models.User
import javax.inject.{Inject, Singleton}
import org.joda.time.DateTime
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

@Singleton
class UserRepositoryImpl @Inject()
  (dbConfigProvider: DatabaseConfigProvider)
  (implicit ec: ExecutionContext)
{
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class UserTable(tag: Tag) extends Table[UserTableRow](tag, "user") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def gitUrl = column[String]("git_url")
    def gitName = column[String]("git_name")
    def avatarUrl = column[String]("avatar_url")
    def accessToken = column[String]("access_token")
    def tokenExpireAt = column[java.sql.Timestamp]("token_expire_at")
    def createdAt = column[java.sql.Timestamp]("created_at")
    def updatedAt = column[java.sql.Timestamp]("updated_at")

    override def * =
      (id, name, gitUrl, gitName, avatarUrl, accessToken, tokenExpireAt, createdAt, updatedAt) <>
        ((UserTableRow.apply _).tupled, UserTableRow.unapply)
  }

  private case class UserTableRow(
    id:Int,
    name: String,
    gitUrl: String,
    gitName: String,
    avatarUrl: String,
    accessToken: String,
    tokenExpiredAt: java.sql.Timestamp,
    createdAt: java.sql.Timestamp,
    updatedAt: java.sql.Timestamp
  )

  private val user = TableQuery[UserTable]
}
