package services

import com.google.inject.Inject
import models.{Idol, Idols}

import scala.concurrent.Future

class IdolService @Inject() (idols: Idols) {

    def listAllIdols: Future[Seq[Idol]] = {
        idols.listAll
    }
    
}