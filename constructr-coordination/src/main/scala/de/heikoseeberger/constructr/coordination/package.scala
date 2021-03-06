/*
 * Copyright 2015 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.heikoseeberger.constructr

import akka.http.scaladsl.model.ResponseEntity
import akka.stream.Materializer
import akka.stream.scaladsl.Sink
import java.util.Base64
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.Duration

package object coordination {

  val Traversable = scala.collection.immutable.Traversable
  type Traversable[+A] = scala.collection.immutable.Traversable[A]

  val Iterable = scala.collection.immutable.Iterable
  type Iterable[+A] = scala.collection.immutable.Iterable[A]

  val Seq = scala.collection.immutable.Seq
  type Seq[+A] = scala.collection.immutable.Seq[A]

  val IndexedSeq = scala.collection.immutable.IndexedSeq
  type IndexedSeq[+A] = scala.collection.immutable.IndexedSeq[A]

  def ignore(entity: ResponseEntity)(implicit ec: ExecutionContext, mat: Materializer) =
    entity.dataBytes.runWith(Sink.ignore)

  def encode(bytes: Array[Byte]): String = Base64.getUrlEncoder.encodeToString(bytes)

  def decode(s: String): Array[Byte] = Base64.getUrlDecoder.decode(s)

  def toSeconds(duration: Duration): String = (duration.toSeconds + 1).toString
}
