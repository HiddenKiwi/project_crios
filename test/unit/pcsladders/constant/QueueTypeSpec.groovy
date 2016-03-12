package pcsladders.constant

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification


@TestMixin(GrailsUnitTestMixin)
class QueueTypeSpec extends Specification {

    void "test de la methode getName"() {
        given: "une constante QueueType"
        QueueType.RANKED_TEAM_5x5

        when: "On execute la methode getName"
        String actualResult = QueueType.RANKED_TEAM_5x5.getName()

        then: "On obtient la chaine de caracteres attendue"
        actualResult.equals("RANKED_TEAM_5x5")
    }

    void "test de la methode toString"() {
        given: "une constante QueueType"
        QueueType.RANKED_TEAM_5x5

        when: "On execute la methode toString"
        String actualResult = QueueType.RANKED_TEAM_5x5.toString()

        then: "On obtient la chaine de caracteres attendue"
        actualResult.equals("RANKED_TEAM_5x5")
    }
}
