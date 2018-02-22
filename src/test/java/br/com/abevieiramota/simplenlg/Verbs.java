package br.com.abevieiramota.simplenlg;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import simplenlg.features.Feature;
import simplenlg.features.InterrogativeType;
import simplenlg.features.Tense;
import simplenlg.framework.DocumentElement;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

@RunWith(JUnit4.class)
public class Verbs {
	
	private static Lexicon lexicon;
	private static NLGFactory nlgFactory;
	private static Realiser realiser;

	@BeforeClass
	public static void beforeClass() {
		lexicon = Lexicon.getDefaultLexicon();
		nlgFactory = new NLGFactory(lexicon);
		realiser = new Realiser(lexicon);
	}

	@Test
	public void test() {
	
		SPhraseSpec p = nlgFactory.createClause();
		p.setSubject("Abelardo");
		p.setVerb("go");
		p.setObject("school");
		p.setFeature(Feature.TENSE, Tense.PAST);
		
		System.out.println(realiser.realise(p));
		
		p.setFeature(Feature.TENSE, Tense.FUTURE);
		
		System.out.println(realiser.realise(p));
		
		p.setFeature(Feature.NEGATED, true);
		
		System.out.println(realiser.realise(p));
		
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.YES_NO);
		
		System.out.println(realiser.realise(p));
		
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHERE);
		p.setFeature(Feature.NEGATED, false);
		p.setObject(null);
		
		DocumentElement doc = nlgFactory.createDocument("oi", p);
		
		System.out.println(realiser.realise(doc));
	}
}
