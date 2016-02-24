
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/sharukh/play-java/eclipse/conf/routes
// @DATE:Wed Feb 24 03:01:48 CST 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
