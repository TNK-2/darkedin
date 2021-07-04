package infra.repositories.db

import java.sql.Timestamp

import domain.models.User
import domain.repositories.UserRepository
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserRepositoryImpl @Inject()
  (dbConfigProvider: DatabaseConfigProvider)
  (implicit ec: ExecutionContext) extends UserRepository
{
  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._

  override def findById(id: Int): Future[Option[User]] = db.run {
    Users
      .filter(_.id === id.bind)
      .result
      .map(_.headOption.map( user =>
        User(
          id = user.id,
          name = user.name,
          gitUrl = user.gitUrl,
          gitName = user.gitName,
          avatarUrl = user.avatarUrl,
          accessToken = Some(user.accessToken),
          tokenExpiredAt = user.createdAt.toLocalDateTime,
          createdAt = user.createdAt.toLocalDateTime,
          updatedAt = user.updatedAt.toLocalDateTime
        )
      ))
  }

  // upsert
  override def save(user: User): Future[Int] = db.run(
    Users += UserTableRow(
      id = user.id,
      name = user.name,
      gitUrl = user.gitUrl,
      gitName = user.gitName,
      avatarUrl = user.avatarUrl,
      accessToken = user.accessToken.getOrElse(""),
      tokenExpiredAt = Timestamp.valueOf(user.tokenExpiredAt),
      createdAt = Timestamp.valueOf(user.createdAt),
      updatedAt = Timestamp.valueOf(user.updatedAt)
    )
  )

  private val Users = TableQuery[UserTable]

  private class UserTable(tag: Tag) extends Table[UserTableRow](tag, "user") {
    def id = column[Int]("id", O.PrimaryKey)
    def name = column[String]("name")
    def gitUrl = column[String]("git_url")
    def gitName = column[String]("git_name")
    def avatarUrl = column[String]("avatar_url")
    def accessToken = column[String]("access_token")
    def tokenExpireAt = column[Timestamp]("token_expire_at")
    def createdAt = column[Timestamp]("created_at")
    def updatedAt = column[Timestamp]("updated_at")

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
    tokenExpiredAt: Timestamp,
    createdAt: Timestamp,
    updatedAt: Timestamp
  )

}
