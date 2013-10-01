
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[List[Task],Form[Task],play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(tasks: List[Task], taskForm: Form[Task]):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._


Seq[Any](format.raw/*1.43*/("""

"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/main("Todo list")/*5.19*/ {_display_(Seq[Any](format.raw/*5.21*/("""
    
    <h1>"""),_display_(Seq[Any](/*7.10*/tasks/*7.15*/.size())),format.raw/*7.22*/(""" task(s)</h1>
    
    <ul>
        """),_display_(Seq[Any](/*10.10*/for(task <- tasks) yield /*10.28*/ {_display_(Seq[Any](format.raw/*10.30*/("""
            <li>
                """),_display_(Seq[Any](/*12.18*/task/*12.22*/.label)),format.raw/*12.28*/("""
                
                """),_display_(Seq[Any](/*14.18*/form(routes.Application.deleteTask(task.id))/*14.62*/ {_display_(Seq[Any](format.raw/*14.64*/("""
                    <input type="submit" value="Delete">
                """)))})),format.raw/*16.18*/("""
            </li>
        """)))})),format.raw/*18.10*/("""
    </ul>
    
    <h2>Add a new task</h2>
    
    """),_display_(Seq[Any](/*23.6*/form(routes.Application.newTask())/*23.40*/ {_display_(Seq[Any](format.raw/*23.42*/("""
        
        """),_display_(Seq[Any](/*25.10*/inputText(taskForm("label")))),format.raw/*25.38*/(""" 
        
        <input type="submit" value="Create">
        
    """)))})),format.raw/*29.6*/("""
    
""")))})),format.raw/*31.2*/("""
"""))}
    }
    
    def render(tasks:List[Task],taskForm:Form[Task]): play.api.templates.HtmlFormat.Appendable = apply(tasks,taskForm)
    
    def f:((List[Task],Form[Task]) => play.api.templates.HtmlFormat.Appendable) = (tasks,taskForm) => apply(tasks,taskForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Sep 26 10:35:31 CEST 2013
                    SOURCE: /home/franck/M2/techno-du-web/play-2.2.0-RC2/MonAppTest/app/views/index.scala.html
                    HASH: db4b0208101f9c145b05dc4e8783937be6f3791d
                    MATRIX: 789->1|940->42|968->61|1004->63|1029->80|1068->82|1118->97|1131->102|1159->109|1232->146|1266->164|1306->166|1377->201|1390->205|1418->211|1489->246|1542->290|1582->292|1689->367|1749->395|1838->449|1881->483|1921->485|1976->504|2026->532|2127->602|2165->609
                    LINES: 26->1|30->1|32->4|33->5|33->5|33->5|35->7|35->7|35->7|38->10|38->10|38->10|40->12|40->12|40->12|42->14|42->14|42->14|44->16|46->18|51->23|51->23|51->23|53->25|53->25|57->29|59->31
                    -- GENERATED --
                */
            