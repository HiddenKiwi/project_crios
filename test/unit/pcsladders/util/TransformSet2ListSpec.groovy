package pcsladders.util

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class TransformSet2ListSpec extends Specification {

    void "test de la methode transformSet2List avec le type String"() {
        given: "Un Set de String valide"
        Set<String> set = new HashSet<String>()
        set.add("Bonjour")
        set.add("Au revoir")
        set.add("PCS")
        set.add("Project Conquerors")
        set.add("Pole LoL")
        set.add("Conseil des Anciens")

        when: "On execute la methode transformSet2List sur ce Set"
        List<String> list = TransformSet2List.transformSet2List(set)

        then: "On recupere une List de String contenant exactement les memes elements que le Set"
        list.size() == set.size()
        list.contains("Bonjour")
        list.contains("Au revoir")
        list.contains("PCS")
        list.contains("Project Conquerors")
        list.contains("Pole LoL")
        list.contains("Conseil des Anciens")
    }

    void "test de la methode transformSet2List avec le type Integer"() {
        given: "Un Set de Integer valide"
        Set<Integer> set = new HashSet<Integer>()
        set.add(1)
        set.add(2)
        set.add(3)
        set.add(4)
        set.add(5)
        set.add(6)

        when: "On execute la methode transformSet2List sur ce Set"
        List<Integer> list = TransformSet2List.transformSet2List(set)

        then: "On recupere une List de Integer contenant exactement les memes elements que le Set"
        list.size() == set.size()
        list.contains(1)
        list.contains(2)
        list.contains(3)
        list.contains(4)
        list.contains(5)
        list.contains(6)
    }

    void "test de la methode transformSet2List avec un Set vide"() {
        given: "Un Set de String vide"
        Set<Integer> set = new HashSet<Integer>()

        when: "On execute la methode transformSet2List sur ce Set"
        List<Integer> list = TransformSet2List.transformSet2List(set)

        then: "On recupere une List de String vide"
        list.size() == set.size()
    }
}
