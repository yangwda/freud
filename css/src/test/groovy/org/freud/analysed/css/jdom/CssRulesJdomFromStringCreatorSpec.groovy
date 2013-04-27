package org.freud.analysed.css.jdom

import org.freud.analysed.css.rule.CssRule
import spock.lang.Specification
import spock.lang.Subject

import static CssParsingFixture.cssDeclaration
import static CssParsingFixture.cssRule
import static CssParsingFixture.cssSelector
import static CssParsingFixture.matchClosuresToList
import static org.freud.analysed.css.rule.selector.CssSelector.Combinator.DESCENDANT
import static org.freud.analysed.css.rule.selector.CssSelector.Type.CLASS
import static org.freud.analysed.css.rule.selector.CssSelector.Type.ID
import static org.freud.analysed.css.rule.selector.CssSelector.Type.TAG

class CssRulesJdomFromStringCreatorSpec extends Specification {

    @Subject
    CssRulesJdomFromStringCreator creator = new CssRulesJdomFromStringCreator()

    def 'creates CssRules from a String'() {
    when:
        List<CssRule> rules = creator.create(
                '''
                tag .class {
                 display: none;
                }
                #id .class {
                 display: none;
                }
                #id {
                 display: none;
                } ''') as List
    then:
        matchClosuresToList.call([
            cssRule([cssSelector(TAG, 'tag', [cssDeclaration('display', 'none')]),
                     cssSelector(CLASS, DESCENDANT, 'class', [cssDeclaration('display', 'none')])]),
            cssRule([cssSelector(ID, 'id', [cssDeclaration('display', 'none')]),
                     cssSelector(CLASS, DESCENDANT, 'class', [cssDeclaration('display', 'none')])]),
            cssRule([cssSelector(ID, 'id', [cssDeclaration('display', 'none')])]),
        ], rules)

    }
}
