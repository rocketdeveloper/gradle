/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.play.integtest.fixtures.app

import org.gradle.integtests.fixtures.SourceFile

class WithFailingTestsApp extends PlayNewApp {

    @Override
    List<SourceFile> getTestSources() {
        return super.getTestSources() + [
                sourceFile("test", "FailingApplicationSpec.scala", """
                        import org.junit.Ignore
                        import org.specs2.mutable._
                        import org.specs2.runner._
                        import org.junit.runner._

                        import play.api.test._
                        import play.api.test.Helpers._

                        @RunWith(classOf[JUnitRunner])
                        class FailingApplicationSpec extends Specification {

                          "Application" should {

                            "send 404 on a bad request" in new WithApplication{
                              route(FakeRequest(GET, "/boum")) must beNone
                            }

                            "render the index page" in new WithApplication{
                              val home = route(FakeRequest(GET, "/")).get

                              status(home) must equalTo(OK)
                              contentType(home) must beSome.which(_ == "text/html")
                              contentAsString(home) must contain ("This application content is wrong")
                            }
                          }
                        }"""),

                sourceFile("test", "FailingIntegrationSpec.scala", """
                        import org.specs2.mutable._
                        import org.specs2.runner._
                        import org.junit.runner._

                        import play.api.test._
                        import play.api.test.Helpers._

                        @RunWith(classOf[JUnitRunner])
                        class FailingIntegrationSpec extends Specification {

                          "Application" should {

                            "work from within a browser" in new WithBrowser {

                              browser.goTo("http://localhost:" + port)

                              browser.pageSource must contain("This application content is wrong.")
                            }
                          }
                        }""")
        ]

    }
}