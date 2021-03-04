package io;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// 2019 Eric Chan
public class JSoupUtil {

	public static Option<Element> elementFirst(Elements elements, String query) {
		return Option.of(elements.select(query).first());
	}

	public static Option<Element> elementFirst(Element element, String query) {
		return Option.of(element.select(query).first());
	}

	public static Stream<Element> elementStream(Element element, String query) {
		return Stream.ofAll(element.select(query).stream());
	}

	public static String firstText(Elements elements, String query) {
		return elementFirst(elements, query).map(Element::text).getOrElse("");
	}

	public static String firstText(Element element, String query) {
		return firstTextOption(element, query).getOrElse("");
	}

	public static String firstText(Element element, String query, String ops) {
		return firstTextOption(element, query).getOrElse(ops);
	}

	public static Option<String> firstTextOption(Element element, String query) {
		return elementFirst(element, query).map(Element::text).filter(s -> !s.isEmpty());
	}

	public static Option<String> firstAbsHref(Element element, String query) {
		return elementFirst(element, query).map(e -> e.attr("abs:href"));
	}

	public static Option<String> firstHref(Element element, String query) {
		return elementFirst(element, query).map(e -> e.attr("href"));
	}

	public static Option<Tuple2<String, String>> firstNameUrlSet(Element element, String query) {
		return elementFirst(element, query).map(e -> Tuple.of(e.text(), e.attr("href")));
	}

	public static Stream<Tuple2<String, String>> textUrlStream(Element element, String query) {
		return elementStream(element, query).map(e -> Tuple.of(e.text(), e.attr("href")));
	}
}
