package scorex.network.redone

import akka.actor.{Actor, ActorRef}
import scorex.network.NetworkController
import scorex.network.message.MessageSpec
import scorex.utils.ScorexLogging


/*  Synchronizing network & local views of an object, e.g. history(blockchain or blocktree), known peers list,
  segments dataset subset etc.

 */

//todo: anti-ddos?
trait ViewSynchronizer extends Actor with ScorexLogging {

  protected val networkControllerRef: ActorRef

  val messageSpecs: Seq[MessageSpec[_]]

  override def preStart: Unit = {
    networkControllerRef ! NetworkController.RegisterMessagesHandler(messageSpecs, self)
  }
}